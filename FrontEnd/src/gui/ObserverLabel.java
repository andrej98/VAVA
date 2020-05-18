package gui;

import controller.GuestHomeObserver;
import controller.Main;
import javafx.scene.control.Label;
//upraveny label aby bol observer
public class ObserverLabel extends Label implements GuestHomeObserver{

	@Override
	public void upovedom(int pocetHotelov) {
		this.setText(Main.bundle.getString("totalHotels") + pocetHotelov);
	}

}
