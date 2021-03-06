package net.yorksolutions.responsebe;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static javax.persistence.CascadeType.ALL;

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
    
    @JsonProperty
    Boolean completed;
    
    @JsonProperty
    @OneToMany(cascade = ALL)
    List<Response> responses;
    
    public Assignment() {
    
    }
    
    public Assignment(Long assignmentId) {
        this.assignmentId = assignmentId;
        this.responses = new ArrayList<>();
    }
    
    public Assignment(String grade, Long gradedBy, Long assignedTo,
                      Long quizTemplateId, List<Response> responses, Boolean completed) {
        this.grade = grade;
        this.gradedBy = gradedBy;
        this.assignedTo = assignedTo;
        this.quizTemplateId = quizTemplateId;
        this.responses = responses;
        this.completed = completed;
    }
    
    public Assignment(Long assignedTo, String grade) {
        this.assignedTo = assignedTo;
        this.grade = grade;
    }
    
    public void addResponse(Response response) {
        responses.add(response);
    }
    
    
    public Assignment(Long assignedTo, Long quizTemplateId) {
        this.assignedTo = assignedTo;
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
        return Objects.equals(assignmentId, that.assignmentId) && Objects.equals(grade,
                that.grade) && Objects.equals(gradedBy, that.gradedBy) && Objects.equals(assignedTo, that.assignedTo) && Objects.equals(quizTemplateId, that.quizTemplateId) && Objects.equals(completed, that.completed) && Objects.equals(responses, that.responses);
    }
    
    @Override
    public String toString() {
        return "Assignment{" +
                "assignmentId=" + assignmentId +
                ", grade='" + grade + '\'' +
                ", gradedBy=" + gradedBy +
                ", assignedTo=" + assignedTo +
                ", quizTemplateId=" + quizTemplateId +
                ", completed=" + completed +
                ", responses=" + responses +
                '}';
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(assignmentId, grade, gradedBy, assignedTo, quizTemplateId, completed,
                responses);
    }
    
    public List<Response> getResponses() {
        return responses;
    }
}