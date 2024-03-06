package co.edu.uniandes.dse.parcialejemplo.services;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import co.edu.uniandes.dse.parcialejemplo.entities.MedicoEntity;
import co.edu.uniandes.dse.parcialejemplo.exceptions.IllegalOperationException;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;



@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@Import(MedicoService.class)
public class MedicoServiceTest {
    @Autowired
    private MedicoService medicoService;
    @Autowired
    private TestEntityManager testEntityManager;

    private PodamFactory factory = new PodamFactoryImpl();

    private List<MedicoEntity> medicoList = new ArrayList<>();

    @BeforeEach
	void setUp() {
		clearData();
		insertData();
	}
    private void clearData() {
		testEntityManager.getEntityManager().createQuery("delete from EspecialidadEntity").executeUpdate();
	}

    private void insertData() {
		for (int i = 0; i < 3; i++) {
			MedicoEntity medicoEntity = factory.manufacturePojo(MedicoEntity.class);
			testEntityManager.persist(medicoEntity);
			medicoList.add(medicoEntity);
		}
	}
    @Test
	void testCreateMedico() throws IllegalOperationException {
		MedicoEntity newEntity = factory.manufacturePojo(MedicoEntity.class);
		
		newEntity.setNombre("Juan");
        newEntity.setApellido("Juanda");
        newEntity.setRegistro("RM8430");

		MedicoEntity result = medicoService.crearMedico(newEntity);
		assertNotNull(result);

		MedicoEntity entity = testEntityManager.find(MedicoEntity.class, result.getId());

		assertEquals(newEntity.getId(), entity.getId());
		assertEquals(newEntity.getNombre(), entity.getNombre());
		assertEquals(newEntity.getApellido(), entity.getApellido());
		assertEquals(newEntity.getRegistro(), entity.getRegistro());
	}
    @Test
	void testCreateMedicoRMFALSO() throws IllegalOperationException {
        assertThrows(IllegalOperationException.class, ()->{
            MedicoEntity newEntity = factory.manufacturePojo(MedicoEntity.class);
            
            newEntity.setNombre("Elman");
            newEntity.setApellido("Malevolo");
            newEntity.setRegistro("430");

            medicoService.crearMedico(newEntity);
            
        });
	}

}
