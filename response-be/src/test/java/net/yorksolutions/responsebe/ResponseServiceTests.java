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

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ResponseServiceTests {
    @InjectMocks
    @Spy
    ResponseService responseService;

    @Mock
    ResponseRepository responseRepository;

    // ******** test getAllResponses ********
    @Test
    void itShouldCallFindAllAndReturnAnIterable() {
        Response newResponse1 = new Response(1L, 1L, "q1", "r1", true);
        Response newResponse2 = new Response(2L, 2L, "q2", "r2", false);
        Response[] responses = new Response[] {newResponse1, newResponse2};
        List<Response> expected = List.of(responses);

        when(responseRepository.findAll()).thenReturn(expected);
        final Iterable<Response> actual = responseService.getAllResponses();

        assertEquals(expected, actual);
    }

    // ******** test deleteResponsesForAssignment ********
//    @Test   // failure
//    void itShouldThrowBadRequestWhenProcessToDeleteDoesNotExist() {
//        Long responseId = 1L;
//        HttpStatus expected = HttpStatus.BAD_REQUEST;
//
//        when(responseRepository.existsById(responseId)).thenReturn(false);
//
//        final ResponseStatusException exception = assertThrows(ResponseStatusException.class,
//                () -> responseService.deleteResponse(responseId));
//        assertEquals(expected, exception.getStatus());
//    }

//    @Test   // success
//    void itShouldDeleteResponseWhenResponseToDeleteExists() {
//        Long responseId = 1L;
//        ArgumentCaptor<Long> captor = ArgumentCaptor.forClass(Long.class);
//        Response responseToDelete = new Response(1L, 1L, "q1", "r1", true);
//        responseToDelete.setId(responseId);
//
//        when(responseRepository.existsById(responseId)).thenReturn(true);
//        when(responseRepository.findById(responseId)).thenReturn(Optional.of(responseToDelete));
//        doNothing().when(responseRepository).deleteById(captor.capture());
//        assertEquals(responseId, captor.getValue());
//    }
}
