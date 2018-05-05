package pdex;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;

public class TitleController {

	private static final TitleController instance;
    private static final Scene SCENE;
    static {
        FXMLLoader fxmlLoader = new FXMLLoader(ClassLoader.getSystemResource("./pdex/Title.fxml"));
        try {
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent parent = fxmlLoader.getRoot();
        Scene s = new Scene(parent);
        s.setFill(Color.TRANSPARENT);
        SCENE = s;
        instance = fxmlLoader.getController();
    }

    /**
     * singletonのインスタンスを返す
     * @return instance
     */
    public static TitleController getInstance() {
        return instance;
    }

    /**
     * 表示する
     */
    public void show() {
        PDex.presentStage.setScene(SCENE);
    }
}
