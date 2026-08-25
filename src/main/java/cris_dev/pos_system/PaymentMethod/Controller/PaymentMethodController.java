package cris_dev.pos_system.PaymentMethod.Controller;

import cris_dev.pos_system.PaymentMethod.Model.DTO.Request.PaymentMethodCreateRequest;
import cris_dev.pos_system.PaymentMethod.Model.DTO.Request.PaymentMethodPatchRequest;
import cris_dev.pos_system.PaymentMethod.Model.DTO.Response.PaymentMethodIdResponse;
import cris_dev.pos_system.PaymentMethod.Model.DTO.Response.PaymentMethodResponse;
import cris_dev.pos_system.PaymentMethod.Service.PaymentMethodService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("payment_method")
public class PaymentMethodController {
    private final PaymentMethodService paymentMethodService;

    @GetMapping("active")
    public ResponseEntity<List<PaymentMethodResponse>> allPayments() {
        List<PaymentMethodResponse> paymentMethods = paymentMethodService.activePaymentMethods();
        return ResponseEntity.status(HttpStatus.OK).body(paymentMethods);
    }

    @GetMapping("all")
    public ResponseEntity<Page<PaymentMethodResponse>> allPayments(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(
                page - 1,
                size);
        Page<PaymentMethodResponse> paymentMethods = paymentMethodService.allPaymentMethods(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(paymentMethods);
    }

    @GetMapping("{id}")
    public ResponseEntity<PaymentMethodIdResponse> paymentMethodId(@PathVariable Long id) {
        PaymentMethodIdResponse paymentMethod = paymentMethodService.getPaymentMethod(id);
        return ResponseEntity.status(HttpStatus.OK).body(paymentMethod);
    }

    @PostMapping("create")
    public ResponseEntity<PaymentMethodResponse> createPaymentMethod(@Valid @RequestBody PaymentMethodCreateRequest payload) {
        PaymentMethodResponse paymentMethod = paymentMethodService.createPaymentMethod(payload);
        return ResponseEntity.status(HttpStatus.CREATED).body(paymentMethod);
    }

    @PatchMapping("update/{id}")
    public ResponseEntity<PaymentMethodResponse> patchPaymentMethod(
            @PathVariable Long id,
            @Valid @RequestBody PaymentMethodPatchRequest payload) {
        PaymentMethodResponse paymentMethod = paymentMethodService.updatePaymentMethod(
                id,
                payload);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(paymentMethod);
    }

    @PutMapping("soft_delete/{id}")
    public ResponseEntity<String> softDelete(@PathVariable Long id) {
        String msg = paymentMethodService.softDeletePaymentMethod(id);
        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }

    @PutMapping("restore/{id}")
    public ResponseEntity<String> restoreSoftDelete(@PathVariable Long id) {
        String msg = paymentMethodService.restoreSoftDeletePaymentMethod(id);
        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }
}
