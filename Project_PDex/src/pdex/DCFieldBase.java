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
	Pokemon p;
	private DCPoke dcpoke;
	private ObservableList<String> names;

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
		p = data.getPokeByName(pokeCB.getValue());
		setStat(p);
		dcpoke = new DCPoke(p,Integer.valueOf(lv.getText()),Integer.valueOf(stat.getText()));

		pokeCB.getEditor().setOnMouseClicked((e)->{
			pokeCB.getSelectionModel().clearSelection();
			names.clear();
			for(Pokemon poke: data.pokeData) {
				names.add(poke.getName());
			}
			pokeCB.show();
		});

		pokeCB.setOnAction((e)->{
			System.out.println(pokeCB.getValue());
			if(pokeCB.getValue()==null||pokeCB.getValue().isEmpty()) {
				int l = names.size();
				for(Pokemon poke: data.pokeData){
					names.add(poke.getName());
				}
				names.remove(0, l);
				pokeCB.hide();
				pokeCB.show();
			}else if(data.isPokeNameOnList(pokeCB.getValue())) {
				p = data.getPokeByName(pokeCB.getValue());
	    		setStat(p);
	    		dcpoke = new DCPoke(p,Integer.valueOf(lv.getText()),Integer.valueOf(stat.getText()));
			}else {

				String s = pokeCB.getValue();
				pokeCB.getItems().clear();
				names.addAll(data.searchPokeByName(s));
				pokeCB.getEditor().setText(s);
				System.out.println("re-searched");
				pokeCB.hide();
				pokeCB.show();

			}
		});

	}

	void initTextField(TextField text) {
		text.textProperty().addListener(new ChangeListener<String>() {
		    @Override
		    public void changed(ObservableValue<? extends String> observable,String oldValue, String newValue) {
		    	if(!"".equals(text.getText())&&data.isPokeNameOnList(pokeCB.getValue())){
		    		p = data.getPokeByName(pokeCB.getValue());
		    		setStat(p);
		    		dcpoke = new DCPoke(p,Integer.valueOf(lv.getText()),Integer.valueOf(stat.getText()));
				}
		    }
		});
	}
	void initRadioButton() {
		up.setOnAction((e)->{
			p = data.getPokeByName(pokeCB.getValue());
    		setStat(p);
    		dcpoke = new DCPoke(p,Integer.valueOf(lv.getText()),Integer.valueOf(stat.getText()));
		});
		neutral.setOnAction((e)->{
			p = data.getPokeByName(pokeCB.getValue());
    		setStat(p);
    		dcpoke = new DCPoke(p,Integer.valueOf(lv.getText()),Integer.valueOf(stat.getText()));
		});
		down.setOnAction((e)->{
			p = data.getPokeByName(pokeCB.getValue());
    		setStat(p);
    		dcpoke = new DCPoke(p,Integer.valueOf(lv.getText()),Integer.valueOf(stat.getText()));
		});
	}

	void setStat(Pokemon poke) {
	}
	public DCPoke getDcpoke() {
		return dcpoke;
	}
}
