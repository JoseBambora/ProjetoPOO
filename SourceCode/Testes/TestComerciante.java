import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TestComerciante
{
    private Comerciante comerciante1;
    private Comerciante comerciante2;
    private Comerciante comerciante3;
    private Comerciante comerciante4;
    private Comerciante comerciante5;
    private Comerciante comerciante6;
    private final List<Fatura> faturaList1 = new ArrayList<>(10);
    private final List<Fatura> faturaList2 = new ArrayList<>(10);
    private final List<Fatura> faturaList3 = new ArrayList<>(10);
    private final List<Fatura> faturaList4 = new ArrayList<>(10);
    private final List<Fatura> faturaList5 = new ArrayList<>(10);
    private final List<Fatura> faturaList6 = new ArrayList<>(10);
    private final String morada1 = "Braga";
    private final String morada2 = "Porto";
    private final String morada3 = "Lisboa";
    private final String morada4 = "Bragança";
    private final String morada5 = "Algarve";
    private final String morada6 = "Setubal";
    private Pessoa pessoa1;
    private Pessoa pessoa2;
    private Pessoa pessoa3;
    @BeforeEach
    public void atribui()
    {
        int imposto = 23;
        try {
            this.pessoa1 = new Pessoa("Jose", 1234);
            this.pessoa2 = new Pessoa("Miguel", 123);
            this.pessoa3 = new Pessoa("Rita", 12);
        }
        catch(ValorNegativoException | NullPointerException e)
        {
            System.out.println(e.getMessage());
        }
        this.comerciante1 = new Comerciante("EDP",new FormulaCalc1());
        this.comerciante2 = new Comerciante("Eneba",new FormulaCalc1());
        this.comerciante3 = new Comerciante("Galp",new FormulaCalc1());
        this.comerciante4 = new Comerciante("Repsol",new FormulaCalc2());
        this.comerciante5 = new Comerciante("GreenEnergy",new FormulaCalc2());
        this.comerciante6 = new Comerciante("BP",new FormulaCalc2());
        double preco = 0;
        for(int i = 0; i < 10; i++)
        {
            double consumo = i * 100 + 50;
            this.faturaList1.add(new Fatura(this.pessoa1,preco,imposto,consumo,LocalDate.now(),this.morada1));
            consumo = i * 75 + 50;
            this.faturaList2.add(new Fatura(this.pessoa2,preco,imposto,consumo,LocalDate.now(),this.morada2));
            consumo = i * 85 + 97;
            this.faturaList3.add(new Fatura(this.pessoa3,preco,imposto,consumo,LocalDate.now(),this.morada3));
            consumo = i * 90 + 65;
            this.faturaList4.add(new Fatura(this.pessoa1,preco,imposto,consumo,LocalDate.now(),this.morada4));
            consumo = i * 105 + 45;
            this.faturaList5.add(new Fatura(this.pessoa2,preco,imposto,consumo,LocalDate.now(),this.morada5));
            consumo = i * 80 + 100;
            this.faturaList6.add(new Fatura(this.pessoa3,preco,imposto,consumo,LocalDate.now(),this.morada6));
        }
        try {
            for(int i = 0; i < 10; i++)
            {
                this.comerciante1.addFatura(this.faturaList1.get(i),imposto);
                this.comerciante2.addFatura(this.faturaList2.get(i),imposto);
                this.comerciante3.addFatura(this.faturaList3.get(i),imposto);
                this.comerciante4.addFatura(this.faturaList4.get(i),imposto);
                this.comerciante5.addFatura(this.faturaList5.get(i),imposto);
                this.comerciante6.addFatura(this.faturaList6.get(i),imposto);
            }
        }
        catch (ValorNegativoException e)
        {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void testGetters()
    {
        assertEquals(this.comerciante1.getNome(), "EDP");
        assertEquals(this.comerciante2.getNome(), "Eneba");
        assertEquals(this.comerciante3.getNome(), "Galp");
        assertEquals(this.comerciante4.getNome(), "Repsol");
        assertEquals(this.comerciante5.getNome(), "GreenEnergy");
        assertEquals(this.comerciante6.getNome(), "BP");
        Comerciante aux = this.comerciante2.clone();
        Map<LocalDate, List<Fatura>> map1 = aux.getFaturasEmitidas();
        Map<LocalDate, List<Fatura>> map2 = this.comerciante2.getFaturasEmitidas();
        for(LocalDate key : map1.keySet())
        {
            assertTrue(map2.containsKey(key));
            List<Fatura> aux1 = map1.get(key);
            List<Fatura> aux2 = map2.get(key);
            for(Fatura fatura : aux1)
                assertTrue(aux2.contains(fatura));
        }
    }
    @Test
    public void testSetters()
    {
        this.comerciante1.setNome("Braga");
        this.comerciante1.setFormula(new FormulaCalc2());
        assertEquals(this.comerciante1.getNome(), "Braga");
        assertEquals(this.comerciante4.getFormula().calculaPreco(30,23), this.comerciante1.getFormula().calculaPreco(30,23));
        this.comerciante1.setFaturasEmitidas(this.comerciante2.getFaturasEmitidas());
        assertNotEquals(this.comerciante1,this.comerciante2);
        Map<LocalDate, List<Fatura>> map1 = this.comerciante1.getFaturasEmitidas();
        Map<LocalDate, List<Fatura>> map2 = this.comerciante2.getFaturasEmitidas();
        for(LocalDate key : map1.keySet())
        {
            assertTrue(map2.containsKey(key));
            List<Fatura> aux1 = map1.get(key);
            List<Fatura> aux2 = map2.get(key);
            for(Fatura fatura : aux1)
                assertTrue(aux2.contains(fatura));
        }

    }
    @Test
    public void testEqualsClone()
    {
        Comerciante aux = this.comerciante2.clone();
        assertEquals(aux,this.comerciante2);
        aux.setFormula(new FormulaCalc1());
        assertEquals(aux,this.comerciante2);
        aux.setNome("Nome");
        assertNotEquals(aux,this.comerciante2);
    }
    @Test
    public void testAdd()
    {

        assertEquals(this.comerciante1.getFaturasEmitidas().get(LocalDate.now()).get(0).getLocal()      , this.faturaList1.get(0).getLocal());
        assertEquals(this.comerciante2.getFaturasEmitidas().get(LocalDate.now()).get(1).getCliente()    , this.faturaList2.get(1).getCliente());
        assertEquals(this.comerciante3.getFaturasEmitidas().get(LocalDate.now()).get(2).getDataEmissao(), this.faturaList3.get(2).getDataEmissao());
        assertEquals(this.comerciante4.getFaturasEmitidas().get(LocalDate.now()).get(3).getPreco()      , this.faturaList4.get(3).getPreco());
        assertEquals(this.comerciante5.getFaturasEmitidas().get(LocalDate.now()).get(4).getConsumo()    , this.faturaList5.get(4).getConsumo());
        assertFalse(this.comerciante3.getFaturasEmitidas().containsKey(LocalDate.of(2000,2,2)));
        assertEquals(this.comerciante1.numFaturasEmitidas(),10);
        assertEquals(this.comerciante2.numFaturasEmitidas(),10);
        assertEquals(this.comerciante3.numFaturasEmitidas(),10);
        assertEquals(this.comerciante4.numFaturasEmitidas(),10);
        assertEquals(this.comerciante5.numFaturasEmitidas(),10);
        assertEquals(this.comerciante6.numFaturasEmitidas(),10);
        this.faturaList1.remove(0);
        this.faturaList2.remove(1);
        this.faturaList3.remove(2);
        this.faturaList4.remove(3);
        this.faturaList5.remove(4);
        this.faturaList6.remove(5);
        assertEquals(this.comerciante1.numFaturasEmitidas(),10);
        assertEquals(this.comerciante2.numFaturasEmitidas(),10);
        assertEquals(this.comerciante3.numFaturasEmitidas(),10);
        assertEquals(this.comerciante4.numFaturasEmitidas(),10);
        assertEquals(this.comerciante5.numFaturasEmitidas(),10);
        assertEquals(this.comerciante6.numFaturasEmitidas(),10);
        try {
            this.faturaList1.get(0).setLocal("");
            this.faturaList2.get(1).setCliente(new Pessoa());
            this.faturaList3.get(2).setDataEmissao(LocalDate.of(2000,2,2));
            this.faturaList4.get(3).setPreco(200);
            this.faturaList5.get(4).setConsumo(100000);
        }
        catch (ValorNegativoException | NullPointerException e)
        {
            System.out.println(e.getMessage());
        }
        assertNotEquals(this.comerciante1.getFaturasEmitidas().get(LocalDate.now()).get(0).getLocal()      , this.faturaList1.get(0).getLocal());
        assertNotEquals(this.comerciante2.getFaturasEmitidas().get(LocalDate.now()).get(1).getCliente()    , this.faturaList2.get(1).getCliente());
        assertNotEquals(this.comerciante3.getFaturasEmitidas().get(LocalDate.now()).get(2).getDataEmissao(), this.faturaList3.get(2).getDataEmissao());
        assertNotEquals(this.comerciante4.getFaturasEmitidas().get(LocalDate.now()).get(3).getPreco()      , this.faturaList4.get(3).getPreco());
        assertNotEquals(this.comerciante5.getFaturasEmitidas().get(LocalDate.now()).get(4).getConsumo()    , this.faturaList5.get(4).getConsumo());
        assertFalse(this.comerciante3.getFaturasEmitidas().containsKey(LocalDate.of(2000,2,2)));
    }
    @Test
    public void testGetFaturas()
    {
        List<Fatura> faturas = this.comerciante3.getFaturasEmitidas(LocalDate.of(2000,2,2),LocalDate.of(2003,2,2));
        assertEquals(0, faturas.size());
        faturas = this.comerciante3.getFaturasEmitidas(LocalDate.now(),LocalDate.of(2003,2,2));
        assertEquals(10, faturas.size());
        faturas = this.comerciante3.getFaturasEmitidas(LocalDate.now(),LocalDate.now());
        assertEquals(10, faturas.size());
        for(int i = 0; i < 10; i++)
            assertEquals(faturas.get(i), this.faturaList3.get(i));
        Fatura fatura = this.comerciante3.getMaiorFatura(LocalDate.now(),LocalDate.now());
        Fatura fatura1 = new Fatura();
        for(Fatura fatura2 : this.faturaList3)
            if(fatura2.getPreco() > fatura1.getPreco())
                fatura1 = fatura2;
        assertEquals(fatura,fatura1);
        fatura = this.comerciante3.getMaiorFatura(LocalDate.of(2000,2,2),LocalDate.of(2003,2,2));
        assertEquals(fatura,new Fatura());
    }
    @Test
    public void testLucro()
    {
        double lucro = 0;
        for(Fatura fatura: this.faturaList4)
            lucro+= fatura.getPreco();
        assertEquals( this.comerciante4.getLucro(),lucro);
        lucro = 0;
        for(Fatura fatura: this.faturaList5)
            lucro+= fatura.getPreco();
        assertEquals( this.comerciante5.getLucro(),lucro);
        lucro = 0;
        for(Fatura fatura: this.faturaList6)
            lucro+= fatura.getPreco();
        assertEquals( this.comerciante6.getLucro(),lucro);
    }
}
