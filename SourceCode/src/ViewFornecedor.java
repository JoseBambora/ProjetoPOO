import java.util.Scanner;

public class ViewFornecedor
{
    private Scanner input;
    ViewFornecedor()
    {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }
    private int scannerInt()
    {
        int r = -1;
        try {
            r = input.nextInt();
        }
        catch (java.util.InputMismatchException e)
        {
            input.nextLine();
            System.out.println("Valor introduzido não compativel");
        }
        return r;
    }
    public String getNome(){
        System.out.println("Qual o nome do Comerciante?");
        String r = "";
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
    public int getFormula()
    {
        System.out.println("Introduza a Formula? Insira um número entre 1 e 2");
        return this.scannerInt();
    }
    public int getOperation(char c)
    {
        if(c == 'm') System.out.println("O que quer mudar? 1 - Formula");
        else         System.out.println("O que quer consultar? 1 - Formula, 2 - Faturas do fornecedor, 3 - Número de faturas, 4 - Lucro do fornecedor, 5 - Número de Fornecedores");
        return this.scannerInt();
    }
    public void sucess()
    {
        System.out.println("Operação com Fornecedores efetuada com sucesso.");
    }
    public int predicates()
    {
        System.out.println("Algum critério de filtragem?");
        System.out.println("1 - Não");
        System.out.println("2 - Têm mais que um certo número inteiro de faturas emitidas");
        System.out.println("3 - Têm volume de faturação maior ou igual a um certo número real");
        System.out.println("4 - Segue uma determinada Formula");
        System.out.println("5 - Têm um certo nome");
        return this.scannerInt();
    }
    public int numeroCompareI()
    {
        System.out.println("Número para comparar?");
        return this.scannerInt();
    }

    public double numeroCompareD()
    {
        System.out.println("Número para comparar?");
        double r = -1;
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
    public void printInfVF(String s)
    {
        System.out.println(s);
    }

    public int consultarAlterar()
    {
        System.out.println("Consultar informação ou altera-la nos fornecedores?");
        System.out.println("1 - Consultar");
        System.out.println("2 - Alterar");
        System.out.println("3 - Nada");
        return this.scannerInt();
    }
}
