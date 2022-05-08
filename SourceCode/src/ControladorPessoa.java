import java.util.Objects;
import java.util.function.Predicate;
import java.lang.Integer;

public class ControladorPessoa {
    private  ViewPessoa viewP;
    private App app;

    ControladorPessoa(App appP) {
        this.viewP = new ViewPessoa();
        this.app = appP;
    }

    public void addPessoa() throws ValorNegativoException, NullPointerException {
        String nome = viewP.getNomePessoa();
        Integer nif = viewP.getNifPessoa();
        app.addPessoa(nome,nif);
        viewP.success();
    }

    public void addPessoa (String nome, Integer NIF) throws ValorNegativoException, NullPointerException {
        app.addPessoa(nome, NIF);
        viewP.success();
    }

    private Predicate<Pessoa> pessoaPredicate(){
        int n = viewP.predicates();
        Predicate<Pessoa> pred;
        switch (n)
        {
            case 1:
                pred = p -> (p.getNIF() >  viewP.numeroCompareInteiro());
                break;

            case 2:
                String nome = viewP.getNomePessoa();
                pred = p ->(p.getNome().equals(nome));
                break;
            default:
                pred = Objects::nonNull;
                break;
        }
        return pred;
    }

    public void consultarDados() {
        Predicate<Pessoa> p = this.pessoaPredicate();
        int option = viewP.consultarOsDados();
        String r = "";
        switch (option)
        {
            case 1:
                r = app.nifsMaioresQueX(p, viewP.inserInteger()).toString();
                break;
            case 2:
                r= app.nomeIgualaX(p,viewP.insertString()).toString();
                break;
            default:
                break;
        }
    }

    public void selectOperation(){
        int n= viewP.atividadeConsultar();
        switch (n) {
            case 1:
                consultarDados();
                break;
            default:
                break;
        }
    }
}

