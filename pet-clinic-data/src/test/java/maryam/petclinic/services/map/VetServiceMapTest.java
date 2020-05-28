package maryam.petclinic.services.map;

import maryam.petclinic.model.Vet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VetServiceMapTest {

    VetServiceMap vetServiceMap;

    @BeforeEach
    void setUp() {
        vetServiceMap = new VetServiceMap(new SpecialitiesServiceMap());
        Vet v = new Vet();v.setId(1L);
        vetServiceMap.save(v);
    }

    @Test
    void findAll() {
        assertEquals(1, vetServiceMap.findAll().size());
    }

    @Test
    void deleteById() {
        vetServiceMap.deleteById(1L);
        assertEquals(0, vetServiceMap.findAll().size());
    }
    @Test
    void deleteByIdNotExisting() {
        vetServiceMap.deleteById(5L);
        assertEquals(1, vetServiceMap.findAll().size());
    }

    @Test
    void save() {
        Vet v = new Vet();
        Vet savedVet=vetServiceMap.save(v);
        assertNotNull(savedVet);
        assertNotNull(savedVet.getId());
        assertEquals(2, vetServiceMap.findAll().size());
    }

    @Test
    void saveWithExistingId() {
        Vet v = new Vet();v.setId(1L);
        Vet savedVet=vetServiceMap.save(v);
        assertNotNull(savedVet);
        assertNotNull(savedVet.getId());
        assertEquals(1, vetServiceMap.findAll().size());
    }

    @Test
    void delete() {
        Vet v = new Vet();v.setId(1L);
        vetServiceMap.delete(v);
        assertEquals(0, vetServiceMap.findAll().size());
    }

    @Test
    void deleteNotExisting() {
        Vet v = new Vet();v.setId(2L);v.setLastname("Mark");
        vetServiceMap.delete(v);
        assertEquals(1, vetServiceMap.findAll().size());
    }

    @Test
    void findById() {
        Vet found = vetServiceMap.findById(1L);
        assertNotNull(found);
        assertEquals(1L, found.getId());
    }


    @Test
    void findByNotExistingId() {
        Vet found = vetServiceMap.findById(2L);
        assertNull(found);
    }
}