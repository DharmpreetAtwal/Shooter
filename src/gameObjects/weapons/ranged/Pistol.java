package gameObjects.weapons.ranged;

import gameObjects.projectile.Projectile;
import javafx.scene.shape.Rectangle;

public class Pistol extends Ranged{

	public Pistol(int reloadTime, int fireRate, String name, int maxAmmo, 
			int magSize, float projectileSpeed, int damage) {
		super(reloadTime, fireRate, name, maxAmmo, magSize, projectileSpeed, damage);
		
		Rectangle image = new Rectangle(30, 10);
		image.setFill(javafx.scene.paint.Color.GREY);
		this.setImage(image);
		
		this.setProjectileSpeed(projectileSpeed);
	}

	public void activate(double angle, double x, double y) {
		
		if(!this.isFiring() && checkAmmo()) {
			Projectile bullet = new Projectile(this.getDamage(), this.getProjectileSpeed(), angle, x, y);
			Rectangle image = ((Rectangle) bullet.getImage());
		
			image.setFill(javafx.scene.paint.Color.YELLOW);
			image.setX(400);
			image.setY(400);
			image.setWidth(10);
			image.setHeight(10);
			
			Thread thread = new Thread(this);
			thread.start();
		}	
	}

}
