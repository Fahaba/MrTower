package al.artofsoul.data;

import java.util.concurrent.CopyOnWriteArrayList;

public class TowerCannonBlue extends Tower {
	
	public TowerCannonBlue (TowerType type, Pllaka merrPllaka, CopyOnWriteArrayList<Armiku> armiqt) {
		super(type, merrPllaka, armiqt);
	}
	
	@Override
	public void shoot (Armiku target) {
		super.getProjectileList().add(new ProjectileCannonball(super.getTowerType().projectileType, super.getTarget(), super.getX(), super.getY(), 32, 32));
	}
}
