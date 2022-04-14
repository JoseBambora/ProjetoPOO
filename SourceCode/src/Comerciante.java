import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.time.LocalDate;

public class Comerciante {
    private String nome;
    private int preco;
    private Map<Pessoa,List<House>>clientes;
    private Map<LocalDate,List<Fatura>>faturasEmitidas

public Comerciante(){
        this.nome = "";
        this.preco = 0;
        this.clientes = new HashMap<>();
        this.faturasEmitidas = new HashMap<>();
}

public Comerciante(String nome, int preco){
    this.nome = nome;
    this.preco = preco;
    this.clientes = new HashMap<>();
    this.faturasEmitidas = new HashMap<>();
}

    public int getPreco() {
        return preco;
    }

    public String getNome() {
        return nome;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Map<LocalDate, List<Fatura>> getFaturasEmitidas() {
        return faturasEmitidas;
    }
}
