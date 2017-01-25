package br.com.thiengo.laranjeirasguiacomercial.extras;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

import br.com.thiengo.laranjeirasguiacomercial.R;
import br.com.thiengo.laranjeirasguiacomercial.domain.Avaliacao;
import br.com.thiengo.laranjeirasguiacomercial.domain.Categoria;
import br.com.thiengo.laranjeirasguiacomercial.domain.Comercio;
import br.com.thiengo.laranjeirasguiacomercial.domain.Data;
import br.com.thiengo.laranjeirasguiacomercial.domain.Imagem;
import br.com.thiengo.laranjeirasguiacomercial.domain.NotificacaoImpl;
import br.com.thiengo.laranjeirasguiacomercial.domain.Resposta;
import br.com.thiengo.laranjeirasguiacomercial.domain.User;

/**
 * Created by viniciusthiengo on 12/01/17.
 */

public class Mock {
    public static User criarUserAleatorio(){
        int[] imagens = {R.drawable.user_1, R.drawable.user_2, R.drawable.user_3, R.drawable.user_4};
        String[] nomes = {"Wesley Jonathan", "Márcia Champ", "Michale Jackson", "Celina Almeida"};
        int posImagem = (int) (Math.random() * 4);
        int posNome = (int) (Math.random() * 4);
        User user = new User( imagens[posImagem], nomes[posNome] );

        return user;
    }

    public static Data criarDataAleatorio(){
        int diasEnvio = (int) (Math.random() * 10) + 3;
        long envio = System.currentTimeMillis() - (diasEnvio * 24 * 60 * 60 * 1000);
        int diasEdicao = (int) (Math.random() * 3);
        long edicao = System.currentTimeMillis() - (diasEdicao * 24 * 60 * 60 * 1000);

        // passando para segundos
        envio = envio / 1000;
        edicao = edicao / 1000;

        //Log.i("log", "Envio: "+envio);
        //Log.i("log", "Edição: "+edicao);

        return new Data( envio, edicao );
    }

    public static Resposta criarRespostaAleatorio(){
        User user = criarUserAleatorio();
        int posResposta = (int) (Math.random() * 5);
        String[] respostas = {
                "In the previous series of articles we looked at DownloadManager and saw that DownloadManager actually handles the sharing of downloaded content with other apps.",
                "But what if we actually need to do this and we’re not using DownloadManager?",
                "A common case for such things would be if we either want to share content with other apps or, as in the example in the previous series.",
                "Let’s first consider why sharing files can be problematic.",
                "The most obvious place for us to store content is in the app’s private storage."
        };
        Resposta resposta = new Resposta( user, respostas[ posResposta ], criarDataAleatorio() );

        return resposta;
    }

    public static List<Resposta> criarRespostasAleatorio(){
        List<Resposta> respostas = new ArrayList<>();
        int totalrespostas = (int) (Math.random() * 3);

        for( int i = 0; i < totalrespostas; i++ ){
            respostas.add( criarRespostaAleatorio() );
        }

        return respostas;
    }

    public static Avaliacao criarAvaliacaoAleatorio(){
        User user = criarUserAleatorio();
        int posMensagem = (int) (Math.random() * 5);
        int estrelasAvaliacao = (int) (Math.random() * 2) + 3;
        String[] mensagens = {
                "Note that this approach is more biased and less efficient than a nextInt approach",
                "The Java Math library function Math.random() generates a double value in the range [0,1). Notice this range does not include the 1.",
                "In order to get a specific range of values first, you need to multiply by the magnitude of the range of values you want covered.",
                "This returns a value in the range [0,Max-Min), where 'Max-Min' is not included.",
                "Now you need to shift this range up to the range that you are targeting. You do this by adding the Min value."
        };
        Avaliacao avaliacao = new Avaliacao( user, mensagens[ posMensagem ], estrelasAvaliacao, criarDataAleatorio() );
        avaliacao.setRespostas( criarRespostasAleatorio() );

        return avaliacao;
    }

    public static List<Avaliacao> criarAvaliacoesAleatorio(){
        List<Avaliacao> avaliacoes = new ArrayList<>();
        avaliacoes.add( criarAvaliacaoAleatorio() );
        avaliacoes.add( criarAvaliacaoAleatorio() );
        avaliacoes.add( criarAvaliacaoAleatorio() );
        avaliacoes.add( criarAvaliacaoAleatorio() );
        avaliacoes.add( criarAvaliacaoAleatorio() );

        return avaliacoes;
    }

