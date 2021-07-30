package gameObjects.obstacle;

import javafx.scene.shape.Rectangle;

public class Door extends Obstacle implements Interactable, Destructible {

	public Door(double x, double y, double rotate) {
		super();
		Rectangle image = new Rectangle();
		this.setImage(image);
		image.setFill(javafx.scene.paint.Color.SADDLEBROWN);
		image.setTranslateX(x);
		image.setTranslateY(y);
		image.setWidth(55);
		image.setHeight(5);
		image.setRotate(rotate);
		
		obstacleSet.add(this);
	}

}
