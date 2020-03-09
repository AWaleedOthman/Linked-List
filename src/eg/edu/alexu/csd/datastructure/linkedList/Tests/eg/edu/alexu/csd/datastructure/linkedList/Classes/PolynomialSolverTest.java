package eg.edu.alexu.csd.datastructure.linkedList.Classes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PolynomialSolverTest {

    @Test
    void setPolynomial() {
        int[][] testArr = {{3,4,1,-1}, {6,-2,10,2}};
        int[][] testArr2 = {{1,2,1}, {2,1,0}};
        PolynomialSolver ps = new PolynomialSolver();
        ps.setPolynomial('A', testArr2);
        System.out.println(ps.print('A'));
        System.out.println(ps.evaluatePolynomial('A', (float) 3.2));
    }
}
