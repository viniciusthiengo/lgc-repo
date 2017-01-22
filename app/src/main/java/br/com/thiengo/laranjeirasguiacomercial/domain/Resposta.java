package br.com.thiengo.laranjeirasguiacomercial.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by viniciusthiengo on 22/01/17.
 */

public class Resposta implements Parcelable {
    private User user;
    private String resposta;
    private Data data;

    public Resposta(User user, String resposta, Data data) {
        this.user = user;
        this.resposta = resposta;
        this.data = data;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
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
        dest.writeString(this.resposta);
        dest.writeParcelable(this.data, flags);
    }

    protected Resposta(Parcel in) {
        this.user = in.readParcelable(User.class.getClassLoader());
        this.resposta = in.readString();
        this.data = in.readParcelable(Data.class.getClassLoader());
    }

    public static final Creator<Resposta> CREATOR = new Creator<Resposta>() {
        @Override
        public Resposta createFromParcel(Parcel source) {
            return new Resposta(source);
        }

        @Override
        public Resposta[] newArray(int size) {
            return new Resposta[size];
        }
    };
}
