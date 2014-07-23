package gameofwar;

public class SoldierFactory {

	public Soldier getSoldier(String soldierType, BaseCamp baseCamp, String name,Transport transport) {
		if (null == soldierType) {
			return (Soldier) null;
		}
		if (soldierType.equalsIgnoreCase("GROUND")) {
			return new GroundCamp(baseCamp, name,transport);
		} else if (soldierType.equalsIgnoreCase("AIR")) {
			return new AirCamp(baseCamp, name,transport);
		} else if (soldierType.equalsIgnoreCase("WATER")) {
			return new WaterCamp(baseCamp, name,transport);
		}
		return (Soldier) null;
	}
}
