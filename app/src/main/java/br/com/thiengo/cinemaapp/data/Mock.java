package br.com.thiengo.cinemaapp.data;

import java.util.ArrayList;

import br.com.thiengo.cinemaapp.domain.Filme;
import br.com.thiengo.cinemaapp.domain.Sala;

/**
 * Created by viniciusthiengo on 03/03/17.
 */

public class Mock {
    public static Sala gerarSala( int numeroSala, int qtdHor ){
        String[] horarios = {
            "11:00", "12:30", "12:55", "13:50", "14:30",
            "15:55", "16:25", "17:45", "18:40", "19:15",
            "21:10", "20:55", "22:35", "23:55" };

        Sala sala = new Sala();
        sala.setNumero(numeroSala);

        qtdHor = qtdHor == 0 ? 1 : qtdHor;
        qtdHor = qtdHor >= horarios.length ? horarios.length : qtdHor;
        for( int i = 0; i < qtdHor; i++ ){
            sala.getHorarios().add( horarios[ i ] );
        }

        return sala;
    }

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
        String[] synopsis = {
            "Em 2024, os mutantes estão em declínio e as pessoas não " +
                "sabem o motivo. Uma organização estátransformando as " +
                "crianças mutantes em assassinas e Wolverine, a pedido " +
                "do Professor Xavier, precisa proteger a jovem e poderosa " +
                "Laura Kinney, conhecida como X-23. Enquanto isso, o " +
                "vilão Nathaniel Essex amplia seu projeto de destruição.",
            "Um grupo de soldados britânicos está lutando na China e se " +
                "depara com o início das construções da Grande Muralha. " +
                "Eles percebem que o intuito não é apenas proteger a " +
                "população do inimigo mongol e que a construção esconde na " +
                "verdade um grande segredo.",
            "Incomodada com os hábitos e atitudes de Christian Grey, " +
                "Anastasia decide terminar o relacionamento e focar no " +
                "desenvolvimento de sua carreira. O desejo, porém, fala mais " +
                "alto e ela logo volta aos jogos sexuais do conturbado empresário.",
            "Em 8 esquetes, a comédia apresenta diferentes tramas que " +
                "dialogam com situações recorrentes do dia a dia. A partir " +
                "de um encontro em uma convenção de youtubers, os personagens " +
                "vivenciam momentos de descobertas, ira, equívocos, raiva, " +
                "inveja e confusões em busca pela fama.",
            "Batman descobre que acidentalmente adotou um garoto órfão, que " +
                "se torna ninguém menos que Robin. A dupla formada pelo " +
                "arrogante Homem-Morcego e o empolgado ajudante deve combater " +
                "o crime e prender o Coringa.",
            "Enquanto Gustavinho, Fefa e os demais integrantes do clube " +
                "Bugigangue estão preocupados com os trabalhos da escola, nem " +
                "imaginam que em um ponto distante da galáxia o vilão Gana " +
                "Golber tomou o poder da Confederação dos Planetas, ameaçando " +
                "a paz do universo. Expulsos da confederação, sete Invas, " +
                "alienígenas atrapalhados e ingênuos, conseguem escapar ao " +
                "cerco de Gana, mas na fuga sua nave é danificada e cai na " +
                "Terra. Logo os Invas fazem amizade com as crianças do clube, " +
                "consertam a nave e embarcam juntos numa aventura intergaláctica " +
                "para restaurar a paz do universo.",
            "O pianista Sebastian conhece a atriz iniciante Mia e os dois se " +
                "apaixonam perdidamente. Em busca de oportunidades para suas " +
                "carreiras na competitiva cidade, os jovens tentam fazer o " +
                "relacionamento amoroso dar certo enquanto perseguem fama e " +
                "sucesso.",
            "John Wick é forçado a deixar a aposentadoria mais uma vez por causa " +
                "de uma promessa antiga e viaja para Roma, com o objetivo de " +
                "ajudar um velho amigo a derrubar uma organização secreta, " +
                "perigosa e mortal de assassinos procurados em todo o mundo.",
            "Em uma missão para eliminar um embaixador nazista em Casablanca, " +
                "no Marrocos, os espiões Max Vatan e Marianne Beausejour se " +
                "apaixonam perdidamente e decidem se casar. Os problemas " +
                "começam anos depois, com suspeitas sobre uma conexão entre " +
                "Marianne e os alemães. Intrigado, Max decide investigar o " +
                "passado da companheira e os dias de felicidade do casal vão " +
                "por água abaixo."
        };

        Filme filme = new Filme();
        filme.setNome( nomes[posicao] );
        filme.setUrlImagem( imagens[posicao] );
        filme.setSynopse( synopsis[posicao] );

        for( int i = 0; i < qtdSalas; i++ ){
            int horarios = 1 + (int)(Math.random() * 14);
            Sala sala = gerarSala( i + 1, horarios );
            filme.getSalas().add( sala );
        }

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
