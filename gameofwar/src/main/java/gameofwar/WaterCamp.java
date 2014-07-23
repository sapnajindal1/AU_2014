package gameofwar;

import java.util.List;

public class WaterCamp implements Soldier {
	protected String Name;
	protected Transport transport;
	BaseCamp baseCamp;
	String Vehicle;
	List<String> Weapons;

	public WaterCamp(BaseCamp baseCamp, String Name,Transport transport) {
		this.baseCamp = baseCamp;
		this.baseCamp.addSoldier(this);
		this.Name = Name;
		this.transport=transport;
	}

	public void move() {
		System.out.println("Water Soldier moving");

	}

	public void shoot() {
		System.out.println("Water Soldier shooting");

	}

	public void update() {
		System.out.println("Water Soldier " + this.Name + " notified");
		transport.board(this.Name);

	}

}
