package fr.cpe.wolodiayannis.pokemongeo.viewmodel;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import fr.cpe.wolodiayannis.pokemongeo.SplashScreenActivity;

public class SplashScreenViewModel extends BaseObservable {

    private SplashScreenActivity splashScreenActivity = null;

    public void setSplashScreenActivity(SplashScreenActivity splashScreenActivity) {
        this.splashScreenActivity = splashScreenActivity;
        notifyChange();
    }
    
    @Bindable
    public String getLoadingText() {
        return splashScreenActivity.getLoadingText();
    }

    public void setLoadingText(String loadingText) {
        splashScreenActivity.setLoadingText(loadingText);
        notifyChange();
    }
}
