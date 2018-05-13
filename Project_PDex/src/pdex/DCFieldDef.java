package pdex;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class DCFieldDef extends DCFieldBase {
	@FXML private TextField hpStat;
	@FXML private TextField hEV;
	@FXML private TextField hIV;

	public void initialize() {
		init();
	}
	void setStat(Pokemon poke) {
		int l = Integer.parseInt(lv.getText());
		int e = Integer.parseInt(ev.getText());
		int i = Integer.parseInt(iv.getText());
		int he = Integer.parseInt(hEV.getText());
		int hi = Integer.parseInt(hIV.getText());

		stat.setText(String.valueOf(poke.getBStat(l, e, i, (int) nature.getSelectedToggle().getUserData())));
		hpStat.setText(String.valueOf(poke.getHStat(l, he, hi)));

	}

}
