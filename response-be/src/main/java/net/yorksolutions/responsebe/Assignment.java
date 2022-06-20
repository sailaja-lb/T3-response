package net.yorksolutions.responsebe;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Assignment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonProperty
    Long assignmentId;
    
    @JsonProperty
    String grade;
    
    @JsonProperty
    String gradedBy;
    
    @JsonProperty
    Long assignedTo;
    
    @JsonProperty
    Long quizId; // called Quiz Template ID in other BEs
    
    // assigned to
    // @OneToMany(cascade = ALL)
    
    // quiz template id
    // @OneToMany(cascade = ALL)
    
    public Assignment(Long assignmentId, String grade, String gradedBy, Long assignedTo,
                      Long quizId) {
        this.assignmentId = assignmentId;
        this.grade = grade;
        this.gradedBy = gradedBy;
        this.assignedTo = assignedTo;
        this.quizId = quizId;
    }
    
    public Assignment() {
    
    }
    
    public Assignment(Long assignedTo, Long assignmentId) {
        this.assignedTo = assignedTo;
        this.assignmentId = assignmentId;
    }
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Assignment that = (Assignment) o;
        return Objects.equals(assignmentId, that.assignmentId) && Objects.equals(grade,
                that.grade) && Objects.equals(gradedBy, that.gradedBy) && Objects.equals(assignedTo, that.assignedTo) && Objects.equals(quizId, that.quizId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(assignmentId, grade, gradedBy, assignedTo, quizId);
    }
    
    @Override
    public String toString() {
        return "Assignments{" +
                "assignmentId=" + assignmentId +
                ", grade='" + grade + '\'' +
                ", gradedBy='" + gradedBy + '\'' +
                ", assignedTo=" + assignedTo +
                ", quizId=" + quizId +
                '}';
    }
}