package net.yorksolutions.responsebe;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
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
        when(assignmentRepository.save(captor.capture())).thenReturn(new Assignment(assignedTo,
                quizTemplateId));
        assertDoesNotThrow(() -> service.addAssignment(assignedTo, quizTemplateId));
        assertEquals(new Assignment(assignedTo, quizTemplateId), captor.getValue());
    }
    
    @Test
    void itShouldThrowWhenNoAssignment() {
        final Long assignedTo = 99999L;
        final Long assignmentId = 9999L;
        final Long quizTemplateId = 9999L;
        when(assignmentRepository.findByQuizTemplateId(quizTemplateId)).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class, () -> service.addAssignment(assignedTo,
                quizTemplateId));
    }
    
    @Test
    void itShouldUpdateGradeWhenCalled() {
        final Long assignmentID = 99999L;
        final String grade = "some grade";
        final Long gradedBy = 99999L;
        final Assignment assignment = new Assignment();
        when(assignmentRepository.findById(assignmentID)).thenReturn(Optional.of(assignment));
        ArgumentCaptor<Assignment> captor = ArgumentCaptor.forClass(Assignment.class);
        when(assignmentRepository.save(captor.capture())).thenReturn(assignment);
        assertDoesNotThrow(() -> service.updateGrade(assignmentID, grade, gradedBy));
        assertEquals(assignment, captor.getValue());
    }
    
    @Test
    void itShouldThrowWhenAssignmentIdIsEmpty() {
        final Long assignmentId = 999999L;
        final String grade = "some grade";
        final Long gradedBy = 99999L;
        when(assignmentRepository.findById(assignmentId)).thenReturn(Optional.empty());
        assertThrows(ResponseStatusException.class,
                () -> service.updateGrade(assignmentId, grade, gradedBy));
    }
    
    @Test
    void itShouldReturnAllAssignments() {
        final ArrayList<Assignment> assignments = new ArrayList<>();
        assignments.add(new Assignment());
        assignments.add(new Assignment());
        assignments.add(new Assignment());
        assignments.add(new Assignment());
        when(assignmentRepository.findAll()).thenReturn(assignments);
        assertEquals(assignments, service.getAllAssignments());
    }
}