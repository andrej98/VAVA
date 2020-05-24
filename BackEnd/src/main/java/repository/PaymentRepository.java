package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import model.Payment;

/**
 * Repository pre triedu Payment, dedi od JpaRepository
 * @author Andrej
 *
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payment,Integer>{

}
