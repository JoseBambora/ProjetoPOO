public class FormulaCalc2 implements Formulas {
    @Override
    public double calculaPreco(double consumo, int imposto) {
        return consumo > 10000 ? consumo * ((double) imposto/100 + 0.75) * 2 : consumo * ((double) imposto/100 + 1.2) * 2 ;
    }
    @Override
    public String toString() {
        return "FormulaCacl2";
    }
}