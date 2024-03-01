package systemGen;

public class CPU extends Node {

	public void shutdown() {
		System.out.println("SYSTEM SHUTDOWN");
	}
	
	public void cancelAlert() {
		this.setIsTriggered(false);
		for (int i = 0; i < this.numberIC(); i++) {
			this.getIC(i).clearAlert();
		}
		System.out.println("ALERT: CANCELLED");
	}
	
	@Override
	public void printInfo(boolean verbose) {
		System.out.println();
		System.out.println("Central Processing Unit " + this.getName());
		System.out.println("-------------------------");
		System.out.println(colorToString(this.getColor()) + " " + this.getRating());
		if (verbose && this.numberIC() > 0) {
			System.out.println("IC ->");
			for (int i = 0; i < this.numberIC(); i++) {
				System.out.println(this.getIC(i).getSummary());
			}
		}
	}
	public void printInfo() {
		this.printInfo(true);
	}

}
