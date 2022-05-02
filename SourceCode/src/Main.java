import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ListResourceBundle;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {
    static String menu = "Entrou menu";
    static String string = "Diga qual a próxima operação. Introduza a letra 'M' ou 'm' ou 'H' ou 'h' para aceder a um menu de comandos";
    static App app;
    static Scanner iteracao = new Scanner(System.in);
    public static void addSmartBulb(boolean mode,int tone, double tamanho, double consumo)
    {
        SmartBulb add = null;
        try {
            add = new SmartBulb("",mode,tone,tamanho,consumo);
            app.addDevice(add);
        }
        catch (ValorNegativoException e)
        {
            System.out.println(e.getMessage());
        }
        if(add != null)
            System.out.println("Smart Bulb adicionado com sucesso " + add);
    }
    public static void addSmartSpeaker(boolean mode, int volume, String canal, String marca, double consumo)
    {
        SmartSpeaker add = null;
        try {
            add = new SmartSpeaker("",mode,volume,canal,marca,consumo);
            app.addDevice(add);
        }
        catch (ValorNegativoException e)
        {
            System.out.println(e.getMessage());
        }
        if(add != null)
            System.out.println("Smart Speaker adicionado com sucesso " + add);
    }
    public static void addSmartCamera(boolean mode,double consumo, int x, int y, double tamanho)
    {
        SmartCamera add = null;
        try {
            add = new SmartCamera("",mode,consumo,x,y,tamanho);
            app.addDevice(add);
        }
        catch (ValorNegativoException e)
        {
            System.out.println(e.getMessage());
        }
        if(add != null)
            System.out.println("Smart Camera adicionado com sucesso " + add);
    }
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
        addSmartBulb(true,tone,tamanho,consumo);
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
        addSmartCamera(true,consumo,x,y,tamanho);
    }
    public static void parseSmartSpeaker(String string)
    {
        String[] campos = string.split(",");
        int volume = Integer.parseInt(campos[0]);
        double consumo = Double.parseDouble(campos[3]);
        addSmartSpeaker(true,volume,campos[1],campos[2],consumo);
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

    public static void addDevice()
    {
        System.out.println("Tipo do SmartDevice? SB -> SmartBulb, SC -> SmartCamera, SS -> SmartSpeaker");
        String tipoDevice = iteracao.next();
        System.out.println("Ligado ou desligado?");
        boolean modo;
        switch (iteracao.next())
        {
            case "On":
            case "O":
            case "on":
            case "o":
            case "ON":
            case "ligado":
            case "Ligado":
            case "LIGADO":
                modo = true;
                break;
            default:
                modo = false;
                break;
        }
        System.out.println("Consumo diário?");
        double consumo = iteracao.nextDouble();
        switch (tipoDevice)
        {
            case "SB":
                System.out.println("Qual o tone?");
                int tone = iteracao.nextInt();
                System.out.println("Qual a dimensão?");
                double dimensao = iteracao.nextDouble();
                addSmartBulb(modo,tone,dimensao,consumo);
                break;
            case "SS":
                System.out.println("Qual a marca?");
                String marca = iteracao.next();
                System.out.println("Qual o volume?");
                int volume = iteracao.nextInt();
                System.out.println("Qual o canal?");
                String canal = iteracao.next();
                addSmartSpeaker(modo,volume,canal,marca,consumo);
                break;
            case "SC":
                System.out.println("Qual é a Resolução? (formato HorizontalxVertical");
                String resolucao = iteracao.nextLine();
                String[] resolucoes = resolucao.split("x");
                System.out.println("Qual é o Tamanho?");
                double tamanho = iteracao.nextDouble();
                addSmartCamera(modo,consumo,parseInt(resolucoes[0]),parseInt(resolucoes[1]),tamanho);
                break;
        }
    }
    public static void addFornecedor()
    {
        System.out.println("Qual o nome do Comerciante?");
        String nomeC = iteracao.next();
        System.out.println("Introduza o preço deste comerciante");
        double preco = iteracao.nextDouble();
        Comerciante cm = new Comerciante(nomeC, preco);
        app.addFornecedor(cm);
        System.out.println("Comerciante adicionado com sucesso " + cm);
    }
    public static void addCasa()
    {
        System.out.println("Qual o Nome do proprietario?");
        String nome = iteracao.next();
        System.out.println("Qual o nome do Comerciante?");
        String cm = iteracao.next();
        try {
            app.addCasa(nome,cm);
        }
        catch (NullPointerNotExistException e)
        {
            System.out.println(e.getMessage());
        }
        System.out.println("Casa adicionada com sucesso " + nome + " " + cm);
    }
    public static void addPessoa()
    {
        System.out.println("Qual o nome da pessoa?");
        String nome = iteracao.next();
        System.out.println("Introduza o NIF da pessoa");
        int NIF = iteracao.nextInt();
        Pessoa newP = new Pessoa(nome, NIF);
        app.addPessoa(newP);
        System.out.println("Pessoa adicionada com sucesso " + newP);

    }
    public static void main(String[] args)
    {
        String input = "";
        System.out.println("Qual o imposto inicial?");
        app = new App(iteracao.nextInt());
        parse();
        System.out.println(app.numberDevices());
        System.out.println(app.numberCasas());
        System.out.println(app.numberFornecedores());
        System.out.println(app.numberPessoas());
        System.out.println(string);
        for(input = iteracao.next(); !input.equals("Sair"); input = iteracao.next())
        {
            switch (input)
            {
                case "Menu":
                case "M":
                case "m":
                case "h":
                case "H":
                    System.out.println(menu);
                    break;
                case "AD":
                    addDevice();
                    break;
                case "AC":
                    addCasa();
                    break;
                case "AF":
                    addFornecedor();
                    break;
                case "AP":
                    addPessoa();
                    break;
                case "ND":
                    System.out.println("Número de Smart Devices: " + app.numberDevices());
                    break;
                default:
                    System.out.println("Comando inválido");
                    break;
            }
            System.out.println(string);
        }
    }
}
