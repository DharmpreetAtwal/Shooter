package gameObjects.entity;
import java.util.HashSet;
import java.util.Iterator;

import application.Main;
import gameObjects.obstacle.Obstacle;
import gameObjects.weapons.Weapon;
import gameObjects.weapons.meele.Fist;
import gameObjects.weapons.ranged.Bow;
import gameObjects.weapons.ranged.Pistol;
import gameObjects.weapons.ranged.Ranged;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Camera;
import javafx.scene.ParallelCamera;
import javafx.scene.input.KeyEvent;
import javafx.scene.shape.Circle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Transform;
import javafx.scene.transform.Translate;
import javafx.scene.Scene;

public class Player extends Entity {
	private ParallelCamera playerCamera;
	private boolean[] playerState = {false, false, false, false, false}; //	North, South, West, East, running
	private Weapon[] weapons = new Weapon[3];
	private Weapon equipped;
	private double angle = 0;
	
	public Player(int health, String name, Scene scene) {
		super(health, name);
		
		Circle image = new Circle(400, 400, 10);
		image.setFill(javafx.scene.paint.Color.ORANGE);
		this.setImage(image);
		
		this.playerCamera = new ParallelCamera();
		this.playerCamera.setLayoutX(image.getLayoutX());
		this.playerCamera.setLayoutY(image.getLayoutY());
		
		this.getEntityGroup().getChildren().add(this.getImage());
		this.getTransltateGroup().setX(this.getX());
		this.getTransltateGroup().setY(this.getY());
		
		Fist fist = new Fist(0, 500, "Fist", 10);
		Pistol pistol = new Pistol(2000, 500, "Pistol", 20, 10, 2.0f, 10);
		Bow bow = new Bow(2000, 1500, "Bow", 12, 2, 1.5f, 20);
		
		this.weapons[0] = fist;
		this.weapons[1] = pistol;
		this.weapons[2] = bow;
		this.equipped = weapons[0];
		this.equipped.getImage().setVisible(true);
		
		for (int i=0; i<this.getWeapons().length; i++) {
			this.getWeapons()[i].getImage().setVisible(false);
			this.getWeapons()[i].getImage().setLayoutX(image.getCenterX());
			this.getWeapons()[i].getImage().setLayoutY(image.getCenterY());
			this.getEntityGroup().getChildren().add(this.getWeapons()[i].getImage());
		}

		this.initKeyActions(scene);
		this.initMouseActions(scene);
		Entity.entitySet.add(this);
	}
	
// USE TIMER INSTEAD OF THREAD????/
	
	public void move(int dx, int dy) {
        Camera camera = this.getPlayerCamera();

        if (!this.checkCollisionX(dx)) {
            camera.setLayoutX(camera.getLayoutX() + dx);
            this.getTransltateGroup().setX(camera.getLayoutX());
            this.getRotateGroup().setPivotX(camera.getLayoutX());
        }

        if(!this.checkCollisionY(dy)) {
            camera.setLayoutY(camera.getLayoutY() + dy);
            this.getTransltateGroup().setY(camera.getLayoutY());
            this.getRotateGroup().setPivotY(camera.getLayoutY());
        }  
	}
	
	public void updateTransforms() {
		this.getRotateGroup().setPivotX(this.playerCamera.getLayoutX() + 400);
		this.getRotateGroup().setPivotY(this.playerCamera.getLayoutY() + 400);
		this.getRotateGroup().setAngle(this.angle);
	}
	
	private boolean checkCollisionX(int dx) {
		HashSet<Obstacle> set = Obstacle.obstacleSet;
		Iterator<Obstacle> iter = set.iterator();
		
		Circle image = (Circle)this.getImage();
		image.setLayoutX(this.playerCamera.getLayoutX() + dx);
		
		while(iter.hasNext()) {
			Obstacle obstacle = iter.next();
			if (obstacle.getImage().getBoundsInParent().intersects(image.getBoundsInParent())) {
				image.setLayoutX(this.playerCamera.getLayoutX() - dx);
				return true;
			}
		}
		image.setLayoutX(this.playerCamera.getLayoutX() - dx);
		return false;
	} 
	
