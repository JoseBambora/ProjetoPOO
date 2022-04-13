/**
 * Lâmpadas Inteligentes, as SmartBulb
 */
public class SmartBulb extends SmartDevices {
    public static final int WARM = 2;
    public static final int NEUTRAL = 1;
    public static final int COLD = 0;
    
    private int tone;
    private int dimensao;
    private int consumoDiario;


    /**
     * Construtor para os objetos de SmartBulb
     */
    public SmartBulb() {
        super();
        this.tone = NEUTRAL;
        this.dimensao =0;
        this.consumoDiario=0;
    }

    public SmartBulb(String id, boolean on) {
        super(id,on);
        this.tone = NEUTRAL;
        this.dimensao =0;
        this.consumoDiario = 0;
    }

    public SmartBulb(String id, boolean on, int dimensao, int consumoDiario){
        // initialise instance variables
        super(id,on);
        this.tone = NEUTRAL;
        this.dimensao = dimensao;
        this.consumoDiario = consumoDiario;
    }

    public SmartBulb (SmartBulb sb) {
        super();
        this.tone = sb.getTone();
        this.dimensao = sb.getDimensao();
        this.consumoDiario = sb.getConsumoDiario();
    }
    public int getDimensao() {

        return this.dimensao;
    }

    public int getConsumoDiario() {

        return this.consumoDiario;
    }

    public int getTone() {

        return this.tone;
    }

    public void setTone(int t) {
        if (t>WARM) this.tone = WARM;
        else if (t<COLD) this.tone = COLD;
        else this.tone = t;
    }

    public void setDimensao(int dimensao) {
        this.dimensao = dimensao;
    }

    public void setConsumoDiario(int consumoDiario) {
        this.consumoDiario = consumoDiario;
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
        return tone == sb.tone && dimensao == sb.getDimensao() && consumoDiario == sb.getConsumoDiario();
    }

    @Override
    public String toString() {
        return "SmartBulb:  Tone = " + tone + ", Dimensão = " + dimensao + ", Consumo Diário = "  + consumoDiario +"\n";
    }


    public SmartBulb clone() {
        return new SmartBulb(this);
    }
    /**
     * Calcula o consumo em função do tipo de luz que está a ser emitida
     */
     int calculaConsumo (int dias) {
        int valor_fixo = 20;
        int fator = this.getConsumoDiario()*dias * this.getTone()*this.getDimensao();
        return (valor_fixo+fator);
    }
}

