package cris_dev.pos_system.PaymentMethod.Service.Impl;

import cris_dev.pos_system.PaymentMethod.Model.DTO.Request.PaymentMethodCreateRequest;
import cris_dev.pos_system.PaymentMethod.Model.DTO.Request.PaymentMethodPatchRequest;
import cris_dev.pos_system.PaymentMethod.Model.DTO.Response.PaymentMethodIdResponse;
import cris_dev.pos_system.PaymentMethod.Model.DTO.Response.PaymentMethodResponse;
import cris_dev.pos_system.PaymentMethod.Model.Entity.PaymentMethodEntity;
import cris_dev.pos_system.PaymentMethod.Repository.PaymentMethodRepository;
import cris_dev.pos_system.PaymentMethod.Service.PaymentMethodService;
import cris_dev.pos_system.PaymentMethod.Utils.PaymentMethodTransform;
import cris_dev.pos_system.Sale.Repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentMethodServiceImpl implements PaymentMethodService {

    private final PaymentMethodRepository paymentMethodRepository;
    private final SaleRepository saleRepository;

    @Override
    public List<PaymentMethodResponse> activePaymentMethods() {
        List<PaymentMethodEntity> payments = paymentMethodRepository.findByStatusAndSoftDeleteIsNull("A");
        return PaymentMethodTransform.paymentMethodList(payments);
    }

    @Override
    public Page<PaymentMethodResponse> allPaymentMethods(Pageable pageable) {
        return paymentMethodRepository.findBySoftDeleteIsNull(pageable).map(PaymentMethodTransform::paymentMethod);
    }

    @Override
    public PaymentMethodIdResponse getPaymentMethod(Long id) {
        PaymentMethodEntity payment = paymentMethodRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "metodo de pago no encontrado"));

        return PaymentMethodTransform.paymentMethodId(payment);
    }

    @Transactional
    @Override
    public String softDeletePaymentMethod(Long id) {

        if (!paymentMethodRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se pudo encontrar el metodo de pago");
        }

        int affected = paymentMethodRepository.softDelete(id);

        if (affected == 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "No se pudo realizar la eliminacion del metodo de pago ");
        }

        return "Se realizo la eliminacion del metodo de pago ";
    }

    @Transactional
    @Override
    public String restoreSoftDeletePaymentMethod(Long id) {
        if (!paymentMethodRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se pudo encontrar el metodo de pago");
        }

        int affected = paymentMethodRepository.restoreSoftDelete(id);

        if (affected == 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "No se pudo realizar la restauracion del metodo de pago ");
        }

        return "Se realizo la restauracion del metodo de pago ";
    }

    @Transactional
    @Override
    public PaymentMethodResponse createPaymentMethod(PaymentMethodCreateRequest payload) {
        String _payment = payload.name().trim().toUpperCase();

        if (paymentMethodRepository.existsByName(_payment)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "metodo de pago " + payload.name() + " ya existe");
        }

        PaymentMethodEntity payment = new PaymentMethodEntity();

        payment.setName(_payment);

        PaymentMethodEntity newPayment = paymentMethodRepository.save(payment);

        return PaymentMethodTransform.paymentMethod(newPayment);
    }

    @Transactional
    @Override
    public PaymentMethodResponse updatePaymentMethod(
            Long id,
            PaymentMethodPatchRequest payload) {
        PaymentMethodEntity payment = paymentMethodRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "No se pudo encontrar el metodo de pago"));

        if (payload.name() != null) {
            if (payload.name().trim().toUpperCase().equals(payment.getName())) {
                throw new ResponseStatusException(
                        HttpStatus.CONFLICT,
                        "metodo de pago " + payment.getName() + " es el mismo.");
            }

            boolean used = saleRepository.existsByPaymentMethodId(id);

            if (used) {
                throw new ResponseStatusException(
                        HttpStatus.CONFLICT,
                        "metodo de pago " + payment.getName() + " ya esta siendo " + "utilizado por ello no puede realizar modificaciones en su nombre");
            }

            payment.setName(payload.name().trim().toUpperCase());

        }

        if (payload.status() != null) {
            if (payload.status().trim().toUpperCase().equals(payment.getStatus())) {
                throw new ResponseStatusException(
                        HttpStatus.CONFLICT,
                        "metodo de pago " + payment.getName() + " su estado " + "es el mismo.");
            }
            payment.setStatus(payload.status().trim().toUpperCase());
        }

        PaymentMethodEntity nPayment = paymentMethodRepository.save(payment);

        return PaymentMethodTransform.paymentMethod(nPayment);
    }
}
