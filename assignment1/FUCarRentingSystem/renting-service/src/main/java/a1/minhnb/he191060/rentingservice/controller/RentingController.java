package a1.minhnb.he191060.rentingservice.controller;

import a1.minhnb.he191060.rentingservice.client.CarDTO;
import a1.minhnb.he191060.rentingservice.client.CarServiceClient;
import a1.minhnb.he191060.rentingservice.client.CustomerDTO;
import a1.minhnb.he191060.rentingservice.client.CustomerServiceClient;
import a1.minhnb.he191060.rentingservice.entity.RentingDetail;
import a1.minhnb.he191060.rentingservice.entity.RentingTransaction;
import a1.minhnb.he191060.rentingservice.repository.RentingTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/rentings")
public class RentingController {

    @Autowired
    private RentingTransactionRepository transactionRepository;

    @Autowired
    private CarServiceClient carServiceClient;

    @Autowired
    private CustomerServiceClient customerServiceClient;

    @PostMapping
    public ResponseEntity<RentingTransaction> createTransaction(
            @RequestHeader(value = "X-User-Role", required = false) String role,
            @RequestHeader(value = "X-User-Email", required = false) String email,
            @RequestBody RentingTransaction request) {
        
        if (!"CUSTOMER".equals(role)) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        
        CustomerDTO customer = customerServiceClient.getCustomerProfile("CUSTOMER", email);
        if (customer == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        request.setCustomerId(customer.getCustomerId());
        request.setRentingDate(new Date());
        
        // Temporarily null details to save transaction first
        List<RentingDetail> details = request.getDetails();
        request.setDetails(null);
        RentingTransaction saved = transactionRepository.save(request);

        BigDecimal total = BigDecimal.ZERO;
        
        if (details != null && !details.isEmpty()) {
            for (RentingDetail detail : details) {
                CarDTO car = carServiceClient.getCarById(detail.getCarId());
                if (car == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
                
                long days = ChronoUnit.DAYS.between(
                    detail.getStartDate().toInstant(), 
                    detail.getEndDate().toInstant()
                );
                if(days <= 0) days = 1;
                
                BigDecimal price = car.getCarRentingPricePerDay().multiply(BigDecimal.valueOf(days));
                detail.setPrice(price);
                total = total.add(price);
                
                detail.setRentingTransactionId(saved.getRentingTransactionId());
                detail.setTransaction(saved);
            }
            saved.setDetails(details);
        }
        
        saved.setTotalPrice(total);
        saved.setRentingStatus(1); // 1 = Active/Pending
        
        saved = transactionRepository.save(saved);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/history")
    public ResponseEntity<List<RentingTransaction>> getHistory(
            @RequestHeader(value = "X-User-Role", required = false) String role,
            @RequestHeader(value = "X-User-Email", required = false) String email) {
        
        if (!"CUSTOMER".equals(role)) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        
        CustomerDTO customer = customerServiceClient.getCustomerProfile("CUSTOMER", email);
        if (customer == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        return ResponseEntity.ok(transactionRepository.findByCustomerId(customer.getCustomerId()));
    }

    @GetMapping("/report")
    public ResponseEntity<List<RentingTransaction>> getReport(
            @RequestHeader(value = "X-User-Role", required = false) String role,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        
        if (!"ADMIN".equals(role)) return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        return ResponseEntity.ok(transactionRepository.findByRentingDateBetweenOrderByRentingDateDesc(startDate, endDate));
    }
}
