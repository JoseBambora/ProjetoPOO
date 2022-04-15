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

    public SmartBulb(String id, boolean on, int tone, int dimensao, int consumoDiario){
        // initialise instance variables
        super(id,on);
        this.setTone(tone);
        this.setDimensao(dimensao); // if the dimensao is >=0 it's okay to use it otherwise, it's initialized as 0
        this.setConsumoDiario(consumoDiario); // if the consumoDiario is >=0 it's okay to use it otherwise, it's initialized as 0
    }

    public SmartBulb (SmartBulb sb) {
        super(sb.getId(), sb.isOn());
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
       if (dimensao >=0) this.dimensao = dimensao;
    }

    public void setConsumoDiario(int consumoDiario) {
        if (consumoDiario>=0) this.consumoDiario = consumoDiario;
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
    public int calculaConsumo (int dias) {
        int fator = (this.getTone() + 1) * this.getDimensao();
        return this.isOn() ? dias*(this.getConsumoDiario()+fator) : 0;
    }
}

