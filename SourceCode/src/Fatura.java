import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Fatura implements Serializable {
    private Pessoa cliente;
    private double preco;
    private double consumo;
    private int imposto;
    private LocalDate dataEmissao;
    private String local;

    public Fatura(){
        this.cliente = new Pessoa();
        this.preco = 0;
        this.consumo = 0;
        this.dataEmissao = LocalDate.now();
        this.imposto = 0;
        this.local = "";
    }

    public Fatura(Pessoa cliente, double preco, int imposto, double consumo, LocalDate date, String local) throws NullPointerException, ValorNegativoException {
        this.setCliente(cliente);
        this.setPreco(preco);
        this.setConsumo(consumo);
        this.setDataEmissao(date);
        this.setLocal(local);
        this.setImposto(imposto);
    }

    public Fatura(Fatura fatura){
        this.cliente = fatura.getCliente();
        this.preco = fatura.getPreco();
        this.consumo = fatura.getConsumo();
        this.dataEmissao = fatura.getDataEmissao();
        this.local = fatura.getLocal();
        this.imposto = fatura.getImposto();
    }

    public int getImposto() {
        return this.imposto;
    }

    public Pessoa getCliente() {
        return this.cliente;
    }

    public double getPreco() {
        return this.preco;
    }

    public double getConsumo() {
        return this.consumo;
    }

    public String getLocal() {
        return this.local;
    }

    public LocalDate getDataEmissao() {
        return this.dataEmissao;
    }

    public void setImposto(int imposto) throws ValorNegativoException {
        if(imposto < 0){
            throw new ValorNegativoException("Tentativa de imposto negativo na Fatura "+this.toString());
        }
        this.imposto = imposto;
    }

    public void setCliente(Pessoa cliente) throws NullPointerException{
        if(cliente == null){
            throw new NullPointerException("Cliente nulo na Fatura "+this.toString());
        }
        this.cliente = cliente;
    }

    public void setPreco(double preco) throws ValorNegativoException {
        if(preco < 0){
            throw new ValorNegativoException("Tentativa Preco negativo na Fatura "+this.toString());
        }
        this.preco = preco;
    }

    public void setConsumo(double consumo) throws ValorNegativoException {
        if(consumo < 0){
            throw new ValorNegativoException("Tentativa de Consumo negativo na Fatura" +this.toString());
        }
        this.consumo = consumo;
    }

    public void setLocal(String local) throws NullPointerException {
        if(local == null){
            throw new NullPointerException("Tentativa de Local nulo na fatura "+this.toString());
        }
        this.local = local;
    }

    public void setDataEmissao(LocalDate dataEmissao) throws NullPointerException {
        if(dataEmissao == null){
            throw new NullPointerException("Tentativa de Data de Emissão nula na Fatura "+this.toString());
        }
        this.dataEmissao = dataEmissao;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Fatura:\n").append("Cliente: ").append(this.cliente).append("\n")
                .append("Preco: ").append(this.preco).append("\n")
                .append("Consumo: ").append(this.consumo).append("\n")
                .append("Data de Emissão: ").append(this.dataEmissao).append("\n")
                .append("Local: ").append(this.local).append("\n");
        return sb.toString();
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

