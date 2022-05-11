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
    static ControladorPessoa controladorPessoa;
    static ControladorFornecedor controladorFornecedor;
    static ControladorHouse controladorHouse;
    static ControladorDevices controladorDevices;
    static String string = "Diga qual a próxima operação. Introduza a letra 'M' ou 'm' ou 'H' ou 'h' para aceder a um menu de comandos";
    static App app;
    static Scanner iteracao = new Scanner(System.in);

    public static String charNTimes(int n,char c){
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(c).repeat(Math.max(0, n)));
        return sb.toString();
    }

    public static String menu(){
        StringBuilder menu = new StringBuilder();
        menu.append(charNTimes(79,'-')).append("\n")
                .append("| Comandos |").append(charNTimes(23,' ')).append("Descriçao").append(charNTimes(34,' ')).append("|\n")
                .append(charNTimes(79,'-')).append("\n")
                .append("|    AD    |").append(" Adiciona um Dispositivo").append(charNTimes(42,' ')).append("|\n")
                .append("|    AC    |").append(" Adiciona um Comerciante").append(charNTimes(42,' ')).append("|\n")
                .append("|    AF    |").append(" Adiciona um Forncecedor").append(charNTimes(42,' ')).append("|\n")
                .append("|    AP    |").append(" Adiciona um Proprietario").append(charNTimes(41,' ')).append("|\n")
                .append("|    ND    |").append(" Total de Dispositivos").append(charNTimes(44,' ')).append("|\n")
                .append("|     F    |").append(" Operaçoes dos Fornecedores").append(charNTimes(39,' ')).append("|\n")
                .append("|     D    |").append(" Operaçoes dos Dispositivos").append(charNTimes(39,' ')).append("|\n")
                .append("|     C    |").append(" Operaçoes das Casas").append(charNTimes(46,' ')).append("|\n")
                .append(charNTimes(79,'-')).append("\n");
        return menu.toString();
    }
    public static List<String> lerFicheiro(String nomeFich) {
        List<String> lines;
        try { lines = Files.readAllLines(Paths.get(nomeFich), StandardCharsets.UTF_8); }
        catch(IOException exc) { lines = new ArrayList<>(); }
        return lines;
    }

    public static void parse() throws ValorNegativoException, ValorExcedeMaximoException {

        List<String> linhas = lerFicheiro("log.txt");
        String[] linhaPartida;
        for (String linha : linhas) {
            linhaPartida = linha.split(":", 2);
            switch(linhaPartida[0]){
                case "Casa":
                    controladorHouse.parseCasa(linhaPartida[1]);
                    break;
                case "Divisao":
                    controladorHouse.addDivisao(linhaPartida[1]);
                    break;
                case "SmartBulb":
                    controladorDevices.parseSmartBulb(linhaPartida[1]);
                    break;
                case "SmartSpeaker":
                    controladorDevices.parseSmartSpeaker(linhaPartida[1]);
                    break;
                case "SmartCamera":
                    controladorDevices.parseSmartCamera(linhaPartida[1]);
                    break;
                case "Fornecedor":
                    controladorFornecedor.addFornecedor(linhaPartida[1],new FormulaCalc1());
                    break;
                default:
                    System.out.println(linhaPartida[0] + " " + linhaPartida[1]);
                    break;
            }
        }
        System.out.println("done! " + linhas.size());
    }

    public static void main(String[] args) throws NullPointerException, ValorNegativoException, ValorExcedeMaximoException {
        String input = "";
        System.out.println("Qual o imposto inicial?");
        app = new App(iteracao.nextInt());
        controladorFornecedor = new ControladorFornecedor(app);
        controladorHouse = new ControladorHouse(app);
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
                    System.out.println(menu());
                    break;
                case "AD":
                    controladorDevices.addDevice();
                    break;
                case "AC":
                    controladorHouse.addCasaApp();
                    break;
                case "AF":
                    controladorFornecedor.addFornecedor();
                    break;
                case "AP":
                    controladorPessoa.addPessoa();
                    break;
                case "ND":
                    System.out.println("Número de Smart Devices: " + controladorDevices.numberDevices());
                    break;
                case "F":
                    controladorFornecedor.whatOperation();
                    break;
                case "D":
                    controladorDevices.whatOperation();
                case "C":
                    controladorHouse.whatOperation();
                    break;
                default:
                    System.out.println("Comando inválido");
                    break;
            }
            System.out.println(string);
        }
    }
}
