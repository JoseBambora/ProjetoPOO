import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class App
{
    private LocalDate dataPrograma;
    private int imposto;
    private LocalDate lastUpdate;
    private Map<String,Comerciante> fornecedores;
    private Map<String,House> casas;
    private Map<String,SmartDevices> devices;
    private Map<Integer,Pessoa> pessoas;
    private String lastCasa;
    private String lastDivisao;

    App(int imposto)
    {
        this.dataPrograma = LocalDate.now();
        this.imposto = imposto;
        this.fornecedores = new HashMap<>();
        this.casas = new HashMap<>();
        this.devices = new HashMap<>();
        this.pessoas = new HashMap<>();
    }
    public void addFornecedor(String nome, Formulas formulas) throws NullPointerException {
        this.fornecedores.put(nome,new Comerciante(nome,formulas));
    }
    public void addSmartBulb(boolean mode,int tone, double tamanho, double consumo) throws ValorNegativoException {
        String id = Integer.toString(this.devices.size() + 1);
        SmartBulb smartBulb = new SmartBulb(id,mode,tone,tamanho,consumo);
        this.devices.put(id,smartBulb);
    }
    public void addSmartSpeaker(boolean mode, int volume, String canal, String marca, double consumo) throws ValorNegativoException, ValorExcedeMaximoException {
        String id = Integer.toString(this.devices.size() + 1);
        SmartSpeaker smartSpeaker = new SmartSpeaker(id,mode,volume,canal,marca,consumo);
        this.devices.put(id,smartSpeaker);
    }
    public void addSmartCamera(boolean mode,double consumo, int x, int y, double tamanho) throws ValorNegativoException {
        String id = Integer.toString(this.devices.size() + 1);
        SmartCamera smartCamera = new SmartCamera(id,mode,consumo,x,y,tamanho);
        this.devices.put(id,smartCamera);
    }
    public void addSmartBulbP(boolean mode,int tone, double tamanho, double consumo) throws ValorNegativoException {
        String id = Integer.toString(this.devices.size() + 1);
        SmartBulb smartBulb = new SmartBulb(id,mode,tone,tamanho,consumo);
        this.devices.put(id,smartBulb);
        this.casas.get(lastCasa).addDevice(lastDivisao,smartBulb);
    }
    public void addSmartSpeakerP(boolean mode, int volume, String canal, String marca, double consumo) throws ValorNegativoException, ValorExcedeMaximoException {
        String id = Integer.toString(this.devices.size() + 1);
        SmartSpeaker smartSpeaker = new SmartSpeaker(id,mode,volume,canal,marca,consumo);
        this.devices.put(id,smartSpeaker);
        this.casas.get(lastCasa).addDevice(lastDivisao,smartSpeaker);
    }
    public void addSmartCameraP(boolean mode,double consumo, int x, int y, double tamanho) throws ValorNegativoException {
        String id = Integer.toString(this.devices.size() + 1);
        SmartCamera smartCamera = new SmartCamera(id,mode,consumo,x,y,tamanho);
        this.devices.put(id,smartCamera);
        this.casas.get(lastCasa).addDevice(lastDivisao,smartCamera);
    }
    public void addPessoa(String nome, int nif) throws ValorNegativoException, NullPointerException {
        this.pessoas.put(nif,new Pessoa(nome,nif));
    }
    public void addDivisao(String divisao) throws DevicesExistException {
        if(this.casas.containsKey(this.lastCasa))
        {
            this.lastDivisao = divisao;
            this.casas.get(this.lastCasa).addDivisao(divisao);
        }
    }
    public void addCasa(Integer pessoa, String fornecedor) throws NullPointerException
    {
        this.lastCasa = Integer.toString(this.casas.size());
        House house = new House(this.pessoas.get(pessoa),this.fornecedores.get(fornecedor), this.lastCasa);
        this.casas.put(house.getLocal(),house);
    }
    public void avancaDias(int dias) throws NullPointerException, ValorNegativoException {
        this.lastUpdate =  this.dataPrograma;
        LocalDate newDate = this.dataPrograma.plusDays(dias);
        this.dataPrograma = newDate;
        for(House casa : this.casas.values())
        {
            double consumo = casa.getConsumo(dias);
            Comerciante comerciante;
            Pessoa proprietario;
            comerciante = casa.getFornecedor();
            proprietario = casa.getProprietario();
            double preco =  0;
            Fatura fatura = new Fatura(proprietario,preco,this.imposto,consumo,newDate,casa.getLocal());
            comerciante.addFatura(fatura,this.getImposto());
        }
    }
    public void changeProprietario(String idHouse,Integer pessoa) throws NullPointerException, PessoaNotExistException, CasaNotExistException {
        if(this.casas.containsKey(idHouse))
            if(this.pessoas.containsKey(pessoa))
                this.casas.get(idHouse).setProprietario(this.pessoas.get(pessoa));
            else
                throw new PessoaNotExistException("Pessoa não existe na aplicação");
        else
            throw new CasaNotExistException("Casa não existe na aplicação");
    }

    public void changeFornecedor(String idHouse, String fornecedor) throws NullPointerException, FornecedorNotExistException, CasaNotExistException {
        if(this.casas.containsKey(idHouse))
            if(this.fornecedores.containsKey(fornecedor))
                this.casas.get(idHouse).setFornecedor(this.fornecedores.get(fornecedor));
            else
                throw new FornecedorNotExistException("Fornecedor não existe na aplicação");
        else
            throw new CasaNotExistException("Casa não existe na aplicação");

    }

    public void setHouseComerciante(String idHouse, String comerciante) throws NullPointerException
    {
        if(this.casas.containsKey(idHouse))
            if(this.fornecedores.containsKey(comerciante))
                this.casas.get(idHouse).setFornecedor(this.fornecedores.get(comerciante));
    }
    public void setHouseDevice(String idHouse, String idDevice) throws ValorNegativoException
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

    public boolean temPessoa (Integer nif) {return this.pessoas.containsKey(nif);}
    public int getImposto() {
        return imposto;
    }

    public LocalDate getDataPrograma() {
        return dataPrograma;
    }
    public String casasToString() throws NullPointerException {
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
            result.append(comerciante.getNome()).append(" ").append(comerciante.getFormula().toString()).append("\n");
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
    public List <Fatura> getFaturasTotal (LocalDate d1, LocalDate d2)
    {
        List<Fatura> allFaturas = new ArrayList<>();
        for(Comerciante comerciante : this.fornecedores.values())
            allFaturas.addAll(comerciante.getFaturasEmitidas(d1,d2));
        Comparator<Fatura> comparator = (f1,f2) -> (int) (f2.getConsumo() - f1.getConsumo());
        allFaturas.sort(comparator);
        return allFaturas;
    }

    public Map<Integer, Double> consumoPessoa (List <Fatura> faturas)
    {
        Map <Integer, Double> newMapConsumidor = new HashMap<>();
        for (Fatura newF : faturas ) {
            if ( ! newMapConsumidor.containsKey(newF.getCliente().getNIF())){
                newMapConsumidor.put (newF.getCliente().getNIF(), 0.0);
            }
            newMapConsumidor.put (newF.getCliente().getNIF(), newMapConsumidor.get(newF.getCliente().getNIF()) + newF.getConsumo() ); // update the key
        }
        return newMapConsumidor;
    }
    public Pessoa queryMaiorConsumidor(){
        List <Fatura> faturas = getFaturasTotal(this.lastUpdate, this.dataPrograma);
        Map <Integer, Double> newMapConsumidor= this.consumoPessoa(faturas);
        Integer maiorNif = -1;
        for (Integer nifC : newMapConsumidor.keySet() ) {
            if (maiorNif == -1 || newMapConsumidor.get(nifC)> newMapConsumidor.get(maiorNif)) maiorNif = nifC;
        }
        return this.pessoas.get(maiorNif).clone();
    }
    public List<Pessoa> queryMaioresConsumidores(LocalDate d1, LocalDate d2)
    {
        List <Fatura> faturas = getFaturasTotal(d1, d2);
        Map <Integer, Double> newMapConsumidor= this.consumoPessoa(faturas);
        List <Pessoa> newPessoa = new ArrayList<>();
        for (Integer nifC : newMapConsumidor.keySet() ) {
            newPessoa.add(this.pessoas.get(nifC).clone());
        }
        Comparator<Pessoa> cPessoa  = (p1,p2) -> (int) (newMapConsumidor.get(p2.getNIF())- newMapConsumidor.get(p1.getNIF()));
        newPessoa.sort(cPessoa);
        return newPessoa;
    }

    public List<Fatura> queryFaturas(String comerciante){
        Map<LocalDate,List<Fatura>> faturas= this.fornecedores.get(comerciante).getFaturasEmitidas();
        List<Fatura> faturasR = new ArrayList<>();
        for (List<Fatura> f : faturas.values()){
            faturasR.addAll(f);
        }
        return faturasR;
    }
    public void changeFormulaFornecedor(Predicate<Comerciante> predicate, Formulas formula) throws NullPointerException {
        for(Comerciante comerciante : this.fornecedores.values())
        {
            if(predicate.test(comerciante))
            {
               comerciante.setFormula(formula);
            }
        }
    }
    public int numeroFaturas(Predicate<Comerciante> predicate)
    {
        int r = 0;
        for(Comerciante comerciante : this.fornecedores.values())
        {
            if(predicate.test(comerciante))
            {
                r += comerciante.numberFaturas();
            }
        }
        return r;
    }

    public double lucro(Predicate<Comerciante> predicate)
    {
        double r = 0;
        for(Comerciante comerciante : this.fornecedores.values())
        {
            if(predicate.test(comerciante))
            {
                r += comerciante.getLucro();
            }
        }
        return r;
    }
    public int respectPredicate(Predicate<Comerciante> predicate)
    {
        int r = 0;
        for(Comerciante comerciante : this.fornecedores.values())
        {
            if(predicate.test(comerciante))
            {
                r++;
            }
        }
        return r;
    }
    public List<Formulas> getFormulasPredicate(Predicate<Comerciante> predicate)
    {
        List<Formulas> formulas = new ArrayList<>();
        for(Comerciante comerciante : this.fornecedores.values())
        {
            if(predicate.test(comerciante))
            {
                formulas.add(comerciante.getFormula());
            }
        }
        return formulas;
    }

    public List<Fatura> queryFaturas(Predicate<Comerciante> predicate)
    {
        List<Fatura> faturas = new ArrayList<>();
        for(Comerciante comerciante : this.fornecedores.values())
        {
            faturas.addAll(comerciante.getFaturasDoComerciante(predicate));
        }
        return faturas;
    }

    public List<SmartDevices> consultaDevice(Predicate<SmartDevices> predicate){
        List<SmartDevices> list = new ArrayList<>();
        for(SmartDevices smartDevice : this.devices.values())
        {
            if(predicate.test(smartDevice))
                list.add(smartDevice.clone());
        }
        return list;
    }

    public void setDevicesOnOff(Predicate<SmartDevices> predicate, boolean on)
    {
        for(SmartDevices smartDevices : this.devices.values())
            smartDevices.setOn(on,predicate);
    }
    public void setConsumoDevices(Predicate<SmartDevices> predicate, double consumo) throws ValorNegativoException {
        for(SmartDevices smartDevices : this.devices.values()){
            if(predicate.test(smartDevices)){
                smartDevices.setConsumo(consumo);
            }
        }
    }
    public void changeToneDevices(Predicate<SmartDevices> predicate, int tone) throws ValorNegativoException {
        for(SmartDevices smartDevices : this.devices.values()){
            if(smartDevices instanceof SmartBulb && predicate.test(smartDevices)){
                ((SmartBulb) smartDevices).setTone(tone);
            }
        }
    }
    public void changeVolumeDevices(Predicate<SmartDevices> predicate, int volume) throws ValorNegativoException, ValorExcedeMaximoException {
        for(SmartDevices smartDevices : this.devices.values()){
            if(smartDevices instanceof SmartSpeaker && predicate.test(smartDevices)){
                ((SmartSpeaker) smartDevices).setVolume(volume);
            }
        }
    }
    public void changeCanalDevices(Predicate<SmartDevices> predicate, String canal) throws NullPointerException {
        for(SmartDevices smartDevices : this.devices.values()){
            if(smartDevices instanceof SmartSpeaker && predicate.test(smartDevices)){
                ((SmartSpeaker) smartDevices).setCanal(canal);
            }
        }
    }
    public List<String> getNomePessoas (){
        List <String> newPessoaList= new ArrayList<>();
        for (Pessoa p : this.pessoas.values()) {
            newPessoaList.add(p.getNome());
        }
        return newPessoaList;
    }

    public List<Integer> getNIFPessoas (){
        List <Integer> newPessoaList= new ArrayList<>();
        for (Pessoa p : this.pessoas.values()) {
            newPessoaList.add(p.getNIF());
        }
        return newPessoaList;
    }

    // SÓ PARA TESTES !!!
    public Map<String, Comerciante> getFornecedores() {
        return fornecedores;
    }
    // SÓ PARA TESTES !!!
    public Map<String, House> getCasas() {
        return casas;
    }
    // SÓ PARA TESTES !!!
    public Map<Integer, Pessoa> getPessoas() {
        return pessoas;
    }
    // SÓ PARA TESTES !!!
    public Map<String, SmartDevices> getDevices() {
        return devices;
    }

    // CRIAR EXCEPTION
    public Pessoa getPessoa(Integer nif) throws PessoaNotExistException {
        if(!this.pessoas.containsKey(nif))
            throw new PessoaNotExistException("Pessoa não existe na aplicação");
        return this.pessoas.get(nif).clone();
    }
    // CRIAR EXCEPTION
    public Comerciante getFornecedor(String nome) throws FornecedorNotExistException {
        if(!this.fornecedores.containsKey(nome))
            throw new FornecedorNotExistException("Fornecedor não existe na aplicação");
        return this.fornecedores.get(nome).clone();
    }

    public Map<String,List<String>> getComerciantesCasas(Predicate<House> predicate)
    {
        Map<String,List<String>> r = new HashMap<>();
        for(House house : this.casas.values())
        {
            if(predicate.test(house))
            {
                String key = house.getFornecedor().getNome();
                if(!r.containsKey(key))
                    r.put(key,new ArrayList<>());
                r.get(key).add(house.getLocal());
            }
        }
        return r;
    }
    public Map<Integer,List<String>> getPropreitarioCasas(Predicate<House> predicate)
    {
        Map<Integer,List<String>> r = new HashMap<>();
        for(House house : this.casas.values())
        {
            if(predicate.test(house))
            {
                Integer key = house.getProprietario().getNIF();
                if(!r.containsKey(key))
                    r.put(key,new ArrayList<>());
                r.get(key).add(house.getLocal());
            }
        }
        return r;
    }
    public Map<String,List<String>> getDevicesCasas(Predicate<House> predicate,Predicate<Map.Entry<String,List<String>>> predicate2, Predicate<SmartDevices> predicate1)
    {
        Map<String,List<String>> r = new HashMap<>();
        for(House house : this.casas.values())
        {
            if(predicate.test(house))
            {
                String key = house.getLocal();
                r.put(key,house.respectPredicate(predicate2,predicate1));

            }
        }
        return r;
    }
    public Map<String,List<String>> getDivisoesCasas(Predicate<House> predicate)
    {
        Map<String,List<String>> r = new HashMap<>();
        for(House house : this.casas.values())
        {
            if(predicate.test(house))
            {
                String key = house.getLocal();
                r.put(key,new ArrayList<>());
                r.get(key).addAll(house.getDivisoes().keySet());
            }
        }
        return r;
    }
    public List<String> getLocalidadeCasas(Predicate<House> predicate)
    {
        List<String> r = new ArrayList<>();
        for(House house : this.casas.values())
        {
            if(predicate.test(house))
            {
                r.add(house.getLocal());
            }
        }
        return r;
    }
    public String getIdLastDeviceAdd()
    {
        return this.devices.get(Integer.toString(this.devices.size()-1)).getId();
    }
    public void associaHouse(Predicate<House> predicate,Map<String, String> map)
    {
        for(House house : this.casas.values())
        {
            if(predicate.test(house))
            {
                for(Map.Entry<String,String> entry : map.entrySet())
                {
                    house.addDevice(entry.getValue(),this.devices.get(entry.getKey()));
                }
            }
        }
    }
    public void addDivisoes(Predicate<House> predicate,List<String> divisoes) throws DevicesExistException {
        for(House house : this.casas.values())
        {
            if(predicate.test(house))
            {
                for(String div : divisoes)
                    house.addDivisao(div);
            }
        }
    }
    public void movedivisao(String local, String divisao, String device) throws CasaNotExistException, DeviceNotExistException, DivisaoNotExistException {
        if(this.casas.containsKey(local))
            this.casas.get(local).moveDivisao(divisao,device);
        else
            throw new CasaNotExistException("Casa não existe na aplicação");
    }
    public void changeStateDevice(Predicate<House> predicate, Predicate<SmartDevices> predicateSD, Predicate<Map.Entry<String,List<String>>> divPredicate, boolean mode)
    {
        for(House house : this.casas.values())
        {
            if(predicate.test(house))
            {
                house.setDivisaoSDOnOff(divPredicate,predicateSD,mode);
            }
        }
    }
    public void changeFornecedor(Predicate<House> predicate, String nome) throws NullPointerException, FornecedorNotExistException {
        if(this.fornecedores.containsKey(nome))
        {
            for(House house : this.casas.values())
            {
                if(predicate.test(house))
                {
                    house.setFornecedor(this.fornecedores.get(nome));
                }
            }
        }
        else
            throw new FornecedorNotExistException("Fornecedor não existe na aplicação");
    }
    public void changeProprietario(Predicate<House> predicate, Integer nif) throws NullPointerException, PessoaNotExistException {
        if(this.pessoas.containsKey(nif))
        {
            for(House house : this.casas.values())
            {
                if(predicate.test(house))
                {
                    house.setProprietario(this.pessoas.get(nif));
                }
            }
        }
        else
            throw new PessoaNotExistException("Pessoa não existe na aplicação");
    }
}
