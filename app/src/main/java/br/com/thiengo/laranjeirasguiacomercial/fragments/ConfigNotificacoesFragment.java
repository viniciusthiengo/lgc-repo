package br.com.thiengo.laranjeirasguiacomercial.fragments;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.view.menu.MenuBuilder;
import android.support.v7.widget.LinearLayoutManager;
import android.text.Layout;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.SubMenu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import br.com.thiengo.laranjeirasguiacomercial.R;
import br.com.thiengo.laranjeirasguiacomercial.extras.Autenticacao;
import br.com.thiengo.laranjeirasguiacomercial.extras.Util;


public class ConfigNotificacoesFragment extends Fragment
        implements View.OnClickListener, MaterialDialog.SingleButtonCallback {

    private boolean statusDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_config_notificacoes, container, false);

        View btAtualizar = view.findViewById(R.id.bt_atualizar);
        btAtualizar.setOnClickListener(this);

        construirChecksCategoria( view );

        statusDialog = false;
        if( savedInstanceState != null ){
            statusDialog = savedInstanceState.getBoolean(Autenticacao.STATUS_DIALOG_KEY);
            Autenticacao.ativarDialogSenha( getActivity(), this, statusDialog );
        }

        return view;
    }

    private void construirChecksCategoria( View view ){
        String[] categorias = getActivity().getResources().getStringArray(R.array.cateogiras);

        LinearLayout llContainer = (LinearLayout) view.findViewById(R.id.ll_categoria_container);
        CheckBox checkBox;
        for( String categoria : categorias ){
            checkBox = (CheckBox) LayoutInflater.from(
                    getActivity()).inflate(R.layout.item_categoria_formulario,
                    null,
                    false);
            checkBox.setText(categoria);
            llContainer.addView(checkBox);
        }
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
                    .title("Notificações atualizadas")
                    .content("Dados de notificações atualizados com sucesso")
                    .positiveText("Ok")
                    .positiveColorRes( R.color.colorLink )
                    .show();
        }
    }
}
