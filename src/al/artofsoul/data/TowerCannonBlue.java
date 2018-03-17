package al.artofsoul.data;

import java.util.concurrent.CopyOnWriteArrayList;

public class TowerCannonBlue extends Tower {
	
	public TowerCannonBlue (TowerType type, Pllaka merrPllaka, CopyOnWriteArrayList<Armiku> armiqt) {
		super(type, merrPllaka, armiqt);
	}
	
	@Override
	public void shoot (Armiku target) {
		super.GetProjectileList().add(new ProjectileCannonball(super.GetTowerType().projectileType, super.GetTarget(), super.getX(), super.getY(), 32, 32));
		//super.target.reduceHiddenHealth(super.type.projectileType.damage);
	}
}
