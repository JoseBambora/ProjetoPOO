import java.time.LocalDate;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;

public class ViewQueries {
    private final Scanner input;
    ViewQueries() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
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

    private Integer scannerInt()
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
    public String getNomeComerciante(){
        System.out.println("Introduza o nome do comerciante");
        return  this.scannerString();
    }

    public static LocalDate convertStringToLocalDate (String n) {
        LocalDate data = LocalDate.parse(n);
        return data;
    }
    public LocalDate getDate(){
        System.out.println("Introduza o ano-mes-dia");
        String n1 = this.scannerString();
        String [] date = n1.split("-");
        LocalDate d1 =LocalDate.of(Integer.parseInt(date[0]), Integer.parseInt (date[1]), Integer.parseInt (date[2]));
        return d1;
    }

    public void printResult(String result)
    {
        System.out.println(result);
    }

    public void success(){
        System.out.println("Os dados necessários para estatísticas do estado do programa  foram introduzidos com sucesso!");
    }

    public Integer inserInteger(){
        System.out.println("Insira um número");
        return this.scannerInt();
    }

    public String insertString() {
        System.out.println("Insira uma String");
        return this.scannerString();
    }

    public Integer consultarOsDados()
    {
        System.out.println("O que pretende consultar?");
        System.out.println("1 - Qual o comercializador com maior volume de faturação");
        System.out.println("2 - A pessoa que gastou mais naquele período");
        System.out.println("3 - Dar uma ordenação dos maiores consumidores de energia num período a determinar");
        System.out.println("4 - Desejo avançar dias");
        System.out.println("5 - Nada");
        return this.scannerInt();
    }
    public Integer atividadeConsultar()
    {
        System.out.println("Pretende consultar estatísticas sobre o estado do programa ou mudar o fornecedor?");
        System.out.println("1 - Realizar queries");
        System.out.println("2 - Mudar imposto");
        return this.scannerInt();
    }



}