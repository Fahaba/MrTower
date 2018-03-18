package al.artofsoul.data;


import static al.artofsoul.ndihma.Artist.*;
import static al.artofsoul.ndihma.Ora.*;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.opengl.Texture;

public class KullaTop {
	
	private float x;
	private float y;
	private float timeSinceLastShot;
	private float firingSpeed;
	private float angle;
	private int width;
	private int height;
	private int range;
	private Texture baseTexture;
	private Texture cannonTexture;
	private ArrayList<Projectile> projectiles;
	private List<Armiku> armiqt;
	private Armiku target;
	private boolean targeted;
	
	
	public KullaTop (Texture baseTexture, Pllaka filloPllaka, int range, List<Armiku> armiqt){
		this.baseTexture = baseTexture;
        this.cannonTexture = quickLoad("/res/player/topiBazaVog"); //plumi for 64, topiBaza for 32

        this.x = filloPllaka.getX();
		this.y = filloPllaka.getY();
		this.width = filloPllaka.getWidth();
		this.height = filloPllaka.getHeight();
		this.range = range;
		this.timeSinceLastShot = 0;
		this.firingSpeed = 3;
		this.projectiles = new ArrayList<>();
		this.armiqt = armiqt;
		this.targeted = false;
	}
	
	
	
	private Armiku acquireTarget() {
		Armiku closest = null;
		float closestDistance = 1000;
		for (Armiku e: armiqt) {
			if (isInRange(e) && findDistance(e) < closestDistance) {
				closestDistance = findDistance(e);
				closest = e;
			}
		}
		if (closest != null)
			targeted = true;
		return closest;
	}
	
	private boolean isInRange(Armiku e) {
		float xDistance = Math.abs(e.getX() - x);
		float yDistance = Math.abs(e.getY() - y);
		return (xDistance < range && yDistance < range);
	}
	
	private float findDistance(Armiku e) {
		float xDistance = Math.abs(e.getX() - x);
		float yDistance = Math.abs(e.getY() - y);
		return xDistance + yDistance;
	}
	
	private float calculateAngle () {
		double angleTemp = Math.atan2(target.getY() - y, target.getX() - x);
		return (float) Math.toDegrees(angleTemp) - 90;	
	}
	
	private void shoot(){ timeSinceLastShot = 0; }
	
	public void updateEnemyLists(List<Armiku> newList) {
		armiqt = newList;
	}
	
	public void update(){
		if (!targeted) {
			target = acquireTarget();
		}
		
		if (target == null || target.isAlive())
			targeted = false;
		
		timeSinceLastShot += delta();
		if(timeSinceLastShot > firingSpeed)
			shoot();
		
		for (Projectile p: projectiles)
			p.update();
		
		angle = calculateAngle();
		draw();
	}
	
	public void draw(){
		vizatoKatrorTex(baseTexture, x, y, width, height);
		vizatoKatrorTexRot(cannonTexture, x, y, width, height, angle);
	}

}
