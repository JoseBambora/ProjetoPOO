
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;



public class TestPessoa
{
    private Pessoa p1;
    private Pessoa p2;
    private Pessoa p3;
    private Pessoa p4;
    private Pessoa p5;
    @BeforeEach
    public void atribui()
    {
        try
        {
            this.p1 = new Pessoa();
            this.p2 = new Pessoa("José"  ,12345);
            this.p3 = new Pessoa("Rita"  ,1234);
            this.p4 = new Pessoa("Miguel",123);
            this.p5 = new Pessoa("Marcelo" ,12);
        }
        catch (ValorNegativoException | NullPointerException e)
        {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void testGetters()
    {
        assertEquals(this.p1.getNome(),"","Erro nome p1");
        assertEquals(this.p1.getNIF(),0,"Erro NIF p1");
        assertEquals(this.p2.getNome(),"José","Erro nome p2");
        assertEquals(this.p2.getNIF(),12345,"Erro NIF p2");
        assertEquals(this.p3.getNome(),"Rita","Erro nome p3");
        assertEquals(this.p3.getNIF(),1234,"Erro NIF p3");
        assertEquals(this.p4.getNome(),"Miguel","Erro nome p4");
        assertEquals(this.p4.getNIF(),123,"Erro NIF p4");
        assertEquals(this.p5.getNome(),"Marcelo","Erro nome p5");
        assertEquals(this.p5.getNIF(),12,"Erro NIF p5");
    }
    @Test
    public void testSetters()
    {
        try {
            this.p1.setNIF(123456);
            this.p1.setNome("Cristiano");
            this.p2.setNIF(234567);
            this.p2.setNome("Ronaldo");
            assertEquals(this.p1.getNIF(),123456,"Erro set NIF");
            assertEquals(this.p1.getNome(),"Cristiano","Erro set NIF");
            assertEquals(this.p2.getNIF(),234567,"Erro set NIF");
            assertEquals(this.p2.getNome(),"Ronaldo","Erro set NIF");
        }
        catch (ValorNegativoException | NullPointerException e)
        {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void testEquals()
    {
        try {
            Pessoa p6 = new Pessoa();
            Pessoa p7 = new Pessoa("José",12345);
            assertEquals(this.p2,p7,"Equals 1 errado");
            assertEquals(this.p1,p6,"Equals 2 errado");
            p7.setNIF(123456);
            assertNotEquals(this.p2,p7,"Equals 3 errado");
        }
        catch (ValorNegativoException | NullPointerException e)
        {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void testHashCode()
    {
        assertEquals(0,this.p1.hashCode(),"HashCode 1 errado");
        assertEquals(12345,this.p2.hashCode(),"HashCode 2 errado");
        assertEquals(1234,this.p3.hashCode(),"HashCode 3 errado");
        assertEquals(123,this.p4.hashCode(),"HashCode 4 errado");
        assertEquals(12,this.p5.hashCode(),"HashCode 5 errado");
    }
    @Test
    public void testClone()
    {
        assertEquals(this.p1,this.p1.clone(),"Clone 1 errado");
        assertEquals(this.p2,this.p2.clone(),"Clone 2 errado");
        assertEquals(this.p3,this.p3.clone(),"Clone 3 errado");
        assertEquals(this.p4,this.p4.clone(),"Clone 4 errado");
        assertEquals(this.p5,this.p5.clone(),"Clone 5 errado");
    }
}
