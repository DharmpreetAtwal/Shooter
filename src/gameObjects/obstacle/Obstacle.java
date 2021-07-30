package gameObjects.obstacle;

import java.util.HashSet;
import java.util.Iterator;

import application.Main;
import javafx.scene.shape.Shape;

public abstract class Obstacle {
	public static HashSet<Obstacle> obstacleSet = new HashSet<Obstacle>();
	private Shape image;
	
	public Obstacle() {

	}

	public static boolean checkCollision(Shape image) {
		HashSet<Obstacle> set = Obstacle.obstacleSet;
		Iterator<Obstacle> iter = set.iterator();
		while(iter.hasNext()) {	
			Obstacle obstacle = iter.next();
			if (obstacle.getImage().getBoundsInParent().intersects(image.getBoundsInParent())) {
				Main.root.getChildren().remove(image);
				return true;
			}
		}
		
		return false;
	}

	public Shape getImage() {
		return image;
	}

	public void setImage(Shape image) {
		this.image = image;
	}

}
