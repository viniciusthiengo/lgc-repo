package br.com.thiengo.laranjeirasguiacomercial;

import android.Manifest;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsResult;
import com.google.android.gms.location.LocationSettingsStates;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.youtube.player.YouTubePlayerSupportFragment;

import java.util.ArrayList;
import java.util.List;

import br.com.thiengo.laranjeirasguiacomercial.adapters.AvaliacoesAdapter;
import br.com.thiengo.laranjeirasguiacomercial.adapters.GaleriaAdapter;
import br.com.thiengo.laranjeirasguiacomercial.domain.Avaliacao;
import br.com.thiengo.laranjeirasguiacomercial.domain.Comercio;
import br.com.thiengo.laranjeirasguiacomercial.domain.Imagem;
import br.com.thiengo.laranjeirasguiacomercial.domain.YouTubeInitializedListener;
import br.com.thiengo.laranjeirasguiacomercial.extras.Mock;
import br.com.thiengo.laranjeirasguiacomercial.extras.Util;
import br.com.thiengo.laranjeirasguiacomercial.fragments.AvaliacaoFragment;

public class ComercioActivity extends AppCompatActivity
        implements OnMapReadyCallback,
            View.OnClickListener,
            GoogleApiClient.ConnectionCallbacks,
            GoogleApiClient.OnConnectionFailedListener,
            ResultCallback<LocationSettingsResult> {

    private Comercio comercio;
    private FragmentManager fragManager;
    private GoogleApiClient googleApiClient;
    private View btRotaMapa;

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
        fab.setOnClickListener(this);


        comercio = getIntent().getParcelableExtra( Comercio.COMERCIO_SELECIONADO_KEY );

        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.toolbar_layout);
        collapsingToolbar.setTitle( comercio.getNome() );

        ImageView ivCabecalho = (ImageView) findViewById(R.id.iv_cabecalho);
        ivCabecalho.setImageResource( comercio.getImagem() );

        TextView tvTelefone = (TextView) findViewById(R.id.tv_telefone);
        tvTelefone.setText( comercio.getTelefone() );

        TextView tvEmail = (TextView) findViewById(R.id.tv_email);
        tvEmail.setText( comercio.getEmail() );

        TextView tvSite = (TextView) findViewById(R.id.tv_site);
        tvSite.setText( comercio.getSite() );

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
        btRotaMapa = findViewById(R.id.bt_rota_mapa);
        iniGooglePlayServices();
        configRequisicaoLocalizacao();
        //SupportMapFragment mapFragment = (SupportMapFragment) fragManager.findFragmentById(R.id.fm_mapa);
        //mapFragment.getMapAsync(this);

        // YOUTUBE
        setYouTubeArea();

        // GALERIA
        iniGaleria();

        // AVALIACOES
        iniAvaliacoes();
    }

    @Override
    protected void onStart() {
        googleApiClient.connect();
        super.onStart();
    }

    @Override
    protected void onStop() {
        googleApiClient.disconnect();
        super.onStop();
    }

    private void iniGooglePlayServices(){
        googleApiClient = new GoogleApiClient.Builder(this)
            .addConnectionCallbacks(this)
            .addOnConnectionFailedListener(this)
            .addApi(LocationServices.API)
            .build();
    }

    protected void configRequisicaoLocalizacao() {
        LocationRequest mLocationRequest = new LocationRequest();
        /*mLocationRequest.setInterval(10000);
        mLocationRequest.setFastestInterval(5000);*/
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);

        LocationSettingsRequest.Builder construtor = new LocationSettingsRequest.Builder()
                .addLocationRequest(mLocationRequest);

        final PendingResult<LocationSettingsResult> resultado = LocationServices
                                                            .SettingsApi
                                                            .checkLocationSettings( googleApiClient, construtor.build() );
        resultado.setResultCallback(this);
    }

    @Override
    public void onResult(@NonNull LocationSettingsResult locationSettingsResult) {
        final Status status = locationSettingsResult.getStatus();
        final LocationSettingsStates lss = locationSettingsResult.getLocationSettingsStates();
        switch (status.getStatusCode()) {
            case LocationSettingsStatusCodes.SUCCESS:
                break;
            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                try {
                    status.startResolutionForResult( this, 552);
                }
                catch (IntentSender.SendIntentException e) {}
                break;
            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                break;
        }
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        if( Util.ehPermitido( this, Manifest.permission.ACCESS_FINE_LOCATION ) ){
            Location coordenada = LocationServices.FusedLocationApi.getLastLocation( googleApiClient );

            if( coordenada != null ){
                btRotaMapa.setVisibility(View.VISIBLE);
                btRotaMapa.setTag( coordenada );
            }
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    public void apresentarRota( View view ){
        Location coordenadas = (Location) btRotaMapa.getTag();
        double latitude = coordenadas.getLatitude();
        double longitude = coordenadas.getLongitude();

        Intent intent = new Intent(
                android.content.Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?saddr="+latitude+","+longitude+"&daddr=-20.195843,-40.204932"));
        intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity"); // PARA ABRIR DIRETAMENTE COM O GOOGLE MAPS
        startActivity(intent);
    }

    private void setYouTubeArea(){
        if( comercio != null ){
            YouTubePlayerSupportFragment youTubeFragment = (YouTubePlayerSupportFragment) fragManager.findFragmentById(R.id.ypv_video);
            youTubeFragment.initialize( getResources().getString(R.string.google_api_key), new YouTubeInitializedListener( comercio ));
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
        GaleriaAdapter adapter = new GaleriaAdapter(this, imagens);

        layoutManager.setAutoMeasureEnabled(true);
        rvGaleria.setNestedScrollingEnabled(false);
        rvGaleria.setHasFixedSize(false);
        rvGaleria.setLayoutManager( layoutManager );
        rvGaleria.setAdapter(adapter);
    }

    private void iniAvaliacoes(){
        List<Avaliacao> avaliacoes = Mock.criarAvaliacoesAleatorio();
        RecyclerView rvGaleria = (RecyclerView) findViewById(R.id.rv_avaliacao);
        LinearLayoutManager layoutManager = new LinearLayoutManager( this );
        AvaliacoesAdapter adapter = new AvaliacoesAdapter( this, avaliacoes );

        layoutManager.setAutoMeasureEnabled(true);
        rvGaleria.setNestedScrollingEnabled(false);
        rvGaleria.setHasFixedSize(false);
        rvGaleria.setLayoutManager( layoutManager );
        rvGaleria.setAdapter(adapter);
    }

    public Comercio getComercio(){
        return comercio;
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
        LatLng sydney = new LatLng(-20.196105, -40.221047);

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(sydney, 13));
        map.addMarker(new MarkerOptions()
                .title("Morada de Laranjeiras")
                .snippet("The most populous city in Australia.")
                .position(sydney));

        //if( Util.ehPermitido( this, Manifest.permission.ACCESS_FINE_LOCATION ) ){
        //    map.setMyLocationEnabled(true);
        //}
    }

    public void intencoesContato( View view ){
        Intent intent = null;

        if( view.getId() == R.id.tv_telefone ){
            String numero = comercio.getTelefonePuro();
            intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", numero, null));
        }
        else if( view.getId() == R.id.tv_email ){
            String email = comercio.getEmail();
            intent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto",email, null));
            intent.putExtra(Intent.EXTRA_TEXT, "Acesso direto de Laranjeiras Guia Comercial APP");
            intent = Intent.createChooser(intent, "Enviar email");
        }
        else if( view.getId() == R.id.tv_site ){
            String url = comercio.getSite();
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
            .title("Atualizar statusNotificacao notificação")
            .content("Status: "+ (cb.isChecked() ? "Checked" : "Not checked") )
            .positiveText("Ok")
            .positiveColorRes( R.color.colorLink )
            .show();
    }

    public void avaliarComercio( View view ){
        FragmentTransaction ft = fragManager.beginTransaction();
        Fragment fragAnterior = fragManager.findFragmentByTag( AvaliacaoFragment.KEY );
        if (fragAnterior != null) {
            ft.remove(fragAnterior);
        }
        ft.addToBackStack(null);

        Bundle dados = new Bundle();
        dados.putParcelable(
                Comercio.COMERCIO_SELECIONADO_KEY,
                comercio );

        DialogFragment dialog = new AvaliacaoFragment();
        dialog.setArguments(dados);
        dialog.show(ft, AvaliacaoFragment.KEY);
    }

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
}
