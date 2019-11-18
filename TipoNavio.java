
/**
 * Enumeration class TipoNavio - write a description of the enum class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public enum TipoNavio {
    PORTA_AVIOES(5,1), CRUZADOR(4,2), DESTROIER(3,3), SUBMARINO(2,4);
    
    private int tamanho;
    private int quantidade;
    
    TipoNavio(int t, int q) {
        tamanho = t;
        quantidade = q;
    }
    
    public int obtemTamanho() {
        return tamanho;
    }

    public int obtemQuantidade() {
        return quantidade;
    }
}
