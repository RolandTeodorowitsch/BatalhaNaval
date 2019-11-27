import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Seu nome: ");
        String nome = in.nextLine();
        Jogador j1 = new Jogador(nome,TipoJogador.USUARIO);
        Jogador j2 = new Jogador("UCP",TipoJogador.COMPUTADOR);
        BatalhaNaval jogo = new BatalhaNaval(j1,j2);
        jogo.iniciar();
    }
}