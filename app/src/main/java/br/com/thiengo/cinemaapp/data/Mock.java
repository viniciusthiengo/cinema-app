package br.com.thiengo.cinemaapp.data;

import java.util.ArrayList;

import br.com.thiengo.cinemaapp.domain.Filme;
import br.com.thiengo.cinemaapp.domain.Sala;

/**
 * Created by viniciusthiengo on 03/03/17.
 */

public class Mock {
    public static Filme gerarFilme( int posicao, int qtdSalas ){
        String[] nomes = {
            "Logan",
            "A Grande Muralha",
            "Cinquenta Tons Mais Escuros",
            "Internet - O Filme",
            "LEGO Batman: O Filme",
            "BugiGangue no Espaço",
            "La La Land",
            "John Wick: Um Novo Dia Para Matar",
            "Allied"
        };
        String[] imagens = {
            "http://gcn.net.br/dir-arquivo-imagem/2017/02/20170228130021_64401194.jpg",
            "http://imagens.cinemacomrapadura.com.br/2016/11/20161119-great-wall.jpg",
            "http://br.web.img3.acsta.net/r_640_360/videothumbnails/16/12/07/17/40/499455.jpg",
            "https://static.omelete.uol.com.br/media/extras/conteudos/internet-o-filme.jpg",
            "http://cinequattro.com/files/2016/12/lego-batman-the-movie-dc-superheroes-unite-515f6fcf7781f.jpg",
            "https://i.ytimg.com/vi/JjM0QtmjP1U/maxresdefault.jpg",
            "http://www.lalaland.movie/assets/images/og.jpg",
            "https://observatoriodocinema.bol.uol.com.br/wp-content/uploads/2016/09/john-wick-2-1.jpg",
            "https://i.ytimg.com/vi/HSCQWX-pUSg/maxresdefault.jpg"
        };

        Filme filme = new Filme();
        filme.setNome( nomes[posicao] );
        filme.setUrlImagem( imagens[posicao] );
        filme.setNumSalas( qtdSalas );

        return filme;
    }

    public static ArrayList<Filme> gerarFilmes(){
        ArrayList<Filme> filmes = new ArrayList<>();

        for( int i = 0; i < 9; i++ ){
            int salas = 2 + (int)(Math.random() * 8);
            filmes.add( gerarFilme( i, salas ) );
        }

        return filmes;
    }
}
