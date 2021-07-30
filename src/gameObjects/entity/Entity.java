package gameObjects.entity;

import java.util.HashSet;
import java.util.Iterator;

import application.Main;
import javafx.scene.Group;
import javafx.scene.shape.Shape;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

public abstract class Entity {
	public static HashSet<Entity> entitySet = new HashSet<Entity>();
	private final double maxHealth;
	private double health;
	private String name;
	private Shape image;
	private Group entityGroup = new Group();
	private Rotate rotateGroup = new Rotate(0, 0, 0);
	private Translate translateGroup = new Translate(0, 0);
	
	public Entity(int maxHealth, String name) {
		this.maxHealth = maxHealth;
		this.health = maxHealth;
		this.name = name;
		this.entityGroup.getTransforms().addAll(rotateGroup, translateGroup);
	}

	public abstract void hit(int damage);
	
	public static boolean checkCollision(Shape image, int damage) {
		HashSet<Entity> set = Entity.entitySet;
		Iterator<Entity> iter = set.iterator();
		while(iter.hasNext()) {	
			Entity entity = iter.next();
			if (entity.getImage().getBoundsInParent().intersects(image.getBoundsInParent())) {
				if (!(entity instanceof Player)) {
					entity.hit(damage);
					Main.root.getChildren().remove(image);
					return true;
				}
			}
		}
		
		return false;
	}
	
	public double getMaxHealth() {
		return maxHealth;
	}

	public double getHealth() {
		return health;
	}

	public void setHealth(double d) {
		this.health = d;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Shape getImage() {
		return image;
	}

	public void setImage(Shape image) {
		this.image = image;
	}

	public Group getEntityGroup() {
		return entityGroup;
	}

	public void setEntityGroup(Group entityGroup) {
		this.entityGroup = entityGroup;
	}

	public Rotate getRotateGroup() {
		return rotateGroup;
	}

	public void setRotateGroup(Rotate rotateGroup) {
		this.rotateGroup = rotateGroup;
	}

	public Translate getTransltateGroup() {
		return translateGroup;
	}

	public void setTransltateGroup(Translate translateGroup) {
		this.translateGroup = translateGroup;
	}
	
}
