package eg.edu.alexu.csd.datastructure.linkedList.Classes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PolynomialSolverTest {

    @Test
    void setPolynomial() {
        int[][] testArr = {{3,4,1,-1}, {6,-2,10,2}};
        PolynomialSolver ps = new PolynomialSolver();
        ps.setPolynomial('A', testArr);
        System.out.println(ps.print('A'));
        ps.clearPolynomial('A');
        System.out.println(ps.print('A'));
    }
}
