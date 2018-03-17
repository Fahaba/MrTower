package al.artofsoul.data;


import java.util.concurrent.CopyOnWriteArrayList;

public class TowerCannonIce extends Tower {

	public TowerCannonIce(TowerType type, Pllaka filloPllaka, CopyOnWriteArrayList<Armiku> armiqt) {
		super(type, filloPllaka, armiqt);
	}
	
	@Override
	public void shoot (Armiku target) {
		super.GetProjectileList().add(new ProjectileIceball(super.GetTowerType().projectileType, super.GetTarget(), super.getX(), super.getY(), 32, 32));
		//super.target.reduceHiddenHealth(super.type.projectileType.damage);
	}
}
