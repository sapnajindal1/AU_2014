package gameofwar;

public interface Subject {

	public void addSoldier(Soldier soldier);

	public void notifyAllSoldiers();

	public void setWar(boolean isWar);
}
