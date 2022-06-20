package net.yorksolutions.responsebe;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AssignmentServiceTest {
    
    @InjectMocks
    AssignmentService service;
    
    @Mock
    AssignmentRepository assignmentRepository;
    
    /***********
     * TESTS *
     ***********/
    
    @Test
    void itShouldSaveNewAssignmentToUser() {
        final Long assignedTo = 99999L;
        final Long assignmentId = 9999L;
        when(assignmentRepository.findById(assignmentId)).thenReturn(Optional.empty());
        ArgumentCaptor<Assignment> captor = ArgumentCaptor.forClass(Assignment.class);
        when(assignmentRepository.save(captor.capture())).thenReturn(new Assignment(assignedTo,
         assignmentId));
        assertDoesNotThrow(() -> service.createAssignment(assignedTo, assignmentId));
        assertEquals(new Assignment(assignedTo, assignmentId), captor.getValue());
        
    }
    
}