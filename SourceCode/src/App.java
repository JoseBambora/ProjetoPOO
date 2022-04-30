import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class App
{
    private LocalDate dataPrograma;
    private int imposto;
    private final Map<String,Comerciante> fornecedores;
    private final Map<String,House> casas;
    private final Map<String,SmartDevices> devices;
    private final Map<String,Pessoa> pessoas;
    App(int imposto)
    {
        this.dataPrograma = LocalDate.now();
        this.imposto = imposto;
        this.fornecedores = new HashMap<>();
        this.casas = new HashMap<>();
        this.devices = new HashMap<>();
        this.pessoas = new HashMap<>();
    }
    public void addFornecedor(Comerciante comerciante)
    {
        this.fornecedores.put(comerciante.getNome(),comerciante.clone());
    }
    public void addDevice(SmartDevices smartDevices)
    {
        this.devices.put(smartDevices.getId(), smartDevices.clone());
    }
    public void addPessoa(Pessoa pessoa)
    {
        this.pessoas.put(pessoa.getNome(),pessoa.clone());
    }
    public void addCasa(House house)
    {
        this.casas.put(house.getLocal(),house.clone());
    }
    public void avancaDias(int dias)
    {
        LocalDate oldDate = this.dataPrograma;
        LocalDate newDate = this.dataPrograma.plusDays(dias);
        this.dataPrograma = newDate;
        for(House casa : this.casas.values())
        {
            double consumo = casa.getConsumo(dias);
            Comerciante comerciante = casa.getFornecedor();
            double preco = consumo * comerciante.getPreco() * (1 + (double) this.imposto / 100);
            Fatura fatura = new Fatura(casa.getProprietario(),preco,this.imposto,consumo,newDate,casa.getLocal());
            comerciante.addFatura(fatura);
        }
    }
    public void mudaPreco( Map<String,Double> atualizacao)
    {
        for(Map.Entry<String,Double> valor : atualizacao.entrySet())
        {
            if(this.fornecedores.containsKey(valor.getKey()))
            {
                this.fornecedores.get(valor.getKey()).setPreco(valor.getValue());
            }
        }
    }
    public void setOnOff(String idHouse, Map<String,Boolean> estadoDispositivos)
    {
        if(this.casas.containsKey(idHouse))
        {
            House house = this.casas.get(idHouse);
            for(String id : estadoDispositivos.keySet())
            {
                if(house.hasDevice(id))
                {
                    house.setDeviceOn(id);
                }
            }
        }
    }

    public void changeProprietario(String idHouse, String pessoa)
    {
        if(this.casas.containsKey(idHouse))
            if(this.pessoas.containsKey(pessoa))
                this.casas.get(idHouse).setProprietario(this.pessoas.get(pessoa));
    }

    public void setHouseComerciante(String idHouse, String comerciante)
    {
        if(this.casas.containsKey(idHouse))
            if(this.fornecedores.containsKey(comerciante))
                this.casas.get(idHouse).setFornecedor(this.fornecedores.get(comerciante));
    }
    public void setHouseDevice(String idHouse, String idDevice)
    {
        if(this.casas.containsKey(idHouse))
            if(this.devices.containsKey(idDevice))
                this.casas.get(idHouse).addDevice(this.devices.get(idDevice));
    }

    public void setImposto(int imposto) {
        this.imposto = imposto;
    }

    public void setDataPrograma(LocalDate dataPrograma) {
        this.dataPrograma = dataPrograma;
    }
    public int numberDevices()
    {
        return this.devices.size();
    }
    public int numberCasas()
    {
        return this.casas.size();
    }
    public int numberPessoas()
    {
        return this.pessoas.size();
    }
    public int numberFornecedores()
    {
        return this.fornecedores.size();
    }

    public int getImposto() {
        return imposto;
    }

    public LocalDate getDataPrograma() {
        return dataPrograma;
    }
    public String casasToString()
    {
        StringBuilder result = new StringBuilder();
        result.append("Formato: Localidade | Nome do proprietário\n");
        for(House casa : this.casas.values())
        {
            result.append(casa.getLocal()).append(" ").append(casa.getProprietario().getNome()).append("\n");
        }
        return result.toString();
    }
    public String pessoasToString()
    {
        StringBuilder result = new StringBuilder();
        result.append("Formato: Localidade | Nome do proprietário\n");
        for(Pessoa pessoa : this.pessoas.values())
        {
            result.append(pessoa.getNome()).append(" ").append(pessoa.getNIF()).append("\n");
        }
        return result.toString();
    }
    public String devicesToString()
    {
        StringBuilder result = new StringBuilder();
        result.append("Formato: Id Device | Ligado ou Desligado\n");
        for(SmartDevices smartDevices : this.devices.values())
        {
            String on = "";
            if(smartDevices.isOn())
                on = "Ligado";
            else
                on = "Desligado";
            result.append(smartDevices.getId()).append(" ").append(on).append("\n");
        }
        return result.toString();
    }
    public String fornecedoresToString()
    {
        StringBuilder result = new StringBuilder();
        result.append("Formato: Nome Fornecedor | Preco do fornecedor\n");
        for(Comerciante comerciante : this.fornecedores.values())
        {
            result.append(comerciante.getNome()).append(" ").append(comerciante.getPreco()).append("\n");
        }
        return result.toString();
    }
    public Comerciante queryMaiorFornecedor()
    {
        double lucro = -1;
        Comerciante result = new Comerciante();
        for(Comerciante comerciante : this.fornecedores.values())
        {
            double lucroaux = comerciante.getLucro();
            if(lucroaux > lucro)
            {
                lucro = lucroaux;
                result = comerciante;
            }
        }
        return result.clone();
    }
    public List<Pessoa> queryMaioresConsumidores(LocalDate d1, LocalDate d2)
    {
        List<Fatura> allFaturas = new ArrayList<>();
        for(Comerciante comerciante : this.fornecedores.values())
            allFaturas.addAll(comerciante.getFaturasEmitidas(d1,d2));
        Comparator<Fatura> comparator = (f1,f2) -> (int) (f2.getConsumo() - f1.getConsumo());
        allFaturas.sort(comparator);
        return allFaturas.stream().map(Fatura::getCliente).collect(Collectors.toList());
    }
}
