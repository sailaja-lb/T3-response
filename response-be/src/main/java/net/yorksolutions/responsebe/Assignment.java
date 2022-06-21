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
    Long gradedBy; // recruiter's generated id
    
    @JsonProperty
    Long assignedTo; // user's generated id
    
    @JsonProperty
    Long quizTemplateId; // Id from quizTemplate
    
    // Join table for assignments and response
    
    // TODO update data types in shared Sheets and Docs
    // TODO add AssignmentTable to Sheets
    
    // assigned to
    // @OneToMany(cascade = ALL)
    
    // quiz template id
    // @OneToMany(cascade = ALL)
    
    public Assignment(String grade, Long gradedBy, Long assignedTo,
                      Long quizTemplateId) {
        this.grade = grade;
        this.gradedBy = gradedBy;
        this.assignedTo = assignedTo;
        this.quizTemplateId = quizTemplateId;
    }
    
    public Assignment() {
    
    }
    
    public Assignment(Long assignedTo, Long quizTemplateId) {
        this.assignedTo = assignedTo;
        this.assignmentId = assignmentId;
        this.quizTemplateId = quizTemplateId;
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
        return  Objects.equals(grade,
                that.grade) && Objects.equals(gradedBy, that.gradedBy)
                && Objects.equals(assignedTo, that.assignedTo)
                && Objects.equals(quizTemplateId, that.quizTemplateId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(grade, gradedBy, assignedTo, quizTemplateId);
    }
    
    @Override
    public String toString() {
        return "Assignments{" +
                "grade='" + grade + '\'' +
                ", gradedBy='" + gradedBy + '\'' +
                ", assignedTo=" + assignedTo +
                ", quizTemplateId=" + quizTemplateId +
                '}';
    }
}