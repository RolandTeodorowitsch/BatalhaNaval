public class Main {
    public static void main(String[] args) {
        Jogador j1 = new Jogador("Fulano",TipoJogador.USUARIO);
        Jogador j2 = new Jogador("UCP",TipoJogador.COMPUTADOR);
        BatalhaNaval jogo = new BatalhaNaval(j1,j2);
        jogo.iniciar();
    }
}