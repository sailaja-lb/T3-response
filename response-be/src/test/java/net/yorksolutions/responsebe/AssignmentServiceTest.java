package net.yorksolutions.responsebe;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

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
        final Long quizTemplateId = 9999L;
        final Assignment assignment = new Assignment(quizTemplateId);
        when(assignmentRepository.findByQuizTemplateId(quizTemplateId)).thenReturn(Optional.of(assignment));
        ArgumentCaptor<Assignment> captor = ArgumentCaptor.forClass(Assignment.class);
        when(assignmentRepository.save(captor.capture())).thenReturn(new Assignment(assignedTo, quizTemplateId));
        assertDoesNotThrow(() -> service.addAssignment(assignedTo, quizTemplateId));
        assertEquals(new Assignment(assignedTo, quizTemplateId), captor.getValue());
    }
    
    @Test
    void itShouldThrowWhenNoAssignment() {
        final Long assignedTo = 99999L;
        final Long assignmentId = 9999L;
        final Long quizTemplateId = 9999L;
        when(assignmentRepository.findByQuizTemplateId(quizTemplateId)).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> service.addAssignment(assignedTo, quizTemplateId));
    }
    
}