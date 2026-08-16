package cris_dev.pos_system.TaxRate.Service;

import cris_dev.pos_system.TaxRate.Model.DTO.Request.TaxRateCreateRequest;
import cris_dev.pos_system.TaxRate.Model.DTO.Response.TaxRateResponse;

import java.util.List;

public interface TaxRateService {
    public List<TaxRateResponse> history();

    public TaxRateResponse current();

    public TaxRateResponse tax(Long id);

    public TaxRateResponse create(TaxRateCreateRequest payload);
}
