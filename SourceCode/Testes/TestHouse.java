import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestFactory;

import java.util.List;
import java.util.Map;

public class TestHouse
{

    SmartBulb b1;
    SmartBulb b2;
    SmartBulb b3;
    SmartBulb b4;

    SmartSpeaker s1;
    SmartSpeaker s2;
    SmartSpeaker s3;
    SmartSpeaker s4;

    SmartCamera c1;
    SmartCamera c2;
    SmartCamera c3;
    SmartCamera c4;

    private Comerciante comerciante1;
    private Comerciante comerciante2;
    private Comerciante comerciante3;
    private Morada morada1;
    private Morada morada2;
    private Morada morada3;
    private Morada morada4;
    private Morada morada5;
    private Morada morada6;
    private Pessoa pessoa1;
    private Pessoa pessoa2;
    private Pessoa pessoa3;
    private House house1;
    private House house2;
    private House house3;
    private House house4;
    private House house5;
    private House house6;
    @BeforeEach
    public void atribui()
    {

        this.b1 = new SmartBulb("b1",true,2,12,20);
        this.b2 = new SmartBulb("b2",true,1,20,28);
        this.b3 = new SmartBulb("b3",true,0,16,23);
        this.b4 = new SmartBulb("b4",true,1,10,15);

        this.s1 = new SmartSpeaker("s1",true,9,30,"LG",20);
        this.s2 = new SmartSpeaker("s2",true,10,2,"LG",20);
        this.s3 = new SmartSpeaker("s3",true,16,6,"Samsung",10);
        this.s4 = new SmartSpeaker("s4",true,5,20,"Sony",30);

        this.c1 = new SmartCamera("c1",true,5,1920,1080,30);
        this.c2 = new SmartCamera("c2",true,10,1090,920,20);
        this.c3 = new SmartCamera("c3",true,15,920,600,15);
        this.c4 = new SmartCamera("c4",true,20,800,500,10);

        this.comerciante1 = new Comerciante("EDP",20);
        this.comerciante2 = new Comerciante("Eneba",15);
        this.comerciante3 = new Comerciante("Galp",17);
        this.morada1 = new Morada("Braga",20);
        this.morada2 = new Morada("Porto",15);
        this.morada3 = new Morada("Lisboa",17);
        this.morada4 = new Morada("Bragança",10);
        this.morada5 = new Morada("Algarve",5);
        this.morada6 = new Morada("Setubal",23);
        this.pessoa1 = new Pessoa("Jose",1234);
        this.pessoa2 = new Pessoa("Miguel",123);
        this.pessoa3 = new Pessoa("Rita",12);
        this.house1 = new House(this.pessoa1,this.comerciante1,this.morada1);
        this.house2 = new House(this.pessoa1,this.comerciante1,this.morada2);
        this.house3 = new House(this.pessoa2,this.comerciante2,this.morada3);
        this.house4 = new House(this.pessoa2,this.comerciante2,this.morada4);
        this.house5 = new House(this.pessoa3,this.comerciante3,this.morada5);
        this.house6 = new House(this.pessoa3,this.comerciante3,this.morada6);

        this.house1.addDevice("Sala",b1);
        this.house1.addDevice("Sala",b2);
        this.house1.addDevice("Quarto",c1);
        this.house1.addDevice("Cozinha",s2);
        this.house1.addDevice("Sala",c2);
        this.house1.addDevice("WC",b3);
        this.house1.addDevice("WC",s1);

        this.house2.addDevice("Sala",b4);
        this.house2.addDevice("Sala",b2);
        this.house2.addDevice("Quarto",c4);
        this.house2.addDevice("Cozinha",s1);
        this.house2.addDevice("Sala",c4);
        this.house2.addDevice("WC",b3);
        this.house2.addDevice("WC",s2);

        this.house3.addDevice("Sala",b4);
        this.house3.addDevice("Sala",b3);
        this.house3.addDevice("Quarto",c2);
        this.house3.addDevice("Cozinha",s2);
        this.house3.addDevice("Sala",c3);
        this.house3.addDevice("WC",b2);
        this.house3.addDevice("WC",s3);

        this.house4.addDevice("Sala",b3);
        this.house4.addDevice("Sala",b2);
        this.house4.addDevice("Quarto",c4);
        this.house4.addDevice("Cozinha",s1);
        this.house4.addDevice("Sala",c1);
        this.house4.addDevice("WC",b1);
        this.house4.addDevice("WC",s4);

        this.house5.addDevice("Sala",b3);
        this.house5.addDevice("Sala",b2);
        this.house5.addDevice("Quarto",c3);
        this.house5.addDevice("Cozinha",s1);
        this.house5.addDevice("Sala",c1);
        this.house5.addDevice("WC",b4);
        this.house5.addDevice("WC",s4);

        this.house6.addDevice("Sala",b1);
        this.house6.addDevice("Sala",b3);
        this.house6.addDevice("Quarto",c4);
        this.house6.addDevice("Cozinha",s1);
        this.house6.addDevice("Sala",c3);
        this.house6.addDevice("WC",b2);
        this.house6.addDevice("WC",s3);

    }
    @Test
    public void testGetters() {
        assertEquals(this.house1.getFornecedor(), this.comerciante1, "Erro equals 1");
        assertEquals(this.house2.getFornecedor(), this.comerciante1, "Erro equals 2");
        assertEquals(this.house3.getFornecedor(), this.comerciante2, "Erro equals 3");
        assertEquals(this.house4.getFornecedor(), this.comerciante2, "Erro equals 4");
        assertEquals(this.house5.getFornecedor(), this.comerciante3, "Erro equals 5");
        assertEquals(this.house6.getFornecedor(), this.comerciante3, "Erro equals 6");

        assertEquals(this.house1.getProprietario(), this.pessoa1, "Erro equals 7");
        assertEquals(this.house2.getProprietario(), this.pessoa1, "Erro equals 8");
        assertEquals(this.house3.getProprietario(), this.pessoa2, "Erro equals 9");
        assertEquals(this.house4.getProprietario(), this.pessoa2, "Erro equals 10");
        assertEquals(this.house5.getProprietario(), this.pessoa3, "Erro equals 11");
        assertEquals(this.house6.getProprietario(), this.pessoa3, "Erro equals 12");


        assertEquals(this.house1.getLocal(), this.morada1, "Erro equals 13");
        assertEquals(this.house2.getLocal(), this.morada2, "Erro equals 14");
        assertEquals(this.house3.getLocal(), this.morada3, "Erro equals 15");
        assertEquals(this.house4.getLocal(), this.morada4, "Erro equals 16");
        assertEquals(this.house5.getLocal(), this.morada5, "Erro equals 17");
        assertEquals(this.house6.getLocal(), this.morada6, "Erro equals 18");

        Map<String, List<String>> divisoes = this.house1.getDivisoes();
        Map<String, SmartDevices> devices = this.house1.getDevices();

        assertTrue(divisoes.containsKey("WC"));
        assertTrue(divisoes.containsKey("Sala"));
        assertTrue(divisoes.containsKey("Cozinha"));
        assertTrue(divisoes.containsKey("Quarto"));

        assertTrue(devices.containsKey("b1") && devices.get("b1").equals(this.b1));
        assertTrue(devices.containsKey("b2") && devices.get("b2").equals(this.b2));
        assertTrue(devices.containsKey("b3") && devices.get("b3").equals(this.b3));
        assertTrue(devices.containsKey("c1") && devices.get("c1").equals(this.c1));
        assertTrue(devices.containsKey("c2") && devices.get("c2").equals(this.c2));
        assertTrue(devices.containsKey("s1") && devices.get("s1").equals(this.s1));
        assertTrue(devices.containsKey("s2") && devices.get("s2").equals(this.s2));

        assertEquals(devices.size(),7);
        assertEquals(divisoes.size(),4);
    }
    @Test
    public void testComposicao()
    {
        this.pessoa1.setNIF(90);
        assertNotEquals(this.house1.getProprietario(),this.pessoa1, "Erro composição 1");
        assertNotEquals(this.house2.getProprietario(),this.pessoa1, "Erro composição 2");
        this.comerciante2.setNome("Repsol");
        assertNotEquals(this.house3.getFornecedor(),this.comerciante2, "Erro composição 3");
        assertNotEquals(this.house4.getFornecedor(),this.comerciante2, "Erro composição 4");
        this.morada3.setPorta(2000);
        assertNotEquals(this.house3.getLocal(),this.morada3, "Erro composição 5");
        Map<String, List<String>> divisoes = this.house1.getDivisoes();
        Map<String, SmartDevices> devices = this.house1.getDevices();
        this.b1.setTone(0);
        this.c2.setOn(false);
        this.s2.setMarca("Sony");
        assertNotEquals(devices.get("b1"), this.b1);
        assertEquals(devices.get("c2"), this.c2);
        assertNotEquals(devices.get("s2"), this.s2);
        devices.remove("b1");
        assertFalse(devices.containsKey("b1"));
        assertTrue(this.house1.getDevices().containsKey("b1"));
        divisoes.remove("Sala");
        assertFalse(divisoes.containsKey("Sala"));
        assertTrue(this.house1.getDivisoes().containsKey("Sala"));

        this.house3.setDeviceOff("b4");
        assertFalse(this.house3.getDevices().get("b4").isOn());
        assertTrue(this.house2.getDevices().get("b4").isOn());
    }
    @Test
    public void testSetters()
    {
        this.house1.setFornecedor(this.comerciante2);
        assertNotEquals(this.house1.getFornecedor(),this.comerciante1);
        assertEquals(this.house1.getFornecedor(),this.comerciante2);

        this.house3.setLocal(this.morada1);
        assertEquals(this.house3.getLocal(),this.morada1);
        assertNotEquals(this.house3.getLocal(),this.morada3);

        this.house5.setProprietario(this.pessoa1);
        assertNotEquals(this.house5.getProprietario(),this.pessoa3);
        assertEquals(this.house5.getProprietario(),this.pessoa1);

        Map<String, List<String>> divisoes = this.house1.getDivisoes();
        Map<String, SmartDevices> devices = this.house1.getDevices();

        divisoes.remove("Sala");
        divisoes.remove("Cozinha");
        devices.remove("b1");
        devices.remove("b2");
        devices.remove("c1");
        devices.remove("s4");
        devices.remove("s3");
        devices.remove("s2");

        this.house1.setDivisoes(divisoes);
        this.house1.setDevices(devices);

        Map<String, List<String>> divisoes2 = this.house1.getDivisoes();
        Map<String, SmartDevices> devices2 = this.house1.getDevices();

        assertEquals(devices2.size(),3);
        assertEquals(divisoes2.size(),2);

        assertTrue(divisoes2.containsKey("WC"));
        assertFalse(divisoes2.containsKey("Sala"));
        assertFalse(divisoes2.containsKey("Cozinha"));
        assertTrue(divisoes2.containsKey("Quarto"));

        assertFalse(devices.containsKey("b1") && devices.get("b1").equals(this.b1));
        assertFalse(devices.containsKey("b2") && devices.get("b2").equals(this.b2));
        assertTrue(devices.containsKey("b3") && devices.get("b3").equals(this.b3));
        assertFalse(devices.containsKey("c1") && devices.get("c1").equals(this.c1));
        assertTrue(devices.containsKey("c2") && devices.get("c2").equals(this.c2));
        assertTrue(devices.containsKey("s1") && devices.get("s1").equals(this.s1));
        assertFalse(devices.containsKey("s2") && devices.get("s2").equals(this.s2));
    }
    @Test
    public void testClone()
    {
        House house = this.house6.clone();
        assertEquals(house,this.house6);
        assertEquals(house.getFornecedor(),this.house6.getFornecedor());
        house.setFornecedor(this.comerciante1);
        assertNotEquals(house.getFornecedor(),this.house6.getFornecedor());
        house.setFornecedor(this.house6.getFornecedor());

        Map<String, List<String>> divisoes1 = this.house6.getDivisoes();
        Map<String, SmartDevices> devices1 = this.house6.getDevices();

        Map<String, List<String>> divisoes2 = house.getDivisoes();
        Map<String, SmartDevices> devices2 = house.getDevices();

        for(String key : divisoes1.keySet())
            assertTrue(divisoes2.containsKey(key));

        for(String key : divisoes1.keySet())
        {
            List<String> l1 = divisoes1.get(key);
            List<String> l2 = divisoes2.get(key);
            for(String s : l1)
                assertTrue(l2.contains(s));
        }

        for(String key : devices1.keySet())
            assertTrue(devices2.containsKey(key));

        for(SmartDevices smartDevices : devices1.values())
            assertEquals(smartDevices,devices2.get(smartDevices.getId()));
    }

