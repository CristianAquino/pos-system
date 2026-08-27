package cris_dev.pos_system.CashRegister.Utils;

import cris_dev.pos_system.CashRegister.Model.DTO.Response.CashRegisterIdResponse;
import cris_dev.pos_system.CashRegister.Model.DTO.Response.CashRegisterResponse;
import cris_dev.pos_system.CashRegister.Model.Entity.CashRegisterEntity;

import java.util.List;

public class CashRegisterTransform {

    public static CashRegisterResponse cashRegister(CashRegisterEntity entity) {
        return new CashRegisterResponse(
                entity.getId(),
                entity.getName(),
                entity.getStatus());
    }

    public static CashRegisterIdResponse cashRegisterId(CashRegisterEntity entity) {
        return new CashRegisterIdResponse(
                entity.getId(),
                entity.getName(),
                entity.getStatus(),
                entity.getSoftDelete());
    }

    public static List<CashRegisterResponse> cashRegisterList(List<CashRegisterEntity> entity) {
        return entity.stream().map(CashRegisterTransform::cashRegister).toList();
    }
}
