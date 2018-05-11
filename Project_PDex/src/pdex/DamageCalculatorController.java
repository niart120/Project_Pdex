package pdex;

import java.io.IOException;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

public class DamageCalculatorController{

	@FXML private ComboBox<String> atkPokeName;
	@FXML private ComboBox<String> defPokeName;
	@FXML private ComboBox<String> moveName;

	@FXML private TextField atkLV;
	@FXML private TextField defLV;
	@FXML private TextField atkEV;
	@FXML private TextField defEV;
	@FXML private TextField atkIV;
	@FXML private TextField defIV;
	@FXML private TextField defHEV;
	@FXML private TextField defHIV;
	@FXML private TextField atkStat;
	@FXML private TextField defStat;
	@FXML private TextField hpStat;
	@FXML private TextField power;

	private ObservableList<String> atkNames;
	private ObservableList<String> defNames;
	private ObservableList<String> mvNames;


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
		mvNames = FXCollections.observableArrayList();

		initPokeComboBox(atkPokeName,atkNames);
		initPokeComboBox(defPokeName,defNames);
		initMoveComboBox(moveName,mvNames);

		initTextField(atkPokeName,atkEV);
		initTextField(atkPokeName,atkIV);

		initTextField(defPokeName,defEV);
		initTextField(defPokeName,defIV);
		initTextField(defPokeName,defHEV);
		initTextField(defPokeName,defHIV);
	}

	public void onBackButtonClicked(ActionEvent event) {
        TitleController.getInstance().show();
    }

	private void initMoveComboBox(ComboBox<String> cBox,ObservableList<String> oList) {
		cBox.itemsProperty().setValue(oList);
		cBox.setItems(oList);
		for(Move mv: data.moveData) {
			oList.add(mv.getName());
		}

		cBox.getEditor().setOnMouseClicked((e)->{
			cBox.getSelectionModel().clearSelection();
			oList.clear();
			for(Move mv: data.moveData) {
				oList.add(mv.getName());
			}
			cBox.show();
		});

		cBox.setOnAction((e)->{
			System.out.println(cBox.getValue());
			if(cBox.getValue()==null||cBox.getValue().isEmpty()) {
				int l = oList.size();
				for(Move mv: data.moveData){
					oList.add(mv.getName());
				}
				oList.remove(0, l);
				cBox.hide();
				cBox.show();
			}else if(data.isMoveNameOnList(cBox.getValue())) {
				setPower(data.getMoveByName(cBox.getValue()));
			}else {

				String s = cBox.getValue();
				cBox.getItems().clear();
				oList.addAll(data.searchMoveByName(s));
				cBox.getEditor().setText(s);
				cBox.hide();
				cBox.show();

			}
		});
	}
	private void initPokeComboBox(ComboBox<String> cBox,ObservableList<String> oList) {
		cBox.itemsProperty().setValue(oList);
		cBox.setItems(oList);
		for(Pokemon poke: data.pokeData) {
			oList.add(poke.getName());
		}


		cBox.getEditor().setOnMouseClicked((e)->{
			cBox.getSelectionModel().clearSelection();
			oList.clear();
			for(Pokemon poke: data.pokeData) {
				oList.add(poke.getName());
			}
			cBox.show();
		});

		cBox.setOnAction((e)->{
			System.out.println(cBox.getValue());
			if(cBox.getValue()==null||cBox.getValue().isEmpty()) {
				int l = oList.size();
				for(Pokemon poke: data.pokeData){
					oList.add(poke.getName());
				}
				oList.remove(0, l);
				cBox.hide();
				cBox.show();
			}else if(data.isPokeNameOnList(cBox.getValue())) {
				Pokemon poke = data.getPokeByName(cBox.getValue());
				if(cBox.hashCode()==atkPokeName.hashCode()) {
					setAtkStat(poke);
				}else {
					setDefStat(poke);
				}
			}else {

				String s = cBox.getValue();
				cBox.getItems().clear();
				oList.addAll(data.searchPokeByName(s));
				cBox.getEditor().setText(s);
				System.out.println("re-searched");
				cBox.hide();
				cBox.show();

			}
		});

	}

	private void initTextField(ComboBox<String> cBox,TextField text) {
		text.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable,String oldValue, String newValue) {
		    	if(!"".equals(text.getText())){
					if(data.isPokeNameOnList(cBox.getValue())) {
						Pokemon poke = data.getPokeByName(cBox.getValue());
						if(cBox.hashCode()==atkPokeName.hashCode()) {
							setAtkStat(poke);
						}else {
							setDefStat(poke);
						}
					}
				}
		    }
		});
	}

	private void setAtkStat(Pokemon poke) {
		int lv = Integer.parseInt(atkLV.getText());
		int ev = Integer.parseInt(atkEV.getText());
		int iv = Integer.parseInt(atkIV.getText());
		double nc = 1;

		atkStat.setText(String.valueOf(poke.getAStat(lv, ev, iv, nc)));
	}

	private void setDefStat(Pokemon poke) {
		int lv = Integer.parseInt(defLV.getText());
		int ev = Integer.parseInt(defEV.getText());
		int iv = Integer.parseInt(defIV.getText());
		int hev = Integer.parseInt(defHEV.getText());
		int hiv = Integer.parseInt(defHIV.getText());
		double nc = 1;

		defStat.setText(String.valueOf(poke.getBStat(lv, ev, iv, nc)));
		hpStat.setText(String.valueOf(poke.getHStat(lv, hev, hiv)));

	}

	private void setPower(Move move) {
		power.setText(String.valueOf(move.getPower()));
	}


}
