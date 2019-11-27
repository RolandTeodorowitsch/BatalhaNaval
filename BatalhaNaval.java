import java.util.Random;
import java.util.Scanner;

public class BatalhaNaval {
    private Jogador[] jogadores;
    private Tabuleiro[] tabuleiros;
    private int estado; // 0 = tabuleiros vazios; 1 = navios posicionados; 2 = jogadas; 3 = encerrado
    private Random gerador;
    private Scanner in;
    
    public BatalhaNaval(Jogador j1, Jogador j2) {
        jogadores = new Jogador[2];
        jogadores[0] = j1;
        jogadores[1] = j2;
        tabuleiros = new Tabuleiro[2];
        tabuleiros[0] = new Tabuleiro();
        tabuleiros[1] = new Tabuleiro();
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

    private void executaTiroComputador(Tabuleiro tab, Jogador jog) {
        int lin, col;
        int numLinhas = tab.obtemNumLinhas(), numColunas = tab.obtemNumColunas();
        
        boolean res = false;
        while (!res) {
            lin = gerador.nextInt(numLinhas);
            col = gerador.nextInt(numColunas);
            res = tab.executaTiro(lin, col);
            if (res) {
                TipoCasa c = tab.obtem(lin, col);
                if (c == TipoCasa.AGUA)
                    System.out.printf("%s acertou na agua...\n",jog.obtemNome());
                else if (c == TipoCasa.EXPLOSAO)
                    System.out.printf("%s acertou algum navio...\n",jog.obtemNome());
            }
        }
    }

    
    private void executaTiroUsuario(Tabuleiro tab, Jogador jog) {
        int lin, col;
        int numLinhas = tab.obtemNumLinhas(), numColunas = tab.obtemNumColunas();
        
        tab.imprimeParaAdversario();
        System.out.printf("%s, execute um tiro no tabuleiro do seu adversário!\n",jog.obtemNome());
        boolean res = false;
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
               System.out.println("Tiro executado!\n");
            else
                System.out.println("Localizacao INVÁLIDA!");
        }
    }

    public void iniciar() {
        for (int j=0; j<jogadores.length; ++j)
            if (jogadores[j].obtemTipo() == TipoJogador.COMPUTADOR)
                posicionaNaviosComputador(tabuleiros[j]);
            else {
                posicionaNaviosUsuario(tabuleiros[j],jogadores[j]);
                tabuleiros[j].imprime();
            }
        System.out.println("\nPOSICIONAMENTO CONCLUÍDO!\n");
        estado = 1;
        int jogador = 0;
        boolean fim = false;
        while (!fim) {
            for (int j=0; j<jogadores.length; ++j) {
                System.out.printf("%20s - frota destruida: %d de %d\n",jogadores[j].obtemNome(),
                                                                        tabuleiros[j].obtemNumExplosoes(),
                                                                        tabuleiros[j].obtemNumCasasOcupadas());
            }
            System.out.println();
            int adversario = 1-jogador;
            if (jogadores[jogador].obtemTipo() == TipoJogador.COMPUTADOR)
                executaTiroComputador(tabuleiros[adversario],jogadores[jogador]);
            else
                executaTiroUsuario(tabuleiros[adversario],jogadores[jogador]);
            if (tabuleiros[adversario].obtemNumCasasOcupadas() == tabuleiros[adversario].obtemNumExplosoes()) {
                System.out.println(jogadores[jogador].obtemNome()+" venceu a partida!");
                fim = true;
            }
            else
                jogador = adversario;
        }
    }
    
}
