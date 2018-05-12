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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;

public class DamageCalculatorController{

	@FXML private ComboBox<String> atkPokeName;
	@FXML private ComboBox<String> defPokeName;
	@FXML private ComboBox<String> moveName;

	@FXML private Label damage;

	@FXML private Button calc;

	@FXML private ToggleGroup atkNature;
	@FXML private ToggleGroup defNature;
	@FXML private RadioButton atkU;
	@FXML private RadioButton atkN;
	@FXML private RadioButton atkD;
	@FXML private RadioButton defU;
	@FXML private RadioButton defN;
	@FXML private RadioButton defD;

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
	private DamageCalculator dm = new DamageCalculator();
	private Move move;
	private Pokemon atkPoke;
	private Pokemon defPoke;


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

		atkU.setUserData(1);
		atkN.setUserData(0);
		atkD.setUserData(-1);

		defU.setUserData(1);
		defN.setUserData(0);
		defD.setUserData(-1);

		initPokeComboBox(atkPokeName,atkNames);
		initPokeComboBox(defPokeName,defNames);
		initMoveComboBox(moveName,mvNames);

		initTextField(atkPokeName,atkEV);
		initTextField(atkPokeName,atkIV);

		initTextField(defPokeName,defEV);
		initTextField(defPokeName,defIV);
		initTextField(defPokeName,defHEV);
		initTextField(defPokeName,defHIV);

		initRadioButton(atkPokeName, atkU);
		initRadioButton(atkPokeName, atkN);
		initRadioButton(atkPokeName, atkD);

		initRadioButton(defPokeName, defU);
		initRadioButton(defPokeName, defN);
		initRadioButton(defPokeName, defD);


		calc.setOnMouseClicked((e)->{
			damage.setText("ダメージ:"+dm.getDamage(move, new DCPoke(atkPoke,Integer.valueOf(atkLV.getText()),Integer.valueOf(atkStat.getText())), new DCPoke(defPoke,Integer.valueOf(defStat.getText()))));
		});
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
				move = data.getMoveByName(cBox.getValue());
				setPower(move);
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
		cBox.getSelectionModel().select(0);
		setStat(cBox);

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
				setStat(cBox);
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
					setStat(cBox);
				}
		    }
		});
	}

	private void initRadioButton(ComboBox<String> cBox, RadioButton btn) {
		btn.setOnAction((e)->{
			setStat(cBox);
		});
	}

	private void setAtkStat(Pokemon poke) {
		int lv = Integer.parseInt(atkLV.getText());
		int ev = Integer.parseInt(atkEV.getText());
		int iv = Integer.parseInt(atkIV.getText());


		atkStat.setText(String.valueOf(poke.getAStat(lv, ev, iv, (int) atkNature.getSelectedToggle().getUserData())));
	}

	private void setDefStat(Pokemon poke) {
		int lv = Integer.parseInt(defLV.getText());
		int ev = Integer.parseInt(defEV.getText());
		int iv = Integer.parseInt(defIV.getText());
		int hev = Integer.parseInt(defHEV.getText());
		int hiv = Integer.parseInt(defHIV.getText());

		defStat.setText(String.valueOf(poke.getBStat(lv, ev, iv, (int) defNature.getSelectedToggle().getUserData())));
		hpStat.setText(String.valueOf(poke.getHStat(lv, hev, hiv)));

	}

	private void setPower(Move move) {
		power.setText(String.valueOf(move.getPower()));
	}

	private void setStat(ComboBox<String> cBox) {
		if(cBox.hashCode()==atkPokeName.hashCode()) {
			atkPoke = data.getPokeByName(cBox.getValue());;
			setAtkStat(atkPoke);
		}else {
			defPoke = data.getPokeByName(cBox.getValue());;
			setDefStat(defPoke);
		}
	}


}
