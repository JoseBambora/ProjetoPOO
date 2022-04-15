import java.util.*;
import java.time.LocalDate;

public class Comerciante {
    private String nome;
    private int preco;
    private Map<Pessoa,List<Morada>>clientes;
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

    public Comerciante(Comerciante comerciante)
    {
        this.nome = comerciante.getNome();
        this.preco = comerciante.getPreco();
        this.faturasEmitidas = comerciante.getFaturasEmitidas();
        this.clientes = comerciante.clientes;
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

    public Map<Pessoa, List<Morada>> getClientes() {
        Map<Pessoa,List<Morada>> result = new HashMap<>();
        for(Pessoa pessoa : this.clientes.keySet())
        {
            result.put(pessoa.clone(),new ArrayList<>());
            List<Morada> aux = this.clientes.get(pessoa);
            for(Morada morada : aux)
                result.get(pessoa).add(morada.clone());
        }
        return result;
    }

    public Comerciante clone()
    {
        return new Comerciante(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comerciante that = (Comerciante) o;
        return Objects.equals(nome, that.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, preco, clientes, faturasEmitidas);
    }
}
