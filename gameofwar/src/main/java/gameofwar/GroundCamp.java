package gameofwar;

import java.util.List;

public class GroundCamp implements Soldier {
	
	protected String Name;
	protected Transport transport;
	BaseCamp baseCamp;
	String Vehicle;
	List<String> Weapons;

	public GroundCamp(BaseCamp baseCamp, String name,Transport transport) {
		this.baseCamp = baseCamp;
		this.baseCamp.addSoldier(this);
		this.Name = name;
		this.transport=transport;
	}

	public void move() {
		System.out.println("Ground Soldier moving");

	}

	public void shoot() {
		System.out.println("Ground Soldier shooting");

	}

	public void update() {
		System.out.println("Ground Soldier " + this.Name + " notified");
		transport.board(this.Name);
	}

}
