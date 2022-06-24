package net.yorksolutions.responsebe;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ResponseServiceTests {
    @InjectMocks
    @Spy
    ResponseService responseService;
    
    @Mock
    ResponseRepository responseRepository;
    
    @Mock
    AssignmentRepository assignmentRepository;
    
    // ******** test getAllResponses ********
//    @Test
//    void itShouldCallFindResponseByCompletedIsTrueAndReturnAnIterable() {
//        Response newResponse1 = new Response(1L, 1L, "q1", "r1", true);
//        Response newResponse2 = new Response(2L, 2L, "q2", "r2", false);
//        Response[] responses = new Response[]{newResponse1, newResponse2};
//        List<Response> expected = List.of(responses);
//
////        when(responseRepository.findResponseByCompletedIsTrue()).thenReturn(expected);
//        when(responseRepository.findAllByCompletedIsTrue()).thenReturn(expected);
//
//        final Iterable<Response> actual = responseService.getAllResponses();
//
//        assertEquals(expected, actual);
//    }
    
    // ******** test addResponse ********
//    @Test // failure
//    void itShouldThrowIfAssignmentIdDoesNotExistWhenAddResponse() {
//        final Long assignmentId = 0L;
//        final Long questionId = 0L;
//        final String questionText = "Explain how to...";
//        final String response = "I would...";
//        final Boolean completed = true;
//
//        final Assignment assignment = new Assignment(assignmentId);
//
//        when(assignmentRepository.findById(assignmentId)).thenReturn(Optional.empty());
//        assertThrows(ResponseStatusException.class, () -> responseService.addResponse
//        (assignmentId, questionId, questionText, response, completed));
//    }

//    @Test // success
//    void itShouldSaveNewResponseWhenAssignmentIdFound() {
//        final Long assignmentId = 0L;
//        final Long questionId = 0L;
//        final String questionText = "Explain how to...";
//        final String response = "I would...";
//        final Boolean completed = true;
//
//        final Assignment assignment = new Assignment(assignmentId);
//
//        when(assignmentRepository.findById(assignmentId)).thenReturn(Optional.of(assignment));
//        ArgumentCaptor<Assignment> captor = ArgumentCaptor.forClass(Assignment.class);
//
//        when(assignmentRepository.save(captor.capture())).thenReturn(assignment);
//        assertDoesNotThrow(() -> responseService.addResponse(assignmentId, questionId,
//        questionText, response, completed));
//        assertEquals(assignment, captor.getValue());
//    }
    
    // ******** test updateIsComplete ********
//    @Test
//    // failure
//    void itShouldThrowIfAssignmentIdDoesNotExistWhenUpdateIsComplete() {
//        final Long assignmentId = 0L;
//        HttpStatus expected = HttpStatus.NOT_FOUND;
//
//        when(assignmentRepository.findById(assignmentId)).thenReturn(Optional.empty());
//        final ResponseStatusException exception = assertThrows(ResponseStatusException.class,
//                () -> responseService.updateIsComplete(assignmentId));
//        assertEquals(expected, exception.getStatus());
//    }
//
//    @Test
//        // success
//    void
//    itShouldUpdateCompletedToTrueForAllResponsesWithTheResponseIdWhenUpdateIsCompleteIsCalled() {
//        final Long assignmentId = 0L;
//
//        Assignment expected = new Assignment(assignmentId);
//        ArgumentCaptor<Assignment> captor = ArgumentCaptor.forClass(Assignment.class);
//
//        when(assignmentRepository.findById(assignmentId)).thenReturn(Optional.of(expected));
//        when(assignmentRepository.save(captor.capture())).thenReturn(new Assignment());
//
//        assertDoesNotThrow(() -> responseService.updateIsComplete(assignmentId));
//        assertEquals(expected, captor.getValue());
//    }
    
    // ******** test deleteResponsesForAssignment ********
//    @Test
//    // failure - no assignment found
//    void
//    itShouldThrowNotFoundWhenNoAssignmentFoundWithTheGivenAssignmentIdWhendeleteResponsesForAssignment() {
//        final Long assignmentId = 0L;
//        HttpStatus expected = HttpStatus.NOT_FOUND;
//
//        when(assignmentRepository.findById(assignmentId)).thenReturn(Optional.empty());
//        final ResponseStatusException exception = assertThrows(ResponseStatusException.class,
//                () -> responseService.deleteResponsesForAssignment(assignmentId));
//        assertEquals(expected, exception.getStatus());
//    }
//
//
//    @Test
//        // failure - no responses found for assignmentId
//    void itShouldThrowNotFoundWhenNoResponsesFoundForTheGivenAssignmentId() {
//        final Long assignmentId = 1L;
//        final Assignment assignment = new Assignment(assignmentId);
//        final HttpStatus expected = HttpStatus.NOT_FOUND;
//
//        when(assignmentRepository.findById(assignmentId)).thenReturn(Optional.of(assignment));
//        final ResponseStatusException exception = assertThrows(ResponseStatusException.class,
//                () -> responseService.deleteResponsesForAssignment(assignmentId));
//
//        assertEquals(expected, exception.getStatus());
//    }
//
//    @Test
//        // success
//    void
//    itShouldDeleteAllResponsesAssociatedWithTheGivenAssignmentIdWhenDeleteAllByAssignmentId() {
//        final Long assignmentId = 1L;
//        final Assignment assignment = new Assignment(assignmentId);
//        Response newResponse1 = new Response(1L, 1L, "q1", "r1", true);
//        Response newResponse2 = new Response(2L, 2L, "q2", "r2", false);
//        Response[] responses = new Response[]{newResponse1, newResponse2};
//        assignment.responses = List.of(responses);
//        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
//
//        when(assignmentRepository.findById(assignmentId)).thenReturn(Optional.of(assignment));
//        doNothing().when(responseRepository).deleteAllByAssignmentId(captor.capture());
//
//        assertDoesNotThrow(() -> responseService.deleteResponsesForAssignment(assignmentId));
//    }
}
