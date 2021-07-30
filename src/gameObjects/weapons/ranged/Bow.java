package gameObjects.weapons.ranged;

import gameObjects.projectile.Projectile;
import javafx.scene.shape.Rectangle;

public class Bow extends Ranged {

	public Bow(int reloadTime, int fireRate, String name, int maxAmmo, 
			int clipAmmo, float projectileSpeed, int damage) {
		super(reloadTime, fireRate, name, maxAmmo, clipAmmo, projectileSpeed, damage);
		Rectangle image = new Rectangle(10, 30);
		image.setFill(javafx.scene.paint.Color.SADDLEBROWN);
		this.setImage(image);
	}
	
	public void activate(double angle, double x, double y) {

		if(!this.isFiring() && checkAmmo()) {
			Projectile arrow = new Projectile(this.getDamage(), this.getProjectileSpeed(), angle, x, y);
			Rectangle image = (Rectangle) arrow.getImage();
			
			image.setFill(javafx.scene.paint.Color.BROWN);
			image.setX(400);
			image.setY(400);
			image.setWidth(30);
			image.setHeight(5);
			
			Thread thread = new Thread(this);
			thread.start();
		}
	}

}
