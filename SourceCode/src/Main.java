import java.time.LocalDate;
import java.util.ListResourceBundle;
import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class Main {
    static String menu = "Entrou menu";
    static String string = "Diga qual a próxima operação. Introduza a letra 'M' ou 'm' ou 'H' ou 'h' para aceder a um menu de comandos";
    static App app;
    static Scanner iteracao = new Scanner(System.in);
    public static void addDevice()
    {
        System.out.println("Tipo do SmartDevice? SB -> SmartBulb, SC -> SmartCamera, SS -> SmartSpeaker");
        String tipoDevice = iteracao.next();
        System.out.println("Introduza o id do device");
        String idDevice = iteracao.next();
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
                SmartBulb sb = new SmartBulb(idDevice,modo,tone,dimensao,consumo);
                app.addDevice(sb);
                System.out.println("Smart Bulb adicionado com sucesso " + sb);
                break;
            case "SS":
                System.out.println("Qual a marca?");
                String marca = iteracao.next();
                System.out.println("Qual o volume?");
                int volume = iteracao.nextInt();
                System.out.println("Qual o canal?");
                int canal = iteracao.nextInt();
                SmartSpeaker add = new SmartSpeaker(idDevice,modo,volume,canal,marca,consumo);
                app.addDevice(add);
                System.out.println("Smart Speaker adicionado com sucesso " + add);
                break;
            case "SC":
                System.out.println("Qual é a Resolução? (formato HorizontalxVertical");
                String resolucao = iteracao.nextLine();
                String[] resolucoes = resolucao.split("x");
                System.out.println("Qual é o Tamanho?");
                double tamanho = iteracao.nextDouble();
                SmartCamera sc = new SmartCamera(idDevice,modo,consumo,parseInt(resolucoes[0]),parseInt(resolucoes[1]),tamanho);
                System.out.println("Smart Camera adicionado com sucesso " + sc);
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
        System.out.println("Qual o local");
        String local = iteracao.next();
        House newH = new House (null,null ,local);
        app.addCasa(newH, nome, cm);
        System.out.println("Casa adicionada com sucesso " + newH);


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
