package a1.minhnb.he191060.rentingservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CAR-SERVICE", path = "/api/cars")
public interface CarServiceClient {
    @GetMapping("/{id}")
    CarDTO getCarById(@PathVariable("id") Integer id);

    @org.springframework.web.bind.annotation.PutMapping("/{id}/reserve")
    org.springframework.http.ResponseEntity<Void> reserveCar(@PathVariable("id") Integer id);

    @org.springframework.web.bind.annotation.PutMapping("/{id}/cancel-reserve")
    org.springframework.http.ResponseEntity<Void> cancelReserveCar(@PathVariable("id") Integer id);
}
