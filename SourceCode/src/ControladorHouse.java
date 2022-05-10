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
    public void addCasaApp() throws NullPointerException {
        this.app.addCasa((int) viewHouse.getNumber("nif do dono"), viewHouse.getStr("nome do comerciante"));
    }
    public void addCasaApp(int nif, String nome) throws NullPointerException {
        this.app.addCasa(nif,nome);
    }
    private Predicate<House> housePredicate() throws ValorNegativoException, NullPointerException {
        int n = viewHouse.predicate();
        Predicate<House> r = null;
        switch (n)
        {
            case 2:
                String local = viewHouse.getStr("local");
                r = h -> h.getLocal().equals(local);
                break;
            case 3:
                String div = viewHouse.getStr("divisao");
                r = h -> h.hasDivisao(div);
                break;
            case 4:
                int number = (int) viewHouse.getNumber("numero de divisoes");
                r = h -> h.numberDivisoes() > number;
                break;
            case 5:
                String id = viewHouse.getStr("id do device");
                r = h -> h.hasDevice(id);
                break;
            case 6:
                number = (int) viewHouse.getNumber("numero de devices");
                r = h -> h.numberDevices() > number;
                break;
            case 7:
                number = (int) viewHouse.getNumber("nif da pessoa");
                r = h -> h.getProprietario().equals(app.getPessoa(number));
                break;
            case 8:
                String nome = viewHouse.getStr("nome do fornecedor");
                r = h -> h.getFornecedor().equals(app.getFornecedor(nome));
                break;
            default:
                r = Objects::nonNull;
                break;
        }
        return r;
    }
    private Predicate<Map.Entry<String,List<String>>> divisaoPredicate() throws ValorNegativoException, NullPointerException {
        int n = viewHouse.predicateDiv();
        Predicate<Map.Entry<String,List<String>>> r = null;
        switch (n)
        {
            case 2:
                int num = (int) viewHouse.getNumber("para quantos dispositivos a mais");
                r = h -> h.getValue().size() > num;
                break;
            case 3:
                String div = viewHouse.getStr(" para o nome da divisao");
                r = h -> h.getKey().equals(div);
                break;
            default:
                r = Objects::nonNull;
                break;
        }
        return r;
    }
    public void consultaDados() throws ValorNegativoException, NullPointerException {
        String print = "";
        Predicate<House> ph = this.housePredicate();
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
        app.associaHouse(ph,aux);
    }
    private void acrescentarDivisoes(Predicate<House> ph)
    {
        List<String> divisoes = viewHouse.getDivisoesAdd();
        app.addDivisoes(ph,divisoes);
    }
    private void moveDivisoes()
    {
        int number = (int) viewHouse.getNumber("mover quantos devices?");
        String local = viewHouse.getStr("local da casa");
        for(int i = 0; i < number; i++)
        {
            String id = viewHouse.getStr("id do device");
            String divisao = viewHouse.getStr("para qual a divisao");
            app.movedivisao(local,divisao,id);
        }
    }
    public void alteraDados() throws ValorNegativoException, ValorExcedeMaximoException, NullPointerException {
        Predicate<House> ph;
        int n = viewHouse.decideAlterar();
        switch (n)
        {
            case 1:
                ph = this.housePredicate();
                this.acrescentarDevices(ph);
                break;
            case 2:
                ph = this.housePredicate();
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
                if(number == 1)
                    app.changeStateDevice(ph,smartDevicesPredicate,predicate2,true);
                else if(number == 2)
                    app.changeStateDevice(ph,smartDevicesPredicate,predicate2,false);
                break;
            case 5:
                ph = this.housePredicate();
                app.changeFornecedor(ph, viewHouse.getStr("nome do fornecedor"));
                break;
            case 6:
                ph = this.housePredicate();
                app.changeProprietario(ph,(int) viewHouse.getNumber("nif do novo proprietario"));
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
