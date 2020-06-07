package maryam.petclinic.services;

import maryam.petclinic.model.Owner;

import java.util.List;
import java.util.Set;

public interface OwnerService extends CrudService<Owner, Long>{
    Owner findByLastName(String lastname);

    List<Owner> findAllByLastNameLike(String lastname);

}
