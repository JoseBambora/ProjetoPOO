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
        this.cliente = cliente;
        this.preco = preco;
        this.consumo = consumo;
        this.dataEmissao = date;
        this.local = local;
    }

    public Fatura(Fatura fatura){
        this.cliente = fatura.getCliente();
        this.preco = fatura.getPreco();
        this.consumo = fatura.getConsumo();
        this.dataEmissao = fatura.getDataEmissao();
        this.local = fatura.getLocal();
    }

    public Pessoa getCliente() {
        return cliente;
    }

    public int getPreco() {
        return preco;
    }

    public int getConsumo() {
        return consumo;
    }

    public Morada getLocal() {
        return local;
    }

    public LocalDate getDataEmissao() {
        return dataEmissao;
    }

    public void setCliente(Pessoa cliente) {
        this.cliente = cliente;
    }

    public void setPreco(int preco) {
        this.preco = preco;
    }

    public void setConsumo(int consumo) {
        this.consumo = consumo;
    }

    public void setLocal(Morada local) {
        this.local = local;
    }

    public void setDataEmissao(LocalDate dataEmissao) {
        this.dataEmissao = dataEmissao;
    }

    @Override
    public String toString() {
        return "Fatura:" +
                "cliente=" + cliente +
                ", preco=" + preco +
                ", consumo=" + consumo +
                ", dataEmissao=" + dataEmissao +
                ", local=" + local +"\n";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Fatura fatura = (Fatura) o;
        return preco == fatura.preco && consumo == fatura.consumo && this.cliente == fatura.cliente &&
                this.dataEmissao.equals(fatura.dataEmissao) && this.local.equals(fatura.local);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cliente, preco, consumo, dataEmissao, local);
    }

    public Fatura clone() { return new Fatura(this); }
}

