package pdex;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class PokeDexController extends Controller {

	private static final Scene SCENE;
	private static final TitleController instance;
	static{
		FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource("PokeDex.fxml"));
		try{
			fxmlLoader.load();
		}catch(IOException e){
			e.printStackTrace();
		}
		Parent parent = fxmlLoader.getRoot();
		Scene s = new Scene(parent);
		s.setFill(Color.TRANSPARENT);
		SCENE = s;
		instance = fxmlLoader.getController();
	}

	public static Controller getInstance(){
		return instance;
	}

	public void show(){
		super.scene = SCENE;
		notifyObservers();
	}
}
