package gameofwar;

public class Transport {
	
	String transportName;
	
	public Transport(String name) {
		this.transportName=name;
	}
	public void board(String Name) {
		System.out.println("Soldier "+ Name + " boarded.");
	}
	
	public void deploy() {
		System.out.println("Soldiers deployed");
	}
}
