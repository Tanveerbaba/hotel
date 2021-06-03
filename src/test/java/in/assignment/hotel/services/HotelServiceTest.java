package in.assignment.hotel.services;

import in.assignment.hotel.dtos.HotelDTO;
import in.assignment.hotel.models.Hotel;
import in.assignment.hotel.repositories.HotelRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

public class HotelServiceTest {

    @Mock
    private HotelRepository hotelRepository;

    @InjectMocks
    private HotelService sut;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void addHotelMethod_shouldSaveHotel() {
        HotelDTO hotelDTO = HotelDTO.builder()
                .name("test name")
                .address("test address")
                .review(5)
                .star(7)
                .established("1984")
                .build();
        Hotel hotel =
                Hotel.builder()
                        .address(hotelDTO.getAddress())
                        .name(hotelDTO.getName())
                        .review(hotelDTO.getReview())
                        .star(hotelDTO.getStar())
                        .established(hotelDTO.getEstablished())
                        .build();
        Mockito.when(hotelRepository.save(hotel)).thenReturn(hotel);
        sut.addHotel(hotelDTO);
        Mockito.verify(hotelRepository, Mockito.times(1)).save(hotel);
    }

    // OTHER TEST CASES ARE SIMILAR BUT DIDN'T COMPLETE DUE TO TIME CONSTRAINTS
}
