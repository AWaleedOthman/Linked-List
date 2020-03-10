package eg.edu.alexu.csd.datastructure.linkedList.Classes;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//import static org.junit.jupiter.api.Assertions.*;

class PolynomialSolverTest {

    /*just knowing it works; will add tests later trying to cover all cases*/
    @Test
    void setPolynomial() throws FileNotFoundException {

        Scanner sc = new Scanner(new File(System.getProperty("user.dir") +
                "\\src\\eg\\edu\\alexu\\csd\\datastructure\\linkedList\\Tests\\Resources\\Polynomials.txt"));
        String sPoly = sc.nextLine();
        if (sPoly.length() != 1) throw new RuntimeException("Invalid variable name");
        char cPoly = sPoly.charAt(0);

    }

    @Test
    void add () {
        int[][] testArr1 = {{3,4,1,-1}, {6,-2,10,2}};
        int[][] testArr2 = {{1,2,1}, {2,1,0}};
        PolynomialSolver ps = new PolynomialSolver();
        ps.setPolynomial('A', testArr1);
        ps.setPolynomial('B', testArr2);
        System.out.println(ps.print('a'));
        System.out.println(ps.print('b'));
        ps.add('a', 'b');
        System.out.println(ps.print('r'));
        ps.subtract('a', 'b');
        System.out.println(ps.print('r'));
    }

}
