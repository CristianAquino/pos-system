package cris_dev.pos_system.PaymentMethod.Controller;

import cris_dev.pos_system.PaymentMethod.Model.DTO.Request.PaymentMethodCreateRequest;
import cris_dev.pos_system.PaymentMethod.Model.DTO.Request.PaymentMethodPatchRequest;
import cris_dev.pos_system.PaymentMethod.Model.DTO.Response.PaymentMethodIdResponse;
import cris_dev.pos_system.PaymentMethod.Model.DTO.Response.PaymentMethodResponse;
import cris_dev.pos_system.PaymentMethod.Service.PaymentMethodService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("payment_method")
public class PaymentMethodController {
    private final PaymentMethodService paymentMethodService;

    // para el usuario
    @GetMapping("active")
    public ResponseEntity<List<PaymentMethodResponse>> allActivePayments() {
        List<PaymentMethodResponse> paymentMethods = paymentMethodService.activePaymentMethods();
        return ResponseEntity.status(HttpStatus.OK).body(paymentMethods);
    }

    // administrador
    @GetMapping("all")
    public ResponseEntity<List<PaymentMethodResponse>> allPayments(@RequestParam(required = false) @Pattern(regexp = "^[aiAI]$") String status) {
        List<PaymentMethodResponse> paymentMethods = paymentMethodService.allPaymentMethods(status);
        return ResponseEntity.status(HttpStatus.OK).body(paymentMethods);
    }

    @GetMapping("all/soft_delete")
    public ResponseEntity<List<PaymentMethodResponse>> allPaymentsSoftDelete() {
        List<PaymentMethodResponse> paymentMethods = paymentMethodService.allPaymentMethodsSoftDelete();
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

    @PatchMapping("update/{id}/name")
    public ResponseEntity<PaymentMethodResponse> patchPaymentMethodName(
            @PathVariable Long id,
            @Valid @RequestBody PaymentMethodPatchRequest payload) {
        PaymentMethodResponse paymentMethod = paymentMethodService.updatePaymentMethodName(
                id,
                payload);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(paymentMethod);
    }

    @PatchMapping("update/{id}/status")
    public ResponseEntity<PaymentMethodResponse> patchPaymentMethodStatus(
            @PathVariable Long id) {
        PaymentMethodResponse paymentMethod = paymentMethodService.updatePaymentMethodStatus(id);
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
