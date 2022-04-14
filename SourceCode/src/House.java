import java.util.HashMap;
import java.util.Map;
import java.util.List;
public class House {
    private Map<String,SmartDevices> devices;
    private Map<String,List<String>> divisoes;
    private Pessoa proprietario;
    private Morada local;
    private Comerciante fornecedor;

    public House(){
        this.proprietario = new Pessoa();
        this.local = new Morada();
        this.fornecedor = new Comerciante();
        this.devices = new HashMap<>();
        this.divisoes = new HashMap<>();
    }

    public House(Pessoa proprietario, Comerciante comerciante, Morada local){
        this.proprietario = proprietario;
        this.local = local;
        this.fornecedor = comerciante;
        this.devices = new HashMap<>();
        this.divisoes = new HashMap<>();
    }
}
