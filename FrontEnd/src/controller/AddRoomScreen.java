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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Hotel;
import model.HotelManager;

/**
 * @author Andrej
 * Controller obrazovky pre hotel managera na pridavanie izieb do hotela
 */
public class AddRoomScreen {
	@FXML
    private Spinner<Integer> bedsS;
    @FXML
    private ChoiceBox<String> heatingCB;
    @FXML
    private ChoiceBox<String> bathroomCB;
    @FXML
    private Spinner<Integer> priceS;
    @FXML
    private Label text2;
    @FXML
    private Label text1;
    @FXML
    private Label text3;
    @FXML
    private Label text4;
    @FXML
    private Label text5;
    @FXML
    private Button addB;
    @FXML
    private Button backB;
    @FXML
    private Label text;

    private Hotel hotel;
	private final static Logger LOG = Logger.getLogger(AddRoomScreen.class.getName());
	private HotelManager manager;
    
    //inicializacia obrazovky
    public void init(Hotel hotel_id,HotelManager manager) {
    	this.hotel=hotel_id;
    	this.manager=manager;
   
    	text.setText(Main.bundle.getString("hotelC") +": " + hotel.getHotel_name());
        
        SpinnerValueFactory<Integer> cena = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 100000);
        SpinnerValueFactory<Integer> postele = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20);
        priceS.setValueFactory(cena);
        bedsS.setValueFactory(postele);
        heatingCB.getItems().addAll(Main.bundle.getString("yes"),Main.bundle.getString("no"));
        heatingCB.getSelectionModel().selectFirst();
        bathroomCB.getItems().addAll(Main.bundle.getString("yes"),Main.bundle.getString("no"));
        bathroomCB.getSelectionModel().selectFirst();
    }
    
    //pridanie izby do databazy
    @SuppressWarnings("unchecked")
	@FXML
    void addClick(ActionEvent event) throws IOException {    	
    	Boolean heating,bathroom;
    	if (heatingCB.getValue().equalsIgnoreCase(Main.bundle.getString("yes")))
    		heating=true;
    	else
    		heating= false;
    	if (bathroomCB.getValue().equalsIgnoreCase(Main.bundle.getString("yes")))
    		bathroom=true;
    	else
    		bathroom= false;
    	
    	JSONObject json = new JSONObject(); 	

    	json.put("beds", bedsS.getValue());
    	json.put("price", priceS.getValue());
    	json.put("heating", heating);
    	json.put("bathroom", bathroom);
    	json.put("hotel_id", hotel.getHotel_id());

    	
    	
		try {
    		URL url = new URL(Main.prop.getProperty("REMOTE")+"/room/save");
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
    		
    		URL url1 = new URL(Main.prop.getProperty("REMOTE")+"/hotel/"+hotel.getHotel_id());
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
			Hotel h = new Hotel();
			h= om.readValue(jp, Hotel.class);
			this.hotel=h;
			in.close();
			
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
    		
    		backClick(event);
    		in2.close();
    	} catch(IOException e) {
    		Alert a = new Alert(AlertType.ERROR);
    		a.setTitle(Main.bundle.getString("error"));
    		a.setContentText(Main.bundle.getString("error"));
    		a.showAndWait();
    		LOG.log(Level.SEVERE, "Nepodarilo sa pridat izbu", e);
    	}
    	
    }

    //navrat na predoslu obrazovku
    @FXML
    void backClick(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/SelectHotelScreen.fxml"));
    	loader.setResources(Main.bundle);
    	Parent root=loader.load();

		SelectHotelScreen m = loader.getController();
		m.init(this.hotel,this.manager);
		
		Scene login = new Scene(root);  			
		Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
		window.setScene(login);
		window.show();
		
    }
}
