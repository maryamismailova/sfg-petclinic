package maryam.petclinic.services.map;

import maryam.petclinic.model.Pet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PetServiceMapTest {

    String petName="Dorian";
    PetServiceMap petServiceMap;

    @BeforeEach
    void setUp() {
        petServiceMap=new PetServiceMap();
        petServiceMap.save(Pet.builder().name(petName).build());
    }

    @Test
    void findAll() {
        assertNotNull(petServiceMap.findAll());
        assertEquals(1, petServiceMap.findAll().size());
    }

    @Test
    void deleteById() {
        petServiceMap.deleteById(1L);
        assertEquals(0, petServiceMap.findAll().size());
    }

    @Test
    void saveWithId() {
        Pet p = Pet.builder().build();
        p.setId(2L);
        Pet savedPet = petServiceMap.save(p);
        assertEquals(2L, savedPet.getId());
        assertEquals(2, petServiceMap.findAll().size());
    }
    @Test
    void saveWithoutId() {
        Pet p = Pet.builder().build();
        Pet savedPet = petServiceMap.save(p);
        assertNotNull(savedPet);
        assertNotNull(savedPet.getId());
    }

    @Test
    void saveWithSameId(){
        Pet p = Pet.builder().name("Basya").build();
        p.setId(1L);
        Pet savedP=petServiceMap.save(p);
        assertNotNull(savedP);
        assertEquals("Basya", savedP.getName());
        assertEquals(1, petServiceMap.findAll().size());

    }


    @Test
    void delete() {
        petServiceMap.delete(petServiceMap.findById(1L));
        assertEquals(0, petServiceMap.findAll().size());

    }

    @Test
    void findById() {
        Pet foundPet=petServiceMap.findById(1L);
        assertNotNull(foundPet);
        assertEquals(1L, foundPet.getId());
        assertEquals(petName, foundPet.getName());
    }
    @Test
    void findNonExistingId(){
        Pet p = petServiceMap.findById(4L);
        assertNull(p);
    }
}