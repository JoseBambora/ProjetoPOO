import java.util.Objects;

public abstract class SmartDevices
{
    private String id;
    private boolean on;

    private double consumo;
    SmartDevices()
    {
        this.id = "";
        this.on = false;
        this.consumo = 0;
    }
    public SmartDevices(String id)
    {
        this.id = id;
        this.on = false;
        this.consumo = 0;
    }
    public SmartDevices(String id, boolean on, double consumo)
    {
        this.id = id;
        this.on = on;
        this.consumo = consumo;
    }
    public abstract double calculaConsumo(int dias);
    public void turnOn()
    {
        this.setOn(true);
    }
    public void turnOff()
    {
        this.setOn(false);
    }
    public boolean isOn() {
        return on;
    }
    public String getId() {
        return id;
    }

    public double getConsumo() {return consumo;}

    public void setId(String id) {
        this.id = id;
    }
    public void setOn(boolean on) {
        this.on = on;
    }
    public void setConsumo(double consumo) {this.consumo = consumo;}
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SmartDevices that = (SmartDevices) o;
        return this.id.equals(that.getId());
    }
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("SmartDevices:\n")
                .append("ID: ").append(this.id).append("\n")
                .append("On: ").append(this.on).append("\n")
                .append("Consumo: ").append(this.consumo).append("\n");
        return sb.toString();
    }
    public abstract SmartDevices clone();
}
