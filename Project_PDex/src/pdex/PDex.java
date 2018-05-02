package pdex;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
public class PDex extends Application{

	public static void main(String[] args) {
		Data test = new Data();
		launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception {
		// TODO 自動生成されたメソッド・スタブ
		Parent root;
		try{
			root = FXMLLoader.load(getClass().getResource("Title.fxml"));
			Scene scene = new Scene(root,400,600);
			stage.setScene(scene);
			stage.show();

		}catch(IOException e){
			e.printStackTrace();
		}
	}


}
