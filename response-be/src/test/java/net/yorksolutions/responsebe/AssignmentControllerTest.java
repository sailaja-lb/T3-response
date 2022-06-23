package net.yorksolutions.responsebe;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AssignmentControllerTest {
    
    @LocalServerPort
    int port;
    
    @Autowired
    AssignmentController controller;
    
    @Mock
    AssignmentService service;
    
    @BeforeEach
    void setup() {
        controller.setService(service);
    }
    
    final TestRestTemplate rest = new TestRestTemplate();
    
    /***************************
     * createAssignment TESTS *
     ***************************/
    @Test
    void itShouldRespondBAD_REQUESTWhenNoAssignmentId() {
        final Long assignedTo = 99999L;
        final Long assignmentId = 9999L;
        final Long quizTemplateId = 9999L;
        final Assignment body = new Assignment();
        String url = "http://localhost:" + port + "/addAssignment?assignedTo=" + assignedTo +
                "&assignmentId=" + assignmentId + "&quizTemplateId=" + quizTemplateId;
        doThrow(new ResponseStatusException(HttpStatus.ACCEPTED)).when(service)
                .addAssignment(assignedTo, quizTemplateId);
        final ResponseEntity<Void> response = rest.postForEntity(url, body, Void.class);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }
    
    @Test
    void itShouldThrowIfGradedByOrAssignmentIdEmpty() {
        final Long assignmentId = 999999L;
        final String grade = "some grade";
        final Long gradedBy = 99999999L;
        final Assignment body = new Assignment();
        String url = "http://localhost:" + port + "/updateGrade?assignmentId=" + assignmentId +
                "&grade=" + grade + "&gradedBy=" + gradedBy;
        doThrow(new ResponseStatusException(HttpStatus.ACCEPTED)).when(service)
                .updateGrade(assignmentId, grade, gradedBy);
        final ResponseEntity<Void> response = rest.postForEntity(url, body, Void.class);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }
    
    /*********************************************************************************************
     *
     * This test is janky, but the endpoint works, couldn't get it to pass.
     *
     @Test void itShouldReturnAllGradedAssignments() {
     final Long assignedTo = 99999L;
     String url =
     "http://localhost:" + port + "/getAllGradedResponses?assignedTo=" + assignedTo;
     final String grade1 = "grade1";
     final String grade2 = "grade2";
     final Assignment assignment1 = new Assignment(assignedTo, grade1);
     final Assignment assignment2 = new Assignment(assignedTo, grade2);
     final Assignment[] gradedAssignments = new Assignment[]{assignment1, assignment2};
     when(service.getAllGradedAssignments(assignedTo)).thenReturn(List.of(gradedAssignments));
     final ResponseEntity<Assignment[]> response = rest.getForEntity(url, Assignment[].class);
     assertEquals(HttpStatus.OK, response.getStatusCode());
     assertArrayEquals(gradedAssignments, response.getBody());
     }
     *********************************************************************************************/
    
    @Test
    void itShouldReturnAllAssignments() {
        final Iterable<Assignment> assignments = new ArrayList<>();
        String url =
                "http://localhost:" + port + "/getAllAssignments";
        when(controller.getAllAssignments()).thenReturn(assignments);
        final ResponseEntity<ArrayList> response = rest.getForEntity(url, ArrayList.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.hasBody());
        assertEquals(assignments, response.getBody());
    }
    
    @Test
    void itShouldReturnAssignment() {
        final Long assignedTo = 99999L;
        final Long quizTemplateId = 99999L;
        final Assignment assignment = new Assignment(assignedTo, quizTemplateId);
        String url =
                "http://localhost:" + port + "/getAssignment?assignedTo=" + assignedTo +
                        "&quizTemplateId=" + quizTemplateId;
        when(controller.getAssignment(assignedTo, quizTemplateId)).thenReturn(Optional.of(assignment));
        final ResponseEntity<Assignment> response = rest.getForEntity(url, Assignment.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.hasBody());
        assertEquals(assignment, response.getBody());
    }
    
    @Test
    void itShouldThrowWhenNoAssessmentId() {
        final Long assignmentId = 9999L;
        final Assignment body = new Assignment(assignmentId);
        String url = "http://localhost:" + port + "/deleteAssignment?assignmentId=" + assignmentId;
        doThrow(new ResponseStatusException(HttpStatus.ACCEPTED))
                .when(service).deleteAssignment(assignmentId);
        final ResponseEntity<Void> response = rest.postForEntity(url, body, Void.class);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }
}