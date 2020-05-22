package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

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
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Customer;
import model.Hotel;
import model.Reservation;

/**
 * @author Andrej
 * Controller obrazovky pre customera, vidi vsetky svoje rezervacie a ich stav
 */
public class GuestReservationsScreen {

    @FXML
    private TableView<Reservation> table;
    @FXML
    private TableColumn<Reservation, SimpleIntegerProperty> idC;
    @FXML
    private TableColumn<Reservation, String> hotelC;
    @FXML
    private TableColumn<Reservation, String> cityC;
    @FXML
    private TableColumn<Reservation, String> countryC;
    @FXML
    private TableColumn<Reservation, String> addressC;
    @FXML
    private TableColumn<Reservation, SimpleIntegerProperty> starsC;
    @FXML
    private TableColumn<Reservation, Date> checkinC;
    @FXML
    private TableColumn<Reservation, Date> checkoutC;
    @FXML
    private TableColumn<Reservation, SimpleIntegerProperty> bedsC;
    @FXML
    private TableColumn<Reservation, SimpleIntegerProperty> priceC;
    @FXML
    private TableColumn<Reservation, String> paidC;
    @FXML
    private Button backB;
    @FXML
    private Button cancelB;
    @FXML
    private Button pdfB;
    @FXML
    private Button payB;
    @FXML
    private Label nameL;

    private Customer c;
    private ObservableList<Reservation> data;
	private final static Logger LOG = Logger.getLogger(GuestReservationsScreen.class.getName());
    private int first = 0;
    
    //inicializacia obrazovky
    public void init(Customer c) {
    	this.c = c;
    	data = FXCollections.observableArrayList(c.getReservations());
//    	try {
//			URL url = new URL(Main.prop.getProperty("REMOTE")+"/reservations/"+this.c.getCustomer_id()) ;
//			HttpURLConnection conn = null;
//			conn = (HttpURLConnection) url.openConnection();
//			conn.setUseCaches(false);
//			conn.setDoInput(true);
//			conn.setDoOutput(true);
//			conn.setRequestMethod("GET");
//			
//			conn.getInputStream();
//			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//	    	
//			JsonFactory fac2 = new JsonFactory();
//			JsonParser jp2 = fac2.createParser(in);
//			Reservation[] arr = new ObjectMapper().readValue(jp2, Reservation[].class);
//			data =  FXCollections.observableArrayList(Arrays.asList(arr));
//		} catch(IOException e) {
//			LOG.log(Level.SEVERE, "Pripojenie k serveru neuspesne", e);
//		}
//    	
        hotelC.setCellValueFactory(new PropertyValueFactory<>("hotel_name"));
        addressC.setCellValueFactory(new PropertyValueFactory<>("address"));
        cityC.setCellValueFactory(new PropertyValueFactory<>("city"));
        countryC.setCellValueFactory(new PropertyValueFactory<>("country"));
        starsC.setCellValueFactory(new PropertyValueFactory<>("stars"));
        
        idC.setCellValueFactory(new PropertyValueFactory<>("reservation_id"));
        checkinC.setCellValueFactory(new PropertyValueFactory<>("checkin_date"));
        checkoutC.setCellValueFactory(new PropertyValueFactory<>("checkout_date"));
        bedsC.setCellValueFactory(new PropertyValueFactory<>("beds"));
//        priceC.setCellValueFactory(new PropertyValueFactory<>("price"));
//        paidC.setCellValueFactory(new PropertyValueFactory<>("paid"));
        
        table.setItems(data);
    	
    }
   
    
    
    //overi ci je este nebol datum checkinu, a ci nebola zaplatena, ak nie tak vymaze rezervaciu
    @FXML
    void cancelClick(ActionEvent event) throws SQLException {
    	
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
    
    //export detailov zvolenej rezervacie do PDF pouzitim iText PDF
    @FXML
    void pdfClick(ActionEvent event) throws FileNotFoundException {
    	if(!data.isEmpty()) {
    		FileChooser fileChooser = new FileChooser();
        	File defaultDir = new File("pdf");
        	fileChooser.setInitialDirectory(defaultDir);
        	fileChooser.setInitialFileName("reservation.pdf");
        	File selectedFile = fileChooser.showSaveDialog(null);
        	   
        	PdfWriter writer = new PdfWriter(selectedFile);
        	PdfDocument pdfDocument = new PdfDocument(writer);
        	pdfDocument.setTagged();
        	Document document = new Document(pdfDocument);
        	document.add(new Paragraph(this.nameL.getText()));

        	for(int i=0;i<table.getColumns().toArray().length;i++) {
        		TableColumn col = table.getColumns().get(i);
            	int row = table.getSelectionModel().getSelectedIndex();
            	String text = col.getCellData(row).toString();
            	
            	document.add(new Paragraph(col.getText() + ": " + text));
        	}
        	document.close();   
        	LOG.info("PDF uspesne vytvorene");
        	
        	Alert a = new Alert(AlertType.CONFIRMATION);
    		a.setTitle(Main.bundle.getString("confirm"));
    		a.setContentText(Main.bundle.getString("pdfAlert"));
    		a.showAndWait();
    	}
    }

    //vytvori zaznam v tabulke payment, priradey k danej rezervacii, v tabulke sa aktualizuje stav na paid
    @FXML
    void payClick(ActionEvent event) throws SQLException {
    	
    }
}
