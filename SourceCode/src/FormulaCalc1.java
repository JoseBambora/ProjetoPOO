import java.io.Serializable;

public class FormulaCalc1 implements Formulas, Serializable {
    @Override
    public double calculaPreco(double consumo, int imposto) {
        return consumo * ((double) imposto/100 + 1);
    }

    @Override
    public String toString() {
        return "FormulaCacl1";
    }
}
