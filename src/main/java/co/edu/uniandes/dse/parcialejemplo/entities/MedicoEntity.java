package co.edu.uniandes.dse.parcialejemplo.entities;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;
import uk.co.jemos.podam.common.PodamExclude;

@Data
@Entity
public class MedicoEntity extends BaseEntity {
    private String nombre;
    private String apellido;
    private String registro;
    
    @PodamExclude
	@ManyToMany
    private List<EspecialidadEntity> especialidades =new ArrayList<>();

}