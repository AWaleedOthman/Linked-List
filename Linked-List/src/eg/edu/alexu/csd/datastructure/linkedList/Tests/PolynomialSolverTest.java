package eg.edu.alexu.csd.datastructure.linkedList.Tests;

import static org.junit.jupiter.api.Assertions.*;

import eg.edu.alexu.csd.datastructure.linkedList.Classes.DLinkedList;
import eg.edu.alexu.csd.datastructure.linkedList.Classes.PolynomialSolver;

import org.junit.jupiter.api.Test;

class PolynomialSolverTest {

	PolynomialSolver p = new PolynomialSolver();
	
	
	
	@Test
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
	}

}
