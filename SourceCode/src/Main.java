import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;

import static java.lang.Integer.parseInt;

public class Main {
    static ControladorPessoa controladorPessoa;
    static ControladorFornecedor controladorFornecedor;
    static ControladorHouse controladorHouse;
    static ControladorDevices controladorDevices;

    static ControladorQueries controladorQueries;
    static String string = "Diga qual a próxima operação. Introduza a letra 'M' ou 'm' ou 'H' ou 'h' para aceder a um menu de comandos";
    static App app;
    static Scanner iteracao = new Scanner(System.in);

    static boolean varControl = false;

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
                .append("|     Q    |").append(" Queries").append(charNTimes(58,' ')).append("|\n")
                .append(charNTimes(79,'-')).append("\n");
        return menu.toString();
    }

    public static void saveInfo() {
        try {
            System.out.println("Insira o nome do ficheiro onde guardará os resultados os binário");
            String nome = iteracao.next();
            FileOutputStream file = new FileOutputStream(nome);
            ObjectOutputStream oos = new ObjectOutputStream(file);
            oos.writeObject(app);
            oos.flush();
            oos.close();
            file.close();
        }
        catch (IOException e){
            System.out.println(e.getMessage());
            saveInfo();
        }
    }

    public static void getInfo() {
        try {
            System.out.println("Insira o nome do ficheiro onde armazenou os resultados");
            String nome = iteracao.next();
            FileInputStream file = new FileInputStream(nome);
            ObjectInputStream ois = new ObjectInputStream(file);
            app = (App) ois.readObject();
            ois.close();
            file.close();
            controladorDevices = new ControladorDevices(app);
            controladorHouse = new ControladorHouse(app);
            controladorFornecedor = new ControladorFornecedor(app);
            controladorPessoa = new ControladorPessoa(app);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
            getInfo();
        }
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
                case "Fornecedor":
                    controladorFornecedor.addFornecedor(linhaPartida[1],new FormulaCalc1());
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
                default:
                    System.out.println(linhaPartida[0] + " " + linhaPartida[1]);
                    break;
            }
        }
        System.out.println("done! " + linhas.size());
    }

    public static String carregaDados(){
        System.out.println("Deseja utilizar os dados iniciais?");
        System.out.println("Y - Sim");
        System.out.println("Outros inputs - Não");
        return iteracao.next();
    }
    public static void main(String[] args) throws NullPointerException, ValorNegativoException, ValorExcedeMaximoException, IOException, ClassNotFoundException {
        String input = "";
        String resposta = carregaDados();
        if(Objects.equals(resposta, "Y") || Objects.equals(resposta, "y") || Objects.equals(resposta, "S") || Objects.equals(resposta, "s")){
            getInfo();
        }
        else {
            System.out.println("Qual o imposto inicial?");
            app = new App(iteracao.nextInt());
            controladorFornecedor = new ControladorFornecedor(app);
            controladorHouse = new ControladorHouse(app);
            controladorDevices = new ControladorDevices(app);
            controladorPessoa = new ControladorPessoa(app);
            controladorQueries = new ControladorQueries(app);
            parse();
        }
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
                    controladorFornecedor.whatOperation(varControl);
                    break;
                case "D":
                    controladorDevices.whatOperation(varControl);
                    break;
                case "C":
                    controladorHouse.whatOperation(varControl);
                    break;
                case "Q":
                    if (!varControl)
                        varControl = controladorQueries.selecionouConsultaDados();
                    else controladorQueries.selecionouConsultaDados();
                    break;
                default:
                    System.out.println("Comando inválido");
                    break;
            }
            System.out.println(string);
        }
        saveInfo();
    }
}
