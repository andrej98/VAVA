package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import java.util.logging.Logger;

import javafx.beans.property.SimpleIntegerProperty;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customer;


/**
 * @author Andrej
 * obrazovka customera, vidi zoznam vsetkych hotelov a moze medzi nimi filtrovat
 */
public class GuestHomeScreen implements Initializable{

    @FXML
    private Button logoutB;
    @FXML
    private Label text;
    @FXML
    private Label text1;
    @FXML
    private TableView<?> table;
    @FXML
    private TableColumn<?, SimpleIntegerProperty> roomsC;
    @FXML
    private TableColumn<?, String> nameC;
    @FXML
    private TableColumn<?, String> addressC;
    @FXML
    private TableColumn<?, String> cityC;
    @FXML
    private TableColumn<?, String> countryC;
    @FXML
    private TableColumn<?, SimpleIntegerProperty> starsC;  
    @FXML
    private Button filterB;
    @FXML
    private Button unfilterB;
    @FXML
    private TextField nameTF;
    @FXML
    private Label text3;
    @FXML
    private TextField cityTB;
    @FXML
    private Label text4;
    @FXML
    private Label text5;
    @FXML
    private Label text6;
    @FXML
    private TextField countryTB;
    @FXML
    private Spinner<Integer> starsS;
    @FXML
    private Label text7;
    @FXML
    private Spinner<Integer> bedsS;
    @FXML
    private Label text8;
//    @FXML
//    private ObserverLabel observerLabel;
    @FXML
    private Spinner<Integer> priceS;
    @FXML
    private Button selectB;
    @FXML
    private Button reservationsB;

//    //list kde su aktualne data v table
//    private ObservableList<Hotel> filterList;
//    //original list mam stale k dispozicii, ked dam vycistit filter, nech sa nerobi narocna query znova
//    private static ObservableList<Hotel> originalList;
    private int pocetHotelov;


    private Customer c;
    private List<GuestHomeObserver> sledovatelia = new ArrayList<GuestHomeObserver>();
	private final static Logger LOG = Logger.getLogger(GuestHomeScreen.class.getName());

 	
	
	public void init(Customer c) {
    	this.c=c;
    	
        
        roomsC.setCellValueFactory(new PropertyValueFactory<>("rooms_count"));
        nameC.setCellValueFactory(new PropertyValueFactory<>("hotel_name"));
        addressC.setCellValueFactory(new PropertyValueFactory<>("address"));
        cityC.setCellValueFactory(new PropertyValueFactory<>("city"));
        countryC.setCellValueFactory(new PropertyValueFactory<>("country"));
        starsC.setCellValueFactory(new PropertyValueFactory<>("stars"));

        
//        table.setItems(filterList);
        table.getSelectionModel().selectFirst();
	}
      
	//metody observera
	public void pridajSledovatela(GuestHomeObserver observerLabel2) {
		sledovatelia.add(observerLabel2);
	}
	
	public void upovedomSledovatelov(int pocetHotelov) {
		for (GuestHomeObserver s : sledovatelia)
			s.upovedom(pocetHotelov);
	}
     
	//filtrovanie hotelov, prazdne policka ignoruje
    @FXML
    void filterClick(ActionEvent event) {
    	
    	
    	upovedomSledovatelov(pocetHotelov);
    }
  
    //navrat na login obrazovku
    @FXML
    void logoutClick(ActionEvent event) throws IOException {
    	Parent root = FXMLLoader.load(GuestHomeScreen.class.getResource("/gui/HomeScreen.fxml"),Main.bundle);
		Scene registerScene = new Scene(root);
		Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
		window.setScene(registerScene);
		window.show();
    }
    
    //otvori sa obrazovka s detailmi o hoteli ktory bol zvoleny
    @FXML
    void selectClick(ActionEvent event) throws IOException {
    		
    }
    
    //otvori sa obrazovka rezervacii
    @FXML
    void reservationsClick(ActionEvent event) throws  IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/GuestReservationsScreen.fxml"));
    	loader.setResources(Main.bundle);
    	Parent root=loader.load();
    	GuestReservationsScreen m = loader.getController();
		m.init(this.c);		
		Scene login = new Scene(root);  			
		Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
		window.setScene(login);
		window.show();
    }
    
    //vymaze filter a vycisti polia na filtrovanie
    @FXML 
    void unfilterClick(ActionEvent event) {
    	
    }

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
       starsS.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,5));
       bedsS.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,15));
 	}
}