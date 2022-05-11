import java.util.Objects;
import java.util.function.Predicate;

public class ControladorFornecedor {
    private ViewFornecedor view;
    private App app;

    ControladorFornecedor(App app) {
        this.app = app;
        this.view = new ViewFornecedor();
    }

    private Formulas convertNumberFormula(int n) {
        Formulas formulas;
        switch (n) {
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

    public void addFornecedor() {
        try {
            String nome = view.getNome();
            if(!nome.equals(""))
            {
                int f = view.getFormula();
                if(f != -1)
                {
                    app.addFornecedor(nome, this.convertNumberFormula(f));
                    view.sucess();
                }
            }
        }
        catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addFornecedor(String nome, Formulas formulas) {
        try {
            app.addFornecedor(nome, formulas);
            view.sucess();
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    private Predicate<Comerciante> comerciantePredicate() {
        int n = view.predicates();
        Predicate<Comerciante> r = null;
        switch (n) {
            case 1:
                r = Objects::nonNull;
                break;
            case 2:
                int num = view.numeroCompareI();
                if(num != -1)
                    r = p -> (p.numberFaturas() > num);
                break;
            case 3:
                double d = view.numeroCompareD();
                if(d != -1) r = p -> (p.getLucro() > d);
                break;
            case 4:
                int f = view.getFormula();
                if (f == 1)
                    r = p -> (p.getFormula() instanceof FormulaCalc1);
                else if(f == 2)
                    r = p -> (p.getFormula() instanceof FormulaCalc2);
                break;
            case 5:
                String nome = view.getNome();
                if(!nome.equals(""))
                    r = p -> (p.getNome().equals(nome));
                break;
            default:
                break;
        }
        return r;
    }

    public void consultarDados() {
        Predicate<Comerciante> p = this.comerciantePredicate();
        if(p == null)
            return;
        int option = view.getOperation('c');
        String r = "";
        switch (option) {
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

    public void changeDadosFornecedor() {
        try {
            // Criar Método na view para IO para obter os dados a mudar do comerciante
            Predicate<Comerciante> p = this.comerciantePredicate();
            if(p == null)
                return;
            int option = view.getOperation('m');
            switch (option) {
                case 1:
                    int f = view.getFormula();
                    if(f != -1) {
                        app.changeFormulaFornecedor(p, this.convertNumberFormula(f));
                        view.sucess();
                    }
                    break;
                default:
                    break;
            }
        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        }
    }

    public void whatOperation() {
        int n = view.consultarAlterar();
        switch (n) {
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