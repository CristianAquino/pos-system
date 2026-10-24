package cris_dev.pos_system.CashRegister.Service;

import cris_dev.pos_system.CashRegister.Model.DTO.Request.CashRegisterCreateRequest;
import cris_dev.pos_system.CashRegister.Model.DTO.Request.CashRegisterPatchRequest;
import cris_dev.pos_system.CashRegister.Model.DTO.Response.CashRegisterIdResponse;
import cris_dev.pos_system.CashRegister.Model.DTO.Response.CashRegisterResponse;

import java.util.List;
import java.util.UUID;

public interface CashRegisterService {

    List<CashRegisterResponse> allCashRegisters(String status);

    List<CashRegisterResponse> allCashRegistersSoftDelete();

    CashRegisterIdResponse getCashRegister(UUID id);

    CashRegisterResponse createCashRegister(CashRegisterCreateRequest payload);

    CashRegisterResponse updateCashRegisterName(
            UUID id,
            CashRegisterPatchRequest payload);

    CashRegisterResponse updateCashRegisterStatus(
            UUID id);

    String softDeleteCashRegister(UUID id);

    String restoreSoftDeleteCashRegister(UUID id);


}
