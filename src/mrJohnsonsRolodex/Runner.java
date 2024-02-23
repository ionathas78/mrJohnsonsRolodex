/**/
package mrJohnsonsRolodex;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

/**
 * 
 */
public class Runner {

	public Random dicebag = new Random();
	
	public String name = "";
	public String handle = "";
	public SRMetatype species = SRMetatype.HUMAN;
	public SRArchetypes archetype = SRArchetypes.CITIZEN;
	
	public String phoneNumber = "";
	public String addressStreet = "";
	public String addressCity = "Seattle";
	public String addressEmail = "";
	
	public int body = 3;
	public int quickness = 3;
	public int strength = 3;
	public int charisma = 3;
	public int intelligence = 3;
	public int willpower = 3;
	public int maxBody = 6;
	public int maxQuickness = 6;
	public int maxStrength = 6;
	public int maxCharisma = 6;
	public int maxIntelligence = 6;
	public int maxWillpower = 6;
	
	public double essence = 6.0;
	public int magic = 0;
	
	private ArrayList<Skill> skills = new ArrayList<Skill>();
	private ArrayList<Cyberware> cyber = new ArrayList<Cyberware>();
	
	public int reaction() {
		int retVal = (this.intelligence + this.quickness) / 2;
		if (this.hasCyberware(SRCyberware.WIREDREFLEXESLV3)) {
			retVal += 6;
		} else if (this.hasCyberware(SRCyberware.WIREDREFLEXESLV2)) {
			retVal += 4;
		} else if (this.hasCyberware(SRCyberware.WIREDREFLEXESLV1)) {
			retVal += 2;
		}
		
		return retVal;		
	}
	
	public int initiativeDice() {
		int retVal = 1;
		if (this.hasCyberware(SRCyberware.WIREDREFLEXESLV3)) {
			retVal += 3;
		} else if (this.hasCyberware(SRCyberware.WIREDREFLEXESLV2)) {
			retVal += 2;
		} else if (this.hasCyberware(SRCyberware.WIREDREFLEXESLV1)) {
			retVal += 1;
		}
	
		return retVal;
	}
	
	public String initiative() {
		String retVal = "";
		int reaction = (this.intelligence + this.quickness) / 2;
		int initDice = 1;
		
		if (this.hasCyberware(SRCyberware.WIREDREFLEXESLV3)) {
			reaction += 6;
			initDice += 3;
		} else if (this.hasCyberware(SRCyberware.WIREDREFLEXESLV2)) {
			reaction += 4;
			initDice += 2;
		} else if (this.hasCyberware(SRCyberware.WIREDREFLEXESLV1)) {
			reaction += 2;
			initDice += 1;
		}
	
		retVal = initDice + "D6 + " + reaction;
		return retVal;
	}
	
	//	Getters, mutators
	
	public ArrayList<Skill> getSkills() {
		return this.skills;
	}
	
	public ArrayList<Cyberware> getCyberwareList() {
		return this.cyber;
	}
	
	public Skill getSkill(int idx) {
		return this.skills.get(idx);
	}
	
	public Cyberware getCyberware(int idx) {
		return this.cyber.get(idx);
	}
	
	public void setSkill(int idx, Skill val) {
		this.skills.set(idx, val);
	}
	
	public void setCyberware(int idx, Cyberware val) {
		this.cyber.set(idx,  val);
	}
	
	public double getTotalEssenceCost() {
		double essenceSum = 0.0;
		
		for (int i = 0; i < this.cyber.size(); i++) {
			essenceSum += this.cyber.get(i).getEssenceCost();
		}
		
		return essenceSum;
	}
	
	//	Public Methods
	
	public void addSkill(Skill newSkill) {
		this.skills.add(newSkill);
	}
	
	public void addSkill(SRSkills baseSkill, int rating) {
		Skill newSkill = new Skill(baseSkill, rating);
		this.skills.add(newSkill);
	}
	
	public void addSkill(SRSkills baseSkill, String concentration, int rating) {
		Skill newSkill = new Skill(baseSkill, concentration, rating);
		this.skills.add(newSkill);
	}
	
	public void addSkill(SRSkills baseSkill, String concentration, String specialization, int rating) {
		Skill newSkill = new Skill(baseSkill, concentration, specialization, rating);
		this.skills.add(newSkill);
	}

	public void removeSkill(int idx) {
		this.skills.remove(idx);
	}
	
	public void addCyberware(Cyberware newCyber) {
		this.cyber.add(newCyber);
	}
	
	public void addCyberware(SRCyberware baseType, double essenceCost) {
		Cyberware newCyber = new Cyberware(baseType, essenceCost);
		this.cyber.add(newCyber);
	}
	
	public void addCyberware(SRCyberware baseType, double essenceCost, int rating) {
		Cyberware newCyber = new Cyberware(baseType, essenceCost, rating);
		this.cyber.add(newCyber);
	}

	public void addCyberware(SRCyberware baseType, double essenceCost, CyberGrades grade) {
		Cyberware newCyber = new Cyberware(baseType, essenceCost, grade);
		this.cyber.add(newCyber);
	}
	
	public void addCyberware(SRCyberware baseType, double essenceCost, int rating, CyberGrades grade) {
		Cyberware newCyber = new Cyberware(baseType, essenceCost, rating, grade);
		this.cyber.add(newCyber);
	}
	
	public void addCyberware(SRCyberware baseType, double essenceCost, int rating, String brand, CyberGrades grade) {
		Cyberware newCyber = new Cyberware(baseType, essenceCost, rating, brand, grade);
		this.cyber.add(newCyber);
	}
	
