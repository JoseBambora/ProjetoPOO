import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.time.LocalDate;

public class Comerciante {
    private String nome;
    private int preco;
    private Map<Pessoa,List<House>>clientes;
    private Map<LocalDate,List<Fatura>>faturasEmitidas;
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
        return this.preco;
    }

    public String getNome() {
        return this.nome;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Map<LocalDate, List<Fatura>> getFaturasEmitidas()
    {
        Map<LocalDate,List<Fatura>> result = new HashMap<>();
        for(LocalDate key : this.faturasEmitidas.keySet())
        {
            result.put(key,new ArrayList<>());
            List<Fatura> aux = this.faturasEmitidas.get(key);
            for(Fatura fatura : aux)
                result.get(key).add(fatura.clone());
        }
        return result;
    }
}
