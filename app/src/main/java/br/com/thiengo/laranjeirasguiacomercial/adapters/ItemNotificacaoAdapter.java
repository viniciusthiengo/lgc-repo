package br.com.thiengo.laranjeirasguiacomercial.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import java.util.ArrayList;

import br.com.thiengo.laranjeirasguiacomercial.R;
import br.com.thiengo.laranjeirasguiacomercial.domain.NotificacaoImpl;

/**
 * Created by viniciusthiengo on 12/01/17.
 */

public class ItemNotificacaoAdapter extends RecyclerView.Adapter<ItemNotificacaoAdapter.ViewHolder> {

    private ArrayList<NotificacaoImpl> itensNotificacao;
    private Context context;

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        CheckBox cbItemNotificacao;

        ViewHolder(View itemView) {
            super(itemView);

            cbItemNotificacao = (CheckBox) itemView.findViewById(R.id.cb_item_notificacao);
            itemView.setOnClickListener(this);
        }

        private void setData( NotificacaoImpl item ){
            cbItemNotificacao.setText( item.getNome() );
            cbItemNotificacao.setChecked( item.statusNotificacao() );
        }

        @Override
        public void onClick(View view) {
            CheckBox cb = (CheckBox) view;
            itensNotificacao.get( getAdapterPosition() ).setStatusNotificacao( cb.isChecked() );
        }
    }

    public ItemNotificacaoAdapter(ArrayList<NotificacaoImpl> itensNotificacao){
        this.itensNotificacao = itensNotificacao;
    }

    @Override
    public ItemNotificacaoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater
            .from(parent.getContext())
            .inflate(R.layout.item_notificacao, parent, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.setData( itensNotificacao.get( position ) );
    }

    @Override
    public int getItemCount() {
        return itensNotificacao.size();
    }
}
