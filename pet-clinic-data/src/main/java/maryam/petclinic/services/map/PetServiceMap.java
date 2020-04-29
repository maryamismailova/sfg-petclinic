package maryam.petclinic.services.map;

import maryam.petclinic.model.Owner;
import maryam.petclinic.model.Pet;
import maryam.petclinic.services.CrudService;
import maryam.petclinic.services.PetService;

import java.util.Set;

public class PetServiceMap extends AbsctractMapService<Pet, Long> implements PetService {

    @Override
    public Set<Pet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Pet save(Pet object) {
        return super.save(object.getId(), object);
    }

    @Override
    public void delete(Pet object) {
        super.delete(object);
    }

    @Override
    public Pet findById(Long id) {
        return super.findById(id);
    }
}
