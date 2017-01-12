package br.com.thiengo.laranjeirasguiacomercial;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

public class CadastroActivity extends AppCompatActivity implements MaterialDialog.SingleButtonCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if( getSupportActionBar() != null ){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        String rotuloTermosECondicoes = "Concordo com os <font color=\"#1193f5\">Termos e Condições de Uso</font> de Laranjeiras Guia Comercial APP";
        TextView tvTermosECondicoes = (TextView) findViewById(R.id.tv_termos_e_condicoes);
        tvTermosECondicoes.setText(Html.fromHtml( rotuloTermosECondicoes ) );
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    public void cadastrar( View view ){
        new MaterialDialog.Builder(this)
                .title("Cadastro realizado")
                .content("Seu cadastro em Laranejrias Guia Comercial APP foi realizado com sucesso, já pode realizar o acesso em Login.")
                .positiveText("Ok")
                .positiveColorRes( R.color.colorLink )
                .onPositive(this)
                .show();
    }

    @Override
    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
        Intent intent = new Intent( this, LoginActivity.class );
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }


    public void carregarImagemPerfil( View view ){
        new MaterialDialog.Builder(this)
                .title("Carregamento de imagem")
                .content("A library ImagePicker provavelmente será utilizada aqui")
                .positiveText("Ok")
                .positiveColorRes( R.color.colorLink )
                .show();
    }

    public void termosECondicoesActivity( View view ){
        Intent intent = new Intent( this, TermosCondicoesActivity.class );
        startActivity( intent );
    }
}
