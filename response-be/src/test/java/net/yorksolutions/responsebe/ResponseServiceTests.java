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
    @Test
    void itShouldCallFindResponseByCompletedIsTrueAndReturnAnIterable() {
        Response newResponse1 = new Response(1L, 1L, "q1", "r1", true);
        Response newResponse2 = new Response(2L, 2L, "q2", "r2", false);
        Response[] responses = new Response[] {newResponse1, newResponse2};
        List<Response> expected = List.of(responses);

//        when(responseRepository.findResponseByCompletedIsTrue()).thenReturn(expected);
        when(responseRepository.findAllByCompletedIsTrue()).thenReturn(expected);

        final Iterable<Response> actual = responseService.getAllResponses();

        assertEquals(expected, actual);
    }

    // ******** test addResponse ********

    @Test // failure
    void itShouldThrowIfAssignmentIdDoesNotExist() {
        final Long assignmentId = 0L;
        final Long questionId = 0L;
        final String questionText = "Explain how to...";
        final String response = "I would...";
        final Boolean completed = true;

        final Assignment assignment = new Assignment(assignmentId);
    }

    @Test // success
    void itShouldSaveNewResponseWhenAssignmentIdFound() {
        final Long assignmentId = 0L;
        final Long questionId = 0L;
        final String questionText = "Explain how to...";
        final String response = "I would...";
        final Boolean completed = true;

        final Assignment assignment = new Assignment(assignmentId);

        when(assignmentRepository.findById(assignmentId)).thenReturn(Optional.empty());
        ArgumentCaptor<Assignment> captor = ArgumentCaptor.forClass(Assignment.class);

        when(assignmentRepository.save(captor.capture())).thenReturn(assignment);
        assertDoesNotThrow(() -> responseService.addResponse(assignmentId, questionId, questionText, response, completed));
        assertEquals(assignment, captor.getValue());
    }

    // ******** test updateIsComplete ********
    @Test   // failure
    void itShouldThrowIfAssignmentIdDoesNotExistWhenUpdateIsComplete() {
        final Long assignmentId = 0L;
        HttpStatus expected = HttpStatus.NOT_FOUND;

        when(assignmentRepository.findById(assignmentId)).thenReturn(Optional.empty());
        final ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> responseService.updateIsComplete(assignmentId));
        assertEquals(expected, exception.getStatus());
    }

    @Test   // success
    void itShouldUpdateCompletedToTrueForAllResponsesWithTheResponseIdWhenUpdateIsCompleteIsCalled() {
        final Long assignmentId = 0L;

        Assignment expected = new Assignment(assignmentId);
        ArgumentCaptor<Assignment> captor = ArgumentCaptor.forClass(Assignment.class);

        when(assignmentRepository.findById(assignmentId)).thenReturn(Optional.of(expected));
        when(assignmentRepository.save(captor.capture())).thenReturn(new Assignment());

        assertDoesNotThrow(() -> responseService.updateIsComplete(assignmentId));
        assertEquals(expected, captor.getValue());
    }


    // ******** test deleteResponsesForAssignment ********
//    @Test   // failure
//    void itShouldThrowNotFoundWhenNoResponsesForAssignmentId() {
//        Long assignmentId = 1L;
//        Response newResponse1 = new Response(1L, 1L, "q1", "r1", true);
//        Response newResponse2 = new Response(2L, 2L, "q2", "r2", false);
//        Response[] responses = new Response[] {newResponse1, newResponse2};
//        List<Response> expected = List.of(responses);
//
//        when(responseRepository.findAll()).thenReturn(expected);
//

//
//        HttpStatus expected = HttpStatus.NOT_FOUND;
//        Iterable<Response> responseList = new Lis
//
//        when(responseRepository.findAllByAssignmentId(assignmentId)).thenReturn(false);
//
//        final ResponseStatusException exception = assertThrows(ResponseStatusException.class,
//                () -> responseService.deleteResponse(id));
//        assertEquals(expected, exception.getStatus());
//    }

    @Test   // success
    void itShouldDeleteResponsesWhenAtLeastOneResponseFoundForAssignment() {
        Long assignmentId = 2L;
        Long questionId = 0L;
        String questionText = "q?";
        String response = "r1";
        Boolean completed = true;
        Response r1 = new Response(assignmentId, questionId, questionText, response, completed);
        r1.setId(1L);
        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
        ArrayList<Response> responses = new ArrayList<>();
        responses.add(r1);

        when(responseRepository.findAllByAssignmentId(assignmentId)).thenReturn(responses);
        when(responseRepository.countByAssignmentId(assignmentId)).thenReturn(1);
        doNothing().when(responseRepository).deleteById(captor.capture());

//        assertDoesNotThrow(() -> responseService.deleteResponsesForAssignment(assignmentId));
        responseService.deleteResponsesForAssignment(assignmentId);
        assertEquals(1L, captor.getValue());
    }
}
