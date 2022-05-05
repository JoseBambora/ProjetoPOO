import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.function.Predicate;

public class House {
    private Map<String,SmartDevices> devices;
    private Map<String,List<String>> divisoes;
    private Pessoa proprietario;
    private String local;
    private Comerciante fornecedor;
    public House(Comerciante comerciante) throws NullPointerException {
        this.proprietario = new Pessoa();
        this.local = "";
        this.setFornecedor(comerciante);
        this.devices = new HashMap<>();
        this.divisoes = new HashMap<>();
    }

    public House(Pessoa proprietario, Comerciante comerciante, String local) throws NullPointerException {
        this.setProprietario(proprietario);
        this.setFornecedor(comerciante);
        this.local = local;
        this.devices = new HashMap<>();
        this.divisoes = new HashMap<>();
    }

    public House(Pessoa proprietario, Comerciante comerciante, String local, Map<String,SmartDevices> smartDevicesMap) throws NullPointerException {
        this.setProprietario(proprietario);
        this.setFornecedor(comerciante);
        this.local = local;
        this.devices = new HashMap<>();
        this.divisoes = new HashMap<>();
        this.setDevices(smartDevicesMap);
    }

    public House(Pessoa proprietario, Comerciante comerciante, String local, Map<String,SmartDevices> smartDevicesMap, Map<String,List<String>> listMap) throws NullPointerException {
        this.setProprietario(proprietario);
        this.setFornecedor(comerciante);
        this.local = local;
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

    public Pessoa getProprietario(){
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

    public Map<String, SmartDevices> getDevices(){
        Map<String, SmartDevices> result = new HashMap<>();
        for(String s: this.devices.keySet())
            result.put(s,this.devices.get(s).clone());
        return result;
    }

    public Comerciante getFornecedor()
    {
       return this.fornecedor;
    }

    public void setDevices(Map<String, SmartDevices> devices){
        this.devices.clear();
        for(String s: devices.keySet())
            this.devices.put(s,devices.get(s).clone());
    }

    public void setFornecedor(Comerciante fornecedor) throws NullPointerException{
        if(fornecedor == null)
            throw new NullPointerException("Set Comerciante nulo na casa " + this.getLocal());
        this.fornecedor = fornecedor;
    }

    public void setLocal(String local) throws NullPointerException{
        if (local == null)
            throw new NullPointerException("Local nulo na casa " +this.toString());
        this.local = local;
    }

    public void setProprietario(Pessoa proprietario) throws NullPointerException{
        if(proprietario == null)
            throw new NullPointerException("Set Proprietário nulo na casa " + this.getLocal());
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

    public void setDivisaoOnOff(String divisao, boolean on)
    {
        if(this.divisoes.containsKey(divisao))
        {
            List<String> devices = this.divisoes.get(divisao);
            for(String device : devices)
                this.devices.get(device).setOn(on);
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
    public void setDevicesOnOff(Predicate<SmartDevices> predicate, boolean on)
    {
        for(SmartDevices smartDevices : this.devices.values())
            smartDevices.setOn(on,predicate);
    }
    public void setDivisaoOn(Predicate<String> predicate, boolean on)
    {
        for(String divisoes : this.divisoes.keySet())
        {
            if(predicate.test(divisoes))
                this.setDivisaoOnOff(divisoes,on);
        }
    }
    public void setDivisaoSDOnOff(Predicate<String> predicate, Predicate<SmartDevices> predicate2, boolean on)
    {
        for(String divisoes : this.divisoes.keySet())
        {
            if(predicate.test(divisoes)) {
                List<String> sd = this.divisoes.get(divisoes);
                for(String sd1 : sd)
                    this.devices.get(sd1).setOn(on,predicate2);
            }
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

    public double getConsumo(int dias) throws ValorNegativoException {
        double result = 0;
        try {
            for (SmartDevices smartDevices : this.devices.values())
                result += smartDevices.calculaConsumo(dias);
        }
        catch (ValorNegativoException e) {
            throw new ValorNegativoException("Casa " + this.getLocal() + " " + e.getMessage());
        }
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
