package al.artofsoul.data;

import al.artofsoul.data.armiku.Armiku;

public abstract class Entity {

    public float x;
    public float y;
    public int width;
    public int height;
    public Armiku target;

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

	public abstract void update();
	public abstract void draw();
}
