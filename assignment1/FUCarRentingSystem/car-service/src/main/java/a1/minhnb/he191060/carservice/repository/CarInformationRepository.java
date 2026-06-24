package a1.minhnb.he191060.carservice.repository;

import a1.minhnb.he191060.carservice.entity.CarInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarInformationRepository extends JpaRepository<CarInformation, Integer> {
}
