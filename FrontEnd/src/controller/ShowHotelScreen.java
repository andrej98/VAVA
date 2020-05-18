package controller;

import java.io.IOException;
import java.util.logging.Logger;

import javafx.beans.property.SimpleIntegerProperty;

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
    private TableView<?> table;
    @FXML
    private TableColumn<?, SimpleIntegerProperty> bedsC;
    @FXML
    private TableColumn<?, SimpleIntegerProperty> priceC;
    @FXML
    private TableColumn<?, String> heatingC;
    @FXML
    private TableColumn<?, String> bathroomC;
    @FXML
    private Label unameL;
    @FXML
    private Button backB;
    @FXML
    private Button reservationB;
    @FXML
    private Label text;
    
	private final static Logger LOG = Logger.getLogger(ShowHotelScreen.class.getName());
    private int hotel_id;
    private Customer c;

    //inicializacia obrazovky
    public void init(int hotel_id, Customer c) {
    	
        
        this.hotel_id = hotel_id;
        this.c = c;
        
        
        table.getSelectionModel().selectFirst();
        
        priceC.setCellValueFactory(new PropertyValueFactory<>("price"));
        heatingC.setCellValueFactory(new PropertyValueFactory<>("heating"));
        bathroomC.setCellValueFactory(new PropertyValueFactory<>("bathroom"));
        bedsC.setCellValueFactory(new PropertyValueFactory<>("beds"));
        
    }
    
    //navrat na predoslu obrazovku
    @FXML
    void backClick(ActionEvent event) throws IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/GuestHomeScreen.fxml"));
    	loader.setResources(Main.bundle);
    	Parent root=loader.load();

    	GuestHomeScreen m = loader.getController();
		m.init(this.c);
		
		Scene login = new Scene(root);  			
		Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
		window.setScene(login);
		window.show();

    }

    //otvori sa okno na rezervaciu zvolenej izby
    @FXML
    void reservationClick(ActionEvent event) throws IOException {
    	
    		
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/MakeReservationScreen.fxml"));
    		loader.setResources(Main.bundle);
        	Parent root = loader.load();
    		MakeReservationScreen a = loader.getController();
    		//a.init(id, this.currentUser, this.hotel_id);;
    		Scene registerScene = new Scene(root);
    		Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
    		window.setScene(registerScene);
    		window.show();
    	
    }
}
