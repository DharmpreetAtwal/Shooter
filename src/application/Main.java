package application;
	
import java.util.Iterator;

import gameObjects.entity.Entity;
import gameObjects.entity.Player;
import gameObjects.entity.Zombie;
import gameObjects.obstacle.Door;
import gameObjects.obstacle.Obstacle;
import gameObjects.obstacle.Wall;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;


public class Main extends Application {
	public static BorderPane root = new BorderPane();
	public static Text equippedInfo;
	
	public void start(Stage primaryStage) {
		Scene scene = new Scene(root,800,800);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		
		Player player = new Player(100, "Dharm", scene);
		Zombie zomb1 = new Zombie(100, "zomb1");
		
		initTimer(player);
		
		Wall wall1 = new Wall(150, 150, 100, 5);
		Wall wall4 = new Wall(150, 150, 5, 100);
		Wall wall2 = new Wall(250, 150, 5, 100);
		Wall wall3 = new Wall(150, 250, 50, 5);
		Door door1 = new Door(200, 250, 0);

		Group cameraGroup = new Group();
		cameraGroup.getChildren().add(player.getPlayerCamera());
		scene.setCamera(player.getPlayerCamera());
	
		equippedInfo = new Text("Info");
		equippedInfo.setStyle("-fx-font: 36 arial;");
		equippedInfo.setTranslateX(600);
		equippedInfo.setTranslateY(600);
		VBox infoContainer = new VBox();
		infoContainer.getChildren().add(equippedInfo);
		root.getChildren().add(infoContainer);

		Iterator<Entity> iter = Entity.entitySet.iterator();
		while(iter.hasNext()) {
//			MAKING entityGroup THE PARENT OF IMAGE CAUSES ISSUES WITH COLLISION
//			IMAGEE'S PARENT MUST BE THE SAME AS THE OBSTACLES IT COLLIDES WITH TO ENSURE A CONSISTENT COORD SYSTEM
//			TRANSFORM BOUNDS SO THAT IT ALIGNS WITH THE CORRECT COORD SYSTEM????
			Entity entity = iter.next();
			root.getChildren().add(entity.getImage());
			root.getChildren().add(entity.getEntityGroup());
		}
	
		Iterator<Obstacle> iter2 = Obstacle.obstacleSet.iterator();
		while(iter2.hasNext()) {
			Obstacle obstacle = iter2.next();
			root.getChildren().add(obstacle.getImage());
		}
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	public void initTimer(Player player) {
		AnimationTimer timer = new AnimationTimer() {
			boolean[] keys = player.getPlayerState();
			@Override
			public void handle(long now) {
				int dx=0, dy=0;
				if(keys[0]) dy -= 1;
				if(keys[1]) dy += 1;
				if(keys[2]) dx -= 1;
				if(keys[3]) dx += 1;
                if(keys[4]) {dx *= 3; dy *= 3;}
                
                player.move(dx, dy);
                player.updateTransforms();
                equippedInfo.setTranslateX(player.getPlayerCamera().getLayoutX());
                equippedInfo.setTranslateY(player.getPlayerCamera().getLayoutY());

			}
		};
		timer.start();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
