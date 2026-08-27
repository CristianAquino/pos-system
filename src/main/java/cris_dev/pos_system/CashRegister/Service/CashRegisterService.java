package cris_dev.pos_system.CashRegister.Service;

import cris_dev.pos_system.CashRegister.Model.DTO.Request.CashRegisterCreateRequest;
import cris_dev.pos_system.CashRegister.Model.DTO.Request.CashRegisterPatchRequest;
import cris_dev.pos_system.CashRegister.Model.DTO.Response.CashRegisterIdResponse;
import cris_dev.pos_system.CashRegister.Model.DTO.Response.CashRegisterResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface CashRegisterService {
    List<CashRegisterResponse> activeCashRegisters();

    Page<CashRegisterResponse> allCashRegisters(Pageable pageable);

    CashRegisterIdResponse getCashRegister(UUID id);

    String softDeleteCashRegister(UUID id);

    String restoreSoftDeleteCashRegister(UUID id);

    CashRegisterResponse createCashRegister(CashRegisterCreateRequest payload);

    CashRegisterResponse updateCashRegister(
            UUID id,
            CashRegisterPatchRequest payload);
}
