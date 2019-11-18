public class Main {
    public static void main(String[] args) {
        Jogador j1 = new Jogador("Fulano",TipoJogador.USUARIO);
        Jogador j2 = new Jogador("UCP",TipoJogador.COMPUTADOR);
        /*
        BatalhaNaval jogo = new BatalhaNaval(j1,j2);
        jogo.iniciar();
        */
        Tabuleiro t1 = new Tabuleiro();
        
        boolean res;
        
        res = t1.posicionaNavio(TipoNavio.PORTA_AVIOES, 1, 1, TipoOrientacao.HORIZONTAL);
        res = t1.posicionaNavio(TipoNavio.CRUZADOR, 3, 3, TipoOrientacao.HORIZONTAL);
        res = t1.posicionaNavio(TipoNavio.CRUZADOR, 3, 1, TipoOrientacao.VERTICAL);
        // res = t1.posicionaNavio(TipoNavio.CRUZADOR, 5, 5, TipoOrientacao.HORIZONTAL);
        res = t1.posicionaNavio(TipoNavio.DESTROIER, 4, 3, TipoOrientacao.HORIZONTAL);
        res = t1.posicionaNavio(TipoNavio.DESTROIER, 5, 3, TipoOrientacao.HORIZONTAL);
        res = t1.posicionaNavio(TipoNavio.DESTROIER, 6, 3, TipoOrientacao.HORIZONTAL);

        res = t1.posicionaNavio(TipoNavio.SUBMARINO, 7, 1, TipoOrientacao.HORIZONTAL);
        res = t1.posicionaNavio(TipoNavio.SUBMARINO, 7, 6, TipoOrientacao.HORIZONTAL);
        res = t1.posicionaNavio(TipoNavio.SUBMARINO, 8, 1, TipoOrientacao.HORIZONTAL);
        res = t1.posicionaNavio(TipoNavio.SUBMARINO, 8, 6, TipoOrientacao.HORIZONTAL);

        //     PORTA_AVIOES(5,1), CRUZADOR(4,2), DESTROIER(3,3), SUBMARINO(2,4);
        t1.print();
        /*
        if ( t1.posicionaNavio(TipoNavio.SUBMARINO,3,2,TipoOrientacao.VERTICAL) )
           System.out.println("OK");
        t1.print();
        */
    }
}