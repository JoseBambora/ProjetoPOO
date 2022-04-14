import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestSmartDevices
{
    private SmartBulb smartBulb1;
    private SmartBulb smartBulb2;
    private SmartSpeaker smartSpeaker1;
    private SmartSpeaker smartSpeaker2;
    private SmartCamera smartCamera1;
    private SmartCamera smartCamera2;
    @BeforeEach
    public void atribui()
    {
        this.smartBulb1 = new SmartBulb("1",true,1,10,5); 
        this.smartBulb2 = new SmartBulb("2",false,0,20,10); 
        this.smartSpeaker1 = new SmartSpeaker("3",false,30,6,"LG",10);
        this.smartSpeaker2 = new SmartSpeaker("4",true,10,10,"LG",20);
        this.smartCamera1 = new SmartCamera("5",true,10,1920,1080,10);
        this.smartCamera2 = new SmartCamera("6",false,20,1920,1080,40);
    }
    @Test
    public void testEquals()
    {
        SmartSpeaker smartSpeaker = new SmartSpeaker(smartSpeaker1);
        assertEquals(smartSpeaker,this.smartSpeaker1,"Smart Speaker 1 errado");
        assertNotEquals(smartSpeaker,this.smartSpeaker2,"Smart Speaker 2 errado");
        SmartBulb sb = new SmartBulb (smartBulb1);
        assertEquals(sb, this.smartBulb1,"Smart Bulb 1 errado");
        assertNotEquals(sb,this.smartBulb2,"Smart Bulb 2 errado");

    }
    @Test
    public void testGetId()
    {
        assertEquals("1",this.smartBulb1.getId());
        assertEquals("2",this.smartBulb2.getId());
        assertEquals("3",this.smartSpeaker1.getId());
        assertEquals("4",this.smartSpeaker2.getId());
        assertEquals("5",this.smartCamera1.getId());
        assertEquals("6",this.smartCamera2.getId());
    }
    @Test
    public void testIsOn()
    {
        assertTrue (this.smartBulb1.isOn());
        assertFalse(this.smartBulb2.isOn());
        assertFalse(this.smartSpeaker1.isOn());
        assertTrue (this.smartSpeaker2.isOn());
        assertTrue (this.smartCamera1.isOn());
        assertFalse(this.smartCamera2.isOn());
    }
    @Test
    public void testTurns()
    {
        this.smartBulb1.turnOff();
        this.smartBulb2.turnOn();
        this.smartSpeaker1.turnOn();
        this.smartSpeaker2.turnOff();
        this.smartCamera1.turnOff();
        this.smartCamera2.turnOn();
        assertFalse(this.smartBulb1.isOn());
        assertTrue (this.smartBulb2.isOn());
        assertTrue (this.smartSpeaker1.isOn());
        assertFalse(this.smartSpeaker2.isOn());
        assertFalse(this.smartCamera1.isOn());
        assertTrue (this.smartCamera2.isOn());
    }
    @Test
    public void testSetId()
    {
        this.smartBulb1.setId("10");
        this.smartSpeaker1.setId("20");
        this.smartCamera1.setId("30");
        assertEquals(this.smartBulb1.getId(),"10","GetID smartBulb errado");
        assertEquals(this.smartSpeaker1.getId(),"20","GetID smartSpeaker errado");
        assertEquals(this.smartCamera1.getId(),"30","GetID smartCamera errado");
    }
}
