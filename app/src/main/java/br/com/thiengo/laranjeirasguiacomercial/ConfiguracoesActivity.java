package br.com.thiengo.laranjeirasguiacomercial;

import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;

import br.com.thiengo.laranjeirasguiacomercial.fragments.ConfigLoginFragment;
import br.com.thiengo.laranjeirasguiacomercial.fragments.ConfigNotificacoesFragment;
import br.com.thiengo.laranjeirasguiacomercial.fragments.ConfigPerfilFragment;
import br.com.thiengo.laranjeirasguiacomercial.fragments.ConfigSenhaFragment;

public class ConfiguracoesActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter sectionsPagerAdapter;
    private ViewPager vpSecoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if( getSupportActionBar() != null ){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        sectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        vpSecoes = (ViewPager) findViewById(R.id.vp_secoes);
        vpSecoes.setAdapter(sectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tl_abas);
        tabLayout.setupWithViewPager(vpSecoes);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch ( position ){
                case 1:
                    return new ConfigLoginFragment();
                case 2:
                    return new ConfigSenhaFragment();
                case 3:
                    return new ConfigNotificacoesFragment();
                default:
                    return new ConfigPerfilFragment();
            }
        }

        @Override
        public int getCount() {
            return 4;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 1:
                    return "Login";
                case 2:
                    return "Senha";
                case 3:
                    return "Notificações";
                default:
                    return "Dados perfil";
            }
        }
    }
}
