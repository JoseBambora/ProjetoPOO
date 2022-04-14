public class SmartCamera extends SmartDevices
{

    private int consumoDiario;
    private int resolucaoX;
    private int resolucaoY;
    private int tamanho;

    /**
     *
     */
    public SmartCamera(){
        this.consumoDiario = 0;
        this.resolucaoX = 0;
        this.resolucaoY = 0;
        this.tamanho = 0;
    }


    /**
     *
     */
    public SmartCamera(int consumoDiario, int resolucaoX, int resolucaoY, int tamanho){
        this.consumoDiario = consumoDiario;
        this.resolucaoX = resolucaoX;
        this.resolucaoY = resolucaoY;
        this.tamanho = tamanho;
    }


    /**
     *
     */
    public SmartCamera(String id, boolean on, int consumoDiario, int resolucaoX, int resolucaoY, int tamanho){
        super(id,on);
        this.setConsumoDiario(consumoDiario);
        this.setResolucaoX(resolucaoX);
        this.setResolucaoY(resolucaoY);
        this.setTamanho(tamanho);
    }


    /**
     *
     */
    public SmartCamera(SmartCamera smartCamera)
    {
        super(smartCamera.getId(),smartCamera.isOn());
        this.consumoDiario = smartCamera.getConsumoDiario();
        this.resolucaoX = smartCamera.getResolucaoX();
        this.resolucaoY = smartCamera.getResolucaoY();
        this.tamanho = smartCamera.getTamanho();
    }


    /**
     *
     */
    public int getConsumoDiario() {
        return this.consumoDiario;
    }


    /**
     *
     */
    public int getResolucaoX() {
        return this.resolucaoX;
    }

    /**
     *
     */
    public int getResolucaoY() {
        return this.resolucaoY;
    }

    /**
     *
     */
    public int getTamanho() {
        return tamanho;
    }

    /**
     *
     */
    public void setConsumoDiario(int consumoDiario) {
        this.consumoDiario = consumoDiario;
    }


    /**
     *
     */
    public void setResolucaoX(int resolucaoX) {
        this.resolucaoX = resolucaoX;
    }


    /**
     *
     */
    public void setResolucaoY(int resolucaoY) {
        this.resolucaoY = resolucaoY;
    }

    /**
     *
     */
    public void setTamanho(int tamanho) {
        this.tamanho = tamanho;
    }

    /**
     *
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmartCamera that = (SmartCamera) o;
        return consumoDiario == that.consumoDiario && this.resolucaoX == that.resolucaoX &&
                this.resolucaoY == that.resolucaoY && this.tamanho == that.tamanho;
    }


    /**
     *
     */
    public SmartCamera clone() { return new SmartCamera(this); }


    /**
     *
     */
    @Override
    public String toString() {
        return "SmartCamera: Consumo Diario = " + consumoDiario +", Resolução = " + resolucaoX+"x" + resolucaoY + ", Tamanho: "+tamanho +"\n";
    }

    public int calculaConsumo (int dias) {
        return this.isOn() ? (this.getConsumoDiario() * dias) + (resolucaoX*resolucaoY*tamanho) : 0;
    }
}
