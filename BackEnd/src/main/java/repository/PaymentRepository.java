package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import model.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment,Integer>{

}
