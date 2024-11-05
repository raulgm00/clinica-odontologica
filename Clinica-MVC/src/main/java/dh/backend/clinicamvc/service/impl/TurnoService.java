package dh.backend.clinicamvc.service.impl;

<<<<<<< HEAD
import ch.qos.logback.classic.Logger;
import dh.backend.clinicamvc.dao.IDAO;

import dh.backend.clinicamvc.dao.impl.TurnoDao;
=======

>>>>>>> 5f228ecfadda3ef12e3de29bcbc921feada6ff8b
import dh.backend.clinicamvc.dto.request.TurnoRequestDto;
import dh.backend.clinicamvc.dto.response.OdontologoResponseDto;
import dh.backend.clinicamvc.dto.response.PacienteResponseDto;
import dh.backend.clinicamvc.dto.response.TurnoResponseDto;
<<<<<<< HEAD
import dh.backend.clinicamvc.model.Odontologo;
import dh.backend.clinicamvc.model.Paciente;
import dh.backend.clinicamvc.model.Turno;
import dh.backend.clinicamvc.service.ITurnoService;
=======
import dh.backend.clinicamvc.entity.Paciente;
import dh.backend.clinicamvc.entity.Odontologo;
import dh.backend.clinicamvc.entity.Turno;
import dh.backend.clinicamvc.exception.ResourcesNotFoundException;
import dh.backend.clinicamvc.repository.IOdontologoRepository;
import dh.backend.clinicamvc.repository.IPacienteRepository;
import dh.backend.clinicamvc.repository.ITurnoRepository;
import dh.backend.clinicamvc.service.ITurnoService;
import org.apache.coyote.BadRequestException;
>>>>>>> 5f228ecfadda3ef12e3de29bcbc921feada6ff8b
import org.modelmapper.ModelMapper;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
<<<<<<< HEAD
=======
import java.util.Optional;
>>>>>>> 5f228ecfadda3ef12e3de29bcbc921feada6ff8b

@Service
public class TurnoService implements ITurnoService {

<<<<<<< HEAD
    private IDAO<Turno> turnoIDAO;
    private IDAO<Paciente> pacienteIDAO;
    private IDAO<Odontologo> odontologoIDAO;
=======
    private IPacienteRepository pacienteRepository;
    private IOdontologoRepository odontologoRepository;
    private ITurnoRepository turnoRepository;
>>>>>>> 5f228ecfadda3ef12e3de29bcbc921feada6ff8b
    private static org.slf4j.Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);

    /*Objeto de mapeo*/
    private ModelMapper modelMapper;