	public void createRunner(Scanner scnr) {
		String response;
		
		System.out.println("Making a new runner:");

		// Name & Alias
		System.out.print("Runner's name? ");
		response = scnr.nextLine();
		if (response.isBlank()) { 
//			scnr.close();
//			return; 
		}
		this.name = response;
		
		System.out.println();
		System.out.print("A.K.A.: ");
		response = scnr.nextLine();
		this.handle = response;
		
		int randomStreetNumber = dicebag.nextInt(100, 9999);
		this.addressEmail = getRandomEmail();
		this.addressCity = getRandomCity();
		this.phoneNumber = getRandomPhoneNumber();
		this.addressStreet = Integer.toString(randomStreetNumber) + " " + getRandomStreetName();
		
		//	Metatype
		
		System.out.println();
		System.out.println("1 - Human");
		System.out.println("2 - Dwarf");
		System.out.println("3 - Elf");
		System.out.println("4 - Ork");
		System.out.println("5 - Troll");
		System.out.print("Runner metatype? ");
		response = scnr.next();
		
		switch (response.toUpperCase()) {
		case "2":
			this.species = SRMetatype.DWARF;
			break;
		case "3":
			this.species = SRMetatype.ELF;
			break;
		case "4":
			this.species = SRMetatype.ORK;
			break;
		case "5":
			this.species = SRMetatype.TROLL;
			break;
		default:
			this.species = SRMetatype.HUMAN;
		}
		this.applyMetatype();
		this.applyMetahumanVariation();
		
		//	Archetype
				
		System.out.println();
		System.out.println("1 - Cop");
		System.out.println("2 - Decker");
		System.out.println("3 - Face");
		System.out.println("4 - Ganger");
		System.out.println("5 - Mage");
		System.out.println("6 - Rigger");
		System.out.println("7 - Rocker");
		System.out.println("8 - Shaman");
		System.out.println("9 - Street Samurai");
		System.out.print("Runner archetype? ");
		response = scnr.next();
		
		switch (response.toUpperCase()) {
		case "1":
			this.archetype = SRArchetypes.COP;
			break;
			
		case "2":
			this.archetype = SRArchetypes.DECKER;
			break;
			
		case "3":
			this.archetype = SRArchetypes.FACE;
			break;
			
		case "4": 
			this.archetype = SRArchetypes.GANGER;
			break;
			
		case "5": 
			this.archetype = SRArchetypes.MAGE;
			break;
			
		case "6":
			this.archetype = SRArchetypes.RIGGER;
			break;
			
		case "7":
			this.archetype = SRArchetypes.ROCKER;
			break;
			
		case "8":
			this.archetype = SRArchetypes.SHAMAN;
			break;

		default:
			this.archetype = SRArchetypes.STREETSAMURAI;
		}
		this.applyArchetype();
		this.checkAttributes();
		
		//	Professionalism Rating
		
		System.out.println();
		System.out.println("1 - Average");
		System.out.println("2 - Semi-trained");
		System.out.println("3 - Trained");
		System.out.println("4 - Professional");
		System.out.print("Runner professionalism rating? ");
		response = scnr.next();

		switch(response.toUpperCase()) {
		case "2":
			this.skills.get(0).setRating(4);
			this.skills.get(1).setRating(3);
			this.skills.get(2).setRating(3);
			this.skills.get(3).setRating(2);
			break;
		case "3":
			this.skills.get(0).setRating(5);
			this.skills.get(1).setRating(4);
			this.skills.get(2).setRating(4);
			this.skills.get(3).setRating(3);
			break;
		case "4":
			this.skills.get(0).setRating(6);
			this.skills.get(1).setRating(5);
			this.skills.get(2).setRating(5);
			this.skills.get(3).setRating(4);
			break;
		default:
			this.skills.get(0).setRating(3);
			this.skills.get(1).setRating(3);
			this.skills.get(2).setRating(2);
			this.skills.get(3).setRating(2);
			break;
		}
				
		//	Augments
		
		int noImplants = 0;
		SRCyberware[] cyberList = SRCyberware.values(); 
		SRCyberware cyberType;
		double cyberEss;
		int cyberRating;
		String cyberBrand = "";
		double totalEssence = 0.0;
		
		System.out.println();
		System.out.println("0 - None known");
		System.out.println("1 - One implant");
		System.out.println("2 - A few implants");
		System.out.println("3 - Bleeding edge");
		System.out.print("Runner known cyberware? ");
		response = scnr.next();

		switch (response.toUpperCase()) {
		case "1":
			noImplants = 1;
			break;
		case "2":
			noImplants = dicebag.nextInt(2, 3);
			break;
		case "3":
			noImplants = dicebag.nextInt(4, 6);
			break;
		default:
			noImplants = 0;
		}
		
		for (int j = 0; j < noImplants; j++) {
			Cyberware newImplant;
			int randomIdx = dicebag.nextInt(1, cyberList.length);
			cyberType = cyberList[randomIdx];
			cyberEss = Cyberware.essenceCostByType(cyberType);
			cyberRating = Cyberware.ratingByType(cyberType);
			cyberBrand = Cyberware.randomCyberwareBrand();
			if (!dicebag.nextBoolean()) { cyberBrand = ""; }
			
			totalEssence = this.getTotalEssenceCost() + cyberEss;
			
			newImplant = new Cyberware(cyberType, cyberEss, cyberRating, cyberBrand);
			if (!this.hasLikeCyberware(cyberType) && (totalEssence < 6.0)) {
				this.cyber.add(newImplant);
			}
			
			if (totalEssence >= 5.0) {
				break;
			}			
		}		
		
		//	Skill Upgrades
		
		int noLifeSkills = 3;
		int noUpgrades = dicebag.nextInt(0, 3);
		SRSkills[] skillsList = SRSkills.values(); 
		
		for (int j = 0; j < noLifeSkills; j++) {
			int lifeIdx = dicebag.nextInt(1, skillsList.length - 1);
			SRSkills lifeType = skillsList[lifeIdx];
			Skill lifeSkill = new Skill(lifeType, 2);
			if (!this.hasSkill(lifeType) && !(lifeType == SRSkills.CONJURING ||
					lifeType == SRSkills.SORCERY || lifeType == SRSkills.MAGICALTHEORY ||
					lifeType == SRSkills.SPECIALSKILL)) {
				this.skills.add(lifeSkill);
			}
		}
		
		for (int k = 0; k < noUpgrades; k++) {
			int upgradeIdx = dicebag.nextInt(0, this.skills.size() - 1);
			int newRating = this.skills.get(upgradeIdx).getRating() + 1;
			this.skills.get(upgradeIdx).setRating(newRating);
		}
	}
	
