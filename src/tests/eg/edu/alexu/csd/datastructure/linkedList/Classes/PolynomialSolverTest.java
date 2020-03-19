package eg.edu.alexu.csd.datastructure.linkedList.Classes;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class PolynomialSolverTest {
    @Test
    void print() {
        PolynomialSolver ps = new PolynomialSolver();
        ps.setPolynomial('A', ps.getNumbers("0,5"));
        System.out.println(ps.print('A'));

    }

    @Test
    void add () {

    }

    @Test
    void multiply() {

    }

}
