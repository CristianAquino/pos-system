package cris_dev.pos_system.CashRegister.Service.Impl;

import cris_dev.pos_system.CashOpening.Repository.CashOpeningRepository;
import cris_dev.pos_system.CashRegister.Model.DTO.Request.CashRegisterCreateRequest;
import cris_dev.pos_system.CashRegister.Model.DTO.Request.CashRegisterPatchRequest;
import cris_dev.pos_system.CashRegister.Model.DTO.Response.CashRegisterIdResponse;
import cris_dev.pos_system.CashRegister.Model.DTO.Response.CashRegisterResponse;
import cris_dev.pos_system.CashRegister.Model.Entity.CashRegisterEntity;
import cris_dev.pos_system.CashRegister.Repository.CashRegisterRepository;
import cris_dev.pos_system.CashRegister.Service.CashRegisterService;
import cris_dev.pos_system.CashRegister.Utils.CashRegisterTransform;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CashRegisterServiceImpl implements CashRegisterService {
    private final CashRegisterRepository cashRegisterRepository;
    private final CashOpeningRepository cashOpeningRepository;

    @Override
    public List<CashRegisterResponse> activeCashRegisters() {
        List<CashRegisterEntity> cashRegisters = cashRegisterRepository.findByStatusAndSoftDeleteIsNull("A");
        return CashRegisterTransform.cashRegisterList(cashRegisters);
    }

    @Override
    public Page<CashRegisterResponse> allCashRegisters(Pageable pageable) {
        return cashRegisterRepository.findBySoftDeleteIsNull(pageable).map(CashRegisterTransform::cashRegister);
    }

    @Override
    public CashRegisterIdResponse getCashRegister(UUID id) {
        CashRegisterEntity cashRegister = cashRegisterRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Caja registradora no encontrada"));

        return CashRegisterTransform.cashRegisterId(cashRegister);
    }

    @Transactional
    @Override
    public String softDeleteCashRegister(UUID id) {
        if (!cashRegisterRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se pudo encontrar la caja registradora");
        }

        int affected = cashRegisterRepository.softDelete(id);

        if (affected == 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "No se pudo realizar la eliminacion de la caja registradora");
        }

        return "Se realizo la eliminacion de la caja registradora";
    }

    @Transactional
    @Override
    public String restoreSoftDeleteCashRegister(UUID id) {
        if (!cashRegisterRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se pudo encontrar la caja registradora");
        }

        int affected = cashRegisterRepository.restoreSoftDelete(id);

        if (affected == 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "No se pudo realizar la restauracion de la caja registradora");
        }

        return "Se realizo la restauracion de la caja registradora";
    }

    @Transactional
    @Override
    public CashRegisterResponse createCashRegister(CashRegisterCreateRequest payload) {
        String _cash = payload.name().trim().toUpperCase();

        if (cashRegisterRepository.existsByName(_cash)) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Caja registradora " + payload.name() + " ya existe");
        }

        CashRegisterEntity cashRegister = new CashRegisterEntity();

        cashRegister.setName(_cash);

        CashRegisterEntity newCashRegister = cashRegisterRepository.save(cashRegister);

        return CashRegisterTransform.cashRegister(newCashRegister);
    }

    @Transactional
    @Override
    public CashRegisterResponse updateCashRegister(
            UUID id,
            CashRegisterPatchRequest payload) {
        CashRegisterEntity cashRegister = cashRegisterRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "No se pudo encontrar la caja registradora"));

        if (payload.name() != null) {
            String _name = payload.name().trim().toUpperCase();
            if (_name.equals(cashRegister.getName())) {
                throw new ResponseStatusException(
                        HttpStatus.CONFLICT,
                        "caja registradora " + cashRegister.getName() + " es el mismo.");
            }

            boolean used = cashOpeningRepository.existsByCashRegisterId(id);

            if (used) {
                throw new ResponseStatusException(
                        HttpStatus.CONFLICT,
                        "Caja registradora " + payload.name() + " ya esta " + "siendo " + "utilizado por ello no puede realizar modificaciones en su nombre");
            }

            cashRegister.setName(_name);
        }

        if (payload.status() != null) {
            String _status = payload.status().trim().toUpperCase();
            if (_status.equals(cashRegister.getStatus())) {
                throw new ResponseStatusException(
                        HttpStatus.CONFLICT,
                        "caja registradora " + cashRegister.getName() + " su estado es el mismo.");
            }
            boolean used = cashOpeningRepository.existsByCashRegisterIdAndStatus(
                    id,
                    "A");

            if (used) {
                throw new ResponseStatusException(
                        HttpStatus.CONFLICT,
                        "Caja registradora esta aperturada, cierrela " + "antes de realizar este cambio.");
            }
            cashRegister.setStatus(_status);
        }

        return CashRegisterTransform.cashRegister(cashRegister);
    }
}
