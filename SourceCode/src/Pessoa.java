public class Pessoa
{
    private String nome;
    private int NIF;
    public Pessoa()
    {
        this.nome = "";
        this.NIF = 0;
    }
    public Pessoa(String nome, int NIF) throws ValorNegativoException,NullPointerException
    {
        this.setNome(nome);
        this.setNIF(NIF);
    }
    public Pessoa(Pessoa pessoa)
    {
        this.nome = pessoa.getNome();
        this.NIF = pessoa.getNIF();
    }
    public int getNIF() {
        return NIF;
    }

    public String getNome() {
        return nome;
    }

    public void setNIF(int NIF) throws ValorNegativoException {
        if(NIF < 0){
            throw new ValorNegativoException("NIF negativo na pessoa "+this.getNome());
        }
        this.NIF = NIF;
    }

    public void setNome(String nome) throws NullPointerException {
        if(nome == null){
            throw new NullPointerException("Nome nulo na pessoa com o NIF = "+this.getNIF());
        }
        this.nome = nome;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pessoa pessoa = (Pessoa) o;
        return NIF == pessoa.NIF;
    }

    @Override
    public int hashCode() {
        return this.getNIF();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pessoa:\n")
                .append("Nome: ").append(this.nome).append("\n").append("NIF: ").append(this.NIF).append("\n");
        return sb.toString();
    }

    public Pessoa clone()
    {
        return new Pessoa(this);
    }
}
