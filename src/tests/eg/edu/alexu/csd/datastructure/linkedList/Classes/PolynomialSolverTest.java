package eg.edu.alexu.csd.datastructure.linkedList.Classes;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PolynomialSolverTest {

    /*just knowing it works; will add tests later trying to cover all cases*/
    //@Test
    /*void setPolynomial() throws FileNotFoundException {

    	try(Scanner sc = new Scanner(new File(System.getProperty("user.dir") +
                "\\src\\eg\\edu\\alexu\\csd\\datastructure\\linkedList\\Tests\\Resources\\Polynomials.txt")); ){
    		String sPoly = sc.nextLine();
            if (sPoly.length() != 1) throw new RuntimeException("Invalid variable name");
    	}

    }*/

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
        assertFalse(ps.isEmpty('a'));
        ps.clearPolynomial('a');
        assertTrue(ps.isEmpty('a'));
        ps.setPolynomial('a', testArr2);
        ps.setPolynomial('b', testArr1);
        assertFalse(ps.isEmpty('a'));
        System.out.println();
        System.out.println(ps.print('a'));
        System.out.println(ps.print('b'));
    }

    @Test
    void multiply() {
        System.out.println();
        int[][] testArr1 = {{3,4,1,-1}, {6,-2,10,2}};
        int[][] testArr2 = {{1,2,1}, {2,1,0}};
        PolynomialSolver ps = new PolynomialSolver();
        ps.setPolynomial('A', testArr1);
        ps.setPolynomial('B', testArr2);
        System.out.print("A=");
        System.out.println(ps.print('a'));
        System.out.print("B=");
        System.out.println(ps.print('b'));
        ps.multiply('a', 'b');
        System.out.print("A*B=");
        System.out.println(ps.print('r'));
    }

	/*@Test
	void test() {
		DLinkedList<PolynomialSolver.Term> p1 = new DLinkedList<PolynomialSolver.Term>(PolynomialSolver.Term.class);
		DLinkedList<PolynomialSolver.Term> p2;
		DLinkedList<PolynomialSolver.Term> p3 = new DLinkedList<PolynomialSolver.Term>(PolynomialSolver.Term.class);

		p1.add(new PolynomialSolver.Term(1,1));
		p1.add(new PolynomialSolver.Term(1,0));
		p2 = p1.sublist(0, 1);

		p3.add(new PolynomialSolver.Term(1,2));
		p3.add(new PolynomialSolver.Term(2,1));
		p3.add(new PolynomialSolver.Term (1,0));
		assertIterableEquals(p3,p.multiply(p1,p2));
	}*/

}
