package br.com.thiengo.laranjeirasguiacomercial.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import br.com.thiengo.laranjeirasguiacomercial.R;
import br.com.thiengo.laranjeirasguiacomercial.extras.Autenticacao;


public class ConfigLoginFragment extends Fragment
        implements View.OnClickListener, MaterialDialog.SingleButtonCallback {

    private boolean statusDialog;
    private EditText etEmailAtual;
    private EditText etEmailNovo;

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_config_login, container, false);

        View btAtualizar = view.findViewById(R.id.bt_atualizar);
        btAtualizar.setOnClickListener(this);

        etEmailAtual = (EditText) view.findViewById(R.id.et_email_atual);
        etEmailNovo = (EditText) view.findViewById(R.id.et_email_novo);

        statusDialog = false;
        if( savedInstanceState != null ){
            statusDialog = savedInstanceState.getBoolean(Autenticacao.STATUS_DIALOG_KEY);
            Autenticacao.ativarDialogSenha( getActivity(), this, statusDialog );
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(Autenticacao.STATUS_DIALOG_KEY, statusDialog);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View view) {
        statusDialog = true;
        Autenticacao.ativarDialogSenha( getActivity(), this, statusDialog );
    }

    @Override
    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

        statusDialog = false;
        if( which == DialogAction.POSITIVE ){
            new MaterialDialog.Builder(getActivity())
                    .title("Dados atualizados")
                    .content("Dados atualizados")
                    .positiveText("Ok")
                    .positiveColorRes( R.color.colorLink )
                    .show();
        }
    }
}
