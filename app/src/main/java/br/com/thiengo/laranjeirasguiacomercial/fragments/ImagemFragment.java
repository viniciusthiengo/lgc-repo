package br.com.thiengo.laranjeirasguiacomercial.fragments;

import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.thiengo.laranjeirasguiacomercial.R;
import br.com.thiengo.laranjeirasguiacomercial.domain.Imagem;

/**
 * Created by viniciusthiengo on 18/01/17.
 */

public class ImagemFragment extends DialogFragment implements View.OnClickListener {
    public static final String KEY = "dialog";
    public static final String GALERIA_KEY = "galeria";
    public static final String POSICAO_GALERIA_KEY = "posicao";
    public static final String STATUS_VIEWS_KEY = "status";

    private ArrayList<Imagem> imagens;
    private View rlCabecalho;
    private ImageView ivImagem;
    private ImageView ivImagemAnterior;
    private ImageView ivImagemPosterior;
    private TextView tvLegenda;
    private int posicao;
    private boolean statusViews = true;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_imagem, null, false);

        imagens = getArguments().getParcelableArrayList(GALERIA_KEY);
        posicao = getArguments().getInt(POSICAO_GALERIA_KEY);

        rlCabecalho = view.findViewById(R.id.rl_cabecalho);
        ivImagem = (ImageView) view.findViewById(R.id.iv_imagem);
        ivImagemAnterior = (ImageView) view.findViewById(R.id.iv_imagem_anterior);
        ivImagemPosterior = (ImageView) view.findViewById(R.id.iv_imagem_posterior);
        tvLegenda = (TextView) view.findViewById(R.id.tv_legenda);

        if( savedInstanceState != null ){
            posicao = savedInstanceState.getInt(POSICAO_GALERIA_KEY);
            statusViews = savedInstanceState.getBoolean(STATUS_VIEWS_KEY);
            esconderMostrarViews();
        }

        ivImagem.setImageResource( imagens.get(posicao).getResource() );
        ivImagem.setOnClickListener(this);

        View llLike = view.findViewById(R.id.ll_like);
        llLike.setOnClickListener(this);
        View ivCompartilhar = view.findViewById(R.id.iv_compartilhar);
        ivCompartilhar.setOnClickListener(this);

        ivImagemAnterior.setOnClickListener(this);
        ivImagemPosterior.setOnClickListener(this);
        atualizaBotoesStatus();

        esconderMostrarLegenda( statusViews );

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(POSICAO_GALERIA_KEY, posicao);
        outState.putBoolean(STATUS_VIEWS_KEY, !statusViews);
        super.onSaveInstanceState(outState);
    }

    private void esconderMostrarViews(){
        if( statusViews ){
            statusViews = false;
            rlCabecalho.setVisibility( View.GONE );
            ivImagemAnterior.setVisibility( View.GONE );
            ivImagemPosterior.setVisibility( View.GONE );
        }
        else{
            statusViews = true;
            rlCabecalho.setVisibility( View.VISIBLE );
            atualizaBotoesStatus();
        }
        esconderMostrarLegenda( statusViews );
    }

    private void esconderMostrarLegenda( boolean status ){
        String legenda = imagens.get( posicao ).getLegenda();

        tvLegenda.setVisibility( status ? View.VISIBLE : View.GONE );
        tvLegenda.setText( legenda );
        if( legenda == null || legenda.length() == 0 ){
            tvLegenda.setVisibility( View.GONE );
        }
    }

    @Override
    public void onClick(View view) {
        if( view.getId() == R.id.ll_like ){
            like( view );
        }
        else if( view.getId() == R.id.iv_compartilhar ){
            compartilharImagem( view );
        }
        else if( view.getId() == R.id.iv_imagem_anterior
                || view.getId() == R.id.iv_imagem_posterior ){
            atualizarImagem( view );
        }
        else if( view.getId() == R.id.iv_imagem ){
            esconderMostrarViews();
        }
    }

    private void like( View view ){
        Log.i("log", "like()");
    }

    private void compartilharImagem( View view ){
        int resID = imagens.get(posicao).getResource();
        Resources res = getActivity().getResources();
        Uri uri = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE + "://" +
                res.getResourcePackageName(resID) + '/' +
                res.getResourceTypeName(resID) + '/' +
                res.getResourceEntryName(resID) );

        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
        shareIntent.setType("image/*");
        startActivity(Intent.createChooser(shareIntent, "Compartilhar"));
    }

    private void atualizarImagem( View view ){
        if( view.getId() == R.id.iv_imagem_posterior ){
            posicao++;
            ivImagem.setImageResource( imagens.get(posicao).getResource() );
            esconderMostrarLegenda( true );
        }
        else{
            posicao--;
            ivImagem.setImageResource( imagens.get(posicao).getResource() );
            esconderMostrarLegenda( true );
        }
        atualizaBotoesStatus();
    }

    private void atualizaBotoesStatus(){
        ivImagemAnterior.setVisibility( View.GONE );
        ivImagemPosterior.setVisibility( View.GONE );

        if( !statusViews ){
            return;
        }

        if( posicao > 0 ){
            ivImagemAnterior.setVisibility( View.VISIBLE );
        }
        if( posicao < imagens.size() - 1 ){
            ivImagemPosterior.setVisibility( View.VISIBLE );
        }
    }
}
