package cris_dev.pos_system.TaxRate.Utils;

import cris_dev.pos_system.TaxRate.Model.DTO.Response.TaxRateResponse;
import cris_dev.pos_system.TaxRate.Model.Entity.TaxRateEntity;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class TaxRateTransform {
    public static List<TaxRateResponse> allTaxRates(List<TaxRateEntity> entity) {
        return entity.stream().map(tax -> {
            return new TaxRateResponse(
                    tax.getId(),
                    tax.getRate(),
                    tax.getStatus(),
                    tax.getEffectiveFrom(),
                    tax.getEffectiveTo());
        }).toList();
    }

    public static TaxRateResponse tax(TaxRateEntity entity) {
        return new TaxRateResponse(
                entity.getId(),
                entity.getRate(),
                entity.getStatus(),
                entity.getEffectiveFrom(),
                entity.getEffectiveTo());
    }
}
