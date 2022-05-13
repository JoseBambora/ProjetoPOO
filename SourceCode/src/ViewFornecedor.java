import java.util.Scanner;

public class ViewFornecedor
{
    private Scanner input;
    ViewFornecedor()
    {
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

    private Double scannerDouble()
    {
        Double r = null;
        try {
            r = input.nextDouble();
        }
        catch (java.util.InputMismatchException e)
        {
            input.nextLine();
            System.out.println("Valor introduzido não compativel.");
        }
        return r;
    }
    public String getNome(){
        System.out.println("Qual o nome do Comerciante?");
        return this.scannerString();
    }
    public Integer getFormula()
    {
        System.out.println("Introduza a Formula? Insira um número entre 1 e 2");
        return this.scannerInt();
    }
    public Integer getOperation(char c)
    {
        if(c == 'm') System.out.println("O que quer mudar? 1 - Formula");
        else
        {
            System.out.println("O que quer consultar?");
            System.out.println("1 - Formula");
            System.out.println("2 - Faturas do fornecedor");
            System.out.println("3 - Número de faturas");
            System.out.println("4 - Lucro do fornecedor");
            System.out.println("5 - Número de Fornecedores que respeitam um certo predicado");
        }
        return this.scannerInt();
    }
    public void sucess()
    {
        System.out.println("Operação com Fornecedores efetuada com sucesso.");
    }
    public Integer predicates()
    {
        System.out.println("Algum critério de filtragem?");
        System.out.println("1 - Não");
        System.out.println("2 - Têm mais que um certo número inteiro de faturas emitidas");
        System.out.println("3 - Têm volume de faturação maior ou igual a um certo número real");
        System.out.println("4 - Segue uma determinada Formula");
        System.out.println("5 - Têm um certo nome");
        return this.scannerInt();
    }
    public Integer numeroCompareI()
    {
        System.out.println("Número para comparar?");
        return this.scannerInt();
    }

    public Double numeroCompareD()
    {
        System.out.println("Número para comparar?");
        return this.scannerDouble();
    }
    public void printInfVF(String s)
    {
        System.out.println(s);
    }

    public Integer consultarAlterar()
    {
        System.out.println("Consultar informação ou altera-la nos fornecedores?");
        System.out.println("1 - Consultar");
        System.out.println("2 - Alterar");
        System.out.println("3 - Nada");
        return this.scannerInt();
    }
}
