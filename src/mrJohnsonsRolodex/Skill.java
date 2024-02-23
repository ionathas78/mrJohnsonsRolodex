package mrJohnsonsRolodex;

import java.util.ArrayList;

public class Skill {
	private SRSkills baseType = SRSkills.UNDEFINED;
	private int rating = 0;
	private String concentration = "";
	private String specialization = "";
	
	//	Constructors
	
	public Skill() {
		this(SRSkills.UNDEFINED, 0);
	}
	
	public Skill(SRSkills baseSkill, int rating) {
		this.baseType = baseSkill;
		this.rating = rating;
	}
	
	public Skill(SRSkills baseSkill, String concentration, int rating) {
		this(baseSkill, rating);
		this.concentration = concentration;
	}
	
	public Skill(SRSkills baseSkill, String concentration, String specialization, int rating) {
		this(baseSkill, rating);
		this.concentration = concentration;
		this.specialization = specialization;
	}
	
	//	Getters, mutators
	
	public SRSkills getSkill() { return this.baseType; }
	public SRSkills equals() { return this.baseType; }
	
	public String getFullName() {
		StringBuilder fullName = new StringBuilder();
		fullName.append(this.skillName());
		if (this.isConcentrated()) {
			fullName.append(" (" + this.concentration);
			
			if (this.isSpecialized()) {
				fullName.append(": " + this.specialization);
			}
			fullName.append(")");
		}
		return fullName.toString();
	}
	
	public String getConcentration() { return this.concentration; }
	public String getSpecialization() { return this.specialization; }
	public int getRating() { return this.rating; }
	
	public void setSkill(SRSkills val) {
		this.baseType = val;
	}
	public void setRating(int val) {
		this.rating = val;
	}
	public void setConcentration(String val) {
		this.concentration = val;
	}
	public void setSpecialization(String val) {
		this.specialization = val;
	}

	//	State flags
	
	public boolean isConcentrated() { return !this.concentration.isBlank(); }
	public boolean isSpecialized() { return !this.specialization.isBlank(); }
	

	//	Public Methods
	
	public boolean equals(Skill o) {
		if (o == this) {
			return true;
		}
		
		if (o != null && o.baseType == this.baseType) {
			return true;
		}
		
		return false;
	}
	
	public boolean concentrate(ArrayList<Skill> parentCollection, String concentration) {
		boolean retVal = false;
		Skill newSkill;
		
		if (this.concentration.isBlank()) {
			newSkill = new Skill(this.baseType, this.rating - 1);
			parentCollection.add(newSkill);

			this.concentration = concentration;
			this.rating++;
			
			retVal = true;
		}
		return retVal;
	}
	
	public boolean specialize(ArrayList<Skill> parentCollection, String concentration, String specialization) {
		boolean retVal = false;
		Skill newSkill, newConcentration;
		
		if (!(this.specialization.isBlank() || this.concentration.isBlank())) {
			//	Don't process! You can only specialize a base skill
			
		} else {
			newSkill = new Skill(this.baseType, this.rating - 2);
			newConcentration = new Skill(this.baseType, concentration, this.rating);
			parentCollection.add(newSkill);
			parentCollection.add(newConcentration);
			
			this.concentration = concentration;
			this.specialization = specialization;
			this.rating += 2;
			
			retVal = true;
		}
		return retVal;
	}
	
	//	Helper methods
	
	public String toString() {
		return this.getFullName() + " " + Integer.toString(this.rating);
	}
	
	public String skillName() {
		String retVal = "";
		
		switch (this.baseType) {
			case AIRCRAFTBR:
				retVal = "Aircraft (B/R)";
				break;
			case ARMEDCOMBAT:
				retVal = "Armed Combat";
				break;
			case ARMEDCOMBATBR:
				retVal = "Armed Combat (B/R)";
				break;
			case ATHLETICS:
				retVal = "Athletics";
				break;
			case BIKE:
				retVal = "Bike";
				break;
			case BIOLOGY:
				retVal = "Biology";
				break;
			case BIOTECH:
				retVal = "Biotech";
				break;
			case BIOTECHBR:
				retVal = "Biotech (B/R)";
				break;
			case BOATSBR:
				retVal = "Boats (B/R)";
				break;
			case CAR:
				retVal = "Car";
				break;
			case COMPUTER:
				retVal = "Computer";
				break;
			case COMPUTERBR:
				retVal = "Computer (B/R)";
				break;
			case COMPUTERTHEORY:
				retVal = "Computer Theory";
				break;
			case CONJURING:
				retVal = "Conjuring";
				break;
			case CYBERTECHNOLOGY:
				retVal = "Cybertechnology";
				break;
			case DEMOLITIONS:
				retVal = "Demolitions";
				break;
			case DEMOLITIONSBR:
				retVal = "Demolitions (B/R)";
				break;
			case ELECTRONICS:
				retVal = "Electronics";
				break;
			case ELECTRONICSBR:
				retVal = "Electronics (B/R)";
				break;
			case ETIQUETTE:
				retVal = "Etiquette";
				break;
			case FIREARMS:
				retVal = "Firearms";
				break;
			case FIREARMSBR:
				retVal = "Firearms (B/R)";
				break;
			case GROUNDVEHICLESBR:
				retVal = "Ground Vehicles (B/R)";
				break;
			case GUNNERY:
				retVal = "Gunnery";
				break;
			case GUNNERYBR:
				retVal = "Gunnery (B/R)";
				break;
			case HOVERCRAFT:
				retVal = "Hovercraft";
				break;
			case INTERROGATION:
				retVal = "Interrogation";
				break;
			case LEADERSHIP:
				retVal = "Leadership";
				break;
			case MAGICALTHEORY:
				retVal = "Magical Theory";
				break;
			case MILITARYTHEORY:
				retVal = "Military Theory";
				break;
			case MOTORBOAT:
				retVal = "Motorboat";
				break;
			case NEGOTIATION:
				retVal = "Negotiation";
				break;
			case PHYSICALSCIENCES:
				retVal = "Physical Sciences";
				break;
			case PROJECTILES:
				retVal = "Projectiles";
				break;
			case PROJECTILESBR:
				retVal = "Projectiles (B/R)";
				break;
			case PSYCHOLOGY:
				retVal = "Psychology";
				break;
			case ROTOR:
				retVal = "Rotor";
				break;
			case SAILBOAT:
				retVal = "Sailboat";
				break;
			case SOCIOLOGY:
				retVal = "Sociology";
				break;
			case SORCERY:
				retVal = "Sorcery";
				break;
			case SPECIALSKILL:
				retVal = "Special Skill";
				break;
			case STEALTH:
				retVal = "Stealth";
				break;
			case THROWING:
				retVal = "Throwing";
				break;
			case THROWINGBR:
				retVal = "Throwing (B/R)";
				break;
			case UNARMEDCOMBAT:
				retVal = "Unarmed Combat";
				break;
			case VECTORTHRUST:
				retVal = "Vector Thrust";
				break;
			case WINGED:
				retVal = "Winged";
			default:
				retVal = "None";
				break;
		}
		
		return retVal;
	}
		
}
	
