package service;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Reservation;
import model.ReservationDTO;
import repository.ReservationRepository;

/**
 * Service pre triedu Reservation
 * @author Andrej
 *
 */
@Service
public class ReservationService {

	@Autowired
	private ReservationRepository reservationR;

	/**
	 * Vrati zoznam rezervacii pre daneho customera, zaroven vypocita cenu kazdej rezervacie
	 * @param customer_id
	 * @return
	 */
	public List<ReservationDTO> getAll(int customer_id) {
		List<ReservationDTO> list = reservationR.getAll(customer_id);
		for(ReservationDTO r : list) {
			 LocalDate in = LocalDate.parse(r.getCheckin_date().toString());
			 LocalDate out = LocalDate.parse(r.getCheckout_date().toString());
			 if(in.isAfter(out)) {
				 LocalDate tmp = out;
				 out = in;
				 in=tmp;
			 }
			 int daysBetween = (int)ChronoUnit.DAYS.between(LocalDate.parse(in.toString()),LocalDate.parse(out.toString()));
			 r.setPrice(r.getPrice()*daysBetween);
		}
		return list;
	}

	/**
	 * Ulozi novu rezervaciu do databazy
	 * @param reservation
	 */
	public void saveReservation(Reservation reservation) {
		reservationR.save(reservation);
	}

	/**
	 * Zmaze danu rezervaciu z databazy
	 * @param reservation_id
	 */
	public void deleteReservation(int reservation_id) {
		reservationR.deleteById(reservation_id);
		
	}
}
