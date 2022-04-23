import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDate;
import java.util.Objects;

public class Comerciante {
    private String nome;
    private double preco;
    private Map<LocalDate,List<Fatura>>faturasEmitidas;
    public Comerciante(){
            this.nome = "";
            this.preco = 0;
            this.faturasEmitidas = new HashMap<>();
    }

    public Comerciante(String nome, double preco){
        this.nome = nome;
        this.preco = preco;
        this.faturasEmitidas = new HashMap<>();
    }

    public Comerciante(Comerciante comerciante)
    {
        this.nome = comerciante.getNome();
        this.preco = comerciante.getPreco();
        this.faturasEmitidas = comerciante.getFaturasEmitidas();
    }

    public double getPreco() {
        return this.preco;
    }

    public String getNome() {
        return this.nome;
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

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setFaturasEmitidas(Map<LocalDate, List<Fatura>> faturasEmitidas) {
        this.faturasEmitidas = new HashMap<>();
        for(LocalDate key : faturasEmitidas.keySet())
        {
            this.faturasEmitidas.put(key,new ArrayList<>());
            List<Fatura> aux = faturasEmitidas.get(key);
            for(Fatura fatura : aux)
                this.faturasEmitidas.get(key).add(fatura.clone());
        }
    }
    public int numFaturasEmitidas()
    {
        int num = 0;
        for(List <Fatura> faturas : this.faturasEmitidas.values())
        {
            num += faturas.size();
        }
        return num;
    }
    public List<Fatura> getFaturasEmitidas (LocalDate d1, LocalDate d2)
    {
        if(d1.isAfter(d2))
        {
            LocalDate aux = d1;
            d1 = d2;
            d2 = aux;
        }
        List<Fatura> faturas = new ArrayList<>();
        for(LocalDate date : this.faturasEmitidas.keySet())
        {
            if((date.isAfter(d1) || date.equals(d1)) && (date.isBefore(d2) || date.equals(d2)))
            {
                List<Fatura> aux = this.faturasEmitidas.get(date);
                for(Fatura fatura : aux)
                    faturas.add(fatura.clone());
            }
        }
        return faturas;
    }

    public Fatura getMaiorFatura(LocalDate d1, LocalDate d2)
    {
        Fatura result = new Fatura();
        for(LocalDate date : this.faturasEmitidas.keySet())
        {
            if((date.isAfter(d1) || date.equals(d1)) && (date.isBefore(d2) || date.equals(d2)))
            {
                List<Fatura> aux = this.faturasEmitidas.get(date);
                for(Fatura fatura : aux)
                {
                    if(fatura.getPreco() > result.getPreco())
                        result = fatura;
                }
            }
        }
        return result.clone();
    }

    public void addFatura(Fatura fatura)
    {
        if(!this.faturasEmitidas.containsKey(fatura.getDataEmissao()))
            this.faturasEmitidas.put(fatura.getDataEmissao(),new ArrayList<>());
        fatura.setPreco(fatura.getConsumo() * this.getPreco());
        this.faturasEmitidas.get(fatura.getDataEmissao()).add(fatura.clone());
    }

    public double getLucro()
    {
        double lucro = 0;
        for(List<Fatura> faturas : this.faturasEmitidas.values())
        {
            for(Fatura fatura : faturas)
                lucro += fatura.getPreco();
        }
        return lucro;
    }

    public String getFaturasEmitidasToString()
    {
        StringBuilder result = new StringBuilder();
        for(List<Fatura> faturas : this.faturasEmitidas.values())
        {
            for(Fatura fatura : faturas)
                result.append(fatura.toString());
        }
        return result.toString();
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
}
