package fr.cpe.wolodiayannis.pokemongeo.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import java.sql.Timestamp;

import fr.cpe.wolodiayannis.pokemongeo.R;
import fr.cpe.wolodiayannis.pokemongeo.data.Datastore;
import fr.cpe.wolodiayannis.pokemongeo.databinding.PokemonFightPopupBinding;
import fr.cpe.wolodiayannis.pokemongeo.entity.CaughtPokemon;
import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;
import fr.cpe.wolodiayannis.pokemongeo.entity.PokemonFight;

public class FightFragment extends Fragment {

    private PokemonFightPopupBinding binding;

    /**
     * User pokemon.
     */
    private Pokemon userPokemon;

    /**
     * Opponent pokemon.
     */
    private Pokemon opponentPokemon;

    /**
     * Pokemon fight
     */
    PokemonFight pokemonFight;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        System.out.println("FightFragment.onCreateView");
        // Hide navigation bar
        requireActivity().findViewById(R.id.bottom_navigation).setVisibility(View.GONE);

        this.binding = DataBindingUtil.inflate(inflater, R.layout.pokemon_fight_popup, container, false);
        this.binding.enemyPokemonName.setText(
                this.opponentPokemon.getName().substring(0, 1).toUpperCase() + this.opponentPokemon.getName().substring(1)
        );
        this.binding.pokemonfightPlayerPokemonName.setText(
                this.userPokemon.getName().substring(0, 1).toUpperCase() + this.userPokemon.getName().substring(1)
        );

        Drawable drawableWildPokemon = ContextCompat.getDrawable(requireContext(), this.opponentPokemon.getImageID());
        Drawable drawableUserPokemon = ContextCompat.getDrawable(requireContext(), this.userPokemon.getImageID());
        this.binding.pokemonfightImageWildPokemon.setImageDrawable(drawableWildPokemon);
        this.binding.pokemonfightImagePlayerPokemon.setImageDrawable(drawableUserPokemon);

        CaughtPokemon userCaughtPokemon = Datastore.getInstance()
                .getCaughtInventory()
                .getCaughtPokemonFromPokemonID(this.userPokemon.getId());

        if (this.pokemonFight == null) {
            this.pokemonFight = new PokemonFight(this.userPokemon, this.opponentPokemon, userCaughtPokemon.getCurrentLifeState(), this.opponentPokemon.getHp());
            this.updateOpponentLifeBar(this.opponentPokemon, this.pokemonFight.getOpponentLifePoints());
            this.updateUserLifeBar(this.userPokemon, userCaughtPokemon);
        } else {
            this.updateLifeBarColor();
            this.updateLifeBarProgress();
        }

        // Return to map fragment
        this.binding.pokemonfightActionsBox.fightpopupButtonRun.setOnClickListener(v -> {
            this.onEscape();
        });

        this.binding.pokemonfightActionsBox.fightpopupButtonFight.setOnClickListener(v -> {
            this.deactivateAllButtons();

            this.playerAttack();

            // 2% chance to exit the fight and disappear from the map
            if ((int) (Math.random() * 100) < 2) {
                onEscape();
            }

            this.opponentAttack();

            // Set 3 second timeout to avoid spamming
            this.binding.pokemonfightActionsBox.fightpopupButtonFight.postDelayed(this::activeAllButtons, 100);
        });

        // On click on bag button try to capture the pokemon
        this.binding.pokemonfightActionsBox.fightpopupButtonBag.setOnClickListener(v -> {

            this.deactivateAllButtons();

            this.animateCapture();

            int random = (int) (Math.random() * 100);

            // Timeout 3 seconds
            this.binding.pokemonfightActionsBox.fightpopupButtonBag.postDelayed(() -> {

                double ballRate = 0.25;
                double lifeRate = 1 - ((double) this.pokemonFight.getOpponentLifePoints() / (double) this.pokemonFight.getOpponentPokemon().getHp());

                double totalRate = ballRate + (lifeRate * 0.5);

                if (random < totalRate * 100) {
                    this.onCapture();
                } else {
                    // 1% chance to exit the fight and disappear from the map
                    if (random < 1) {
                        this.onEscape();
                    }
                    this.opponentAttack();
                }
                this.activeAllButtons();
            }, 2000);

        });

        // On click on pokemon button, open a modal to select a pokemon
        this.binding.pokemonfightActionsBox.fightpopupButtonPokemon.setOnClickListener(v -> {
            this.deactivateAllButtons();

            // Switch fragment without closing the current one
            CaughtFragment fragment = new CaughtFragment();
            fragment.setSwitchListener(this::onSwitchPokemon);

            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .addToBackStack("fight")
                    .setReorderingAllowed(true)
                    .commit();

            this.activeAllButtons();
        });

