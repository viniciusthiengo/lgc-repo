package br.com.thiengo.laranjeirasguiacomercial.extras;

import android.content.Context;
import android.text.InputType;

import com.afollestad.materialdialogs.MaterialDialog;

import br.com.thiengo.laranjeirasguiacomercial.R;

/**
 * Created by viniciusthiengo on 11/01/17.
 */

public class Autenticacao {
    public static final String STATUS_DIALOG_KEY = "status_dialog";

    public static void ativarDialogSenha(
            Context context,
            MaterialDialog.SingleButtonCallback callback,
            boolean statusDialog ){

        MaterialDialog.Builder mdBuilder = new MaterialDialog.Builder( context )
            .title("Confirmação")
            .content("Informe sua senha para prosseguir com a atualização")
            .positiveText("Ok")
            .inputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)
            .input("Senha", "", new MaterialDialog.InputCallback() {
                @Override
                public void onInput(MaterialDialog dialog, CharSequence input) {}
            })
            .positiveText("Continuar")
            .positiveColorRes( R.color.colorLink )
            .onPositive( callback )
            .negativeText("Voltar")
            .onNegative(callback)
            .cancelable(false);

        if( statusDialog ){
            mdBuilder.show();
        }
    }
}
