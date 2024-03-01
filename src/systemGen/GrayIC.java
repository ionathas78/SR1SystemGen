package systemGen;

public class GrayIC extends IC {

	private boolean isTriggered = false;
	static final int TRACE_DURATION = 10;
	
	public boolean getIsTriggered() { return this.isTriggered; }
	
	public void setIsTriggered(boolean val) { this.isTriggered = val; }
	
	public void triggerIC() { this.isTriggered = true; }
	
	/**
	 * Makes an attack roll against target persona
	 * @param targetEvasion Target persona's Evasion rating
	 * @return 0 if failure; number of successes if positive
	 */
	public int attack(int targetEvasion) {
		return Dice.rollCheck(this.getRating(), targetEvasion + this.woundMod());
	}
	
	/**
	 * Rolls damage against target persona
	 * @param extraSuccesses Number of extra successes from the attack roll
	 * @param targetBod Target persona's Bod rating
	 * @return 0 if failure; number of wound boxes if positive
	 */
	public int damage(int extraSuccesses, int targetBod) {
		return Dice.rollCheck(this.getRating() + extraSuccesses, targetBod);
	}
	
	/**
	 * Attempts to fry the user's deck after crashing hir persona
	 * @param mpcpRating Rating of target deck's MPCP
	 * @return 0 if failure; number of successes if positive
	 */
	public int blastDeck(int mpcpRating) {
		return Dice.rollCheck(this.getRating(), mpcpRating + this.woundMod());
	}
	
	/**
	 * Attempts a Trace against the target decker
	 * @param targetMasking Target persona's Masking rating
	 * @return -1 if failure; number of turns to complete trace if positive
	 */
	public int trace(int targetMasking) {
		int retVal = -1;
		int successes = Dice.rollCheck(this.getRating(), targetMasking + this.woundMod());
		if (successes > 0) {
			retVal = (int) Math.ceil(10.0 / (double) successes);
		}
		return retVal;
	}
	
	public String getSummary() {
		String retVal = super.getSummary();
		if (this.isTriggered) {
			retVal += ": Active";
		} else {
			retVal += ": Inactive";
		}
		return retVal;
	}
}
