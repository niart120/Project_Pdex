package pdex;

import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.paint.Color;

public class PokeDexController{
	@FXML private TableView<Pokemon> table;
	@FXML private TableColumn nameCol;
	@FXML private TableColumn type1Col;
	@FXML private TableColumn type2Col;

	private ObservableList<Pokemon> obsData;
	private Data data = new Data();

	private static final Scene SCENE;
	private static final PokeDexController instance;
	static{
		FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource("./pdex/PokeDex.fxml"));
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

	public static PokeDexController getInstance(){
		return instance;
	}

	public void show(){
		PDex.presentStage.setScene(SCENE);
	}

	public void initialize() {
		obsData = FXCollections.observableArrayList();
		table.setItems(obsData);

		nameCol.setCellValueFactory(new PropertyValueFactory<Pokemon, String>("name"));
		type1Col.setCellValueFactory(new PropertyValueFactory<Pokemon, String>("type1"));
		type2Col.setCellValueFactory(new PropertyValueFactory<Pokemon, String>("type2"));
	}

	@FXML public void onBackButtonClicked(ActionEvent event) {
        TitleController.getInstance().show();
    }

	@FXML public void onSearchButtonClicked(ActionEvent event) {
		for(Pokemon poke: data.pokeData) {
			obsData.addAll(poke);
		}
	}
}
