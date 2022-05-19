import java.io.Serializable;

public class SmartSpeaker extends SmartDevices implements Serializable
{
    private static final int MAX = 100;
    private int volume;
    private String canal;
    private String marca;
    public SmartSpeaker()
    {
        super();
        this.volume = 0;
        this.canal = "";
        this.marca = "";
    }
    public SmartSpeaker(String id, boolean on, double consumo)  throws ValorNegativoException
    {
        super(id, on,consumo);
        this.volume = 0;
        this.canal = "";
        this.marca = "";
    }
    public SmartSpeaker(String id, boolean on, int volume,String canal,String marca,double consumo) throws ValorNegativoException, ValorExcedeMaximoException {
        super(id, on, consumo);
        this.setVolume(volume);
        this.canal = canal;
        this.marca = marca;
    }
    public SmartSpeaker(SmartSpeaker smartSpeaker)
    {
        super(smartSpeaker);
        this.volume = smartSpeaker.getVolume();
        this.canal = smartSpeaker.getCanal();
        this.marca = smartSpeaker.getMarca();
    }
    public String getCanal() { return this.canal; }
    public String getMarca() { return this.marca; }
    public int getVolume() { return this.volume; }
    public void setCanal(String canal) throws NullPointerException {
        if(canal == null){
            throw new NullPointerException("Canal Inexistente - String Nula no dispositivo "+this.getId());
        }
        this.canal = canal;
    }
    public void setMarca(String marca) throws NullPointerException {
        if (marca == null) {
            throw new NullPointerException("Marca Inexistente - String Nula no dispositivo "+this.getId());
        }
        this.marca = marca;
    }
    public void setVolume(int volume) throws ValorExcedeMaximoException,ValorNegativoException{

        if(volume > MAX) {
            throw new ValorExcedeMaximoException("Volume excede o máximo no dispositvo "+getId());
        }
        if(volume < 0) {
            throw new ValorNegativoException("Volume negativo no dispositivo "+this.getId());
        }
        this.volume = volume;
    }
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmartSpeaker that = (SmartSpeaker) o;
        return this.marca.equals(that.marca) && canal == that.canal;
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SmartSpeaker:\n")
                .append("Volume: ").append(this.volume).append("\n")
                .append("Canal: ").append(this.canal).append("\n")
                .append("Marca: ").append(this.marca).append("\n");
        return sb.toString();
    }
    public void volumeUp(int aumenta)
    {
        this.volume += aumenta;
        if(this.volume > MAX)
            this.volume = MAX;
    }
    public void volumeUp()
    {
        this.volume++;
        if(this.volume > MAX)
            this.volume = MAX;
    }
    public void volumeDown(int diminui)
    {
        this.volume -= diminui;
        if(this.volume < 0)
            this.volume = 0;
    }
    public void volumeDown()
    {
        this.volume--;
        if(this.volume < 0)
            this.volume = 0;
    }
    public SmartSpeaker clone(){ return new SmartSpeaker(this); }
    public double calculaConsumo(int dias) throws ValorNegativoException
    {
        if(dias < 0){
            throw new ValorNegativoException("Dias Negativo "+ dias);
        }
        return this.isOn() ? (this.getConsumo() + this.getVolume()) * dias : 0;
    }
}
