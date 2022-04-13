import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TestSmartSpeaker
{
    private SmartSpeaker smartSpeaker1;
    private SmartSpeaker smartSpeaker2;
    private SmartSpeaker smartSpeaker3;
    private SmartSpeaker smartSpeaker4;
    private SmartSpeaker smartSpeaker5;
    private SmartSpeaker smartSpeaker6;
    @BeforeEach
    public void atribui()
    {
        this.smartSpeaker1 = new SmartSpeaker();
        this.smartSpeaker2 = new SmartSpeaker("1",true);
        this.smartSpeaker3 = new SmartSpeaker("2",false,10,30,"Samsung",10);
        this.smartSpeaker4 = new SmartSpeaker("3",true,-2,40,"LG",30);
        this.smartSpeaker5 = new SmartSpeaker("4",true,30,1,"Sony",5);
        this.smartSpeaker6 = new SmartSpeaker("4",true,30,1,"Sony",5);
    }
    @Test
    public void testGetters()
    {
        assertTrue(this.smartSpeaker1.getCanal() == 0  && this.smartSpeaker2.getVolume() == 0, "Construtor 1 Errado");
        assertTrue(this.smartSpeaker2.getCanal() == 0  && this.smartSpeaker2.getVolume() == 0, "Construtor 2 Errado");
        assertTrue(this.smartSpeaker3.getCanal() == 30 && this.smartSpeaker3.getVolume() == 10 && this.smartSpeaker3.getMarca().equals("Samsung") && this.smartSpeaker3.getConsumoDiario() == 10, "Construtor 3 Errado");
        assertTrue(this.smartSpeaker4.getCanal() == 40 && this.smartSpeaker4.getVolume() == 0  && this.smartSpeaker4.getMarca().equals("LG")      && this.smartSpeaker4.getConsumoDiario() == 30, "Construtor 4 Errado");
        assertTrue(this.smartSpeaker5.getCanal() == 1  && this.smartSpeaker5.getVolume() == 20 && this.smartSpeaker5.getMarca().equals("Sony")    && this.smartSpeaker5.getConsumoDiario() == 5  , "Construtor 5 Errado");
    }
    @Test
    public void testSetters()
    {
        this.smartSpeaker1.setCanal(10);
        this.smartSpeaker1.setVolume(19);
        this.smartSpeaker2.setCanal(2);
        this.smartSpeaker2.setVolume(30);
        this.smartSpeaker3.setMarca("LG");
        this.smartSpeaker3.setConsumoDiario(9);
        this.smartSpeaker4.setMarca("Sony");
        this.smartSpeaker4.setConsumoDiario(4);
        this.smartSpeaker5.setMarca("Samsung");
        this.smartSpeaker5.setConsumoDiario(19);
        assertTrue(this.smartSpeaker1.getCanal() == 10 && this.smartSpeaker1.getVolume() == 19, "Construtor 1 Errado");
        assertTrue(this.smartSpeaker2.getCanal() == 2  && this.smartSpeaker2.getVolume() == 20, "Construtor 2 Errado");
        assertTrue(this.smartSpeaker3.getCanal() == 30 && this.smartSpeaker3.getVolume() == 10 && this.smartSpeaker3.getMarca().equals("LG")      && this.smartSpeaker3.getConsumoDiario() == 9 , "Construtor 3 Errado");
        assertTrue(this.smartSpeaker4.getCanal() == 40 && this.smartSpeaker4.getVolume() == 0  && this.smartSpeaker4.getMarca().equals("Sony")    && this.smartSpeaker4.getConsumoDiario() == 4 , "Construtor 4 Errado");
        assertTrue(this.smartSpeaker5.getCanal() == 1  && this.smartSpeaker5.getVolume() == 20 && this.smartSpeaker5.getMarca().equals("Samsung") && this.smartSpeaker5.getConsumoDiario() == 19, "Construtor 5 Errado");

    }
    @Test
    public void testEquals()
    {
        assertEquals(this.smartSpeaker6, this.smartSpeaker5);
        this.smartSpeaker6.setMarca("Samsung");
        assertNotEquals(this.smartSpeaker6, this.smartSpeaker5);
        assertNotEquals(this.smartSpeaker5, this.smartSpeaker3);
        assertEquals(this.smartSpeaker1,this.smartSpeaker2);
    }
    @Test
    public void testVolumeUp()
    {
        this.smartSpeaker6.setVolume(10);
        assertEquals(10, this.smartSpeaker6.getVolume(), "VolumeUp teste 1 errado");
        this.smartSpeaker6.volumeUp();
        assertEquals(this.smartSpeaker6.getVolume(), 11, "VolumeUp teste 2 errado");
        this.smartSpeaker6.volumeUp(5);
        assertEquals(this.smartSpeaker6.getVolume(), 16, "VolumeUp teste 3 errado");
    }
    @Test
    public void testVolumeDown()
    {
        int volume = this.smartSpeaker6.getVolume();
        this.smartSpeaker6.setVolume(0);
        assertEquals(0, this.smartSpeaker6.getVolume(), "VolumeDown teste 1 errado");
        this.smartSpeaker6.setVolume(volume);
        this.smartSpeaker6.volumeDown();
        assertEquals(this.smartSpeaker6.getVolume(), volume - 1, "VolumeDown teste 2 errado");
        this.smartSpeaker6.volumeDown(10);
        assertEquals(this.smartSpeaker6.getVolume(), volume - 11, "VolumeDown teste 3 errado");
    }
    @Test
    public void testClone()
    {
        SmartSpeaker smartSpeaker7 = this.smartSpeaker6.clone();
        assertEquals(smartSpeaker7,this.smartSpeaker6);
        assertEquals(smartSpeaker7,this.smartSpeaker5);
        smartSpeaker7 = this.smartSpeaker1;
        assertEquals(smartSpeaker7,smartSpeaker1);
        assertNotEquals(smartSpeaker7,this.smartSpeaker6);
    }
    @Test
    public void testCalculaConsumo()
    {
        int dias = 31;
        assertEquals(this.smartSpeaker1.calculaConsumo(dias),0,"Erro Calculo Consumo 1");
        assertEquals(this.smartSpeaker2.calculaConsumo(dias),0,"Erro Calculo Consumo 2");
        assertEquals(this.smartSpeaker3.calculaConsumo(dias),0,"Erro Calculo Consumo 3");
        assertEquals(this.smartSpeaker4.calculaConsumo(dias),930,"Erro Calculo Consumo 4");
        assertEquals(this.smartSpeaker5.calculaConsumo(dias),175,"Erro Calculo Consumo 5");
    }
}
