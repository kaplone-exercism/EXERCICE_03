package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import enums.Orientation;
import enums.Sens;
//import application.Contact;
//import application.Controlleur;
//import application.JfxUtils;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import models.Mur;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.shape.Circle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;

public class Main_Exercice_03 extends Application implements Initializable{
		
	int bonus = 0;
	Rectangle  r0;
	Rectangle  m1;
	Rectangle  m2;
	Rectangle  m3;
	Rectangle  m4;
	Rectangle  m5;
	Rectangle  p1;

	@FXML
	ImageView maze;
	@FXML
	ImageView settings;
	@FXML
	ImageView launch;
	@FXML
	ImageView exit;
	
	@FXML
	AnchorPane root;

	Scene scene;
	
	Stage stagePrincipal;
	
	Thread t_launch;
	
	@Override
	public void start(Stage primaryStage) {
		
		//primaryStage.initStyle(StageStyle.UNDECORATED);
		
		try {
			
			scene = new Scene((Parent) JfxUtils.loadFxml("menu2.fxml"), 600, 400);

			primaryStage.setScene(scene);
			primaryStage.setWidth(600);
			primaryStage.setHeight(435);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private Rectangle gerer_keys(Rectangle r, KeyEvent e){
		
		
		KeyCode kc = e.getCode();
		
		//System.out.println(kc.getName());
		
		switch (kc) {
		case UP:   r.setY(r.getY() - Contact.rienNeBloque(r0, Sens.HAUT, root));
			break;
		case DOWN: r.setY(r.getY() + Contact.rienNeBloque(r0, Sens.BAS, root));
		    break;
		case LEFT: r.setX(r.getX() - Contact.rienNeBloque(r0, Sens.GAUCHE, root));
		    break;
		case RIGHT: r.setX(r.getX() + Contact.rienNeBloque(r0, Sens.DROITE, root));
		    break;
		case Z: r.setY(r.getY() - Contact.rienNeBloque(r0, Sens.HAUT, root));
		        r.setFill(Color.PINK);
		break;
		case S: r.setY(r.getY() + Contact.rienNeBloque(r0, Sens.BAS, root));
		        r.setFill(Color.BLUE);
		break;
		case Q: r.setX(r.getX() - Contact.rienNeBloque(r0, Sens.GAUCHE, root));
		        r.setFill(Color.ORANGE);
		break;
		case D: r.setX(r.getX() + Contact.rienNeBloque(r0, Sens.DROITE, root));
		        r.setFill(Color.GREENYELLOW);
		break;
		case NUMPAD9 : r.setFill(Color.BLACK);
        break;
		case NUMPAD8 : r.setFill(Color.CHOCOLATE);
        break;
		case NUMPAD7 : r.setFill(Color.RED);
        break;
		case NUMPAD6 : r.setFill(Color.BLUE);
        break;
		case NUMPAD5 : r.setFill(Color.MAROON);
        break;
		case NUMPAD4 : r.setFill(Color.GREEN);
        break;
		case NUMPAD3 : r.setFill(Color.CORNFLOWERBLUE);
        break;
		case NUMPAD2 : r.setFill(Color.BLUEVIOLET);
        break;
		case NUMPAD1 : r.setFill(Color.YELLOW);
        break;
		case ADD: bonus += 5;
		break;
		case SUBTRACT: bonus -= 5;
		break;
		case ESCAPE: start(stagePrincipal);
		break;
		}
		
		return r;		
	}
	
    private Rectangle gerer_clicks(Rectangle r, MouseEvent e){
		
		r.setX(e.getSceneX());
		r.setY(e.getSceneY());
		
		return r;
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void nouvelleFenetre(){

		Controlleur ct = new Controlleur();
		ct.init();
		r0 = ct.getR0();
		
		m1 = new Mur(Orientation.VERTICAL, 15, 150, 0, 400, null);		
		m2 = new Mur(Orientation.VERTICAL, 15, 350, 50, 600, null);		
		m3 = new Mur(Orientation.VERTICAL, 15, 700, 200, 500, null);
		
		m4 = new Mur(Orientation.HORIZONTAL, 15, 200, 100, 500, null);		
		m5 = new Mur(Orientation.HORIZONTAL, 15, 500, 300, 800, null);
				
		root.getChildren().clear();
		root.getChildren().addAll( m1, r0, m2, m3, m4, m5);
		
		scene = root.getScene();
		stagePrincipal = (Stage) scene.getWindow();
		
		stagePrincipal.setWidth(1000);
		stagePrincipal.setHeight(600);
		
		root.setOnMouseClicked(e -> gerer_clicks(r0, e));
		scene.setOnKeyPressed(e1 -> gerer_keys(r0, e1));
		
	}
	
    public void nouvelleFenetreSettings(){
		
		ControlleurSettings cts = new ControlleurSettings();
		root = cts.init(root);
		
//		root.setOnMouseClicked(e -> gerer_clicks(r0, e));
//		scene.setOnKeyPressed(e1 -> gerer_keys(r0, e1));
		
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		
//		cercle.setOnMouseEntered(a -> cercle.setFill(Color.GREEN));
//		cercle.setOnMouseExited(a -> cercle.setFill(Color.YELLOW));
		
//		String [] command = new String [] {
//				"firefox",
//				"ina.fr"			
//		};
		
		maze.setOnMouseEntered(a -> maze.setImage(new Image("maze_v2.png")));
		maze.setOnMouseExited(a -> maze.setImage(new Image("maze_v2_2_nb.png")));
		
		launch.setOnMouseEntered(a -> {
			t_launch = new Thread(r_launch);
			t_launch.start();
		});
		launch.setOnMouseExited(a -> {
			t_launch.stop();
			launch.setImage(new Image("launch_nb.png"));
		});
		
		exit.setOnMouseEntered(a -> exit.setImage(new Image("exit_0.png")));
		exit.setOnMouseExited(a -> exit.setImage(new Image("exit.png")));
		
		settings.setOnMouseEntered(a -> settings.setImage(new Image("settings2.png")));
		settings.setOnMouseExited(a -> settings.setImage(new Image("settings2_nb.png")));
		
		settings.setOnMouseClicked(a -> nouvelleFenetreSettings());
		exit.setOnMouseClicked(a -> System.exit(0));
		launch.setOnMouseClicked(a -> nouvelleFenetre());	
		
//		maze.setOnMouseClicked(a -> new Thread(new Runnable() {
//		
//			@Override
//			public void run() {
//				
//				System.out.println("run");
//				Process p;
//				try {
//					p = new ProcessBuilder(command).start();
//				}
//                catch (IOException e){
//                	e.printStackTrace();		
//				}			
//			}
//		}).start());
	}
	
	Runnable r_launch = new Runnable() {		
		@Override
		public void run() {
			
			while (true){
				try {
					launch.setImage(new Image("launch_2.png"));	
					Thread.sleep(100);
					launch.setImage(new Image("launch.png"));	
					Thread.sleep(50);
					
				} catch (InterruptedException e) {
					launch.setImage(new Image("launch_nb.png"));
					e.printStackTrace();
				}
			}
			
		}
	};
	
}
