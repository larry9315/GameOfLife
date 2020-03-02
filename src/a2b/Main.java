/**
Main class that launches javaFX. Also implemented serialization to save and load from files.
 * 
 */
package a2b;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * @author larry
 *
 */
public class Main extends Application {

	Button saveButton;
	Button loadButton;
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	
	public void update(Game game, Group group) {
		for (int i = 0; i < 50; i++) {
			for (int j = 0; j < 50; j++) {
				
				group.getChildren().add(game.getWorld().getBoard()[i][j].draw(i, j));
				
			}
		}
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		primaryStage.setTitle("Game of Life");
		Group group = new Group();
		
		Game game = new Game();
		
		saveButton = new Button("Save");
		loadButton = new Button("Load");
		
		
		saveButton.setLayoutX(0);
		saveButton.setLayoutY(500);
		
		loadButton.setLayoutX(458);
		loadButton.setLayoutY(500);
		
		saveButton.setOnAction((event) -> {
            try {
                FileChooser fileChooser = new FileChooser();
                
                FileChooser.ExtensionFilter extentionFilter = new FileChooser.ExtensionFilter("SER files (*.ser)", "*.ser");
                fileChooser.getExtensionFilters().add(extentionFilter);
                
                FileOutputStream fileOutputStream = new FileOutputStream(fileChooser.showSaveDialog(primaryStage));
                
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
                
                objectOutputStream.writeObject(game.getWorld());
                objectOutputStream.close();
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
		
		loadButton.setOnAction((event) -> {
            try {
                FileChooser fileChooser = new FileChooser();
                FileChooser.ExtensionFilter extentionFilter = new FileChooser.ExtensionFilter("SER files (*.ser)", "*.ser");
                fileChooser.getExtensionFilters().add(extentionFilter);
                FileInputStream fileInputStream = new FileInputStream(fileChooser.showOpenDialog(primaryStage));
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
                
                World savedWorld = (World) objectInputStream.readObject();
                
                
                game.setWorld(savedWorld);
                update(game, group);
                
                
                objectInputStream.close();
                fileInputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
		
		
		update(game, group);
		group.getChildren().add(saveButton);
		group.getChildren().add(loadButton);
		
		Scene scene = new Scene(group, 500, 525);
		scene.setOnMousePressed(new EventHandler<MouseEvent>() {
	        @Override
	        public void handle(MouseEvent event) {

	            game.playGame();
	            group.getChildren().clear();
	            
	            update(game, group);
	            group.getChildren().add(saveButton);
	    		group.getChildren().add(loadButton);

	            
	        }
	    });
		
	
		primaryStage.setScene(scene);
		primaryStage.show();
	}

}
