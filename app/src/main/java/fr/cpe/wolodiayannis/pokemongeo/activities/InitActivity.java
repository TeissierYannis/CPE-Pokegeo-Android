package fr.cpe.wolodiayannis.pokemongeo.activities;

import android.os.Build;
import android.os.Bundle;
import android.view.Window;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import fr.cpe.wolodiayannis.pokemongeo.R;
import fr.cpe.wolodiayannis.pokemongeo.data.Datastore;

public class InitActivity extends AppCompatActivity  {

    /**
     * Datastore instance.
     */
    private Datastore datastore;


    @RequiresApi(api = Build.VERSION_CODES.R)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starter_choice);

        Window window = getWindow();
        window.setStatusBarColor(getColor(R.color.colorPrimaryDark));

        this.datastore = Datastore.getInstance();

    }
}
