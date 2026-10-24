package cris_dev.pos_system.CashRegister.Controller;

import cris_dev.pos_system.CashRegister.Model.DTO.Request.CashRegisterCreateRequest;
import cris_dev.pos_system.CashRegister.Model.DTO.Request.CashRegisterPatchRequest;
import cris_dev.pos_system.CashRegister.Model.DTO.Response.CashRegisterIdResponse;
import cris_dev.pos_system.CashRegister.Model.DTO.Response.CashRegisterResponse;
import cris_dev.pos_system.CashRegister.Service.CashRegisterService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("cash_register")
public class CashRegisterController {
    private final CashRegisterService cashRegisterService;

    @GetMapping("all")
    public ResponseEntity<List<CashRegisterResponse>> allCashRegisters(
            @RequestParam(required = false) @Pattern(regexp = "^[aiAI]$") String status) {
        List<CashRegisterResponse> cashRegisters = cashRegisterService.allCashRegisters(status);
        return ResponseEntity.status(HttpStatus.OK).body(cashRegisters);
    }

    @GetMapping("all/soft_delete")
    public ResponseEntity<List<CashRegisterResponse>> allPaymentsSoftDelete() {
        List<CashRegisterResponse> cashRegisters = cashRegisterService.allCashRegistersSoftDelete();
        return ResponseEntity.status(HttpStatus.OK).body(cashRegisters);
    }

    @GetMapping("{id}")
    public ResponseEntity<CashRegisterIdResponse> cashRegisterId(@PathVariable UUID id) {
        CashRegisterIdResponse cashRegister = cashRegisterService.getCashRegister(id);
        return ResponseEntity.status(HttpStatus.OK).body(cashRegister);
    }

    @PostMapping("create")
    public ResponseEntity<CashRegisterResponse> createCashRegister(@Valid @RequestBody CashRegisterCreateRequest payload) {
        CashRegisterResponse cashRegister = cashRegisterService.createCashRegister(payload);
        return ResponseEntity.status(HttpStatus.CREATED).body(cashRegister);
    }

    @PatchMapping("update/{id}/name")
    public ResponseEntity<CashRegisterResponse> patchCashRegisterName(
            @PathVariable UUID id,
            @Valid @RequestBody CashRegisterPatchRequest payload) {
        CashRegisterResponse cashRegister = cashRegisterService.updateCashRegister(
                id,
                payload);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(cashRegister);
    }

    @PatchMapping("update/{id}/status")
    public ResponseEntity<CashRegisterResponse> patchCashRegisterStatus(
            @PathVariable UUID id) {
        CashRegisterResponse cashRegister = cashRegisterService.updateCashRegister(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(cashRegister);
    }
    
    @PutMapping("soft_delete/{id}")
    public ResponseEntity<String> softDelete(@PathVariable UUID id) {
        String msg = cashRegisterService.softDeleteCashRegister(id);
        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }

    @PutMapping("restore/{id}")
    public ResponseEntity<String> restoreSoftDelete(@PathVariable UUID id) {
        String msg = cashRegisterService.restoreSoftDeleteCashRegister(id);
        return ResponseEntity.status(HttpStatus.OK).body(msg);
    }
}
