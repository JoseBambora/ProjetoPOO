public class Morada {

    private String localidade;
    private int porta;

    public Morada(){
        this.localidade = "";
        this.porta = 0;
    }

    public Morada(String localidade, int porta){
        this.localidade = localidade;
        this.porta = porta;
    }

    public Morada(Morada local){
        this.localidade = local.getLocalidade();
        this.porta = local.getPorta();
    }

    public String getLocalidade() {
        return this.localidade;
    }

    public int getPorta() {
        return this.porta;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public void setPorta(int porta) {
        this.porta = porta;
    }

    public Morada clone() { return new Morada(this); }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Morada morada = (Morada) o;
        return this.porta == morada.getPorta() && this.localidade.equals(morada.getLocalidade());
    }

    @Override
    public String toString() {
        return "Morada, Localidade: '" + this.localidade + "', Porta:" + this.porta;
    }
}
