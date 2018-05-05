package pdex;
import javafx.stage.Stage;

public class TransitionObserver implements Observer {

	public Stage presentStage;
	@Override
	public void update(Controller controller) {
		// TODO 自動生成されたメソッド・スタブ
		presentStage.setScene(controller.scene);
	}

}
