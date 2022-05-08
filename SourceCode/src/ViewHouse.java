import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ViewHouse
{
    private Scanner input;
    ViewHouse()
    {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }
    public double getNumber()
    {
        System.out.println("Insira um número");
        return this.input.nextDouble();
    }
    public String getStr()
    {
        System.out.println("Insira uma string");
        return input.next();
    }
    public int predicate()
    {
        System.out.println("Qual o critério de filtragem?");
        System.out.println("1 - Não");
        System.out.println("2 - Localidade");
        System.out.println("3 - Têm um certa divisão");
        System.out.println("4 - Têm um certo número de divisões");
        System.out.println("5 - Têm um certo device");
        System.out.println("6 - Têm um certo número de devices");
        System.out.println("7 - Têm um certo proprietario");
        System.out.println("8 - Têm um certo fornecedor");
        return input.nextInt();
    }
    public int consultarChange()
    {
        System.out.println("Consultar ou mudar dados?");
        System.out.println("1 - Consultar");
        System.out.println("2 - Mudar");
        System.out.println("3 - Nada");
        return input.nextInt();
    }

    public int decideConsulta()
    {
        System.out.println("O que deseja consultar?");
        System.out.println("1 - Comerciante");
        System.out.println("2 - Proprietário");
        System.out.println("3 - Lista de devices");
        System.out.println("4 - Lista de divisões");
        System.out.println("5 - Lista de localidades");
        return input.nextInt();
    }
    public int decideAlterar()
    {
        System.out.println("O que deseja consultar?");
        System.out.println("1 - Acrescentar n devices");
        System.out.println("2 - Acrescentar n divisões");
        System.out.println("3 - Mover n devices para uma divisão (Só funciona para 1 casa)");
        System.out.println("4 - Ligar ou desligar devices");
        System.out.println("5 - Mudar fornecedor");
        System.out.println("6 - Mudar Proprietário");
        return input.nextInt();
    }
    public int ligarDesligar()
    {
        System.out.println("Ligar ou desligar?");
        System.out.println("1 - Ligar Dispostivo");
        System.out.println("2 - Desligar Dispostivo");
        return input.nextInt();
    }
    public List<String> getDivisoesAdd()
    {
        System.out.println("Qual o número de divisões?");
        int n = input.nextInt();
        List<String> r = new ArrayList<>(n);
        for(int i = 0; i < n; i++)
        {
            System.out.println("Nome da divisão " + i);
            r.add(input.next());
        }
        return r;
    }
    public List<String> getDevicesAdd()
    {
        System.out.println("Qual o número de devices?");
        int n = input.nextInt();
        List<String> r = new ArrayList<>(n);
        for(int i = 0; i < n; i++)
        {
            System.out.println("Device " + i + ". Escrever I para formato");
            String aux = input.next();
            if(aux.equals("I"))
            {
                System.out.println("3 Possibilidades: (Separar cada parte por ',')");
                System.out.println("SmartBulb , (ligado ou desligado) , (consumo diário) , (Tom) , (Dimensao)");
                System.out.println("SmartSpeaker , (ligado ou desligado) , (consumo diário) , (Volume) , (Canal) , (Marca)");
                System.out.println("SmartCamera , (ligado ou desligado) , (consumo diário) , (Resolução) , (Tamanho)");
            }
            else r.add(input.next());
        }
        return r;
    }
    public void printMessage(String message)
    {
        System.out.println(message);
    }
}