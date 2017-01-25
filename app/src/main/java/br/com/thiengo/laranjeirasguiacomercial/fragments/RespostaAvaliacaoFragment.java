package br.com.thiengo.laranjeirasguiacomercial.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

import br.com.thiengo.laranjeirasguiacomercial.R;
import br.com.thiengo.laranjeirasguiacomercial.domain.Resposta;

/**
 * Created by viniciusthiengo on 18/01/17.
 */

public class RespostaAvaliacaoFragment extends DialogFragment implements View.OnClickListener {
    public static final String KEY = "resposta_avaliacao_fragment";

    private Resposta resposta;
    private EditText etResposta;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        resposta = getArguments().getParcelable( Resposta.RESPOSTA_KEY );
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog( savedInstanceState );
        dialog.getWindow().requestFeature( Window.FEATURE_NO_TITLE );
        setCancelable(false);
        return dialog;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_resposta_avaliacao, null, false);

        TextView tvCabecalho = (TextView) view.findViewById( R.id.tv_cabecalho );
        tvCabecalho.setText( Html.fromHtml("Resposta a <b>"+resposta.getUser().getNome()+"</b>:") );

        etResposta = (EditText) view.findViewById( R.id.et_resposta );

        View tvEnviar = view.findViewById(R.id.tv_enviar);
        tvEnviar.setOnClickListener( this );

        View tvCancelar = view.findViewById(R.id.tv_cancelar);
        tvCancelar.setOnClickListener( this );

        return view;
    }

    @Override
    public void onClick(View view) {
        if( view.getId() == R.id.tv_enviar ){
            new MaterialDialog.Builder(getActivity())
                .title("Resposta enviada")
                .content("Sua resposta foi enviada com sucesso")
                .positiveText("Ok")
                .show();
        }
        dismiss();
    }
}
