import java.util.Objects;
import java.util.function.Predicate;

public class ControladorFornecedor
{
    private ViewFornecedor view;
    private App app;
    ControladorFornecedor( App app)
    {
        this.app = app;
        this.view = new ViewFornecedor();
    }
    public Formulas convertNumberFormula(int n)
    {
        Formulas formulas;
        switch (n)
        {
            case 1:
                formulas = new FormulaCalc1();
                break;
            case 2:
                formulas = new FormulaCalc2();
                break;
            default:
                formulas = null;
                break;
        }
        return formulas;
    }
    public void addFornecedor(){
        String nome = view.getNome();
        int f = view.getFormula();
        app.addFornecedor(nome,this.convertNumberFormula(f));
        // Criar Método na view para IO para obter todos os dados do comerciante
        view.sucess();
    }
    public void addFornecedor(String nome, Formulas formulas)
    {
        app.addFornecedor(nome,formulas);
        view.sucess();
    }
    private Predicate<Comerciante> comerciantePredicate(){
        int n = view.predicates();
        Predicate<Comerciante> r;
        switch (n)
        {
            case 2:
                r = p -> (p.numberFaturas() > view.numeroCompareI());
                break;
            case 3:
                r = p -> (p.getLucro() > view.numeroCompareD());
                break;
            case 4:
                int f = view.getFormula();
                if(f == 1)
                    r = p -> (p.getFormula() instanceof FormulaCalc1);
                else
                    r = p -> (p.getFormula() instanceof FormulaCalc2);
                break;
            case 5:
                String nome = view.getNome();
                r = p ->(p.getNome().equals(nome));
                break;
            default:
                r = Objects::nonNull;
                break;
        }
        return r;
    }
    public void consultarDados() {
        Predicate<Comerciante> p = this.comerciantePredicate();
        int option = view.getOperation('c');
        String r = "";
        switch (option)
        {
            case 1:
                r = app.getFormulasPredicate(p).toString();
                break;
            case 2:
                r = app.queryFaturas(p).toString();
                break;
            case 3:
                r = Integer.toString(app.numeroFaturas(p));
                break;
            case 4:
                r = Double.toString(app.lucro(p));
                break;
            case 5:
                view.sucess();
                r = Integer.toString(app.respectPredicate(p));
            default:
                break;
        }
        view.printInfVF(r);
    }

    public void changeDadosFornecedor(){
        // Criar Método na view para IO para obter os dados a mudar do comerciante
        Predicate<Comerciante> p = this.comerciantePredicate();
        int option = view.getOperation('m');
        switch (option)
        {
            case 1:
                int f = view.getFormula();
                app.changeFormulaFornecedor(p,this.convertNumberFormula(f));
                break;
            default:
                break;
        }
        view.sucess();
    }
    public void whatOperation(){
        int n = view.consultarAlterar();
        switch (n)
        {
            case 1:
                consultarDados();
                break;
            case 2:
                changeDadosFornecedor();
                break;
            default:
                break;
        }
    }
}
