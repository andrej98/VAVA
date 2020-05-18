package controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application{

	//premenne na to, ci je aplikacia prvykrat spustena, kvoli loadingScreen
	//ResourceBundle na jazykove nastavenia
	public static Boolean firstLaunch = true;
	public static ResourceBundle bundle = ResourceBundle.getBundle("resources/language",Locale.getDefault());
	public static Properties prop = new Properties();
 
	@Override
	public void start(Stage primaryStage) throws IOException {
		primaryStage.setTitle(bundle.getString("header"));		
		InputStream inputStream = new FileInputStream("src/resources/DbContract.properties");
		prop.load(inputStream);
		Parent root = FXMLLoader.load(getClass().getResource("/gui/HomeScreen.fxml"),bundle);
		
		primaryStage.setScene(new Scene(root));
		primaryStage.show();		
	}
	
	
	public static void main(String args[]) {
		Application.launch(args);
	}

}
