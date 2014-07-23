package gameofwar;

public class Base {

	public static void main(String[] args) {
		BaseCamp bc=BaseCamp.getBaseCamp();
		Transport transport=new Transport("UFO");
		SoldierFactory sf=new SoldierFactory();
		
		bc.addSoldier(sf.getSoldier("GROUND", bc, "Mohit",transport));
		bc.addSoldier(sf.getSoldier("GROUND", bc, "Sir",transport));
		bc.addSoldier(sf.getSoldier("AIR", bc, "Lord",transport));
		bc.addSoldier(sf.getSoldier("AIR", bc, "jadeja",transport));
		bc.addSoldier(sf.getSoldier("WATER", bc, "Ambani",transport));
		bc.addSoldier(sf.getSoldier("WATER", bc, "Agent",transport));
		
		bc.setName("Ädda");
		bc.setWar(true);
		
		

	}

}
