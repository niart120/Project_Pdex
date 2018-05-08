package pdex;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.paint.Color;

public class DamageCalculatorController{

	@FXML private ComboBox<String> atkPokeName;
	@FXML private ComboBox<String> defPokeName;
	private ObservableList<String> atkNames;
	private ObservableList<String> defNames;

	private Data data = new Data();

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
		atkNames = FXCollections.observableArrayList();
		defNames = FXCollections.observableArrayList();

		initComboBox(atkPokeName,atkNames);
		initComboBox(defPokeName,defNames);
	}

	public void onBackButtonClicked(ActionEvent event) {
        TitleController.getInstance().show();
    }


	private void initComboBox(ComboBox<String> cBox,ObservableList<String> oList) {
		cBox.itemsProperty().setValue(oList);
		cBox.setItems(oList);
		for(Pokemon poke: data.pokeData) {
			oList.add(poke.getName());
		}

		cBox.getEditor().setOnMouseClicked((e)->{
			if(cBox.getValue()==null||cBox.getValue().isEmpty()) {
				cBox.getSelectionModel().clearSelection();
				oList.clear();
				for(Pokemon poke: data.pokeData) {
					oList.add(poke.getName());
				}
			}else if(!data.isNameOnList(cBox.getValue())) {
				oList.clear();
				for(Pokemon poke: data.searchByName(cBox.getValue())){
					oList.add(poke.getName());
				}
			}
			cBox.autosize();
			cBox.show();
		});

		cBox.setOnAction((e)->{
			if(cBox.getValue()!=null) {
				System.out.println(cBox.getSelectionModel().getSelectedItem());
				if(cBox.getSelectionModel().isEmpty()) {
					oList.clear();
					for(Pokemon poke: data.searchByName(cBox.getValue())){
						oList.add(poke.getName());
					}
				}else if(!data.isNameOnList(cBox.getValue())) {
					String s = cBox.getValue();
					oList.clear();
					for(Pokemon poke: data.searchByName(s)){
						oList.add(poke.getName());
					}
				}
			}else {
				oList.clear();
				for(Pokemon poke: data.pokeData) {
					oList.add(poke.getName());
				}
			}
			cBox.autosize();
			cBox.show();
		});


	}


}
