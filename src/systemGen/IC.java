package systemGen;

public class IC {
	private SRIC type = SRIC.UNDEFINED;
	private String id = "";
	private int rating = 0;
	private int condition = 0;
	private boolean alertTriggered = false;
	static final private int MAX_CONDITION = 10;
	
	//	Constructors
	
	public IC(SRIC type, String id, int rating) {
		this.type = type;
		this.id = id;
		this.rating = rating;
		this.condition = 0;
		this.alertTriggered = false;
	}
	
	public IC(SRIC type, int rating) {
		this(type, "", rating);
	}
	
	public IC() {
		this(SRIC.UNDEFINED, "", 0);
	}
	
	//	Getters, mutators
	
	public SRIC getType() { return this.type; }
	public String getID() { return this.id; }
	public int getCondition() { return this.condition; }
	public int getMaxCondition() { return MAX_CONDITION; }
	public boolean getAlertStatus() { return this.alertTriggered; }
	
	public void setType(SRIC val) { this.type = val; }
	public void setID(String val) { this.id = val; }
	public void setRating(int val) { this.rating = val; }
	public void setCondition(int val) { this.condition = val; }
	public void setAlertStatus(boolean val) { this.alertTriggered = val; }
	
	//	Public methods
	
	public int getRating() { 
		int effectiveRating = this.rating;
		if (this.alertTriggered) {
			effectiveRating += effectiveRating / 2;
		}
		return effectiveRating; 
	}

	public boolean isCrashed() { 
		return this.condition >= MAX_CONDITION; 
	}
	
	public boolean isWounded() { 
		return this.condition > 0; 
	}
	
	public void triggerAlert() {
		this.alertTriggered = true;
	}
	
	public void clearAlert() {
		this.alertTriggered = false;
	}
	
	public int woundMod() {
		int retVal = 0;
		if (this.condition == 0) {
			//	Unwounded
			retVal = 0;
		} else if (this.condition < 3) {
			//	Light
			retVal = 1;
		} else if (this.condition < 6) {
			//	Moderate
			retVal = 2;
		} else {
			//	Serious+
			retVal = 3;
		}
		return retVal;
	}
	
	public void resistDamage(int amount, int computerSkill) {
		int resistCheck = Dice.rollCheck(this.getRating(), computerSkill);
		amount -= resistCheck;
		if (amount > 0) {
			this.condition += amount;
		}
	}
	
	public String getSummary() {
		String retVal = icClassToString(this.type) + " " + icToString(this.type) + " " + this.rating;
		if (!this.id.isBlank()) {
			retVal += " (" + this.id + ")";
		}
		if (this.isCrashed()) {
			retVal += " - CRASHED";
		} else if (this.isWounded()) {
			retVal += " - WOUNDED";
		} else {
			retVal += " - OK";
		}
		return retVal;
	}
	
	public void printInfo() {
		System.out.println(icClassToString(this.type) + " " + icToString(this.type));
		if (!this.id.isBlank()) {
			System.out.println("ID: " + this.id);
		}
		System.out.println("--------------------");
		System.out.println("Rating: " + this.getRating());
		System.out.println("Condition: " + this.condition + " / " + MAX_CONDITION);
		
	}
	
	static public String icToString(SRIC val) {
		String retVal = "";
		switch (val) {
		case ACCESS:
			retVal = "Access";
			break;
		case BARRIER:
			retVal = "Barrier";
			break;
		case SCRAMBLE:
			retVal = "Scramble";
			break;
		case BLASTER:
			retVal = "Blaster";
			break;
		case KILLER:
			retVal = "Killer";
			break;
		case TARBABY:
			retVal = "Tar Baby";
			break;
		case TARPIT:
			retVal = "Tar Pit";
			break;
		case TRACEANDREPORT:
			retVal = "Trace and Report";
			break;
		case TRACEANDDUMP:
			retVal = "Trace and Dump";
			break;
		case TRACEANDBURN:
			retVal = "Trace and Burn";
			break;
		case BLACKIC:
			retVal = "IC";
			break;
		default:
			retVal = "Undefined";
		}
		return retVal;
	}
	
	static public String icClassToString(SRIC val) {
		String retVal = "";
		switch (val) {
		case ACCESS:
			retVal = "White";
			break;
		case BARRIER:
			retVal = "White";
			break;
		case SCRAMBLE:
			retVal = "White";
			break;
		case BLASTER:
			retVal = "Gray";
			break;
		case KILLER:
			retVal = "Gray";
			break;
		case TARBABY:
			retVal = "Gray";
			break;
		case TARPIT:
			retVal = "Gray";
			break;
		case TRACEANDREPORT:
			retVal = "Gray";
			break;
		case TRACEANDDUMP:
			retVal = "Gray";
			break;
		case TRACEANDBURN:
			retVal = "Gray";
			break;
		case BLACKIC:
			retVal = "Black";
			break;
		default:
			retVal = "Undefined";
		}
		return retVal;
	}
}
