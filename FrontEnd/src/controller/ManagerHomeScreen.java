package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;


import org.json.simple.parser.ParseException;

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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import model.Hotel;
import model.HotelManager;


/**
 * @author Andrej
 * controller obrazovky managera, kde vidi vsetky svoje hotely aj s poctom zamestnancov
 */
public class ManagerHomeScreen {

    @FXML
    private Button logoutB;
    @FXML
    private Label nameL;
    @FXML
    private Label text1;
    @FXML
    private Button addB;
    @FXML
    private Button editB;
    @FXML
    private Button removeB;
    @FXML
    private Button selectB;
    @FXML
    private TableView<Hotel> table;
    @FXML
    private TableColumn<Hotel, SimpleIntegerProperty> roomsC;
    @FXML
    private TableColumn<Hotel, String> nameC;
    @FXML
    private TableColumn<Hotel, String> addressC;
    @FXML
    private TableColumn<Hotel, String> cityC;
    @FXML
    private TableColumn<Hotel, String> countryC;
    @FXML
    private TableColumn<Hotel, SimpleIntegerProperty> starsC; 

    private ObservableList<Hotel> hotels;
	private final static Logger LOG = Logger.getLogger(ManagerHomeScreen.class.getName());
	private HotelManager manager;
	
    //inicializacia obrazovky
    public void init(HotelManager manager) throws IOException, ParseException {
    	this.manager=manager;
    	hotels = FXCollections.observableArrayList(manager.getHotels());

    	for (Hotel hotel : hotels) {
			hotel.setRooms_count(hotel.getRooms().size());
		}
    	nameL.setText(manager.getFirst_name() + " " + manager.getLast_name());
    	
        roomsC.setCellValueFactory(new PropertyValueFactory<>("rooms_count"));
        nameC.setCellValueFactory(new PropertyValueFactory<>("hotel_name"));
        addressC.setCellValueFactory(new PropertyValueFactory<>("address"));
        cityC.setCellValueFactory(new PropertyValueFactory<>("city"));
        countryC.setCellValueFactory(new PropertyValueFactory<>("country"));
        starsC.setCellValueFactory(new PropertyValueFactory<>("stars"));
        
        table.setItems(hotels);
        table.getSelectionModel().selectFirst();
      }
  
    //prejde na obrazovku pridania noveho hotela
    @FXML
    void addClick(ActionEvent event) throws IOException, SQLException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/AddHotelScreen.fxml"));
    	loader.setResources(Main.bundle);
		Parent root = loader.load();
		AddHotelScreen a = loader.getController();
		a.init(manager);
		Scene registerScene = new Scene(root);
		Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
		window.setScene(registerScene);
		window.show();
    }
    
    //navrat na obrazovku prihlasenia
    @FXML
    void logoutClick(ActionEvent event) throws IOException, SQLException {
    	Parent root = FXMLLoader.load(ManagerHomeScreen.class.getResource("/gui/HomeScreen.fxml"),Main.bundle);
		Scene registerScene = new Scene(root);
		Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
		window.setScene(registerScene);
		window.show();
    }
    
    //vymazanie hotela z databazy
    @FXML
    void removeClick(ActionEvent event) throws SQLException {
    	
    }

    //otvori sa okno s detailmi o vybranom hoteli
    @FXML
    void selectClick(ActionEvent event) throws IOException {
    	Hotel selected = table.getSelectionModel().getSelectedItem();
    	
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/SelectHotelScreen.fxml"));
    	loader.setResources(Main.bundle);
		Parent root = loader.load();
		SelectHotelScreen a = loader.getController();
		a.init(selected,manager);
		Scene registerScene = new Scene(root);
		Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
		window.setScene(registerScene);
		window.show();
    }

    //otvori sa okno editacie zvoleneho hotela
    @FXML
    void editClick(ActionEvent event) throws SQLException, IOException {
    	
    }
    
}