	public void generateRandomRunner() {
		
		//	Name
		
		String firstName = "";
		
		if (dicebag.nextBoolean()) {
			firstName = NameGenerator.getMaleName();
		} else {
			firstName = NameGenerator.getFemaleName();
		}
		this.name = firstName + " " + NameGenerator.getSurname();
		
		//	Alias
		
		this.handle = NameGenerator.getAlias();
		
		int randomStreetNumber = dicebag.nextInt(100, 9999);
		this.addressEmail = getRandomEmail();
		this.addressCity = getRandomCity();
		this.phoneNumber = getRandomPhoneNumber();
		this.addressStreet = Integer.toString(randomStreetNumber) + " " + getRandomStreetName();
		
		//	Metatype
		
		this.species = getRandomMetatype();
		applyMetatype();
		
		//	Archetype
		this.archetype = getRandomArchetype();
		applyArchetype();
		checkAttributes();
		
		//	Professionalism
		
		int profRating = getRandomProfRating();
		
		switch(profRating) {
		case 2:
			this.skills.get(0).setRating(4);
			this.skills.get(1).setRating(3);
			this.skills.get(2).setRating(3);
			this.skills.get(3).setRating(2);
			break;
		case 3:
			this.skills.get(0).setRating(5);
			this.skills.get(1).setRating(4);
			this.skills.get(2).setRating(4);
			this.skills.get(3).setRating(3);
			break;
		case 4:
			this.skills.get(0).setRating(6);
			this.skills.get(1).setRating(5);
			this.skills.get(2).setRating(5);
			this.skills.get(3).setRating(4);
			break;
		default:
			this.skills.get(0).setRating(3);
			this.skills.get(1).setRating(3);
			this.skills.get(2).setRating(2);
			this.skills.get(3).setRating(2);
			break;
		}

		//	Cyber

		int noImplants = 0;
		SRCyberware[] cyberList = SRCyberware.values(); 
		SRCyberware cyberType;
		double cyberEss;
		int cyberRating;
		String cyberBrand = "";
		double totalEssence = 0.0;
		
		int cyberwareRating = getRandomProfRating();
		
		switch (cyberwareRating) {
		case 2:
			noImplants = 1;
			break;
		case 3:
			noImplants = dicebag.nextInt(2, 3);
			break;
		case 4:
			noImplants = dicebag.nextInt(4, 6);
			break;
		default:
			noImplants = 0;
		}
		
		for (int j = 0; j < noImplants; j++) {
			Cyberware newImplant;
			int randomIdx = dicebag.nextInt(1, cyberList.length);
			cyberType = cyberList[randomIdx];
			cyberEss = Cyberware.essenceCostByType(cyberType);
			cyberRating = Cyberware.ratingByType(cyberType);
			cyberBrand = Cyberware.randomCyberwareBrand();
			if (!dicebag.nextBoolean()) { cyberBrand = ""; }
			
			totalEssence = this.getTotalEssenceCost() + cyberEss;
			
			newImplant = new Cyberware(cyberType, cyberEss, cyberRating, cyberBrand);
			if (!this.hasLikeCyberware(cyberType) && (totalEssence < 6.0)) {
				this.cyber.add(newImplant);
			}
			
			if (totalEssence >= 5.0) {
				break;
			}			
		}		
		
		//	Skill Upgrades
		
		int noLifeSkills = 3;
		int noUpgrades = dicebag.nextInt(0, 3);
		SRSkills[] skillsList = SRSkills.values(); 
		
		for (int j = 0; j < noLifeSkills; j++) {
			int lifeIdx = dicebag.nextInt(1, skillsList.length - 1);
			SRSkills lifeType = skillsList[lifeIdx];
			Skill lifeSkill = new Skill(lifeType, 2);
			if (!this.hasSkill(lifeType) && !(lifeType == SRSkills.CONJURING ||
					lifeType == SRSkills.SORCERY || lifeType == SRSkills.MAGICALTHEORY ||
					lifeType == SRSkills.SPECIALSKILL)) {
				this.skills.add(lifeSkill);
			}
		}
		
		for (int k = 0; k < noUpgrades; k++) {
			int upgradeIdx = dicebag.nextInt(0, this.skills.size() - 1);
			int newRating = this.skills.get(upgradeIdx).getRating() + 1;
			this.skills.get(upgradeIdx).setRating(newRating);
		}

	}
	
	public void printSheet() {
		double essenceScore = this.essence - this.getTotalEssenceCost();
		String cyberList = this.cyber.toString();
		if (cyberList.equals("[]")) {
			cyberList = "[ none ]";
		}
		
		System.out.println();
		System.out.println(this.name + " A.K.A. " + this.handle + " the " + this.metatypeName() + " " + this.archetypeName());
		System.out.println("tel: " + this.phoneNumber + "   " + "em: " + this.addressEmail);
		System.out.println("add: " + this.addressStreet + ", " + this.addressCity);
		System.out.println("B " + this.body + "   " + "C " + this.charisma + "   " + "Re " + this.reaction());
		System.out.println("Q " + this.quickness + "   " + "I " + this.intelligence + "   " + "In " + this.initiative());
		System.out.print("S " + this.strength + "   " + "W " + this.willpower + "   " + "Es ");
		System.out.printf("%1.2f\n", essenceScore);
		System.out.println("Skills: " + this.skills.toString());
		System.out.println("Known Cyberware: " + cyberList);
	}
	
