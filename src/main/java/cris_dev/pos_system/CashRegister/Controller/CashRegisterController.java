package cris_dev.pos_system.CashRegister.Controller;

import cris_dev.pos_system.CashRegister.Model.DTO.Request.CashRegisterCreateRequest;
import cris_dev.pos_system.CashRegister.Model.DTO.Request.CashRegisterPatchRequest;
import cris_dev.pos_system.CashRegister.Model.DTO.Response.CashRegisterIdResponse;
import cris_dev.pos_system.CashRegister.Model.DTO.Response.CashRegisterResponse;
import cris_dev.pos_system.CashRegister.Service.CashRegisterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    @GetMapping("active")
    public ResponseEntity<List<CashRegisterResponse>> allActiveCashRegisters() {
        List<CashRegisterResponse> cashRegister = cashRegisterService.activeCashRegisters();
        return ResponseEntity.status(HttpStatus.OK).body(cashRegister);
    }

    @GetMapping("all")
    public ResponseEntity<Page<CashRegisterResponse>> allCashRegisters(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(
                page - 1,
                size);
        Page<CashRegisterResponse> cashRegister = cashRegisterService.allCashRegisters(pageable);
        return ResponseEntity.status(HttpStatus.OK).body(cashRegister);
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

    @PatchMapping("update/{id}")
    public ResponseEntity<CashRegisterResponse> patchCashRegister(
            @PathVariable UUID id,
            @Valid @RequestBody CashRegisterPatchRequest payload) {
        CashRegisterResponse cashRegister = cashRegisterService.updateCashRegister(
                id,
                payload);
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
