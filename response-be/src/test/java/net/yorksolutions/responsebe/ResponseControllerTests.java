package net.yorksolutions.responsebe;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
        newResponse1.setId(1L);
        newResponse2.setId(2L);
        Response[] responses = new Response[] {newResponse1, newResponse2};
        TestRestTemplate rest = new TestRestTemplate();
        String url = "http://localhost:" + port + "/getAllResponses";

        when(responseService.getAllResponses()).thenReturn(List.of(responses));
        final ResponseEntity<Response[]> endpointResponse = rest.getForEntity(url, Response[].class);

        assertArrayEquals(responses, endpointResponse.getBody());
    }

    // ******** test addResponse ********
    @Test
    void itShouldCallAddResponseWithAssignmentIdQuestionIdQuestionTextResponseAndCompletedAndReturnAssignment() {
//        final Long assignmentId = 0L;
//        final Long questionId = 0L;
//        final String questionText = "q text";
//        final String response = "a response";
//        final boolean completed = true;
        final Response newResponse1 = new Response(1L, 1L, "q1", "r1", true);
        final Response newResponse2 = new Response(2L, 2L, "q2", "r2", false);
        newResponse1.setId(1L);
        newResponse2.setId(2L);
        final Response[] responses = new Response[] {newResponse1, newResponse2};
        final List<Response> responseList = List.of(responses);
        final Assignment expected = new Assignment("A", 1L, 1L, 1L, responseList);

        final String url = "http://localhost:" + port + "/addResponse";
        TestRestTemplate rest = new TestRestTemplate();

//        final ArgumentCaptor<> captor = ArgumentCaptor.forClass(HttpServletRequest.class);

        when(responseService.addResponse(1L, 1L, "q1", "r1", true)).thenReturn(expected);
        final ResponseEntity<Assignment> assignmentEnt = rest.getForEntity(url, Assignment.class);

        assertEquals(expected, assignmentEnt.getBody());
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
}