	public void printSummary() {
		System.out.println();
		System.out.println(this.handle);
		System.out.println(" " + this.archetypeName() + " - " + Integer.toString(this.skills.get(0).getRating() * 5/6));
		System.out.println(this.name + " (" + this.metatypeName() + ")");
		System.out.println(" tel: " + this.phoneNumber + "   em: " + this.addressEmail);
		
	}
	
	//	Helper Methods
	
	public String metatypeName() {
		String retVal = "";
		
		switch (this.species) {
			case CYCLOPS:
				retVal = "Cyclops";
				break;
			case DRYAD:
				retVal = "Dryad";
				break;
			case DWARF:
				retVal = "Dwarf";
				break;
			case ELF:
				retVal = "Elf";
				break;
			case FOMORI:
				retVal = "Fomori";
				break;
			case GHOUL:
				retVal = "Ghoul";
				break;
			case GIANT:
				retVal = "Giant";
				break;
			case GNOME:
				retVal = "Gnome";
				break;
			case HOBGOBLIN:
				retVal = "Hobgoblin";
				break;
			case KOBOROKURU:
				retVal = "Koborokuru";
				break;
			case MENEHUNE:
				retVal = "Menehune";
				break;
			case MINOTAUR:
				retVal = "Minotaur";
				break;
			case NIGHTONE:
				retVal = "Night One";
				break;
			case OGRE:
				retVal = "Ogre";
				break;
			case ONI:
				retVal = "Oni";
				break;
			case ORK:
				retVal = "Ork";
				break;
			case SATYR:
				retVal = "Satyr";
				break;
			case SHAPESHIFTER:
				retVal = "Shapeshifter";
				break;
			case TROLL:
				retVal = "Troll";
				break;
			case WAKYAMBI:
				retVal = "Wakyambi";
				break;
			default:
				retVal = "Human";
		}
		
		return retVal;
	}
	
	public String archetypeName() {
		String retVal = "";
		
		switch (this.archetype) {
		case CITIZEN:
			retVal = "Citizen";
			break;
		case COP:
			retVal = "Cop";
			break;
		case DECKER:
			retVal = "Decker";
			break;
		case FACE:
			retVal = "Face";
			break;
		case FIXER:
			retVal = "Fixer";
			break;
		case GANGER:
			retVal = "Ganger";
			break;
		case MAGE:
			retVal = "Mage";
			break;
		case RIGGER:
			retVal = "Rigger";
			break;
		case ROCKER:
			retVal = "Rocker";
			break;
		case SHAMAN:
			retVal = "Shaman";
			break;
		case STREETSAMURAI:
			retVal = "Street Samurai";
			break;
		case WAGESLAVE:
			retVal = "Wage Slave";
			break;
			
		default:
			retVal = "Squatter";
		}
		
		return retVal;
	}
	
	private void applyMetatype() {
		this.maxBody = 6;
		this.maxQuickness = 6;
		this.maxStrength = 6;
		this.maxCharisma = 6;
		this.maxIntelligence = 6;
		this.maxWillpower = 6;
		
		switch (this.species) {
		case DWARF:
			this.body += 1;
			this.quickness -= 1;
			this.strength += 2;
			this.willpower += 1;
			this.maxBody = 7;
			this.maxQuickness = 5;
			this.maxStrength = 8;
			this.maxWillpower = 7;
			break;
		case ELF:
			this.quickness += 1;
			this.charisma += 2;
			this.maxQuickness = 7;
			this.maxCharisma = 8;
			break;
		case ORK:
			this.body += 3;
			this.strength += 2;
			this.charisma -= 1;
			this.intelligence -= 1;
			this.maxBody = 9;
			this.maxStrength = 8;
			this.maxCharisma = 5;
			this.maxIntelligence = 5;
			break;
		case TROLL:
			this.body += 5;
			this.quickness -= 1;
			this.strength += 4;
			this.charisma -= 2;
			this.intelligence -= 2;
			this.willpower -= 1;
			this.maxBody = 11;
			this.maxQuickness = 5;
			this.maxStrength = 10;
			this.maxCharisma = 4;
			this.maxIntelligence = 4;
			this.maxWillpower = 5;
			break;
		default:
			//		i.e., Human
		}
	}
	
