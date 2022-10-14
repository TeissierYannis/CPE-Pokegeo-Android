package fr.cpe.wolodiayannis.pokemongeo.utils;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;

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
        return new BitmapDrawable(Resources.getSystem(), Bitmap.createScaledBitmap(bitmap, width, height, true));
    }

    /**
     * Resize vector.
     *
     * @param drawable Drawable.
     * @param width    Width.
     * @param height   Height.
     * @return Drawable.
     */
    public static Drawable resizeVectorDrawable(VectorDrawable drawable, int width, int height) {
        // Convert vector drawable to drawable
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);
        return resize(new BitmapDrawable(Resources.getSystem(), bitmap), width, height);
    }
}