<<<<<<< HEAD
    public TurnoService(IDAO<Turno> turnoIDAO, IDAO<Paciente> pacienteIDAO, IDAO<Odontologo> odontologoIDAO, ModelMapper modelMapper) {
        this.turnoIDAO = turnoIDAO;
        this.pacienteIDAO = pacienteIDAO;
        this.odontologoIDAO = odontologoIDAO;
        /*Objeto de mapeo*/
=======
    public TurnoService(IPacienteRepository pacienteRepository, IOdontologoRepository odontologoRepository, ITurnoRepository turnoRepository, ModelMapper modelMapper) {
        this.pacienteRepository = pacienteRepository;
        this.odontologoRepository = odontologoRepository;
        this.turnoRepository = turnoRepository;
>>>>>>> 5f228ecfadda3ef12e3de29bcbc921feada6ff8b
        this.modelMapper = modelMapper;
    }

    @Override
<<<<<<< HEAD
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
=======
    public TurnoResponseDto registrarTurno(TurnoRequestDto turnoRequestDto) throws BadRequestException {
        Optional<Paciente> p = pacienteRepository.findById(turnoRequestDto.getPaciente_id());
        LOGGER.info("Paciente -  "+ p.isPresent() );
        Optional<Odontologo> o = odontologoRepository.findById(turnoRequestDto.getOdontologo_id());
        LOGGER.info("Odontologo -  "+ o.isPresent()  );
        Turno turnoARegistrar = new Turno();
        Turno turnoGuardado = null;
        TurnoResponseDto turnoADevolver = null;
        if(p.isPresent() && o.isPresent()){
            turnoARegistrar.setPaciente(p.get());
            turnoARegistrar.setOdontologo(o.get());
            turnoARegistrar.setFecha(LocalDate.parse(turnoRequestDto.getFecha()));
            turnoGuardado = turnoRepository.save(turnoARegistrar);
>>>>>>> 5f228ecfadda3ef12e3de29bcbc921feada6ff8b
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
<<<<<<< HEAD

        }
        return turnoADevolver;
=======
            LOGGER.info("Turno registrado: " + turnoADevolver);
            return turnoADevolver;
        }else{
            LOGGER.info("El Turno no se puede registrar por que el paciente :" +  p.isPresent() + " , odontologo : " +o.isPresent() );
            throw new BadRequestException("{\"messages\":  \"El paciente u odontologo no existen\"}");
        }



>>>>>>> 5f228ecfadda3ef12e3de29bcbc921feada6ff8b
    }

    @Override
    public TurnoResponseDto buscarTurnoPorId(Integer idTurno) {
<<<<<<< HEAD
        Turno turnoEncontrado = turnoIDAO.buscarPorId(idTurno);
        TurnoResponseDto turnoADevolver = mapToResponseDTO(turnoEncontrado);
        return turnoADevolver;
=======
        Optional<Turno> turnoEncontradoOptional = turnoRepository.findById(idTurno);
        /*Validar si existe el turno bajo la API de JPA*/
        if(turnoEncontradoOptional.isPresent()){
            TurnoResponseDto turnoADevolver = mapToResponseDTO(turnoEncontradoOptional.get());
            LOGGER.info("Turno encontrado por id: " + turnoADevolver);
            return turnoADevolver;
        }else{
            return null;
        }

>>>>>>> 5f228ecfadda3ef12e3de29bcbc921feada6ff8b
    }

    @Override
    public List<TurnoResponseDto> buscarTodosLosTurnos() {
        List<TurnoResponseDto> listaTurnosResponseDto = new ArrayList<>();
<<<<<<< HEAD
        List<Turno> listaTurnosADevolver = turnoIDAO.buscarTodos();
=======
        List<Turno> listaTurnosADevolver = turnoRepository.findAll();
>>>>>>> 5f228ecfadda3ef12e3de29bcbc921feada6ff8b
        TurnoResponseDto turnoADevolver = null;
        for (Turno t: listaTurnosADevolver) {
            turnoADevolver = mapToResponseDTO(t);
            listaTurnosResponseDto.add(turnoADevolver);
        }
<<<<<<< HEAD

=======
        LOGGER.info("Lista de turnos generada: " + listaTurnosResponseDto);
>>>>>>> 5f228ecfadda3ef12e3de29bcbc921feada6ff8b
        return listaTurnosResponseDto;
    }

    @Override
    public void actualizarTurno(Integer idTurno,TurnoRequestDto turnoRequestDto) {


        Integer idPaciente = turnoRequestDto.getPaciente_id();
        Integer idOdontologo = turnoRequestDto.getOdontologo_id();

<<<<<<< HEAD
        /*Buscando dependencias*/
        Paciente p = pacienteIDAO.buscarPorId(idPaciente);
        Odontologo o = odontologoIDAO.buscarPorId(idOdontologo);

        /*Solo para saber si existe , si existe lo modifica si no no lo modifica*/
        Turno turnoABuscarPorId = turnoIDAO.buscarPorId(idTurno);

        LOGGER.info("Paciente encontrado :" +  (p != null ? true : false) );
        LOGGER.info("Odontologo encontrado :" +  (o != null ? true : false) );
        LOGGER.info("Turno encontrado :" +  (turnoABuscarPorId != null ? true : false) );
=======
        /*Buscando dependencias bajo el Framework de Hibernate - JPA*/
        Optional<Paciente> p = pacienteRepository.findById(idPaciente);
        Optional<Odontologo> o = odontologoRepository.findById(idOdontologo);

        /*Solo para saber si existe , si existe lo modifica si no no lo modifica*/
        Optional<Turno> turnoABuscarPorId = turnoRepository.findById(idTurno);

        LOGGER.info("Paciente encontrado :" +  (p.isPresent() ? true : false) );
        LOGGER.info("Odontologo encontrado :" +  (o.isPresent() ? true : false) );
        LOGGER.info("Turno encontrado :" +  (turnoABuscarPorId.isPresent() ? true : false) );
>>>>>>> 5f228ecfadda3ef12e3de29bcbc921feada6ff8b


        Turno turnoAModificar = new Turno();

<<<<<<< HEAD
        if(p != null && o != null && turnoABuscarPorId != null){

            turnoAModificar.setId(idTurno);
            turnoAModificar.setPaciente(p);
            turnoAModificar.setOdontologo(o);
            turnoAModificar.setFecha(LocalDate.parse(turnoRequestDto.getFecha()));
            LOGGER.info("Turno a modificar "+ turnoAModificar);
            turnoIDAO.actualizar(turnoAModificar);
=======
        if(p.isPresent() && o.isPresent() && turnoABuscarPorId.isPresent()){

            turnoAModificar.setId(idTurno);
            turnoAModificar.setPaciente(p.get());
            turnoAModificar.setOdontologo(o.get());
            turnoAModificar.setFecha(LocalDate.parse(turnoRequestDto.getFecha()));
            LOGGER.info("Turno a modificar "+ turnoAModificar);
            turnoRepository.save(turnoAModificar);
>>>>>>> 5f228ecfadda3ef12e3de29bcbc921feada6ff8b
        }

    }

    @Override
<<<<<<< HEAD
    public void eliminarTurno(Integer id) {
        turnoIDAO.eliminar(id);
    }

=======
    public void eliminarTurno(Integer id) throws ResourcesNotFoundException {
        Optional<Turno> optionalTurno = turnoRepository.findById(id);

        if(optionalTurno.isPresent()){
            LOGGER.info("Borrando Turno con id: " + id  );
            turnoRepository.deleteById(id);
        }else{
            throw  new ResourcesNotFoundException("{\"messages\":  \"Turno no encontrado\"}");
        }


    }


    /**
     * Busqueda de metodos HQL
     */

    @Override
    public List<TurnoResponseDto> buscarTurnoEntreFechas(LocalDate startDate, LocalDate endDate) {
        List<Turno> listaTurnos = turnoRepository.buscarTurnoEntreFechas(startDate,endDate);
        List<TurnoResponseDto> listaTurnosResponseDto = new ArrayList<>();
        TurnoResponseDto turnoAuxiliar = null;
        for (Turno t: listaTurnos) {
            turnoAuxiliar = mapToResponseDTO(t);
            listaTurnosResponseDto.add(turnoAuxiliar);
        }
        LOGGER.info("Tamaño de la lista de turnos reponse dto´s "+ listaTurnosResponseDto.size());
        return listaTurnosResponseDto;
    }

    @Override
    public List<TurnoResponseDto> buscarTurnoPosteriorFecha(LocalDate startDate) {
        List<Turno> listaTurnoPosteriorFecha = turnoRepository.findByStartDateTurnosAfter(startDate);
        List<TurnoResponseDto> listaTurnosResponseDto = new ArrayList<>();
        for (Turno t: listaTurnoPosteriorFecha) {
            listaTurnosResponseDto.add( mapToResponseDTO(t));
        }
        LOGGER.info("Tamaño de la lista de turnos posteriores a la fecha "+ startDate + " " + listaTurnosResponseDto.size());
        return listaTurnosResponseDto;
    }


>>>>>>> 5f228ecfadda3ef12e3de29bcbc921feada6ff8b
    /*Metodo de mapeo*/
    private TurnoResponseDto mapToResponseDTO( Turno turno){
        TurnoResponseDto turnoResponseDto = modelMapper.map(turno, TurnoResponseDto.class);
        turnoResponseDto.setPaciente(modelMapper.map(turno.getPaciente(), PacienteResponseDto.class));
        turnoResponseDto.setOdontologo(modelMapper.map(turno.getOdontologo(), OdontologoResponseDto.class));
<<<<<<< HEAD

        return turnoResponseDto;
    }
=======
        LOGGER.info("Mapeando tipos de datos: " + turnoResponseDto);
        return turnoResponseDto;
    }



>>>>>>> 5f228ecfadda3ef12e3de29bcbc921feada6ff8b
}
