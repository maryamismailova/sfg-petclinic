package maryam.petclinic.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="vets")
public class Vet extends Person{

    @ManyToMany( fetch = FetchType.EAGER )
    @JoinTable( name ="vet_specialties", joinColumns = @JoinColumn(name="vet_id"),
        inverseJoinColumns = @JoinColumn(name="specility_id"))
    private Set<Speciality> specialities = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vet vet = (Vet) o;

        if(this.id.equals(vet.getId()))return true;
        return false;
    }

    @Override
    public int hashCode() {
        return specialities != null ? specialities.hashCode() : 0;
    }
}
