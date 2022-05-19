import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Predicate;

public class TestApp
{
    static App app = new App(23);
    static boolean bool = false;
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
        catch (NullPointerException | ValorNegativoException | FornecedorNotExistException | PessoaNotExistException e)
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
    public static void parse() throws ValorNegativoException, ValorExcedeMaximoException, NullPointerException, DivisaoExistException {

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
        if(bool) assertEquals(app.numberDevices(),6555 + 102);
        else
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
    @Test
    public void testChangeProprietarioPredicate()
    {
        try {
            Predicate<House> p = h -> h.getLocal().equals("111");
            app.changeProprietario(p,91953719);
            Pessoa pessoa = app.getPessoa(91953719);
            Predicate<House> p2 = h -> h.getProprietario().equals(pessoa);
            Map<Integer,List<String>> map = app.getPropreitarioCasas(p2);
            assertEquals(app.getCasas().get("111").getProprietario(),app.getPessoa(91953719));
            assertEquals(app.getCasas().get("17").getProprietario(),app.getPessoa(91953719));
            assertEquals(map.size(),1);
            List<String> list = map.get(91953719);
            assertEquals(list.size(),2);
            assertEquals(app.getCasas().get(list.get(0)),app.getCasas().get("111"));
            assertEquals(app.getCasas().get(list.get(1)),app.getCasas().get("17"));
            app.changeProprietario(p,758618872);
            map = app.getPropreitarioCasas(p2);
            assertNotEquals(app.getCasas().get("111").getProprietario(),app.getPessoa(91953719));
            assertEquals(app.getCasas().get("111").getProprietario(),app.getPessoa(758618872));
            assertEquals(app.getCasas().get("17").getProprietario(),app.getPessoa(91953719));
            assertEquals(map.size(),1);
            list = map.get(91953719);
            assertEquals(list.size(),1);
            assertEquals(app.getCasas().get(list.get(0)),app.getCasas().get("17"));
        }
        catch (PessoaNotExistException | NullPointerException e)
        {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void testChangeFornecedorPredicate()
    {
        try {
            Predicate<House> p = h -> h.getLocal().equals("111");
            app.changeFornecedor(p,"EDP Comercial");
            Comerciante comerciante = app.getFornecedor("EDP Comercial");
            Predicate<House> p2 = h -> h.getFornecedor().equals(comerciante);
            assertEquals(app.getCasas().get("111").getFornecedor(),app.getFornecedor("EDP Comercial"));
            Map<String,List<String>> map = app.getComerciantesCasas(p2);
            assertEquals(map.size(),1);
            List<String> strings = map.get("EDP Comercial");
            assertEquals(strings.size(),17);
            assertTrue(strings.contains("111"));
            app.changeFornecedor(p,"Endesa");
            assertEquals(app.getCasas().get("111").getFornecedor(),app.getFornecedor("Endesa"));
            map = app.getComerciantesCasas(p2);
            assertEquals(map.size(),1);
            strings = map.get("EDP Comercial");
            assertEquals(strings.size(),16);
            assertFalse(strings.contains("111"));
        }
        catch (FornecedorNotExistException | NullPointerException e)
        {
            System.out.println(e.getMessage());
        }
    }
    public void auxTestChangeStateDevices(House casa, Predicate<Map.Entry<String,List<String>>> divPredicate,Predicate<SmartDevices> p1,boolean bool)
    {
        Map<String, SmartDevices> smartDevicesMap = app.getDevices();
        Map<String,List<String>> divisoes = casa.getDivisoes();
        for(Map.Entry<String,List<String>> div : divisoes.entrySet())
        {
            if(divPredicate.test(div))
            {
                List<String> list = divisoes.get(div.getKey());
                for(String key : list)
                {
                    if(p1.test(smartDevicesMap.get(key)))
                        assertEquals(smartDevicesMap.get(key).isOn(),bool);
                }
            }
        }
    }
    @Test
    public void testChangeStateDevices()
    {
        Predicate<House> p = h -> h.getLocal().equals("111");
        Predicate<SmartDevices> p1 = SmartDevices::isOn;
        Predicate<SmartDevices> p2 = sd ->(sd instanceof SmartBulb);
        Predicate<Map.Entry<String,List<String>>> divPredicate = d -> d.getKey().equals("Sala de Jantar");
        app.changeStateDevice(p,p1,divPredicate,false);
        Map<String, House> houseMap = app.getCasas();
        for(House casa : houseMap.values())
        {
            if(p.test(casa))
                this.auxTestChangeStateDevices(casa,divPredicate,p1,false);
        }
        app.changeStateDevice(p,p2,divPredicate,false);
        for(House casa : houseMap.values())
        {
            if(p.test(casa))
                this.auxTestChangeStateDevices(casa,divPredicate,p2,false);
        }
        app.changeStateDevice(p,p1,divPredicate,true);
        for(House casa : houseMap.values())
        {
            if(p.test(casa))
                this.auxTestChangeStateDevices(casa,divPredicate,p1,true);
        }
        app.changeStateDevice(p,p2,divPredicate,true);
        for(House casa : houseMap.values())
        {
            if(p.test(casa))
                this.auxTestChangeStateDevices(casa,divPredicate,p2,true);
        }
    }
    @Test
    public void testMoveDivisao()
    {
        try {
            app.movedivisao("111","Casa de Banho","3632");
            app.movedivisao("111","Casa de Banho","3633");
            assertEquals(app.getCasas().get("111").getDivisoes().get("Quarto").size(),0);
            assertEquals(app.getCasas().get("111").getDivisoes().get("Casa de Banho").size(),4);
            assertEquals(app.getCasas().get("111").getDivisoes().get("Casa de Banho").get(0),"3626");
            assertEquals(app.getCasas().get("111").getDivisoes().get("Casa de Banho").get(2),"3632");
            assertEquals(app.getCasas().get("111").getDivisoes().get("Casa de Banho").get(3),"3633");
            app.movedivisao("111","Quarto","3633");
            app.movedivisao("111","Quarto","3632");
            assertEquals(app.getCasas().get("111").getDivisoes().get("Quarto").size(),2);
            assertEquals(app.getCasas().get("111").getDivisoes().get("Casa de Banho").size(),2);
            assertEquals(app.getCasas().get("111").getDivisoes().get("Casa de Banho").get(0),"3626");
            assertEquals(app.getCasas().get("111").getDivisoes().get("Quarto").get(0),"3633");
            assertEquals(app.getCasas().get("111").getDivisoes().get("Quarto").get(1),"3632");
        }
        catch (DeviceNotExistException | CasaNotExistException | DivisaoNotExistException e)
        {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void testQueries()
    {
        try {

            Predicate<Comerciante> p = c -> c.getNome().equals("EDP Comercial");
            Comerciante comerciante = app.queryMaiorFornecedor();
            Pessoa pessoa = app.queryMaiorConsumidor();
            List<Fatura> faturas = app.queryFaturas(p);
            List<Pessoa> pessoas = app.queryMaioresConsumidores(LocalDate.of(2020,1,1),LocalDate.now().plusDays(31));
            assertTrue(faturas.size() > 0);
            for(Fatura fatura : faturas)
            {
                assertEquals(fatura.getImposto(), app.getImposto());
                assertTrue(fatura.getPreco() > 0);
                assertTrue(fatura.getConsumo() > 0);
                assertNotEquals(fatura.getCliente(), null);
                assertNotEquals(app.getCasas().get(fatura.getLocal()), null);
            }
            assertNotEquals(pessoa, null);
            assertNotEquals(app.getPessoa(pessoa.getNIF()), null);
            assertNotEquals(app.getFornecedor(comerciante.getNome()), null);
            assertTrue(pessoas.size() > 0);
            assertEquals(app.getDataPrograma(),LocalDate.now().plusDays(31));
            assertEquals(pessoa,pessoas.get(0));
            for(Pessoa pessoa1 : pessoas)
            {
                assertNotEquals(app.getPessoa(pessoa1.getNIF()),null);
            }
        }
        catch (PessoaNotExistException | FornecedorNotExistException e)
        {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void testReplicateNTimes()
    {
        try {
            bool = true;
            Predicate<House> p1 = Objects::nonNull;
            String div = "Casa de Banho";
            app.addSmartBulb(true,2,30,30);
            String lastid = app.getIdLastDeviceAdd();
            app.replicateNTimesDevice(p1,div);
            Map<String,SmartDevices> map = app.getDevices();
            SmartDevices smartDevices = map.get(lastid);
            int n = Integer.parseInt(lastid) + 1;
            lastid = app.getIdLastDeviceAdd();
            int ultimo = Integer.parseInt(lastid);
            for(int i = n; i < ultimo + 1; i++)
            {
                String sd = Integer.toString(i);
                SmartDevices smartDevices1 = map.get(sd);
                assertEquals(smartDevices1,smartDevices);
            }
        }
        catch (ValorNegativoException e)
        {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void testAddDivisao()
    {
        try {
            List<String> listDiv = new ArrayList<>();
            listDiv.add("Quarto do Ze");
            listDiv.add("Garagem dos arrumos");
            listDiv.add("Varanda");
            Pessoa pessoa = app.getPessoa(758618872);
            app.changeProprietario("112",758618872);
            Predicate<House> p = h->h.getProprietario().equals(pessoa);
            app.addDivisoes(p,listDiv);
            Map<String,House> casas = app.getCasas();
            House h1 = casas.get("111");
            House h2 = casas.get("112");
            assertTrue(h1.hasDivisao("Quarto do Ze"));
            assertTrue(h1.hasDivisao("Garagem dos arrumos"));
            assertTrue(h1.hasDivisao("Varanda"));
            assertTrue(h2.hasDivisao("Quarto do Ze"));
            assertTrue(h2.hasDivisao("Garagem dos arrumos"));
            assertTrue(h2.hasDivisao("Varanda"));
            List<String> listcasas = app.getLocalidadeCasas(p);
            assertEquals(listcasas.size(),2);
            assertTrue(listcasas.contains("111"));
            assertTrue(listcasas.contains("112"));
            app.changeProprietario("112",586185742);
            listcasas = app.getLocalidadeCasas(p);
            assertEquals(listcasas.size(),1);
            assertTrue(listcasas.contains("111"));
            Map<String,List<String>> div = app.getDivisoesCasas(p);
            List<String> divisoes = div.get("111");
            Set<String> keys = div.keySet();
            assertEquals(keys.size(),1);
            assertTrue(divisoes.contains("Varanda"));
            assertTrue(divisoes.contains("Casa de Banho"));
            assertTrue(divisoes.contains("Escritorio"));
        }
        catch (PessoaNotExistException | CasaNotExistException |NullPointerException | DivisaoExistException e)
        {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void testDevices()
    {
        Predicate<SmartDevices> smartDevicesPredicate = Objects::nonNull;
        app.setDevicesOnOff(smartDevicesPredicate,false);
        Map<String,SmartDevices> smartDevicesMap = app.getDevices();
        for(SmartDevices smartDevices : smartDevicesMap.values())
        {
            assertFalse(smartDevices.isOn());
        }
        Map<String,House> casas = app.getCasas();
        for(House casa : casas.values())
        {
            Map<String,SmartDevices> dev = casa.getDevices();
            for(SmartDevices smartDevices1 : dev.values())
                assertFalse(smartDevices1.isOn());
        }
        app.setDevicesOnOff(smartDevicesPredicate,true);
        for(SmartDevices smartDevices : smartDevicesMap.values())
        {
            assertTrue(smartDevices.isOn());
        }
        smartDevicesPredicate = d -> d instanceof SmartBulb;
        app.setDevicesOnOff(smartDevicesPredicate,false);
        smartDevicesMap = app.getDevices();
        for(SmartDevices smartDevices : smartDevicesMap.values())
        {
            if(smartDevices instanceof SmartBulb)
                assertFalse(smartDevices.isOn());
        }
        app.setDevicesOnOff(smartDevicesPredicate,true);
    }

    @Test
    public void testFaturasTotal()
    {
        List<Fatura> faturas = app.getFaturasTotal(LocalDate.now(),LocalDate.now().plusDays(2));
        assertEquals(faturas.size(),0);
        faturas = app.getFaturasTotal(LocalDate.now(),LocalDate.now().plusDays(31));
        assertEquals(faturas.size(),200);
    }
}
