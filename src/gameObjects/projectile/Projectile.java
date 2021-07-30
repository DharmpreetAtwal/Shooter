package gameObjects.projectile;

import application.Main;
import gameObjects.entity.Entity;
import gameObjects.obstacle.Obstacle;
import javafx.animation.TranslateTransition;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class Projectile {
	private Shape image;
	private float projectileSpeed;
	private float xVel;
	private float yVel;

	public Projectile(int damage, float projectileSpeed, double angle, double x, double y) {
		this.projectileSpeed = projectileSpeed;
		this.image = new Rectangle(400, 400, 10, 10);
		this.image.setFill(javafx.scene.paint.Color.YELLOW);
		this.getImage().setRotate(angle);
		this.getImage().setTranslateX(x + 10);
		this.getImage().setTranslateY(y + 10);

		Main.root.getChildren().add(this.getImage());

		double angleRad = Math.toRadians(angle);
		this.xVel = (float) (this.projectileSpeed * Math.cos(angleRad));
		this.yVel = (float) (this.projectileSpeed * Math.sin(angleRad));
		
		TranslateTransition translate = new TranslateTransition();
		translate.setByX(300 * this.xVel);
		translate.setByY(300 * this.yVel);
        translate.setCycleCount(1);  
        translate.setAutoReverse(false);  
        translate.setNode(this.getImage());
        translate.play();
        
        this.image.translateXProperty().addListener((observable, oldValue, newValue) -> {
        	if(Obstacle.checkCollision(this.image) || Entity.checkCollision(this.image, damage)) {
        		translate.stop();
        	}
        });
        
	}
	
	

	public Shape getImage() {
		return image;
	}

	public void setImage(Shape image) {
		this.image = image;
	}

	public float getProjectileSpeed() {
		return projectileSpeed;
	}

	public void setProjectileSpeed(float projectileSpeed) {
		this.projectileSpeed = projectileSpeed;
	}

	public float getxVel() {
		return xVel;
	}

	public void setxVel(float xVel) {
		this.xVel = xVel;
	}

	public float getyVel() {
		return yVel;
	}

	public void setyVel(float yVel) {
		this.yVel = yVel;
	}

}
