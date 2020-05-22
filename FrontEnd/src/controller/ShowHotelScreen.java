package controller;

import java.io.IOException;
import java.util.logging.Logger;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customer;
import model.Hotel;
import model.Room;


/**
 * @author Andrej
 * Controller obrazovky pre customera, vidi tu detaily o zvolenom hoteli a zoznam izieb ktore si moze zarezervovat
 */
public class ShowHotelScreen {

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
    private TableView<Room> table;
    @FXML
    private TableColumn<Room, SimpleIntegerProperty> bedsC;
    @FXML
    private TableColumn<Room, SimpleIntegerProperty> priceC;
    @FXML
    private TableColumn<Room, String> heatingC;
    @FXML
    private TableColumn<Room, String> bathroomC;
    @FXML
    private Label unameL;
    @FXML
    private Button backB;
    @FXML
    private Button reservationB;
    @FXML
    private Label text;
    
    private ObservableList<Room> rooms;
	private final static Logger LOG = Logger.getLogger(ShowHotelScreen.class.getName());
    private Hotel hotel;
    private Customer customer;

    //inicializacia obrazovky
    public void init(Hotel hotel, Customer c) { 
        this.hotel = hotel;
        this.customer = c;
                
        rooms = FXCollections.observableArrayList(hotel.getRooms());
        
        hnameL.setText(Main.bundle.getString("hotelC")+": " +hotel.getHotel_name());
    	unameL.setText(customer.getName());
    	addressL.setText(Main.bundle.getString("addressC") +": " + hotel.getAddress());
    	starsL.setText(Main.bundle.getString("starsC") +": "+ hotel.getStars());
    	cityL.setText(Main.bundle.getString("cityC") +": "+ hotel.getCity());
    	countryL.setText(Main.bundle.getString("countryC") +": "+ hotel.getCountry());
        
        priceC.setCellValueFactory(new PropertyValueFactory<>("price"));
        heatingC.setCellValueFactory(new PropertyValueFactory<>("heating"));
        bathroomC.setCellValueFactory(new PropertyValueFactory<>("bathroom"));
        bedsC.setCellValueFactory(new PropertyValueFactory<>("beds"));
        
        table.setItems(rooms);
        table.getSelectionModel().selectFirst();

        
    }
    
    //navrat na predoslu obrazovku
    @FXML
    void backClick(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/GuestHomeScreen.fxml"));
    	loader.setResources(Main.bundle);
    	Parent root=loader.load();

    	GuestHomeScreen m = loader.getController();
		m.init(this.customer);
		
		Scene login = new Scene(root);  			
		Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
		window.setScene(login);
		window.show();

    }

    //otvori sa okno na rezervaciu zvolenej izby
    @FXML
    void reservationClick(ActionEvent event) throws IOException {
    	if(!rooms.isEmpty()) {
    		Room selected = table.getSelectionModel().getSelectedItem();

        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MakeReservationScreen.fxml"));
    		loader.setResources(Main.bundle);
        	Parent root = loader.load();
    		MakeReservationScreen a = loader.getController();
    		a.init(selected, this.customer, this.hotel);;
    		Scene registerScene = new Scene(root);
    		Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
    		window.setScene(registerScene);
    		window.show();
    	}
    }
}
