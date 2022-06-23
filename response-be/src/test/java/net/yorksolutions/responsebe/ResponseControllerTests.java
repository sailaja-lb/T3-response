package net.yorksolutions.responsebe;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(MockitoExtension.class)
public class ResponseControllerTests {
    @LocalServerPort
    int port;

    @Autowired
    ResponseController responseController;

    @Mock
    ResponseService responseService;

    @BeforeEach
    void setup() {
        responseController.setResponseService(responseService);
    }

    // ******** test getAllResponses ********
    @Test
    void itShouldCallGetAllResponsesAndReturnIterable() {
        Response newResponse1 = new Response(1L, 1L, "q1", "r1", true);
        Response newResponse2 = new Response(2L, 2L, "q2", "r2", false);

        Response[] responses = new Response[] {newResponse1, newResponse2};
        TestRestTemplate rest = new TestRestTemplate();
        String url = "http://localhost:" + port + "/getAllResponses";

        when(responseService.getAllResponses()).thenReturn(List.of(responses));
        final ResponseEntity<Response[]> endpointResponse = rest.getForEntity(url, Response[].class);

        assertArrayEquals(responses, endpointResponse.getBody());
    }

    // ******** test addResponse ********
    @Test
    void itShouldCallAddResponseWithAssignmentIdQuestionIdQuestionTextResponseAndCompleted() {
        final Long assignmentId = 1L;
        final Long questionId = 1L;
        final String questionText = "q text";
        final String response = "r text";
        final Boolean completed = false;

        final HttpStatus expectedStatus = HttpStatus.ACCEPTED;

        final String url = "http://localhost:" + port + "/addResponse?assignmentId=" + assignmentId + "&questionId=" + questionId + "&questionText=" + questionText + "&response=" + response + "&completed=" + completed;
        TestRestTemplate rest = new TestRestTemplate();
        doThrow(new ResponseStatusException(expectedStatus)).when(responseService).addResponse(assignmentId, questionId, questionText, response, completed);
        final ResponseEntity<Assignment> assignmentEnt = rest.getForEntity(url, Assignment.class);

        assertEquals(expectedStatus, assignmentEnt.getStatusCode());
    }

    // ******** test updateIsComplete ********
    @Test
    void itShouldCallUpdateIsCompleteWithAssignmentId() {
        Long assignmentId = 1L;
        String url = "http://localhost:" + port + "/updateIsComplete?assignmentId=" + assignmentId;
        TestRestTemplate rest = new TestRestTemplate();
        ResponseStatusException exception = new ResponseStatusException(HttpStatus.ACCEPTED);

        doThrow(exception).when(responseService).updateIsComplete(assignmentId);
        final ResponseEntity<Void> response = rest.getForEntity(url, Void.class);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }

    // ******** test deleteResponsesForAssignment ********
    @Test
    void itShouldCallDeleteResponsesForAssignment() {
        Long assignmentId = 1L;
        String url = "http://localhost:" + port + "/deleteResponsesForAssignment?assignmentId=" + assignmentId;
        TestRestTemplate rest = new TestRestTemplate();
        ResponseStatusException exception = new ResponseStatusException(HttpStatus.ACCEPTED);

        doThrow(exception).when(responseService).deleteResponsesForAssignment(assignmentId);
        final ResponseEntity<Void> response = rest.getForEntity(url, Void.class);

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }
}
