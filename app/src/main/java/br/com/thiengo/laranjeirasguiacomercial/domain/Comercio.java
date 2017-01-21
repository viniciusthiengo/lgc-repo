package br.com.thiengo.laranjeirasguiacomercial.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by viniciusthiengo on 12/01/17.
 */

public class Comercio implements Parcelable, NotificacaoImpl {
    public static final String COMERCIO_SELECIONADO_KEY = "comercio_selecionado";

    private int imagem;
    private String nome;
    private String localizacao;
    private double avaliacaoPontos;
    private int avaliacaoQtd;
    private List<Comentario> comentarios;
    private boolean statusNotificacao;

    public Comercio(int imagem, String nome, String localizacao, double avaliacaoPontos, int avaliacaoQtd, boolean statusNotificacao) {
        this.imagem = imagem;
        this.nome = nome;
        this.localizacao = localizacao;
        this.avaliacaoPontos = avaliacaoPontos;
        this.avaliacaoQtd = avaliacaoQtd;
        this.statusNotificacao = statusNotificacao;
        comentarios = new ArrayList<>();
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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
        dest.writeInt(this.imagem);
        dest.writeString(this.nome);
        dest.writeString(this.localizacao);
        dest.writeDouble(this.avaliacaoPontos);
        dest.writeInt(this.avaliacaoQtd);
        dest.writeTypedList(this.comentarios);
        dest.writeByte(this.statusNotificacao ? (byte) 1 : (byte) 0);
    }

    protected Comercio(Parcel in) {
        this.imagem = in.readInt();
        this.nome = in.readString();
        this.localizacao = in.readString();
        this.avaliacaoPontos = in.readDouble();
        this.avaliacaoQtd = in.readInt();
        this.comentarios = in.createTypedArrayList(Comentario.CREATOR);
        this.statusNotificacao = in.readByte() != 0;
    }

    public static final Creator<Comercio> CREATOR = new Creator<Comercio>() {
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
