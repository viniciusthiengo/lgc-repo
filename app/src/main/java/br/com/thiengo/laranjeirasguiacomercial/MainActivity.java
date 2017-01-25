package br.com.thiengo.laranjeirasguiacomercial;

import android.Manifest;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;

import br.com.thiengo.laranjeirasguiacomercial.extras.Util;
import br.com.thiengo.laranjeirasguiacomercial.fragments.ComerciosFragment;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final int PERMISSAO_REQUISICAO_CODIGO = 553;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        if( savedInstanceState == null ){
            ComerciosFragment fragment = new ComerciosFragment();
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add( R.id.content_main, fragment );
            fragmentTransaction.commit();
        }

        solicitacaoPermissao();
    }

    private void solicitacaoPermissao(){

        if ( !Util.ehPermitido( this, Manifest.permission.ACCESS_FINE_LOCATION ) ) {
            boolean mostrarDialog = ActivityCompat
                    .shouldShowRequestPermissionRationale(
                        this,
                        Manifest.permission.ACCESS_FINE_LOCATION );

            if( mostrarDialog ){
                permissaoDialog(
                    getResources().getString(R.string.permissao_localizacao),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION} );
            }
            else{
                ActivityCompat
                    .requestPermissions(
                        this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        PERMISSAO_REQUISICAO_CODIGO );
            }
        }
    }

    private void permissaoDialog( String message, final String[] permissions ){
        new MaterialDialog.Builder(this)
            .title("Permissão")
            .content(message)
            .positiveText("Ok")
            .onPositive(new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    ActivityCompat.requestPermissions(MainActivity.this, permissions, PERMISSAO_REQUISICAO_CODIGO);
                    dialog.dismiss();
                }
            })
            .negativeText("Cancelar")
            .onNegative(new MaterialDialog.SingleButtonCallback() {
                @Override
                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                    dialog.dismiss();
                }
            })
            .show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent = null;

        if (id == R.id.nav_configuracoes) {
            intent = new Intent(this, ConfiguracoesActivity.class);
        }
        else if (id == R.id.nav_contato) {
            intent = new Intent(this, ContatoActivity.class);
        }
        else if (id == R.id.nav_sobre) {
            intent = new Intent(this, SobreActivity.class);
        }

        if( intent != null ){
            startActivity(intent);
            return true;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void acessarLoginActivity(View view){
        invocaActivity( LoginActivity.class );
    }

    public void acessarCadastroActivity(View view){
        invocaActivity( CadastroActivity.class );
    }

    private void invocaActivity( Class cls ){
        Intent intent = new Intent(this, cls);
        startActivity( intent );
    }
}
