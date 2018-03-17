package al.artofsoul.data;

import java.util.concurrent.CopyOnWriteArrayList;

public class TowerIce extends Tower {

	public TowerIce(TowerType type, Pllaka filloPllaka, CopyOnWriteArrayList<Armiku> armiqt) {
		super(type, filloPllaka, armiqt);
	}
	
	@Override
	public void shoot (Armiku target) {
		super.GetProjectileList().add(new ProjectileIceball(super.GetTowerType().projectileType, super.GetTarget(), super.getX(), super.getY(), 32, 32));
	}
}
