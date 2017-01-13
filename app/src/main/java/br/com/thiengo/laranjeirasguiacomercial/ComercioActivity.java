package br.com.thiengo.laranjeirasguiacomercial;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

import br.com.thiengo.laranjeirasguiacomercial.domain.Comercio;

public class ComercioActivity extends AppCompatActivity {
    private Comercio comercio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comercio);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if( getSupportActionBar() != null ){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MaterialDialog.Builder(ComercioActivity.this)
                        .title("Compartilhar")
                        .content("Aqui aparecerão todos os ícones, com ações de compartilhamento, das redes sociais.")
                        .positiveText("Ok")
                        .positiveColorRes( R.color.colorLink )
                        .show();
            }
        });


        comercio = getIntent().getParcelableExtra( Comercio.COMERCIO_SELECIONADO_KEY );

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsingToolbar.setTitle( comercio.getNomeFantasia() );

        ImageView ivCabecalho = (ImageView) findViewById(R.id.iv_cabecalho);
        ivCabecalho.setImageResource( comercio.getImagem() );

        String conteudo = "Segunda-feira: <b>09:00 - 18:00</b><br>";
        conteudo += "Terça-feira: <b>09:00 - 18:00</b><br>";
        conteudo += "Quarta-feira: <b>09:00 - 18:00</b><br>";
        conteudo += "Quinta-feira: <b>09:00 - 18:00</b><br>";
        conteudo += "Sexta-feira: <b>09:00 - 18:00</b><br>";
        conteudo += "Sábado: <b>10:00 - 14:00</b><br>";
        conteudo += "Domingo: <b>não funciona</b>";
        TextView tvHorarioFuncionamento = (TextView) findViewById(R.id.tv_horario_funcionamento);
        tvHorarioFuncionamento.setText( Html.fromHtml( conteudo ) );
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
