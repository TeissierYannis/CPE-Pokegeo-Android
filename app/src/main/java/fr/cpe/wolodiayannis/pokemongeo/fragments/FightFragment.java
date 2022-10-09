package fr.cpe.wolodiayannis.pokemongeo.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import java.sql.Timestamp;
import java.util.concurrent.atomic.AtomicInteger;

import fr.cpe.wolodiayannis.pokemongeo.R;
import fr.cpe.wolodiayannis.pokemongeo.data.Datastore;
import fr.cpe.wolodiayannis.pokemongeo.databinding.PokemonFightPopupBinding;
import fr.cpe.wolodiayannis.pokemongeo.entity.CaughtInventory;
import fr.cpe.wolodiayannis.pokemongeo.entity.CaughtPokemon;
import fr.cpe.wolodiayannis.pokemongeo.entity.Pokemon;
import fr.cpe.wolodiayannis.pokemongeo.entity.PokemonFight;
import fr.cpe.wolodiayannis.pokemongeo.listeners.PokedexListenerInterface;
import fr.cpe.wolodiayannis.pokemongeo.listeners.PokemonSwitchInterface;

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

        if (this.pokemonFight == null) {

            this.pokemonFight = new PokemonFight(this.userPokemon, this.opponentPokemon);

            this.binding.lifeBarRight.setMax(this.pokemonFight.getOpponentLifePoints());
            this.binding.lifeBarRight.setProgress(this.pokemonFight.getOpponentLifePoints());
        }
        this.binding.pokemonfightLifebarPlayer.setMax(this.pokemonFight.getPlayerLifePoints());
        this.binding.pokemonfightLifebarPlayer.setProgress(this.pokemonFight.getPlayerLifePoints());

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

                System.out.println("Random : " + random + " Total rate : " + totalRate);

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
            // Switch to the pokemon selection fragment
            CaughtFragment caughtFragment = new CaughtFragment();

            // set listener
            PokemonSwitchInterface listenerInterface = this::onSwitchPokemon;

            caughtFragment.setSwitchListener(listenerInterface);
            requireActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, caughtFragment)
                    .addToBackStack(null)
                    .commit();
        });

        return binding.getRoot();
    }

    private void onWin() {
        Toast.makeText(getContext(), "You win !", Toast.LENGTH_SHORT).show();
        // remove pokemon from the map
        Datastore.getInstance().getSpawnedPokemons().remove(this.opponentPokemon);

        /**
         * Update user pokemon life in the caught inventory
         */
        this.updateUserPokemon();

        requireActivity().getSupportFragmentManager().popBackStack();
    }

    private void updateUserPokemon() {
        // Update the pokemon in the user inventory
        Datastore.getInstance().getCaughtInventory().updateCaughtPokemonLife(
                userPokemon,
                this.pokemonFight.getPlayerLifePoints()
        );
    }

    private void onLoose() {
        Toast.makeText(getContext(), "You loose !", Toast.LENGTH_SHORT).show();
        // remove pokemon from the map
        Datastore.getInstance().getSpawnedPokemons().remove(this.opponentPokemon);

        /**
         * Update user pokemon life in the caught inventory
         */
        this.updateUserPokemon();

        requireActivity().getSupportFragmentManager().popBackStack();
    }

    private void onCapture() {
        Toast.makeText(getContext(), "Pokemon captured !", Toast.LENGTH_SHORT).show();
        // remove pokemon from the map
        Datastore.getInstance().getSpawnedPokemons().remove(this.opponentPokemon);

        /**
         * Update user pokemon life in the caught inventory
         */
        this.updateUserPokemon();

        this.updateCaughtPokemon();

        requireActivity().getSupportFragmentManager().popBackStack();
    }

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

    private void onEscape() {
        Toast.makeText(getContext(), "Pokemon escaped !", Toast.LENGTH_SHORT).show();
        // remove pokemon from the map
        Datastore.getInstance().getSpawnedPokemons().remove(this.opponentPokemon);

        /**
         * Update user pokemon life in the caught inventory
         */
        this.updateUserPokemon();

        requireActivity().getSupportFragmentManager().popBackStack();
    }

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
            requireActivity().getSupportFragmentManager().popBackStack();
        }
    }

    private void playerAttack() {
        this.pokemonFight.attack(this.userPokemon, this.opponentPokemon);
        this.binding.lifeBarRight.setProgress(this.pokemonFight.getOpponentLifePoints());

        Drawable enemyProgressDrawable = this.binding.lifeBarRight.getProgressDrawable().mutate();
        enemyProgressDrawable.setTint(ContextCompat.getColor(requireContext(), this.pokemonFight.getEnemyProgressBarColor()));

        if (this.pokemonFight.isOpponentPokemonDead()) {
            this.onWin();
            requireActivity().getSupportFragmentManager().popBackStack();
        }
    }

    private void onSwitchPokemon(Pokemon pokemon, CaughtPokemon caughtPokemon) {
        this.updateUserPokemon();

        this.userPokemon = pokemon;
        pokemonFight.switchPlayerPokemon(pokemon, caughtPokemon.getCurrentLifeState());
        System.out.println("PokemonFightFragment.onSwitchPokemon: " + pokemon.getName() + " " + caughtPokemon.getCurrentLifeState() + " " + pokemon.getHp());
        this.binding.pokemonfightLifebarPlayer.setMax(pokemon.getHp());
        this.binding.pokemonfightLifebarPlayer.setProgress(caughtPokemon.getCurrentLifeState());

        System.out.println("Pokemon switch to " + pokemon.getName());
        // go back to this fragment and pass the pokemon to the fight
        requireActivity().getSupportFragmentManager().popBackStack();
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


}
