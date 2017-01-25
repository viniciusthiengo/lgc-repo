package br.com.thiengo.laranjeirasguiacomercial.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by viniciusthiengo on 22/01/17.
 */

public class Data implements Parcelable {
    private static final long ANO_MICROSSEGUNDOS = 365 * 24 * 60 * 60;
    private static final long MES_MICROSSEGUNDOS = 30 * 24 * 60 * 60;
    private static final long SEMANA_MICROSSEGUNDOS = 7 * 24 * 60 * 60;
    private static final long DIA_MICROSSEGUNDOS = 24 * 60 * 60;
    private static final long HORA_MICROSSEGUNDOS = 60 * 60;
    private static final long MINUTO_MICROSSEGUNDOS = 60;
    private static final long SEGUNDO_MICROSSEGUNDOS = 1;

    private long envio;
    private long edicao;


    public Data(long envio, long edicao) {
        this.envio = envio;
        this.edicao = edicao;
    }

    public long getEnvio() {
        return envio;
    }

    public void setEnvio(long envio) {
        this.envio = envio;
    }

    public long getEdicao() {
        return edicao;
    }

    public void setEdicao(long edicao) {
        this.edicao = edicao;
    }

    public String getDataFormatada() {
        long data = edicao == 0 ? envio : edicao;
        String rotuloInicio = edicao == 0 ? "Enviado a " : "Editado a ";
        String rotuloFinal;

        //Log.i("Log", "Data:: "+(System.currentTimeMillis() / 1000)+" | "+data);
        data = (System.currentTimeMillis() / 1000) - data;
        rotuloFinal = getDataRotuloFinal( data );

        return rotuloInicio + rotuloFinal;
    }

    private String getDataRotuloFinal( long data ){
        String rotuloFinal = getAnos( data );

        if( rotuloFinal == null ){
            rotuloFinal = getMeses( data );

            if( rotuloFinal == null ){
                rotuloFinal = getSemanas( data );

                if( rotuloFinal == null ){
                    rotuloFinal = getDias( data );

                    if( rotuloFinal == null ){
                        rotuloFinal = getHoras( data );

                        if( rotuloFinal == null ){
                            rotuloFinal = getMinutos( data );

                            if( rotuloFinal == null ){
                                rotuloFinal = getSegundos( data );
                            }
                        }
                    }
                }
            }
        }

        return rotuloFinal;
    }

    private String getAnos( long segundos ){
        return dataCalculo( segundos, ANO_MICROSSEGUNDOS, "ano", "anos" );
    }

    private String getMeses( long segundos ){
        return dataCalculo( segundos, MES_MICROSSEGUNDOS, "mês", "meses" );
    }

    private String getSemanas( long segundos ){
        return dataCalculo( segundos, SEMANA_MICROSSEGUNDOS, "semana", "semanas" );
    }

    private String getDias( long segundos ){
        return dataCalculo( segundos, DIA_MICROSSEGUNDOS, "dia", "dias" );
    }

    private String getHoras( long segundos ){
        return dataCalculo( segundos, HORA_MICROSSEGUNDOS, "hora", "horas" );
    }

    private String getMinutos( long segundos ){
        return dataCalculo( segundos, MINUTO_MICROSSEGUNDOS, "minuto", "minutos" );
    }

    private String getSegundos( long segundos ){
        return dataCalculo( segundos, SEGUNDO_MICROSSEGUNDOS, "segundo", "segundos" );
    }

    private String dataCalculo( long segundos, long divisor, String rotuloUm, String rotuloVarios ){
        /*Log.i("log", "segundos: "+segundos);
        Log.i("log", "divisor: "+divisor);
        Log.i("log", "rotuloUm: "+rotuloUm);
        Log.i("log", "rotuloVarios: "+rotuloVarios);*/
        segundos = segundos / divisor;
        //Log.i("log", "segundos (antes calculo): "+segundos);
        segundos = segundos % divisor;
        //Log.i("log", "segundos (calculo): "+segundos);
        String rotulo = null;

        if( segundos > 0 ){
            rotulo = segundos == 1 ? rotuloUm : rotuloVarios;
            rotulo = segundos + " " + rotulo;
        }
        return rotulo;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.envio);
        dest.writeLong(this.edicao);
    }

    protected Data(Parcel in) {
        this.envio = in.readLong();
        this.edicao = in.readLong();
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel source) {
            return new Data(source);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };
}
