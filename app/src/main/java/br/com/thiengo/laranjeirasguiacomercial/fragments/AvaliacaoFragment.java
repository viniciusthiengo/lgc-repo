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
import android.widget.RatingBar;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

import br.com.thiengo.laranjeirasguiacomercial.R;
import br.com.thiengo.laranjeirasguiacomercial.domain.Avaliacao;
import br.com.thiengo.laranjeirasguiacomercial.domain.Comercio;

/**
 * Created by viniciusthiengo on 18/01/17.
 */

public class AvaliacaoFragment extends DialogFragment
        implements View.OnClickListener, RatingBar.OnRatingBarChangeListener {

    public static final String KEY = "avaliacao_fragment";

    private Comercio comercio;
    private Avaliacao avaliacao;
    private RatingBar rbAvaliacao;
    private EditText etResposta;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        comercio = getArguments().getParcelable( Comercio.COMERCIO_SELECIONADO_KEY );
        avaliacao = getArguments().getParcelable( Avaliacao.AVALIACAO_KEY );
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
        View view = inflater.inflate(R.layout.fragment_avaliacao, null, false);

        TextView tvCabecalho = (TextView) view.findViewById( R.id.tv_cabecalho );
        tvCabecalho.setText( Html.fromHtml("Avaliação a <b>"+comercio.getNome()+"</b>:") );

        rbAvaliacao = (RatingBar) view.findViewById( R.id.rb_avaliacao );
        rbAvaliacao.setOnRatingBarChangeListener(this);
        etResposta = (EditText) view.findViewById( R.id.et_resposta );

        if( avaliacao !=null ){
            rbAvaliacao.setRating( avaliacao.getAvaliacao() );
            etResposta.setText( avaliacao.getMensagem() );
        }

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
                .title("Avaliação enviada")
                .content("Sua avaliação foi enviada com sucesso: " + rbAvaliacao.getRating())
                .positiveText("Ok")
                .show();
        }
        dismiss();
    }

    @Override
    public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
        if( ratingBar.getRating() < 1 ){
            ratingBar.setRating(1);
        }
    }
}
