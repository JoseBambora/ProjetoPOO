
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class TestSmartBulb {
    private SmartBulb sb1;
    private SmartBulb sb2;
    private SmartBulb sb3;
    private SmartBulb sb4;
    private SmartBulb sb5;
    private SmartBulb sb6;
    @Test
    public void testGetTone() {
        try {
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
            sb6 = new SmartBulb("6", false,20);
            assertEquals(SmartBulb.NEUTRAL, sb6.getTone());
        }
        catch (ValorNegativoException e)
        {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void testGetDimensao() {
        try {
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
            sb6 = new SmartBulb("6", false,29);
            assertEquals(0, sb6.getDimensao());
        }
        catch (ValorNegativoException e)
        {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void testGetConsumoDiario() {
        try {
            sb1  = new SmartBulb("b1", true, 1,4, 4);
            assertEquals(4, sb1.getConsumo());
            sb2 = new SmartBulb("4", false, 0,5, 9);;
            assertEquals(9, sb2.getConsumo());
            sb3 = new SmartBulb("5", true,2, 6, 10);
            assertEquals(10, sb3.getConsumo());
            sb4 =  new SmartBulb("6", false,1, 3, 5);
            assertEquals(5, sb4.getConsumo());
            sb5 = new SmartBulb();
            assertEquals(0, sb5.getConsumo());
            sb6 = new SmartBulb("6", false,0);
            assertEquals(0, sb6.getConsumo());
        }
        catch (ValorNegativoException e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testSetTone() {
        try {
            sb1  = new SmartBulb("b1", true, 1,4, 4);
            sb1.setTone(2);
            assertEquals(SmartBulb.WARM, sb1.getTone());
            sb1.setTone(10);
            assertEquals(SmartBulb.WARM, sb1.getTone());
            sb1.setTone(0);
            assertEquals(SmartBulb.COLD, sb1.getTone());
        }
        catch (ValorNegativoException e)
        {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void testSetDimensao() {
        try {

            sb1  = new SmartBulb("b1", true, 1,4, 4);
            sb1.setDimensao(2);
            assertEquals(2, sb1.getDimensao());
            sb1.setDimensao(10);
            assertEquals(10, sb1.getDimensao());
            sb1.setDimensao(20);
            assertEquals(20, sb1.getDimensao());
        }
        catch (ValorNegativoException e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testSetConsumoDiario() {
        try {
            sb1  = new SmartBulb("b1", true, 1,4, 4);
            sb1.setConsumo(2);
            assertEquals(2, sb1.getConsumo());
            sb1.setConsumo(10);
            assertEquals(10, sb1.getConsumo());
            sb1.setConsumo(10);
            assertEquals(10, sb1.getConsumo());
        }
        catch (ValorNegativoException e)
        {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void testEquals() {
        try {
            sb1 = new SmartBulb("b1", true, 1, 4, 4);
            sb2 = new SmartBulb("b2", true, 2, 4, 4);
            sb3 = new SmartBulb("b3", true, 4, 8, 6);
            this.sb1.setTone(2);
            assertEquals(this.sb1, this.sb2);
            assertNotEquals(sb3, this.sb2);
        }
        catch (ValorNegativoException e)
        {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void testClone()
    {
        try {

            sb1  = new SmartBulb("b1", true, 1,4, 4);
            sb2  = new SmartBulb("b2", true, 0,9, 10);
            SmartBulb sb7 = this.sb1.clone();
            assertEquals(sb7,this.sb1);
            assertNotEquals(sb7,this.sb2);
            SmartBulb sb8 = this.sb1;
            assertEquals(sb8,sb1);
        }
        catch (ValorNegativoException e)
        {
            System.out.println(e.getMessage());
        }
    }
    @Test
    public void testConsumo() {
        try {
            int dias = 31;
            sb1  = new SmartBulb("b1", true, 1,4, 4);
            assertEquals(this.sb1.calculaConsumo(dias),372,"Erro Calculo Consumo sb1");
            sb2  = new SmartBulb("b2", true, 3,5, 4);
            assertEquals(this.sb2.calculaConsumo(dias),589,"Erro Calculo Consumo sb2");
            sb3  = new SmartBulb("b3", true, 1,10, 4);
            assertEquals(this.sb3.calculaConsumo(dias),744,"Erro Calculo Consumo sb3");
            sb4  = new SmartBulb("b4", true, 10,9, 4);
            assertEquals(this.sb4.calculaConsumo(dias),961,"Erro Calculo Consumo sb4");
            sb5  = new SmartBulb();
            assertEquals(this.sb5.calculaConsumo(dias),0,"Erro Calculo Consumo sb5");
            sb6  = new SmartBulb("b6", true,0);
            assertEquals(this.sb6.calculaConsumo(dias),0,"Erro Calculo Consumo sb6");
        }
        catch (ValorNegativoException e)
        {
            System.out.println(e.getMessage());
        }
    }


}




