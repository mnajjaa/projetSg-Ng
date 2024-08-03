package com.projetSpringBoot_Ng.projetSg_Ng.offres;

import com.projetSpringBoot_Ng.projetSg_Ng.user.User;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "offre")
@EntityListeners(AuditingEntityListener.class)
public class Offre {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nomOffre;
    private String description;

//    @OneToMany(mappedBy = "offre", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Poste> postes;

    @ManyToOne
    @JoinColumn(name = "recruteur_id")
    private User recruteur;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastUpdatedDate;

    @CreatedBy
    @Column(nullable = false, updatable = false)
    private Integer createdBy;

    @LastModifiedBy
    @Column(insertable = false)
    private Integer lastModifiedBy;

}
