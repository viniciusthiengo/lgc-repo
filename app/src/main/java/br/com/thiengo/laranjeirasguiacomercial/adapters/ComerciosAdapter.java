package br.com.thiengo.laranjeirasguiacomercial.adapters;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.List;

import br.com.thiengo.laranjeirasguiacomercial.ComercioActivity;
import br.com.thiengo.laranjeirasguiacomercial.R;
import br.com.thiengo.laranjeirasguiacomercial.domain.Comentario;
import br.com.thiengo.laranjeirasguiacomercial.domain.Comercio;

/**
 * Created by viniciusthiengo on 12/01/17.
 */

public class ComerciosAdapter extends RecyclerView.Adapter<ComerciosAdapter.ViewHolder> {

    private List<Comercio> comercios;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView ivPrincipal;
        ImageView ivFavorito;
        TextView tvNomeFantasia;
        TextView tvLocalizacao;
        TextView tvAvaliacaoPontos;
        TextView tvAvaliacaoQtd;
        CircularImageView ivComentario1;
        TextView tvComentario1;
        CircularImageView ivComentario2;
        TextView tvComentario2;
        CircularImageView ivComentario3;
        TextView tvComentario3;
        ImageView ivEstrela1;
        ImageView ivEstrela2;
        ImageView ivEstrela3;
        ImageView ivEstrela4;
        ImageView ivEstrela5;

        ViewHolder(View itemView) {
            super(itemView);

            ivPrincipal = (ImageView) itemView.findViewById(R.id.iv_principal);
            ivFavorito = (ImageView) itemView.findViewById(R.id.iv_favorito);
            tvNomeFantasia = (TextView) itemView.findViewById(R.id.tv_nome_fantasia);
            tvLocalizacao = (TextView) itemView.findViewById(R.id.tv_localizacao);
            tvAvaliacaoPontos = (TextView) itemView.findViewById(R.id.tv_avaliacao_pontos);
            tvAvaliacaoQtd = (TextView) itemView.findViewById(R.id.tv_avaliacao_qtd);
            ivComentario1 = (CircularImageView) itemView.findViewById(R.id.iv_comentario_1);
            tvComentario1 = (TextView) itemView.findViewById(R.id.tv_comentario_1);
            ivComentario2 = (CircularImageView) itemView.findViewById(R.id.iv_comentario_2);
            tvComentario2 = (TextView) itemView.findViewById(R.id.tv_comentario_2);
            ivComentario3 = (CircularImageView) itemView.findViewById(R.id.iv_comentario_3);
            tvComentario3 = (TextView) itemView.findViewById(R.id.tv_comentario_3);
            ivEstrela1 = (ImageView) itemView.findViewById(R.id.iv_estrela_1);
            ivEstrela2 = (ImageView) itemView.findViewById(R.id.iv_estrela_2);
            ivEstrela3 = (ImageView) itemView.findViewById(R.id.iv_estrela_3);
            ivEstrela4 = (ImageView) itemView.findViewById(R.id.iv_estrela_4);
            ivEstrela5 = (ImageView) itemView.findViewById(R.id.iv_estrela_5);

            itemView.setOnClickListener(this);
            ivFavorito.setOnClickListener(this);
        }

        private void setData( Comercio comercio ){
            ivPrincipal.setImageResource( comercio.getImagem() );
            tvNomeFantasia.setText( comercio.getNomeFantasia() );
            tvLocalizacao.setText( comercio.getLocalizacao() );
            tvAvaliacaoPontos.setText( String.format("%.1f", comercio.getAvaliacaoPontos()) );
            tvAvaliacaoQtd.setText( "("+String.valueOf(comercio.getAvaliacaoQtd())+")" );
            setEstrelasAvaliacao( comercio );
            setComentarios( comercio );
        }

        private void setComentarios( Comercio comercio){
            setComentario(ivComentario1, tvComentario1, comercio, 0);
            setComentario(ivComentario2, tvComentario2, comercio, 1);
            setComentario(ivComentario3, tvComentario3, comercio, 2);
        }

        private void setComentario(CircularImageView civPerfil, TextView tvMensagem, Comercio comercio, int position){
            if( comercio.getComentarios().size() >= (position + 1) ){
                Comentario comentario = comercio.getComentarios().get( position );
                civPerfil.setImageResource( comentario.getUser().getImagem() );
                tvMensagem.setText( comentario.getMensagem() );
            }
            else{
                civPerfil.setVisibility( View.GONE );
                tvMensagem.setVisibility( View.GONE );
            }
        }

        private void setEstrelasAvaliacao( Comercio comercio){
            setEstrela( ivEstrela1, 1, comercio);
            setEstrela( ivEstrela2, 2, comercio);
            setEstrela( ivEstrela3, 3, comercio);
            setEstrela( ivEstrela4, 4, comercio);
            setEstrela( ivEstrela5, 5, comercio);
        }

        private void setEstrela( ImageView ivEstrela, int posicaoEstrela, Comercio comercio){
            if( posicaoEstrela <= (int) comercio.getAvaliacaoPontos() ){
                ivEstrela.setImageResource(R.drawable.ic_estrela);
            }
            else if( posicaoEstrela > comercio.getAvaliacaoPontos()
                    && (posicaoEstrela - 1) < comercio.getAvaliacaoPontos() ){
                ivEstrela.setImageResource(R.drawable.ic_estrela_metade);
            }
            else{
                ivEstrela.setImageResource(R.drawable.ic_estrela_vazia);
            }
        }

        @Override
        public void onClick(View view) {
            if( view.getId() == R.id.iv_favorito ){
                ImageView iv = (ImageView)view;
                Integer resourceId = (Integer) iv.getTag();

                if( resourceId == null || resourceId == R.drawable.ic_nao_favorito ){
                    resourceId = R.drawable.ic_favorito;
                }
                else{
                    resourceId = R.drawable.ic_nao_favorito;
                }
                iv.setImageResource( resourceId );
                iv.setTag( resourceId );
            }
            else{
                Intent intent = new Intent( view.getContext(), ComercioActivity.class);
                intent.putExtra( Comercio.COMERCIO_SELECIONADO_KEY, comercios.get( getAdapterPosition() ) );
                view.getContext().startActivity( intent );
            }
        }
    }

    public ComerciosAdapter(List<Comercio> comercios ){
        this.comercios = comercios;
    }

    @Override
    public ComerciosAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
                    .from(parent.getContext())
                    .inflate(R.layout.item_comercio, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData( comercios.get( position ) );
    }

    @Override
    public int getItemCount() {
        return comercios.size();
    }
}
