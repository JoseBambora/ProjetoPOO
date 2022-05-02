import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class leficheiro
{
    static App app = new App(10);

    public static List<String> lerFicheiro(String nomeFich) {
        List<String> lines;
        try { lines = Files.readAllLines(Paths.get(nomeFich), StandardCharsets.UTF_8); }
        catch(IOException exc) { lines = new ArrayList<>(); }
        return lines;
    }

    public static void parseCasa(String input){
        String[] campos = input.split(",");
        String nome = campos[0];
        int nif = Integer.parseInt(campos[1]);
        app.addPessoa(new Pessoa(nome,nif));
        String fornecedor = campos[2];
        try {
            app.addCasa(nome,fornecedor);
        }
        catch (NullPointerNotExistException e)
        {
            System.out.println(e.getMessage());
        }
    }
    public static void parseSmartBulb(String string)
    {
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
        try {
            app.addDevice(new SmartBulb("",true,tone,tamanho,consumo));
        }
        catch (ValorNegativoException e)
        {
            System.out.println(e.getMessage());
        }
    }
    public static void parseSmartCamera(String string)
    {
        String[] campos = string.split(",");
        int x, y;
        campos[0] = campos[0].substring(1,campos[0].length() - 1);
        String []resolucao = campos[0].split("x");
        x = Integer.parseInt(resolucao[0]);
        y = Integer.parseInt(resolucao[1]);
        double tamanho = Integer.parseInt(campos[1]);
        double consumo = Double.parseDouble(campos[2]);
        try {
            app.addDevice(new SmartCamera("",true,consumo,x,y,tamanho));
        }
        catch (ValorNegativoException e)
        {
            System.out.println(e.getMessage());
        }
    }
    public static void parseSmartSpeaker(String string)
    {
        String[] campos = string.split(",");
        int volume = Integer.parseInt(campos[0]);
        double consumo = Double.parseDouble(campos[3]);
        try {
            app.addDevice(new SmartSpeaker("",true,volume,campos[1],campos[2],consumo));

        }
        catch (ValorNegativoException e)
        {
            System.out.println(e.getMessage());
        }
    }
    public static void parse(){

        List<String> linhas = lerFicheiro("log.txt");
        String[] linhaPartida;
        for (String linha : linhas) {
            linhaPartida = linha.split(":", 2);
            switch(linhaPartida[0]){
                case "Casa":
                    parseCasa(linhaPartida[1]);
                    break;
                case "Divisao":
                    app.addDivisao(linhaPartida[1]);
                    break;
                case "SmartBulb":
                    parseSmartBulb(linhaPartida[1]);
                    break;
                case "SmartSpeaker":
                    parseSmartSpeaker(linhaPartida[1]);
                    break;
                case "SmartCamera":
                    parseSmartCamera(linhaPartida[1]);
                    break;
                case "Fornecedor":
                    app.addFornecedor(new Comerciante(linhaPartida[1],app.numberFornecedores()+1));
                    break;
                default:
                    System.out.println(linhaPartida[0] + " " + linhaPartida[1]);
                    break;
            }
        }
        System.out.println("done! " + linhas.size());
    }

    public static void main(String[] args)
    {
        parse();
        System.out.println(app.numberDevices());
        System.out.println(app.numberCasas());
        System.out.println(app.numberFornecedores());
        System.out.println(app.numberPessoas());
    }
}
