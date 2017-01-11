package br.com.thiengo.laranjeirasguiacomercial;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;

public class SobreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sobre);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if( getSupportActionBar() != null ){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        String conteudo = "<p>Sou Vinícius Thiengo e além de manter o blog Thiengo [Calopsita] sou desenvolvedor profissional (Web e Android) e tenho um outro projeto em atividade, o marketplace BirdingBox.</p><p><b>Você programa desde criança?</b></p><p>Não, na verdade conheci programação de computadores somente quando entrei na faculdade. Antes disso o que eu conhecia de computação era:\tMSN, Orkut, The Sims, OMatic (o maior cheater de games - até o PinBall não escapava) e Brasfoot\t(sem falta de humildade, eu fui \"O Mago\" do brasfoot - isso, sem macete de dinheiro).</p><p><b>Você então tem formação em computação, certo?</b></p><p>Sim, sou bacharel em Sistemas de Informação\tpelo IFES (Instituto Federal do Espírito Santo) campus Serra.</p><p>Cheguei a estudar o 1º período de Administração na UFES (Universidade Federal do Espírito Santo), pois um dos meus objetivos era se tornar stockbroker, corretor da bolsa de valores.</p><p>Nunca esqueci da frase no filme \"Wall Street - O Dinheiro Nunca Dorme\" onde Josh Brolin interpretando Bretton James disse, quando perguntado pelo jovem Jake Moore\t(interpretado por Shia LaBeouf) sobre qual era o número máximo dele (eles estavam falando de dinheiro). O personagem Bretton James, sem rodeios, respondeu: \"Meu número é ‘MAIS'\". Pode soar um pouco soberbo, mas po%#@, foi fo$@&.</p><p>Se acessar as resenhas que tenho sobre\t&nbsp;<a href=\"./livros\" title=\"Resenhas de livros\" target=\"_blank\">livros que li<i class=\"fa fa-external-link\" aria-hidden=\"true\"></i></a>(<b>Sério? Tem como fazer resenha de livro que você não leu?</b>), logo nas primeiras vai notar que tem várias sobre livros de investimentos em bolsa, mais precisamente sobre análise técnica e opções de ações (essas últimas que acabaram com minha possível carreira de corretor).</p><p><b>O que você quer dizer com: \"essas últimas que acabaram com minha possível carreira de corretor”?</b></p><p>Que minha carreira como aprendiz de stockbroker foi curta e que as opções de ação foram responsáveis pelo empurrão final.</p><p>Depois de ler um bocado sobre investimentos em bolsa, tinha chegado a hora de investir. Já sabia o suficiente sobre padrões de gráficos: mulher grávida, martelinho invertido, … e muitos outros. Contratei um online broker e comecei a participar do pregão eletrônico. Comecei com, se não me engano, um pouco mais que R$ 240,00.</p><p><b>Ah, mais isso não é nada!</b></p><p>Para mim era. Meu cargo naquela época era de\testagiário do setor de T.I. do Centro de Ensino a Distância do IFES, ou seja, R$ 240,00 era dinheiro\tsem fim!</p><p>Continuando... na época que entrei na bolsa adivinha qual empresa era a \"bambambam\"? Isso mesmo que veio a sua\tmente, a ENRON brasileira! OGX. Entrei com afinco na OGX3 (se não me engano era esse o código). Todo o dinheiro foi investido nesse papel.</p><p>Se me lembro bem comprei a R$ 8,40 cada pacote de ações. Entrei em êxtase quando vi um pico de R$ 10,40. Me vi trilhando o caminho do Warren Buffett, porém em meses e não em décadas.</p><p>Porém, em pouco tempo, as coisas começaram a aparecer na OGX, na verdade, a não aparecer. Pois as promessas de barris de petróleo, as metas, ..., nada aconteceu. E pior que sua mãe com raiva e todo o corpo de operações\tespeciais Navy Seal... pior que todos esses é o mercado, se você errar ele não te perdoa. Promessas não cumpridas não têm perdão. Resultado? As ações da empresa (de todo o conglomerado: OGX, MMX, LLX, OCX, ...) começaram a cair sem controle.</p>";
        TextView tvSobre = (TextView) findViewById(R.id.tv_sobre);

        try{ // A PARTIR DA API 24
            //tvSobre.setText( Html.fromHtml(conteudo, Html.FROM_HTML_MODE_LEGACY) );
            tvSobre.setText( Html.fromHtml(conteudo) );
        }
        catch( Exception e ){
            tvSobre.setText( conteudo );
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
