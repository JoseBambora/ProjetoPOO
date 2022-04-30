import java.util.Scanner;

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
                break;
        }
    }
    public static void addFornecedor()
    {

    }
    public static void addCasa()
    {

    }
    public static void addPessoa()
    {

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
