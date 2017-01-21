package br.com.thiengo.laranjeirasguiacomercial.extras;

import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.thiengo.laranjeirasguiacomercial.R;
import br.com.thiengo.laranjeirasguiacomercial.domain.Categoria;
import br.com.thiengo.laranjeirasguiacomercial.domain.Comentario;
import br.com.thiengo.laranjeirasguiacomercial.domain.Comercio;
import br.com.thiengo.laranjeirasguiacomercial.domain.Imagem;
import br.com.thiengo.laranjeirasguiacomercial.domain.NotificacaoImpl;
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

    public static Comentario criarComentarioAleatorio(){
        User user = criarUserAleatorio();
        int posMensagem = (int) (Math.random() * 5);
        String[] mensagens = {
                "Note that this approach is more biased and less efficient than a nextInt approach",
                "The Java Math library function Math.random() generates a double value in the range [0,1). Notice this range does not include the 1.",
                "In order to get a specific range of values first, you need to multiply by the magnitude of the range of values you want covered.",
                "This returns a value in the range [0,Max-Min), where 'Max-Min' is not included.",
                "Now you need to shift this range up to the range that you are targeting. You do this by adding the Min value."
        };
        Comentario comentario = new Comentario(user, mensagens[ posMensagem ]);

        return comentario;
    }

    public static Comercio criarComercioAleatorio(){
        int[] imagens = {R.drawable.loja_1, R.drawable.loja_2, R.drawable.loja_3, R.drawable.loja_4};
        String[] nomesFantasia = {"Pontiac 22 Street", "McDonalds Brasil", "Shopping Aldeia das Laranjeiras"};
        String[] localizacoes = {
                "Centro Cultural São Paulo, Rua Vergueiro, 1000 - Paraíso, São Paulo - SP, 01504-000, Brasil",
                "Museu da Imigração do Estado de São Paulo, R. Visc. de Parnaíba, 1316 - Mooca, São Paulo - SP, 03164-300, Brasil",
                "Anhembi, Av. Olavo Fontoura, 1209 - Santana, São Paulo - SP, 02012-021, Brasil"
        };
        double avaliacaoPontos = Math.random() * 5;
        int avaliacaoQtd = (int) (Math.random() * 109);
        int posImagem = (int) (Math.random() * 4);
        int posNome = (int) (Math.random() * 3);
        int posLocal = (int) (Math.random() * 3);
        int qtdComentarios = (int) (Math.random() * 4);
        boolean statusNotificacao = (int)(Math.random() * 3) % 2 == 0;
        Comercio comercio = new Comercio(
                imagens[ posImagem ],
                nomesFantasia[ posNome ],
                localizacoes[ posLocal ],
                avaliacaoPontos,
                avaliacaoQtd,
                statusNotificacao );

        for( int i = 0; i < qtdComentarios; i++ ){
            comercio.getComentarios().add( criarComentarioAleatorio() );
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





