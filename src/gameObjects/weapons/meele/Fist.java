package gameObjects.weapons.meele;

import javafx.scene.shape.Rectangle;

public class Fist extends Meele {

	public Fist(int reloadTime, int fireRate, String name, int damage) {
		super(reloadTime, fireRate, name, damage);
		
		Rectangle image = new Rectangle(10, 10);
		image.setFill(javafx.scene.paint.Color.BROWN);
		this.setImage(image);
	}

	@Override
	public void activate(double angle, double x, double y) {
		if (!this.isFiring()) {
			Thread t = new Thread(this);
			t.start();
		}
	}

	@Override
	public void run() {
		this.setFiring(true);
		this.getImage().setFill(javafx.scene.paint.Color.RED);
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.getImage().setFill(javafx.scene.paint.Color.BROWN);
		this.setFiring(false);

	}

}
