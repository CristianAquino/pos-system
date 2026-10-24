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
    public List<CashRegisterResponse> allCashRegisters(String status) {
        if (status == null) {
            return CashRegisterTransform.cashRegisterList(cashRegisterRepository.findBySoftDeleteIsNullOrderByStatusDesc());
        }
        String nStatus = status.trim().toUpperCase();
        return CashRegisterTransform.cashRegisterList(cashRegisterRepository.findByStatusAndSoftDeleteIsNull(nStatus));
    }

    @Override
    public List<CashRegisterResponse> allCashRegistersSoftDelete() {
        return CashRegisterTransform.cashRegisterList(cashRegisterRepository.findBySoftDeleteIsNotNull());
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
    public CashRegisterResponse updateCashRegisterName(
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

        return CashRegisterTransform.cashRegister(cashRegister);
    }

    @Transactional
    @Override
    public CashRegisterResponse updateCashRegisterStatus(
            UUID id) {
        CashRegisterEntity cashRegister = cashRegisterRepository.findById(id).orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "No se pudo encontrar la caja registradora"));

        boolean used = cashOpeningRepository.existsByCashRegisterIdAndStatus(
                id,
                "A");

        if (used) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Caja registradora esta aperturada, cierrela " + "antes de realizar este cambio.");
        }

        if (cashRegister.getStatus().equals("A")) {
            cashRegister.setStatus("I");
        } else {
            cashRegister.setStatus("A");
        }

        return CashRegisterTransform.cashRegister(cashRegister);
    }

    @Transactional
    @Override
    public String softDeleteCashRegister(UUID id) {
        if (!cashRegisterRepository.existsByIdAndSoftDeleteIsNull(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se pudo encontrar la caja registradora o la caja " + "registradora ya se encuentra eliminada");
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
        if (!cashRegisterRepository.existsByIdAndSoftDeleteIsNotNull(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "No se pudo encontrar la caja registradora o la caja " + "registradora ya esta restaurada");
        }

        int affected = cashRegisterRepository.restoreSoftDelete(id);

        if (affected == 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "No se pudo realizar la restauracion de la caja registradora");
        }

        return "Se realizo la restauracion de la caja registradora";
    }
}
