public class Main {
    public static void main(String[] args) {
        Jogador j1 = new Jogador("Fulano",TipoJogador.USUARIO);
        Jogador j2 = new Jogador("UCP",TipoJogador.COMPUTADOR);
        /*
        BatalhaNaval jogo = new BatalhaNaval(j1,j2);
        jogo.iniciar();
        */
        Tabuleiro t1 = new Tabuleiro();
        t1.print();
        /*
        if ( t1.posicionaNavio(TipoNavio.SUBMARINO,3,2,TipoOrientacao.VERTICAL) )
           System.out.println("OK");
        t1.print();
        */
    }
}