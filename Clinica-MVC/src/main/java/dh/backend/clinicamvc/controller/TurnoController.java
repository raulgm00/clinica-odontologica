package dh.backend.clinicamvc.controller;

import dh.backend.clinicamvc.dto.request.TurnoRequestDto;
import dh.backend.clinicamvc.dto.response.TurnoResponseDto;
import dh.backend.clinicamvc.service.ITurnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/turnos")
public class TurnoController {

    /*Inyeccion de dependencias
    * Inyectamos el Service por medio d ela interface
    * */
    private ITurnoService turnoService;

    /*Inyeccion de dependencias a la clase Services*/

    public TurnoController(ITurnoService turnoService) {
        this.turnoService = turnoService;
    }

    /* POST*/
    /* http://localhost:8080/turnos */
    @PostMapping
    public ResponseEntity <TurnoResponseDto> registrarTurno(@RequestBody TurnoRequestDto turnoRequestDto){

        TurnoResponseDto turnoResponseDtoADevolver = turnoService.registrarTurno(turnoRequestDto);

        if(turnoResponseDtoADevolver == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }else{
            return ResponseEntity.status(HttpStatus.CREATED).body(turnoResponseDtoADevolver);
        }
    }

    /* GET*/
    /* http://localhost:8080/turnos */
    @GetMapping
    public ResponseEntity<List<TurnoResponseDto>> buscarTodosTurnos(){
        return ResponseEntity.ok(turnoService.buscarTodosLosTurnos());
    }

    /* GET */
    /* PATH VARIABLE */
    /* http://localhost:8080/turnos/2 */

    @GetMapping("/{id}")
    public ResponseEntity<TurnoResponseDto> buscarTurnoPorId(@PathVariable Integer id){
        TurnoResponseDto turnoResponseDto = turnoService.buscarTurnoPorId(id);
        if(turnoResponseDto != null){
            return ResponseEntity.ok(turnoResponseDto);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /* PUT */
    /* ACTUALIZACION TOTAL */
    /* http://localhost:8080/turnos/id */
    @PutMapping("{id}")
    public ResponseEntity<String> actualizarTurno(@PathVariable Integer id, @RequestBody TurnoRequestDto turnoRequestDto){
        turnoService.actualizarTurno(id, turnoRequestDto);
        return ResponseEntity.ok("{\"messages\":  \"Turno actualizado\"}");
    }

    /*DELETE*/
    /*PATH VARIABLE*/
    /* http://localhost:8080/pacientes/2 */
    @DeleteMapping("{id}")
    public ResponseEntity<String> borrarTurno(@PathVariable Integer id){
        turnoService.eliminarTurno(id);
        return ResponseEntity.ok("{\"messages\":  \"Turno eliminado\"}");
    }
}
