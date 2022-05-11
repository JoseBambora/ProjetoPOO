import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestApp
{
    static App app = new App(23);
    public static void addSmartBulb(boolean mode,int tone, double tamanho, double consumo)
    {
        try {
            app.addSmartBulb(mode,tone,tamanho,consumo);
        }
        catch (ValorNegativoException e)
        {
            System.out.println(e.getMessage());
        }
    }
    public static void addSmartSpeaker(boolean mode, int volume, String canal, String marca, double consumo)
    {
        try {
            app.addSmartSpeaker(mode,volume,canal,marca,consumo);
        }
        catch (ValorNegativoException | ValorExcedeMaximoException e)
        {
            System.out.println(e.getMessage());
        }
    }
    public static void addSmartCamera(boolean mode,double consumo, int x, int y, double tamanho)
    {
        try {
            app.addSmartCamera(mode,consumo,x,y,tamanho);
        }
        catch (ValorNegativoException e)
        {
            System.out.println(e.getMessage());
        }
    }
    public static List<String> lerFicheiro(String nomeFich) {
        List<String> lines;
        try { lines = Files.readAllLines(Paths.get(nomeFich), StandardCharsets.UTF_8); }
        catch(IOException exc) { lines = new ArrayList<>(); }
        return lines;
    }

    public static void parseCasa(String input){
        try {

            String[] campos = input.split(",");
            String nome = campos[0];
            int nif = Integer.parseInt(campos[1]);
            app.addPessoa(nome,nif);
            String fornecedor = campos[2];
            app.addCasa(nif,fornecedor);
        }
        catch (NullPointerException | ValorNegativoException e)
        {
            System.out.println(e.getMessage());
        }
    }
    public static void parseSmartBulb(String string) throws ValorNegativoException {
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
    public static void parseSmartCamera(String string) throws ValorNegativoException {
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
    public static void parseSmartSpeaker(String string) throws ValorNegativoException, ValorExcedeMaximoException {
        String[] campos = string.split(",");
        int volume = Integer.parseInt(campos[0]);
        double consumo = Double.parseDouble(campos[3]);
        app.addSmartSpeakerP(true,volume,campos[1],campos[2],consumo);
    }
    @BeforeAll
    public static void parse() throws ValorNegativoException, ValorExcedeMaximoException, NullPointerException, DevicesExistException {

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
                    app.addFornecedor(linhaPartida[1],new FormulaCalc1());
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
        try {
            Map<Integer,Pessoa> pessoas = app.getPessoas();
            Map<String,Comerciante> fornecedores = app.getFornecedores();
            Map<String,House> casas = app.getCasas();
            Map<String,SmartDevices> devices = app.getDevices();
            assertTrue(pessoas.get(758618872) == casas.get("111").getProprietario());
            assertTrue(fornecedores.get("Endesa") == casas.get("111").getFornecedor());
            app.changeFornecedor("111","EDP Comercial");
            assertTrue(fornecedores.get("EDP Comercial") == casas.get("111").getFornecedor());
            app.changeFornecedor("111","Endesa");
            assertTrue(fornecedores.get("Endesa") == casas.get("111").getFornecedor());
            Pessoa pessoa = casas.get("110").getProprietario();
            app.changeProprietario("110",758618872);
            assertTrue(pessoas.get(758618872) == casas.get("110").getProprietario());
            assertTrue(casas.get("110").getProprietario() == casas.get("111").getProprietario());
            app.changeProprietario("110",pessoa.getNIF());
            assertFalse(pessoas.get(758618872) == casas.get("110").getProprietario());
            assertTrue(pessoas.get(pessoa.getNIF()) == casas.get("110").getProprietario());
        }
        catch (NullPointerException | FornecedorNotExistException | PessoaNotExistException | CasaNotExistException e)
        {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void testAnvacaDias(){
        try
        {
            app.avancaDias(31);
            Map<String,Comerciante> fornecedores = app.getFornecedores();
            for(Comerciante value : fornecedores.values())
            {
                Map<LocalDate, List<Fatura>>  faturas = value.getFaturasEmitidas();
                for(List<Fatura> fatura : faturas.values())
                {
                    assertTrue(fatura.size() > 0);
                    switch (value.getNome()) {
                        case "EDP Comercial":
                            assertEquals(fatura.size(), 16);
                            break;
                        case "Galp Comercial":
                            assertEquals(fatura.size(), 10);
                            break;
                        case "Iberdrola":
                            assertEquals(fatura.size(), 23);
                            break;
                        case "Endesa":
                            assertEquals(fatura.size(), 13);
                            break;
                        case "Gold Energy":
                            assertEquals(fatura.size(), 13);
                            break;
                        case "Coopernico":
                            assertEquals(fatura.size(), 11);
                            break;
                        case "Enat":
                            assertEquals(fatura.size(), 10);
                            break;
                        case "MEO Energia":
                            assertEquals(fatura.size(), 21);
                            break;
                        case "Muon":
                            assertEquals(fatura.size(), 15);
                            break;
                        case "Luzboa":
                            assertEquals(fatura.size(), 9);
                            break;
                        case "Energia Simples":
                            assertEquals(fatura.size(), 12);
                            break;
                        case "SU Electricidade":
                            assertEquals(fatura.size(), 16);
                            break;
                        case "EDA":
                            assertEquals(fatura.size(), 14);
                            break;
                    }
                    for(Fatura fatura1 : fatura)
                    {
                        assertTrue(fatura1.getConsumo() > 0);
                        assertTrue(fatura1.getPreco() > 0);
                        double consumo = app.getCasas().get(fatura1.getLocal()).getConsumo(31);
                        assertEquals(consumo, fatura1.getConsumo());
                        assertEquals(value.getFormula().calculaPreco(consumo,app.getImposto()), fatura1.getPreco());
                        assertNotNull(fatura1.getCliente());
                        assertEquals(app.getImposto(), fatura1.getImposto());
                        assertNotNull(fatura1.getLocal());
                    }
                }
            }
        }
        catch (ValorNegativoException | NullPointerException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
