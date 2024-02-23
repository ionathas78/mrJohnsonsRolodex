package mrJohnsonsRolodex;

public class Cyberware {
	private SRCyberware baseType;
	private double essenceCost;
	private CyberGrades grade;
	private int rating;
	private String location;
	private String manufacturer;
	private String notes;

	//	Constructors
	
	public Cyberware() {
		this(SRCyberware.UNDEFINED, 0.0, -1, "", CyberGrades.STANDARD, "", "");
	 }
 	
	public Cyberware(SRCyberware baseType, double essenceCost, int rating, String brand, CyberGrades grade, String location, String notes) {
		this.baseType = baseType;
		this.essenceCost = essenceCost;
		this.rating = rating;
		this.grade = grade;
		this.manufacturer = brand;
		this.location = location;
		this.notes = notes;
	}
	
	public Cyberware(SRCyberware baseType, double essenceCost, int rating, String brand) {
		this(baseType, essenceCost, rating, brand, CyberGrades.STANDARD, "", "");
	}
	
	public Cyberware(SRCyberware baseType, double essenceCost, int rating) {
		this(baseType, essenceCost, rating, "", CyberGrades.STANDARD, "", "");
	}

	public Cyberware(SRCyberware baseType, double essenceCost, String brand, CyberGrades grade) {
		this(baseType, essenceCost, -1, brand, grade, "", "");
	}

	public Cyberware(SRCyberware baseType, double essenceCost, CyberGrades grade) {
		this(baseType, essenceCost, -1, "", grade, "", "");
	}
	
	public Cyberware(SRCyberware baseType, double essenceCost, int rating, CyberGrades grade) {
		this(baseType, essenceCost, rating, "", grade, "", "");
	}
	
	public Cyberware(SRCyberware baseType, double essenceCost, int rating, String brand, CyberGrades grade) {
		this(baseType, essenceCost, rating, brand, grade, "", "");
	}

	public Cyberware(SRCyberware baseType, double essenceCost) {
		this(baseType, essenceCost, -1, "", CyberGrades.STANDARD, "", "");
	}
	
	public Cyberware(SRCyberware baseType) {
		this(baseType, 0, -1, "", CyberGrades.STANDARD, "", "");
		double essCost = essenceCostByType(baseType);
		this.essenceCost = essCost;
	}

	public Cyberware(SRCyberware baseType, int rating) {
		this(baseType, 0, rating, "", CyberGrades.STANDARD, "", "");
		double essCost = essenceCostByType(baseType);
		this.essenceCost = essCost;
	}

	public Cyberware(SRCyberware baseType, String brand) {
		this(baseType, 0, -1, brand, CyberGrades.STANDARD, "", "");
		double essCost = essenceCostByType(baseType);
		this.essenceCost = essCost;
	}
	
	public Cyberware(SRCyberware baseType, int rating, String brand) {
		this(baseType, 0, rating, brand, CyberGrades.STANDARD, "", "");
		double essCost = essenceCostByType(baseType);
		this.essenceCost = essCost;
	}

	//	Getters, Mutators
	
	public SRCyberware getType() {
		return this.baseType;
	}
	
	public double getEssenceCost() {
		return this.essenceCost;
	}
	
	public CyberGrades getGrade() {
		return this.grade;
	}
	
	public int getRating() {
		return this.rating;
	}
	
	public String getBrand() {
		return this.manufacturer;
	}
	
	public String getLocation() {
		return this.location;
	}
	
	public String getNotes() {
		return this.notes;
	}
	
	public void setType(SRCyberware val) {
		this.baseType = val;
	}
	
	public void setEssenceCost(double val) {
		this.essenceCost = val;
	}
	
	public void setGrade(CyberGrades val) {
		this.grade = val;
	}
	
	public void setRating(int val) {
		this.rating = val;
	}
	
	public void setBrand(String val) {
		this.manufacturer = val;
	}
	
	public void setLocation(String val) {
		this.location = val;
	}
	
	public void setNotes(String val) {
		this.notes = val;
	}
	
	//	Public methods
	
	public String toString() {
		StringBuilder retVal = new StringBuilder();
		
		if (this.grade != CyberGrades.STANDARD) {
			retVal.append(this.gradeName() + " ");
		}
		if (!this.manufacturer.isBlank()) {
			retVal.append(this.manufacturer + " ");
		}
		
		retVal.append(this.typeName());
		
		if (this.rating > -1) {
			retVal.append(" (" + Integer.toString(this.rating) + ")");
		}
		if (!this.location.isBlank()) {
			retVal.append(" (" + this.location + ")");
		}
		if (!this.notes.isBlank()) {
			retVal.append(" - " + this.notes);
		}
//		retVal.append(" " + this.essenceCost);
		
		return retVal.toString();
	}
	
