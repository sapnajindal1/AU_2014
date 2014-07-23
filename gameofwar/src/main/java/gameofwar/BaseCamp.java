package gameofwar;

import java.util.ArrayList;
import java.util.List;

public class BaseCamp implements Subject {
	// List of soldier observers
	private List<Soldier> soldiers;
	private boolean isWar;
	private static BaseCamp baseCamp;
	private String Name;
	private BaseCamp() {
		soldiers = new ArrayList<Soldier>();
	}

	public static BaseCamp getBaseCamp() {
		if (null == baseCamp) {
			synchronized (BaseCamp.class) {
				if (null == baseCamp) {
					baseCamp = new BaseCamp();
				}
			}
		}
		return baseCamp;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public void addSoldier(Soldier soldier) {
		soldiers.add(soldier);
	}

	public void notifyAllSoldiers() {
		for (Soldier s : soldiers) {
			s.update();
		}
	}

	public void setWar(boolean isWar) {
		this.isWar = isWar;
		notifyAllSoldiers();
	}

	public boolean isWar() {
		return isWar;
	}

}
