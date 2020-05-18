package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

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
import model.Customer;

/**
 * @author Andrej
 * Controller obrazovky pre customera, vidi vsetky svoje rezervacie a ich stav
 */
public class GuestReservationsScreen {

    @FXML
    private TableView<?> table;
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
    private ObservableList data = FXCollections.observableArrayList();
	private final static Logger LOG = Logger.getLogger(GuestReservationsScreen.class.getName());
    private int first = 0;
    
    //inicializacia obrazovky
    public void init(Customer c) {
    	this.c = c;
    	
    	
    	
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

    //vytvori zaznam v tabulke payment, priradey k danej rezervacii, v tabulke sa aktualizuje stav na paid
    @FXML
    void payClick(ActionEvent event) throws SQLException {
    	
    }
}
