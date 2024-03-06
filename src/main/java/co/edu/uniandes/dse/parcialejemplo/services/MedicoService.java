package co.edu.uniandes.dse.parcialejemplo.services;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniandes.dse.parcialejemplo.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import co.edu.uniandes.dse.parcialejemplo.repositories.MedicoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MedicoService {
    @Autowired
    MedicoRepository medicoRepository;

    @Transactional
    public MedicoEntity crearMedico(MedicoEntity medico) throws IllegalOperationException {
        log.info("Inicia proceso de creación del autor");
        if (medico.getRegistro().substring(0, 2)!="RM"){
            throw new IllegalOperationException("El registro no tiene el formato indicado");
        }
        log.info("Finaliza proceso de creación del autor");
        return medicoRepository.save(medico);
    }
}