	private void applyArchetype() {
		int noJobSkills = 4;
		Skill[] jobSkills = new Skill[noJobSkills];
		Cyberware jobCyber;
		SRCyberware cyberType;
		double cyberEss;
		int cyberRating;
		String cyberBrand = "";
		
		switch (this.archetype) {
		case COP:
			jobSkills[0] = new Skill(SRSkills.INTERROGATION, 0);
			jobSkills[1] = new Skill(SRSkills.FIREARMS, 0);
			jobSkills[2] = new Skill(SRSkills.ETIQUETTE, "Street", 0);
			if (dicebag.nextBoolean()) {
				jobSkills[3] = new Skill(SRSkills.ATHLETICS, 0);
			} else {
				jobSkills[3] = new Skill(SRSkills.CAR, 0);
			}
			this.body++;
			this.quickness++;
			this.strength++;
			this.charisma++;
			this.willpower++;
			if (dicebag.nextBoolean()) {
				this.intelligence--;
			} else {
				this.charisma--;
			}
			break;
			
		case DECKER:
			jobSkills[0] = new Skill(SRSkills.COMPUTER, 0);
			jobSkills[1] = new Skill(SRSkills.COMPUTERTHEORY, 0);
			jobSkills[2] = new Skill(SRSkills.COMPUTERBR, 0);
			if (dicebag.nextBoolean()) {
				jobSkills[3] = new Skill(SRSkills.ETIQUETTE, "Street", 0);
			} else {
				jobSkills[3] = new Skill(SRSkills.ELECTRONICS, 0);
			}
			this.intelligence += 2;
			this.quickness += 2;
			if (dicebag.nextBoolean()) {
				this.quickness++;
				this.strength--;
			}			
			cyberType = SRCyberware.DATAJACK;
			cyberEss = Cyberware.essenceCostByType(cyberType);
			cyberRating = -1;
			cyberBrand = Cyberware.randomCyberwareBrand();
			if (!dicebag.nextBoolean()) { cyberBrand = ""; }
			jobCyber = new Cyberware(cyberType, cyberEss, cyberRating, cyberBrand);
			this.cyber.add(jobCyber);
			break;
			
		case FACE:
			jobSkills[0] = new Skill(SRSkills.NEGOTIATION, 0);
			jobSkills[1] = new Skill(SRSkills.ETIQUETTE, "Corporate", 0);
			jobSkills[2] = new Skill(SRSkills.STEALTH, 0);
			this.charisma += 2;
			this.quickness++;
			this.intelligence++;
			this.willpower++;
			if (dicebag.nextBoolean()) {
				this.intelligence--;
				jobSkills[3] = new Skill(SRSkills.UNARMEDCOMBAT, 0);
			} else {
				this.strength--;
				jobSkills[3] = new Skill(SRSkills.FIREARMS, 0);
			}
			break;
			
		case GANGER: 
			jobSkills[0] = new Skill(SRSkills.ARMEDCOMBAT, 0);
			jobSkills[1] = new Skill(SRSkills.FIREARMS, 0);
			jobSkills[2] = new Skill(SRSkills.ETIQUETTE, "Street", 0);
			if (dicebag.nextBoolean()) {
				jobSkills[3] = new Skill(SRSkills.ATHLETICS, 0);
			} else {
				jobSkills[3] = new Skill(SRSkills.LEADERSHIP, 0);

			}
			this.body += 2;
			this.quickness++;
			this.strength++;
			this.willpower++;
			if (dicebag.nextBoolean()) {
				this.intelligence--;
			} else {
				this.charisma--;
			}
			break;
			
		case MAGE:
			jobSkills[0] = new Skill(SRSkills.SORCERY, 0);
			jobSkills[1] = new Skill(SRSkills.CONJURING, 0);
			jobSkills[2] = new Skill(SRSkills.MAGICALTHEORY, 0);
			if (dicebag.nextBoolean()) {
				jobSkills[3] = new Skill(SRSkills.ETIQUETTE, "Street", 0);
			} else {
				jobSkills[3] = new Skill(SRSkills.ETIQUETTE, "Corporate", 0);
			}
			this.magic = 6;
			this.charisma++;
			this.intelligence += 2;
			this.willpower += 2;
			if (dicebag.nextBoolean()) {
				this.strength--;
			} else {
				this.body--;
			}
			break;
			
		case RIGGER:
			jobSkills[0] = new Skill(SRSkills.CAR, 0);
			jobSkills[1] = new Skill(SRSkills.GUNNERY, 0);
			jobSkills[2] = new Skill(SRSkills.FIREARMS, 0);
			if (dicebag.nextBoolean()) {
				jobSkills[3] = new Skill(SRSkills.COMPUTER, 0);
			} else {
				jobSkills[3] = new Skill(SRSkills.ELECTRONICS, 0);
			}
			this.intelligence += 2;
			this.quickness += 2;
			if (dicebag.nextBoolean()) {
				this.willpower++;
				this.charisma--;
			} else {
				this.charisma++;
				this.strength--;
			}
			int vcrIdx = dicebag.nextInt(0, 9);
			if (vcrIdx < 6) {
				cyberType = SRCyberware.VEHICLECONTROLRIGLV1;
			} else if (vcrIdx < 9) {
				cyberType = SRCyberware.VEHICLECONTROLRIGLV2;
			} else {
				cyberType = SRCyberware.VEHICLECONTROLRIGLV3;
			}
			cyberEss = Cyberware.essenceCostByType(cyberType);
			cyberRating = -1;
			cyberBrand = Cyberware.randomCyberwareBrand();
			if (!dicebag.nextBoolean()) { cyberBrand = ""; }
			jobCyber = new Cyberware(cyberType, cyberEss, cyberRating, cyberBrand);
			this.cyber.add(jobCyber);				
			break;
			
		case ROCKER:
			jobSkills[0] = new Skill(SRSkills.SPECIALSKILL, "Instrumental Music", 0);
			jobSkills[1] = new Skill(SRSkills.ETIQUETTE, "Media", 0);
			jobSkills[2] = new Skill(SRSkills.ETIQUETTE, "Street", 0);
			if (dicebag.nextBoolean()) {
				jobSkills[3] = new Skill(SRSkills.FIREARMS, 0);
			} else {
				jobSkills[3] = new Skill(SRSkills.ARMEDCOMBAT, 0);
			}
			this.charisma += 2;
			this.quickness++;
			this.willpower++;
			if (dicebag.nextBoolean()) {
				this.charisma++;
				this.strength--;
			}
			if (dicebag.nextBoolean()) {
				cyberType = SRCyberware.SYNTHLINK;
				cyberEss = Cyberware.essenceCostByType(cyberType);
				cyberRating = -1;
				cyberBrand = Cyberware.randomCyberwareBrand();
				if (!dicebag.nextBoolean()) { cyberBrand = ""; }
				jobCyber = new Cyberware(cyberType, cyberEss, cyberRating, cyberBrand);
				this.cyber.add(jobCyber);
			}
			if (dicebag.nextBoolean()) {
				cyberType = SRCyberware.VOICEMODULATOR;
				cyberEss = Cyberware.essenceCostByType(cyberType);
				cyberRating = -1;
				jobCyber = new Cyberware(cyberType, cyberEss, cyberRating, cyberBrand);
				this.cyber.add(jobCyber);
			}
			break;
			
		case SHAMAN:
			jobSkills[0] = new Skill(SRSkills.CONJURING, 0);
			jobSkills[1] = new Skill(SRSkills.SORCERY, 0);
			jobSkills[2] = new Skill(SRSkills.MAGICALTHEORY, 0);
			if (dicebag.nextBoolean()) {
				jobSkills[3] = new Skill(SRSkills.ETIQUETTE, "Street", 0);
			} else {
				jobSkills[3] = new Skill(SRSkills.STEALTH, 0);
			}
			this.magic = 6;
			this.charisma += 2;
			this.willpower += 2;
			if (dicebag.nextBoolean()) {
				this.quickness++;
				this.intelligence--;
			}
			break;
			
		case WAGESLAVE:
			jobSkills[0] = new Skill(SRSkills.ETIQUETTE, "Corporate", 0);
			jobSkills[1] = new Skill(SRSkills.COMPUTER, 0);
			jobSkills[2] = new Skill(SRSkills.SPECIALSKILL, "Office Work", 0);
			if (dicebag.nextBoolean()) {
				jobSkills[3] = new Skill(SRSkills.NEGOTIATION, 0);
			} else {
				jobSkills[3] = new Skill(SRSkills.LEADERSHIP, 0);
			}
			this.charisma += 1;
			this.intelligence += 1;
			this.willpower += 1;
			if (dicebag.nextBoolean()) {
				this.body++;
			} else {
				this.body--;
			}
			if (dicebag.nextBoolean()) {
				cyberBrand = Cyberware.randomCyberwareBrand();
				cyberType = SRCyberware.MEMORY;
				jobCyber = new Cyberware(cyberType, 0.5, 50, cyberBrand);
				this.cyber.add(jobCyber);
				cyberType = SRCyberware.DATALOCK;
				jobCyber = new Cyberware(cyberType, cyberBrand);
				this.cyber.add(jobCyber);
			}
			break;
			
		default:
			//	Street Samurai
			jobSkills[0] = new Skill(SRSkills.FIREARMS, 0);
			jobSkills[1] = new Skill(SRSkills.ARMEDCOMBAT, 0);
			jobSkills[2] = new Skill(SRSkills.UNARMEDCOMBAT, 0);
			if (dicebag.nextBoolean()) {
				jobSkills[3] = new Skill(SRSkills.ETIQUETTE, "Corporate", 0);
			} else {
				jobSkills[3] = new Skill(SRSkills.ETIQUETTE, "Street", 0);
			}
			this.body += 2;
			this.quickness += 2;
			this.strength++;
			if (dicebag.nextBoolean()) {
				this.intelligence--;
			} else {
				this.willpower--;
			}
			int wrIdx = dicebag.nextInt(0, 9);
			if (wrIdx < 4) {
				cyberType = SRCyberware.UNDEFINED;
			} else if (wrIdx < 7) {
				cyberType = SRCyberware.WIREDREFLEXESLV1;
			} else if (wrIdx < 9) {
				cyberType = SRCyberware.WIREDREFLEXESLV2;
			} else {
				cyberType = SRCyberware.WIREDREFLEXESLV3;
			}
			if (cyberType != SRCyberware.UNDEFINED) {
				cyberEss = Cyberware.essenceCostByType(cyberType);
				cyberRating = -1;
				cyberBrand = Cyberware.randomCyberwareBrand();
				if (!dicebag.nextBoolean()) { cyberBrand = ""; }
				jobCyber = new Cyberware(cyberType, cyberEss, cyberRating, cyberBrand);
				this.cyber.add(jobCyber);				
			}
			if (dicebag.nextBoolean()) {
				cyberType = SRCyberware.SMARTGUNLINK;
				cyberEss = Cyberware.essenceCostByType(cyberType);
				cyberRating = -1;
				jobCyber = new Cyberware(cyberType, cyberEss, cyberRating, cyberBrand);
				this.cyber.add(jobCyber);
			}
		}
	
		for (int i = 0; i < noJobSkills; i++) {
			this.skills.add(jobSkills[i]);
		}
	}
	
