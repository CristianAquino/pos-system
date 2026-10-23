package cris_dev.pos_system.PaymentMethod.Service;

import cris_dev.pos_system.PaymentMethod.Model.DTO.Request.PaymentMethodCreateRequest;
import cris_dev.pos_system.PaymentMethod.Model.DTO.Request.PaymentMethodPatchRequest;
import cris_dev.pos_system.PaymentMethod.Model.DTO.Response.PaymentMethodIdResponse;
import cris_dev.pos_system.PaymentMethod.Model.DTO.Response.PaymentMethodResponse;

import java.util.List;

public interface PaymentMethodService {
    List<PaymentMethodResponse> activePaymentMethods();

    List<PaymentMethodResponse> allPaymentMethods(String status);

    List<PaymentMethodResponse> allPaymentMethodsSoftDelete();

    PaymentMethodIdResponse getPaymentMethod(Long id);

    PaymentMethodResponse createPaymentMethod(PaymentMethodCreateRequest payload);

    PaymentMethodResponse updatePaymentMethodName(
            Long id,
            PaymentMethodPatchRequest payload);

    PaymentMethodResponse updatePaymentMethodStatus(
            Long id);

    String softDeletePaymentMethod(Long id);

    String restoreSoftDeletePaymentMethod(Long id);
}
