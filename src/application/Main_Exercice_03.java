package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//import application.Contact;
//import application.Controlleur;
//import application.JfxUtils;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
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
	Rectangle  p1;

//	@FXML
//	Circle cercle;
//	
//	@FXML
//	Label texte;
	
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
	
	@Override
	public void start(Stage primaryStage) {
		
		//primaryStage.initStyle(StageStyle.UNDECORATED);
		
		stagePrincipal = primaryStage;
		
		System.out.println(stagePrincipal);
		
		try {
			
			scene = new Scene((Parent) JfxUtils.loadFxml("menu2.fxml"), 600, 400);

			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private Rectangle gerer_keys(Rectangle r, KeyEvent e){
		
		
		KeyCode kc = e.getCode();
		
		System.out.println(kc.getName());
		
		switch (kc) {
		case UP: r.setY(r.getY() - 5);			
			break;
		case DOWN: r.setY(r.getY() + 5);			
		break;
		case LEFT: r.setX(r.getX() - 5);
		break;
		case RIGHT: r.setX(r.getX() + 6);
		break;
		case Z: r.setY(r.getY() - 5 - bonus);
		r.setFill(Color.PINK);
		break;
		case S: r.setY(r.getY() + 5 + bonus);
		r.setFill(Color.BLUE);
		break;
		case Q: r.setX(r.getX() - 5 - bonus);
		r.setFill(Color.ORANGE);
		break;
		case D: r.setX(r.getX() + 5 + bonus);
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
		}
		
		Contact.estEnContact(r0, m1);
//		String l = e.getCharacter();
//		System.out.println(l);
//		
//		switch(l){
//		case "8" : r.setY(r.getY() - 5);
//		           break;
//		case "2" : r.setY(r.getY() + 5);
//        break;
//		case "4" : r.setX(r.getX() - 5);
//        break;
//		case "6" : r.setX(r.getX() + 5);
//        break;
//		case "5" : double temp_X = r.getX();
//			       r.setX(r.getY());
//		           r.setY(temp_X);
//        break;
//		case "b" : r.setFill(Color.CORNFLOWERBLUE);
//        break;
//		case "o" : r.setFill(Color.ORANGE);
//        break;
//		case "v" : r.setFill(Color.MEDIUMPURPLE);
//        break;
//		}

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
		
		//root = new AnchorPane();
		
		Controlleur ct = new Controlleur();
		ct.init();
		r0 = ct.getR0();
		
		m1 = new Controlleur(2).getR0();
		m1.setLayoutX(200);
		
		m2 = new Controlleur(2).getR0();
		m2.setLayoutX(350);
		
		p1 = new Controlleur(Color.WHITE).getR0();
		p1.setLayoutX(200);
		p1.setLayoutY(100);
		
		root.getChildren().clear();
		root.getChildren().addAll( m1, r0, m2, p1);
		
		scene = root.getScene();
		Stage primary = (Stage) scene.getWindow();
		
		primary.setWidth(1000);
		primary.setHeight(600);
		
		root.setOnMouseClicked(e -> gerer_clicks(r0, e));
		scene.setOnKeyPressed(e1 -> gerer_keys(r0, e1));
		
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
		
		launch.setOnMouseEntered(a -> launch.setImage(new Image("launch_2.png")));
		launch.setOnMouseExited(a -> launch.setImage(new Image("launch_nb.png")));
		
		exit.setOnMouseEntered(a -> exit.setImage(new Image("exit_0.png")));
		exit.setOnMouseExited(a -> exit.setImage(new Image("exit.png")));
		
		settings.setOnMouseEntered(a -> settings.setImage(new Image("settings2.png")));
		settings.setOnMouseExited(a -> settings.setImage(new Image("settings2_nb.png")));
		
		
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
}
