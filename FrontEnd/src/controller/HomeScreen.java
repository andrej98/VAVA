package controller;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.Customer;
import model.HotelManager;


/**
 * @author Andrej
 * Controller domovskej obrazovky s loginom
 */
public class HomeScreen implements Initializable{

    @FXML
    private TextField mailTF;
    @FXML
    private PasswordField hesloTF;
    @FXML
    private Button loginB;
    @FXML
    private Button registerB;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Label welcomeL;
    @FXML
    private RadioButton guestRB;
    @FXML
    private ToggleGroup tg;
    @FXML
    private RadioButton managerRB;
    @FXML
    private ComboBox<String> langCB;

    
	private final static Logger LOG = Logger.getLogger(HomeScreen.class.getName());

    //otvori okno registracie
    @FXML
    void registerClick(ActionEvent event) throws Exception {
		Parent root = FXMLLoader.load(HomeScreen.class.getResource("/gui/RegistrationScreen.fxml"),Main.bundle);
		Scene registerScene = new Scene(root);
		
		Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
		window.setScene(registerScene);
		window.show();
	}
    
    //overi ci user existuje v DB a prihlasi ho ako spravcu alebo ako managera, podla volby
    @FXML
    void loginClick(ActionEvent event) throws ParseException, java.text.ParseException, IOException {
          	
    	if(mailTF.getText().isEmpty() || hesloTF.getText().isEmpty()) {
    		Alert a = new Alert(AlertType.ERROR);
    		a.setTitle(Main.bundle.getString("error"));
    		a.setContentText(Main.bundle.getString("loginError"));
    		a.showAndWait();
    	}
    	else {    		
			try {  		
	    		if(managerRB.isSelected()) {
	    			URL url = new URL(Main.prop.getProperty("REMOTE")+"/login/manager/"+mailTF.getText()+"/"+hesloTF.getText());
					HttpURLConnection conn = null;
					conn = (HttpURLConnection) url.openConnection();
					conn.setUseCaches(false);
					conn.setDoInput(true);
					conn.setDoOutput(true);
					conn.setRequestMethod("GET");
					
					conn.getInputStream();
					BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    			
	    			JsonFactory fac = new JsonFactory();
	    			JsonParser jp = fac.createParser(in);
	    			ObjectMapper om = new ObjectMapper();
	    			HotelManager mana = new HotelManager();
	    			mana= om.readValue(jp, HotelManager.class);
	    			
	    			
	    			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ManagerHomeScreen.fxml"));
	    			loader.setResources(Main.bundle);

	    			Parent root=loader.load();
	    			ManagerHomeScreen m = loader.getController();
	    			m.init(mana);
	   
	    			Scene login = new Scene(root);  			
	    			Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
	    			window.setScene(login);
	    			window.show();
	    			
	        	}
	    		else {
	    			URL url = new URL(Main.prop.getProperty("REMOTE")+"/login/customer/"+mailTF.getText()+"/"+hesloTF.getText());
					HttpURLConnection conn = null;
					conn = (HttpURLConnection) url.openConnection();
					conn.setUseCaches(false);
					conn.setDoInput(true);
					conn.setDoOutput(true);
					conn.setRequestMethod("GET");
					
					conn.getInputStream();
					BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

					
					JsonFactory fac = new JsonFactory();
	    			JsonParser jp = fac.createParser(in);
	    			ObjectMapper om = new ObjectMapper();
	    			Customer cus = new Customer();
	    			cus= om.readValue(jp, Customer.class);
	    			
	    			FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/GuestHomeScreen.fxml"));
	    			loader.setResources(Main.bundle);

	    			Parent root=loader.load();
	    			GuestHomeScreen m = loader.getController();
	    			m.init(cus);
	   
	    			Scene login = new Scene(root);  			
	    			Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
	    			window.setScene(login);
	    			window.show();
	    		}
			} catch (IOException e) {
				Alert a = new Alert(AlertType.ERROR);
	    		a.setTitle(Main.bundle.getString("error"));
	    		a.setContentText(Main.bundle.getString("loginError"));
	    		a.showAndWait();
				LOG.log(Level.SEVERE, "Chyba pri prihlaseni", e);;
			}
			
    	}
    	
    	
    	
    }
    
    //obrazovka nacitania, pri prvom spusteni HomeScreen
    private void loadingScreen() throws IOException {
    	Main.firstLaunch = false;
    	Parent pane =FXMLLoader.load(getClass().getResource("/gui/LoadingScreen.fxml"),Main.bundle);
    	mainPane.getChildren().setAll(pane);
    	
    	FadeTransition fade = new FadeTransition(Duration.seconds(3),pane);
    	fade.setFromValue(1);
    	fade.setToValue(0);
    	fade.setCycleCount (1);
    	
       	fade.play();
       	
       	fade.setOnFinished((e)->{
       		try {
       			ResourceBundle resources = ResourceBundle.getBundle("resources.language",Locale.getDefault());       			
       			Parent main =FXMLLoader.load(getClass().getResource("/gui/HomeScreen.fxml"),resources);
	        	mainPane.getChildren().setAll(main);

			} catch (IOException e1) {
	    		LOG.log(Level.SEVERE, "Zla cesta k suboru", e);
			}
       	});
    }
    
    //zmena jazyka
    @FXML
    void changeLanguage(ActionEvent event) throws IOException {
    	if("SK".equals(langCB.getValue())) {
    		Main.bundle = ResourceBundle.getBundle("resources.language_sk");
    		
    		Parent root = FXMLLoader.load(ManagerHomeScreen.class.getResource("/gui/HomeScreen.fxml"),Main.bundle);
    		Scene registerScene = new Scene(root);
    		Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
    		window.setTitle(Main.bundle.getString("header"));
    		window.setScene(registerScene);
    		window.show();
    	}
    	if("EN".equals(langCB.getValue())) {
    		Main.bundle = ResourceBundle.getBundle("resources.language_en");
    		
    		Parent root = FXMLLoader.load(ManagerHomeScreen.class.getResource("/gui/HomeScreen.fxml"),Main.bundle);
    		Scene registerScene = new Scene(root);
    		Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
    		window.setTitle(Main.bundle.getString("header"));
    		window.setScene(registerScene);
    		window.show();
    	}
    }
    
    @Override
	public void initialize(URL arg0, ResourceBundle arg1) {
    	
    	this.guestRB.setToggleGroup(tg);
    	this.managerRB.setToggleGroup(tg);
    	this.langCB.getItems().addAll("EN","SK");
    	mailTF.setText("a@gmail.com");
    	hesloTF.setText("a");
        if(Main.firstLaunch) {
        	try {
				loadingScreen();
			} catch (IOException e) {
	    		LOG.log(Level.SEVERE, "Zla cesta k suboru", e);
			}
        }
        
    }
}
