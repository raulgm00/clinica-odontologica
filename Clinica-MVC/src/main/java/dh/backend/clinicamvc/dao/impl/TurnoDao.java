package dh.backend.clinicamvc.dao.impl;

import dh.backend.clinicamvc.dao.IDAO;
import dh.backend.clinicamvc.model.Turno;
import dh.backend.clinicamvc.service.impl.OdontologoService;
import dh.backend.clinicamvc.service.impl.PacienteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Component
public class TurnoDao implements IDAO<Turno> {

    private static Logger LOGGER = LoggerFactory.getLogger(TurnoDao.class);
    /*Inyecciond e dependencias con el constructor */

    private List<Turno> listaTurnos;



    public TurnoDao(List<Turno> listaTurnos) {
        this.listaTurnos = listaTurnos;
    }

    @Override
    public Turno registrar(Turno turno) {
        Integer id = this.listaTurnos.size()+1;
        turno.setId(id);
//        Integer idPaciente = turno.getPaciente().getId();
//        Integer idOdontologo = turno.getOdontologo().getId();
//        LOGGER.info("Id de Paciente " + idPaciente);
//        turno.setPaciente( ps.buscarPacientesPorId(idPaciente));
//        turno.setOdontologo( os.buscarOdontologoPorId( idOdontologo) );
        listaTurnos.add(turno);
        LOGGER.info("Turno creado" + turno);
        return turno;
    }

    @Override
    public Turno buscarPorId(Integer id) {

        for(Turno t : this.listaTurnos){
            if(t.getId().equals((id))){
                LOGGER.info("Turno encontrado por id : " + t);
                return t;
            }
        }
        LOGGER.info("Turno no encontrado por id : " + id );
        return null;
    }

    @Override
    public List<Turno> buscarTodos() {
        return this.listaTurnos;
    }

    @Override
    public void actualizar(Turno turno) {

        Integer idPaciente = turno.getPaciente().getId();
        Integer idOdontologo = turno.getOdontologo().getId();
        Boolean bandera = false;
        for( Turno t : listaTurnos){
            if(t.getId().equals(turno.getId())){
                t.setPaciente(  turno.getPaciente() );
                t.setOdontologo( turno.getOdontologo() );
                t.setFecha(turno.getFecha());
                LOGGER.info("turno actualizado " + t);
                bandera= true;
                break;
            }

        }
        if(!bandera) {
            LOGGER.info("turno no modificado " + turno.getId() );
        }

    }

    @Override
    public void eliminar(Integer id) {
        Turno turnoAEliminar = null;
        for(Turno t : listaTurnos){
            if(t.getId().equals((id))){
                turnoAEliminar = t;
                LOGGER.info("turno encontrado se procedera a eliminar: " + t);
                break;
            }
        }
        listaTurnos.remove(turnoAEliminar);
        LOGGER.info("turno eliminado: " );
    }
}