	public boolean equals(Cyberware o) {
		String myType = this.typeName();
		String myBaseType = myType;
		String oType = o.typeName();
		String oBaseType = oType;
		
		int myPos = myType.indexOf("Level");
		if (myPos > -1) {
			myBaseType = myType.substring(0, myPos);
		}
		int oPos = oType.indexOf("Level");
		if (oPos > -1) {
			oBaseType = oType.substring(0, oPos);
		}
		
		return (oBaseType == myBaseType);
	}
	
	public boolean equals(SRCyberware o) {
		Cyberware oCyber = new Cyberware(o);
		return this.equals(oCyber);
	}
	public String typeName() {
		String retVal = "";
		
		switch (this.baseType) {
		case AIRFILTRATIONSYSTEM:
			retVal = "Air Filtration System";
			break;
		case AUDIODAMPER:
			retVal = "Audio Damper";
			break;
		case AUDIORECORDER:
			retVal = "Audio Recorder";
			break;
		case BLOODFILTRATIONSYSTEM:
			retVal = "Blood Filtration System";
			break;
		case CAMERA:
			retVal = "Camera";
			break;
		case CHIPJACK:
			retVal = "Chipjack";
			break;
		case CORTEXBOMB:
			retVal = "Cortex Bomb";
			break;
		case COSMETICEARMODIFICATION:
			retVal = "Cosmetic Ear Modification";
			break;
		case COSMETICEYEMODIFICATION:
			retVal = "Cosmetic Eye Modification";
			break;
		case CYBERARM:
			retVal = "Cyberarm";
			break;
		case CYBEREAR:
			retVal = "Cyberear(s)";
			break;
		case CYBEREYE:
			retVal = "Cybereye(s)";
			break;
		case CYBERLEG:
			retVal = "Cyberleg";
			break;
		case CYBERSKULL:
			retVal = "Cyberskull";
			break;
		case CYBERTORSO:
			retVal = "Cybertorso";
			break;
		case DATAFILTER:
			retVal = "Data Filter";
			break;
		case DATAJACK:
			retVal = "Datajack";
			break;
		case DATALOCK:
			retVal = "Data Lock";
			break;
		case DATASOFTLINK:
			retVal = "Datasoft Link";
			break;
		case DERMALPLATINGLV1:
			retVal = "Dermal Plating Level 1";
			break;
		case DERMALPLATINGLV2:
			retVal = "Dermal Plating Level 2";
			break;
		case DERMALPLATINGLV3:
			retVal = "Dermal Plating Level 3";
			break;
		case DISPLAYLINK:
			retVal = "Display Link";
			break;
		case FINGERTIPCOMPARTMENT:
			retVal = "Fingertip Compartment";
			break;
		case FLARECOMPENSATION:
			retVal = "Flare Compensation";
			break;
		case HANDRAZORS:
			retVal = "Hand Razors";
			break;
		case HIGHFREQUENCYRECEIVER:
			retVal = "High Frequency Receiver";
			break;
		case INGESTEDTOXINFILTRATIONSYSTEM:
			retVal = "Ingested Toxin Filtration System";
			break;
		case LOWFREQUENCYRECEIVER:
			retVal = "Low Frequency Receiver";
			break;
		case LOWLIGHTVISION:
			retVal = "Low-light Vision";
			break;
		case MEMORY:
			retVal = "Headware Memory";
			break;
		case MUSCLEREPLACEMENTLV1:
			retVal = "Muscle Replacement Level 1";
			break;
		case MUSCLEREPLACEMENTLV2:
			retVal = "Muscle Replacement Level 2";
			break;
		case MUSCLEREPLACEMENTLV3:
			retVal = "Muscle Replacement Level 3";
			break;
		case MUSCLEREPLACEMENTLV4:
			retVal = "Muscle Replacement Level 4";
			break;
		case PROGRAMCARRIER:
			retVal = "Program Carrier";
			break;
		case RADIO:
			retVal = "Radio";
			break;
		case RADIORECEIVER:
			retVal = "Radio Receiver";
			break;
		case RETRACTABLERAZORS:
			retVal = "Retractable Razors";
			break;
		case RETRACTABLESPUR:
			retVal = "Retractable Spur";
			break;
		case RETINALDUPLICATION:
			retVal = "Retinal Duplication";
			break;
		case SKILLWIRESLV1:
			retVal = "Skillwires Level 1";
			break;
		case SKILLWIRESLV2:
			retVal = "Skillwires Level 2";
			break;
		case SKILLWIRESLV3:
			retVal = "Skillwires Level 3";
			break;
		case SKILLWIRESLV4:
			retVal = "Skillwires Level 4";
			break;
		case SKILLWIRESLV5:
			retVal = "Skillwires Level 5";
			break;
		case SKILLWIRESLV6:
			retVal = "Skillwires Level 6";
			break;
		case SKILLWIRESLV7:
			retVal = "Skillwires Level 7";
			break;
		case SKILLWIRESLV8:
			retVal = "Skillwires Level 8";
			break;
		case SKILLWIRESLV9:
			retVal = "Skillwires Level 9";
			break;
		case SMARTGUNLINK:
			retVal = "Smartgun Link";
			break;
		case SPUR:
			retVal = "Spur";
			break;
		case SYNTHLINK:
			retVal = "Synthlink";
			break;
		case TELEPHONE:
			retVal = "Telephone";
			break;
		case THERMOGRAPHICVISION:
			retVal = "Thermographic Vision";
			break;
		case VEHICLECONTROLRIGLV1:
			retVal = "Vehicle Control Rig Level 1";
			break;
		case VEHICLECONTROLRIGLV2:
			retVal = "Vehicle Control Rig Level 2";
			break;
		case VEHICLECONTROLRIGLV3:
			retVal = "Vehicle Control Rig Level 3";
			break;
		case VOICEMODULATOR:
			retVal = "Voice Modulator";
			break;
		case WIREDREFLEXESLV1:
			retVal = "Wired Reflexes Level 1";
			break;
		case WIREDREFLEXESLV2:
			retVal = "Wired Reflexes Level 2";
			break;
		case WIREDREFLEXESLV3:
			retVal = "Wired Reflexes Level 3";
			break;
		default:
			retVal = "None";
		}
		
		return retVal;
	}
	
