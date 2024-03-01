package systemGen;

public class Dice {
	
	public static int rollCheck(int rating, int tN) {
		int noSuccesses = 0;
		int noOnes = 0;
		for (int i = 0; i < rating; i++) {
			int currRoll = rollSRDie();
			if (currRoll >= tN) {
				noSuccesses++;
			} else if (currRoll == 1) {
				noOnes++;
			}
		}
		if (noOnes == rating) {
			noSuccesses = -1;
		}
		return noSuccesses;
	}
	
	public static int rollD6(int noDice) {
		int noSides = 6;
		int totalRoll = 0;
		for (int i = 0; i < noDice; i++) {
			totalRoll += Math.floor(Math.random() * noSides) + 1;
		}
		return totalRoll;
	}
	
	public static SRIC randomWhiteIC() {
		SRIC retVal = SRIC.UNDEFINED;
		int d6 = rollD6(1);
		switch (d6) {
			case 1, 2, 3:
				retVal = SRIC.ACCESS;
				break;
			case 4, 5:
				retVal = SRIC.BARRIER;
				break;
			case 6:
				retVal = SRIC.SCRAMBLE;
		}
		return retVal;
	}
	public static SRIC randomGrayIC() {
		SRIC retVal = SRIC.UNDEFINED;
		int d6 = rollD6(2);
		switch (d6) {
			case 2, 3:
				retVal = SRIC.BLASTER;
				break;
			case 4, 5, 6:
				retVal = SRIC.KILLER;
				break;
			case 7, 8:
				retVal = SRIC.TARBABY;
				break;
			case 9:
				retVal = SRIC.TRACEANDREPORT;
				break;
			case 10:
				retVal = SRIC.TRACEANDDUMP;
				break;
			case 11:
				retVal = SRIC.TARPIT;
				break;
			case 12:
				retVal = SRIC.TRACEANDBURN;
		}
		return retVal;
	}
	
	static private int rollSRDie() {
		int currentRoll;
		int totalRoll = 0;
		do {
			currentRoll = (int) Math.floor(Math.random() * 6) + 1;
			totalRoll += currentRoll;
		} while (currentRoll == 6);
		return totalRoll;
	}
}
