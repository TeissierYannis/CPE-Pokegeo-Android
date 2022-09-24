package fr.cpe.wolodiayannis.pokemongeo.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class DrawableResizer {

    /**
     * Resize a drawable.
     *
     * @param drawable Drawable.
     * @param width    Width.
     * @param height   Height.
     * @return Drawable.
     */
    public static Drawable resize(Drawable drawable, int width, int height) {
        Bitmap bitmap = ((BitmapDrawable) drawable).getBitmap();
        Drawable result = new BitmapDrawable(Resources.getSystem(), Bitmap.createScaledBitmap(bitmap, width, height, true));
        return result;
    }
}
