package eg.edu.alexu.csd.datastructure.linkedList.Classes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PolynomialSolverTest {
    @Test
    void print() {
        PolynomialSolver ps = new PolynomialSolver();
        ps.setPolynomial('A', ps.getNumbers("0 0 "));
        assertEquals("0", ps.print('A'));
        ps.setPolynomial('A', ps.getNumbers("0 0 1 0"));
        assertEquals(ps.print('A'), "1");
        ps.setPolynomial('A', ps.getNumbers("0 1 5 1 5 -1"));
        assertEquals("5x + 5x^(-1)", ps.print('A'));
        ps.setPolynomial('A', ps.getNumbers("-1 5 -1 0 0 4 "));
        assertEquals("-x^(5) - 1", ps.print('A'));

    }

    @Test
    void add() {
        PolynomialSolver ps = new PolynomialSolver();
        int[][] a = {{2,1}};
        int[][] b = {{-2,1}};
        ps.setPolynomial('A', a);
        ps.setPolynomial('B', b);
        ps.add('a', 'b');
        assertEquals("0", ps.print('r'));
        a = new int[][]{{2, 1}};
        b = new int[][]{{2, 0}};
        ps.setPolynomial('A', a);
        ps.setPolynomial('B', b);
        ps.add('a', 'b');
        assertEquals("2x + 2", ps.print('r'));
        a = new int[][]{{2, 1}, {5, 0}};
        b = new int[][]{{2, 0}, {-7, -1}};
        ps.setPolynomial('A', a);
        ps.setPolynomial('B', b);
        ps.add('a', 'b');
        assertEquals("2x + 7 - 7x^(-1)", ps.print('r'));
    }

    @Test
    void subtract() {
        PolynomialSolver ps = new PolynomialSolver();
        int[][] a = {{2,1}};
        int[][] b = {{2,1}};
        ps.setPolynomial('A', a);
        ps.setPolynomial('B', b);
        ps.subtract('a', 'b');
        assertEquals("0", ps.print('r'));
        a = new int[][]{{2, 1}};
        b = new int[][]{{-2, 0}};
        ps.setPolynomial('A', a);
        ps.setPolynomial('B', b);
        ps.subtract('a', 'b');
        assertEquals("2x + 2", ps.print('r'));
        a = new int[][]{{2, 1}, {5, 0}};
        b = new int[][]{{-2, 0}, {7, -1}};
        ps.setPolynomial('A', a);
        ps.setPolynomial('B', b);
        ps.subtract('a', 'b');
        assertEquals("2x + 7 - 7x^(-1)", ps.print('r'));
    }

    @Test
    void multiply() {
        PolynomialSolver ps = new PolynomialSolver();
        int[][] a = {{0,0}};
        int[][] b = {{2,1}};
        ps.setPolynomial('A', a);
        ps.setPolynomial('B', b);
        ps.multiply('a', 'b');
        assertEquals("0", ps.print('r'));
        a = new int[][]{{2, 1}};
        b = new int[][]{{-2, 0}};
        ps.setPolynomial('A', a);
        ps.setPolynomial('B', b);
        ps.multiply('a', 'b');
        assertEquals("-4x", ps.print('r'));
        a = new int[][]{{2, 1}, {5, 0}};
        b = new int[][]{{-2, 0}};
        ps.setPolynomial('A', a);
        ps.setPolynomial('B', b);
        ps.multiply('a', 'b');
        assertEquals("-4x - 10", ps.print('r'));
    }

    @Test
    void clear() {
        PolynomialSolver ps = new PolynomialSolver();
        int[][] a = {{2,1}};
        ps.setPolynomial('a', a);
        assertFalse(ps.isEmpty('a'));
        ps.clearPolynomial('a');
        assertTrue(ps.isEmpty('a'));
        a = new int[][]{{0,0}};
        ps.setPolynomial('a', a);
        assertFalse(ps.isEmpty('a'));
    }

    @Test
    void evaluate() {
        PolynomialSolver ps = new PolynomialSolver();
        int[][] a = {{2,1}};
        ps.setPolynomial('a', a);
        assertEquals(4, ps.evaluatePolynomial('a', 2));
    }
}
