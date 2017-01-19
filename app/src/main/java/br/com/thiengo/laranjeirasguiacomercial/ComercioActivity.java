package br.com.thiengo.laranjeirasguiacomercial;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.List;

import br.com.thiengo.laranjeirasguiacomercial.adapters.GaleriaAdapter;
import br.com.thiengo.laranjeirasguiacomercial.domain.Comercio;
import br.com.thiengo.laranjeirasguiacomercial.domain.Imagem;
import br.com.thiengo.laranjeirasguiacomercial.domain.YouTubeInitializedListener;
import br.com.thiengo.laranjeirasguiacomercial.extras.Mock;
import br.com.thiengo.laranjeirasguiacomercial.extras.Util;

public class ComercioActivity extends AppCompatActivity
        implements OnMapReadyCallback {

    private Comercio comercio;
    private FragmentManager fragManager;

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
                ImageView iv = (ImageView)view;
                Integer resourceId = (Integer) iv.getTag();

                if( resourceId == null || resourceId == R.drawable.ic_nao_favorito ){
                    resourceId = R.drawable.ic_favorito_big_icone;
                }
                else{
                    resourceId = R.drawable.ic_nao_favorito_big_icone;
                }
                iv.setImageResource( resourceId );
                iv.setTag( resourceId );
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


        fragManager = getSupportFragmentManager();

        // MAPS
        SupportMapFragment mapFragment = (SupportMapFragment) fragManager.findFragmentById(R.id.fm_mapa);
        mapFragment.getMapAsync(this);

        // YOUTUBE
        setYouTubeArea();

        // GALERIA
        iniGaleria();
    }

    private void setYouTubeArea(){
        if( comercio != null ){
            YouTubePlayerSupportFragment youTubeFragment = (YouTubePlayerSupportFragment) fragManager.findFragmentById(R.id.ypv_video);
            youTubeFragment.initialize( getResources().getString(R.string.google_api_key), new YouTubeInitializedListener(this));
        }
        else{
            findViewById(R.id.tv_titulo_video).setVisibility(View.GONE);
            findViewById(R.id.iv_video).setVisibility(View.GONE);
            findViewById(R.id.cv_video).setVisibility(View.GONE);

            View view = findViewById(R.id.tv_titulo_galeria);
            RelativeLayout.LayoutParams lp = (RelativeLayout.LayoutParams) view.getLayoutParams();
            lp.addRule(RelativeLayout.BELOW, R.id.cv_descricao);

            view = findViewById(R.id.iv_galeria);
            lp = (RelativeLayout.LayoutParams) view.getLayoutParams();
            lp.addRule(RelativeLayout.BELOW, R.id.cv_descricao);
        }
    }

    private void iniGaleria(){
        ArrayList<Imagem> imagens = Mock.criarGaleriaAleatorio();
        RecyclerView rvGaleria = (RecyclerView) findViewById(R.id.rv_galeria);
        //GridLayoutManager layoutManager = new GridLayoutManager(this, 5);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(4, StaggeredGridLayoutManager.VERTICAL );
        GaleriaAdapter adapter = new GaleriaAdapter(imagens);

        layoutManager.setAutoMeasureEnabled(true);
        rvGaleria.setNestedScrollingEnabled(false);
        rvGaleria.setHasFixedSize(false);
        rvGaleria.setLayoutManager( layoutManager );
        rvGaleria.setAdapter(adapter);
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

    public void compartilhar( View view ){
        new MaterialDialog.Builder(ComercioActivity.this)
            .title("Compartilhar")
            .content("Compartilhar em: "+view.getId())
            .positiveText("Ok")
            .positiveColorRes( R.color.colorLink )
            .show();
    }

    public void atualizarNotificacaoStatus( View view ){
        CheckBox cb = (CheckBox) view;

        new MaterialDialog.Builder(ComercioActivity.this)
            .title("Atualizar status notificação")
            .content("Status: "+ (cb.isChecked() ? "Checked" : "Not checked") )
            .positiveText("Ok")
            .positiveColorRes( R.color.colorLink )
            .show();
    }

    public FragmentManager getFragManager(){
        return fragManager;
    }
}
