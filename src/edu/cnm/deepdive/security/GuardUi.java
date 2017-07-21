/**
 * 
 */
package edu.cnm.deepdive.security;

import java.util.ResourceBundle;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * @author davem
 *
 */
public class GuardUi extends Application {

	private static final String STYLE_SHEET = "/styles/application.css";
	private static final String LAYOUTS_RESOURCE = "/layouts/Generate.fxml";
	private static final String UI_BUNDLE = "resources/ui";
	private static final String WINDOW_TITLE_KEY = "window.title";
	
	@FXML
	private VBox passwordOptions;
	@FXML
	private VBox passphraseOptions;
	@FXML
	private CheckBox upperIncluded;
	@FXML
	private CheckBox lowerIncluded;
	@FXML
	private CheckBox punctuationIncluded;
	@FXML
	private CheckBox ambiguousExcluded;
	
	private SecurePasswordGenerator passwordGenerator = new SecurePasswordGenerator();

	/*
	 * (non-Javadoc)
	 * 
	 * @see javafx.application.Application#start(javafx.stage.Stage)
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		ResourceBundle uiBundle = ResourceBundle.getBundle(UI_BUNDLE);
		Pane pane = FXMLLoader.load(getClass().getResource(LAYOUTS_RESOURCE), uiBundle);
		Scene scene = new Scene(pane, 400, 400);
		scene.getStylesheets().add(getClass().getResource(STYLE_SHEET).toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setTitle(uiBundle.getString(WINDOW_TITLE_KEY));
		//upperIncluded.setSelected(passwordGenerator.isUpperCaseIncluded());
		//lowerIncluded.setSelected(passwordGenerator.isLowerCaseIncluded());
		//punctuationIncluded.setSelected(passwordGenerator.isPunctuationIncluded());
		//ambiguousExcluded.setSelected(passwordGenerator.isAmbiguousIncluded());
		primaryStage.show();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);

	}

}
