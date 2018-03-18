package al.artofsoul.data;

import al.artofsoul.data.Pllaka.Pllaka;

public class Checkpoint {
	
	private Pllaka pllaka;
	private int xDirection;
	private int yDirection;
	
	public Checkpoint (Pllaka pllaka, int xDirection, int yDirection) {
		this.pllaka = pllaka;
		this.xDirection = xDirection;
		this.yDirection = yDirection;
	}

	public Pllaka getPllaka() {
		return pllaka;
	}

	public int getxDirection() {
		return xDirection;
	}

	public int getyDirection() {
		return yDirection;
	}
}
