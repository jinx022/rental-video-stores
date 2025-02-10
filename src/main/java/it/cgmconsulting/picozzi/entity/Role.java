package it.cgmconsulting.picozzi.entity;

import it.cgmconsulting.picozzi.entity.enumeration.RoleName;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Entity
@AllArgsConstructor @NoArgsConstructor
@Setter @Getter
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private long roleId;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_name", length = 30)
    private RoleName roleName;

    @OneToMany(mappedBy = "role")
    private Set<FilmStaff> filmStaffs;
}