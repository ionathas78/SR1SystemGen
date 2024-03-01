package systemGen;

public class BlackIC extends IC {

	private boolean isTriggered = false;
	
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
	 * Rolls damage against target decker's meat
	 * @param extraSuccesses Number of extra successes from the attack roll
	 * @param targetBod Target decker's Body attribute
	 * @return 0 if failure; number of wound boxes if positive
	 */
	public int damage(int extraSuccesses, int targetBody) {
		return Dice.rollCheck(this.getRating() + extraSuccesses, targetBody);
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
