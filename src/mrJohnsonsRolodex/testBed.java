package mrJohnsonsRolodex;

import java.util.ListIterator;
import java.util.Scanner;

public class testBed {

	public static void testRunner() {
		Scanner userIn = new Scanner(System.in);
		Runner newRunner = new Runner();
		newRunner.createRunner(userIn);
		newRunner.printSheet();
		
		userIn.close();
	}

	public static void testRandomRunner() {
		Runner newRunner = new Runner();
		newRunner.generateRandomRunner();
		newRunner.printSheet();
	}

	public static void testRolodex(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner userIn = new Scanner(System.in);
		String response;
		int currIdx;
		
		Rolodex newDex = new Rolodex();
		newDex.populate();
		ListIterator<Runner> itr = newDex.getRollingList();

		Runner newRunner;
		Runner currRunner = itr.next();
		
		newDex.printIndex();
		
		response = "";
		currRunner.printSummary();
		while (!response.equalsIgnoreCase("X")) {
			System.out.print("N: Next. P: Previous. D: Detail. A: Add. R: Remove. I: Index. X: eXit. ? ");
			
			response = userIn.nextLine();
			
			if (!response.isEmpty()) {
				switch (response.toUpperCase().charAt(0)) {
				case 'A':
					newRunner = new Runner();
					newRunner.createRunner(userIn);
					
					newDex.add(newRunner);
					currIdx = newDex.indexOf(newRunner);
					itr = newDex.getRollingList();
					for (int i = 0; i < currIdx + 1; i++) {
						currRunner = itr.next();
					}
					currRunner.printSummary();
					break;
					
				case 'D':
					currRunner.printSheet();
					break;
					
				case 'I':
					newDex.printIndex();
					break;
					
				case 'N':
					if (itr.hasNext()) {
						currRunner = itr.next();
					}
					currRunner.printSummary();
					break;
				
				case 'P':
					if (itr.hasPrevious()) {
						currRunner = itr.previous();
					}
					currRunner.printSummary();
					break;
				
				case 'R':
					currIdx = newDex.indexOf(currRunner);
					newDex.remove(currIdx);
					itr = newDex.getRollingList();
					for (int j = 0; j < currIdx - 1; j++) {
						currRunner = itr.next();
					}
					currRunner.printSummary();
					System.out.println();
					break;
					
				case 'X':
					response = "X";
					break;
					
				default:
					currRunner.printSummary();
				}
				
			}
			
//			System.out.println();
		}
		
		userIn.close();
	}

}
