public class SmartSpeaker extends SmartDevices
{
    private static final int MAX = 20;
    private int volume;
    private int canal;
    private String marca;
    private int consumoDiario;

    /**
     *
     */
    public SmartSpeaker()
    {
        super();
        this.volume = 0;
        this.canal = 0;
        this.marca = "";
        this.consumoDiario = 0;
    }

    /**
     * @param id
     * @param on
     */
    public SmartSpeaker(String id, boolean on)
    {
        super(id, on);
        this.volume = 0;
        this.canal = 0;
        this.marca = "";
        this.consumoDiario = 0;
    }

    /**
     * @param id
     * @param on
     * @param volume
     * @param canal
     * @param marca
     * @param consumoDiario
     */
    public SmartSpeaker(String id, boolean on, int volume,int canal,String marca,int consumoDiario)
    {
        super(id, on);
        this.setVolume(volume);
        this.canal = canal;
        this.marca = marca;
        this.consumoDiario = consumoDiario;
    }

    /**
     * @param smartSpeaker
     */
    public SmartSpeaker(SmartSpeaker smartSpeaker)
    {
        super();
        this.volume = smartSpeaker.getVolume();
        this.canal = smartSpeaker.getCanal();
        this.marca = smartSpeaker.getMarca();
        this.consumoDiario = smartSpeaker.getConsumoDiario();
    }

    /**
     * @return
     */
    public int getCanal() { return this.canal; }

    /**
     * @return
     */
    public int getConsumoDiario() { return this.consumoDiario; }

    /**
     * @return
     */
    public String getMarca() { return this.marca; }

    /**
     * @return
     */
    public int getVolume() { return this.volume; }

    /**
     * @param canal
     */
    public void setCanal(int canal) { this.canal = canal; }

    /**
     * @param marca
     */
    public void setMarca(String marca) { this.marca = marca;}

    /**
     * @param consumoDiario
     */
    public void setConsumoDiario(int consumoDiario) { this.consumoDiario = consumoDiario; }

    /**
     * @param volume
     */
    public void setVolume(int volume)
    {
        this.volume = volume;
        if     (this.volume > MAX) this.volume = MAX;
        else if(this.volume < 0)   this.volume = 0;
    }

    /**
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmartSpeaker that = (SmartSpeaker) o;
        return this.marca.equals(that.marca) && canal == that.canal;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return "SmartSpeaker, V: " + volume + ", C: " + canal + ", M: '" + marca + '\'' + ", CD: " + consumoDiario;
    }

    /**
     * @param aumenta
     */
    public void volumeUp(int aumenta)
    {
        this.volume += aumenta;
        if(this.volume > MAX)
            this.volume = MAX;
    }

    /**
     *
     */
    public void volumeUp()
    {
        this.volume++;
        if(this.volume > MAX)
            this.volume = MAX;
    }

    /**
     * @param diminui
     */
    public void volumeDown(int diminui)
    {
        this.volume -= diminui;
        if(this.volume < 0)
            this.volume = 0;
    }

    /**
     *
     */
    public void volumeDown()
    {
        this.volume--;
        if(this.volume < 0)
            this.volume = 0;
    }

    /**
     * @return
     */
    public SmartSpeaker clone() { return new SmartSpeaker(this); }
}
