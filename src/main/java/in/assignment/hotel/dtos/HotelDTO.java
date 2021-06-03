package in.assignment.hotel.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class HotelDTO {
    private String name;
    private String address;
    private Integer review;
    private Integer star;
    private String established;
}
