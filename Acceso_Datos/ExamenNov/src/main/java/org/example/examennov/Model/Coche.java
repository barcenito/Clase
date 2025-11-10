package org.example.examennov.Model;

import javax.persistence.*;
import java.util.List;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="Coches")
public class Coche {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name="idcoche")
	protected int idcoche;
	@Column(name = "matricula")
	protected String matricula;
	@Column(name = "marca")
	protected String marca;
	@Column(name = "km")
	protected int km;

	@OneToMany(mappedBy = "coche", cascade = CascadeType.ALL)
	private List<Reparacion> reparaciones;

	public Coche() {
	}

	public Coche(int idCoche, String matricula, int km, String marca) {
		this.idcoche = idCoche;
		this.matricula = matricula;
		this.km = km;
		this.marca = marca;
	}

	public int getIdCoche() {
		return idcoche;
	}

	public void setIdCoche(int idcoche) {
		this.idcoche = idcoche;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public int getKm() {
		return km;
	}

	public void setKm(int km) {
		this.km = km;
	}

	public List<Reparacion> getReparaciones() {
		return reparaciones;
	}

	public void setReparaciones(List<Reparacion> reparaciones) {
		this.reparaciones = reparaciones;
	}

	@Override
	public String toString() {
		return "Coche{" +
				"idCoche=" + idcoche +
				", matricula='" + matricula + '\'' +
				", marca='" + marca + '\'' +
				", km=" + km +
				", reparaciones=" + reparaciones.size() +
				'}';
	}
}
