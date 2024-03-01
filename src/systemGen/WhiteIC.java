package systemGen;

public class WhiteIC extends IC {

	/**
	 * Attempts to raise a system alert against target persona
	 * @param targetMasking Target persona's Masking rating
	 * @return 0 if failure; success if positive
	 */
	public boolean raiseAlarm(int targetMasking) {
		return Dice.rollCheck(this.getRating(), targetMasking) > 0;
	}
	
}
