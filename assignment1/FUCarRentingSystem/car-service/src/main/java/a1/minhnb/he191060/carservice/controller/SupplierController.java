package a1.minhnb.he191060.carservice.controller;

import a1.minhnb.he191060.carservice.entity.Supplier;
import a1.minhnb.he191060.carservice.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {

    @Autowired
    private SupplierRepository repository;

    @GetMapping
    public List<Supplier> getAll() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Supplier> create(@RequestHeader(value = "X-User-Role", required = false) String role, @RequestBody Supplier entity) {
        if (!"ADMIN".equals(role)) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        return ResponseEntity.ok(repository.save(entity));
    }
}
