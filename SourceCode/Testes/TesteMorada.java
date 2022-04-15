import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TesteMorada {
    private Morada m1;
    private Morada m2;
    private Morada m3;
    private Morada m4;
    private Morada m5;
    private Morada m6;

    @BeforeEach
    public void atribui()
    {
        m1 = new Morada("Rua Lugar das Camélias",62);
        m2 = new Morada("Rua Fernando Pessoa",15);
        m3 = new Morada("Rua da Praia",31);
    }

    @Test
    public void testGetters(){
        assertEquals("Rua Lugar das Camélias",m1.getLocalidade());
        assertEquals(62,m1.getPorta());
        assertEquals("Rua Fernando Pessoa",m2.getLocalidade());
        assertEquals(15,m2.getPorta());
        assertEquals("Rua da Praia",m3.getLocalidade());
        assertEquals(31,m3.getPorta());
    }

    @Test
    public void testSetters(){
        m1.setLocalidade("Rua de Camões");
        m1.setPorta(19);
        assertNotEquals("Rua Lugar das Camélias",m1.getLocalidade());
        assertNotEquals(62,m1.getPorta());
        assertEquals("Rua de Camões",m1.getLocalidade());
        assertEquals(19,m1.getPorta());
    }

    @Test
    public void testClone(){
        Morada clonada = new Morada(this.m1);
        Morada clonada2 = new Morada(this.m2);
        Morada clonada3 = new Morada(this.m3);
        assertEquals(this.m1,clonada,"Erro clone 1");
        assertEquals(this.m2,clonada2,"Erro clone 2");
        assertEquals(this.m3,clonada3,"Erro clone 3");
    }

    @Test
    public void testEquals(){
        Morada morada = new Morada(this.m1);
        Morada clonada = this.m2.clone();
        assertEquals(morada,this.m1,"Equals 1 errado");
        assertEquals(clonada,this.m2,"Equals 2 errado");
        this.m2.setPorta(14);
        assertNotEquals(clonada, this.m1, "Not Equals Errado");
        assertNotEquals(this.m1,this.m2,"Not Equals 2 Errado");
    }
}
