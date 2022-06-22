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

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    
    @Mock
    AssignmentRepository assignmentRepository;
    
    @BeforeEach
    void setup() {
        controller.setService(service);
    }
    
    final TestRestTemplate rest = new TestRestTemplate();
    
    /*********
     * TESTS *
     *********/
    
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
    
    @Test
    void itShouldReturnAllGradedResponses() {
        final Long assignedTo = 99999L;
        final String grade = "some grade";
        final Iterable<Assignment> gradedResponses = new ArrayList<>();
        String url =
                "http://localhost:" + port + "/getAllGradedResponses?assignedTo=" + assignedTo +
                        "&grade=" + grade;
        when(controller.getAllGradedResponses(assignedTo, grade)).thenReturn(gradedResponses);
        
        final ResponseEntity<ArrayList> response = rest.getForEntity(url, ArrayList.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(true, response.hasBody());
        assertEquals(gradedResponses, response.getBody());
    }
    
}