public class SmartCamera extends SmartDevices
{

    private int consumoDiario;
    private String resolucao;
    private int tamanho;

    /**
     *
     */
    public SmartCamera(){
        this.consumoDiario = 0;
        this.resolucao = "";
        this.tamanho = 0;
    }


    /**
     *
     */
    public SmartCamera(int consumoDiario, String resolucao, int tamanho){
        this.consumoDiario = consumoDiario;
        this.resolucao = resolucao;
        this.tamanho = tamanho;
    }


    /**
     *
     */
    public SmartCamera(String id, boolean on, int consumoDiario, String resolucao, int tamanho){
        super(id,on);
        this.setConsumoDiario(consumoDiario);
        this.setResolucao(resolucao);
        this.setTamanho(tamanho);
    }


    /**
     *
     */
    public SmartCamera(SmartCamera smartCamera)
    {
        super();
        this.consumoDiario = smartCamera.getConsumoDiario();
        this.resolucao = smartCamera.getResolucao();
        this.tamanho = smartCamera.getTamanho();
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
    public void setResolucao(String resolucao) {
        this.resolucao = resolucao;
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
        if (!super.equals(o)) return false;
        SmartCamera that = (SmartCamera) o;
        return consumoDiario == that.consumoDiario && this.resolucao.equals(that.resolucao) && this.tamanho == that.tamanho;
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
        return "SmartCamera: Consumo Diario = " + consumoDiario +", Resolução = " + resolucao +", Tamanho: "+tamanho +"\n";
    }

    public int calculaConsumo (int dias) {
        // a definir
        return this.isOn() ? 1 : 0;
    }
}
