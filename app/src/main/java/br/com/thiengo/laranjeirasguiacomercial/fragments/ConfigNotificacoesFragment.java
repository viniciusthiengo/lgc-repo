package br.com.thiengo.laranjeirasguiacomercial.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import java.util.ArrayList;

import br.com.thiengo.laranjeirasguiacomercial.R;
import br.com.thiengo.laranjeirasguiacomercial.adapters.ItemNotificacaoAdapter;
import br.com.thiengo.laranjeirasguiacomercial.domain.NotificacaoImpl;
import br.com.thiengo.laranjeirasguiacomercial.extras.Autenticacao;
import br.com.thiengo.laranjeirasguiacomercial.extras.Mock;


public class ConfigNotificacoesFragment extends Fragment
        implements View.OnClickListener, MaterialDialog.SingleButtonCallback {

    private static final String CATEGORIAS_KEY = "categorias_key";
    private static final String COMERCIOS_KEY = "comercios_key";

    private boolean statusDialog;
    private ArrayList<NotificacaoImpl> categorias;
    private ArrayList<NotificacaoImpl> comercios;

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

        statusDialog = false;
        if( savedInstanceState != null ){
            categorias = savedInstanceState.getParcelableArrayList( CATEGORIAS_KEY );
            comercios = savedInstanceState.getParcelableArrayList( COMERCIOS_KEY );
            statusDialog = savedInstanceState.getBoolean(Autenticacao.STATUS_DIALOG_KEY);
            Autenticacao.ativarDialogSenha( getActivity(), this, statusDialog );
        }
        else{
            categorias = Mock.obterCategoriasNotificacao( getActivity() );
            comercios = Mock.obterComerciosNotificacao();
        }

        RecyclerView rvCategoria = (RecyclerView) view.findViewById( R.id.rv_categorias );
        LinearLayoutManager layoutManager = new LinearLayoutManager( getActivity() );
        ItemNotificacaoAdapter adapter = new ItemNotificacaoAdapter( categorias );
        layoutManager.setAutoMeasureEnabled(true);
        rvCategoria.setNestedScrollingEnabled(false);
        rvCategoria.setHasFixedSize(false);
        rvCategoria.setLayoutManager( layoutManager );
        rvCategoria.setAdapter( adapter );

        RecyclerView rvComercios = (RecyclerView) view.findViewById( R.id.rv_comercios );
        layoutManager = new LinearLayoutManager( getActivity() );
        adapter = new ItemNotificacaoAdapter( comercios );
        layoutManager.setAutoMeasureEnabled(true);
        rvComercios.setNestedScrollingEnabled(false);
        rvComercios.setHasFixedSize(false);
        rvComercios.setLayoutManager( layoutManager );
        rvComercios.setAdapter( adapter );

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putBoolean(Autenticacao.STATUS_DIALOG_KEY, statusDialog);
        outState.putParcelableArrayList(CATEGORIAS_KEY, categorias);
        outState.putParcelableArrayList(COMERCIOS_KEY, comercios);
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