    public static Comercio criarComercioAleatorio(){
        int[] imagens = {R.drawable.loja_1, R.drawable.loja_2, R.drawable.loja_3, R.drawable.loja_4};
        String[] nomesFantasia = {"Pontiac 22 Street", "McDonalds Brasil", "Shopping Aldeia das Laranjeiras"};
        String[] telefones = {"(27) 9-9865-3325", "(27) 9-9985-8684", "(27) 9-8865-3214"};
        String email = "thiengocalopsita@gmail.com";
        String site = "http://www.thiengo.com.br";
        String[] localizacoes = {
                "Centro Cultural São Paulo, Rua Vergueiro, 1000 - Paraíso, São Paulo - SP, 01504-000, Brasil",
                "Museu da Imigração do Estado de São Paulo, R. Visc. de Parnaíba, 1316 - Mooca, São Paulo - SP, 03164-300, Brasil",
                "Anhembi, Av. Olavo Fontoura, 1209 - Santana, São Paulo - SP, 02012-021, Brasil"
        };
        String[] youTubeCodes = { "dXrHCAxdE-0", "yFiiBet0NJY", "K-OTJU6-PnE" };
        double avaliacaoPontos = Math.random() * 5;
        int avaliacaoQtd = (int) (Math.random() * 109);
        int posImagem = (int) (Math.random() * 4);
        int posNome = (int) (Math.random() * 3);
        int posTelefone = (int) (Math.random() * 3);
        int posLocal = (int) (Math.random() * 3);
        int posYouTubeCode = (int) (Math.random() * 3);
        int qtdComentarios = (int) (Math.random() * 4);
        boolean statusNotificacao = (int)(Math.random() * 3) % 2 == 0;
        Comercio comercio = new Comercio(
            imagens[ posImagem ],
            nomesFantasia[ posNome ],
            telefones[ posTelefone ],
            email,
            site,
            localizacoes[ posLocal ],
            avaliacaoPontos,
            avaliacaoQtd,
            statusNotificacao,
            youTubeCodes[ posYouTubeCode ] );

        for( int i = 0; i < qtdComentarios; i++ ){
            comercio.getAvaliacoes().add( criarAvaliacaoAleatorio() );
        }

        return comercio;
    }

    public static ArrayList<NotificacaoImpl> obterCategoriasNotificacao(Context context){
        String[] categoriasString = context.getResources().getStringArray( R.array.cateogiras );
        ArrayList<NotificacaoImpl> categorias = new ArrayList<>();

        for( String categoria : categoriasString ){
            boolean status = (int)(Math.random() * 3) % 2 == 0;
            categorias.add( new Categoria( categoria, status ) );
        }
        return categorias;
    }

    public static ArrayList<NotificacaoImpl> obterComerciosNotificacao(){
        ArrayList<NotificacaoImpl> comercios = new ArrayList<>();
        int posComercios = (int) (Math.random() * 4) + 4;

        for( int i = 0; i < posComercios; i++ ){
            comercios.add( criarComercioAleatorio() );
        }

        return comercios;
    }

    public static Imagem criarImagemAleatorio(){
        int[] imagens = {R.drawable.user_1, R.drawable.user_2, R.drawable.user_3, R.drawable.user_4};
        String[] legendas = {
                "Faixada do estabeleciomento",
                "Alguns amigos se reunindo para desfrutar dos melhores frutos do mar",
                "Churrasco marítmo, primeiro do estado com essa característica",
                ""// Assistindo ao jogo depois da hora do rush
        };
        int posImagem = (int) (Math.random() * 4);
        int posLegenda = (int) (Math.random() * 4);
        int likes = (int) (Math.random() * 100);
        int compartilhamentos = (int) (Math.random() * 100);
        Imagem imagem = new Imagem( "", imagens[posImagem], legendas[posLegenda], likes, compartilhamentos );

        return imagem;
    }

    public static ArrayList<Imagem> criarGaleriaAleatorio(){
        ArrayList<Imagem> galeria = new ArrayList<>();
        for( int i = 0; i < 20; i++ ){
            galeria.add( criarImagemAleatorio() );
        }

        return galeria;
    }
}





