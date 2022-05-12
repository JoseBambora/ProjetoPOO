import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ViewHouse
{
    private Scanner input;
    ViewHouse()
    {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }
    private Integer getNumberIntScanner()
    {
        Integer r = null;
        try {
            r = input.nextInt();
        }
        catch (java.util.InputMismatchException e)
        {
            input.nextLine();
            System.out.println("Valor introduzido não compativel.");
        }
        return r;
    }

    private String scannerString()
    {
        String r = null;
        try {
            r = input.next();
        }
        catch (java.util.InputMismatchException e)
        {
            input.nextLine();
            System.out.println("Valor introduzido não compativel.");
        }
        return r;
    }
    public Integer getNumber2(String str)
    {
        System.out.println("Insira um número " + str);
        return this.getNumberIntScanner();
    }
    public String getStr(String str)
    {
        System.out.println("Insira uma string para " +str);
        return this.scannerString();
    }
    public Integer predicate()
    {
        System.out.println("Algum o critério de filtragem para a casa?");
        System.out.println("1 - Não");
        System.out.println("2 - Localidade");
        System.out.println("3 - Têm um certa divisão");
        System.out.println("4 - Têm um certo número de divisões");
        System.out.println("5 - Têm um certo device");
        System.out.println("6 - Têm um certo número de devices");
        System.out.println("7 - Têm um certo proprietario");
        System.out.println("8 - Têm um certo fornecedor");
        return this.getNumberIntScanner();
    }
    public Integer predicateDiv()
    {
        System.out.println("Algum o critério de filtragem para as divisões?");
        System.out.println("1 - Não");
        System.out.println("2 - Têm mais que x dispositivos");
        System.out.println("3 - Têm um certo nome");
        return this.getNumberIntScanner();
    }
    public Integer consultarChange()
    {
        System.out.println("Consultar ou mudar dados?");
        System.out.println("1 - Consultar");
        System.out.println("2 - Mudar");
        System.out.println("3 - Nada");
        return this.getNumberIntScanner();
    }

    public Integer decideConsulta()
    {
        System.out.println("O que deseja consultar?");
        System.out.println("1 - Comerciante");
        System.out.println("2 - Proprietário");
        System.out.println("3 - Lista de devices seguindo um certo predicado");
        System.out.println("4 - Lista de divisões");
        System.out.println("5 - Lista de localidades");
        return this.getNumberIntScanner();
    }
    public Integer decideAlterar()
    {
        System.out.println("O que deseja consultar/alterar?");
        System.out.println("1 - Acrescentar n devices");
        System.out.println("2 - Acrescentar n divisões");
        System.out.println("3 - Mover n devices para uma divisão (Só funciona para 1 casa)");
        System.out.println("4 - Ligar ou desligar devices seguindo um certo predicado");
        System.out.println("5 - Mudar fornecedor");
        System.out.println("6 - Mudar Proprietário");
        return this.getNumberIntScanner();
    }
    public Integer ligarDesligar()
    {
        System.out.println("Ligar ou desligar?");
        System.out.println("1 - Ligar Dispostivo");
        System.out.println("2 - Desligar Dispostivo");
        return this.getNumberIntScanner();
    }
    public List<String> getDivisoesAdd()
    {
        System.out.println("Qual o número de divisões?");
        Integer n = this.getNumberIntScanner();
        if(n == null)
            return null;
        List<String> r = new ArrayList<>(n);
        for(int i = 0; i < n; i++)
        {
            System.out.println("Nome da divisão " + i);
            String aux = this.scannerString();
            if(aux != null)
            {
                r.add(aux);
                i--;
            }
        }
        return r;
    }
    public void printMessage(String message)
    {
        System.out.println(message);
    }
    public void printMaps1(Map<String,List<String>> map)
    {
        for(String key : map.keySet())
        {
            StringBuilder stringBuilder = new StringBuilder();
            for(String value : map.get(key))
                stringBuilder.append(value).append(" ");
            System.out.println(key + ":" + stringBuilder);

        }
    }
    public void printMaps2(Map<Integer,List<String>> map)
    {
        for(Integer key : map.keySet())
        {
            StringBuilder stringBuilder = new StringBuilder();
            for(String value : map.get(key))
                stringBuilder.append(value).append(" ");
            System.out.println(key.toString() + ":" + stringBuilder);

        }
    }
}
