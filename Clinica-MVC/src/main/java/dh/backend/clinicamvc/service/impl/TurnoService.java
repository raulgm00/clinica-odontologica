package dh.backend.clinicamvc.service.impl;

import ch.qos.logback.classic.Logger;
import dh.backend.clinicamvc.dao.IDAO;

import dh.backend.clinicamvc.dao.impl.TurnoDao;
import dh.backend.clinicamvc.dto.request.TurnoRequestDto;
import dh.backend.clinicamvc.dto.response.OdontologoResponseDto;
import dh.backend.clinicamvc.dto.response.PacienteResponseDto;
import dh.backend.clinicamvc.dto.response.TurnoResponseDto;
import dh.backend.clinicamvc.model.Odontologo;
import dh.backend.clinicamvc.model.Paciente;
import dh.backend.clinicamvc.model.Turno;
import dh.backend.clinicamvc.service.ITurnoService;
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class TurnoService implements ITurnoService {

    private IDAO<Turno> turnoIDAO;
    private IDAO<Paciente> pacienteIDAO;
    private IDAO<Odontologo> odontologoIDAO;
    private static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);

    /*Objeto de mapeo*/
    private ModelMapper modelMapper;

    public TurnoService(IDAO<Turno> turnoIDAO, IDAO<Paciente> pacienteIDAO, IDAO<Odontologo> odontologoIDAO, ModelMapper modelMapper) {
        this.turnoIDAO = turnoIDAO;
        this.pacienteIDAO = pacienteIDAO;
        this.odontologoIDAO = odontologoIDAO;
        /*Objeto de mapeo*/
        this.modelMapper = modelMapper;
    }

    @Override
    public TurnoResponseDto registrarTurno(TurnoRequestDto turnoRequestDto) {
        Paciente p = pacienteIDAO.buscarPorId(turnoRequestDto.getPaciente_id());
        Odontologo o = odontologoIDAO.buscarPorId(turnoRequestDto.getOdontologo_id());
        Turno turnoARegistrar = new Turno();
        Turno turnoGuardado = null;
        TurnoResponseDto turnoADevolver = null;
        if(p != null && o != null){
            turnoARegistrar.setPaciente(p);
            turnoARegistrar.setOdontologo(o);
            turnoARegistrar.setFecha(LocalDate.parse(turnoRequestDto.getFecha()));
            turnoGuardado = turnoIDAO.registrar(turnoARegistrar);
            turnoADevolver = mapToResponseDTO(turnoGuardado);

            //Armar el turno a devolver
            // Conformado por PacienteResponseDto & OdontologoResponseDto

//            PacienteResponseDto pacienteResponseDTO= new PacienteResponseDto();
//            pacienteResponseDTO.setId(turnoGuardado.getPaciente().getId());
//            pacienteResponseDTO.setNombre(turnoGuardado.getPaciente().getNombre());
//            pacienteResponseDTO.setApellido(turnoGuardado.getPaciente().getApellido());
//            pacienteResponseDTO.setDni(turnoGuardado.getPaciente().getDni());
//
//            OdontologoResponseDto odontologoResponseDto = new OdontologoResponseDto();
//            odontologoResponseDto.setId(turnoGuardado.getOdontologo().getId());
//            odontologoResponseDto.setNumeroMatricula(turnoGuardado.getOdontologo().getNumeroMatricula());
//            odontologoResponseDto.setNombre(turnoGuardado.getOdontologo().getNombre());
//            odontologoResponseDto.setApellido(turnoGuardado.getOdontologo().getApellido());

            //Objeto creado
//            turnoADevolver = new TurnoResponseDto();
//            turnoADevolver.setId(turnoGuardado.getId());
//            turnoADevolver.setPaciente(pacienteResponseDTO);
//            turnoADevolver.setOdontologo(odontologoResponseDto);
//            turnoADevolver.setFecha(String.valueOf(turnoGuardado.getFecha()));
//
//            turnoADevolver = new TurnoResponseDto();
//            turnoADevolver.setId(turnoGuardado.getId());
//            turnoADevolver.setPaciente(pacienteResponseDTO);
//            turnoADevolver.setOdontologo(odontologoResponseDto);
//            turnoADevolver.setFecha(String.valueOf(turnoGuardado.getFecha()));

        }
        return turnoADevolver;
    }

    @Override
    public TurnoResponseDto buscarTurnoPorId(Integer idTurno) {
        Turno turnoEncontrado = turnoIDAO.buscarPorId(idTurno);
        TurnoResponseDto turnoADevolver = mapToResponseDTO(turnoEncontrado);
        return turnoADevolver;
    }

    @Override
    public List<TurnoResponseDto> buscarTodosLosTurnos() {
        List<TurnoResponseDto> listaTurnosResponseDto = new ArrayList<>();
        List<Turno> listaTurnosADevolver = turnoIDAO.buscarTodos();
        TurnoResponseDto turnoADevolver = null;
        for (Turno t: listaTurnosADevolver) {
            turnoADevolver = mapToResponseDTO(t);
            listaTurnosResponseDto.add(turnoADevolver);
        }

        return listaTurnosResponseDto;
    }

    @Override
    public void actualizarTurno(Integer idTurno,TurnoRequestDto turnoRequestDto) {


        Integer idPaciente = turnoRequestDto.getPaciente_id();
        Integer idOdontologo = turnoRequestDto.getOdontologo_id();

        /*Buscando dependencias*/
        Paciente p = pacienteIDAO.buscarPorId(idPaciente);
        Odontologo o = odontologoIDAO.buscarPorId(idOdontologo);

        /*Solo para saber si existe , si existe lo modifica si no no lo modifica*/
        Turno turnoABuscarPorId = turnoIDAO.buscarPorId(idTurno);

        LOGGER.info("Paciente encontrado :" +  (p != null ? true : false) );
        LOGGER.info("Odontologo encontrado :" +  (o != null ? true : false) );
        LOGGER.info("Turno encontrado :" +  (turnoABuscarPorId != null ? true : false) );


        Turno turnoAModificar = new Turno();

        if(p != null && o != null && turnoABuscarPorId != null){

            turnoAModificar.setId(idTurno);
            turnoAModificar.setPaciente(p);
            turnoAModificar.setOdontologo(o);
            turnoAModificar.setFecha(LocalDate.parse(turnoRequestDto.getFecha()));
            LOGGER.info("Turno a modificar "+ turnoAModificar);
            turnoIDAO.actualizar(turnoAModificar);
        }

    }

    @Override
    public void eliminarTurno(Integer id) {
        turnoIDAO.eliminar(id);
    }

    /*Metodo de mapeo*/
    private TurnoResponseDto mapToResponseDTO( Turno turno){
        TurnoResponseDto turnoResponseDto = modelMapper.map(turno, TurnoResponseDto.class);
        turnoResponseDto.setPaciente(modelMapper.map(turno.getPaciente(), PacienteResponseDto.class));
        turnoResponseDto.setOdontologo(modelMapper.map(turno.getOdontologo(), OdontologoResponseDto.class));

        return turnoResponseDto;
    }
}
