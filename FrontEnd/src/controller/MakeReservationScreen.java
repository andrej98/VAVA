package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import model.Customer;
import model.Hotel;
import model.HotelManager;
import model.Room;

/**
 * @author Andrej
 * Controller obrazovky customera kde vykona rezervaciu
 */
public class MakeReservationScreen {

    @FXML
    private DatePicker inDate;
    @FXML
    private DatePicker outDate;
    @FXML
    private Label nameL;
    @FXML
    private Button backB;
    @FXML
    private Button reservationB;
    
    private Hotel hotel_id;
    private Room room_id;
    private Customer c;
	private final static Logger LOG = Logger.getLogger(MakeReservationScreen.class.getName());

    //inicializacia obrazovky
    public void init(Room room_id, Customer c, Hotel hotel_id) {
    	this.room_id=room_id;
    	this.c=c;
    	this.hotel_id = hotel_id;
    	
    	
        nameL.setText(c.getName());
    	
    	
    }

    //navrat na predoslu obrazovku
    @FXML
    void backClick(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ShowHotelScreen.fxml"));
    	loader.setResources(Main.bundle);
    	Parent root=loader.load();

    	ShowHotelScreen m = loader.getController();
		m.init(this.hotel_id, this.c);
		
		Scene login = new Scene(root);  			
		Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
		window.setScene(login);
		window.show();
    }

    //vytvori zaznam v tabulke rezervacii
    @FXML
    void reservationClick(ActionEvent event) throws IOException {
    	LocalDate in = inDate.getValue();
    	LocalDate out = outDate.getValue();
    	LocalDate now = LocalDate.now();

    	//overim spravnost datumov
    	if(in == null || out == null || in.isAfter(out) || in.isBefore(now) || in.isEqual(out)) {
    		Alert a = new Alert(AlertType.ERROR);
			a.setTitle(Main.bundle.getString("error"));
			a.setContentText(Main.bundle.getString("dateError"));
			a.showAndWait();
    	}
    	else {
    		JSONObject json = new JSONObject();

        	json.put("checkin_date", inDate.getValue().toString());
        	json.put("checkout_date", outDate.getValue().toString());
        	json.put("customer", this.c.getCustomer_id());
        	json.put("room", this.room_id.getRoom_id());
        	System.out.println(json.toJSONString());
        	
        	URL url = new URL(Main.prop.getProperty("REMOTE")+"/reservation/save");
    		HttpURLConnection conn = null;
    		conn = (HttpURLConnection) url.openConnection();
    		conn.setUseCaches(false);
    		conn.setDoInput(true);
    		conn.setDoOutput(true);
    		conn.setRequestMethod("POST");
    		conn.setRequestProperty("Content-Type", "application/json");
    		OutputStreamWriter output = new OutputStreamWriter(conn.getOutputStream());
    		output.write(json.toString());
    		output.close();
    		conn.getInputStream();
    		conn.disconnect();
    		Alert a = new Alert(AlertType.CONFIRMATION);
    		a.setTitle(Main.bundle.getString("confirm"));
    		a.setContentText(Main.bundle.getString("confirmRes"));
    		a.showAndWait();
    		
    		
    		backClick(event);
    	}
    }
}
