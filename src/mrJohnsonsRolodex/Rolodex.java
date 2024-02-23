package mrJohnsonsRolodex;

import java.util.ListIterator;
import java.util.LinkedList;

public class Rolodex {
	private LinkedList<Runner> runners = new LinkedList<Runner>();
	
	final private int POP_COUNT = 10;
	
	public int getSize() {
		return this.runners.size();
	}
	
	public void add(Runner r) {
		int insertIdx = 0;
		while (insertIdx < this.runners.size()) {
			if (this.runners.get(insertIdx).name.compareTo(r.name) > 0) {
				break;
			}
			insertIdx++;
		}
		this.runners.add(insertIdx, r);
	}
	
	public void remove(int idx) {
		this.runners.remove(idx);
	}
	
	public void populate() {
		Runner newRunner;
		for (int i = 0; i < POP_COUNT; i++) {
			newRunner = new Runner();
			newRunner.generateRandomRunner();
			this.add(newRunner);
		}
	}
	
	public int indexOf(Runner r) {
		return this.runners.indexOf(r);
	}

	public ListIterator<Runner> getRollingList() {
		return this.runners.listIterator();
	}
	
	public void printIndex() {
		String currLine;
		Runner currRunner;
		String currIdx, currName, currHandle, currMetatype, currJob;
		for (int i = 0; i < runners.size(); i++) {
			currRunner = this.runners.get(i);
			currIdx = Integer.toString(i + 1);
			currName = currRunner.name;
			currHandle = currRunner.handle;
			currMetatype = currRunner.metatypeName();
			currJob = currRunner.archetypeName();
			currLine = currIdx + " " + currName + " A.K.A. " + currHandle + " the " + currMetatype + " " + currJob;
			System.out.println(currLine);
		}
	}
	
	public void printCard(int idx) {
		this.runners.get(idx).printSummary();
	}
	
	public void printDetail(int idx) {
		this.runners.get(idx).printSheet();
	}
}