	private void applyMetahumanVariation() {
		int random2d6Roll = dicebag.nextInt(1, 6) + dicebag.nextInt(1, 6);
		int totalPoints = 0;
		int modifier = 1;
		
		if (random2d6Roll == 2) {
			totalPoints = -4;
		} else if (random2d6Roll < 5) {
			totalPoints = -2;
		} else if (random2d6Roll < 8) {
			totalPoints = 0;
		} else if (random2d6Roll < 10) {
			totalPoints = 2;
		} else if (random2d6Roll < 12) {
			totalPoints = 4;
		} else {
			totalPoints = 8;
		}
		
		if (totalPoints < 0) {
			modifier = -1;
			totalPoints *= -1;
		}
		
		for (int i = 0; i < totalPoints; i++) {
			int randomD6Roll = dicebag.nextInt(1, 6);
			switch (randomD6Roll) {
			case 1:
				this.body += modifier;
				break;
			case 2:
				this.quickness += modifier;
				break;
			case 3:
				this.strength += modifier;
				break;
			case 4:
				this.charisma += modifier;
				break;
			case 5:
				this.intelligence += modifier;
				break;
			case 6:
				this.willpower += modifier;
				break;
			default:				
			}
		}
	}
	
	private String getRandomEmail() {
		String retVal = "";
		
		String[] names = this.name.toLowerCase().split(" ");
		int lastIdx = names.length - 1;
		String firstName = names[0];
		String lastName = "";
		String alias = this.handle.toLowerCase().replaceAll(" ", "");
		String domain = getRandomDomain();
		int randomMethod = dicebag.nextInt(3);
		int randomSuffix = dicebag.nextInt(999,10000);
		String suffix = Integer.toString(randomSuffix);
		
		if (lastIdx > 0) {
			lastName = names[lastIdx];
		}
		if (lastName.isBlank()) {
			lastName = NameGenerator.getSurname();
		}
		
		switch (randomMethod) {
		case 0:
			retVal = firstName.substring(0,1) + lastName + suffix;			
			break;
		case 1:
			retVal = firstName + "_" + lastName + suffix;
			break;
		case 2:
			retVal = firstName + "." + lastName.substring(0,1) + alias + suffix;
			break;
		default:	
			retVal = alias + suffix;
		}
		
		return retVal + "@" + domain;
	}
	
