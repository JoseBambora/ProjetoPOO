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
        if (nome == null) return;
        Integer nif = viewP.getNifPessoa();
        if (nif == null) return;
        app.addPessoa(nome, nif);
        viewP.success();
    }

    public void addPessoa(String nome, Integer NIF) throws ValorNegativoException, NullPointerException {
        app.addPessoa(nome, NIF);
        viewP.success();
    }

    private Predicate<Pessoa> pessoaPredicate() {
        Integer n = viewP.predicates();
        Predicate<Pessoa> pred;
        if(n == null)
            return null;
        switch (n) {
            case 1:
                Integer comp =  viewP.numeroCompareInteiro();
                if (comp == null) return null;
                pred = p -> (p.getNIF() > comp);
                break;

            case 2:
                String nome = viewP.getNomePessoa();
                if (nome == null) return null;
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
        if (pessoa == null)
                return;
        Integer n = viewP.consultarOsDados();
        if (n == null) return;
        switch (n) {
            case 1:
                r = app.getNomePessoas().toString();
                break;
            case 2:
                r = app.getNIFPessoas().toString();
                break;
            case 3:
                r = Integer.toString(app.respectPredicatePessoa(pessoa));
            default:
                break;
        }
        viewP.printMessage(r);
    }

    public void selecionouConsultaDados()
    {
        try {
            Integer n = viewP.atividadeConsultar();
            if (n == null) return;
            if (n == 1)
                this.consultaDados();
        }
        catch (ValorNegativoException | NullPointerException e)
        {
            viewP.printMessage(e.getMessage());
        }
    }
}

