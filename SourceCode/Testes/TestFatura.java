import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class TestFatura
{
    private Pessoa pessoa1 = new Pessoa("Jose",20);
    private Pessoa pessoa2 = new Pessoa("Rita",19);
    private Pessoa pessoa3 = new Pessoa("Miguel",18);
    private Morada morada1 = new Morada("Lisboa",12);
    private Morada morada2 = new Morada("Porto",13);
    private Morada morada3 = new Morada("Braga",14);
    private Fatura fatura1;
    private Fatura fatura2;
    private Fatura fatura3;
    private Fatura fatura4;
    private Fatura fatura5;
    private Fatura fatura6;
    @BeforeEach
    public void atribui()
    {
        this.fatura1 = new Fatura(this.pessoa1,20,10,LocalDate.of(2020,1,1),morada1);
        this.fatura2 = new Fatura(this.pessoa1,30,13,LocalDate.of(2021,1,1),morada1);
        this.fatura3 = new Fatura(this.pessoa2,40,15,LocalDate.of(2020,1,1),morada2);
        this.fatura4 = new Fatura(this.pessoa2,50,23,LocalDate.of(2021,1,1),morada2);
        this.fatura5 = new Fatura(this.pessoa3,60,26,LocalDate.of(2020,1,1),morada3);
        this.fatura6 = new Fatura(this.pessoa3,70,33,LocalDate.of(2021,1,1),morada3);
    }
    @Test
    public void testGetters()
    {
        this.morada2 = new Morada();
        this.morada3 = new Morada(this.morada1);
        this.pessoa2 = new Pessoa();
        this.pessoa3 = new Pessoa(this.pessoa1);

        assertEquals(this.fatura1.getCliente(),this.fatura2.getCliente(),"Get Cliente 1 Errado");
        assertEquals(this.fatura3.getCliente(),this.fatura4.getCliente(),"Get Cliente 2 Errado");
        assertEquals(this.fatura5.getCliente(),this.fatura6.getCliente(),"Get Cliente 3 Errado");
        assertNotEquals(this.fatura5.getCliente(),this.fatura2.getCliente(),"Get Cliente 4 Errado");

        assertEquals(this.fatura1.getPreco(),20,"Get Preco 1 Errado");
        assertEquals(this.fatura3.getPreco(),40,"Get Preco 2 Errado");
        assertNotEquals(this.fatura5.getPreco(),this.fatura6.getPreco(),"Get Preco 3 Errado");

        assertNotEquals(this.fatura1.getConsumo(),this.fatura2.getConsumo(),"Get Consumo 1 Errado");
        assertNotEquals(this.fatura3.getConsumo(),45,"Get Consumo 2 Errado");
        assertEquals(this.fatura6.getConsumo(),33,"Get Consumo 3 Errado");

        assertEquals(this.fatura1.getLocal(),this.fatura2.getLocal(),"Get Local 1 Errado");
        assertEquals(this.fatura3.getLocal(),this.fatura4.getLocal(),"Get Local 2 Errado");
        assertEquals(this.fatura5.getLocal(),this.fatura6.getLocal(),"Get Local 3 Errado");
        assertNotEquals(this.fatura5.getLocal(),this.fatura2.getLocal(),"Get Local 4 Errado");

        assertEquals(this.fatura1.getDataEmissao(),this.fatura3.getDataEmissao(),"Get DataEmissao 1 Errado");
        assertEquals(this.fatura3.getDataEmissao(),this.fatura5.getDataEmissao(),"Get DataEmissao 2 Errado");
        assertEquals(this.fatura2.getDataEmissao(),this.fatura6.getDataEmissao(),"Get DataEmissao 3 Errado");
        assertNotEquals(this.fatura2.getDataEmissao(),this.fatura5.getDataEmissao(),"Get DataEmissao 4 Errado");
    }
    @Test
    public void testSetters()
    {
        this.fatura1.setCliente(this.fatura3.getCliente());
        this.fatura4.setPreco(100);
        this.fatura4.setConsumo(60);
        this.fatura3.setConsumo(this.fatura4.getConsumo());
        this.fatura3.setPreco(this.fatura4.getPreco());
        this.fatura6.setLocal(this.morada2);
        this.fatura5.setDataEmissao(LocalDate.of(2020,5,5));
        this.fatura2.setDataEmissao(this.fatura5.getDataEmissao());
        this.fatura6.setCliente(this.fatura1.getCliente());
        this.morada2 = new Morada();
        assertEquals(this.fatura1.getCliente(),this.fatura3.getCliente(),"Set Cliente 1 Errado");
        assertEquals(this.fatura3.getCliente(),this.fatura4.getCliente(),"Set Cliente 2 Errado");
        assertEquals(this.fatura3.getCliente(),this.fatura6.getCliente(),"Set Cliente 3 Errado");

        assertEquals(this.fatura4.getPreco(),this.fatura3.getPreco(),"Set Preco 1 Errado");
        assertEquals(this.fatura4.getPreco(),100,"Set Preco 2 Errado");
        assertNotEquals(this.fatura3.getPreco(),this.fatura6.getPreco(),"Set Preco 3 Errado");

        assertEquals(this.fatura4.getConsumo(),this.fatura3.getConsumo(),"Set Consumo 1 Errado");
        assertEquals(this.fatura4.getConsumo(),60,"Set Consumo 2 Errado");
        assertEquals(this.fatura6.getConsumo(),33,"Set Consumo 3 Errado");

        assertEquals(this.fatura1.getLocal(),this.fatura2.getLocal(),"Set Local 1 Errado");
        assertEquals(this.fatura3.getLocal(),this.fatura6.getLocal(),"Set Local 2 Errado");
        assertNotEquals(this.fatura5.getLocal(),this.fatura6.getLocal(),"Set Local 3 Errado");

        assertEquals(this.fatura1.getDataEmissao(),this.fatura3.getDataEmissao(),"Set DataEmissao 1 Errado");
        assertEquals(this.fatura5.getDataEmissao(),this.fatura2.getDataEmissao(),"Set DataEmissao 2 Errado");
        assertEquals(this.fatura5.getDataEmissao(),LocalDate.of(2020,5,5),"Set DataEmissao 3 Errado");
        assertNotEquals(this.fatura3.getDataEmissao(),this.fatura5.getDataEmissao(),"Set DataEmissao 4 Errado");
    }
    @Test
    public void testClone()
    {
        Fatura fatura = new Fatura(this.fatura3);
        Fatura fatura7 = new Fatura(this.fatura4);
        Fatura fatura8 = this.fatura3.clone();
        assertEquals(fatura8,fatura, "Erro clone 1");
        assertEquals(fatura7,this.fatura4, "Erro clone 2");
        fatura7 = this.fatura4.clone();
        assertEquals(fatura7,this.fatura4, "Erro clone 3");
    }

    @Test
    public void testEquals()
    {
        Fatura fatura = new Fatura(this.fatura1);
        Fatura fatura7 = this.fatura4.clone();
        assertEquals(fatura,this.fatura1,"Equals 1 errado");
        assertEquals(fatura7,this.fatura4,"Equals 2 errado");
        this.fatura4.setCliente(this.fatura1.getCliente());
        assertNotEquals(fatura7,this.fatura4,"Equals 1 errado");
        assertNotEquals(this.fatura1,this.fatura4,"Equals 1 errado");

    }
    @Test
    public void testAgregação()
    {
        Fatura faturaT1 = new Fatura(pessoa1, 20, 10,LocalDate.of(2020,1,1),morada1);
        Fatura faturaT2 = new Fatura(pessoa2, 20, 10,LocalDate.of(2020,1,1),morada1);
        this.pessoa1.setNome("Alberto");
        this.pessoa2.setNome("Josefa");
        assertEquals(faturaT1.getCliente(), this.pessoa1, "Agregação Errada");
        assertEquals(faturaT2.getCliente(), this.pessoa2, "Agregação Errada");
    }}


