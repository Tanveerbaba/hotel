package in.assignment.hotel.controllers;

import in.assignment.hotel.dtos.APIResponse;
import in.assignment.hotel.dtos.HotelDTO;
import in.assignment.hotel.services.HotelService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping(value = "/hotels")
@RestController
public class HotelReviewController {

    private final HotelService hotelService;

    public HotelReviewController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @PostMapping(value = "/add")
    public ResponseEntity<?> addHotel(@RequestBody HotelDTO hotelDTO) {
        hotelService.addHotel(hotelDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/remove/{hotelId}")
    public ResponseEntity<?> removeHotel(@PathVariable Long hotelId) {
        APIResponse apiResponse = hotelService.removeHotel(hotelId);
        if (apiResponse != null) {
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping(value = "/update/{hotelId}")
    public ResponseEntity<?> updateHotelInfo(@PathVariable("hotelId") Long hotelId, @RequestBody HotelDTO hotelDTO) {
        APIResponse apiResponse = hotelService.updateHotel(hotelId, hotelDTO);
        if (apiResponse != null) {
            return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }

  @GetMapping(
      value = "/{hotelId}",
      produces = {"application/json", "application/xml"})
  public ResponseEntity<?> getHotelInfo(@PathVariable("hotelId") Long hotelId) {
        HotelDTO hotelDTO = hotelService.getHotel(hotelId);
        if (hotelDTO == null) {
            return new ResponseEntity<>("Hotel not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(hotelDTO, HttpStatus.OK);
    }

    @GetMapping(value = "",
            produces = {"application/json", "application/xml"})
    public ResponseEntity<?> getAllHotels() {
        List<HotelDTO> hotels = hotelService.getAllHotels();
        if (hotels.isEmpty()) {
            return new ResponseEntity<>("Hotels not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(hotels, HttpStatus.OK);
    }

}
