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
		setX(x);
		setY(y);
		setWidth(width);
		setHeight(height);
		this.speed = type.speed;
		this.damage = type.damage;
		setTarget(target);
		this.alive = true;
		this.xVelocity = 0f;
		this.yVelocity = 0f;
		calculateDirection();	
	}
	
	private void calculateDirection() {
		float totalAllowedMovement = 1.0f;
		Armiku target = getTarget();
		float x = getX();
		float y = getY();
		float xDistanceFromTraget = (float)Math.abs(target.getX() - x - (double)TILE_SIZE / 4 + (double)TILE_SIZE / 2);
		float yDistanceFromTarget = (float)Math.abs(target.getY() - y - (double)TILE_SIZE / 4 + (double)TILE_SIZE / 2);
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
		getTarget().damage(damage);
		alive = false;
	}
	
	public void update() {
		if (alive) {
		    float x = getX();
		    float y = getY();
		    Armiku target = getTarget();

			setX(x + xVelocity * speed * delta());
			setY(y + yVelocity * speed * delta());
            x = getX();
            y = getY();
			if (checkCollosion(x, y, getWidth(), getHeight(), target.getX(),
					target.getY(), target.getWidth()))
				damage();
			draw();
		}
	}
	 public void draw() {
		 vizatoKatrorTex(texture, getX(), getY(), 32, 32);
	 }

	public void setAlive(boolean status) {
		alive = status;
	}
}
