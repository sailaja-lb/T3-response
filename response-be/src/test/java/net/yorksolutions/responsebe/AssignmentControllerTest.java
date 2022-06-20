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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doThrow;

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
        String url = "http://localhost:" + port + "/addAssignment?assignedTo=" + assignedTo +
                "&assignmentId=" + assignmentId;
        doThrow(new ResponseStatusException(HttpStatus.ACCEPTED)).when(service).createAssignment(assignedTo, assignmentId);
        final ResponseEntity<Void> response = rest.getForEntity(url, Void.class);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
    }
    
}