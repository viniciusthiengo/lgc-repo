package br.com.thiengo.laranjeirasguiacomercial.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by viniciusthiengo on 18/01/17.
 */

public class Imagem implements Parcelable {
    private String uri;
    private int resource;
    private String legenda;
    private int numLikes;
    private int numCompartilhamentos;

    public Imagem(String uri, int resource, String legenda, int numLikes, int numCompartilhamentos) {
        this.uri = uri;
        this.resource = resource;
        this.legenda = legenda;
        this.numLikes = numLikes;
        this.numCompartilhamentos = numCompartilhamentos;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getResource() {
        return resource;
    }

    public void setResource(int resource) {
        this.resource = resource;
    }

    public int getNumLikes() {
        return numLikes;
    }

    public void setNumLikes(int numLikes) {
        this.numLikes = numLikes;
    }

    public int getNumCompartilhamentos() {
        return numCompartilhamentos;
    }

    public void setNumCompartilhamentos(int numCompartilhamentos) {
        this.numCompartilhamentos = numCompartilhamentos;
    }

    public String getLegenda() {
        return legenda;
    }

    public void setLegenda(String legenda) {
        this.legenda = legenda;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.uri);
        dest.writeInt(this.resource);
        dest.writeString(this.legenda);
        dest.writeInt(this.numLikes);
        dest.writeInt(this.numCompartilhamentos);
    }

    protected Imagem(Parcel in) {
        this.uri = in.readString();
        this.resource = in.readInt();
        this.legenda = in.readString();
        this.numLikes = in.readInt();
        this.numCompartilhamentos = in.readInt();
    }

    public static final Creator<Imagem> CREATOR = new Creator<Imagem>() {
        @Override
        public Imagem createFromParcel(Parcel source) {
            return new Imagem(source);
        }

        @Override
        public Imagem[] newArray(int size) {
            return new Imagem[size];
        }
    };
}
