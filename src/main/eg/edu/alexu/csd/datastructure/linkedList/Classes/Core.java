package eg.edu.alexu.csd.datastructure.linkedList.Classes;

import java.util.Scanner;

public class Core {
    public static void main (String[] args) { //changes to scanner usage: 
    	//1. try with resources to prevent resource leak
    	//2. pass sc as an argument to methods that read from System.in because https://stackoverflow.com/questions/13042008/java-util-nosuchelementexception-scanner-reading-user-input
    	//TL;DR: scanner must be closed to free the input stream but it is not very reliable to open and close repeatedly especially if the input stream is Closeable 
        PolynomialSolver ps = new PolynomialSolver();
        try(Scanner sc = new Scanner(System.in)){
	        String chooseOne = "Insert the variable name: A, B, C or R:";
	        String chooseFirst = "Insert first operand variable name: A, B or C";
	        String chooseSecond = "Insert second operand variable name: A, B or C";
	        int input = menu(sc);
	        char poly, poly2;
	        while (input != 8) { //exit
	            switch (input) {
	
	                case 1: //set
	                	int[][] numbers;
	                    System.out.println("Insert the variable name: A, B or C:");
	                    poly = getChar(false,sc);
	                    System.out.println("Insert the polynomial terms in the form: " +
	                            "(coeff1, exponent1), (coeff2, exponent2), ..");
	                    numbers = ps.getNumbers(sc.nextLine());
	                    if(numbers == null)//no elements
	                    {	
	                    	System.out.println("Please enter terms");
	                    	break;
	                    }
	                    ps.setPolynomial(poly, numbers);
	                    System.out.println("Polynomial " + Character.toUpperCase(poly) + " has been set.");
	                    System.out.println("====================================================================");
	                    break;
	                case 2: //print
	                    System.out.println(chooseOne);
	                    poly = getChar(true,sc);
	                    while (ps.isEmpty(poly)) {
	                        System.out.println("Variable not set");
	                        System.out.println(chooseOne);
	                        poly = getChar(true,sc);
	                    }
	                    System.out.println(Character.toUpperCase(poly) + " value in "
	                            + Character.toUpperCase(poly) + " is: " + ps.print(poly));
	                    System.out.println("====================================================================");
	                    break;
	                case 3: //add
	                    System.out.println(chooseFirst);
	                    poly = getChar(true,sc);
	                    while (ps.isEmpty(poly)) {
	                        System.out.println("Variable not set");
	                        System.out.println(chooseOne);
	                        poly = getChar(true,sc);
	                    }
	                    System.out.println(chooseSecond);
	                    poly2 = getChar(true,sc);
	                    while (ps.isEmpty(poly2)) {
	                        System.out.println("Variable not set");
	                        System.out.println(chooseOne);
	                        poly2 = getChar(true,sc);
	                    }
	                    ps.add(poly, poly2);
	                    System.out.println("Result set in R: " + ps.print('r'));
	                    System.out.println("====================================================================");
	                    break;
	                case 4: //subtract
	                    System.out.println(chooseFirst);
	                    poly = getChar(true,sc);
	                    while (ps.isEmpty(poly)) {
	                        System.out.println("Variable not set");
	                        System.out.println(chooseOne);
	                        poly = getChar(true,sc);
	                    }
	                    System.out.println(chooseSecond);
	                    poly2 = getChar(true,sc);
	                    while (ps.isEmpty(poly2)) {
	                        System.out.println("Variable not set");
	                        System.out.println(chooseOne);
	                        poly2 = getChar(true,sc);
	                    }
	                    ps.subtract(poly, poly2);
	                    System.out.println("Result set in R: " + ps.print('r'));
	                    System.out.println("====================================================================");
	                    break;
	                case 5: //multiply
	                    System.out.println(chooseFirst);
	                    poly = getChar(true,sc);
	                    while (ps.isEmpty(poly)) {
	                        System.out.println("Variable not set");
	                        System.out.println(chooseOne);
	                        poly = getChar(true,sc);
	                    }
	                    System.out.println(chooseSecond);
	                    poly2 = getChar(true,sc);
	                    while (ps.isEmpty(poly2)) {
	                        System.out.println("Variable not set");
	                        System.out.println(chooseOne);
	                        poly2 = getChar(true,sc);
	                    }
	                    ps.multiply(poly, poly2);
	                    System.out.println("Result set in R: " + ps.print('r'));
	                    System.out.println("====================================================================");
	                    break;
	                case 6: //evaluate
	                    System.out.println(chooseOne);
	                    poly = getChar(true,sc);
	                    while (ps.isEmpty(poly)) {
	                        System.out.println("Variable not set");
	                        System.out.println(chooseOne);
	                        poly = getChar(true,sc);
	                    }
	                    float point;
	                    try {
	                        System.out.println("Please enter the point at which you want to evaluate " + poly);
	                        point = sc.nextFloat();
	                        sc.nextLine();
	                    } catch (Exception e) {
	                        throw new RuntimeException("Invalid Input");
	                    }
	                    System.out.println(Character.toUpperCase(poly) + " value at "
	                            + point + " is: " + ps.evaluatePolynomial(poly,point));
	                    System.out.println("====================================================================");
	                    break;
	                case 7: //clear
	                    System.out.println(chooseOne);
	                    poly = getChar(true, sc);
	                    ps.clearPolynomial(poly);
	                    break;
	                default:
	                    throw new RuntimeException("Invalid Input");
	            }
	            input = menu(sc);
	        }
    	}
    }

    /*
    Main Menu
     */
    private static int menu (Scanner sc) {
    	int inputInt;
        System.out.println("Please choose an action\n" +
                "-----------------------\n" +
                "1- Set a polynomial variable\n" +
                "2- Print the value of a polynomial variable\n" +
                "3- Add two polynomials\n" +
                "4- Subtract two polynomials\n" +
                "5- Multiply two polynomials\n"+
                "6- Evaluate a polynomial at some point\n"+
                "7- Clear a polynomial variable\n"+
                "8- Exit..\n"+
                "====================================================================");
        String input = sc.nextLine().trim(); //trims any whitespace
        try {
            inputInt = Integer.parseInt(input); //if user inputs a string with char other than [1-9] and whitespace
        } catch (Exception e) {
            throw new RuntimeException("Invalid Input");
        }
	    return inputInt;
    }

    private static char getChar (boolean rIncluded, Scanner sc) { //rIncluded means we accept r (result) as input
	        String input = sc.nextLine().trim();
	        if (input.length() > 1) throw new RuntimeException("Invalid Input"); //Accepts onlt one char
	        char cInput = Character.toLowerCase(input.charAt(0));
	        if (cInput != 'a' && cInput != 'b' && cInput != 'c' && (!rIncluded || (cInput != 'r')) )
	            throw new RuntimeException("Invalid Input");
	        return cInput;
    }

}
