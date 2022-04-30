/**
 * Lâmpadas Inteligentes, as SmartBulb
 */
public class SmartBulb extends SmartDevices {
    public static final int WARM = 2;
    public static final int NEUTRAL = 1;
    public static final int COLD = 0;
    
    private int tone;
    private double dimensao;


    /**
     * Construtor para os objetos de SmartBulb
     */
    public SmartBulb() {
        super();
        this.tone = NEUTRAL;
        this.dimensao =0;
    }

    public SmartBulb(String id, boolean on, double consumo) {
        super(id,on,consumo);
        this.tone = NEUTRAL;
        this.dimensao =0;
    }

    public SmartBulb(String id, boolean on, int tone, double dimensao, double consumo){
        // initialise instance variables
        super(id,on,consumo);
        this.setTone(tone);
        this.setDimensao(dimensao); // if the dimensao is >=0 it's okay to use it otherwise, it's initialized as 0
    }

    public SmartBulb (SmartBulb sb) {
        super(sb.getId(), sb.isOn(),sb.getConsumo());
        this.tone = sb.getTone();
        this.dimensao = sb.getDimensao();
    }
    public double getDimensao() {
        return this.dimensao;
    }


    public int getTone() {
        return this.tone;
    }

    public void setTone(int t) {
        if (t>WARM) this.tone = WARM;
        else this.tone = Math.max(t, COLD);
    }

    public void setDimensao(double dimensao) {
       if (dimensao >=0) this.dimensao = dimensao;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if ((o == null) || (this.getClass() != o.getClass())) {
            return false;
        }
        SmartBulb sb = (SmartBulb) o;
        return tone == sb.tone && dimensao == sb.getDimensao() && this.getConsumo() == sb.getConsumo();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SmartBulb:\n")
                .append("Tone: ").append(this.tone).append("\n")
                .append("Dimensao: ").append(this.dimensao).append("\n");
        return sb.toString();
    }


    public SmartBulb clone() {
        return new SmartBulb(this);
    }
    /**
     * Calcula o consumo em função do tipo de luz que está a ser emitida
     */
    public double calculaConsumo (int dias) {
        double fator = (this.getTone() + 1) * this.getDimensao();
        return this.isOn() ? dias*(this.getConsumo()+fator) : 0;
    }
}

