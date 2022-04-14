import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestSmartCamera {

    private SmartCamera smartCamera1;
    private SmartCamera smartCamera2;
    private SmartCamera smartCamera3;
    private SmartCamera smartCamera4;
    private SmartCamera smartCamera5;
    private SmartCamera smartCamera6;

    @BeforeEach
    public void atribuicao() {
        this.smartCamera1 = new SmartCamera("1", true, 10, 1920, 1080, 10);

        this.smartCamera2 = new SmartCamera("2", false, 20, 1920, 1080, 40);

        this.smartCamera3 = new SmartCamera("3", true, 10, 1640, 1440, 30);

        this.smartCamera4 = new SmartCamera("4", false, 5, 1280, 720, 55);

        this.smartCamera5 = new SmartCamera("5", true, 10, 1920, 1080, 70);

        this.smartCamera6 = new SmartCamera("6", false, 10, 580, 450, 12);

    }

    @Test
    public void testGetConsumo() {
        assertEquals(10, smartCamera1.getConsumoDiario());
        assertEquals(20, smartCamera2.getConsumoDiario());
        assertEquals(10, smartCamera3.getConsumoDiario());
        assertEquals(5, smartCamera4.getConsumoDiario());
        assertEquals(10, smartCamera5.getConsumoDiario());
        assertEquals(10, smartCamera6.getConsumoDiario());
    }

    @Test
    public void testGetResolucao(){
        assertEquals(1920, smartCamera1.getResolucaoX());
        assertEquals(1080, smartCamera1.getResolucaoY());
        assertEquals(1920, smartCamera2.getResolucaoX());
        assertEquals(1080, smartCamera2.getResolucaoY());
        assertEquals(1640, smartCamera3.getResolucaoX());
        assertEquals(1440, smartCamera3.getResolucaoY());
        assertEquals(1280, smartCamera4.getResolucaoX());
        assertEquals(720, smartCamera4.getResolucaoY());
        assertEquals(1920, smartCamera5.getResolucaoX());
        assertEquals(1080, smartCamera5.getResolucaoY());
        assertEquals(580, smartCamera6.getResolucaoX());
        assertEquals(450, smartCamera6.getResolucaoY());
    }

    @Test
    public void testGetTamanho(){
        assertEquals(10, smartCamera1.getTamanho());
        assertEquals(40, smartCamera2.getTamanho());
        assertEquals(30, smartCamera3.getTamanho());
        assertEquals(55, smartCamera4.getTamanho());
        assertEquals(70, smartCamera5.getTamanho());
        assertEquals(12, smartCamera6.getTamanho());
    }

    @Test
    public void testSetConsumo(){
        smartCamera1.setConsumoDiario(10);
        assertEquals(10, smartCamera1.getConsumoDiario());
        smartCamera1.setConsumoDiario(12);
        assertEquals(12, smartCamera1.getConsumoDiario());
        smartCamera1.setConsumoDiario(10);
        assertEquals(10, smartCamera1.getConsumoDiario());
    }

    @Test
    public void testSetResolucao(){
        smartCamera1.setResolucaoX(1920);
        smartCamera1.setResolucaoY(1080);
        assertEquals(1920, smartCamera1.getResolucaoX());
        assertEquals(1080, smartCamera1.getResolucaoY());
        smartCamera1.setResolucaoX(0);
        smartCamera1.setResolucaoY(0);
        assertNotEquals(1920, smartCamera1.getResolucaoX());
        assertNotEquals(1080, smartCamera1.getResolucaoY());
        smartCamera1.setResolucaoX(1920);
        smartCamera1.setResolucaoY(1080);
        assertEquals(1920, smartCamera1.getResolucaoX());
        assertEquals(1080, smartCamera1.getResolucaoY());
    }

    @Test
    public void testSetTamanho(){
        assertEquals(10,smartCamera1.getTamanho());
        smartCamera1.setTamanho(5);
        assertEquals(5,smartCamera1.getTamanho());
        smartCamera1.setTamanho(10);
        assertEquals(10,smartCamera1.getTamanho());
    }

    @Test
    public void testEquals(){
    assertNotEquals(this.smartCamera1,this.smartCamera2);
    SmartCamera igual = new SmartCamera("1", true, 10, 1920, 1080, 10);
    assertEquals(this.smartCamera1,igual);
    }

    @Test
    public void testClone(){
        SmartCamera clonada = smartCamera1.clone();
        assertNotEquals(clonada,smartCamera2);
        assertEquals(clonada,smartCamera1);
    }

    @Test
    public void testConsumo(){
    int dias = 31;
        assertEquals(this.smartCamera1.calculaConsumo(dias),20736310,"Erro Calculo Consumo smartcamera1");
        assertEquals(this.smartCamera2.calculaConsumo(dias),0,"Erro Calculo Consumo smartcamera2");
        assertEquals(this.smartCamera3.calculaConsumo(dias),70848310,"Erro Calculo Consumo smartcamera3");
        assertEquals(this.smartCamera4.calculaConsumo(dias),0,"Erro Calculo Consumo smartcamera4");
        assertEquals(this.smartCamera5.calculaConsumo(dias),145152310,"Erro Calculo Consumo smartcamera5");
        assertEquals(this.smartCamera6.calculaConsumo(dias),0,"Erro Calculo Consumo smartcamera6");
    }
}

