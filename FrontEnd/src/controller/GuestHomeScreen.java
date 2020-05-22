package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.util.logging.Logger;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import gui.ObserverLabel;
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
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Customer;
import model.Hotel;
import model.Room;


/**
 * @author Andrej
 * obrazovka customera, vidi zoznam vsetkych hotelov a moze medzi nimi filtrovat
 */
public class GuestHomeScreen {

    @FXML
    private Button logoutB;
    @FXML
    private Label text;
    @FXML
    private Label text1;
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
    @FXML
    private ObserverLabel observerLabel;
    @FXML
    private Spinner<Integer> priceS;
    @FXML
    private Button selectB;
    @FXML
    private Button reservationsB;

    //list kde su aktualne data v table
    private ObservableList<Hotel> filterList;
    //original list mam stale k dispozicii, ked dam vycistit filter, nech sa nerobi narocna query znova
    private static ObservableList<Hotel> originalList;
    private int pocetHotelov;


    private Customer customer;
    private List<GuestHomeObserver> sledovatelia = new ArrayList<GuestHomeObserver>();
	private final static Logger LOG = Logger.getLogger(GuestHomeScreen.class.getName());

 	
	
	public void init(Customer c,List<Hotel> list) throws JsonParseException, JsonMappingException, IOException {
    	this.customer=c;   	
    	
    	starsS.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,5));
        bedsS.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,15));
        
    	originalList = FXCollections.observableArrayList(list);
    	this.filterList = FXCollections.observableArrayList(list);

     	text1.setText(c.getName());
    	
        roomsC.setCellValueFactory(new PropertyValueFactory<>("rooms_count"));
        nameC.setCellValueFactory(new PropertyValueFactory<>("hotel_name"));
        addressC.setCellValueFactory(new PropertyValueFactory<>("address"));
        cityC.setCellValueFactory(new PropertyValueFactory<>("city"));
        countryC.setCellValueFactory(new PropertyValueFactory<>("country"));
        starsC.setCellValueFactory(new PropertyValueFactory<>("stars"));

        this.pocetHotelov = list.size();
    	pridajSledovatela(observerLabel);
    	upovedomSledovatelov(pocetHotelov);
        
        table.setItems(filterList);
        table.getSelectionModel().selectFirst();
	}
	
	public void init(Customer c) {
		this.customer=c;	
    	this.filterList = FXCollections.observableArrayList(originalList);
    	text1.setText(c.getName());
    	
        roomsC.setCellValueFactory(new PropertyValueFactory<>("rooms_count"));
        nameC.setCellValueFactory(new PropertyValueFactory<>("hotel_name"));
        addressC.setCellValueFactory(new PropertyValueFactory<>("address"));
        cityC.setCellValueFactory(new PropertyValueFactory<>("city"));
        countryC.setCellValueFactory(new PropertyValueFactory<>("country"));
        starsC.setCellValueFactory(new PropertyValueFactory<>("stars"));

        this.pocetHotelov = filterList.size();
    	pridajSledovatela(observerLabel);
    	upovedomSledovatelov(pocetHotelov);
        
        table.setItems(filterList);
        table.getSelectionModel().selectFirst();
        
        starsS.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,5));
        bedsS.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0,15));
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
    void filterClick(ActionEvent event) throws JsonParseException, JsonMappingException, IOException {
    	
    	if(nameTF.getText().isEmpty() && cityTB.getText().isEmpty() && countryTB.getText().isEmpty() && starsS.getValue() == 0
    			&& priceS.getValue() == 0 && bedsS.getValue() == 0) {
    		filterList = FXCollections.observableArrayList(originalList);
            table.setItems(filterList);
    	}
    	else {

	    	StringBuilder sb = new StringBuilder(Main.prop.getProperty("REMOTE"));
	    	sb.append("/hotels/?");
	    	if(!nameTF.getText().isEmpty()) {
	    		sb.append("hotel_name=").append(nameTF.getText());
	    	}
	    	if(!countryTB.getText().isEmpty()) {
	    		sb.append("&country=").append(countryTB.getText());
	    	}
	    	if(!cityTB.getText().isEmpty()) {
	    		sb.append("&city=").append(cityTB.getText());
	    	}
	    	if(priceS.getValue() != 0) {
	    		sb.append("&price=").append(priceS.getValue().toString());
	    	}
	    	if(bedsS.getValue() != 0) {
	    		sb.append("&beds=").append(bedsS.getValue().toString());
	    	}
	    	if(starsS.getValue() != 0) {
	    		sb.append("&stars=").append(starsS.getValue().toString());
	    	}
	    	sb = new StringBuilder(sb.toString().replaceAll(" ", "%20"));
	    	
	    	filterList.clear();
	    	
	    	URL url = new URL(sb.toString());
			HttpURLConnection conn = null;
			conn = (HttpURLConnection) url.openConnection();
			conn.setUseCaches(false);
			conn.setDoInput(true);
			conn.setDoOutput(true);
			conn.setRequestMethod("GET");
			
			conn.getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
	    	
			JsonFactory fac2 = new JsonFactory();
			JsonParser jp2 = fac2.createParser(in);
			Hotel[] arr = new ObjectMapper().readValue(jp2, Hotel[].class);
			filterList =  FXCollections.observableArrayList(Arrays.asList(arr));

			for (Hotel hotel : filterList) {
				int count = 0;
				for(Room room : hotel.getRooms()) {
					if((priceS.getValue() != 0 && room.getPrice()<=priceS.getValue()) && (bedsS.getValue() != 0 && room.getBeds()==bedsS.getValue())) 
						count++;
					else if(priceS.getValue() ==0 && bedsS.getValue() == 0)
						count++;
					else if(priceS.getValue() == 0 && bedsS.getValue() != 0 && bedsS.getValue()==room.getBeds())
						count++;
					else if(bedsS.getValue() == 0 && priceS.getValue() != 0 && priceS.getValue()>=room.getPrice())
						count++;
				
				}
				hotel.setRooms_count(count);
			}
			
            table.setItems(filterList);

		
    	}
    	table.getSelectionModel().selectFirst();
    	pocetHotelov = filterList.size();
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
    	if(!filterList.isEmpty()) {
    		Hotel selected = table.getSelectionModel().getSelectedItem();
        	FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ShowHotelScreen.fxml"));
        	loader.setResources(Main.bundle);
        	        		            		
			Parent root=loader.load();

			ShowHotelScreen m = loader.getController();
			m.init(selected, this.customer);
			
			Scene login = new Scene(root);  			
			Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
			window.setScene(login);
			window.show();
    		
    	}	
    }
    
    //otvori sa obrazovka rezervacii
    @FXML
    void reservationsClick(ActionEvent event) throws  IOException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/GuestReservationsScreen.fxml"));
    	loader.setResources(Main.bundle);
    	Parent root=loader.load();
    	GuestReservationsScreen m = loader.getController();
		m.init(this.customer);		
		Scene login = new Scene(root);  			
		Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
		window.setScene(login);
		window.show();
    }
    
    //vymaze filter a vycisti polia na filtrovanie
    @FXML 
    void unfilterClick(ActionEvent event) {
    	filterList = FXCollections.observableArrayList(originalList);
        table.setItems(filterList);
        nameTF.clear();
        cityTB.clear();
        countryTB.clear();
        starsS.getValueFactory().setValue(0);
        priceS.getValueFactory().setValue(0);
        bedsS.getValueFactory().setValue(0);
    	table.getSelectionModel().selectFirst();
        upovedomSledovatelov(filterList.size());
    }
}