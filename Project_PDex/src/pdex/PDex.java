package pdex;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
public class PDex extends Application{
	public static Stage presentStage;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		presentStage = stage;

		stage.setTitle("PDex");
		Scene scene = new Scene(new Pane());
		stage.setScene(scene);

		TitleController.getInstance().show();

		stage.show();
	}


}
