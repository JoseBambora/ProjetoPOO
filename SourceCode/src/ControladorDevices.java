import java.util.Objects;
import java.util.function.Predicate;

public class ControladorDevices {
    private ViewDevices view;
    private App app;

    ControladorDevices(App app){
        this.app = app;
        this.view = new ViewDevices();
    }

    public void addDevice() throws ValorNegativoException, ValorExcedeMaximoException {
        boolean on = view.getOn();
        double consumo = view.getConsumo();
        int option = view.getDevice(); // 0 -> smartbulb, 1 -> smartcamera, 2 -> smartspeaker
        switch(option)
        {
            case 0:
                int tone = view.getTone();
                double dimensao = view.getDimensao();
                app.addSmartBulb(on,tone,dimensao,consumo);
                view.sucess();
                break;
            case 1:
                String[] res = view.getResolucao().split("x");
                double tamanho = view.getTamanho();
                app.addSmartCamera(on,consumo,Integer.parseInt(res[0]),Integer.parseInt(res[1]),tamanho);
                view.sucess();
                break;
            case 2:
                int volume = view.getVolume();
                String canal = view.getCanal();
                String marca = view.getMarca();
                app.addSmartSpeaker(on,volume,canal,marca,consumo);
                view.sucess();
                break;
            default:
                break;
        }
    }
    public void addSmartBulb(boolean on, double consumo, int tone, double dimensao) throws ValorNegativoException {
        app.addSmartBulb(on, tone, dimensao, consumo);
        view.sucess();
    }
    public void addSmartCamera(boolean on, double consumo, String resolucao,double tamanho) throws ValorNegativoException {
        String[] res = resolucao.split("x");
        app.addSmartCamera(on, consumo, Integer.parseInt(res[0]), Integer.parseInt(res[1]), tamanho);
        view.sucess();
    }
    public void addSmartSpeaker(boolean on, double consumo, int volume, String canal, String marca) throws ValorNegativoException, ValorExcedeMaximoException {
        app.addSmartSpeaker(on,volume,canal,marca,consumo);
        view.sucess();
    }
    private Predicate<SmartDevices> devicesPredicate(){
        int n = view.predicates();
        Predicate<SmartDevices> r;
        switch (n){
            case 2:
                r = p ->(p.getConsumo() > view.insertValue());
                break;
            case 3:
                r = p ->(p.getConsumo() < view.insertValue());
                break;
            case 4:
                r = SmartDevices::isOn;
                break;
            case 5:
                r = p ->(!p.isOn());
                break;
            case 6:
                r = p ->(p instanceof SmartCamera);
                break;
            case 7:
                r = p -> (p instanceof SmartBulb);
                break;
            case 8:
                r = p -> (p instanceof SmartSpeaker);
                break;
            default:
                r = Objects::nonNull;
                break;
        }
        return r;
    }
    public void consultarDados() {
        Predicate<SmartDevices> p = this.devicesPredicate();
        String r = "";
        r = app.consultaDevice(p).toString();
        view.print(r);
    }

    private Predicate<SmartDevices> devicesPredicateChange(){
        int n = view.predicates();
        Predicate<SmartDevices> r;
        switch (n){
            case 2:
                r = p ->(p.getConsumo() > view.insertValue());
                break;
            case 3:
                r = p ->(p.getConsumo() < view.insertValue());
                break;
            case 4:
                r = SmartDevices::isOn;
                break;
            case 5:
                r = p ->(!p.isOn());
                break;

            default:
                r = Objects::nonNull;
                break;
        }
        return r;
    }



    public void changeDadosDevice() throws ValorNegativoException, NullPointerException, ValorExcedeMaximoException {
        //mudar - on, consumo, tone, volume e canal
        int mudaestado = view.mudaEstado();
        switch(mudaestado){
            case 1:
                Predicate<SmartDevices> r = this.devicesPredicate();
                int onoffconsumo = view.onOffConsumo();
                switch (onoffconsumo){
                    case 1:
                        app.setDevicesOnOff(r,true);
                        break;
                    case 2:
                        app.setDevicesOnOff(r,false);
                        break;
                    case 3:
                        app.setConsumoDevices(r,view.insertValue());
                        break;
                    default:
                        break;
                }
                break;
            case 2:
                Predicate<SmartDevices> r2 = this.devicesPredicateChange();
                int tom = (int) view.insertValue();
                app.changeToneDevices(r2,tom);
                break;
            case 3:
                Predicate<SmartDevices> r3 = this.devicesPredicateChange();
                int canalvolume = view.canalVolume();
                if(canalvolume == 1){
                    String canal = view.insertString();
                    app.changeCanalDevices(r3,canal);
                }
                else if (canalvolume == 2){
                    int volume = (int) view.insertValue();
                    app.changeVolumeDevices(r3,volume);
                }
                break;
            default:
                break;
        }
    }
}


