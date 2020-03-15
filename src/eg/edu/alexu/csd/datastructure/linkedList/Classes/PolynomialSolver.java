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
	
	@SuppressWarnings("unchecked")
    private SLinkedList<Term>[] polynomials = new SLinkedList[4];

    
    private int[][] toArray (SLinkedList<Term> polynomial) {
        int size = polynomial.size();
        int[][] polyArray = new int[2][size];
        Term tempTerm;
        polynomial.resetNext();
        for (int i = 0; i < size; i++) {
            tempTerm = polynomial.getNext();
            polyArray[0][i] = tempTerm.coefficient;
            polyArray[1][i] = tempTerm.exponent;
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

    /*
    TODO
    needs to be changed to sort while taking input from user
    also need to handle case if user inputs two terms with same exponent while sorting
    will probably use SLinkedList to take input and sort then change to int[][]
     */

    @Override
    public void setPolynomial(char poly, int[][] terms) {
        clearPolynomial(poly);
        SLinkedList<Term> temp = new SLinkedList<Term>();
        temp.add(new Term(terms[0][0], terms[1][0]));
        int index;
        boolean added;
        //sort(terms); remove zero coefficients at the end so that if e.g. 2x and -2x are in the same expression they will add up to zero
        for (int i = 1; i < terms[0].length; i++) {
        	index = 0;
        	added = false;
        	for(temp.resetNext(); temp.hasNext(); temp.getNext()) {
        		if(temp.next().exponent > terms[1][i]) {//find place in list
        			index++;
        			continue;
        		} 
        		else if(temp.next().exponent == terms[1][i]) {//repeated exponent
        			temp.next().coefficient+= terms[0][i]; //skip this term
        			added = true;
        			break;
        		}
        		else {//add new term here
        			temp.add(index, new Term(terms[0][i]/*coefficient*/, terms[1][i]/*exponent*/));
        			added = true;
        			break;
        		}
        	}
        	if(!added)//we continued till the very end of the loop
        		temp.add(new Term(terms[0][i]/*coefficient*/, terms[1][i]/*exponent*/));
        }
        index = this.getIndex(poly); //here index has a different purpose than above
        for(temp.resetNext(); temp.hasNext(); temp.getNext()) {
        	if(temp.next().coefficient!=0)
        		polynomials[index].add(temp.next());
        }
    }

    @Override
    public String print(char poly) {
        SLinkedList<Term> tempPoly = polynomials[getIndex(poly)];
        if (tempPoly.size() == 0) return "0"; //why is this null? this should be zero because if I subtract 2 equal polynomials, it should print 0
        Term tempTerm = tempPoly.get(0);
        Integer tempCo = tempTerm.coefficient; //because it is used A LOT
        String sExponent = tempTerm.exponent == 0? "" : (tempTerm.exponent == 1? "x" :
                ("x^(" + tempTerm.exponent + ")"));
        StringBuilder expression;
        if (tempCo > 0) { //+ve coefficient
            expression = new StringBuilder((tempCo == 1 ? "" : tempCo) + sExponent);
        } else if (tempCo < 0) { //-ve coefficient
            expression = new StringBuilder((tempCo == -1 ? " - " : tempCo) + sExponent);
        } else { //coefficient = zero
            expression = new StringBuilder();
        }

        for (int i = 1; i < tempPoly.size(); i++) {

            tempTerm = tempPoly.getNext();
            tempCo = tempTerm.coefficient;
            sExponent = tempTerm.exponent == 0? "" : (tempTerm.exponent == 1? "x" :
                    ("x^(" + tempTerm.exponent + ")"));

            if (tempCo > 0) { //+ve coefficient
                expression.append(" + ");
                expression.append(sExponent.equals("")? tempCo:(tempCo == 1 ? "" : tempCo)).append(sExponent);

            } else if (tempCo < 0) { //-ve coefficient
                expression.append(" - ");
                expression.append(sExponent.equals("")? -1*tempCo:(tempCo == -1 ? "" : -1 * tempCo)).append(sExponent);

            }
            //zero coefficient : continue;
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
    TODO
    must check that both polynomials not empty in main
    will probably need to create another method for that purpose
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
        return res;
    }

    //@SuppressWarnings("DuplicatedCode")
    @Override
    public int[][] subtract(char poly1, char poly2) {
        SLinkedList<Term> x = polynomials[getIndex(poly1)];
        SLinkedList<Term> y = polynomials[getIndex(poly2)];
        SLinkedList<Term> res = polynomials[getIndex('R')];
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
