package dh.backend.clinicamvc.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OdontologoResponseDto {
    private Integer id;
    private Integer numeroMatricula;
    private String nombre;
    private String apellido;
}