	private void checkAttributes() {
		if (this.body < 1) {
			this.body = 1;
		} else if (this.body > this.maxBody) {
			this.body = this.maxBody;
		}
		if (this.quickness < 1) {
			this.quickness = 1;
		} else if (this.quickness > this.maxQuickness) {
			this.quickness = this.maxQuickness;
		}
		if (this.strength < 1) {
			this.strength = 1;
		} else if (this.strength > this.maxStrength) {
			this.strength = this.maxStrength;
		}
		if (this.charisma < 1) {
			this.charisma = 1;
		} else if (this.charisma > this.maxCharisma) {
			this.charisma = this.maxCharisma;
		}
		if (this.intelligence < 1) {
			this.intelligence = 1;
		} else if (this.intelligence > this.maxIntelligence) {
			this.intelligence = this.maxIntelligence;
		}
		if (this.willpower < 1) {
			this.willpower = 1;
		} else if (this.willpower > this.maxWillpower) {
			this.willpower = this.maxWillpower;
		}
	}
	
	private String getRandomDomain() {
		String[] domains = {
				"monobe.com",
				"highstar.com",
				"etc.com",
				"ucas.gov",
				"stuffershack.com",
				"microtronics.com",
				"seattle.gov",
				"redmond.gov",
				"weaponsworld.com",
				"shadowland.net",
				"vstelectronics.com",
				"lonestar.com",
				"knighterrant.com",
				"cas.gov",
				"salish.gov",
				"syntronics.com",
				"tirtairngire.gov",
				"tirnanog.gov",
				"att.com",
				"nan.gov",
				"hk.gov",
				"calfree.gov",
				"atlanta.gov",
				"denver.gov",
				"frisco.gov",
				"keaneinc.com",
				"zorb.gov",
				"chicago.gov",
				"humanis.org",
				"ub.org",
				"microlux.com",
				"ucastrak.com",
				"nakatomi.com",
				"chiba.net",
				"colt.com",
				"hkoch.com",
				"boa.com",
				"paneuropa.com",
				"barclays.com",
				"gemeinschaft.zo",
				"hawaii.gov",
				"chryslernissan.com",
				"eurocar.com",
				"ford.com",
				"ssi.com",
				"krupmfg.com",
				"vashon.com",
				"armani.com",
				"gucci.com",
				"mostrans.com",
				"grazburya.com",
				"aztlan.gov",
				"tokyo.gov",
				"nyc.gov",
				"qzecorp.com",
				"ares.com",
				"aztechnology.com",
				"fuchi.com",
				"mct.com",
				"renraku.com",
				"saederkrupp.com",
				"shiawase.com",
				"yamatetsu.com",
				"rangerarms.com",
				"remington.com",
				"glock.com"
		};
		
		int randIdx = dicebag.nextInt(0, domains.length - 1);
		
		return domains[randIdx];
	}
	
	private String getRandomCity() {
		String[] cities = {
				"Seattle",
				"Bellevue",
				"Pullayup",
				"Redmond",
				"Renraku Arcology",
				"Tacoma",
				"Everett",
				"Renton",
				"Auburn",
				"Snohomish",
				"Fort Lewis",
				"Ork Underground",
				"Council Island",
				"Downtown"
		};
		
		int randIdx = dicebag.nextInt(0, cities.length - 1);
		
		return cities[randIdx];
	}
	
	private String getRandomPhoneNumber() {
		int numDigits = 10;
		StringBuilder pN = new StringBuilder();
		for (int i = 0; i < numDigits; i++) {
			pN.append(dicebag.nextInt(0, 10));
		}
		return pN.toString();
	}
	
