package gameObjects.weapons.ranged;

import application.Main;
import gameObjects.weapons.Weapon;

public abstract class Ranged extends Weapon{
	private final int maxAmmo; // The max amount of bullets the player can hold
	private int currentAmmo; // The current number of bullets the player is holding, excluding clip
	private final int magSize; // The max number of bullets 1 mag can hold
	private int clipAmmo; // Current ammo in the mag
	private float projectileSpeed;
	
	public Ranged(int reloadTime, int fireRate, String name, int maxAmmo, int magSize, 
			float projectileSpeed, int damage) {
		super(reloadTime, fireRate, name, damage);
		this.maxAmmo = maxAmmo;
		this.currentAmmo = maxAmmo; // Player initialized to have maxAmmo
		this.magSize = magSize; 
		this.clipAmmo = magSize; // Gun initialized to be fully loaded
		this.projectileSpeed = projectileSpeed;
	}
	
	public boolean checkAmmo() {
		boolean clipFilled = false;
		
		if(this.getClipAmmo() > 0) {
			this.setClipAmmo(this.getClipAmmo() - 1);
			clipFilled = true;
		} else if (this.getCurrentAmmo() > 0){
			if(this.getCurrentAmmo() >= this.getClipAmmo()) {
				this.setClipAmmo(this.getMagSize());
				this.setCurrentAmmo(this.getCurrentAmmo() - this.getMagSize());
			} else {
				this.setClipAmmo(this.getCurrentAmmo());
				this.setCurrentAmmo(0);
			}
		}
		
		Main.equippedInfo.setText(this.getName() + "\n" +
				  this.getClipAmmo() + " / " + this.getCurrentAmmo());

		
		return clipFilled;
	}
	
	@Override
	public void run() {
		this.setFiring(true);
		
		try {
			if (this.getClipAmmo() == 0) {
				Thread.sleep(this.getReloadTime());
			} else {
				Thread.sleep(this.getFireRate());
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		this.setFiring(false);
	}

	public int getMaxAmmo() {
		return maxAmmo;
	}
	
	public int getCurrentAmmo() {
		return currentAmmo;
	}

	public void setCurrentAmmo(int currentAmmo) {
		this.currentAmmo = currentAmmo;
	}

	public int getMagSize() {
		return magSize;
	}

	public int getClipAmmo() {
		return clipAmmo;
	}

	public void setClipAmmo(int clipAmmo) {
		this.clipAmmo = clipAmmo;
	}

	public float getProjectileSpeed() {
		return projectileSpeed;
	}

	public void setProjectileSpeed(float projectileSpeed) {
		this.projectileSpeed = projectileSpeed;
	}

}
