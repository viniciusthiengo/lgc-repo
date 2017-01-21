package br.com.thiengo.laranjeirasguiacomercial.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by viniciusthiengo on 20/01/17.
 */

public class Categoria implements Parcelable, NotificacaoImpl {
    private String nome;
    private boolean statusNotificacao;

    public Categoria(String nome, boolean statusNotificacao) {
        this.nome = nome;
        this.statusNotificacao = statusNotificacao;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean statusNotificacao() {
        return statusNotificacao;
    }

    public void setStatusNotificacao(boolean statusNotificacao) {
        this.statusNotificacao = statusNotificacao;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.nome);
        dest.writeByte(this.statusNotificacao ? (byte) 1 : (byte) 0);
    }

    protected Categoria(Parcel in) {
        this.nome = in.readString();
        this.statusNotificacao = in.readByte() != 0;
    }

    public static final Parcelable.Creator<Categoria> CREATOR = new Parcelable.Creator<Categoria>() {
        @Override
        public Categoria createFromParcel(Parcel source) {
            return new Categoria(source);
        }

        @Override
        public Categoria[] newArray(int size) {
            return new Categoria[size];
        }
    };
}
