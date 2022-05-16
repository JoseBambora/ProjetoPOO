import java.io.Serializable;

public class FormulaCalc2 implements Formulas, Serializable {
    @Override
    public double calculaPreco(double consumo, int imposto) {
        return consumo > 10000 ? consumo * ((double) imposto/100 + 0.75) * 2 : consumo * ((double) imposto/100 + 1.2) * 2 ;
    }
    @Override
    public String toString() {
        return "FormulaCacl2";
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof FormulaCalc2;
    }
    @Override
    public int hashCode() {
        return 2;
    }
}