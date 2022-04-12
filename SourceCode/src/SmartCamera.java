public class SmartCamera extends SmartDevices
{

    private int consumoDiario;
    private String resolucao;


    /**
     *
     */
    public SmartCamera(){
        this.consumoDiario = 0;
        this.resolucao = "";
    }


    /**
     *
     */
    public SmartCamera(int consumoDiario, String resolucao){
        this.consumoDiario = consumoDiario;
        this.resolucao = resolucao;
    }


    /**
     *
     */
    public SmartCamera(String id, boolean on, int consumoDiario, String resolucao){
        super(id,on);
        this.setConsumoDiario(consumoDiario);
        this.setResolucao(resolucao);
    }


    /**
     *
     */
    public SmartCamera(SmartCamera smartCamera)
    {
        super();
        this.consumoDiario = smartCamera.getConsumoDiario();
        this.resolucao = smartCamera.getResolucao();
    }


    /**
     *
     */
    public int getConsumoDiario() {
        return consumoDiario;
    }


    /**
     *
     */
    public String getResolucao() {
        return resolucao;
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
    public void setResolucao(String resolucao) {
        this.resolucao = resolucao;
    }


    /**
     *
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SmartCamera that = (SmartCamera) o;
        return consumoDiario == that.consumoDiario && this.resolucao.equals(that.resolucao);
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
        return "SmartCamera: " +
                "consumoDiario=" + consumoDiario +
                ", resolucao='" + resolucao + '\'' +
                '}';
    }
}
