package br.com.thiengo.laranjeirasguiacomercial.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.List;

import br.com.thiengo.laranjeirasguiacomercial.ComercioActivity;
import br.com.thiengo.laranjeirasguiacomercial.R;
import br.com.thiengo.laranjeirasguiacomercial.domain.Resposta;
import br.com.thiengo.laranjeirasguiacomercial.fragments.ImagemFragment;
import br.com.thiengo.laranjeirasguiacomercial.fragments.RespostaAvaliacaoFragment;

/**
 * Created by viniciusthiengo on 12/01/17.
 */

public class RespostasAdapter extends RecyclerView.Adapter<RespostasAdapter.ViewHolder> {

    private Context context;
    private List<Resposta> respostas;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        CircularImageView civPerfil;
        TextView tvNome;
        TextView tvData;
        TextView tvResposta;
        TextView tvResponder;

        ViewHolder(View itemView) {
            super(itemView);

            civPerfil = (CircularImageView) itemView.findViewById(R.id.civ_perfil);
            tvNome = (TextView) itemView.findViewById(R.id.tv_nome);
            tvData = (TextView) itemView.findViewById(R.id.tv_data);
            tvResposta = (TextView) itemView.findViewById(R.id.tv_resposta);
            tvResponder = (TextView) itemView.findViewById(R.id.tv_responder);

            tvResponder.setOnClickListener( this );
        }

        private void setData( Resposta resposta ){
            civPerfil.setImageResource( resposta.getUser().getImagem() );
            tvNome.setText( resposta.getUser().getNome() );
            tvData.setText( resposta.getData().getDataFormatada() );
            tvResposta.setText( resposta.getResposta() );
        }

        @Override
        public void onClick(View view) {
            FragmentManager fragManager = ((ComercioActivity) context).getSupportFragmentManager();
            FragmentTransaction ft = fragManager.beginTransaction();
            Fragment fragAnterior = fragManager.findFragmentByTag( RespostaAvaliacaoFragment.KEY );
            if (fragAnterior != null) {
                ft.remove(fragAnterior);
            }
            ft.addToBackStack(null);

            Bundle dados = new Bundle();
            dados.putParcelable(
                    Resposta.RESPOSTA_KEY,
                    respostas.get( getAdapterPosition() ) );

            DialogFragment dialog = new RespostaAvaliacaoFragment();
            dialog.setArguments(dados);
            dialog.show(ft, RespostaAvaliacaoFragment.KEY);
        }
    }

    public RespostasAdapter(Context context){
        this.context = context;
        respostas = new ArrayList<>();
    }

    @Override
    public RespostasAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
            .from(parent.getContext())
            .inflate(R.layout.item_resposta, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData( respostas.get( position ) );
    }

    @Override
    public int getItemCount() {
        return respostas.size();
    }

    public void setRespostas( List<Resposta> respostas ){
        this.respostas.clear();
        this.respostas.addAll( respostas );
        notifyDataSetChanged();
    }
}
