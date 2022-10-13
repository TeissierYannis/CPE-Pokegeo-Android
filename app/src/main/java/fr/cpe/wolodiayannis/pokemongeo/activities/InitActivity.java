package fr.cpe.wolodiayannis.pokemongeo.activities;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import java.util.Objects;

import fr.cpe.wolodiayannis.pokemongeo.R;
import fr.cpe.wolodiayannis.pokemongeo.data.Datastore;
import fr.cpe.wolodiayannis.pokemongeo.entity.CaughtInventory;
import fr.cpe.wolodiayannis.pokemongeo.entity.CaughtPokemon;
import fr.cpe.wolodiayannis.pokemongeo.entity.item.ItemInventory;
import fr.cpe.wolodiayannis.pokemongeo.fetcher.CaughtInventoryFetcher;
import fr.cpe.wolodiayannis.pokemongeo.fetcher.ItemInventoryFetcher;
import fr.cpe.wolodiayannis.pokemongeo.fetcher.UserUpdateFetcher;
import fr.cpe.wolodiayannis.pokemongeo.utils.Logger;

public class InitActivity extends AppCompatActivity {

    /**
     * Datastore instance.
     */
    private Datastore datastore;

    /**
     * Mother layout.
     */
    private ConstraintLayout mother_Layout;
    /**
     * Starter box layout.
     */
    private ConstraintLayout starterBox_Layout;
    /**
     * Bulbasaur layout.
     */
    private ConstraintLayout starterBox_Bulbasaur;
    /**
     * Charmander Layout.
     */
    private ConstraintLayout starterBox_Charmander;
    /**
     * Squirtle layout.
     */
    private ConstraintLayout starterBox_Squirtle;
    /**
     * Professor image.
     */
    private ImageView imageView_Professor;
    /**
     * Bulbasaur Image.
     */
    private ImageView imageView_Bulbasaur;
    /**
     * Charmander Image.
     */
    private ImageView imageView_Charmander;
    /**
     * Squirtle Image.
     */
    private ImageView imageView_Squirtle;
    /**
     * Text dialog.
     */
    private TextView textView_Dialog;
    /**
     * Yes button.
     */
    private Button button_YESSS;

    /**
     * Bulbasaur id.
     */
    private final int BULBASAUR = 1;
    /**
     * Charmander id.
     */
    private final int CHARMANDER = 4;
    /**
     * Squirtle id.
     */
    private final int SQUIRTLE = 7;

