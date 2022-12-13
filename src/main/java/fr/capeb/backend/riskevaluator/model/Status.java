package fr.capeb.backend.riskevaluator.model;

import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "status_entity")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Status {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column(name = "ok")
    private  String ok ;

}
