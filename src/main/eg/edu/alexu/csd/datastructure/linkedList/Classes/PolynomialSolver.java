package eg.edu.alexu.csd.datastructure.linkedList.Classes;

import eg.edu.alexu.csd.datastructure.linkedList.Interfaces.IPolynomialSolver;

import java.util.Iterator;
import java.util.Scanner;

public class PolynomialSolver implements IPolynomialSolver {

	private static class Term {
        private Integer coefficient, exponent;

        public Term(int coefficient, int exponent) {
            this.coefficient = coefficient;
            this.exponent = exponent;
        }
    }
	
	@SuppressWarnings("unchecked")
    private SLinkedList<Term>[] polynomials = new SLinkedList[4];

    
    private int[][] toArray (SLinkedList<Term> polynomial) {
        int size = polynomial.size();
        int[][] polyArray = new int[size][2];
        Term tempTerm;
        polynomial.resetNext();
        for (int i = 0; i < size; i++) {
            tempTerm = polynomial.getNext();
            polyArray[i][0] = tempTerm.coefficient;
            polyArray[i][1] = tempTerm.exponent;
        }
        return polyArray;
    }

    public PolynomialSolver() {
        polynomials[0] = new SLinkedList<>(); //A
        polynomials[1] = new SLinkedList<>(); //B
        polynomials[2] = new SLinkedList<>(); //C
        polynomials[3] = new SLinkedList<>(); //R
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

    int[][] getNumbers (String s) {

        int[][] numbers;

        DLinkedList<Term> ll = new DLinkedList<>(Term.class); //felt like this is better than looping using the scanner
        String input = s.replaceAll("[^\\d+.-]", " ");
        try(Scanner sc = new Scanner(input)){ 
	        try {
	        	
	        	if(!sc.hasNext())//no terms entered at all
	        	{
	        		return null;
	        	}
	        	
	        	
	        	Term currentTerm;
	        	Term pastTerm;
	        	int i;
	        	boolean added;
	        	while (sc.hasNext()) {
	            	currentTerm = new Term(Math.round(sc.nextFloat()),Math.round(sc.nextFloat()));
	            	
	            	if(currentTerm.coefficient == 0)//has to be here, in order to read numbers in pairs! forget this comment I just did something stupid
	            		continue;
	            	i = ll.size();
	            	added = false;
	                for(Iterator<Term> itr = ll.iterator(1); itr.hasNext(); i--) {
	                	pastTerm = itr.next();
	                	if(currentTerm.exponent < pastTerm.exponent)//insert here
	                	{
	                		ll.add(i,currentTerm);
	                		added = true;
	                		break;
	                	}
	                	else if(currentTerm.exponent.equals(pastTerm.exponent))//repeated exp
	                	{
	                		pastTerm.coefficient += currentTerm.coefficient;
	                		if(pastTerm.coefficient == 0)
	                			itr.remove();
	                		added = true;
	                		break;
	                	}
	                }
	                if(!added) {//loop ended normally
	                	ll.add(0,currentTerm);
	                }
	            }
	        } catch (Exception e) {
	            throw new RuntimeException("Invalid Input"); //exception originally thrown by Scanner.nextFloat
	        }
	        
	        if(ll.size() == 0)
	        	return new int[][]{{0,0}}; //zero polynomial
	        
        	numbers = new int[ll.size()][2];
            int i = 0;
            for(Term term : ll) {
            	numbers[i][0] = term.coefficient;
            	numbers[i][1] = term.exponent;
            	i++;
            }
	        return numbers;
        }
    }

    @Override
    public void setPolynomial(char poly, int[][] terms) {
        clearPolynomial(poly);
        int index = getIndex(poly);
        for (int[] term : terms) {
            polynomials[index].add(new Term(term[0], term[1]));
        }
    }

    @Override
    public String print(char poly) {
        SLinkedList<Term> myPoly = polynomials[getIndex(poly)];
        if (myPoly.size() == 0) return null; //back to null because now if there is a zero sized polynomial then ti has never been set
        StringBuilder expression = new StringBuilder();
        boolean flag = false; //did we print a coefficient?
        /*print first term*/
        Term tempTerm = myPoly.get(0);
        Integer coefficient = tempTerm.coefficient;
        if (coefficient == 0) return "0"; //zero polynomial
        else if (coefficient < 0) expression.append("-");
        if (coefficient != 1 && coefficient != -1) { expression.append(Math.abs(coefficient)); flag = true;}
        Integer exponent = tempTerm.exponent;
        if (exponent == 0 && !flag) expression.append(1);
        else if (exponent == 1) expression.append("x");
        else if (exponent != 0) expression.append("x^(").append(exponent).append(")");
        /*rest of the terms*/
        while (myPoly.hasNext()) {
            flag = false;
            tempTerm = myPoly.getNext();
            coefficient = tempTerm.coefficient;
            if (coefficient > 0) expression.append(" + ");
            else expression.append(" - "); //because no zero coefficients
            if (coefficient != 1 && coefficient != -1) { expression.append(Math.abs(coefficient)); flag = true;}
            exponent = tempTerm.exponent;
            if (exponent == 0 && !flag) expression.append(1);
            else if (exponent == 1) expression.append("x");
            else if (exponent != 0) expression.append("x^(").append(exponent).append(")");
        }

        return expression.toString();
    }

    @Override
    public void clearPolynomial(char poly) {
        polynomials[getIndex(poly)].clear();
    }

    @Override
    public float evaluatePolynomial(char poly, float value) {
        SLinkedList<Term> tempPoly = polynomials[getIndex(poly)];
        Term tempTerm;
        float result = 0;
        tempPoly.resetNext();
        while (tempPoly.hasNext()) {
            tempTerm = tempPoly.getNext();
            result += tempTerm.coefficient * Math.pow(value, tempTerm.exponent);
        }
        return result;
    }

    //@SuppressWarnings("DuplicatedCode") my compiler doesn't like it i have no idea why
    @Override
    /*
    must check that both polynomials not empty in main
     */
    public int[][] add(char poly1, char poly2) {
        SLinkedList<Term> x = polynomials[getIndex(poly1)];
        SLinkedList<Term> y;
        if(poly1 == poly2) //if both are the same, polynomial, then x and y reference the same object, so when x.hasNext() is false, so is y, although we have not yet dully iterated through y!
        	y = x.sublist(0, x.size()-1);
        else
        	y = polynomials[getIndex(poly2)];
        SLinkedList<Term> res;
        x.resetNext(); y.resetNext();
        res = add(x,y,0);
		polynomials[getIndex('R')] = res;
        return toArray(res);
    }
    
    private SLinkedList<Term> add(SLinkedList<Term> x, SLinkedList<Term> y, int skip) {
    	Term tempx, tempy;
    	SLinkedList<Term> res = new SLinkedList<>();
    	for(int i=0; i<skip; i++) {//skipping unnecessary terms, thanks for the 'resetNext()' feature, Waleed :D!
			res.add(x.getNext());
		}
    	while (x.hasNext() && y.hasNext()) {
            if (x.next().exponent > y.next().exponent) {
                tempx = x.getNext();
                res.add(new Term(tempx.coefficient, tempx.exponent));
            } else if (x.next().exponent < y.next().exponent) {
                tempy = y.getNext();
                res.add(new Term(tempy.coefficient, tempy.exponent));
            } else {
                tempx = x.getNext(); tempy = y.getNext();
                if (tempx.coefficient + tempy.coefficient == 0) continue;
                res.add(new Term(tempx.coefficient + tempy.coefficient, tempx.exponent));
            }
        } 
        while (x.hasNext()) {
            tempx = x.getNext();
            res.add(new Term(tempx.coefficient, tempx.exponent));
        } 
        while (y.hasNext()) {
            tempy = y.getNext();
            res.add(new Term(tempy.coefficient, tempy.exponent));
        }
        if(res.size() == 0)//if we added x+1 to -x-1
        	res.add(new Term(0,0));
        return res;
    }

    //@SuppressWarnings("DuplicatedCode")
    @Override
    public int[][] subtract(char poly1, char poly2) {
        SLinkedList<Term> x = polynomials[getIndex(poly1)];
        SLinkedList<Term> y;
        if(poly1 == poly2) //if both are the same, polynomial, then x and y reference the same object, so when x.hasNext() is false, so is y, although we have not yet dully iterated through y!
        	y = x.sublist(0, x.size()-1);
        else
        	y = polynomials[getIndex(poly2)];
        SLinkedList<Term> res = new SLinkedList<Term>();
        x.resetNext(); y.resetNext(); res.clear();
        Term tempx, tempy;
        do {
            if (x.next().exponent > y.next().exponent) {
                tempx = x.getNext();
                res.add(new Term(tempx.coefficient, tempx.exponent));
            } else if (x.next().exponent < y.next().exponent) {
                tempy = y.getNext();
                res.add(new Term(-1*tempy.coefficient, tempy.exponent));
            } else {
                tempx = x.getNext(); tempy = y.getNext();
                if (tempx.coefficient - tempy.coefficient == 0) continue;
                res.add(new Term(tempx.coefficient - tempy.coefficient, tempx.exponent));
            }
        } while (x.hasNext() && y.hasNext());
        if (x.hasNext()) {
            tempx = x.getNext();
            res.add(new Term(tempx.coefficient, tempx.exponent));
        } else if (y.hasNext()) {
            tempy = y.getNext();
            res.add(new Term(-1*tempy.coefficient, tempy.exponent));
        }
        if(res.size() == 0)
        	res.add(new Term(0,0));
        polynomials[getIndex('r')] = res;
        return toArray(res);
    }

    @Override
	public int[][] multiply(char poly1, char poly2) {
    	SLinkedList<Term> temp;
    	SLinkedList<Term> x = polynomials[getIndex(poly1)];
    	SLinkedList<Term> y;
    	if(poly1 == poly2)
        	y = x.sublist(0, x.size()-1);
        else
        	y = polynomials[getIndex(poly2)];
    	
        if(x.size() < y.size()) {//make y the shorter one
        	temp = x;
        	x = y;
        	y = temp;
        }
        SLinkedList<Term> res = new SLinkedList<>();
		SLinkedList<Term> partialResult = new SLinkedList<>();
		Term tempTerm, term1, term2;
		y.resetNext();
		int skippedTerms = 0;
		for(;y.hasNext();y.getNext(),skippedTerms++) {
			x.resetNext();
			term2 = y.next();
			for(;x.hasNext();x.getNext()) {
				
				term1 = x.next();
				tempTerm = new Term(term1.coefficient*term2.coefficient, term1.exponent+term2.exponent);
				
				partialResult.add(tempTerm);
			}
			res.resetNext();
			partialResult.resetNext();
			res = add(res, partialResult,skippedTerms);
			partialResult.clear();
		}
		polynomials[getIndex('r')] = res;
		return toArray(res);
	}

    public boolean isEmpty(char poly) {
        return polynomials[getIndex(poly)].isEmpty();
    }
}
