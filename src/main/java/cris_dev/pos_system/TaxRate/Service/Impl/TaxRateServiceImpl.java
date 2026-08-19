package cris_dev.pos_system.TaxRate.Service.Impl;

import cris_dev.pos_system.TaxRate.Model.DTO.Request.TaxRateCreateRequest;
import cris_dev.pos_system.TaxRate.Model.DTO.Response.TaxRateResponse;
import cris_dev.pos_system.TaxRate.Model.Entity.TaxRateEntity;
import cris_dev.pos_system.TaxRate.Repository.TaxRateRepository;
import cris_dev.pos_system.TaxRate.Service.TaxRateService;
import cris_dev.pos_system.TaxRate.Utils.TaxRateTransform;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaxRateServiceImpl implements TaxRateService {

    private final TaxRateRepository taxRateRepository;
    private TaxRateTransform taxRateTransform;

    @Override
    public List<TaxRateResponse> history() {
        List<TaxRateEntity> tax = taxRateRepository.findAllByOrderByEffectiveToDesc();
        return taxRateTransform.allTaxRates(tax);
    }

    @Override
    public TaxRateResponse current() {
        TaxRateEntity current = taxRateRepository.findByStatusAndEffectiveToIsNull("A").orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "No hay tax en vigencia actualmente"));
        return taxRateTransform.tax(current);
    }

    @Override
    public TaxRateResponse tax(Long id) {
        TaxRateEntity tax = taxRateRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Tax no existe"));
        return taxRateTransform.tax(tax);
    }

    @Override
    public TaxRateResponse create(TaxRateCreateRequest payload) {
        TaxRateEntity tax = new TaxRateEntity();

        tax.setRate(payload.rate());
        TaxRateEntity resp = taxRateRepository.save(tax);

        return TaxRateTransform.tax(resp);
    }
}
