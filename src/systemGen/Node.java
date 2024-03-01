package systemGen;
import java.util.ArrayList;

public class Node {
	private String name = "";
	private SRSecurityColors securityColor = SRSecurityColors.UNDEFINED;
	private int securityRating = 1;
	private boolean isTriggered = false;
	
	private ArrayList<IC> ic = new ArrayList<IC>();
	private ArrayList<Node> linkedNodes = new ArrayList<Node>();

	//	Constructors
	
	public Node(String name, SRSecurityColors secColor, int secRating) {
		this.name = name;
		this.securityColor = secColor;
		this.securityRating = secRating;
	}
	
	public Node(SRSecurityColors secColor, int secRating) {
		this("", secColor, secRating);
	}
	
	public Node() {
		this("", SRSecurityColors.UNDEFINED, 1);
	}
	
	//	Getters, mutators
	
	public String getName() { return this.name; }
	public SRSecurityColors getColor() { return this.securityColor; }
	public int getRating() { return this.securityRating; }
	public IC getIC(int idx) { return this.ic.get(idx); }
	public Node getLinkedNode(int idx) { return this.linkedNodes.get(idx); }
	public int numberIC() { return this.ic.size(); }
	public int numberLinkedNodes() { return this.linkedNodes.size(); }
	public boolean isTriggered() { return this.isTriggered; }
	
	public void setName(String val) { this.name = val; }
	public void setColor(SRSecurityColors val) { this.securityColor = val; }
	public void setRating(int val) { this.securityRating = val; }
	public void addIC(IC toAdd) { this.ic.add(toAdd); }
	public void linkNode(Node toAdd) { this.linkedNodes.add(toAdd); }
	public void removeIC(int idx) { this.ic.remove(idx); }
	public void removeLinkedNode(int idx) { this.linkedNodes.remove(idx); }
	public void setIsTriggered(boolean val) { this.isTriggered = val; }
	
	//	Public methods
	
	public int requiredSuccesses() {
		return requiredSuccessesByColor(this.securityColor);
	}
	
	public void triggerNode() {
		this.isTriggered = true;
	}
	
	public boolean checkForAlert(int attemptedOperations) {
		return (Dice.rollD6(1) <= attemptedOperations);
	}
	public boolean checkForAlert() {
		return checkForAlert(1);
	}

	public String getSummary() {
		String retVal = colorToString(this.securityColor) + " " + this.securityRating + " Node";
		if (!this.name.isBlank()) {
			retVal += " (" + this.name + ")";
		}
		return retVal;		
	}
	
	public void printInfo(boolean verbose) {
		System.out.println();
		System.out.println("Node " + this.name);
		System.out.println("-------------------------");
		System.out.println(colorToString(this.securityColor) + " " + this.securityRating);
		if (verbose && this.ic.size() > 0) {
			System.out.println("IC ->");
			for (int i = 0; i < this.ic.size(); i++) {
				System.out.println(this.ic.get(i).getSummary());
			}
		}
		if (verbose && this.linkedNodes.size() > 0) {
			System.out.println("Linked Nodes ->");
			for (int j = 0; j < this.linkedNodes.size(); j++) {
				System.out.println(this.linkedNodes.get(j).getSummary());
			}
		}
	}
	public void printInfo() {
		this.printInfo(true);
	}
	
	public void randomize() {
		int randColor = Dice.rollD6(1);
		SRIC icType;
		IC security;
		switch (randColor) {
		case 1:
			this.securityColor = SRSecurityColors.BLUE;
			this.securityRating = Dice.rollD6(1);
			break;
		case 2, 3:
			this.securityColor = SRSecurityColors.GREEN;
			this.securityRating = Dice.rollD6(1) + 1;
			icType = Dice.randomWhiteIC();
			security = new IC(icType, Dice.rollD6(1) + 1);
			this.addIC(security);
			break;
		case 4, 5:
			this.securityColor = SRSecurityColors.ORANGE;
			this.securityRating = Dice.rollD6(2);
			icType = Dice.randomWhiteIC();
			security = new IC(icType, Dice.rollD6(2));
			this.addIC(security);
			if (Dice.rollD6(1) > 3) {
				icType = Dice.randomGrayIC();
				security = new IC(icType, Dice.rollD6(2));
				this.addIC(security);
			}
			break;
		case 6:
			this.securityColor = SRSecurityColors.RED;
			this.securityRating = Dice.rollD6(2) + 1;
			icType = Dice.randomWhiteIC();
			security = new IC(icType, Dice.rollD6(2) + 1);
			this.addIC(security);
			if (Dice.rollD6(1) < 5) {
				icType = Dice.randomGrayIC();
			} else {
				icType = SRIC.BLACKIC;
			}
			security = new IC(icType, Dice.rollD6(2) + 1);
			this.addIC(security);
		}
	}
	
	//	Static public methods
	
	static public int requiredSuccessesByColor(SRSecurityColors val) {
		int retVal = 0;
		switch (val) {
		case BLUE:
			retVal = 1;
			break;
		case GREEN:
			retVal = 2;
			break;
		case ORANGE:
			retVal = 3;
			break;
		case RED:
			retVal = 4;
			break;
		default:
			retVal = -1;
		}
		return retVal;
	}
	static public String colorToString(SRSecurityColors val) {
		String retVal = "";
		switch (val) {
		case BLUE:
			retVal = "Blue";
			break;
		case GREEN:
			retVal = "Green";
			break;
		case ORANGE:
			retVal = "Orange";
			break;
		case RED:
			retVal = "Red";
			break;
		default:
			retVal = "Undefined";
		}
		return retVal;
	}
}
