public class Tabuleiro {

    private final static int LINHAS = 10;
    private final static int COLUNAS = 10;
    private TipoCasa[][] tabuleiro;
    
    public Tabuleiro() {
        tabuleiro = new TipoCasa[LINHAS][COLUNAS];
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
        return false;
    }
    
}