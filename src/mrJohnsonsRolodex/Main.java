package mrJohnsonsRolodex;

import java.util.ListIterator;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner userIn = new Scanner(System.in);
		String response;
		int currIdx;
		
		Rolodex newDex = new Rolodex();
		newDex.populate();
		ListIterator<Runner> itr = newDex.getRollingList();

		Runner newRunner;
		Runner currRunner = itr.next();
		Runner displayedRunner = null;
		
		newDex.printIndex();
		
		response = "";
		currRunner.printSummary();
		while (!response.equalsIgnoreCase("X")) {
			System.out.print("#: Skip to... N: Next. P: Previous. D: Detail. A: Add. R: Remove. I: Index. X: eXit. ? ");
			
			response = userIn.nextLine();
			if (!response.isEmpty()) {
				if (Character.isDigit(response.charAt(0))) {
					int skipIdx = Integer.parseInt(response) - 1;
					if (skipIdx >= newDex.getSize()) {
						skipIdx = newDex.getSize() - 1;
					} else if (skipIdx < 1) {
						skipIdx = 0;
					}
					currIdx = itr.nextIndex() - 1;
					while (currIdx != skipIdx) {
						if (currIdx > skipIdx) {
							currIdx = itr.previousIndex();
							currRunner = itr.previous();
						} else if (currIdx < skipIdx) {
							currIdx = itr.nextIndex();
							currRunner = itr.next();
						}
					}
					if (currRunner != displayedRunner) {
						currRunner.printSummary();
						displayedRunner = currRunner;
					}
					response = " ";
				}

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
					if (currRunner == displayedRunner) {
						currRunner = itr.next();
					}

					currRunner.printSummary();
					displayedRunner = currRunner;
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
						if (currRunner == displayedRunner) {
							currRunner = itr.next();
						}
						
						currRunner.printSummary();
						displayedRunner = currRunner;
					}
					break;
				
				case 'P':
					if (itr.hasPrevious()) {
						currRunner = itr.previous();
						if (currRunner == displayedRunner) {
							currRunner = itr.previous();
						}

						currRunner.printSummary();	
						displayedRunner = currRunner;
					}
					break;
				
				case 'R':
					currIdx = newDex.indexOf(currRunner);
					newDex.remove(currIdx);
					itr = newDex.getRollingList();
					for (int j = 0; j < currIdx - 1; j++) {
						currRunner = itr.next();
					}
					if (currRunner == displayedRunner) {
						currRunner = itr.next();
					}

					currRunner.printSummary();
					System.out.println();
					displayedRunner = currRunner;
					break;
					
				case 'X':
					response = "X";
					break;
					
				default:
				}
			}
		}
		
		userIn.close();
	}

}
