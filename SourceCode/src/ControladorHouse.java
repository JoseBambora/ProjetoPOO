import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
    private Predicate<House> housePredicate() throws ValorNegativoException, NullPointerException {
        int n = viewHouse.predicate();
        Predicate<House> r = null;
        switch (n)
        {
            case 2:
                r = h -> h.getLocal().equals(viewHouse.getStr());
                break;
            case 3:
                r = h -> h.hasDivisao(viewHouse.getStr());
                break;
            case 4:
                r = h -> h.numberDivisoes() > (int) viewHouse.getNumber();
                break;
            case 5:
                r = h -> h.hasDevice(viewHouse.getStr());
                break;
            case 6:
                r = h -> h.numberDevices() > (int) viewHouse.getNumber();
                break;
            case 7:
                //r = h -> h.getProprietario().equals(app.getPesssoa((int) viewHouse.getNumber()));
                break;
            case 8:
                //r = h -> h.getFornecedor().equals(app.getFornecedor(viewHouse.getStr()));
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
                //print = app.getComerciantesCasas Map<Comerciante,List<String>>;
                break;
            case 2:
                //print = app.getPropreitarioCasas Map<Proprietario,List<String>>;
                break;
            case 3:
                //print = app.getDevicesCasas.toString() List<SmartDevice>;
                break;
            case 4:
                //print = app.getDivisoesCasas.toString() List<String>;
                break;
            case 5:
                //print = app.getLocalidadeCasas.toString() List<String>;
                break;
            default:
                break;
        }
        viewHouse.printMessage(print);
    }
    private void acrescentarDevices(Predicate<House> ph) throws ValorNegativoException, ValorExcedeMaximoException {
        int number = (int) viewHouse.getNumber();
        List<SmartDevices> smartDevices = new ArrayList<>(number);
        for(int i = 0; i < number; i++)
        {
            controladorDevices.addDevice();
            //String id = app.getIdLastDeviceAdd();
            //app.associaHouse(ph,id); FAZER DIFERENCIAÇÃO NOS IDS
        }
    }
    private void acrescentarDivisoes(Predicate<House> ph)
    {
        List<String> divisoes = viewHouse.getDivisoesAdd();
        //app.addDivisoes(ph,divisoes)
    }
    private void moveDivisoes()
    {
        String divisao = viewHouse.getStr();
        String local = viewHouse.getStr();
        //Predicate<SmartDevices> ps = controladorDevices.devicesPredicate();
        //app.movedivisao(local,ps,divisao)
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
                //if(number == 1)
                    //app.changeStateDevice(ph,true);
                //else if(number == 2)
                    //app.changeStateDevice(ph,false);
                break;
            case 5:
                ph = this.housePredicate();
                //app.changeFornecedor(ph, viewHouse.getStr());
                break;
            case 6:
                ph = this.housePredicate();
                //app.changeProprietario(ph,(int) viewHouse.getNumber());
                break;
            default:
                break;
        }
    }
    public void whatOperation()
    {
        try {
            int n = viewHouse.consultarChange();
            if(n == 0)
                this.consultaDados();
            else if(n == 1)
                this.alteraDados();
        }
        catch (ValorNegativoException | ValorExcedeMaximoException | NullPointerException e)
        {
            viewHouse.printMessage(e.getMessage());
        }
    }
}
