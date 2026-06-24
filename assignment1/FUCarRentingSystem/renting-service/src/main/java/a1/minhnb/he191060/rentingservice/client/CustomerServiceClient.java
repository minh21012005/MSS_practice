package a1.minhnb.he191060.rentingservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "CUSTOMER-SERVICE", path = "/api/customers")
public interface CustomerServiceClient {
    @GetMapping("/profile")
    CustomerDTO getCustomerProfile(@RequestHeader("X-User-Role") String role, @RequestHeader("X-User-Email") String email);
}
