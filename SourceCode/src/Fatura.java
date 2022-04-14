import java.time.LocalDate;
import java.util.Objects;

public class Fatura {
    private Pessoa cliente;
    private int preco;
    private int consumo;
    private LocalDate dataEmissao;
    private Morada local;

    public Fatura(){
        this.cliente = new Pessoa();
        this.preco = 0;
        this.consumo = 0;
        this.dataEmissao = LocalDate.now();
        this.local = new Morada();
    }

    public Fatura(Pessoa cliente, int preco, int consumo, LocalDate date, Morada local){
        this.cliente = cliente.clone();
        this.preco = preco;
        this.consumo = consumo;
        this.dataEmissao = date;
        this.local = local.clone();
    }

    public Fatura(Fatura fatura){
        this.cliente = fatura.getCliente();
        this.preco = fatura.getPreco();
        this.consumo = fatura.getConsumo();
        this.dataEmissao = fatura.getDataEmissao();
        this.local = fatura.getLocal();
    }

    public Pessoa getCliente() {
        return this.cliente.clone();
    }

    public int getPreco() {
        return this.preco;
    }

    public int getConsumo() {
        return this.consumo;
    }

    public Morada getLocal() {
        return this.local.clone();
    }

    public LocalDate getDataEmissao() {
        return this.dataEmissao;
    }

    public void setCliente(Pessoa cliente) {
        this.cliente = cliente.clone();
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    public void setConsumo(int consumo) {
        this.consumo = consumo;
    }

    public void setLocal(Morada local) {
        this.local = local.clone();
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    @Override
    public String toString() {
        return "Fatura, Cliente: " + cliente +
                ", Preco: " + preco +
                ", Consumo:" + consumo +
                ", DataEmissao:" + dataEmissao +
                ", Local:" + local;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fatura fatura = (Fatura) o;
        return preco == fatura.preco && consumo == fatura.consumo && this.cliente.equals(fatura.getCliente()) &&
                this.dataEmissao.equals(fatura.dataEmissao) && this.local.equals(fatura.local);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cliente, preco, consumo, dataEmissao, local);
    }

    public Fatura clone() { return new Fatura(this); }
}

