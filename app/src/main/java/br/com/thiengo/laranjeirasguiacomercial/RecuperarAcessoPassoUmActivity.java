package br.com.thiengo.laranjeirasguiacomercial;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.URLSpan;
import android.text.util.Linkify;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;

import br.com.thiengo.laranjeirasguiacomercial.extras.URLSpanSemUnderline;

public class RecuperarAcessoPassoUmActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recuperar_acesso_passo_um);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if( getSupportActionBar() != null ){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        TextView tvEmailContato = (TextView) findViewById(R.id.tv_email_contato);
        tvEmailContato.setLinkTextColor(ContextCompat.getColor(this, R.color.colorLink));
        Linkify.addLinks(tvEmailContato, Linkify.EMAIL_ADDRESSES);
        removeUnderlines( tvEmailContato );
    }

    private void removeUnderlines(TextView textView) {
        Spannable s = new SpannableString(textView.getText());
        URLSpan[] spans = s.getSpans(0, s.length(), URLSpan.class);
        for (URLSpan span: spans) {
            int start = s.getSpanStart(span);
            int end = s.getSpanEnd(span);
            s.removeSpan(span);
            span = new URLSpanSemUnderline(span.getURL());
            s.setSpan(span, start, end, 0);
        }
        textView.setText(s);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    public void iniciarRecuperacaoAcesso( View view ){
        new MaterialDialog.Builder(this)
                .title("Recuperação de acesso")
                .content("O processo de recuperação de acesso foi inciado. Entre no email cadastrado em sua conta e siga as instruções para o próximo passo.")
                .positiveText("Ok")
                .positiveColorRes( R.color.colorLink )
                .show();
    }
}