        return binding.getRoot();
    }

    /**
     * On win
     */
    private void onWin() {
        Toast.makeText(getContext(), "You win !", Toast.LENGTH_SHORT).show();
        // remove pokemon from the map
        Datastore.getInstance().getSpawnedPokemons().remove(this.opponentPokemon);

        /**
         * Update user pokemon life in the caught inventory
         */
        this.updateUserPokemon();

        // navigation bar
        requireActivity().findViewById(R.id.bottom_navigation).setVisibility(View.VISIBLE);
        requireActivity().getSupportFragmentManager().popBackStack();
    }

    /**
     * Update user pokemon in datastore
     */
    private void updateUserPokemon() {
        // Update the pokemon in the user inventory
        Datastore.getInstance().getCaughtInventory().updateCaughtPokemonLife(
                userPokemon,
                this.pokemonFight.getPlayerLifePoints()
        );
    }

    /**
     * On loose
     */
    private void onLoose() {
        Toast.makeText(getContext(), "You loose !", Toast.LENGTH_SHORT).show();
        // remove pokemon from the map
        Datastore.getInstance().getSpawnedPokemons().remove(this.opponentPokemon);

        /**
         * Update user pokemon life in the caught inventory
         */
        this.updateUserPokemon();

        // navigation bar
        requireActivity().findViewById(R.id.bottom_navigation).setVisibility(View.VISIBLE);
        requireActivity().getSupportFragmentManager().popBackStack();
    }

    /**
     * On capture
     */
    private void onCapture() {
        Toast.makeText(getContext(), "Pokemon captured !", Toast.LENGTH_SHORT).show();
        // remove pokemon from the map
        Datastore.getInstance().getSpawnedPokemons().remove(this.opponentPokemon);

        /**
         * Update user pokemon life in the caught inventory
         */
        this.updateUserPokemon();

        this.updateCaughtPokemon();

        // navigation bar
        requireActivity().findViewById(R.id.bottom_navigation).setVisibility(View.VISIBLE);
        requireActivity().getSupportFragmentManager().popBackStack();
    }

    /**
     * Update the caught pokemon in the datastore
     */
    private void updateCaughtPokemon() {
        CaughtPokemon caughtPokemon = new CaughtPokemon(
                Datastore.getInstance().getUser().getId(),
                this.opponentPokemon.getId(),
                0,
                this.pokemonFight.getOpponentLifePoints(),
                new Timestamp(System.currentTimeMillis())
        );
        Datastore.getInstance().getCaughtInventory().addPokemon(this.opponentPokemon, caughtPokemon);
    }

    /**
     * On pokemon escape
     */
    private void onEscape() {
        Toast.makeText(getContext(), "Pokemon escaped !", Toast.LENGTH_SHORT).show();
        // remove pokemon from the map
        Datastore.getInstance().getSpawnedPokemons().remove(this.opponentPokemon);

        /**
         * Update user pokemon life in the caught inventory
         */
        this.updateUserPokemon();

        // navigation bar
        requireActivity().findViewById(R.id.bottom_navigation).setVisibility(View.VISIBLE);
        requireActivity().getSupportFragmentManager().popBackStack();
    }

    /**
     * Animate capture
     */
    private void animateCapture() {
        // replace opponent pokemon with pokeball image
        this.binding.pokemonfightImageWildPokemon.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.pokeball));
        // Animate the pokeball
        this.binding.pokemonfightImageWildPokemon.animate().rotation(20).setDuration(500);
        this.binding.pokemonfightImageWildPokemon.animate().scaleX(0.5f).setDuration(500);
        this.binding.pokemonfightImageWildPokemon.animate().scaleY(0.5f).setDuration(500);
        // remove animation after 1.5s
        this.binding.pokemonfightImageWildPokemon.postDelayed(() -> {
            this.binding.pokemonfightImageWildPokemon.animate().rotation(-20).setDuration(500);
            this.binding.pokemonfightImageWildPokemon.postDelayed(() -> {
                this.binding.pokemonfightImageWildPokemon.animate().rotation(20).setDuration(500);
                this.binding.pokemonfightImageWildPokemon.postDelayed(() -> {
                    this.binding.pokemonfightImageWildPokemon.animate().rotation(0).setDuration(500);
                    this.binding.pokemonfightImageWildPokemon.animate().scaleX(1f).setDuration(500);
                    this.binding.pokemonfightImageWildPokemon.animate().scaleY(1f).setDuration(500);
                }, 500);
            }, 500);
        }, 500);
    }

    /**
     * Opponent attack
     */
    private void opponentAttack() {
        this.pokemonFight.attack(this.opponentPokemon, this.userPokemon);
        this.binding.pokemonfightLifebarPlayer.setProgress(this.pokemonFight.getPlayerLifePoints());

        // replace opponent pokemon with pokemon image
        this.binding.pokemonfightImageWildPokemon.setImageDrawable(ContextCompat.getDrawable(requireContext(), this.opponentPokemon.getImageID()));
        this.pokemonFight.attack(this.opponentPokemon, this.userPokemon);
        this.binding.pokemonfightLifebarPlayer.setProgress(this.pokemonFight.getPlayerLifePoints());

        Drawable playerProgressDrawable = this.binding.pokemonfightLifebarPlayer.getProgressDrawable().mutate();
        playerProgressDrawable.setTint(ContextCompat.getColor(requireContext(), this.pokemonFight.getPlayerProgressBarColor()));

        if (this.pokemonFight.isPlayerPokemonDead()) {
            this.onLoose();
            this.updateUserPokemon();
            requireActivity().getSupportFragmentManager().popBackStack();
        }
    }

    /**
     * Player attack
     */
    private void playerAttack() {
        this.pokemonFight.attack(this.userPokemon, this.opponentPokemon);
        this.binding.lifeBarRight.setProgress(this.pokemonFight.getOpponentLifePoints());

        Drawable enemyProgressDrawable = this.binding.lifeBarRight.getProgressDrawable().mutate();
        enemyProgressDrawable.setTint(ContextCompat.getColor(requireContext(), this.pokemonFight.getEnemyProgressBarColor()));

        if (this.pokemonFight.isOpponentPokemonDead()) {
            this.onWin();
            this.updateUserPokemon();
            requireActivity().getSupportFragmentManager().popBackStack();
        }
    }

    /**
     * On switch pokemon
     *
     * @param pokemon       pokemon
     * @param caughtPokemon caught pokemon
     */
    private void onSwitchPokemon(Pokemon pokemon, CaughtPokemon caughtPokemon) {
        this.updateUserPokemon();

        this.userPokemon = pokemon;
        pokemonFight.switchPlayerPokemon(pokemon, caughtPokemon.getCurrentLifeState());
        // go back to this fragment and reupdate bar
        requireActivity().getSupportFragmentManager().popBackStack();
    }

    /**
     * Update lifebar
     */
    private void updateLifeBarProgress() {
        this.binding.pokemonfightLifebarPlayer.setProgress(this.pokemonFight.getPlayerLifePoints());
        this.binding.lifeBarRight.setProgress(this.pokemonFight.getOpponentLifePoints());
        // reset max
        this.binding.pokemonfightLifebarPlayer.setMax(this.userPokemon.getHp());
        this.binding.lifeBarRight.setMax(this.opponentPokemon.getHp());

    }

    /**
     * Deactivate all buttons
     */
    private void deactivateAllButtons() {
        this.binding.pokemonfightActionsBox.fightpopupButtonFight.setEnabled(false);
        this.binding.pokemonfightActionsBox.fightpopupButtonBag.setEnabled(false);
        this.binding.pokemonfightActionsBox.fightpopupButtonPokemon.setEnabled(false);
    }

    /**
     * Activate all buttons
     */
    private void activeAllButtons() {
        this.binding.pokemonfightActionsBox.fightpopupButtonFight.setEnabled(true);
        this.binding.pokemonfightActionsBox.fightpopupButtonBag.setEnabled(true);
        this.binding.pokemonfightActionsBox.fightpopupButtonPokemon.setEnabled(true);
    }

    /**
     * Update opponent life bar.
     *
     * @param opponentPokemon opponent pokemon
     * @param lifePoints      life points
     */
    private void updateOpponentLifeBar(Pokemon opponentPokemon, int lifePoints) {
        this.binding.lifeBarRight.setMax(opponentPokemon.getHp());
        this.binding.lifeBarRight.setProgress(lifePoints);
    }

    /**
     * Update user life bar.
     *
     * @param userPokemon       user pokemon
     * @param userCaughtPokemon user caught pokemon
     */
    private void updateUserLifeBar(Pokemon userPokemon, CaughtPokemon userCaughtPokemon) {
        this.binding.pokemonfightLifebarPlayer.setMax(userPokemon.getHp());
        this.binding.pokemonfightLifebarPlayer.setProgress(userCaughtPokemon.getCurrentLifeState());
    }

    /**
     * Update lifebar color according to the life points
     */
    private void updateLifeBarColor() {
        Drawable enemyProgressDrawable = this.binding.lifeBarRight.getProgressDrawable().mutate();
        enemyProgressDrawable.setTint(ContextCompat.getColor(requireContext(), this.pokemonFight.getEnemyProgressBarColor()));

        Drawable playerProgressDrawable = this.binding.pokemonfightLifebarPlayer.getProgressDrawable().mutate();
        playerProgressDrawable.setTint(ContextCompat.getColor(requireContext(), this.pokemonFight.getPlayerProgressBarColor()));
    }

    /**
     * Set the user pokemon.
     *
     * @param userPokemon
     */
    public void setUserPokemon(Pokemon userPokemon) {
        this.userPokemon = userPokemon;
    }

    /**
     * Set the opponent pokemon.
     *
     * @param opponentPokemon
     */
    public void setOpponentPokemon(Pokemon opponentPokemon) {
        this.opponentPokemon = opponentPokemon;
    }

    /**
     * On Going back to this fragment, update the lifebar
     */
    @Override
    public void onResume() {
        super.onResume();
        this.updateLifeBarProgress();
        this.updateLifeBarColor();
    }

}
