package al.artofsoul.tower;

import static al.artofsoul.tower.Tower.TOPI_BAZA_VOG;
import static al.artofsoul.ndihma.Artist.*;

import al.artofsoul.data.projectile.ProjectileType;
import org.newdawn.slick.opengl.Texture;

public enum TowerType {
	CANNON_RED(new Texture[]{quickLoad(TOPI_BAZA_VOG), quickLoad("/res/player/topiRedplumbiVog")}, ProjectileType.CANNON_BALL, 10, 1000, 3, 0),
	CANNON_BLUE(new Texture[]{quickLoad(TOPI_BAZA_VOG), quickLoad("/res/player/topiBlueplumbiVog")}, ProjectileType.CANNON_BALL, 30, 1000, 3, 15),
	CANNON_ICE(new Texture[]{quickLoad(TOPI_BAZA_VOG), quickLoad("/res/player/topiIceplumbiVog")}, ProjectileType.ICE_BALL, 10, 1000, 3, 20),;

    Texture[] textures;
	ProjectileType projectileType;
	int damage;
    int range;
    int cost;
	float firingSpeed;
	
	TowerType(Texture[] textures, ProjectileType projectileType, int damage, int range, float firingSpeed, int cost) {
		this.textures = textures;
		this.projectileType = projectileType;
		this.damage = damage;
		this.range = range;
		this.firingSpeed = firingSpeed;
		this.cost = cost;
	}
}
