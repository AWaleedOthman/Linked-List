/*package eg.edu.alexu.csd.datastructure.linkedList;

import java.util.Scanner;

public class CoreCode {
	public static void main(String[] args) {
		int choice = -1;
		boolean validChoice = false;
		PolynomialSolver ps = new PolynomialSolver();
		int[][] terms;
		try(Scanner s = new Scanner(System.in)){
			do {
				System.out.println("Please choose an action\r\n" + 
						"-----------------------\r\n" + 
						"1- Set a polynomial variable\r\n" + 
						"2- Print the value of a polynomial variable\r\n" + 
						"3- Add two polynomials\r\n" + 
						"4- Subtract two polynomials\r\n" + 
						"5- Multiply two polynomials"+"6- Evaluate a polynomial at some point"+
						"7- Clear a polynomial variable"+
						"8- Exit.."+
						"===================================================================="); //copied and pasted because life is too short
				while(!validChoice) {
					validChoice = true;
					if(s.hasNextInt() ) {
						choice = s.nextInt();
					}else {
						choice = -1;
					}
					switch(choice) {
					case 1:
						char poly;
						poly = ps.choosePolynomial();
						terms = ps.inputTerms();
						ps.setPolynomial(poly, terms);
						break;
					case 2:
						char poly;
						poly = ps.choosePolynomial();
						System.out.printf("Value in %c: %s\n",poly,ps.print(poly));
						break;
					case 3:
						char poly1, poly2;
						poly1 = ps.choosePolynomial();
						poly2 = ps.choosePolynomial();
						ps.add(poly1, poly2);
						break;
					case 4:
					case 5:
					case 6:
					case 7:
						char poly
					case 8:
						break;
					default:
						validChoice = false;
						System.out.println("Invalid choice!");
					}
				}
				
			}while(choice != 8);
		}
		
	}
}
*/