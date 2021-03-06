package br.com.thiengo.laranjeirasguiacomercial.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.List;

import br.com.thiengo.laranjeirasguiacomercial.ComercioActivity;
import br.com.thiengo.laranjeirasguiacomercial.R;
import br.com.thiengo.laranjeirasguiacomercial.domain.Avaliacao;
import br.com.thiengo.laranjeirasguiacomercial.domain.Comercio;
import br.com.thiengo.laranjeirasguiacomercial.fragments.AvaliacaoFragment;

/**
 * Created by viniciusthiengo on 12/01/17.
 */

public class AvaliacoesAdapter extends RecyclerView.Adapter<AvaliacoesAdapter.ViewHolder> {

    private Context context;
    private List<Avaliacao> avaliacoes;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        CircularImageView civPerfil;
        TextView tvNome;
        TextView tvData;
        ImageView ivEstrela1;
        ImageView ivEstrela2;
        ImageView ivEstrela3;
        ImageView ivEstrela4;
        ImageView ivEstrela5;
        TextView tvAvaliacao;
        TextView tvAtualizar;
        RecyclerView rvRespostas;

        ViewHolder(View itemView) {
            super(itemView);

            civPerfil = (CircularImageView) itemView.findViewById(R.id.civ_perfil);
            tvNome = (TextView) itemView.findViewById(R.id.tv_nome);
            tvData = (TextView) itemView.findViewById(R.id.tv_data);
            ivEstrela1 = (ImageView) itemView.findViewById(R.id.iv_estrela_1);
            ivEstrela2 = (ImageView) itemView.findViewById(R.id.iv_estrela_2);
            ivEstrela3 = (ImageView) itemView.findViewById(R.id.iv_estrela_3);
            ivEstrela4 = (ImageView) itemView.findViewById(R.id.iv_estrela_4);
            ivEstrela5 = (ImageView) itemView.findViewById(R.id.iv_estrela_5);
            tvAvaliacao = (TextView) itemView.findViewById(R.id.tv_avaliacao);
            tvAtualizar = (TextView) itemView.findViewById(R.id.tv_atualizar);
            rvRespostas = (RecyclerView) itemView.findViewById(R.id.rv_respostas);

            tvAtualizar.setOnClickListener(this);
            iniRecyclerViewRespostas();
        }

        private void iniRecyclerViewRespostas(){
            LinearLayoutManager layoutManager = new LinearLayoutManager( context );
            RespostasAdapter adapter = new RespostasAdapter( context );

            layoutManager.setAutoMeasureEnabled(true);
            rvRespostas.setNestedScrollingEnabled(false);
            rvRespostas.setHasFixedSize(false);
            rvRespostas.setLayoutManager( layoutManager );
            rvRespostas.setAdapter(adapter);
        }

        private void setData( Avaliacao avaliacao ){
            civPerfil.setImageResource( avaliacao.getUser().getImagem() );
            tvNome.setText( avaliacao.getUser().getNome() );
            tvData.setText( avaliacao.getData().getDataFormatada() );
            tvAvaliacao.setText( avaliacao.getMensagem() );
            setEstrelasAvaliacao( avaliacao );
            setRespostas( avaliacao );
        }

        private void setEstrelasAvaliacao( Avaliacao avaliacao){
            setEstrela( ivEstrela1, 1, avaliacao);
            setEstrela( ivEstrela2, 2, avaliacao);
            setEstrela( ivEstrela3, 3, avaliacao);
            setEstrela( ivEstrela4, 4, avaliacao);
            setEstrela( ivEstrela5, 5, avaliacao);
        }

        private void setEstrela( ImageView ivEstrela, int posicaoEstrela, Avaliacao avaliacao){
            if( posicaoEstrela <= (int) avaliacao.getAvaliacao() ){
                ivEstrela.setImageResource(R.drawable.ic_estrela);
            }
            else if( posicaoEstrela > avaliacao.getAvaliacao()
                    && (posicaoEstrela - 1) < avaliacao.getAvaliacao() ){
                ivEstrela.setImageResource(R.drawable.ic_estrela_metade);
            }
            else{
                ivEstrela.setImageResource(R.drawable.ic_estrela_vazia);
            }
        }

        private void setRespostas( Avaliacao avaliacao ){
            ((RespostasAdapter) rvRespostas.getAdapter()).setRespostas( avaliacao.getRespostas() );
        }

        @Override
        public void onClick(View view) {
            ComercioActivity comercioActivity = (ComercioActivity) context;
            FragmentManager fragManager = comercioActivity.getSupportFragmentManager();
            FragmentTransaction ft = fragManager.beginTransaction();
            Fragment fragAnterior = fragManager.findFragmentByTag( AvaliacaoFragment.KEY );
            if (fragAnterior != null) {
                ft.remove(fragAnterior);
            }
            ft.addToBackStack(null);

            Bundle dados = new Bundle();
            dados.putParcelable( Comercio.COMERCIO_SELECIONADO_KEY, comercioActivity.getComercio() );
            dados.putParcelable( Avaliacao.AVALIACAO_KEY, avaliacoes.get( getAdapterPosition() ) );

            DialogFragment dialog = new AvaliacaoFragment();
            dialog.setArguments(dados);
            dialog.show(ft, AvaliacaoFragment.KEY);
        }
    }

    public AvaliacoesAdapter( Context context, List<Avaliacao> avaliacoes ){
        this.context = context;
        this.avaliacoes = avaliacoes;
    }

    @Override
    public AvaliacoesAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.item_avaliacao, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData( avaliacoes.get( position ) );
    }

    @Override
    public int getItemCount() {
        return avaliacoes.size();
    }
}