    @Test
    public void testOnOff()
    {
        this.house4.setAllOff();
        Map<String, SmartDevices> devices = this.house4.getDevices();
        for(SmartDevices device : devices.values())
            assertFalse(device.isOn());
        this.house4.setAllOn();
        for(SmartDevices device : devices.values())
            assertFalse(device.isOn());
        devices = this.house4.getDevices();
        for(SmartDevices device : devices.values())
            assertTrue(device.isOn());

        this.house4.setDeviceOff("b3");
        this.house4.setDeviceOff("b4");
        this.house4.setDeviceOff("c4");
        this.house4.setDeviceOff("s1");
        this.house4.setDeviceOff("s2");
        devices = this.house4.getDevices();
        for(SmartDevices device : devices.values())
            if(device.getId().equals("b3") || device.getId().equals("b4") || device.getId().equals("c4") || device.getId().equals("s1") || device.getId().equals("s2"))
                assertFalse(device.isOn(),"device " + device.getId());
            else
                assertTrue(device.isOn());

        this.house4.setDeviceOn("b3");
        this.house4.setDeviceOn("b4");
        this.house4.setDeviceOn("c4");
        this.house4.setDeviceOn("s1");
        this.house4.setDeviceOn("s2");
        devices = this.house4.getDevices();
        for(SmartDevices device : devices.values())
                assertTrue(device.isOn());

        this.house4.setDivisaoOff("Sala");
        this.house4.setDivisaoOff("WC");
        devices = this.house4.getDevices();
        Map<String, List<String>> divisoes = this.house4.getDivisoes();
        for(SmartDevices device : devices.values())
            if(divisoes.get("Sala").contains(device.getId()) || divisoes.get("WC").contains(device.getId()))
                assertFalse(device.isOn(),"device " + device.getId());
            else
                assertTrue(device.isOn());

        this.house4.setDivisaoOn("Sala");
        this.house4.setDivisaoOn("WC");
        devices = this.house4.getDevices();
        for(SmartDevices device : devices.values())
                assertTrue(device.isOn());


        House house = this.house3.clone();
        house.setAllOff();
        devices = this.house3.getDevices();
        for(SmartDevices smartDevices : devices.values())
            assertTrue(smartDevices.isOn());

        devices = house.getDevices();
        for(SmartDevices smartDevices : devices.values())
            assertFalse(smartDevices.isOn());
    }

