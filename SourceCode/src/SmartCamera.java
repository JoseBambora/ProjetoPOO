public class SmartCamera extends SmartDevices
{
    private double consumoDiario;
    private int resolucaoX;
    private int resolucaoY;
    private double tamanho;

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
    public SmartCamera(double consumoDiario, int resolucaoX, int resolucaoY, double tamanho){
        this.consumoDiario = consumoDiario;
        this.resolucaoX = resolucaoX;
        this.resolucaoY = resolucaoY;
        this.tamanho = tamanho;
    }


    /**
     *
     */
    public SmartCamera(String id, boolean on, double consumoDiario, int resolucaoX, int resolucaoY, double tamanho){
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
    public double getConsumoDiario() {
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
    public double getTamanho() {
        return tamanho;
    }

    /**
     *
     */
    public void setConsumoDiario(double consumoDiario) {
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
    public void setTamanho(double tamanho) {
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

    public double calculaConsumo (int dias) {
        return this.isOn() ? (this.getConsumoDiario() * dias) + (resolucaoX*resolucaoY*tamanho) / 1000000 : 0;
    }
}
