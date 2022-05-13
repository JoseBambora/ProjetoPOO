import java.time.LocalDate;
import java.util.Objects;
import java.util.function.Predicate;

public class ControladorQueries {
    private ViewQueries viewQ;
    private App app;

    ControladorQueries(App appQ) {
        this.viewQ = new ViewQueries();
        this.app = appQ;
    }

    public String queryMF() {
        String r = "";
        r = app.queryMaiorFornecedor().toString();
        return r;
    }

    public String queryMC() {
        String r = app.queryMaiorConsumidor().toString();
        return r;
    }

    public String queryMCDates(LocalDate d1, LocalDate d2) {
        String r = app.queryMaioresConsumidores(d1, d2).toString();
        return r;
    }


    public void consultaDados() throws ValorNegativoException, NullPointerException {
        String r = "";
        Integer n = viewQ.consultarOsDados();
        if(n == null)
            return;
        switch (n) {
            case 1: //
                r = queryMF();
                break;
            case 2:
                r = queryMC();
                break;
            case 3:
                LocalDate d1 = viewQ.getDate();
                LocalDate d2 = viewQ.getDate();
                r = queryMCDates(d1, d2);

            case 4:
                Integer numero = viewQ.inserInteger();
                app.avancaDias(numero);

            default:
                break;
        }
        viewQ.printResult(r);
    }
    public void mudaDados()
    {
        Integer imposto = viewQ.inserInteger();
        app.setImposto(imposto);
    }
    public void selecionouConsultaDados() {
        try {
            Integer n = viewQ.atividadeConsultar();
            if (n == 1)
                this.consultaDados();
            else if(n == 2)
                this.mudaDados();

        } catch (ValorNegativoException | NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }
}