	public String gradeName() {
		String retVal = "";
		switch (this.grade) {
		case SECONDHAND:
			retVal = "Used";
			break;
		case ALPHA:
			retVal = "Alphaware";
			break;
		case BETA:
			retVal = "Betaware";
			break;
		case DELTA:
			retVal = "Deltaware";
			break;
		default:
			retVal = "Standard";
		}
		
		return retVal;
	}
	
	public double gradeEssenceMult() {
		double retVal = 1.0;
		
		switch (this.grade) {
		case SECONDHAND:
			retVal = 1.0;
			break;
		case ALPHA:
			retVal = 0.8;
			break;
		case BETA:
			retVal = 0.6;
			break;
		case DELTA:
			retVal = 0.5;
			break;
		default:
			retVal = 1.0;
		}
		return retVal;
	}
	
	static public double essenceCostByType(SRCyberware type) {
		double retVal = 0.0;
		
		switch (type) {
		case AIRFILTRATIONSYSTEM:
			//	at Rating 3
			retVal = 0.3;
			break;
		case AUDIODAMPER:
			retVal = 0.1;
			break;
		case AUDIORECORDER:
			retVal = 0.3;
			break;
		case BLOODFILTRATIONSYSTEM:
			//	at Rating 3
			retVal = 0.6;
			break;
		case CAMERA:
			retVal = 0.4;
			break;
		case CHIPJACK:
			retVal = 0.2;
			break;
		case CORTEXBOMB:
			retVal = 0.0;
			break;
		case COSMETICEARMODIFICATION:
			retVal = 0.0;
			break;
		case COSMETICEYEMODIFICATION:
			retVal = 0.0;
			break;
		case CYBERARM:
			retVal = 1.0;
			break;
		case CYBEREAR:
			retVal = 0.3;
			break;
		case CYBEREYE:
			retVal = 0.2;
			break;
		case CYBERLEG:
			retVal = 1.0;
			break;
		case CYBERSKULL:
			retVal = 1.0;
			break;
		case CYBERTORSO:
			retVal = 1.0;
			break;
		case DATAFILTER:
			retVal = 0.3;
			break;
		case DATAJACK:
			retVal = 0.2;
			break;
		case DATALOCK:
			retVal = 0.2;
			break;
		case DATASOFTLINK:
			retVal = 0.1;
			break;
		case DERMALPLATINGLV1:
			retVal = 0.5;
			break;
		case DERMALPLATINGLV2:
			retVal = 1.0;
			break;
		case DERMALPLATINGLV3:
			retVal = 1.5;
			break;
		case DISPLAYLINK:
			retVal = 0.1;
			break;
		case FINGERTIPCOMPARTMENT:
			retVal = 0.1;
			break;
		case FLARECOMPENSATION:
			retVal = 0.1;
			break;
		case HANDRAZORS:
			retVal = 0.1;
			break;
		case HIGHFREQUENCYRECEIVER:
			retVal = 0.2;
			break;
		case INGESTEDTOXINFILTRATIONSYSTEM:
			//	at Rating 3
			retVal = 0.6;
			break;
		case LOWFREQUENCYRECEIVER:
			retVal = 0.2;
			break;
		case LOWLIGHTVISION:
			retVal = 0.2;
			break;
		case MEMORY:
			//	at Rating 50 Mp
			retVal = 0.5;
			break;
		case MUSCLEREPLACEMENTLV1:
			retVal = 1.0;
			break;
		case MUSCLEREPLACEMENTLV2:
			retVal = 2.0;
			break;
		case MUSCLEREPLACEMENTLV3:
			retVal = 3.0;
			break;
		case MUSCLEREPLACEMENTLV4:
			retVal = 4.0;
			break;
		case PROGRAMCARRIER:
			retVal = 0.2;
			break;
		case RADIO:
			retVal = 0.75;
			break;
		case RADIORECEIVER:
			retVal = 0.4;
			break;
		case RETRACTABLERAZORS:
			retVal = 0.2;
			break;
		case RETRACTABLESPUR:
			retVal = 0.3;
			break;
		case RETINALDUPLICATION:
			retVal = 0.1;
			break;
		case SKILLWIRESLV1:
			retVal = 0.1;
			break;
		case SKILLWIRESLV2:
			retVal = 0.2;
			break;
		case SKILLWIRESLV3:
			retVal = 0.3;
			break;
		case SKILLWIRESLV4:
			retVal = 0.8;
			break;
		case SKILLWIRESLV5:
			retVal = 1.0;
			break;
		case SKILLWIRESLV6:
			retVal = 1.2;
			break;
		case SKILLWIRESLV7:
			retVal = 2.1;
			break;
		case SKILLWIRESLV8:
			retVal = 2.4;
			break;
		case SKILLWIRESLV9:
			retVal = 2.7;
			break;
		case SMARTGUNLINK:
			retVal = 0.5;
			break;
		case SPUR:
			retVal = 0.1;
			break;
		case SYNTHLINK:
			retVal = 0.2;
			break;
		case TELEPHONE:
			retVal = 0.5;
			break;
		case THERMOGRAPHICVISION:
			retVal = 0.2;
			break;
		case VEHICLECONTROLRIGLV1:
			retVal = 2.0;
			break;
		case VEHICLECONTROLRIGLV2:
			retVal = 3.0;
			break;
		case VEHICLECONTROLRIGLV3:
			retVal = 5.0;
			break;
		case VOICEMODULATOR:
			retVal = 0.3;
			break;
		case WIREDREFLEXESLV1:
			retVal = 2.0;
			break;
		case WIREDREFLEXESLV2:
			retVal = 3.0;
			break;
		case WIREDREFLEXESLV3:
			retVal = 5.0;
			break;
		default:
			retVal = 0.0;
		}
		
		return retVal;
	}
	
	static public int ratingByType(SRCyberware type) {
		int retVal = -1;
		
		switch (type) {
		case AIRFILTRATIONSYSTEM:
			//	at Rating 3
			retVal = 3;
			break;
		case BLOODFILTRATIONSYSTEM:
			//	at Rating 3
			retVal = 3;
			break;
		case INGESTEDTOXINFILTRATIONSYSTEM:
			//	at Rating 3
			retVal = 3;
			break;
		case MEMORY:
			//	at Rating 50 Mp
			retVal = 50;
			break;
		default:
			retVal = -1;
		}
		
		return retVal;
	}
	
	static public String randomCyberwareBrand() {
		java.util.Random rand = new java.util.Random();
		
		String[] brandNames = {
				"",
				"Ares Macrotechnology",
				"Aztechnology",
				"Fuchi",
				"Renraku",
				"Saeder-Krupp",
				"Shiawase",
				"Transys Neuronet",
				"Universal Omnitech",
				"Yamatetsu"			
		};
		
		return brandNames[rand.nextInt(0, brandNames.length - 1)];
	}
}
