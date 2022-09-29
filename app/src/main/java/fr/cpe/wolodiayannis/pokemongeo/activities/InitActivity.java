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
import fr.cpe.wolodiayannis.pokemongeo.utils.Logger;

public class InitActivity extends AppCompatActivity {

    /**
     * Datastore instance.
     */
    private Datastore datastore;

    private ConstraintLayout mother_Layout;
    private ConstraintLayout starterBox_Layout;
    private ConstraintLayout starterBox_Bulbasaur;
    private ConstraintLayout starterBox_Charmander;
    private ConstraintLayout starterBox_Squirtle;
    private ImageView imageView_Professor;
    private ImageView imageView_Bulbasaur;
    private ImageView imageView_Charmander;
    private ImageView imageView_Squirtle;
    private TextView textView_Dialog;
    private Button button_YESSS;

    private final int BULBASAUR = 1;
    private final int CHARMANDER = 4;
    private final int SQUIRTLE = 7;

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
        mother_Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setDialog_toNext();

                if (datastore.getUser().isInit()) {
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

        // changement du dialog lors du click la box de bulbasaur
        starterBox_Bulbasaur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setViewOnCLick(BULBASAUR);
            }
        });

        // changement du dialog lors du click la box de charmander
        starterBox_Charmander.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setViewOnCLick(CHARMANDER);
            }
        });

        // changement du dialog lors du click la box de squirtle
        starterBox_Squirtle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setViewOnCLick(SQUIRTLE);
            }
        });

        // changement du dialog lors du click sur le bouton YESSS
        button_YESSS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button_YESSS.setVisibility(View.INVISIBLE);
                textView_Dialog.setText(R.string.init_text_04);
                datastore.getUser().setInit(true);
                addStarterToInventory();
            }
        });
    }

    private void setDialog_toNext() {

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

    private void addStarterToInventory() {
        datastore.getCaughtInventory().addPokemon(datastore.getPokemons().get(starterChoice), datastore.getUser().getId());
    }
}
