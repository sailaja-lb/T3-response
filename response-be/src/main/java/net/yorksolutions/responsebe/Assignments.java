package net.yorksolutions.responsebe;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;

import static javax.persistence.CascadeType.ALL;

@Entity
public class Assignments {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonProperty
    Long id;
    
    @JsonProperty
    String grade;
    
    @JsonProperty
    String gradedBy;
    
    // assigned to
    // @OneToMany(cascade = ALL)
    
    // quiz template id
    // @OneToMany(cascade = ALL)
}