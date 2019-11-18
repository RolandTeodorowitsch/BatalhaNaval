public class Tabuleiro {

    private final static int LINHAS = 10;
    private final static int COLUNAS = 10;
    private TipoCasa[][] tabuleiro;
    private int[] numNavios;
    
    public Tabuleiro() {
        tabuleiro = new TipoCasa[LINHAS][COLUNAS];
        numNavios = new int[TipoNavio.values().length];
        for (int i=0; i<numNavios.length; ++i)
            numNavios[i] = 0;
        for (int i=0; i<LINHAS; ++i)
            for (int j=0; j<COLUNAS; ++j)
                tabuleiro[i][j] = TipoCasa.VAZIO;
    }
    
    public void print() {
        String linha = " +-+-+-+-+-+-+-+-+-+-+";
        System.out.print("  ");
        for (int j=0; j<COLUNAS; ++j)
            System.out.printf("%-2d",j+1);
        System.out.println();
        for (int i=0; i<LINHAS; ++i) {
            System.out.println(linha);
            System.out.printf("%c|",'A'+i);
            for (int j=0; j<COLUNAS; ++j) {
                switch(tabuleiro[i][j]) {
                    case OCUPADO:
                         System.out.print("#|");
                         break;
                    case AGUA:
                         System.out.print(".|");
                         break;
                    case EXPLOSAO:
                         System.out.print("*|");
                         break;
                    default:
                         System.out.print(" |");
                         break;
                }
            }
            System.out.println();
        }
        System.out.println(linha);
    }
    
    public boolean posicionaNavio(TipoNavio navio,int lin, int col, TipoOrientacao ori) {
        int tamNav = navio.obtemTamanho();
        if (lin < 0 || lin >= LINHAS || col < 0 || col >= COLUNAS)
           return false;
        int n = navio.ordinal();
        if ( numNavios[n] >= navio.obtemQuantidade() )
           return false;
        numNavios[n]++;
        if (ori == TipoOrientacao.HORIZONTAL) {
            if (col+tamNav >= COLUNAS)
               return false;
            for (int i=0; i<tamNav; ++i) {
                if (tabuleiro[lin][col+i] != TipoCasa.VAZIO)
                   return false;
            }
            for (int i=0; i<tamNav; ++i)
                tabuleiro[lin][col+i] = TipoCasa.OCUPADO;
        }
        else {
            if (lin+tamNav >= LINHAS)
               return false;
            for (int i=0; i<tamNav; ++i) {
                if (tabuleiro[lin+i][col] != TipoCasa.VAZIO)
                   return false;
            }
            for (int i=0; i<tamNav; ++i)
                tabuleiro[lin+i][col] = TipoCasa.OCUPADO;
        }
        return true;
    }
    
}