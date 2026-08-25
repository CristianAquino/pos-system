package cris_dev.pos_system.PaymentMethod.Service;

import cris_dev.pos_system.PaymentMethod.Model.DTO.Request.PaymentMethodCreateRequest;
import cris_dev.pos_system.PaymentMethod.Model.DTO.Request.PaymentMethodPatchRequest;
import cris_dev.pos_system.PaymentMethod.Model.DTO.Response.PaymentMethodIdResponse;
import cris_dev.pos_system.PaymentMethod.Model.DTO.Response.PaymentMethodResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PaymentMethodService {
    List<PaymentMethodResponse> activePaymentMethods();

    Page<PaymentMethodResponse> allPaymentMethods(Pageable pageable);

    PaymentMethodIdResponse getPaymentMethod(Long id);

    String softDeletePaymentMethod(Long id);

    String restoreSoftDeletePaymentMethod(Long id);

    PaymentMethodResponse createPaymentMethod(PaymentMethodCreateRequest payload);

    PaymentMethodResponse updatePaymentMethod(
            Long id,
            PaymentMethodPatchRequest payload);

}
