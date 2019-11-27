import java.util.Random;
import java.util.Scanner;

public class BatalhaNaval {
    private Jogador j1, j2;
    private Tabuleiro t1, t2;
    private int estado; // 0 = tabuleiros vazios; 1 = navios posicionados; 2 = jogadas; 3 = encerrado
    private Random gerador;
    private Scanner in;
    
    public BatalhaNaval(Jogador j1, Jogador j2) {
        this.j1 = j1;
        this.j2 = j2;
        t1 = new Tabuleiro();
        t2 = new Tabuleiro();
        estado = 0;
        gerador = new Random();
        in = new Scanner(System.in);
    }
    
    private void posicionaNaviosComputador(Tabuleiro tab) {
        TipoOrientacao orient;
        int quantidade, tamanho, lin, col;
        int numLinhas = tab.obtemNumLinhas(), numColunas = tab.obtemNumColunas();
        TipoNavio navio;
        boolean res;
        for (int tipo=0; tipo<TipoNavio.values().length; ++tipo) {
            navio = TipoNavio.values()[tipo];
            quantidade = navio.obtemQuantidade();
            tamanho = navio.obtemTamanho();
            for (int i=0; i<quantidade; ++i) {
                res = false;
                while (!res) {
                    orient = (gerador.nextInt(2)==0)?TipoOrientacao.HORIZONTAL:TipoOrientacao.VERTICAL;
                    if (orient == TipoOrientacao.HORIZONTAL) {
                       lin = gerador.nextInt(numLinhas);
                       col = gerador.nextInt(numColunas-tamanho);
                    }
                    else {
                       lin = gerador.nextInt(numLinhas-tamanho);
                       col = gerador.nextInt(numColunas);
                    }
                    res = tab.posicionaNavio(navio, lin, col, orient);
                }
            }
             
        }
    }
    
    private void posicionaNaviosUsuario(Tabuleiro tab, Jogador jog) {
        TipoOrientacao orient;
        int quantidade, tamanho, lin, col;
        int numLinhas = tab.obtemNumLinhas(), numColunas = tab.obtemNumColunas();
        TipoNavio navio;
        boolean res;
        for (int tipo=0; tipo<TipoNavio.values().length; ++tipo) {
            navio = TipoNavio.values()[tipo];
            quantidade = navio.obtemQuantidade();
            tamanho = navio.obtemTamanho();
            for (int i=0; i<quantidade; ++i) {
                tab.imprime();
                System.out.printf("%s, posicione um %s no tabuleiro!\n",jog.obtemNome(),navio.obtemNome());
                res = false;
                while (!res) {
                    System.out.print("Forneça coordenadas no formato linha[A-J]-coluna[0-9]-sentido[HV] (por exemplo \"B5H\" ou \"C3V\"): ");
                    String entrada = in.next().toUpperCase();
                    if (entrada.length() < 3)
                       continue;
                    lin = entrada.charAt(0)-'A';
                    if (lin<0 || lin>=numLinhas)
                       continue;
                    col = entrada.charAt(1)-'0';
                    if (col<0 || col>=numColunas)
                       continue;
                    if (entrada.charAt(2)=='H')
                       orient = TipoOrientacao.HORIZONTAL;
                    else if (entrada.charAt(2)=='V')
                       orient = TipoOrientacao.VERTICAL;
                    else
                       continue;
                    res = tab.posicionaNavio(navio, lin, col, orient);
                    if (res)
                       System.out.println("Navio posicionado!");
                    else
                       System.out.println("Localizacao INVÁLIDA!");
                }
            }
             
        }
    }

    
    private void executaTiroUsuario(Tabuleiro tab, Jogador jog) {
        int lin, col;
        int numLinhas = tab.obtemNumLinhas(), numColunas = tab.obtemNumColunas();
        boolean res;
        
        tab.imprimeParaAdversario();
        System.out.printf("%s, execute um tiro no tabuleiro do seu adversário!\n",jog.obtemNome());
        res = false;
        while (!res) {
            System.out.print("Forneça coordenadas no formato linha[A-J]-coluna[0-9] (por exemplo \"B5\" ou \"C3\"): ");
            String entrada = in.next().toUpperCase();
            if (entrada.length() < 2)
                continue;
            lin = entrada.charAt(0)-'A';
            if (lin<0 || lin>=numLinhas)
                continue;
            col = entrada.charAt(1)-'0';
            if (col<0 || col>=numColunas)
               continue;
            res = tab.executaTiro(lin, col);
            if (res)
               System.out.println("Tiro executado!");
            else
                System.out.println("Localizacao INVÁLIDA!");
        }
    }

    public void iniciar() {
        if (j1.obtemTipo() == TipoJogador.COMPUTADOR)
           posicionaNaviosComputador(t1);
        else {
           posicionaNaviosUsuario(t1,j1);
           t1.imprime();
        }
        if (j2.obtemTipo() == TipoJogador.COMPUTADOR)
           posicionaNaviosComputador(t2);
        else {
           posicionaNaviosUsuario(t2,j2);
           t2.imprime();
        }
        System.out.println("\nPOSICIONAMENTO CONCLUÍDO!\n");
        estado = 1;
        for (int i=0; i<10; ++i)
            executaTiroUsuario(t2,j1);
    }
    
}
