
/**
 * Enumeration class TipoNavio - write a description of the enum class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */
public enum TipoNavio {
    PORTA_AVIOES(5,1,"Porta-aviões"), CRUZADOR(4,2,"Cruzador"), DESTROIER(3,3,"Destróier"), SUBMARINO(2,4,"Submarino");
    
    private int tamanho;
    private int quantidade;
    private String nome;
    
    TipoNavio(int t, int q, String n) {
        tamanho = t;
        quantidade = q;
        nome = n;
    }
    
    public int obtemTamanho() {
        return tamanho;
    }

    public int obtemQuantidade() {
        return quantidade;
    }

    public String obtemNome() {
        return nome;
    }
}
