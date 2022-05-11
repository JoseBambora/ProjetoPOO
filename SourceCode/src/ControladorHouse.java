import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

public class ControladorHouse
{
    private ViewHouse viewHouse;
    private ControladorDevices controladorDevices;
    private App app;
    ControladorHouse(App app)
    {
        this.app = app;
        this.controladorDevices = new ControladorDevices(app);
        this.viewHouse = new ViewHouse();
    }
    public void parseCasa(String input){
        try {
            String[] campos = input.split(",");
            String nome = campos[0];
            int nif = Integer.parseInt(campos[1]);
            app.addPessoa(nome,nif);
            String fornecedor = campos[2];
            this.addCasaApp(nif,fornecedor);
            viewHouse.printMessage("Casa adicionada com sucesso");
        }
        catch (NullPointerException |  ValorNegativoException e)
        {
            System.out.println(e.getMessage());
        }
    }
    public void addDivisao(String divisao)
    {
        try {
            app.addDivisao(divisao);
            viewHouse.printMessage("Divisao adicionada com sucesso");
        }
        catch (DevicesExistException e) {
            viewHouse.printMessage(e.getMessage());
        }
    }
    public void addCasaApp() throws NullPointerException {
        int number = (int) viewHouse.getNumber("nif do dono");
        String str = viewHouse.getStr("nome do comerciante");
        if(number != -1 && str != null)
            this.app.addCasa(number,str);
    }
    public void addCasaApp(int nif, String nome){
        try {
            this.app.addCasa(nif,nome);
        }
        catch (NullPointerException e)
        {
            viewHouse.printMessage(e.getMessage());
        }
    }
    private Predicate<House> housePredicate() {
        int n = viewHouse.predicate();
        Predicate<House> r = null;
        switch (n)
        {
            case 1:
                r = Objects::nonNull;
                break;
            case 2:
                String local = viewHouse.getStr("local");
                if(!local.equals(""))
                    r = h -> h.getLocal().equals(local);
                break;
            case 3:
                String div = viewHouse.getStr("divisao");
                if(!div.equals(""))
                    r = h -> h.hasDivisao(div);
                break;
            case 4:
                int number = (int) viewHouse.getNumber("numero de divisoes");
                if(number != -1)
                    r = h -> h.numberDivisoes() > number;
                break;
            case 5:
                String id = viewHouse.getStr("id do device");
                if(!id.equals(""))
                    r = h -> h.hasDevice(id);
                break;
            case 6:
                number = (int) viewHouse.getNumber("numero de devices");
                if(number != -1)
                    r = h -> h.numberDevices() > number;
                break;
            case 7:
                try {
                    number = (int) viewHouse.getNumber("nif da pessoa");
                    if(number != -1)
                    {
                        Pessoa p = app.getPessoa(number);
                        r = h -> h.getProprietario().equals(p);
                    }
                }
                catch (PessoaNotExistException e)
                {
                    viewHouse.printMessage(e.getMessage());
                }
                break;
            case 8:
                try {
                    String nome = viewHouse.getStr("nome do fornecedor");
                    if (!nome.equals("")) {
                        Comerciante comerciante = app.getFornecedor(nome);
                        r = h -> h.getFornecedor().equals(comerciante);
                    }
                }
                catch (FornecedorNotExistException e)
                {
                    viewHouse.printMessage(e.getMessage());
                }
                break;
            default:
                break;
            }
        return r;
    }
    private Predicate<Map.Entry<String,List<String>>> divisaoPredicate() {
        int n = viewHouse.predicateDiv();
        Predicate<Map.Entry<String,List<String>>> r = null;
        switch (n)
        {
            case 1:
                r = Objects::nonNull;
                break;
            case 2:
                int num = (int) viewHouse.getNumber("para quantos dispositivos a mais");
                r = h -> h.getValue().size() > num;
                break;
            case 3:
                String div = viewHouse.getStr(" para o nome da divisao");
                r = h -> h.getKey().equals(div);
                break;
            default:
                break;
        }
        return r;
    }
    public void consultaDados(){
        String print = "";
        Predicate<House> ph = this.housePredicate();
        if(ph == null)
            return;
        int n = viewHouse.decideConsulta();
        switch (n)
        {
            case 1:
                viewHouse.printMaps1(app.getComerciantesCasas(ph));
                break;
            case 2:
                viewHouse.printMaps2(app.getPropreitarioCasas(ph));
                break;
            case 3:
                Predicate<Map.Entry<String,List<String>>> predicate2 = this.divisaoPredicate();
                Predicate<SmartDevices> smartDevicesPredicate = controladorDevices.devicesPredicate();
                if(predicate2 != null&& smartDevicesPredicate != null)
                    viewHouse.printMaps1(app.getDevicesCasas(ph,predicate2,smartDevicesPredicate));
                break;
            case 4:
                viewHouse.printMaps1(app.getDivisoesCasas(ph));
                break;
            case 5:
                print = app.getLocalidadeCasas(ph).toString() ;
                break;
            default:
                break;
        }
        viewHouse.printMessage(print);
    }
    private void acrescentarDevices(Predicate<House> ph) throws ValorNegativoException, ValorExcedeMaximoException {
        int number = (int) viewHouse.getNumber("quantos devices");
        Map<String,String> aux = new HashMap<>();
        for(int i = 0; i < number; i++)
        {
            controladorDevices.addDevice();
            String divisao = viewHouse.getStr("divisao");
            aux.put(app.getIdLastDeviceAdd(),divisao);
        }
        if(number != -1)app.associaHouse(ph,aux);
    }
    private void acrescentarDivisoes(Predicate<House> ph)
    {
        try {
            List<String> divisoes = viewHouse.getDivisoesAdd();
            app.addDivisoes(ph,divisoes);
        }
        catch (DevicesExistException e)
        {
            viewHouse.printMessage(e.getMessage());
        }

    }
    private void moveDivisoes()
    {
        try {
            int number = (int) viewHouse.getNumber("mover quantos devices?");
            String local = viewHouse.getStr("local da casa");
            if(!local.equals(""))
            {
                for (int i = 0; i < number; i++) {
                    String id = viewHouse.getStr("id do device");
                    String divisao = viewHouse.getStr("para qual a divisao");
                    app.movedivisao(local, divisao, id);
                }
            }
        }
        catch (CasaNotExistException | DeviceNotExistException | DivisaoNotExistException e)
        {
            viewHouse.printMessage(e.getMessage());
        }
    }
    public void alteraDados() throws ValorNegativoException, ValorExcedeMaximoException, NullPointerException {
        Predicate<House> ph;
        int n = viewHouse.decideAlterar();
        switch (n)
        {
            case 1:
                ph = this.housePredicate();
                if(ph != null)
                    this.acrescentarDevices(ph);
                break;
            case 2:
                ph = this.housePredicate();
                if(ph != null)
                    this.acrescentarDivisoes(ph);
                break;
            case 3:
                this.moveDivisoes();
                break;
            case 4:
                ph = this.housePredicate();
                int number = viewHouse.ligarDesligar();
                Predicate<Map.Entry<String,List<String>>> predicate2 = this.divisaoPredicate();
                Predicate<SmartDevices> smartDevicesPredicate = controladorDevices.devicesPredicate();
                if(ph == null || predicate2 == null || smartDevicesPredicate == null)
                    break;
                if(number == 1)
                    app.changeStateDevice(ph,smartDevicesPredicate,predicate2,true);
                else if(number == 2)
                    app.changeStateDevice(ph,smartDevicesPredicate,predicate2,false);
                break;
            case 5:
                try {
                    ph = this.housePredicate();
                    if(ph != null)
                        app.changeFornecedor(ph, viewHouse.getStr("nome do fornecedor"));
                }
                catch (FornecedorNotExistException e) {
                    viewHouse.printMessage(e.getMessage());
                }
                break;
            case 6:
                try {
                    ph = this.housePredicate();
                    if(ph != null)
                        app.changeProprietario(ph,(int) viewHouse.getNumber("nif do novo proprietario"));
                }
                catch (PessoaNotExistException e)
                {
                    viewHouse.printMessage(e.getMessage());
                }
                break;
            default:
                break;
        }
    }
    public void whatOperation()
    {
        try {
            int n = viewHouse.consultarChange();
            if(n == 1)
                this.consultaDados();
            else if(n == 2)
                this.alteraDados();
        }
        catch (ValorNegativoException | ValorExcedeMaximoException | NullPointerException e)
        {
            viewHouse.printMessage(e.getMessage());
        }
    }
}
