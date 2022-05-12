import java.util.Objects;
import java.util.function.Predicate;

public class ControladorDevices {
    private ViewDevices view;
    private App app;

    ControladorDevices(App app){
        this.app = app;
        this.view = new ViewDevices();
    }
    public void parseSmartBulb(String string) throws ValorNegativoException {
        String[] campos = string.split(",");
        int tone;
        switch (campos[0])
        {
            case "Warm":
                tone = SmartBulb.WARM;
                break;
            case "Neutral":
                tone = SmartBulb.NEUTRAL;
                break;
            default:
                tone = SmartBulb.COLD;
                break;
        }
        double tamanho = Integer.parseInt(campos[1]);
        double consumo = Double.parseDouble(campos[2]);
        app.addSmartBulbP(true,tone,tamanho,consumo);
    }
    public void parseSmartCamera(String string) throws ValorNegativoException {
        String[] campos = string.split(",");
        int x, y;
        campos[0] = campos[0].substring(1,campos[0].length() - 1);
        String []resolucao = campos[0].split("x");
        x = Integer.parseInt(resolucao[0]);
        y = Integer.parseInt(resolucao[1]);
        double tamanho = Integer.parseInt(campos[1]);
        double consumo = Double.parseDouble(campos[2]);
        app.addSmartCameraP(true,consumo,x,y,tamanho);
    }
    public void parseSmartSpeaker(String string) throws ValorNegativoException, ValorExcedeMaximoException {
        String[] campos = string.split(",");
        int volume = Integer.parseInt(campos[0]);
        double consumo = Double.parseDouble(campos[3]);
        app.addSmartSpeakerP(true,volume,campos[1],campos[2],consumo);
    }
    public void addDevice() throws ValorNegativoException, ValorExcedeMaximoException {
        Integer option = view.getDevice(); // 1 -> smartbulb, 2 -> smartcamera, 3 -> smartspeaker
        Boolean on = view.getOn();
        Double consumo = view.getConsumo();
        if(option == null || on == null || consumo == null)
            return;
        switch(option)
        {
            case 1:
                Integer tone = view.getTone();
                if(tone == null) return;
                Double dimensao = view.getDimensao();
                if (dimensao == null) return;
                app.addSmartBulb(on,tone,dimensao,consumo);
                view.sucess();
                break;
            case 2:
                String resolucao = view.getResolucao();
                if(resolucao == null) return;
                String[] res = resolucao.split("x");
                Double tamanho = view.getTamanho();
                if(tamanho == null) return;
                app.addSmartCamera(on,consumo,Integer.parseInt(res[0]),Integer.parseInt(res[1]),tamanho);
                view.sucess();
                break;
            case 3:
                Integer volume = view.getVolume();
                if(volume == null) return;
                String canal = view.getCanal();
                if(canal == null) return;
                String marca = view.getMarca();
                if(marca == null) return;
                app.addSmartSpeaker(on,volume,canal,marca,consumo);
                view.sucess();
                break;
            default:
                break;
        }
    }
    public int numberDevices()
    {
        return app.numberDevices();
    }
    public Predicate<SmartDevices> devicesPredicate(){
        Integer n = view.predicates();
        if(n == null) return null;
        Predicate<SmartDevices> r;
        switch (n){
            case 2:
                Double aux = view.insertValue();
                if(aux == null) return null;
                r = p ->(p.getConsumo() > aux);
                break;
            case 3:
                Double aux2 = view.insertValue();
                if(aux2 == null) return null;
                r = p ->(p.getConsumo() < aux2);
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
        if(p == null) return;
        String r = null;
        r = app.consultaDevice(p).toString();
        view.print(r);
    }

    private Predicate<SmartDevices> devicesPredicateChange(){
        Integer n = view.predicates();
        if(n == null) return null;
        Predicate<SmartDevices> r;
        switch (n){
            case 2:
                Double aux = view.insertValue();
                if(aux == null) return null;
                r = p ->(p.getConsumo() > aux);
                break;
            case 3:Double aux2 = view.insertValue();
                if(aux2 == null) return null;
                r = p ->(p.getConsumo() < aux2);
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
        Integer mudaestado = view.mudaEstado();
        if(mudaestado == null) return;
        switch(mudaestado) {
            case 1:
                Predicate<SmartDevices> r = this.devicesPredicate();
                if(r == null) return;
                Integer onoffconsumo = view.onOffConsumo();
                if(onoffconsumo == null) return;
                switch (onoffconsumo) {
                    case 1:
                        app.setDevicesOnOff(r, true);
                        break;
                    case 2:
                        app.setDevicesOnOff(r, false);
                        break;
                    case 3:
                        app.setConsumoDevices(r, view.insertValue());
                        break;
                    default:
                        break;
                }
                break;
            case 2:
                Predicate<SmartDevices> r2 = this.devicesPredicateChange();
                if(r2 == null) return;
                Integer tom = view.insertValueInteger();
                if(tom == null) return;
                app.changeToneDevices(r2, tom);
                break;
            case 3:
                Predicate<SmartDevices> r3 = this.devicesPredicateChange();
                if(r3 == null) return;
                Integer canalvolume = view.canalVolume();
                if(canalvolume == null) return;
                if (canalvolume == 1) {
                    String canal = view.insertString();
                    if (canal == null) return;
                    app.changeCanalDevices(r3, canal);
                } else if (canalvolume == 2) {
                    Integer volume = view.insertValueInteger();
                    if (volume == null) return;
                    app.changeVolumeDevices(r3, volume);
                }
                break;
            default:
                break;
        }
    }

    public void whatOperation() throws ValorNegativoException, NullPointerException, ValorExcedeMaximoException {
        Integer n = view.consultarAlterar();
        if(n == null) return;
        switch (n){
            case 1:
                consultarDados();
                break;
            case 2:
                changeDadosDevice();
                break;
            default:
                break;
        }
    }
}