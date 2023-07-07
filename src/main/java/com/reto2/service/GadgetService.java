package com.reto2.service;

import com.reto2.model.Gadget;
import com.reto2.repository.GadgetRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author DAVID BELTRAN 
 */
@Service
public class GadgetService {

    @Autowired
    private GadgetRepository repositorio;

    public List<Gadget> listAll() {
        return repositorio.listAll();
    }

    public Optional<Gadget> getSupplement(String reference) {
        return repositorio.getGadget(reference);
    }

    public Gadget create(Gadget gadget) {
        if (gadget.getId()== null) {
            return gadget;
        } else {
            return repositorio.create(gadget);
        }
    }

    public Gadget update(Gadget gadget) {

        if (gadget.getId()!= null) {
            Optional<Gadget> supplementDb = repositorio.getGadget(gadget.getId());
            if (!supplementDb.isEmpty()) {
                if (gadget.getBrand() != null) {
                    supplementDb.get().setBrand(gadget.getBrand());
                }
                if (gadget.getCategory() != null) {
                    supplementDb.get().setCategory(gadget.getCategory());
                }

                if (gadget.getDescription() != null) {
                    supplementDb.get().setDescription(gadget.getDescription());
                }
                if (gadget.getPrice() != 0.0) {
                    supplementDb.get().setPrice(gadget.getPrice());
                }
                if (gadget.getQuantity() != 0) {
                    supplementDb.get().setQuantity(gadget.getQuantity());
                }
                if (gadget.getPhotography() != null) {
                    supplementDb.get().setPhotography(gadget.getPhotography());
                }
                supplementDb.get().setAvailability(gadget.isAvailability());
                repositorio.update(supplementDb.get());
                return supplementDb.get();
            } else {
                return gadget;
            }
        } else {
            return gadget;
        }
    }

    public boolean delete(String id) {
        Boolean aBoolean = getSupplement(id).map(supplement -> {
            repositorio.delete(supplement);
            return true;
        }).orElse(false);
        return aBoolean;
    }
}
