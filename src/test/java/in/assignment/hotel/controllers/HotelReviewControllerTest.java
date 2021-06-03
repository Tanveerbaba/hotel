package in.assignment.hotel.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import in.assignment.hotel.dtos.HotelDTO;
import in.assignment.hotel.services.HotelService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class HotelReviewControllerTest {


    private MockMvc mockMvc;
    private ObjectMapper mapper;

    @Mock
    private HotelService hotelService;

    @InjectMocks
    private HotelReviewController sut;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(sut).build();
        mapper = new ObjectMapper();
    }

    @Test
    public void addHotelAPI_shouldReturnOK() throws Exception {
        HotelDTO hotelDTO = HotelDTO.builder()
                .name("test name")
                .address("test address")
                .review(5)
                .star(7)
                .established("1984")
                .build();
        mockMvc.perform(MockMvcRequestBuilders.post("/hotels/add")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(mapper.writeValueAsString(hotelDTO))
        ).andExpect(status().isCreated()).andReturn();
    }

    // OTHER TEST CASES ARE SIMILAR BUT DIDN'T COMPLETE DUE TO TIME CONSTRAINTS
}
