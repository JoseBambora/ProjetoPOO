
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TestSmartBulb {
    SmartBulb sb1;
    SmartBulb sb2;
    SmartBulb sb3;
    SmartBulb sb4;
    SmartBulb sb5;
    SmartBulb sb6;
    @BeforeEach

    @Test
    public void testGetTone() {
        sb1  = new SmartBulb("1", true, 1,4, 4);
        assertEquals(SmartBulb.NEUTRAL, sb1.getTone());
        sb2 = new SmartBulb("2", false, 0,5, 9);;
        assertEquals(SmartBulb.COLD, sb2.getTone());
        sb3 = new SmartBulb("3", true,2, 6, 10);
        assertEquals(SmartBulb.WARM, sb3.getTone());
        sb4 =  new SmartBulb("4", false,1, 3, 5);
        assertEquals(SmartBulb.NEUTRAL, sb4.getTone());
        sb5 = new SmartBulb();
        assertEquals(SmartBulb.NEUTRAL, sb5.getTone());
        sb6 = new SmartBulb("6", false);
        assertEquals(SmartBulb.NEUTRAL, sb6.getTone());
    }
    @Test
    public void testGetDimensao() {
        sb1  = new SmartBulb("5", true, 1,4, 4);
        assertEquals(4, sb1.getDimensao());
        sb2 = new SmartBulb("6", false, 0,5, 9);;
        assertEquals(5, sb2.getDimensao());
        sb3 = new SmartBulb("5", true,2, 6, 10);
        assertEquals(6, sb3.getDimensao());
        sb4 =  new SmartBulb("6", false,1, 3, 5);
        assertEquals(3, sb4.getDimensao());
        sb5 = new SmartBulb();
        assertEquals(0, sb5.getDimensao());
        sb6 = new SmartBulb("6", false);
        assertEquals(0, sb6.getDimensao());
    }
    @Test
    public void testGetConsumoDiario() {
        sb1  = new SmartBulb("b1", true, 1,4, 4);
        assertEquals(4, sb1.getConsumoDiario());
        sb2 = new SmartBulb("4", false, 0,5, 9);;
        assertEquals(9, sb2.getConsumoDiario());
        sb3 = new SmartBulb("5", true,2, 6, 10);
        assertEquals(10, sb3.getConsumoDiario());
        sb4 =  new SmartBulb("6", false,1, 3, 5);
        assertEquals(5, sb4.getConsumoDiario());
        sb5 = new SmartBulb();
        assertEquals(0, sb5.getConsumoDiario());
        sb6 = new SmartBulb("6", false);
        assertEquals(0, sb6.getConsumoDiario());
    }

    @Test
    public void testSetTone() {
        sb1  = new SmartBulb("b1", true, 1,4, 4);
        sb1.setTone(2);
        assertEquals(SmartBulb.WARM, sb1.getTone());
        sb1.setTone(10);
        assertEquals(SmartBulb.WARM, sb1.getTone());
        sb1.setTone(-10);
        assertEquals(SmartBulb.COLD, sb1.getTone());
    }
    @Test
    public void testSetDimensao() {
        sb1  = new SmartBulb("b1", true, 1,4, 4);
        sb1.setDimensao(2);
        assertEquals(2, sb1.getDimensao());
        sb1.setDimensao(10);
        assertEquals(10, sb1.getDimensao());
        sb1.setDimensao(-10);
        assertEquals(10, sb1.getDimensao());
    }

    @Test
    public void testSetConsumoDiario() {
        sb1  = new SmartBulb("b1", true, 1,4, 4);
        sb1.setConsumoDiario(2);
        assertEquals(2, sb1.getConsumoDiario());
        sb1.setConsumoDiario(10);
        assertEquals(10, sb1.getConsumoDiario());
        sb1.setConsumoDiario(-10);
        assertEquals(10, sb1.getConsumoDiario());
    }
    @Test
    public void testEquals() {
        sb1 = new SmartBulb("b1", true, 1, 4, 4);
        sb2 = new SmartBulb("b2", true, 2, 4, 4);
        sb3 = new SmartBulb("b3", true, 4, 8, 6);
        this.sb1.setTone(2);
        assertEquals(this.sb1, this.sb2);
        assertNotEquals(sb3, this.sb2);
    }
    @Test
    public void testClone()
    {
        sb1  = new SmartBulb("b1", true, 1,4, 4);
        sb2  = new SmartBulb("b2", true, 0,9, 10);
        SmartBulb sb7 = this.sb1.clone();
        assertEquals(sb7,this.sb1);
        assertNotEquals(sb7,this.sb2);
        SmartBulb sb8 = this.sb1;
        assertEquals(sb8,sb1);
    }
    @Test
    public void testConsumo() {
        int dias = 31;
        sb1  = new SmartBulb("b1", true, 1,4, 4);
        assertEquals(this.sb1.calculaConsumo(dias),516,"Erro Calculo Consumo sb1");
        sb2  = new SmartBulb("b2", true, 3,5, 4);
        assertEquals(this.sb2.calculaConsumo(dias),1880,"Erro Calculo Consumo sb2");
        sb3  = new SmartBulb("b3", true, 1,10, 4);
        assertEquals(this.sb3.calculaConsumo(dias),1260,"Erro Calculo Consumo sb3");
        sb4  = new SmartBulb("b4", true, 10,9, 4);
        assertEquals(this.sb4.calculaConsumo(dias),11180,"Erro Calculo Consumo sb4");
        sb5  = new SmartBulb();
        assertEquals(this.sb5.calculaConsumo(dias),20,"Erro Calculo Consumo sb5");
        sb6  = new SmartBulb("b6", true);
        assertEquals(this.sb6.calculaConsumo(dias),20,"Erro Calculo Consumo sb6");
    }


}




