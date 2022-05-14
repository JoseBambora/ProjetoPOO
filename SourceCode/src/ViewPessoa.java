import java.util.Scanner;


public class ViewPessoa {
    private final Scanner input;
    ViewPessoa() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
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


    public String getNomePessoa(){
        System.out.println("Introduza o nome da Pessoa");
        return this.scannerString();
    }

    public Integer getNifPessoa(){
        System.out.println("Introduza o NIF da Pessoa");
        return this.scannerInt();
    }

    public void success(){
        System.out.println("Os dados da pessoa foram introduzidos com sucesso!");
    }

    public Integer getConsulta (char c) {
        if (c == 'c') System.out.println("O que pretende consultar? 1 - Nome, 2-NIF");
        return input.nextInt();
    }
    public Integer predicates()
    {
        System.out.println("Pretende aplicar algum critério de filtragem?");
        System.out.println("1 - Não pretendo");
        System.out.println("2 - A pessoa tem um NIF superior a um certo número inteiro");
        System.out.println("3 - A pessoa tem um certo nome");
        return this.scannerInt();
    }
    public Integer numeroCompareInteiro()
    {
        System.out.println("Número inteiro para comparar?");
        return this.scannerInt();
    }

    public Integer consultarOsDados()
    {
        System.out.println("O que pretende consultar??");
        System.out.println("1 - O nome da pessoa");
        System.out.println("2 - O NIF da pessoa");
        System.out.println("3 - Número de Pesosas que respeitam um dado predicado ");
        System.out.println("4 - Nada");
        return this.scannerInt();
    }
    public Integer atividadeConsultar()
    {
        System.out.println("Pretende consultar  informação da Pessoa?");
        System.out.println("1 - Sim");
        System.out.println("2 - Não ");
        return this.scannerInt();
    }

    public void printMessage(String message)
    {
        System.out.println(message);
    }

}

