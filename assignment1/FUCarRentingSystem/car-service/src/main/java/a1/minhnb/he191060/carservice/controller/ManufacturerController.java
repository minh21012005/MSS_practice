package a1.minhnb.he191060.carservice.controller;

import a1.minhnb.he191060.carservice.entity.Manufacturer;
import a1.minhnb.he191060.carservice.repository.ManufacturerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/manufacturers")
public class ManufacturerController {

    @Autowired
    private ManufacturerRepository repository;

    @GetMapping
    public List<Manufacturer> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Manufacturer> create(@RequestHeader(value = "X-User-Role", required = false) String role, @RequestBody Manufacturer entity) {
        if (!"ADMIN".equals(role)) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        return ResponseEntity.ok(repository.save(entity));
    }
}
