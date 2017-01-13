package br.com.thiengo.laranjeirasguiacomercial.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by viniciusthiengo on 12/01/17.
 */

public class Comercio implements Parcelable {
    public static final String COMERCIO_SELECIONADO_KEY = "comercion_selecionado";

    private int imagem;
    private String nomeFantasia;
    private String localizacao;
    private double avaliacaoPontos;
    private int avaliacaoQtd;
    private List<Comentario> comentarios;

    public Comercio(int imagem, String nomeFantasia, String localizacao, double avaliacaoPontos, int avaliacaoQtd) {
        this.imagem = imagem;
        this.nomeFantasia = nomeFantasia;
        this.localizacao = localizacao;
        this.avaliacaoPontos = avaliacaoPontos;
        this.avaliacaoQtd = avaliacaoQtd;
        comentarios = new ArrayList<>();
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public String getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(String localizacao) {
        this.localizacao = localizacao;
    }

    public double getAvaliacaoPontos() {
        return avaliacaoPontos;
    }

    public void setAvaliacaoPontos(double avaliacaoPontos) {
        this.avaliacaoPontos = avaliacaoPontos;
    }

    public int getAvaliacaoQtd() {
        return avaliacaoQtd;
    }

    public void setAvaliacaoQtd(int avaliacaoQtd) {
        this.avaliacaoQtd = avaliacaoQtd;
    }

    public List<Comentario> getComentarios() {
        return comentarios;
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.comentarios = comentarios;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.imagem);
        dest.writeString(this.nomeFantasia);
        dest.writeString(this.localizacao);
        dest.writeDouble(this.avaliacaoPontos);
        dest.writeInt(this.avaliacaoQtd);
        dest.writeTypedList(this.comentarios);
    }

    protected Comercio(Parcel in) {
        this.imagem = in.readInt();
        this.nomeFantasia = in.readString();
        this.localizacao = in.readString();
        this.avaliacaoPontos = in.readDouble();
        this.avaliacaoQtd = in.readInt();
        this.comentarios = in.createTypedArrayList(Comentario.CREATOR);
    }

    public static final Parcelable.Creator<Comercio> CREATOR = new Parcelable.Creator<Comercio>() {
        @Override
        public Comercio createFromParcel(Parcel source) {
            return new Comercio(source);
        }

        @Override
        public Comercio[] newArray(int size) {
            return new Comercio[size];
        }
    };
}
