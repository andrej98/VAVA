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
import model.Hotel;
import model.HotelManager;

/**
 * 
 * @author Andrej
 * Controller obrazovky pre hotel managera na upravovanie udajov o hoteli
 */
public class EditHotelScreen {

    @FXML
    private Label headL;
    @FXML
    private Label managerL;
    @FXML
    private Label nameL;
    @FXML
    private TextField nameTF;
    @FXML
    private TextField addressTF;
    @FXML
    private TextField cityTF;
    @FXML
    private TextField countryTF;
    @FXML
    private Label hnameL;
    @FXML
    private Label addressL;
    @FXML
    private Label starsL;
    @FXML
    private Label cityL;
    @FXML
    private Label countryL;
    @FXML
    private Button backB;
    @FXML
    private Button editB;
    @FXML
    private Spinner<Integer> starsS;
    
    private HotelManager manager;
    private Hotel hotel;
	private final static Logger LOG = Logger.getLogger(EditHotelScreen.class.getName());
    
    //inicializacia obrazovky
    public void init(Hotel hotel, HotelManager man) throws IOException {
    	this.manager=man;
    	this.hotel=hotel;
    	
    	managerL.setText(this.manager.getName());
    	nameL.setText(this.hotel.getHotel_name());
    	
    	nameTF.setText(hotel.getHotel_name());
    	addressTF.setText(hotel.getAddress());
    	cityTF.setText(hotel.getCity());
    	countryTF.setText(hotel.getCountry());
    	SpinnerValueFactory<Integer> stars = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 5);
    	stars.setValue(hotel.getStars());
    	starsS.setValueFactory(stars);
        
    }

    //aktualizacia hotela cez transakciu
    @SuppressWarnings("unchecked")
	@FXML
    void editClick(ActionEvent event) throws ParseException  {
    	JSONObject json = new JSONObject();

    	json.put("stars", starsS.getValue());
    	json.put("country", countryTF.getText());
    	json.put("city", cityTF.getText());
    	json.put("address", addressTF.getText());
    	json.put("hotel_name", nameTF.getText());
    	json.put("manager_id", manager.getManager_id());
    	json.put("hotel_id", this.hotel.getHotel_id());
    	
    	
    	if (nameTF.getText().isEmpty() || addressTF.getText().isEmpty() || cityTF.getText().isEmpty() || countryTF.getText().isEmpty())
        {
        	Alert a = new Alert(AlertType.ERROR);
    		a.setTitle(Main.bundle.getString("error"));
    		a.setContentText(Main.bundle.getString("errorFill"));
    		a.showAndWait();
        }
    	else {
    		try {
    			URL url = new URL(Main.prop.getProperty("REMOTE")+"/hotel/update");
    			HttpURLConnection httpCon = (HttpURLConnection) url.openConnection();
    			httpCon.setDoOutput(true);
    			httpCon.setRequestMethod("PUT");
    			httpCon.setRequestProperty("Content-Type", "application/json");
    			OutputStreamWriter out = new OutputStreamWriter(
    			    httpCon.getOutputStream());
    			out.write(json.toString());
    			out.close();
    			httpCon.getInputStream();
    			
        		Alert a = new Alert(AlertType.CONFIRMATION);
        		a.setTitle(Main.bundle.getString("confirm"));
        		a.setContentText(Main.bundle.getString("confirmEdit"));
        		a.showAndWait();
        		
        		URL url2 = new URL(Main.prop.getProperty("REMOTE")+"/manager/"+manager.getManager_id());
        		System.out.print(url2);
        		HttpURLConnection conn2 = null;
    			conn2 = (HttpURLConnection) url2.openConnection();
    			conn2.setUseCaches(false);
    			conn2.setDoInput(true);
    			conn2.setDoOutput(true);
    			conn2.setRequestMethod("GET");
    			
    			conn2.getInputStream();
    			BufferedReader in2 = new BufferedReader(new InputStreamReader(conn2.getInputStream()));
    			
    			JsonFactory fac2 = new JsonFactory();
    			JsonParser jp2 = fac2.createParser(in2);
    			ObjectMapper om2 = new ObjectMapper();
    			HotelManager man = new HotelManager();
    			man = om2.readValue(jp2, HotelManager.class);
    			this.manager=man;
        		in2.close();
        		
        		backClick(event);
    		}catch (IOException e) {
    			Alert a = new Alert(AlertType.ERROR);
	    		a.setTitle(Main.bundle.getString("error"));
	    		a.setContentText(Main.bundle.getString("errorExists"));
	    		a.showAndWait();
	    		LOG.log(Level.SEVERE, "Nepodarilo sa upravit hotel", e);
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
