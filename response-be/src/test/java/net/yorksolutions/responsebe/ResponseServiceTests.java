package net.yorksolutions.responsebe;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
        Response newResponse1 = new Response(1L, 1L, "q1", "r1", "s1");
        Response newResponse2 = new Response(2L, 2L, "q2", "r2", "s2");
        Response[] responses = new Response[] {newResponse1, newResponse2};
        List<Response> expected = List.of(responses);

        when(responseRepository.findAll()).thenReturn(expected);
        final Iterable<Response> actual = responseService.getAllResponses();

        assertEquals(expected, actual);
    }
}
