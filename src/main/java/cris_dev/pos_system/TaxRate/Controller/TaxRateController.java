package cris_dev.pos_system.TaxRate.Controller;

import cris_dev.pos_system.TaxRate.Model.DTO.Request.TaxRateCreateRequest;
import cris_dev.pos_system.TaxRate.Model.DTO.Response.TaxRateResponse;
import cris_dev.pos_system.TaxRate.Service.TaxRateService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("tax")
public class TaxRateController {

    private final TaxRateService taxRateService;

    @GetMapping("history")
    public ResponseEntity<List<TaxRateResponse>> taxHistory() {
        List<TaxRateResponse> taxes = taxRateService.history();
        return ResponseEntity.status(HttpStatus.OK).body(taxes);
    }

    @GetMapping("current")
    public ResponseEntity<TaxRateResponse> currentTax() {
        TaxRateResponse current = taxRateService.current();
        return ResponseEntity.status(HttpStatus.OK).body(current);
    }

    @GetMapping("{id}")
    public ResponseEntity<TaxRateResponse> tax(@PathVariable() Long id) {
        TaxRateResponse tax = taxRateService.tax(id);
        return ResponseEntity.status(HttpStatus.OK).body(tax);
    }

    @PostMapping("create")
    public ResponseEntity<TaxRateResponse> createTax(
            @Valid @RequestBody TaxRateCreateRequest payload) {
        TaxRateResponse resp = taxRateService.create(payload);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }
}
