
/**
 * Enumeration class TipoNavio - write a description of the enum class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public enum TipoNavio {
    PORTA_AVIOES(5), NAVIO_TANQUE(4), CONTRATORPEDEIRO(3), SUBMARINO(2);
    
    private int tamanho;
    
    TipoNavio(int t) {
        tamanho = t;
    }
    
    public int obtemTamanho() {
        return tamanho;
    }
}
