package al.artofsoul.data.projectile;

import static al.artofsoul.ndihma.Artist.*;
import static al.artofsoul.ndihma.Ora.*;

import al.artofsoul.data.armiku.Armiku;
import al.artofsoul.data.Entity;
import org.newdawn.slick.opengl.Texture;

public abstract class Projectile extends Entity {

	private Texture texture;
	private float speed;
	private float xVelocity;
	private float yVelocity;
	private int damage;
	private boolean alive;
	
	public Projectile(ProjectileType type, Armiku target, float x, float y, int width, int height ){
		this.texture = type.texture;
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = type.speed;
		this.damage = type.damage;
		this.target = target;
		this.alive = true;
		this.xVelocity = 0f;
		this.yVelocity = 0f;
		calculateDirection();	
	}
	
	private void calculateDirection() {
		float totalAllowedMovement = 1.0f;
		float xDistanceFromTraget = Math.abs(target.getX() - x - TILE_SIZE / 4 + TILE_SIZE / 2);
		float yDistanceFromTarget = Math.abs(target.getY() - y - TILE_SIZE / 4 + TILE_SIZE / 2);
		float totalDistanceFromTarget = xDistanceFromTraget + yDistanceFromTarget;
		float xPercentOfMovment = xDistanceFromTraget / totalDistanceFromTarget;
		xVelocity = xPercentOfMovment;
		yVelocity = totalAllowedMovement - xPercentOfMovment;
		//set direction based on position of target relative to tower
		if (target.getX() < x)
			xVelocity *= -1;
		if (target.getY() < y)
			yVelocity *= -1;
	}
	//Deals damage to Enemy
	public void damage () {
		target.damage(damage);
		alive = false;
	}
	
	public void update() {
		if (alive) {
			x += xVelocity * speed * delta();
			y += yVelocity * speed * delta();
			if (checkCollosion(x, y, width, height, target.getX(),
					target.getY(), target.getWidth()))
				damage();
			draw();
		}
	}
	 public void draw() {
		 vizatoKatrorTex(texture, x, y, 32, 32);
	 }

	public void setAlive(boolean status) {
		alive = status;
	}
}
