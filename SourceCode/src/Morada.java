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
        return localidade;
    }

    public int getPorta() {
        return porta;
    }

    public void setLocalidade(String localidade) {
        this.localidade = localidade;
    }

    public void setPorta(int porta) {
        this.porta = porta;
    }

    public Morada clone() { return new Morada(this); }
}
