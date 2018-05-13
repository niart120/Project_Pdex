package pdex;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

public class DamageCalculatorController{


	@FXML private Label damage;
	@FXML private Button calc;
	@FXML private AnchorPane atk;
	@FXML private AnchorPane def;
	@FXML private DCFieldAtk atkController;
	@FXML private DCFieldDef defController;

	private DamageCalculator dm;

	private static final DamageCalculatorController instance;
	private static final Scene SCENE;

	static{
		FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource("./pdex/DamageCalculator.fxml"));
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

	public static DamageCalculatorController getInstance(){
		return instance;
	}

	public void show(){
		PDex.presentStage.setScene(SCENE);
	}

	public void initialize() {

		calc.setOnMouseClicked((e)->{
			System.out.println(atkController.getMove().getName());
			damage.setText("ダメージ:"+dm.getDamage(atkController.getMove(), atkController.getDcpoke(),defController.getDcpoke()));

		});

	}

	public void onBackButtonClicked(ActionEvent event) {
        TitleController.getInstance().show();
    }

}
