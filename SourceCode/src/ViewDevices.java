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
    public Integer getDevice() {
        System.out.println("Insira o tipo do dispositivo a adicionar:");
        System.out.println("1: SmartBulb");
        System.out.println("2: SmartCamera");
        System.out.println("3: SmartSpeaker");
        System.out.println("Outros Inputs - Nada");
        Integer r = null;
        try{
             r = input.nextInt();
        }catch(java.util.InputMismatchException e){
            input.nextLine();
            System.out.println("Valor introduzido não compativel");
        }
        return r;
    }

    public Boolean getOn() {
        System.out.println("Qual é o estado do dispositivo (Booleano: true - ligado ou false - desligado)?");
        Boolean r = null;
        try {
            r = input.nextBoolean();
        }
        catch(java.util.InputMismatchException e){
            input.nextLine();
            System.out.println("Valor introduzido não compativel");
        }
        return r;
    }

    public Double getConsumo() {
        System.out.println("Qual é o consumo do dispostivo?");
        Double r = null;
        try {
            r = input.nextDouble();
        }
        catch (java.util.InputMismatchException e)
        {
            input.nextLine();
            System.out.println("Valor introduzido não compativel");
        }
        return r;
    }

    //Smart Camera
    public String getResolucao() {
        System.out.println("Qual é a resolução da câmara (formato HorizontalxVertical?");
        String r = null;
        try {
            r = input.next();
        }
        catch (java.util.InputMismatchException e)
        {
            input.nextLine();
            System.out.println("Valor introduzido não compativel");
        }
        return r;
    }

    public Double getTamanho() {
        System.out.println("Qual é o tamanho dos ficheiros gerados pela câmera?");
        Double r = null;
        try {
            r = input.nextDouble();
        }
        catch (java.util.InputMismatchException e)
        {
            input.nextLine();
            System.out.println("Valor introduzido não compativel");
        }
        return r;
    }

    //Smart Bulb
    public Integer getTone() {
        System.out.println("Qual é o tom da luz emitida pela lâmpada (0, 1 ou 2)?");
        Integer r = null;
        try{
            r = input.nextInt();
        }catch(java.util.InputMismatchException e){
            input.nextLine();
            System.out.println("Valor introduzido não compativel");
        }
        return r;
    }

    public Double getDimensao() {
        System.out.println("Qual é a dimensao da lâmpada?");
        Double r = null;
        try{
            r = input.nextDouble();
        }catch(java.util.InputMismatchException e){
            input.nextLine();
            System.out.println("Valor introduzido não compativel");
        }
        return r;
    }

    //Smart Speaker
    public Integer getVolume() {
        System.out.println("Qual é o Volume do dispositivo (valor inteiro entre 0 e 20)?");
        Integer r = null;
        try{
            r = input.nextInt();
        }catch(java.util.InputMismatchException e){
            input.nextLine();
            System.out.println("Valor introduzido não compativel");
        }
        return r;
    }

    public String getCanal() {
        System.out.println("Qual é o Canal que está a transmitir no dispositivo?");
        String r = null;
        try {
            r = input.next();
        }
        catch (java.util.InputMismatchException e)
        {
            input.nextLine();
            System.out.println("Valor introduzido não compativel");
        }
        return r;
    }

    public String getMarca() {
        System.out.println("Qual é a marca do dispositivo?");
        String r = null;
        try {
            r = input.next();
        }
        catch (java.util.InputMismatchException e)
        {
            input.nextLine();
            System.out.println("Valor introduzido não compativel");
        }
        return r;
    }

    //Operações
    public void sucess() {
        System.out.println("Operação com dispositivos efetuada com sucesso.");
    }

    //Predicates
    public Integer predicates() {
        System.out.println("Algum critério de filtragem?");
        System.out.println("1 - Não");
        System.out.println("2 - Consumo Superior a um dado valor");
        System.out.println("3 - Consumo Inferiores a um dado valor");
        System.out.println("4 - Ligados");
        System.out.println("5 - Desligados");
        System.out.println("6 - SmartCameras");
        System.out.println("7 - SmartBulbs");
        System.out.println("8 - SmartSpeakers");
        System.out.println("Outros Inputs - Nada");
        Integer r = null;
        try{
            r = input.nextInt();
        }catch(java.util.InputMismatchException e){
            input.nextLine();
            System.out.println("Valor introduzido não compativel");
        }
        return r;
    }

    public Double insertValue() {
        System.out.println("Insira um número");
        Double r = null;
        try{
            r = input.nextDouble();
        }catch(java.util.InputMismatchException e){
            input.nextLine();
            System.out.println("Valor introduzido não compativel");
        }
        return r;
    }
    public Integer insertValueInteger() {
        System.out.println("Insira um número");
        Integer r = null;
        try{
            r = input.nextInt();
        }catch(java.util.InputMismatchException e){
            input.nextLine();
            System.out.println("Valor introduzido não compativel");
        }
        return r;
    }
    public String insertString() {
        System.out.println("Insira uma String");
        String r = null;
        try {
            r = input.next();
        }
        catch (java.util.InputMismatchException e)
        {
            input.nextLine();
            System.out.println("Valor introduzido não compativel");
        }
        return r;
    }

    public void print(String r) {
        System.out.println(r);
    }

    public Integer mudaEstado() {
        System.out.println("O que deseja mudar?");
        System.out.println("1 - Estado do dispositivo (ligar/desligar) ou o consumo diário");
        System.out.println("2 - Mudar o tom (SmartBulb) (Valor igual a 0, 1 ou 2)");
        System.out.println("3 - Canal e volume (SmartSpeaker) ");
        System.out.println("Outros Inputs - Nada");
        Integer r = null;
        try{
            r = input.nextInt();
        }catch(java.util.InputMismatchException e){
            input.nextLine();
            System.out.println("Valor introduzido não compativel");
        }
        return r;
    }

    public Integer onOffConsumo() {
        System.out.println("O que deseja mudar?");
        System.out.println("1 - Ligar Dispostivo");
        System.out.println("2 - Desligar Dispostivo");
        System.out.println("3 - Consumo");
        System.out.println("Outros Inputs - Nada");
        Integer r = null;
        try{
            r = input.nextInt();
        }catch(java.util.InputMismatchException e){
            System.out.println("Valor introduzido não compativel");
        }
        return r;
    }

    public Integer canalVolume() {
        System.out.println("O que deseja mudar?");
        System.out.println("1 - Canal");
        System.out.println("2 - Volume");
        System.out.println("Outros Inputs - Nada");
        Integer r = null;
        try{
            r = input.nextInt();
        }catch(java.util.InputMismatchException e){
            input.nextLine();
            System.out.println("Valor introduzido não compativel");
        }
        return r;
    }

    public Integer consultarAlterar(){
        System.out.println("1 - Consultar Dados");
        System.out.println("2 - Alterar Dados");
        System.out.println("Outros Inputs - Nada");
        Integer r = null;
        try{
            r = input.nextInt();
        }catch(java.util.InputMismatchException e){
            input.nextLine();
            System.out.println("Valor introduzido não compativel");
        }
        return r;
    }
}
