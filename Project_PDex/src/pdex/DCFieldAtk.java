package pdex;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class DCFieldAtk extends DCFieldBase {

	@FXML private ComboBox<String> moveCB;
	@FXML private TextField power;

	private Move move;

	private ObservableList<String> mvNames;

	public void initialize() {
		init();
		initMoveComboBox();
	}
	void initMoveComboBox() {
		mvNames = FXCollections.observableArrayList();
		moveCB.itemsProperty().setValue(mvNames);
		moveCB.setItems(mvNames);
		for(Move mv: data.moveData) {
			mvNames.add(mv.getName());
		}

		moveCB.getEditor().setOnMouseClicked((e)->{
			moveCB.getSelectionModel().clearSelection();
			mvNames.clear();
			for(Move mv: data.moveData) {
				mvNames.add(mv.getName());
			}
			moveCB.show();
		});

		moveCB.getEditor().textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> observable,String oldValue, String newValue) {
				if(newValue==null||newValue.isEmpty()) {
					mvNames.clear();
					for(Move mv: data.moveData){
						mvNames.add(mv.getName());
					}
					moveCB.show();
				}else if(data.isMoveNameOnList(moveCB.getValue())) {
					move = data.getMoveByName(moveCB.getValue());
					setPower(move);
				}else {
					mvNames.clear();
					mvNames.addAll(data.searchMoveByName(newValue));
					moveCB.show();

				}
			}
		});
	}
	void setStat(Pokemon poke) {
		int l = Integer.parseInt(lv.getText());
		int e = Integer.parseInt(ev.getText());
		int i = Integer.parseInt(iv.getText());
		int n = (int) nature.getSelectedToggle().getUserData();

		stat.setText(String.valueOf(poke.getAStat(l, e, i, n)));
	}

	void setPower(Move move) {
		power.setText(String.valueOf(move.getPower()));
	}

	public Move getMove() {
		return move;
	}
}
