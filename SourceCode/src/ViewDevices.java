import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class ViewDevices {

    private final Scanner input;

    ViewDevices() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    //Geral
    public int getDevice() {
        System.out.println("Insira o tipo do dispositivo a adicionar:");
        System.out.println("1: SmartBulb");
        System.out.println("2: SmartCamera");
        System.out.println("3: SmartSpeaker");
        int option = input.nextInt();
        return option;
    }

    public boolean getOn() {
        System.out.println("Qual é o estado do dispositivo?");
        return input.nextBoolean();
    }

    public double getConsumo() {
        System.out.println("Qual é o consumo do dispostivo?");
        return input.nextDouble();
    }

    //Smart Camera
    public String getResolucao() {
        System.out.println("Qual é a resolução da câmara (formato HorizontalxVertical?");
        return input.next();
    }

    public double getTamanho() {
        System.out.println("Qual é o tamanho dos ficheiros gerados pela câmera?");
        return input.nextDouble();
    }

    //Smart Bulb
    public int getTone() {
        System.out.println("Qual é o tom da luz emitida pela lâmpada?");
        return input.nextInt();
    }

    public double getDimensao() {
        System.out.println("Qual é a dimensao da lâmpada?");
        return input.nextDouble();
    }

    //Smart Speaker
    public int getVolume() {
        System.out.println("Qual é o Volume do dispositivo?");
        return input.nextInt();
    }

    public String getCanal() {
        System.out.println("Qual é o Canal que está a transmitir no dispositivo?");
        return input.next();
    }

    public String getMarca() {
        System.out.println("Qual é a marca do dispositivo?");
        return input.next();
    }

    //Operações
    public void sucess() {
        System.out.println("Operação com dispositivos efetuada com sucesso.");
    }

    //Predicates
    public int predicates() {
        System.out.println("Algum critério de filtragem?");
        System.out.println("1 - Não");
        System.out.println("2 - Consumo Superior a um dado valor");
        System.out.println("3 - Consumo Inferiores a um dado valor");
        System.out.println("4 - Ligados");
        System.out.println("5 - Desligados");
        System.out.println("6 - SmartCameras");
        System.out.println("7 - SmartBulbs");
        System.out.println("8 - SmartSpeakers");
        return input.nextInt();
    }

    public double insertValue() {
        System.out.println("Insira um número");
        return input.nextDouble();
    }

    public String insertString() {
        System.out.println("Insira uma String");
        return input.next();
    }

    public void print(String r) {
        System.out.println(r);
    }

    public int mudaEstado() {
        System.out.println("O que deseja mudar?");
        System.out.println("1 - Estado do dispositivo (ligar/desligar) ou o consumo diário");
        System.out.println("2 - Mudar o tom (SmartBulb");
        System.out.println("3 - Canal e volume (SmartSpeaker");
        return input.nextInt();
    }

    public int onOffConsumo() {
        System.out.println("O que deseja mudar?");
        System.out.println("1 - Ligar Dispostivo");
        System.out.println("2 - Desligar Dispostivo");
        System.out.println("3 - Consumo");
        return input.nextInt();
    }

    public int canalVolume() {
        System.out.println("O que deseja mudar?");
        System.out.println("1 - Canal");
        System.out.println("2 - Volume");
        return input.nextInt();
    }
}
