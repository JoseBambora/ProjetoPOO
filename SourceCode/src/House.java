import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.zip.CheckedOutputStream;

public class House {
    private Map<String,SmartDevices> devices;
    private Map<String,List<String>> divisoes;
    private Pessoa proprietario;
    private String local;
    private Comerciante fornecedor;
    public House(Comerciante comerciante){
        this.proprietario = new Pessoa();
        this.local = "";
        this.fornecedor = comerciante;
        this.devices = new HashMap<>();
        this.divisoes = new HashMap<>();
    }

    public House(Pessoa proprietario, Comerciante comerciante, String local){
        this.proprietario = proprietario;
        this.local = local;
        this.fornecedor = comerciante;
        this.devices = new HashMap<>();
        this.divisoes = new HashMap<>();
    }

    public House(Pessoa proprietario, Comerciante comerciante, String local, Map<String,SmartDevices> smartDevicesMap) throws ValorNegativoException
    {
        this.proprietario = proprietario;
        this.local = local;
        this.fornecedor = comerciante;
        this.devices = new HashMap<>();
        this.divisoes = new HashMap<>();
        this.setDevices(smartDevicesMap);
    }

    public House(Pessoa proprietario, Comerciante comerciante, String local, Map<String,SmartDevices> smartDevicesMap, Map<String,List<String>> listMap) throws ValorNegativoException
    {
        this.proprietario = proprietario;
        this.local = local;
        this.fornecedor = comerciante;
        this.devices = new HashMap<>();
        this.divisoes = new HashMap<>();
        this.setDevices(smartDevicesMap);
        this.setDivisoes(listMap);
    }
    public House(House house) throws NullPointerNotExistException , ValorNegativoException{
        this.proprietario = house.getProprietario();
        this.local = house.getLocal();
        this.fornecedor = house.getFornecedor();
        this.devices = house.getDevices();
        this.divisoes = house.getDivisoes();
    }

    public Pessoa getProprietario() throws NullPointerNotExistException {
        if(this.proprietario == null)
            throw new NullPointerNotExistException("Pessoa não existe na casa " + this.getLocal());
        return this.proprietario;
    }

    public String getLocal() {
        return this.local;
    }

    public Map<String, List<String>> getDivisoes() {
        Map<String, List<String>> result = new HashMap<>();
        for(String divisao : this.divisoes.keySet())
        {
            List<String> devicesDiv = this.divisoes.get(divisao);
            int capacidade = devicesDiv.size();
            result.put(divisao,new ArrayList<>(capacidade));
            for(String dev : devicesDiv)
                result.get(divisao).add(dev);
        }
        return result;
    }

    public Map<String, SmartDevices> getDevices()  throws ValorNegativoException{
        Map<String, SmartDevices> result = new HashMap<>();
        for(String s: this.devices.keySet())
            result.put(s,this.devices.get(s).cloneDevice());
        return result;
    }

    public Comerciante getFornecedor() throws NullPointerNotExistException
    {
        if(this.fornecedor == null)
            throw new NullPointerNotExistException("Fornecedor não existe na casa " + this.getLocal());
        return this.fornecedor;
    }

    public void setDevices(Map<String, SmartDevices> devices) throws ValorNegativoException{
        this.devices.clear();
        for(String s: devices.keySet())
            this.devices.put(s,devices.get(s).cloneDevice());
    }

    public void setFornecedor(Comerciante fornecedor) throws NullPointerNotExistException{
        if(fornecedor == null)
            throw new NullPointerNotExistException("Set Comerciante nulo na casa " + this.getLocal());
        this.fornecedor = fornecedor;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public void setProprietario(Pessoa proprietario) throws NullPointerNotExistException{
        if(proprietario == null)
            throw new NullPointerNotExistException("Set Proprietário nulo na casa " + this.getLocal());
        this.proprietario = proprietario;
    }

    public void setDivisoes(Map<String, List<String>> divisoes) {
        this.divisoes.clear();
        for(String divisao : divisoes.keySet())
        {
            List<String> devicesDiv = divisoes.get(divisao);
            int capacidade = devicesDiv.size();
            this.divisoes.put(divisao,new ArrayList<>(capacidade));
            for(String dev : devicesDiv)
                this.divisoes.get(divisao).add(dev);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        House house = (House) o;
        return this.proprietario.equals(house.proprietario) && this.local.equals(house.local);
    }

    public void setAllOn()
    {
        for(SmartDevices smartDevices : this.devices.values())
            smartDevices.turnOn();
    }

    public void setAllOff()
    {
        for(SmartDevices smartDevices : this.devices.values())
            smartDevices.turnOff();
    }

    public void setDivisaoOn(String divisao)
    {
        if(this.divisoes.containsKey(divisao))
        {
            List<String> devices = this.divisoes.get(divisao);
            for(String device : devices)
            {
                this.devices.get(device).turnOn();
            }
        }
    }

    public void setDivisaoOff(String divisao)
    {
        if(this.divisoes.containsKey(divisao))
        {
            List<String> devices = this.divisoes.get(divisao);
            for(String device : devices)
            {
                this.devices.get(device).turnOff();
            }
        }
    }

    public void setDeviceOn(String device)
    {
        if(this.devices.containsKey(device))
        {
            this.devices.get(device).turnOn();
        }
    }

    public void setDeviceOff(String device)
    {
        if(this.devices.containsKey(device))
        {
            this.devices.get(device).turnOff();
        }
    }

    public void addDevice(SmartDevices smartDevices) throws ValorNegativoException
    {
        try
        {
            this.devices.put(smartDevices.getId(),smartDevices.cloneDevice());
        }
        catch (ValorNegativoException e)
        {
            throw new ValorNegativoException("Na casa " +this.getLocal() + " " +e.getMessage());
        }
    }

    public void addDevice(String divisao, SmartDevices smartDevices) throws ValorNegativoException
    {
        if(!this.divisoes.containsKey(divisao))
            this.divisoes.put(divisao,new ArrayList<>());
        this.divisoes.get(divisao).add(smartDevices.getId());
        this.addDevice(smartDevices);
    }

    public void removeDevice(String device)
    {
        if(this.devices.containsKey(device))
        {
            this.devices.remove(device);
            for(List<String> list : this.divisoes.values())
                list.removeIf(s -> s.equals(device));
        }
    }

    public void removeDivisao(String divisao)
    {
        if(this.divisoes.containsKey(divisao))
        {
            List<String> list = this.divisoes.remove(divisao);
            for(String s : list)
                this.devices.remove(s);
        }
    }

    public House cloneHouse () throws NullPointerNotExistException, ValorNegativoException
    {
        House r;
        r = new House(this);
        return r;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("House:\n")
                .append("Devices: ").append(this.devices).append("\n")
                .append("Divisoes").append(this.divisoes).append("\n")
                .append("Proprietario: ").append(this.proprietario).append("\n")
                .append("Local").append(this.local).append("\n")
                .append("Fornecedor").append(this.fornecedor).append("\n");
        return sb.toString();
    }

    public double getConsumo(int dias)
    {
        double result = 0;
        for(SmartDevices smartDevices : this.devices.values())
            result += smartDevices.calculaConsumo(dias);
        return result;
    }

    public boolean hasDevice(String id)
    {
        return this.devices.containsKey(id);
    }

    public void addDivisao(String id)
    {
        this.divisoes.put(id,new ArrayList<>());
    }
}
