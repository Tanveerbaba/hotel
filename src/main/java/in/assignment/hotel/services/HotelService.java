package in.assignment.hotel.services;

import in.assignment.hotel.dtos.APIResponse;
import in.assignment.hotel.dtos.HotelDTO;
import in.assignment.hotel.models.Hotel;
import in.assignment.hotel.repositories.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HotelService {

  private final HotelRepository hotelRepository;

  public HotelService(HotelRepository hotelRepository) {
    this.hotelRepository = hotelRepository;
  }

  public void addHotel(HotelDTO hotelDTO) {
    Hotel hotel =
        Hotel.builder()
            .address(hotelDTO.getAddress())
            .name(hotelDTO.getName())
            .review(hotelDTO.getReview())
            .star(hotelDTO.getStar())
            .established(hotelDTO.getEstablished())
            .build();
    hotelRepository.save(hotel);
  }

  public APIResponse removeHotel(Long hotelId) {
    try {
      hotelRepository.deleteById(hotelId);
    } catch (Exception e) {
      return new APIResponse("Hotel Id not found");
    }
    return null;
  }

  public APIResponse updateHotel(Long hotelId, HotelDTO hotelDTO) {
    Optional<Hotel> hotel = hotelRepository.findById(hotelId);
    if (!hotel.isPresent()) {
      return new APIResponse("Hotel Id not found");
    }
    hotel.ifPresent(
        value -> {
          value.setName(hotelDTO.getName() != null ? hotelDTO.getName() : value.getName());
          value.setAddress(
              hotelDTO.getAddress() != null ? hotelDTO.getAddress() : value.getAddress());
          value.setEstablished(
              hotelDTO.getEstablished() != null
                  ? hotelDTO.getEstablished()
                  : value.getEstablished());
          value.setReview(hotelDTO.getReview() != null ? hotelDTO.getReview() : value.getReview());
          value.setStar(hotelDTO.getStar() != null ? hotelDTO.getStar() : value.getStar());
        });
    hotelRepository.save(hotel.get());
    return null;
  }

  public HotelDTO getHotel(Long hotelId) {
    Optional<Hotel> hotel = hotelRepository.findById(hotelId);
    return hotel
        .map(
            value ->
                HotelDTO.builder()
                    .address(value.getAddress())
                    .name(value.getName())
                    .established(value.getEstablished())
                    .star(value.getStar())
                    .review(value.getReview())
                    .build())
        .orElse(null);
  }

  public List<HotelDTO> getAllHotels() {
    List<HotelDTO> hotelDTOList = new ArrayList<>();
    List<Hotel> hotels = hotelRepository.findAll();
    hotels.forEach(
        hotel -> {
          HotelDTO hotelDTO =
              HotelDTO.builder()
                  .name(hotel.getName())
                  .review(hotel.getReview())
                  .star(hotel.getStar())
                  .established(hotel.getEstablished())
                  .address(hotel.getAddress())
                  .build();
          hotelDTOList.add(hotelDTO);
        });
    return hotelDTOList;
  }
}
