package a1.minhnb.he191060.rentingservice.repository;

import a1.minhnb.he191060.rentingservice.entity.RentingTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface RentingTransactionRepository extends JpaRepository<RentingTransaction, Integer> {
    List<RentingTransaction> findByCustomerId(Integer customerId);
    List<RentingTransaction> findByRentingDateBetweenOrderByRentingDateDesc(Date startDate, Date endDate);
}
