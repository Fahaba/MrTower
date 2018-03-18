package al.artofsoul.data;

import static al.artofsoul.ndihma.Artist.vizatoKatrorTex;
import static al.artofsoul.ndihma.Artist.vizatoKatrorTexRot;
import static al.artofsoul.ndihma.Ora.delta;

import java.util.ArrayList;
import java.util.List;

import org.newdawn.slick.opengl.Texture;

public abstract class Tower implements Entity {


    public static final String TOPI_BAZA_VOG = "/res/player/topiBazaVog";

	private float x;
    private float y;
    private float timeSinceLastShot;
    private float firingSpeed;
    private float angle;
	private int width;
    private int height;
    private int range;
    private int cost;
	private Armiku target;
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
		this.x = filloPllaka.getX();
		this.y = filloPllaka.getY();
		this.width = filloPllaka.getWidth();
		this.height = filloPllaka.getHeight();
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
		double angleTemp = Math.atan2(getTarget().getY() - y, target.getX() - x);
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
		vizatoKatrorTex(textures[0], x, y, width, height);
		if (textures.length > 1)
			for(int i = 1; i < textures.length; i++)
				vizatoKatrorTexRot(textures[i], x, y, width, height, angle);
	}

	public void setTarget(Armiku target) { this.target = target; }
	public List getProjectileList() { return projectiles; }
    public void setProjectileList(List projectiles) { this.projectiles = projectiles; }
    public void setType(TowerType type) { this.type = type; }
    public TowerType getTowerType() { return this.type; }

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
	
	public Armiku getTarget() { return target; }
	public int getCost() {
		return cost;
	}
}
