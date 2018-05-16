package pdex;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class DCFieldBase {
	@FXML protected ComboBox<String> pokeCB;

	@FXML protected ToggleGroup nature;

	@FXML protected RadioButton up;
	@FXML protected RadioButton neutral;
	@FXML protected RadioButton down;

	@FXML protected TextField lv;
	@FXML protected TextField ev;
	@FXML protected TextField iv;
	@FXML protected TextField stat;

	protected Data data = new Data();
	private Pokemon p;
	private DCPoke dcpoke;
	private ObservableList<String> names;

	boolean isPhys = false;

	void init() {
		up.setUserData(1);
		neutral.setUserData(0);
		down.setUserData(-1);

		initPokeComboBox();
		initRadioButton();

		initTextField(lv);
		initTextField(ev);
		initTextField(iv);
		initTextField(stat);

	}
	void initPokeComboBox() {
		names = FXCollections.observableArrayList();
		pokeCB.itemsProperty().setValue(names);
		pokeCB.setItems(names);
		for(Pokemon poke: data.pokeData) {
			names.add(poke.getName());
		}
		pokeCB.getSelectionModel().select(0);
		setDCPoke();

		pokeCB.getEditor().setOnMouseClicked((e)->{
			pokeCB.getSelectionModel().clearSelection();
			names.clear();
			for(Pokemon poke: data.pokeData) {
				names.add(poke.getName());
			}
			pokeCB.show();
		});

		pokeCB.getEditor().textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable,String oldValue, String newValue) {
				if(newValue==null||newValue.isEmpty()) {
					names.clear();
					for(Pokemon poke: data.pokeData){
						names.add(poke.getName());
					}
					pokeCB.show();
				}else if(data.isPokeNameOnList(pokeCB.getValue())) {
					setDCPoke();
				}else {
					names.clear();
					names.addAll(data.searchPokeByName(newValue));
					pokeCB.show();

				}
			}
		});
	}

	void initTextField(TextField text) {
		text.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable,String oldValue, String newValue) {
		    	if(!"".equals(text.getText())&&data.isPokeNameOnList(pokeCB.getValue())){
		    		setDCPoke();
				}
		    }
		});
	}
	void initRadioButton() {
		up.setOnAction((e)->{
			setDCPoke();
		});
		neutral.setOnAction((e)->{
			setDCPoke();
		});
		down.setOnAction((e)->{
			setDCPoke();
		});
	}

	void setStat(Pokemon poke) {
	}
	public DCPoke getDcpoke() {
		return dcpoke;
	}
	public void setPhys(boolean isPhys) {
		this.isPhys = isPhys;
	}
	public void setDCPoke() {
		p = data.getPokeByName(pokeCB.getValue());
		setStat(p);
		dcpoke = new DCPoke(p,Integer.valueOf(lv.getText()),Integer.valueOf(stat.getText()));
	}
}
