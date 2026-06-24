package a1.minhnb.he191060.carservice.controller;

import a1.minhnb.he191060.carservice.entity.CarInformation;
import a1.minhnb.he191060.carservice.repository.CarInformationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarInformationController {

    @Autowired
    private CarInformationRepository carInformationRepository;

    @GetMapping
    public List<CarInformation> getAll() {
        return carInformationRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarInformation> getById(@PathVariable Integer id) {
        return carInformationRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<CarInformation> create(@RequestHeader("X-User-Role") String role, @RequestBody CarInformation car) {
        if (!"ADMIN".equals(role)) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        return ResponseEntity.ok(carInformationRepository.save(car));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarInformation> update(@RequestHeader("X-User-Role") String role, @PathVariable Integer id, @RequestBody CarInformation carDetails) {
        if (!"ADMIN".equals(role)) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        return carInformationRepository.findById(id).map(car -> {
            car.setCarName(carDetails.getCarName());
            car.setCarDescription(carDetails.getCarDescription());
            car.setNumberOfDoors(carDetails.getNumberOfDoors());
            car.setSeatingCapacity(carDetails.getSeatingCapacity());
            car.setFuelType(carDetails.getFuelType());
            car.setYear(carDetails.getYear());
            car.setManufacturerId(carDetails.getManufacturerId());
            car.setSupplierId(carDetails.getSupplierId());
            car.setCarStatus(carDetails.getCarStatus());
            car.setCarRentingPricePerDay(carDetails.getCarRentingPricePerDay());
            return ResponseEntity.ok(carInformationRepository.save(car));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@RequestHeader("X-User-Role") String role, @PathVariable Integer id) {
        if (!"ADMIN".equals(role)) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        if (carInformationRepository.existsById(id)) {
            carInformationRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
