package br.com.thiengo.laranjeirasguiacomercial.extras;

import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.content.ContextCompat;
import android.util.TypedValue;

/**
 * Created by viniciusthiengo on 19/01/17.
 */

public class Util {
    public static int converterSpParaPixels(Context context, float sp) {
        int px = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, sp, context.getResources().getDisplayMetrics());
        return px;
    }

    public static boolean ehPermitido( Context context, String permissaoCodigo ){
        int permissao = ContextCompat.checkSelfPermission(context, permissaoCodigo);
        return permissao == PackageManager.PERMISSION_GRANTED;
    }
}
