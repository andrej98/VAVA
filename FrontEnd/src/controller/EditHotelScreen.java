package controller;

import java.io.IOException;
import java.util.logging.Logger;

import org.json.simple.parser.ParseException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.HotelManager;

/**
 * 
 * @author Andrej
 * Controller obrazovky pre hotel managera na upravovanie udajov o hoteli
 */
public class EditHotelScreen {

    @FXML
    private Label headL;
    @FXML
    private Label managerL;
    @FXML
    private Label nameL;
    @FXML
    private TextField nameTF;
    @FXML
    private TextField addressTF;
    @FXML
    private TextField cityTF;
    @FXML
    private TextField countryTF;
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
    private Button backB;
    @FXML
    private Button editB;
    @FXML
    private Spinner<Integer> starsS;
    
    private HotelManager c;
    private int hotelID;
	private final static Logger LOG = Logger.getLogger(EditHotelScreen.class.getName());
    
    //inicializacia obrazovky
    public void init(int hotelID, HotelManager c) {
    	this.c=c;
        
    }

    //aktualizacia hotela cez transakciu
    @FXML
    void editClick(ActionEvent event) throws  IOException {
    	
    }

    //navrat na predoslu obrazovku
    @FXML
    void backClick(ActionEvent event) throws IOException, ParseException {
    	FXMLLoader loader = new FXMLLoader(getClass().getResource("/gui/ManagerHomeScreen.fxml"));
    	loader.setResources(Main.bundle);
    	Parent root=loader.load();

		ManagerHomeScreen m = loader.getController();
		m.init(this.c);
		
		Scene login = new Scene(root);  			
		Stage window = (Stage)((Node) event.getSource()).getScene().getWindow();
		window.setScene(login);
		window.show();
		
    }
}
