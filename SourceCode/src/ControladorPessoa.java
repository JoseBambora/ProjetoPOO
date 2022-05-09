import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Predicate;
import java.lang.Integer;

public class ControladorPessoa {
    private ViewPessoa viewP;
    private App app;

    ControladorPessoa(App appP) {
        this.viewP = new ViewPessoa();
        this.app = appP;
    }

    public void addPessoa() throws ValorNegativoException, NullPointerException {
        String nome = viewP.getNomePessoa();
        Integer nif = viewP.getNifPessoa();
        app.addPessoa(nome, nif);
        viewP.success();
    }

    public void addPessoa(String nome, Integer NIF) throws ValorNegativoException, NullPointerException {
        app.addPessoa(nome, NIF);
        viewP.success();
    }

    private Predicate<Pessoa> pessoaPredicate() {
        int n = viewP.predicates();
        Predicate<Pessoa> pred;
        switch (n) {
            case 1:
                pred = p -> (p.getNIF() > viewP.numeroCompareInteiro());
                break;

            case 2:
                String nome = viewP.getNomePessoa();
                pred = p -> (p.getNome().equals(nome));
                break;
            default:
                pred = Objects::nonNull;
                break;
        }
        return pred;
    }

    public void consultaDados() throws ValorNegativoException, NullPointerException {
        String r = "";
        Predicate<Pessoa> pessoa = this.pessoaPredicate();
        int n = viewP.consultarOsDados();
        switch (n) {
            case 1: //
                r = app.getNomePessoas().toString();
                break;
            case 2:
                r = app.getNIFPessoas().toString();
                break;
            default:
                break;
        }
    }

    public void selecionouConsultaDados()
    {
        try {
            int n = viewP.atividadeConsultar();
            if (n == 1)
                this.consultaDados();
        }
        catch (ValorNegativoException | NullPointerException e)
        {
            viewP.printMessage(e.getMessage());
        }
    }
}