    @Test
    public void testCalculoConsumo()
    {
        this.house4.setAllOff();

        assertEquals(this.b1.calculaConsumo(1),56);
        assertEquals(this.b2.calculaConsumo(1),68);
        assertEquals(this.b3.calculaConsumo(1),39);
        assertEquals(this.b4.calculaConsumo(1),35);
        assertEquals(this.s1.calculaConsumo(1),29);
        assertEquals(this.s2.calculaConsumo(1),30);
        assertEquals(this.s3.calculaConsumo(1),26);
        assertEquals(this.s4.calculaConsumo(1),35);

        assertEquals(this.house1.getConsumo(1),319); // b1 + b2 + c1 + s2 + c2 + b3 + s1 = 319
        assertEquals(this.house2.getConsumo(3),667); // b4 + b2 + c4 + s1 + c4 + b3 + s2 = 667
        assertEquals(this.house3.getConsumo(6),1366); // b4 + b3 + c2 + s2 + c3 + b2 + s3 = 1366
        assertEquals(this.house4.getConsumo(9),0); // b3 + b2 + c4 + s1 + c1 + b1 + s4
        assertEquals(this.house5.getConsumo(12),2782); // b3 + b2 + c3 + s1 + c1 + b4 + s4 = 2782
        assertEquals(this.house6.getConsumo(15),3807); // b1 + b3 + c4 + s1 + c3 + b2 + s3 = 3807
    }
}
