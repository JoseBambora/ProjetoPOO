import java.util.Objects;

public class Pessoa
{
    private String nome;
    private int NIF;
    public Pessoa()
    {
        this.nome = "";
        this.NIF = 0;
    }
    public Pessoa(String nome, int NIF)
    {
        this.nome = nome;
        this.NIF = NIF;
    }

    public int getNIF() {
        return NIF;
    }

    public String getNome() {
        return nome;
    }

    public void setNIF(int NIF) {
        this.NIF = NIF;
    }

    public void setNome(String nome) {
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
        return "Pessoa, nome: '" + this.nome + "', NIF:" + this.NIF;
    }
}
