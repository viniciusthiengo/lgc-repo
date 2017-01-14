package br.com.thiengo.laranjeirasguiacomercial;

import android.content.Intent;
import android.net.Uri;
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
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import br.com.thiengo.laranjeirasguiacomercial.domain.Comercio;

public class ComercioActivity extends AppCompatActivity implements OnMapReadyCallback {
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

        conteudo = "Nunca esqueci da frase no filme \"Wall Street - O Dinheiro Nunca Dorme\" onde Josh Brolin interpretando Bretton James disse,<br><br>quando perguntado pelo jovem Jake Moore\t(interpretado por Shia LaBeouf) sobre qual era o número máximo dele (eles estavam falando de dinheiro). O personagem Bretton James, sem rodeios, respondeu: \"Meu número é ‘MAIS'\".<br><br>Pode soar um pouco soberbo, mas po%#@, foi fo$@&.";
        TextView tvDescricao = (TextView) findViewById(R.id.tv_descricao);
        tvDescricao.setText( Html.fromHtml( conteudo ) );

        TextView tvLocalizacao = (TextView) findViewById(R.id.tv_localizacao);
        tvLocalizacao.setText( Html.fromHtml( "<b>Endereço:</b> "+comercio.getLocalizacao() ) );


        // MAPS
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.fm_mapa);
        mapFragment.getMapAsync(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        LatLng sydney = new LatLng(-33.867, 151.206);

        //map.setMyLocationEnabled(true);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));

        map.addMarker(new MarkerOptions()
                .title("Sydney")
                .snippet("The most populous city in Australia.")
                .position(sydney));
    }

    public void intencoesContato( View view ){
        Intent intent = null;

        if( view.getId() == R.id.tv_telefone ){
            String numero = "27997863727";
            intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", numero, null));
        }
        else if( view.getId() == R.id.tv_email ){
            String email = "thiengocalopsita@gmail.com";
            intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto",email, null));
            intent.putExtra(Intent.EXTRA_TEXT, "Acesso direto de Laranjeiras Guia Comercial APP");
            intent = Intent.createChooser(intent, "Enviar email");
        }
        else if( view.getId() == R.id.tv_site ){
            String url = "http://www.thiengo.com.br";
            intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse(url));
        }

        startActivity(intent);
    }
}
