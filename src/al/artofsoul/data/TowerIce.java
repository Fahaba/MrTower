package al.artofsoul.data;


import java.util.List;

public class TowerIce extends Tower {

	public TowerIce(TowerType type, Pllaka filloPllaka, List<Armiku> armiqt) {
		super(type, filloPllaka, armiqt);
	}
	
	@Override
	public void shoot (Armiku target) {
		super.getProjectileList().add(new ProjectileIceball(super.getTowerType().projectileType, super.getTarget(), super.getX(), super.getY(), 32, 32));
	}
}
