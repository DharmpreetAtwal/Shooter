package gameObjects.weapons.meele;

import gameObjects.weapons.Weapon;

public abstract class Meele extends Weapon {

	public Meele(int reloadTime, int fireRate, String name, int damage) {
		super(reloadTime, fireRate, name, damage);
	}

}
