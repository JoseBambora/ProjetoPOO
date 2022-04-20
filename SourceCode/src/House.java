import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.function.Predicate;

public class House {
    private Map<String,SmartDevices> devices;
    private Map<String,List<String>> divisoes;
    private Pessoa proprietario;
    private Morada local;
    private Comerciante fornecedor;

    public House(Comerciante comerciante){
        this.proprietario = new Pessoa();
        this.local = new Morada();
        this.fornecedor = comerciante.clone();
        this.devices = new HashMap<>();
        this.divisoes = new HashMap<>();
    }

    public House(Pessoa proprietario, Comerciante comerciante, Morada local){
        this.proprietario = proprietario.clone();
        this.local = local.clone();
        this.fornecedor = comerciante.clone();
        this.devices = new HashMap<>();
        this.divisoes = new HashMap<>();
    }

    public House(Pessoa proprietario, Comerciante comerciante, Morada local, Map<String,SmartDevices> smartDevicesMap){
        this.proprietario = proprietario.clone();
        this.local = local.clone();
        this.fornecedor = comerciante.clone();
        this.devices = new HashMap<>();
        this.divisoes = new HashMap<>();
        this.setDevices(smartDevicesMap);
    }

    public House(Pessoa proprietario, Comerciante comerciante, Morada local, Map<String,SmartDevices> smartDevicesMap, Map<String,List<String>> listMap){
        this.proprietario = proprietario.clone();
        this.local = local.clone();
        this.fornecedor = comerciante.clone();
        this.devices = new HashMap<>();
        this.divisoes = new HashMap<>();
        this.setDevices(smartDevicesMap);
        this.setDivisoes(listMap);
    }
    public House(House house){
        this.proprietario = house.getProprietario();
        this.local = house.getLocal();
        this.fornecedor = house.getFornecedor();
        this.devices = house.getDevices();
        this.divisoes = house.getDivisoes();
    }

    public Pessoa getProprietario() {
        return this.proprietario.clone();
    }

    public Morada getLocal() {
        return this.local.clone();
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

    public Map<String, SmartDevices> getDevices() {
        Map<String, SmartDevices> result = new HashMap<>();
        for(String s: this.devices.keySet())
            result.put(s,this.devices.get(s).clone());
        return result;
    }

    public Comerciante getFornecedor() {
        return this.fornecedor.clone();
    }

    public void setDevices(Map<String, SmartDevices> devices) {
        this.devices.clear();
        for(String s: devices.keySet())
            this.devices.put(s,devices.get(s).clone());
    }

    public void setFornecedor(Comerciante fornecedor) {
        this.fornecedor = fornecedor.clone();
    }

    public void setLocal(Morada local) {
        this.local = local.clone();
    }

    public void setProprietario(Pessoa proprietario) {
        this.proprietario = proprietario.clone();
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

    public void addDevice(SmartDevices smartDevices)
    {
        this.devices.put(smartDevices.getId(),smartDevices.clone());
    }

    public void addDevice(String divisao, SmartDevices smartDevices)
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

    public House clone()
    {
        return new House(this);
    }

    public double getConsumo(int dias)
    {
        double result = 0;
        for(SmartDevices smartDevices : this.devices.values())
            result += smartDevices.calculaConsumo(dias);
        return result;
    }
}