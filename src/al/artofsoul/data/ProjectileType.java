package al.artofsoul.data;

import static al.artofsoul.ndihma.Artist.*;

import org.newdawn.slick.opengl.Texture;

public enum ProjectileType {
	
	CANNON_BALL(quickLoad("/res/player/plumbiBlueVog"), 10, 200),
	ICE_BALL(quickLoad("/res/player/plumbiIceVog"), 6, 450);
	
	Texture texture;
	int damage;
	float speed;
	
	ProjectileType(Texture texture, int damage, float speed) {
		this.texture = texture;
		this.damage = damage;
		this.speed = speed;
	}
}
