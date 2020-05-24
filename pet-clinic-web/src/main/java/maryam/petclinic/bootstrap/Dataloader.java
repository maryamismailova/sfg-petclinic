package maryam.petclinic.bootstrap;

import maryam.petclinic.model.*;
import maryam.petclinic.services.OwnerService;
import maryam.petclinic.services.PetTypeService;
import maryam.petclinic.services.SpecialitiesService;
import maryam.petclinic.services.VetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class Dataloader implements CommandLineRunner {

    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialitiesService specialitiesService;

    @Autowired
    public Dataloader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialitiesService specialitiesService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialitiesService = specialitiesService;
    }


    @Override
    public void run(String... args) throws Exception {

        int count=petTypeService.findAll().size();
        if(count==0)loadData();
    }

    private void loadData() {
        Speciality radiology= new Speciality();
        radiology.setDescription("Radiology speciality");
        Speciality savedRadiology=specialitiesService.save(radiology);

        Speciality surgery=new Speciality();
        surgery.setDescription("Surgery speciality");
        Speciality savedSurgery=specialitiesService.save(surgery);

        Speciality dentistry = new Speciality();
        dentistry.setDescription("Dentistry speciality");
        Speciality savedDentistry=specialitiesService.save(dentistry);

        PetType dog=new PetType();
        dog.setName("Dog");
        PetType savedDogPetType=petTypeService.save(dog);

        PetType cat=new PetType();
        dog.setName("Cat");
        PetType savedCatPetType=petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstname("Michael");
        owner1.setLastname("Weston");
        owner1.setAddress("123 Baker Street");
        owner1.setCity("Miami");
        owner1.setTelephone("123432343");

        Pet mikesPet=new Pet();
        mikesPet.setPetType(savedDogPetType);
        mikesPet.setOwner(owner1);
        mikesPet.setBirthday(LocalDate.now());
        mikesPet.setName("Rosco");
        owner1.getPets().add(mikesPet);

        ownerService.save(owner1);

        Owner owner2= new Owner();
        owner2.setFirstname("Fiona");
        owner2.setLastname("Glenanne");
        owner2.setAddress("B121 Baker Street");
        owner2.setCity("London");
        owner2.setTelephone("123432111");

        Pet fionasCat=new Pet();
        fionasCat.setName("Kitty");
        fionasCat.setOwner(owner2);
        fionasCat.setBirthday(LocalDate.now());
        fionasCat.setPetType(cat);
        owner2.getPets().add(fionasCat);

        ownerService.save(owner2);

        System.out.println("Loaded Owners..");

        Vet vet1 = new Vet();
        vet1.setFirstname("Sam");
        vet1.setLastname("Axe");
        vet1.getSpecialities().add(savedRadiology);

        vetService.save(vet1);

        Vet vet2 = new Vet();
        vet2.setFirstname("Jessie");
        vet2.setLastname("Porter");
        vet2.getSpecialities().add(savedSurgery);
        vetService.save(vet2);
        System.out.println("Loaded Vets..");
    }
}
