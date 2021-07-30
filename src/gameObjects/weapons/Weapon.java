package gameObjects.weapons;

import javafx.scene.shape.Shape;

public abstract class Weapon implements Runnable{
	private Shape image;
	private int fireRate;
	private int reloadTime;
	private String name;
	private int damage;
	private boolean isFiring;
	
	public Weapon(int reloadTime, int fireRate, String name, int damage) {
		this.reloadTime=reloadTime;
		this.fireRate=fireRate;
		this.name = name;
		this.damage =damage;
	}
	
	public abstract void activate(double angle, double x, double y);

	public Shape getImage() {
		return image;
	}

	public void setImage(Shape image) {
		this.image = image;
	}

	public int getFireRate() {
		return fireRate;
	}

	public void setFireRate(int fireRate) {
		this.fireRate = fireRate;
	}

	public int getReloadTime() {
		return reloadTime;
	}

	public void setReloadTime(int reloadTime) {
		this.reloadTime = reloadTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public boolean isFiring() {
		return isFiring;
	}

	public void setFiring(boolean isFiring) {
		this.isFiring = isFiring;
	}
	
}
