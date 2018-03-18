package al.artofsoul.tower;


import al.artofsoul.data.armiku.Armiku;
import al.artofsoul.data.pllaka.Pllaka;
import al.artofsoul.data.projectile.ProjectileIceball;

import java.util.List;

public class TowerCannonIce extends Tower {

	public TowerCannonIce(TowerType type, Pllaka filloPllaka, List<Armiku> armiqt) {
		super(type, filloPllaka, armiqt);
	}
	
	@Override
	public void shoot (Armiku target) {
		super.getProjectileList().add(new ProjectileIceball(super.getTowerType().projectileType, super.getTarget(), super.getX(), super.getY(), 32, 32));
	}
}
