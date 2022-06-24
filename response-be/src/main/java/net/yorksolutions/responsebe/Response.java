package net.yorksolutions.responsebe;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonProperty
    Long id;
    
    @JsonProperty
    Long questionId;
    
    @JsonProperty
    String questionText;
    
    @JsonProperty
    String response;
    
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "response_id")
    Assignment assignment;
    
    
    public Response(Long questionId, String questionText, String response) {
        this.questionId = questionId;
        this.questionText = questionText;
        this.response = response;
    }
    
    public Response() {
    
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Response response1 = (Response) o;
        return Objects.equals(id, response1.id) && Objects.equals(questionId,
                response1.questionId) && Objects.equals(questionText, response1.questionText)
                && Objects.equals(response, response1.response)
                && Objects.equals(assignment, response1.assignment);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, questionId, questionText, response, assignment);
    }
}