	private boolean checkCollisionY(int dy) {
		HashSet<Obstacle> set = Obstacle.obstacleSet;
		Iterator<Obstacle> iter = set.iterator();
		
		Circle image = (Circle)this.getImage();
		image.setLayoutY(this.playerCamera.getLayoutY() + dy);

		while(iter.hasNext()) {
			Obstacle obstacle = iter.next();
			if (obstacle.getImage().getBoundsInParent().intersects(image.getBoundsInParent())) {
				image.setLayoutY(this.playerCamera.getLayoutY() - dy);
				return true;
			}
		}
		
		image.setLayoutY(this.playerCamera.getLayoutY() - dy);
		return false;
	} 
	
	public void initKeyActions(Scene scene) {
		Weapon[] weapon = this.weapons;
		boolean[] playerSta = this.playerState;
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				switch(event.getCode()) {
					case W: playerSta[0] = true; break;
					case S: playerSta[1] = true; break;
					case A: playerSta[2] = true; break;
					case D: playerSta[3] = true; break;
					case SHIFT: playerSta[4] = true; break;
					case DIGIT1: equipped = weapon[0]; 
								 weapon[0].getImage().setVisible(true);
								 weapon[1].getImage().setVisible(false); 
								 weapon[2].getImage().setVisible(false); 
								 break;
					case DIGIT2: equipped = weapon[1]; 
					 			 weapon[0].getImage().setVisible(false); 
								 weapon[1].getImage().setVisible(true); 
								 weapon[2].getImage().setVisible(false); 
								 break;
					case DIGIT3: equipped = weapon[2]; 
								 weapon[0].getImage().setVisible(false); 
								 weapon[1].getImage().setVisible(false);
					             weapon[2].getImage().setVisible(true); 
					             break;
				default:
					break;
				}
				
				if (equipped instanceof Ranged) {
					Main.equippedInfo.setText(equipped.getName() + "\n" +
							((Ranged) equipped).getClipAmmo() + " / " + ((Ranged) equipped).getCurrentAmmo());
				} else {
					Main.equippedInfo.setText(equipped.getName());
				}
				
			}
		});
		
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                switch (event.getCode()) {
                    case W:    playerState[0] = false; break;
                    case S:  playerState[1] = false; break;
                    case A:  playerState[2]  = false; break;
                    case D: playerState[3]  = false; break;
                    case SHIFT: playerState[4] = false; break;
				default:
					break;
                }
            }
        });
	}
	
	public void initMouseActions(Scene scene) {
		scene.setOnMouseClicked(e->{
			ObservableList<Transform> transform = this.getEntityGroup().getTransforms();
			Rotate rotate = (Rotate)transform.get(0);
			Translate translate = (Translate)transform.get(1);
						
			this.equipped.activate(rotate.getAngle(), translate.getX() + 10, translate.getY() + 10);
		});
				
		scene.setOnMouseMoved(e->{
			double x = (400 - e.getSceneX());
			double y = (400 - e.getSceneY());
			double angleRad = Math.atan(y/x);
			
			if (x >= 0  && y <=0) {
				this.angle = 180 + Math.toDegrees(angleRad);
			} else if (x >= 0  && y >= 0) {
				this.angle = Math.toDegrees(angleRad) - 180;
			} else {
				this.angle = Math.toDegrees(angleRad);
			}	
		});
	}
	
	@Override
	public void hit(int damage) {
		// TODO Auto-generated method stub
		
	}

	public ParallelCamera getPlayerCamera() {
		return playerCamera;
	}

	
	public void setPlayerCamera(ParallelCamera playerCamera) {
		this.playerCamera = playerCamera;
	}

	
	public boolean[] getPlayerState() {
		return playerState;
	}

	
	public void setPlayerState(boolean[] playerState) {
		this.playerState = playerState;
	}

	public Weapon[] getWeapons() {
		return weapons;
	}

	public void setWeapons(Weapon[] weapons) {
		this.weapons = weapons;
	}

	public Weapon getEquipped() {
		return equipped;
	}

	public void setEquipped(Weapon equipped) {
		this.equipped = equipped;
	}
	
	public double getX() {
		return ((Circle)this.getImage()).getCenterX();
	}
	
	public double getY() {
		return ((Circle)this.getImage()).getCenterY();
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}
	
}
