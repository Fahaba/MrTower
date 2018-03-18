package al.artofsoul.tower;

import static al.artofsoul.ndihma.Artist.vizatoKatrorTex;
import static al.artofsoul.ndihma.Artist.vizatoKatrorTexRot;
import static al.artofsoul.ndihma.Ora.delta;

import java.util.ArrayList;
import java.util.List;

import al.artofsoul.data.armiku.Armiku;
import al.artofsoul.data.Entity;
import al.artofsoul.data.pllaka.Pllaka;
import al.artofsoul.data.projectile.Projectile;
import org.newdawn.slick.opengl.Texture;

public abstract class Tower extends Entity {


    public static final String TOPI_BAZA_VOG = "/res/player/topiBazaVog";

    private float timeSinceLastShot;
    private float firingSpeed;
    private float angle;
    private int range;
    private int cost;
	private Texture[] textures;
	private List<Armiku> armiqt;
	private boolean targeted;
	private List<Projectile> projectiles;
	private TowerType type;

	public Tower(TowerType type, Pllaka filloPllaka, List<Armiku> armiqt) {
		setType(type);
		this.textures = type.textures;
		this.range = type.range;
		this.cost = type.cost;
		setX(filloPllaka.getX());
		setY(filloPllaka.getY());
		setWidth(filloPllaka.getWidth());
		setHeight(filloPllaka.getHeight());
		this.armiqt = armiqt;
		this.targeted = false;
		this.timeSinceLastShot = 0f;
		setProjectileList(new ArrayList<Projectile>());
		this.firingSpeed = type.firingSpeed;
		this.angle = 0f;
	}
	
	private Armiku acquireTarget() {
		Armiku closest = null;
		// arbitrary distance (Larger than map), to help with sorting Enemy distances
		float closestDistance = 1000;
		// Go thought each Enemy in 'Armiku' and return nearest one
		for (Armiku e: armiqt) {
			if (isInRange(e) && findDistance(e) < closestDistance && e.isAlive()) {
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
		float xDistance = Math.abs(e.getX() - getX());
		float yDistance = Math.abs(e.getY() - getY());
		return (xDistance < range && yDistance < range);
	}
	
	private float findDistance(Armiku e) {
		float xDistance = Math.abs(e.getX() - getX());
		float yDistance = Math.abs(e.getY() - getY());
		return xDistance + yDistance;
	}
	
	private float calculateAngle () {
		double angleTemp = Math.atan2(getTarget().getY() - getY(), getTarget().getX() - getX());
		return (float) Math.toDegrees(angleTemp) - 90;	
	}
	//abstarct method for 'shoot', must be override in subclasses
	public abstract void shoot (Armiku target);
	
	public void updateEnemyLists(List<Armiku> newList) {
		armiqt = newList;
	}
	
	public void update(){
		if (!targeted ) {
			setTarget(acquireTarget());
		} else {
			angle = calculateAngle();
			if (timeSinceLastShot > firingSpeed) {
				shoot(getTarget());
				timeSinceLastShot = 0;
			}
		}
		Armiku tar = getTarget();
		if (tar == null || !tar.isAlive())
			targeted = false;
		
		timeSinceLastShot += delta();
		
		for (Projectile p: projectiles)
			p.update();
		
		draw();
	}

	public void draw() {
	    float x = getX();
	    float y = getY();
	    int width = getWidth();
	    int height = getHeight();

		vizatoKatrorTex(textures[0], x, y, width, height);
		if (textures.length > 1)
			for(int i = 1; i < textures.length; i++)
				vizatoKatrorTexRot(textures[i], x, y, width, height, angle);
	}

	public List getProjectileList() { return projectiles; }
    public void setProjectileList(List projectiles) { this.projectiles = projectiles; }
    public void setType(TowerType type) { this.type = type; }
    public TowerType getTowerType() { return this.type; }

	public int getCost() {
		return cost;
	}
}