    /**
     * Chosen pokemon id.
     */
    private int starterChoice = -1;

    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.init_fragment);

        Window window = getWindow();
        window.setStatusBarColor(getColor(R.color.colorPrimaryDark));

        this.datastore = Datastore.getInstance();

        mother_Layout = findViewById(R.id.init_fragment_Mother_constraintLayout);
        starterBox_Layout = findViewById(R.id.initFragment_constraintLayout_starterBox);
        starterBox_Bulbasaur = findViewById(R.id.initfrag_layout_starter_1);
        starterBox_Charmander = findViewById(R.id.initfrag_layout_starter_2);
        starterBox_Squirtle = findViewById(R.id.initfrag_layout_starter_3);
        imageView_Professor = findViewById(R.id.initFragment_ImageView_professor);
        imageView_Bulbasaur = findViewById(R.id.initFragment_ImageView_Bulbasaur);
        imageView_Charmander = findViewById(R.id.initFragment_ImageView_Charmander);
        imageView_Squirtle = findViewById(R.id.initFragment_ImageView_Squirtle);
        textView_Dialog = findViewById(R.id.initFragment_textView_dialog);
        button_YESSS = findViewById(R.id.initFragment_button_YESSS);

        // INIT
        starterBox_Layout.setVisibility(View.INVISIBLE);
        button_YESSS.setVisibility(View.INVISIBLE);
        textView_Dialog.setText(R.string.init_text_01);

        // changement du dialog lors du click sur la view
        mother_Layout.setOnClickListener(v -> {
            setDialogToNext();

            if (datastore.getUser().isInit()) {
                Intent intent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        // changement du dialog lors du click la box de bulbasaur
        starterBox_Bulbasaur.setOnClickListener(v -> setViewOnCLick(BULBASAUR));

        // changement du dialog lors du click la box de charmander
        starterBox_Charmander.setOnClickListener(v -> setViewOnCLick(CHARMANDER));

        // changement du dialog lors du click la box de squirtle
        starterBox_Squirtle.setOnClickListener(v -> setViewOnCLick(SQUIRTLE));

        // changement du dialog lors du click sur le bouton YESSS
        button_YESSS.setOnClickListener(v -> {
            button_YESSS.setVisibility(View.INVISIBLE);
            starterBox_Layout.setVisibility(View.INVISIBLE);
            imageView_Professor.setVisibility(View.VISIBLE);
            textView_Dialog.setText(R.string.init_text_04);
            datastore.getUser().setInit(true);
            addStarterToInventory();
            addItemsToInventory();
        });
    }

    /**
     * Switch the dialog to the next one.
     */
    private void setDialogToNext() {

        if (Objects.equals(
                textView_Dialog.getText().toString(),
                getResources().getString(R.string.init_text_01))) {
            textView_Dialog.setText(R.string.init_text_02);

        } else if (Objects.equals(
                textView_Dialog.getText().toString(),
                getResources().getString(R.string.init_text_02))) {
            textView_Dialog.setText(R.string.init_text_03);
            starterBox_Layout.setVisibility(View.VISIBLE);
            imageView_Professor.setVisibility(View.INVISIBLE);
            imageView_Bulbasaur.setVisibility(View.INVISIBLE);
            imageView_Charmander.setVisibility(View.INVISIBLE);
            imageView_Squirtle.setVisibility(View.INVISIBLE);

        } else {
            Logger.logOnUiThread("Click but no more dialog");
        }
    }

    /**
     * Set the view on click.
     *
     * @param STARTER_CLICK the starter clicked.
     */
    private void setViewOnCLick(int STARTER_CLICK) {

        imageView_Bulbasaur.setVisibility(View.INVISIBLE);
        imageView_Charmander.setVisibility(View.INVISIBLE);
        imageView_Squirtle.setVisibility(View.INVISIBLE);
        button_YESSS.setVisibility(View.VISIBLE);

        switch (STARTER_CLICK) {

            case BULBASAUR:
                imageView_Bulbasaur.setVisibility(View.VISIBLE);
                textView_Dialog.setText(R.string.init_text_bulbasaur_choice);
                starterChoice = BULBASAUR;
                break;

            case CHARMANDER:
                imageView_Charmander.setVisibility(View.VISIBLE);
                textView_Dialog.setText(R.string.init_text_charmander_choice);
                starterChoice = CHARMANDER;
                break;

            case SQUIRTLE:
                imageView_Squirtle.setVisibility(View.VISIBLE);
                textView_Dialog.setText(R.string.init_text_squirtle_choice);
                starterChoice = SQUIRTLE;
                break;

            default:
                Logger.logOnUiThread("Error on click starter");
                break;
        }
    }

    /**
     * Add start to the player inventory.
     */
    private void addStarterToInventory() {

        if (datastore.getCaughtInventory() == null) {
            datastore.setCaughtInventory(new CaughtInventory());
        }

        CaughtPokemon cp = datastore.getCaughtInventory().addPokemon(datastore.getPokemons().get(starterChoice), datastore.getUser().getId());
        // Avoid NetworkOnMainThreadException and call Datafetcher
        new Thread(() ->
        {
            (new CaughtInventoryFetcher(this)).addPokemonAndCache(cp);
            try {
                (new UserUpdateFetcher(this)).fetchAndCache(datastore.getUser(), true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    /**
     * Add base items to the inventory.
     */
    private void addItemsToInventory() {
        if (Datastore.getInstance().getItemInventory() == null) {
            Datastore.getInstance().setItemInventory(new ItemInventory());
        }

        // ajout de 20 pokéballs
        Datastore.getInstance().getItemInventory().addItem(
                Datastore.getInstance().getItemList().getPokeballList().get(1),
                20
        );
        // ajout de 10 potions
        Datastore.getInstance().getItemInventory().addItem(
                Datastore.getInstance().getItemList().getPotionList().get(0),
                10
        );
        // ajout de 3 revive
        Datastore.getInstance().getItemInventory().addItem(
                Datastore.getInstance().getItemList().getReviveList().get(0),
                3
        );

        // Avoid NetworkOnMainThreadException and call Datafetcher
        new Thread(() ->
        {
            try {
                (new ItemInventoryFetcher(this)).postAndCache(Datastore.getInstance().getItemInventory());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }
}