	private String getRandomStreetName() {
		String[] streets = {
				"100th St",
				"115th St",
				"121st St",
				"138th St",
				"146th St",
				"153rd St",
				"167th St",
				"174th St",
				"188th St",
				"192nd St",
				"204th St",
				"98th St",
				"214th St",
				"222nd St",
				"236th St",
				"245th St",
				"259th St",
				"261st St",
				"273rd St",
				"284th St",
				"39th St",
				"44th St",
				"56th St",
				"63rd St",
				"71st St",
				"82nd St",
				"Adams St",
				"Agnew Ave",
				"Airport Way",
				"Alaskan Way",
				"Alder St",
				"Alonzo Ave",
				"Alpine Way",
				"Ambaum Blvd",
				"Andover Park",
				"Ann Arbor Ave",
				"Aqua Way",
				"Arnold Rd",
				"Arrowsmith Ave",
				"Ashworth Ave",
				"Aurora Ave",
				"Bagley Dr",
				"Ballinger Way",
				"Bartlett Ave",
				"Bayard Ave",
				"Beach Dr",
				"Beacon Ave",
				"Bellevue Ave",
				"Beverly Ln",
				"Boren Ave",
				"Boyd Pl",
				"Broad St",
				"Burke Ave",
				"Carkeek Dr",
				"Canterbury Ln",
				"Cascade Ave",
				"Cherry Ln",
				"Cliff Ave",
				"Columbia St",
				"Convention Pl",
				"Cornell Ave",
				"Crockett St",
				"Dayton Ave",
				"Des Moines Memorial Dr",
				"Dexter Ave",
				"Dixon Dr",
				"Erskine Way",
				"Euclid St",
				"Fairmount Ave",
				"Fauntleroy Way",
				"Forest Park Dr",
				"Gardenspot Rd",
				"Greenwood Ave",
				"Harbor Ave",
				"Holman Rd",
				"Hummingbird Ln",
				"Industry Dr",
				"International Blvd",
				"Interurban Pl",
				"Jefferson St",
				"Jones Ave",
				"Kensington Pl",
				"Kings Garden Dr",
				"Lake Ballinger Way",
				"Lake Washington Blvd",
				"Lenora St",
				"Madrona Dr",
				"Marine View Dr",
				"Martin Luther King Jr Way",
				"Mercer St",
				"Monster Rd",
				"Northgate Plz",
				"Occidental Ave",
				"Pacific Hwy",
				"Perimeter Rd",
				"Puget Blvd",
				"Randolph Ave",
				"Riviera Pl",
				"Roosevelt Way",
				"Seneca St",
				"South Dakota St",
				"Stone Ave",
				"Sylvan Way",
				"Terrace Ct",
				"Terry Ave",
				"Thorndyke Ave",
				"University St",
				"Vashon Pl",
				"Woodlawn Ave",
				"Yale Ave"
		};
		int randIdx = dicebag.nextInt(0, streets.length - 1);
		return streets[randIdx];
	}
	
	private SRMetatype getRandomMetatype() {
		SRMetatype retVal = SRMetatype.HUMAN;
		int randomPercent = dicebag.nextInt(0, 99);
		
		if (randomPercent < 70) {
			retVal = SRMetatype.HUMAN;
		} else if (randomPercent < 80) {
			retVal = SRMetatype.DWARF;
		} else if (randomPercent < 89) {
			retVal = SRMetatype.ORK;
		} else if (randomPercent < 95) {
			retVal = SRMetatype.TROLL;
		} else {
			retVal = SRMetatype.ELF;
		}
		
		return retVal;
	}
	
	private SRArchetypes getRandomArchetype() {
		SRArchetypes retVal = SRArchetypes.CITIZEN;
		int randomPercent = dicebag.nextInt(0, 99);
		
		if (randomPercent < 10) {
			retVal = SRArchetypes.COP;
		} else if (randomPercent < 20) {
			retVal = SRArchetypes.DECKER;
		} else if (randomPercent < 30) {
			retVal = SRArchetypes.FACE;
		} else if (randomPercent < 40) {
			retVal = SRArchetypes.GANGER;
		} else if (randomPercent < 50) {
			retVal = SRArchetypes.MAGE;
		} else if (randomPercent < 60) {
			retVal = SRArchetypes.RIGGER;
		} else if (randomPercent < 70) {
			retVal = SRArchetypes.ROCKER;
		} else if (randomPercent < 80) {
			retVal = SRArchetypes.SHAMAN;
		} else if (randomPercent < 90) {
			retVal = SRArchetypes.STREETSAMURAI;
		} else {
			retVal = SRArchetypes.WAGESLAVE;
		}
		
		return retVal;
	}
	
	private int getRandomProfRating() {
		int retVal = 0;
		int randomPercent = dicebag.nextInt(0, 99);
		
		if (randomPercent < 40) {
			retVal = 2;
		} else if (randomPercent < 70) {
			retVal = 3;
		} else if (randomPercent < 90) {
			retVal = 1;
		} else {
			retVal = 4;
		}
		
		return retVal;
	}
	
	public boolean hasCyberware(SRCyberware c) {
		boolean retVal = false;
		
//		System.out.print(c + " == ");
		for (int i = 0; i < this.cyber.size(); i++) {
			Cyberware curCyber = this.cyber.get(i);
//			System.out.print(" " + curCyber.getType());
			if (curCyber.getType() == c) {
				retVal = true;
				break;
			}
		}
//		System.out.println();
		
		return retVal;
	}
	
	public boolean hasLikeCyberware(SRCyberware c) {
		boolean retVal = false;
		
		Cyberware cyberC = new Cyberware(c);
		String cType = cyberC.typeName();
		String cBaseType = cType;
		int cPos = cType.indexOf("Level");

		String myType, myBaseType;
		int myPos;
		
		if (cPos > -1) {
			cBaseType = cType.substring(0, cPos);
		}
		
		for (int i = 0; i < this.cyber.size(); i++) {
			Cyberware myCyber = this.cyber.get(i);
			myType = myCyber.typeName();
			myBaseType = myType;
			myPos = myType.indexOf("Level");
			if (myPos > -1) {
				myBaseType = myType.substring(0, myPos);
			}
			
			if (cBaseType.equals(myBaseType)) {
				retVal = true;
				break;
			}
		}
		
		return retVal;
	}
	
	public boolean hasSkill(SRSkills s) {
		boolean retVal = false;
		
		for (int i = 0; i < this.skills.size(); i++) {
			Skill curSkill = this.skills.get(i);
			if (curSkill.getSkill() == s) {
				retVal = true;
				break;
			}
		}
		
		return retVal;
	}
}
