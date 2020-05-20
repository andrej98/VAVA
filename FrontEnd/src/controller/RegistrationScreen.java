package controller;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONObject;

import javafx.event.ActionEvent;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;

/**
 * @author Andrej
 * Controller obrazovky registracie noveho pouzivatela
 */
public class RegistrationScreen {
	@FXML
    private RadioButton guestRB;
    @FXML
    private ToggleGroup tg;
    @FXML
    private RadioButton managerRB;
    @FXML
    private TextField fnameTB;
    @FXML
    private TextField lnameTB;
    @FXML
    private TextField addressTB;
    @FXML
    private TextField emailTB;
    @FXML
    private PasswordField passwordTB;
    @FXML
    private Button registerB;
    @FXML
    private Button backB;
    @FXML
    private DatePicker bdateTB;
    @FXML
    private ChoiceBox<String> genderTB;
    
	private final static Logger LOG = Logger.getLogger(RegistrationScreen.class.getName());

    
    //pridanie noveho pouzivatela medzi customerov alebo managerov, podla volby
    @FXML
    void registerClick(ActionEvent event) {
    	
        String q1 = new String();
		if (bdateTB.getValue() == null || fnameTB.getText().isEmpty() || lnameTB.getText().isEmpty() || addressTB.getText().isEmpty() || emailTB.getText().isEmpty() || passwordTB.getText().isEmpty())
        {
        	Alert a = new Alert(AlertType.ERROR);
    		a.setTitle(Main.bundle.getString("error"));
    		a.setContentText(Main.bundle.getString("errorFill"));
    		a.showAndWait();
        }
        else {
        	JSONObject json = new JSONObject();
        	json.put("first_name", fnameTB.getText());
        	json.put("last_name", lnameTB.getText());
        	json.put("gender", genderTB.getValue().toString());
        	json.put("birth_date",bdateTB.valueProperty().get().toString());
        	json.put("password", passwordTB.getText());
        	json.put("email", emailTB.getText());
        	json.put("address", addressTB.getText());
        	System.out.println(json.toJSONString());

        	
        	try {
        		if(guestRB.isSelected()) {
        			URL url = new URL("http://localhost:8080/customer/save");
    				HttpURLConnection conn = null;
    				conn = (HttpURLConnection) url.openConnection();
    				conn.setUseCaches(false);
    				conn.setDoInput(true);
    				conn.setDoOutput(true);
    				conn.setRequestMethod("POST");
    				conn.setRequestProperty("Content-Type", "application/json");
    				OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
    				out.write(json.toString());
    				out.close();
    				
    				conn.getInputStream();
    				
    				
                }
                else {
                	URL url = new URL("http://localhost:8080/manager/save");
    				HttpURLConnection conn = null;
    				conn = (HttpURLConnection) url.openConnection();
    				conn.setUseCaches(false);
    				conn.setDoInput(true);
    				conn.setDoOutput(true);
    				conn.setRequestMethod("POST");
    				conn.setRequestProperty("Content-Type", "application/json");
    				OutputStreamWriter out = new OutputStreamWriter(conn.getOutputStream());
    				out.write(json.toString());
    				out.close();
    				conn.getInputStream();

    				
                }
            	
            	
    			Alert a = new Alert(AlertType.INFORMATION);
        		a.setTitle(Main.bundle.getString("confirm"));
        		a.setContentText(Main.bundle.getString("confirmReg"));
        		a.showAndWait();
        		
    			this.backClick(event);
    			
            	
        	} catch(IOException e) {
        		LOG.log(Level.SEVERE,"Registracia neuspesna",e);
        		Alert a = new Alert(AlertType.ERROR);
        		a.setTitle(Main.bundle.getString("error"));
        		a.setContentText(Main.bundle.getString("errorFill"));
        		a.showAndWait();
        	}
        	
        }     
    }

    //navrat na domovsku obrazovku
    @FXML
    void backClick(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(RegistrationScreen.class.getResource("/gui/HomeScreen.fxml"),Main.bundle);
		Scene registerScene = new Scene(root);
		Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
		window.setScene(registerScene);
		window.show();
    }
    
    //inicializacia obrazovky
    @FXML
    public void initialize()
    {
    	this.guestRB.setToggleGroup(tg);
    	this.managerRB.setToggleGroup(tg);
    	this.genderTB.getItems().addAll("M","F");
    	this.genderTB.getSelectionModel().selectFirst();
    }
    

}
