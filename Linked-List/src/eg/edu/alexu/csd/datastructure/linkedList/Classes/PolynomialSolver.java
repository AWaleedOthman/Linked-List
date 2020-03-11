package eg.edu.alexu.csd.datastructure.linkedList.Classes;
import java.util.Iterator;
import java.util.NoSuchElementException;

import eg.edu.alexu.csd.datastructure.linkedList.Interfaces.IPolynomialSolver;
public class PolynomialSolver implements IPolynomialSolver{

	public static class Term { //made public for the test
        private Integer coefficient, exponent;

        public Term(int coefficient, int exponent) {
            this.coefficient = coefficient;
            this.exponent = exponent;
        }
        @Override
        public boolean equals(Object o) { //added just for JUnit
        	Term t = (Term) o;
        	if(t.coefficient.equals(this.coefficient)  && t.exponent.equals(this.exponent))
        		return true;
        	return false;
        }
    }
	@Override
	public void setPolynomial(char poly, int[][] terms) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String print(char poly) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clearPolynomial(char poly) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public float evaluatePolynomial(char poly, float value) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int[][] add(char poly1, char poly2) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int[][] subtract(char poly1, char poly2) {
		// TODO Auto-generated method stub
		return null;
	}

	public DLinkedList<Term> multiply(DLinkedList<Term> firstPolynomial, DLinkedList<Term> secondPolynomial){//TEST
		DLinkedList<Term> resultPolynomial = new DLinkedList<Term>(Term.class);
		DLinkedList<Term> partialResult = new DLinkedList<Term>(Term.class);
		Term tempTerm;
		//boolean firstPass = true;
		for(Term term2 : secondPolynomial) { //again, we will replace this, it's only possible because of iterable
			
			for(Term term1 : firstPolynomial) {
				
				tempTerm = new Term(term1.coefficient*term2.coefficient, term1.exponent+term2.exponent);
				
				/*if(firstPass) //first pass,, base case
				{
					resultPolynomial.add(tempTerm);
					
				}
				else//general case, multiply a term2 by term1 and find its position in resultPolynomial; starting from largestExpIndex till the end
				{
					partialResult.add(tempTerm);
				}*/
				partialResult.add(tempTerm);
			}
			resultPolynomial = mergeSort(resultPolynomial, partialResult);
			partialResult.clear();
		}
		return resultPolynomial;
	}
	@Override
	public int[][] multiply(char poly1, char poly2) {
		//initialising dummy polynomials just so that my compiler wouldn't throw errors at my face
		DLinkedList<Term> firstPolynomial = new DLinkedList<Term>(Term.class);
		DLinkedList<Term> secondPolynomial = new DLinkedList<Term>(Term.class);//preferrably, secondPolynomial should be the shorter one
		DLinkedList<Term> resultPolynomial = new DLinkedList<Term>(Term.class);
		DLinkedList<Term> partialResult = new DLinkedList<Term>(Term.class);
		Term tempTerm;
		boolean firstPass = true;
		for(Term term2 : secondPolynomial) { //again, we will replace this, it's only possible because of iterable
			
			for(Term term1 : firstPolynomial) {
				
				tempTerm = new Term(term1.coefficient*term2.coefficient, term1.exponent+term2.exponent);
				
				if(firstPass) //first pass,, base case
				{
					resultPolynomial.add(tempTerm);
					
				}
				else//general case, multiply a term2 by term1 and find its position in resultPolynomial; starting from largestExpIndex till the end
				{
					partialResult.add(tempTerm);
				}
			}
			resultPolynomial = mergeSort(resultPolynomial, partialResult);
			partialResult.clear();
		}
		return null;
	}
	
	
	
	private DLinkedList<Term> mergeSort(DLinkedList<Term> list1, DLinkedList<Term> list2) {
		Iterator<Term> list1Iterator = list1.iterator();
		Iterator<Term> list2Iterator = list2.iterator();
		DLinkedList<Term> result = new DLinkedList<Term>(Term.class);
		Term next1 = new Term(0,0), next2 = new Term(0,0);
		boolean stepped1 = true;
		boolean stepped2 = true;
		
		if(list1.size() == 0) return list2.sublist(0,list2.size()-1); //partial result will never be empty
			
		
		do {
			if(stepped1) {
				next1 = list1Iterator.next();
				stepped1 = false;
			}
				
			if(stepped2) {
				next2 = list2Iterator.next();
				stepped2 = false;
			}
			
			if(next1.exponent>next2.exponent) {
				result.add(next1);
				stepped1 = true;
			}
			else if(next1.exponent == next2.exponent) {
				result.add(new Term(next1.coefficient+next2.coefficient, next1.exponent));
				stepped1 = true;
				stepped2 = true;
			}
			else {
				result.add(next2);
				stepped2 = true;
			}
		}while(list1Iterator.hasNext() && list2Iterator.hasNext());
		
		
		while(list1Iterator.hasNext()) {
			result.add(list1Iterator.next());
		}
		while(list2Iterator.hasNext()) {
			result.add(list2Iterator.next());
		}
		return result;
	}
}
