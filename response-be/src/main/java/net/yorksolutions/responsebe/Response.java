package net.yorksolutions.responsebe;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Response {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonProperty
    Long responseId;

    @JsonProperty
    Long assignmentId;

    @JsonProperty
    Long questionId;

    @JsonProperty
    String questionText;

    @JsonProperty
    String response;

    @JsonProperty
    Boolean completed;

    public Response(Long assignmentId, Long quizId, String questionText, String response, Boolean completed) {
        this.assignmentId = assignmentId;
        this.questionId = questionId;
        this.questionText = questionText;
        this.response = response;
        this.completed = completed;
    }
    public Response() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response response1 = (Response) o;
        return Objects.equals(responseId, response1.responseId) && Objects.equals(assignmentId, response1.assignmentId) && Objects.equals(questionId, response1.questionId) && Objects.equals(questionText, response1.questionText) && Objects.equals(response, response1.response) && Objects.equals(completed, response1.completed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(responseId, assignmentId, questionId, questionText, response, completed);
    }

// ******** setters ********

    public void setId(Long id) {
        this.responseId = responseId;
    }
}
