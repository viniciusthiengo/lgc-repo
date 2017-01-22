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
    private List<Avaliacao> avaliacoes;
    private boolean statusNotificacao;
    private String youTubeCode;
    private String telefone;
    private String email;
    private String site;

    public Comercio(int imagem,
                    String nome,
                    String telefone,
                    String email,
                    String site,
                    String localizacao,
                    double avaliacaoPontos,
                    int avaliacaoQtd,
                    boolean statusNotificacao, String youTubeCode ) {

        this.imagem = imagem;
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.site = site;
        this.localizacao = localizacao;
        this.avaliacaoPontos = avaliacaoPontos;
        this.avaliacaoQtd = avaliacaoQtd;
        this.statusNotificacao = statusNotificacao;
        this.youTubeCode = youTubeCode;
        avaliacoes = new ArrayList<>();
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

    public List<Avaliacao> getAvaliacoes() {
        return avaliacoes;
    }

    public void setAvaliacoes(List<Avaliacao> avaliacoes) {
        this.avaliacoes = avaliacoes;
    }

    public boolean statusNotificacao() {
        return statusNotificacao;
    }

    public void setStatusNotificacao(boolean statusNotificacao) {
        this.statusNotificacao = statusNotificacao;
    }

    public String getYouTubeCode() {
        return youTubeCode;
    }

    public void setYouTubeCode(String youTubeCode) {
        this.youTubeCode = youTubeCode;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getTelefonePuro(){
        return telefone.replaceAll("[^0-9]","");
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
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
        dest.writeTypedList(this.avaliacoes);
        dest.writeByte(this.statusNotificacao ? (byte) 1 : (byte) 0);
        dest.writeString(this.youTubeCode);
        dest.writeString(this.telefone);
        dest.writeString(this.email);
        dest.writeString(this.site);
    }

    protected Comercio(Parcel in) {
        this.imagem = in.readInt();
        this.nome = in.readString();
        this.localizacao = in.readString();
        this.avaliacaoPontos = in.readDouble();
        this.avaliacaoQtd = in.readInt();
        this.avaliacoes = in.createTypedArrayList(Avaliacao.CREATOR);
        this.statusNotificacao = in.readByte() != 0;
        this.youTubeCode = in.readString();
        this.telefone = in.readString();
        this.email = in.readString();
        this.site = in.readString();
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
