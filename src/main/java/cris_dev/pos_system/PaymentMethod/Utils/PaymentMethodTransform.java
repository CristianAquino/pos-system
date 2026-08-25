package cris_dev.pos_system.PaymentMethod.Utils;

import cris_dev.pos_system.PaymentMethod.Model.DTO.Response.PaymentMethodIdResponse;
import cris_dev.pos_system.PaymentMethod.Model.DTO.Response.PaymentMethodResponse;
import cris_dev.pos_system.PaymentMethod.Model.Entity.PaymentMethodEntity;

import java.util.List;

public class PaymentMethodTransform {

    public static PaymentMethodResponse paymentMethod(PaymentMethodEntity entity) {
        return new PaymentMethodResponse(
                entity.getId(),
                entity.getName(),
                entity.getStatus());
    }

    public static PaymentMethodIdResponse paymentMethodId(PaymentMethodEntity entity) {
        return new PaymentMethodIdResponse(
                entity.getId(),
                entity.getName(),
                entity.getStatus(),
                entity.getSoftDelete());
    }

    public static List<PaymentMethodResponse> paymentMethodList(List<PaymentMethodEntity> entity) {
        return entity.stream().map(PaymentMethodTransform::paymentMethod).toList();
    }
}
