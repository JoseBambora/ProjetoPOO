import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestApp
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
        app.addCasa(nome,fornecedor);
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
        app.addDevice(new SmartBulb("",true,tone,tamanho,consumo));
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
        app.addDevice(new SmartCamera("",true,consumo,x,y,tamanho));
    }
    public static void parseSmartSpeaker(String string)
    {
        String[] campos = string.split(",");
        int volume = Integer.parseInt(campos[0]);
        double consumo = Double.parseDouble(campos[3]);
        app.addDevice(new SmartSpeaker("",true,volume,campos[1],campos[2],consumo));
    }
    @BeforeAll
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
    }
    @Test
    public void TestCount()
    {
        assertEquals(app.numberDevices(),6555);
        assertEquals(app.numberFornecedores(),14);
        assertEquals(app.numberPessoas(),200);
        assertEquals(app.numberCasas(),200);
    }
    @Test
    public void TestInfo()
    {
        Map<String,Pessoa> pessoas = app.getPessoas();
        Map<String,Comerciante> fornecedores = app.getFornecedores();
        Map<String,House> casas = app.getCasas();
        Map<String,SmartDevices> devices = app.getDevices();
        assertTrue(pessoas.get("Jose Manuel Antunes de Carvalho") == casas.get("111").getProprietario());
        assertTrue(fornecedores.get("Endesa") == casas.get("111").getFornecedor());
        double op = fornecedores.get("Endesa").getPreco();
        fornecedores.get("Endesa").setPreco(30000);
        assertEquals(fornecedores.get("Endesa").getPreco(),casas.get("111").getFornecedor().getPreco());
        app.changeFornecedor("111","EDP Comercial");
        assertTrue(fornecedores.get("EDP Comercial") == casas.get("111").getFornecedor());
        app.changeFornecedor("111","Endesa");
        assertTrue(fornecedores.get("Endesa") == casas.get("111").getFornecedor());
        fornecedores.get("Endesa").setPreco(op);
        Pessoa pessoa = casas.get("110").getProprietario();
        app.changeProprietario("110","Jose Manuel Antunes de Carvalho");
        assertTrue(pessoas.get("Jose Manuel Antunes de Carvalho") == casas.get("110").getProprietario());
        assertTrue(casas.get("110").getProprietario() == casas.get("111").getProprietario());
        app.changeProprietario("110",pessoa.getNome());
        assertFalse(pessoas.get("Jose Manuel Antunes de Carvalho") == casas.get("110").getProprietario());
        assertTrue(pessoas.get(pessoa.getNome()) == casas.get("110").getProprietario());
    }
}
