package maryam.petclinic.services.springdatajpa;

import maryam.petclinic.model.Owner;
import maryam.petclinic.repositories.OwnerRepository;
import maryam.petclinic.repositories.PetRepository;
import maryam.petclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    public static final String LASTNAME = "Smith";
    @InjectMocks
    OwnerSDJpaService ownerSDJpaService;
    @Mock
    OwnerRepository ownerRepository;
    @Mock
    PetRepository petRepository;
    @Mock
    PetTypeRepository petTypeRepository;

    @BeforeEach
    void setUp() {

    }

    @Test
    void findByLastName() {
        Owner returnOwner = Owner.builder().id(1L).lastname(LASTNAME).build();

        when(ownerRepository.findByLastname(any())).thenReturn(returnOwner);

        Owner smith = ownerSDJpaService.findByLastName(LASTNAME);

        assertEquals(LASTNAME, smith.getLastname());
        verify(ownerRepository).findByLastname(any());
    }

    @Test
    void findAll() {
        Set<Owner> returnOwners = new HashSet<>();
        returnOwners.add(Owner.builder().id(1L).build());
        returnOwners.add(Owner.builder().id(2L).build());
        returnOwners.add(Owner.builder().id(3L).build());

        when(ownerRepository.findAll()).thenReturn(returnOwners);

        Set<Owner> allOwners = ownerSDJpaService.findAll();

        assertNotNull(allOwners);
        assertEquals(3, allOwners.size());

    }

    @Test
    void findById() {
        Owner returnOwner = Owner.builder().id(1L).build();

        when(ownerRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(returnOwner));

        Owner owner = ownerSDJpaService.findById(1L);

        assertNotNull(owner);
        assertEquals(1L, owner.getId());
        verify(ownerRepository, times(1)).findById(1L);
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(1L)).thenReturn(java.util.Optional.ofNullable(null));

        Owner owner = ownerSDJpaService.findById(1L);

        assertNull(owner);
        verify(ownerRepository, times(1)).findById(1L);
    }

    @Test
    void save() {
        Owner savedOwner = Owner.builder().id(1L).build();

        when(ownerRepository.save(savedOwner)).thenReturn(savedOwner);

        Owner owner = ownerSDJpaService.save(savedOwner);
        assertNotNull(owner);
        assertEquals(1L, owner.getId());
    }

    @Test
    void delete() {
        Owner toDelete = Owner.builder().id(1L).build();

        ownerSDJpaService.delete(toDelete);

        verify(ownerRepository, times(1)).delete(toDelete);

    }

    @Test
    void deleteById() {
        Owner toDelete = Owner.builder().id(1L).build();

        ownerSDJpaService.deleteById(1L);

        verify(ownerRepository, times(1)).deleteById(1L);


    }
}