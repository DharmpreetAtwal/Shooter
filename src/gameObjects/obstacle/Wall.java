package gameObjects.obstacle;

import javafx.scene.shape.Rectangle;

public class Wall extends Obstacle {

	public Wall(double x, double y, double width, double height) {
		super();
		Rectangle image = new Rectangle(width, height);
		image.setFill(javafx.scene.paint.Color.FIREBRICK);
		this.setImage(image);
		this.getImage().setTranslateX(x);
		this.getImage().setTranslateY(y);
		
		obstacleSet.add(this);
	}

}
