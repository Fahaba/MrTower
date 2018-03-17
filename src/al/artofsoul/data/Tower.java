package al.artofsoul.data;

import static al.artofsoul.ndihma.Artist.VizatoKatrorTex;
import static al.artofsoul.ndihma.Artist.VizatoKatrorTexRot;
import static al.artofsoul.ndihma.Ora.Delta;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

import org.newdawn.slick.opengl.Texture;

public abstract class Tower implements Entity {
	
	private float x, y, timeSinceLastShot, firingSpeed, angle;
	private int width, height, range, cost;
	private Armiku target;
	private Texture[] textures;
	private CopyOnWriteArrayList<Armiku> armiqt;
	private boolean targeted;
	private ArrayList<Projectile> projectiles;
	private TowerType type;

	public Tower(TowerType type, Pllaka filloPllaka, CopyOnWriteArrayList<Armiku> armiqt) {
		SetType(type);
		this.textures = type.textures;
		this.range = type.range;
		this.cost = type.cost;
		this.x = filloPllaka.getX();
		this.y = filloPllaka.getY();
		this.width = filloPllaka.getWidth();
		this.height = filloPllaka.getHeight();
		this.armiqt = armiqt;
		this.targeted = false;
		this.timeSinceLastShot = 0f;
		SetProjectileList(new ArrayList<Projectile>());
		this.firingSpeed = type.firingSpeed;
		this.angle = 0f;
	}
	
	private Armiku acquireTarget() {
		Armiku closest = null;
		// arbitrary distance (Larger than map), to help with sorting Enemy distances
		float closestDistance = 1000;
		// Go thought each Enemy in 'Armiku' and return nearest one
		for (Armiku e: armiqt) {
			if (isInRange(e) && findDistance(e) < closestDistance && e.isAlive()) {//e.getHiddenHealth() > 0) {
				closestDistance = findDistance(e);
				closest = e;
			}
		}
		// if an enemy exists an is returned, targeted == true
		if (closest != null)
			targeted = true;
		return closest;
	}
	
	private boolean isInRange(Armiku e) {
		float xDistance = Math.abs(e.getX() - x);
		float yDistance = Math.abs(e.getY() - y);
		if (xDistance < range && yDistance < range)
			return true;
		return false;
	}
	
	private float findDistance(Armiku e) {
		float xDistance = Math.abs(e.getX() - x);
		float yDistance = Math.abs(e.getY() - y);
		return xDistance + yDistance;
	}
	
	private float calculateAngle () {
		double angleTemp = Math.atan2(GetTarget().getY() - y, target.getX() - x);
		return (float) Math.toDegrees(angleTemp) - 90;	
	}
	//abstarct method for 'shoot', must be override in subclasses
	public abstract void shoot (Armiku target);
	
	public void updateEnemyLists(CopyOnWriteArrayList<Armiku> newList) {
		armiqt = newList;
	}
	
	public void update(){
		if (!targeted ) { //|| target.getHiddenHealth() < 0 ) {
			SetTarget(acquireTarget());
		} else {
			angle = calculateAngle();
			if (timeSinceLastShot > firingSpeed) {
				shoot(GetTarget());
				timeSinceLastShot = 0;
			}
		}
		Armiku tar = GetTarget();
		if (tar == null || tar.isAlive() == false)
			targeted = false;
		
		timeSinceLastShot += Delta();
		
		for (Projectile p: projectiles)
			p.update();
		
		draw();
	}

	public void draw() {
		VizatoKatrorTex(textures[0], x, y, width, height);
		if (textures.length > 1)
			for(int i = 1; i < textures.length; i++)
				VizatoKatrorTexRot(textures[i], x, y, width, height, angle);
	}

	public void SetTarget(Armiku target) { this.target = target; }
    public Armiku GetTarget() { return target; }
	public ArrayList GetProjectileList() { return projectiles; }
    public void SetProjectileList(ArrayList projectiles) { this.projectiles = projectiles; }
    public void SetType(TowerType type) { this.type = type; }
    public TowerType GetTowerType() { return this.type; }

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setX(float x) {
		this.x = x;	
	}

	public void setY(float y) {
		this.y = y;
	}

	public void setWidth(int width) {
		this.width = width;	
	}

	public void setHeight(int height) {
		this.height = height;	
	}
	
	public Armiku getTarget() {
		return target;
	}
	public int getCost() {
		return cost;
	}
}
