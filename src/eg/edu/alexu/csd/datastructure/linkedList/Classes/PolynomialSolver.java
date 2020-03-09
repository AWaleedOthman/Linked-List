package eg.edu.alexu.csd.datastructure.linkedList.Classes;

import eg.edu.alexu.csd.datastructure.linkedList.Interfaces.IPolynomialSolver;

public class PolynomialSolver implements IPolynomialSolver {

    private static class Term {
        private Integer coefficient, exponent;

        public Term(int coefficient, int exponent) {
            this.coefficient = coefficient;
            this.exponent = exponent;
        }
    }

    private SLinkedList<SLinkedList<Term>> polynomials = new SLinkedList<>();

    public PolynomialSolver() {
        polynomials.add(new SLinkedList<Term>()); //A
        polynomials.add(new SLinkedList<Term>()); //B
        polynomials.add(new SLinkedList<Term>()); //C
        polynomials.add(new SLinkedList<Term>()); //R
    }

    private int getIndex(char c) {
        int i;
        switch (c) {
            case 'A':
            case 'a':
                i = 0;
                break;
            case 'B':
            case 'b':
                i = 1;
                break;
            case 'C':
            case 'c':
                i = 2;
                break;
            case 'R':
            case 'r':
                i = 3;
                break;
            default:
                throw new RuntimeException();
        }
        return i;
    }

    private void sort(int[][] terms) {
        int n = terms[0].length;
        int i, j, temp;
        boolean swapped;
        for (i = 0; i < n - 1; i++) {
            swapped = false;
            for (j = 0; j < n - i - 1; j++) {
                if (terms[1][j] < terms[1][j + 1]) {
                    temp = terms[1][j];
                    terms[1][j] = terms[1][j + 1];
                    terms[1][j + 1] = temp;
                    temp = terms[0][j];
                    terms[0][j] = terms[0][j + 1];
                    terms[0][j + 1] = temp;
                    swapped = true;
                }
            }

            if (!swapped)
                break;
        }
    }

    @Override
    public void setPolynomial(char poly, int[][] terms) {
        sort(terms);
        int i = getIndex(poly);
        for (int j = 0; j < terms[0].length; j++) {
            polynomials.get(i).add(new Term(terms[0][j], terms[1][j]));
        }
    }

    @Override
    public String print(char poly) {
        SLinkedList<Term> tempPoly = polynomials.get(getIndex(poly));
        if (tempPoly.size() == 0) return null;
        Term tempTerm = tempPoly.get(0);
        Integer tempCo = tempTerm.coefficient; //because it is used A LOT
        StringBuilder expression;
        if (tempCo > 0) {
            expression = new StringBuilder((tempCo == 1 ? "" : tempCo) + "x^(" + tempTerm.exponent + ") ");
        } else if (tempCo < 0) {
            expression = new StringBuilder((tempCo == -1 ? "-" : tempCo) + "x^(" + tempCo + ") ");
        } else {
            expression = new StringBuilder();
        }
        for (int i = 1; i < tempPoly.size(); i++) {
            tempTerm = tempPoly.get(i);
            tempCo = tempTerm.coefficient;

            /*
            for sign
             */
            if (tempCo > 0) { //+ve coefficient
                expression.append("+ ");
                expression.append(tempCo == 1 ? "" : tempCo).append("x^(").append(tempTerm.exponent).append(") ");

            } else if (tempCo < 0) { //-ve coefficient
                expression.append("- ");
                expression.append(tempCo == -1 ? "" : -1 * tempCo).append("x^(").append(tempTerm.exponent).append(") ");

            }
        }

        return expression.toString();
    }

    @Override
    public void clearPolynomial(char poly) {
        polynomials.remove(getIndex(poly));
    }

    @Override
    public float evaluatePolynomial(char poly, float value) {
        return 0;
    }

    @Override
    public int[][] add(char poly1, char poly2) {
        return new int[0][];
    }

    @Override
    public int[][] subtract(char poly1, char poly2) {
        return new int[0][];
    }

    @Override
    public int[][] multiply(char poly1, char poly2) {
        return new int[0][];
    }
}
