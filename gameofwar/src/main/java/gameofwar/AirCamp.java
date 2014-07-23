package gameofwar;

import java.util.List;

public class AirCamp implements Soldier {

	protected String Name;
	protected Transport transport;
	BaseCamp baseCamp;
	String Vehicle;
	List<String> Weapons;

	public AirCamp(BaseCamp baseCamp, String Name, Transport transport) {
		this.baseCamp = baseCamp;
		this.baseCamp.addSoldier(this);
		this.Name = Name;
		this.transport = transport;
	}

	public void move() {
		System.out.println("Air Soldier moving");

	}

	public void shoot() {
		System.out.println("Air Soldier shooting");

	}

	public void update() {
		System.out.println("Air Soldier " + this.Name + " notified");
		transport.board(this.Name);

	}

}
