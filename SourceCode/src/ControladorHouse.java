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
        catch (DivisaoExistException e) {
            viewHouse.printMessage(e.getMessage());
        }
    }
    public void addCasaApp(){
        Integer number = viewHouse.getNumber2("nif do dono");
        String str = viewHouse.getStr("nome do comerciante");
        if(number != null && str != null)
        {
            try {
                this.app.addCasa(number,str);
            }
            catch (NullPointerException | PessoaNotExistException | FornecedorNotExistException e)
            {
                viewHouse.printMessage(e.getMessage());
            }
        }
    }
    public void addCasaApp(int nif, String nome){
        try {
            this.app.addCasa(nif,nome);
        }
        catch (NullPointerException | PessoaNotExistException | FornecedorNotExistException e)
        {
            viewHouse.printMessage(e.getMessage());
        }
    }
    private Predicate<House> housePredicate() {
        Integer n = viewHouse.predicate();
        Predicate<House> r = null;
        if(n == null)
            return null;
        switch (n)
        {
            case 1:
                r = Objects::nonNull;
                break;
            case 2:
                String local = viewHouse.getStr("local");
                if(local != null)
                    r = h -> h.getLocal().equals(local);
                break;
            case 3:
                String div = viewHouse.getStr("divisao");
                if(div != null)
                    r = h -> h.hasDivisao(div);
                break;
            case 4:
                Integer number = viewHouse.getNumber2("numero de divisoes");
                if(number != null)
                    r = h -> h.numberDivisoes() > number;
                break;
            case 5:
                String id = viewHouse.getStr("id do device");
                if(id != null)
                    r = h -> h.hasDevice(id);
                break;
            case 6:
                number = viewHouse.getNumber2("numero de devices");
                if(number != null)
                    r = h -> h.numberDevices() > number;
                break;
            case 7:
                try {
                    number = viewHouse.getNumber2("nif da pessoa");
                    if(number != null)
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
                    if (nome != null) {
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
        Integer n = viewHouse.predicateDiv();
        Predicate<Map.Entry<String,List<String>>> r = null;
        if(n == null)
            return null;
        switch (n)
        {
            case 1:
                r = Objects::nonNull;
                break;
            case 2:
                Integer num = viewHouse.getNumber2("para quantos dispositivos a mais");
                if(num != null)
                    r = h -> h.getValue().size() > num;
                break;
            case 3:
                String div = viewHouse.getStr(" para o nome da divisao");
                if(div != null)
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
        Integer n = viewHouse.decideConsulta();
        if(n == null)
            return;
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
            case 6:
                print = Integer.toString(app.respectPredicateCasa(ph));
                break;
            default:
                break;
        }
        viewHouse.printMessage(print);
    }
    private void acrescentarDevices(Predicate<House> ph) throws ValorNegativoException, ValorExcedeMaximoException {
        Integer number = viewHouse.getNumber2("quantos devices");
        Map<String,String> aux = new HashMap<>();
        if(number == null)
            return;
        for(int i = 0; i < number; i++)
        {
            controladorDevices.addDevice();
            String divisao = viewHouse.getStr("divisao");
            if(divisao != null)
            {
                app.replicateNTimesDevice(ph,divisao);
            }
            else
                i--;
        }
    }
    private void acrescentarDivisoes(Predicate<House> ph)
    {
        try {
            List<String> divisoes = viewHouse.getDivisoesAdd();
            app.addDivisoes(ph,divisoes);
        }
        catch (DivisaoExistException e)
        {
            viewHouse.printMessage(e.getMessage());
        }

    }
    private void moveDivisoes()
    {
        try {
            Integer number = viewHouse.getNumber2("mover quantos devices?");
            String local = viewHouse.getStr("local da casa");
            if(number != null && local != null)
            {
                for (int i = 0; i < number; i++) {
                    String id = viewHouse.getStr("id do device");
                    String divisao = viewHouse.getStr("para qual a divisao");
                    if(id != null && divisao != null)
                        app.movedivisao(local, divisao, id);
                    else
                        i--;
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
        Integer n = viewHouse.decideAlterar();
        if(n == null)
            return;
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
                Integer number = viewHouse.ligarDesligar();
                Predicate<Map.Entry<String,List<String>>> predicate2 = this.divisaoPredicate();
                Predicate<SmartDevices> smartDevicesPredicate = controladorDevices.devicesPredicate();
                if(number == null || ph == null || predicate2 == null || smartDevicesPredicate == null)
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
                    {
                        Integer num = viewHouse.getNumber2("nif do novo proprietario");
                        if(num != null)
                            app.changeProprietario(ph,num);
                    }
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
    public void whatOperation(boolean changes)
    {
        try {
            Integer n = viewHouse.consultarChange();
            if(n == null)
                return;
            else if(n == 1)
                this.consultaDados();
            else if(n == 2)
                if(changes) this.alteraDados();
        }
        catch (ValorNegativoException | ValorExcedeMaximoException | NullPointerException e)
        {
            viewHouse.printMessage(e.getMessage());
        }
    }
}
