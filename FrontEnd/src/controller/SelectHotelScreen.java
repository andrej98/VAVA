package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Hotel;
import model.HotelManager;
import model.Room;


/**
 * @author Andrej
 * Controller obrazovky kde hotel manager vidi detaily o svojom hoteli, aj so zoznamom izieb, ktore su v nom
 */
public class SelectHotelScreen {

    @FXML
    private Label text3;
    @FXML
    private Button addB;
    @FXML
    private Button removeB;
    @FXML
    private TableView<Room> table;
    @FXML
    private TableColumn<Room, SimpleIntegerProperty> idC;
    @FXML
    private TableColumn<Room, SimpleIntegerProperty> bedsC;
    @FXML
    private TableColumn<Room, SimpleIntegerProperty> priceC;
    @FXML
    private TableColumn<Room, String> heatingC;
    @FXML
    private TableColumn<Room, String> bathroomC;
    @FXML
    private Label text1;
    @FXML
    private Label text2;
    @FXML
    private Button backB;
    @FXML
    private Button employeesB;
    @FXML
    private Label addressL;
    @FXML
    private Label starsL;
    @FXML
    private Label cityL;
    @FXML
    private Label countryL;

    
    private Hotel hotel;
    private HotelManager manager;
    
	private final static Logger LOG = Logger.getLogger(SelectHotelScreen.class.getName());
    private ObservableList<Room> rooms;
    
    //inicializacia obrazovky
    public void init(Hotel hotel, HotelManager manager) {
    	this.hotel = hotel;
    	this.manager = manager;
    	
    	rooms = FXCollections.observableArrayList(hotel.getRooms());

        
    	text1.setText(Main.bundle.getString("hotelC")+": " +hotel.getHotel_name());
    	text2.setText(Main.bundle.getString("managerRB") +": "+manager.getName());
    	addressL.setText(Main.bundle.getString("addressC") +": " + hotel.getAddress());
    	starsL.setText(Main.bundle.getString("starsC") +": "+ hotel.getStars());
    	cityL.setText(Main.bundle.getString("cityC") +": "+ hotel.getCity());
    	countryL.setText(Main.bundle.getString("countryC") +": "+ hotel.getCountry());
        
                
        idC.setCellValueFactory(new PropertyValueFactory<>("room_id"));
        priceC.setCellValueFactory(new PropertyValueFactory<>("price"));
        heatingC.setCellValueFactory(new PropertyValueFactory<>("heating"));
        bathroomC.setCellValueFactory(new PropertyValueFactory<>("bathroom"));
        bedsC.setCellValueFactory(new PropertyValueFactory<>("beds"));
          
        table.setItems(rooms);
        table.getSelectionModel().selectFirst();
    }
    
    //otvori obrazovku na pridavanie izby do hotela
    @FXML
    void addClick(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AddRoomScreen.fxml"));
		loader.setResources(Main.bundle);
    	Parent root = loader.load();
		AddRoomScreen a = loader.getController();
		a.init(this.hotel,this.manager);
		Scene registerScene = new Scene(root);
		Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
		window.setScene(registerScene);
		window.show();
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
    

    //vymazanie izby z hotela
    @FXML
    void removeClick(ActionEvent event) throws IOException {
    	if(!rooms.isEmpty()) {
    		Room selected = table.getSelectionModel().getSelectedItem();
        		
    		Alert alert = new Alert(AlertType.CONFIRMATION, Main.bundle.getString("deleteAlert") +" "+
    		    	Main.bundle.getString("roomNumber") + selected.getRoom_id() +" ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
    		    	alert.showAndWait();

    		    	if (alert.getResult() == ButtonType.YES) {
    		    		
    		    		URL url = new URL(Main.prop.getProperty("REMOTE")+"/room/delete/"+selected.getRoom_id());
    		    		HttpURLConnection conn = null;
    		    		conn = (HttpURLConnection) url.openConnection();
    		    		conn.setDoOutput(true);
    		    		conn.setRequestProperty(
    		    		    "Content-Type", "application/x-www-form-urlencoded" );
    		    		conn.setRequestMethod("DELETE");
    		    		conn.connect();   	
    		    		conn.getInputStream();
    		    		
    		    		table.getItems().remove(selected);
    					
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
    		    	}
    	}
    	
    }
}
