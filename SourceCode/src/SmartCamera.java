import java.io.Serializable;

public class SmartCamera extends SmartDevices implements Serializable
{
    private int resolucaoX;
    private int resolucaoY;
    private double tamanho;

    /**
     *
     */
    public SmartCamera(){
        super();
        this.resolucaoX = 0;
        this.resolucaoY = 0;
        this.tamanho = 0;
    }


    /**
     *
     */
    public SmartCamera (String id, boolean on, double consumo) throws ValorNegativoException{
        super(id,on,consumo);
        this.resolucaoX = 0;
        this.resolucaoY = 0;
        this.tamanho = 0;
    }


    /**
     *
     */
    public SmartCamera(String id, boolean on, double consumoDiario, int resolucaoX, int resolucaoY, double tamanho)  throws ValorNegativoException
    {
        super(id,on,consumoDiario);
        this.setResolucaoX(resolucaoX);
        this.setResolucaoY(resolucaoY);
        this.setTamanho(tamanho);
    }


    /**
     *
     */
    public SmartCamera(SmartCamera smartCamera)
    {
        super(smartCamera);
        this.resolucaoX = smartCamera.getResolucaoX();
        this.resolucaoY = smartCamera.getResolucaoY();
        this.tamanho = smartCamera.getTamanho();
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
    public void setResolucaoX(int resolucaoX) throws ValorNegativoException {
        if(resolucaoX < 0){
            throw new ValorNegativoException("Resolução Horizontal Negativa no dispositivo "+this.getId());
        }
        this.resolucaoX = resolucaoX;
    }


    /**
     *
     */
    public void setResolucaoY(int resolucaoY) throws ValorNegativoException{
        if(resolucaoY < 0){
            throw new ValorNegativoException("Resolução Vertical Negativa no dispositivo "+this.getId());
        }
        this.resolucaoY = resolucaoY;
    }

    /**
     *
     */
    public void setTamanho(double tamanho) throws ValorNegativoException{
        if(tamanho < 0){
            throw new ValorNegativoException("Tamanho Negativo no dispositivo "+this.getId());
        }
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
        return this.getConsumo() == that.getConsumo() && this.resolucaoX == that.resolucaoX &&
                this.resolucaoY == that.resolucaoY && this.tamanho == that.tamanho;
    }


    /**
     *
     */
    public SmartCamera clone(){ return new SmartCamera(this); }


    /**
     *
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SmartCamera:\n")
                .append("Resolucao = ").append(resolucaoX).append("x").append(resolucaoY).append("\n")
                .append("Tamanho = ").append(tamanho).append("\n");
        return sb.toString();
    }

    public double calculaConsumo (int dias) throws ValorNegativoException{
        if(dias < 0){
            throw new ValorNegativoException("Dias Negativo "+ dias);
        }
        return this.isOn() ? (this.getConsumo() * dias) + (resolucaoX*resolucaoY*tamanho) / 1000000 : 0;
    }
}
