package br.com.thiengo.laranjeirasguiacomercial.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.thiengo.laranjeirasguiacomercial.ComercioActivity;
import br.com.thiengo.laranjeirasguiacomercial.R;
import br.com.thiengo.laranjeirasguiacomercial.domain.Imagem;
import br.com.thiengo.laranjeirasguiacomercial.fragments.ImagemFragment;

/**
 * Created by viniciusthiengo on 12/01/17.
 */

public class GaleriaAdapter extends RecyclerView.Adapter<GaleriaAdapter.ViewHolder> {

    private ArrayList<Imagem> imagens;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView ivImagem;
        TextView tvLikes;

        ViewHolder(View itemView) {
            super(itemView);

            ivImagem = (ImageView) itemView.findViewById(R.id.iv_imagem);
            tvLikes = (TextView) itemView.findViewById(R.id.tv_likes);
            itemView.setOnClickListener(this);
        }

        private void setData( Imagem imagem ){
            ivImagem.setImageResource( imagem.getResource() );
            ivImagem.setContentDescription( imagem.getLegenda() );

            tvLikes.setText( String.valueOf( imagem.getNumLikes() ) );
            if( imagem.getNumLikes() > 0 ){
                tvLikes.setVisibility( View.VISIBLE );
            }
            else{
                tvLikes.setVisibility( View.GONE );
            }
        }

        @Override
        public void onClick(View view) {
            FragmentManager fragManager = ((ComercioActivity) context).getSupportFragmentManager();
            FragmentTransaction ft = fragManager.beginTransaction();
            Fragment fragAnterior = fragManager.findFragmentByTag( ImagemFragment.KEY );
            if (fragAnterior != null) {
                ft.remove(fragAnterior);
            }
            ft.addToBackStack(null);

            Bundle dados = new Bundle();
            dados.putParcelableArrayList(ImagemFragment.GALERIA_KEY, imagens);
            dados.putInt(ImagemFragment.POSICAO_GALERIA_KEY, getAdapterPosition());

            DialogFragment dialog = new ImagemFragment();
            dialog.setArguments(dados);
            dialog.show(ft, ImagemFragment.KEY);
        }
    }

    public GaleriaAdapter( Context context, ArrayList<Imagem> imagens){
        this.context = context;
        this.imagens = imagens;
    }

    @Override
    public GaleriaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.item_galeria, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData( imagens.get( position ) );
    }

    @Override
    public int getItemCount() {
        return imagens.size();
    }
}
