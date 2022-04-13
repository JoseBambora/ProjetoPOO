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
    public abstract int calculaConsumo(int dias);
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
        return Objects.equals(id, that.id);
    }
    @Override
    public String toString() {
        return "SmartDevices, id: '" + id + '\'' + ", on: " + on;
    }
}
