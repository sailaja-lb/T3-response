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
    Long id;

    @JsonProperty
    Long assignmentId;

    @JsonProperty
    Long quizId;

    @JsonProperty
    String question;

    @JsonProperty
    String response;

    @JsonProperty
    String status;

    public Response(Long assignmentId, Long quizId, String question, String response, String status) {
        this.assignmentId = assignmentId;
        this.quizId = quizId;
        this.question = question;
        this.response = response;
        this.status = status;
    }
    public Response() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Response response1 = (Response) o;
        return Objects.equals(id, response1.id) && Objects.equals(assignmentId, response1.assignmentId) && Objects.equals(quizId, response1.quizId) && Objects.equals(question, response1.question) && Objects.equals(response, response1.response) && Objects.equals(status, response1.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, assignmentId, quizId, question, response, status);
    }

    // ******** setters ********

    public void setId(Long id) {
        this.id = id;
    }
}
