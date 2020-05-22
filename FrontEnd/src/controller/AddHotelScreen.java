package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.HotelManager;
/**
 * @author Andrej
 * Controller obrazovky pre hotel managera na pridanie noveho hotela
 */
public class AddHotelScreen {

    @FXML
    private TextField addressTB;
    @FXML
    private Spinner<Integer> starsS;
    @FXML
    private TextField nameTB;
    @FXML
    private Label text1;
    @FXML
    private Label text2;
    @FXML
    private Label text3;
    @FXML
    private Label text4;
    @FXML
    private Button addB;
    @FXML
    private Button backB;
    @FXML
    private TextField cityTB;
    @FXML
    private TextField countryTB;
    @FXML
    private Label text5;
    @FXML
    private Label text6;
    
    private HotelManager manager;
	private final static Logger LOG = Logger.getLogger(RegistrationScreen.class.getName());

    //inicializacia obrazovky
    public void init(HotelManager c) {
    	this.manager = c;
    	
    	SpinnerValueFactory<Integer> stars = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5);
    	stars.setValue(1);
    	starsS.setValueFactory(stars);
    }

    //pridanie hotela do databazy
    @SuppressWarnings("unchecked")
	@FXML
    void addClick(ActionEvent event) throws ParseException {  
    	JSONObject json = new JSONObject();

    	json.put("stars", starsS.getValue());
    	json.put("country", countryTB.getText());
    	json.put("city", cityTB.getText());
    	json.put("address", addressTB.getText());
    	json.put("hotel_name", nameTB.getText());
    	json.put("manager_id", manager.getManager_id());

    	System.out.println(json.toJSONString());
    	if (nameTB.getText().isEmpty() || addressTB.getText().isEmpty() || cityTB.getText().isEmpty() || countryTB.getText().isEmpty())
        {
        	Alert a = new Alert(AlertType.ERROR);
    		a.setTitle(Main.bundle.getString("error"));
    		a.setContentText(Main.bundle.getString("errorFill"));
    		a.showAndWait();
        }
    	else {
    		try {
        		URL url = new URL(Main.prop.getProperty("REMOTE")+"/hotel/save");
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
        		conn.disconnect();
        		Alert a = new Alert(AlertType.CONFIRMATION);
        		a.setTitle(Main.bundle.getString("confirm"));
        		a.setContentText(Main.bundle.getString("addConfirm"));
        		a.showAndWait();
        		
        		URL url1 = new URL(Main.prop.getProperty("REMOTE")+"/manager/"+manager.getManager_id());
        		System.out.print(url1);
        		HttpURLConnection conn1 = null;
				conn1 = (HttpURLConnection) url1.openConnection();
				conn1.setUseCaches(false);
				conn1.setDoInput(true);
				conn1.setDoOutput(true);
				conn1.setRequestMethod("GET");
				
				conn1.getInputStream();
				BufferedReader in = new BufferedReader(new InputStreamReader(conn1.getInputStream()));
				
				JsonFactory fac = new JsonFactory();
    			JsonParser jp = fac.createParser(in);
    			ObjectMapper om = new ObjectMapper();
    			HotelManager mana = new HotelManager();
    			mana= om.readValue(jp, HotelManager.class);
    			this.manager=mana;
    			in.close();
    			
        		backClick(event);
        	} catch(IOException e) {
        		Alert a = new Alert(AlertType.ERROR);
        		a.setTitle(Main.bundle.getString("error"));
        		a.setContentText(Main.bundle.getString("errorExists"));
        		a.showAndWait();
        		LOG.log(Level.SEVERE, "Nepodarilo sa pridat hotel", e);
        	}
    	}
    	
    	
    }

    //navrat na predoslu obrazovku
    @FXML
    void backClick(ActionEvent event) throws IOException, ParseException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ManagerHomeScreen.fxml"));
		loader.setResources(Main.bundle);
    	Parent root=loader.load();

		ManagerHomeScreen m = loader.getController();
		m.init(this.manager);
		
		Scene login = new Scene(root);  			
		Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
		window.setScene(login);
		window.show();
		
    }

}
