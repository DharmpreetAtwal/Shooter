package gameObjects.entity;

import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class Zombie extends Entity{
	private Rectangle healthBar;
	
	public Zombie(int health, String name ) {
		super(health, name);
		Circle image = new Circle(10);
		image.setFill(javafx.scene.paint.Color.DARKGREEN);
		this.setImage(image);
		
		this.healthBar = new Rectangle();
		this.healthBar.setFill(javafx.scene.paint.Color.LAWNGREEN);
		this.healthBar.setHeight(5);
		this.healthBar.setWidth(50);
		this.healthBar.setTranslateX(-25);
		this.healthBar.setTranslateY(-20);

		this.translateX(200);
		this.translateY(200);
		
		this.getEntityGroup().getChildren().add(this.healthBar);
		this.getEntityGroup().getChildren().add(this.getImage());
		
		Entity.entitySet.add(this);
	}
	
	public void translateX(double x) {
		this.getTransltateGroup().setX(this.getTransltateGroup().getX() + x);
		this.getImage().setTranslateX(this.getTransltateGroup().getX());
	}
	
	public void translateY(double y) {
		this.getTransltateGroup().setY( this.getTransltateGroup().getY() + y);
		this.getImage().setTranslateY(this.getTransltateGroup().getY());
	}
	
	@Override
	public void hit(int damage) {
		this.setHealth(this.getHealth() - damage);
		double factor = this.getHealth() / this.getMaxHealth();
		this.healthBar.setWidth(50 * factor);
	}

}
