package maryam.petclinic.repositories;

import maryam.petclinic.model.Owner;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface OwnerRepository extends CrudRepository<Owner, Long> {
    Owner findByLastname(String lastname);

    List<Owner> findAllByLastnameLike(String lastname);
    List<Owner> findAllByLastnameLikeIgnoreCase(String lastname);

}
