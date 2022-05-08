import java.util.Scanner;


public class ViewPessoa {
    private final Scanner input;
    ViewPessoa() {
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    public String getNomePessoa(){
        System.out.println("Introduza o nome da Pessoa");
        return input.next();
    }

    public int getNifPessoa(){
        System.out.println("Introduza o NIF da Pessoa");
        return input.nextInt();
    }

    public void success(){
        System.out.println("Os dados da pessoa foram introduzidos com sucesso!");
    }

    public int getConsulta (char c) {
        if (c == 'c') System.out.println("O que pretende consultar? 1 - Nome, 2-NIF");
        return input.nextInt();
    }
    public int predicates()
    {
        System.out.println("Pretende aplicar algum critério de filtragem?");
        System.out.println("1 - Não pretendo");
        System.out.println("2 - A pessoa tem um NIF superior a um certo número inteiro");
        System.out.println("3 - A pessoa tem um certo nome");
        return input.nextInt();
    }
    public int numeroCompareInteiro()
    {
        System.out.println("Número inteiro para comparar?");
        return input.nextInt();
    }

    public int inserInteger(){
        System.out.println("Insira um número");
        return input.nextInt();
    }

    public String insertString() {
        System.out.println("Insira uma String");
        return input.next();
    }

    public int consultarOsDados()
    {
        System.out.println("O que pretende consultar??");
        System.out.println("1 - O nome da pessoa");
        System.out.println("2 - O NIF da pessoa");
        System.out.println("3 - Nada");
        return input.nextInt();
    }
    public int atividadeConsultar()
    {
        System.out.println("Pretende consultar  informação da Pessoa?");
        System.out.println("1 - Sim");
        System.out.println("2 - Não ");
        return input.nextInt();
    }

}

