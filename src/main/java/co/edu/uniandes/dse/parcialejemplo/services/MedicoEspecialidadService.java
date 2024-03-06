package co.edu.uniandes.dse.parcialejemplo.services;

import org.modelmapper.spi.ErrorMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;
import co.edu.uniandes.dse.parcialejemplo.entities.EspecialidadEntity;
import co.edu.uniandes.dse.parcialejemplo.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.EntityNotFoundException;
import co.edu.uniandes.dse.parcialejemplo.repositories.EspecialidadRepository;
import co.edu.uniandes.dse.parcialejemplo.repositories.MedicoRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MedicoEspecialidadService {
    @Autowired
    MedicoRepository medicoRepository;
    @Autowired
    EspecialidadRepository especialidadRepository;
    
    @Transactional
	public EspecialidadEntity addEspecialidad(Long medicoId, Long especialidadId) throws EntityNotFoundException {
		log.info("Inicia proceso de asociarle una especialidad al medico con id = {0}", medicoId);
		Optional<MedicoEntity> medicoEntity = medicoRepository.findById(medicoId);
		Optional<EspecialidadEntity> especialidadEntity = especialidadRepository.findById(especialidadId);

		if (medicoEntity.isEmpty())
			throw new EntityNotFoundException("No se encontró el medico a asociar");

		if (especialidadEntity.isEmpty())
			throw new EntityNotFoundException("No se encontró la especialidad");

		medicoEntity.get().getEspecialidades().add(especialidadEntity.get());
		log.info("Termina proceso de asociarle una especialidad al medico con id = {0}", medicoId);
		return especialidadEntity.get();
	}
}
