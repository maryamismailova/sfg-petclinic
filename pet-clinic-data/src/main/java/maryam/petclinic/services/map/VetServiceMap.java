package maryam.petclinic.services.map;

import maryam.petclinic.model.Speciality;
import maryam.petclinic.model.Vet;
import maryam.petclinic.services.SpecialitiesService;
import maryam.petclinic.services.VetService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class VetServiceMap extends AbsctractMapService<Vet, Long> implements VetService {

    private final SpecialitiesService specialitiesService;

    public VetServiceMap(SpecialitiesService specialitiesService) {
        this.specialitiesService = specialitiesService;
    }

    @Override
    public Set<Vet> findAll() {
        return super.findAll();
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Vet save(Vet object) {

        if(object.getSpecialities().size()>0){
            object.getSpecialities().forEach(speciality -> {
                if(speciality.getId() == null){
                    Speciality savedSpeciality=specialitiesService.save(speciality);
                    speciality.setId(savedSpeciality.getId());
                }
            });
        }
        return super.save(object);
    }

    @Override
    public void delete(Vet object) {
        super.delete(object);
    }

    @Override
    public Vet findById(Long id) {
        return super.findById(id);
    }
}
