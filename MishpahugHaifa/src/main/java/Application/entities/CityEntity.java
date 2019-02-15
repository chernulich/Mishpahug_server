package Application.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="cities")
@Getter @Setter
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of = "userEntities")
public class CityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nameOfCity;

    @ManyToOne                                  // Country of city
    @JsonBackReference
    private CountryEntity countryEntity;

    @OneToMany(mappedBy = "cityItem")
    @JsonManagedReference
    private List<AddressEntity> addressEntities = new ArrayList<>();


    @OneToMany(mappedBy = "cityItem")       // Users in the city
    @JsonManagedReference
    private List<UserEntity> userEntities = new ArrayList<>();


}