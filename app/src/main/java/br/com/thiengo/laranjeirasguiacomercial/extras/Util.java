package br.com.thiengo.laranjeirasguiacomercial.extras;

import android.content.Context;
import android.util.TypedValue;

/**
 * Created by viniciusthiengo on 19/01/17.
 */

public class Util {
    public static int converterSpParaPixels(Context context, float sp) {
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
        return px;
    }
}
