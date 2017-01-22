package br.com.thiengo.laranjeirasguiacomercial.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by viniciusthiengo on 12/01/17.
 */

public class Avaliacao implements Parcelable {
    public static final String AVALIACAO_KEY = "avaliacao_key";

    private User user;
    private String mensagem;
    private float avaliacao;
    private List<Resposta> respostas;
    private Data data;

    public Avaliacao(User user, String mensagem, float avaliacao, Data data) {
        this.user = user;
        this.mensagem = mensagem;
        this.avaliacao = avaliacao;
        this.data = data;
        respostas = new ArrayList<>();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public float getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }

    public List<Resposta> getRespostas() {
        return respostas;
    }

    public void setRespostas(List<Resposta> respostas) {
        this.respostas = respostas;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.user, flags);
        dest.writeString(this.mensagem);
        dest.writeFloat(this.avaliacao);
        dest.writeTypedList(this.respostas);
        dest.writeParcelable(this.data, flags);
    }

    protected Avaliacao(Parcel in) {
        this.user = in.readParcelable(User.class.getClassLoader());
        this.mensagem = in.readString();
        this.avaliacao = in.readFloat();
        this.respostas = in.createTypedArrayList(Resposta.CREATOR);
        this.data = in.readParcelable(Data.class.getClassLoader());
    }

    public static final Creator<Avaliacao> CREATOR = new Creator<Avaliacao>() {
        @Override
        public Avaliacao createFromParcel(Parcel source) {
            return new Avaliacao(source);
        }

        @Override
        public Avaliacao[] newArray(int size) {
            return new Avaliacao[size];
        }
    };
}
