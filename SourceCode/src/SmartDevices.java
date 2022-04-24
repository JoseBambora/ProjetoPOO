import java.util.Objects;

public abstract class SmartDevices
{
    private String id;
    private boolean on;
    SmartDevices()
    {
        this.id = "";
        this.on = false;
    }
    public SmartDevices(String id)
    {
        this.id = id;
        this.on = false;
    }
    public SmartDevices(String id, boolean on)
    {
        this.id = id;
        this.on = on;
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
    public void setId(String id) {
        this.id = id;
    }
    public void setOn(boolean on) {
        this.on = on;
    }
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
                .append("On: ").append(this.on).append("\n");
        return sb.toString();
    }
    public abstract SmartDevices clone();
}
