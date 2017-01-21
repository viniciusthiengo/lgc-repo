package br.com.thiengo.laranjeirasguiacomercial.domain;

import android.os.Parcelable;

/**
 * Created by viniciusthiengo on 20/01/17.
 */

public interface NotificacaoImpl extends Parcelable {
    public String getNome();
    public void setStatusNotificacao( boolean status );
    public boolean statusNotificacao();
}
