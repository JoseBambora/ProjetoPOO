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

    @Override
    public boolean equals(Object obj) {
        return obj instanceof FormulaCalc1;
    }

    @Override
    public int hashCode() {
        return 1;
    }
}
