package org.example.examennov.Model;

import javax.persistence.*;
import java.time.LocalDate;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="Reparaciones")
public class Reparacion {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "IdReparacion")
	protected int idReparacion;
	@Column(name = "idcoche" , insertable = false, updatable = false)
	protected int idcoche;
	@Column(name = "fechareparacion")
	protected LocalDate fechareparacion;
	@Column(name = "descripcion")
	protected String descripcion;
	@Column(name = "precio")
	protected int precio;
	@Column(name = "pagado")
	protected boolean pagado;

	@ManyToOne
	@JoinColumn(name = "idcoche", referencedColumnName = "idcoche")
	protected Coche coche;

	public Reparacion() {
	}

	public Reparacion(int idReparacion, int idcoche, LocalDate fechareparacion, String descripcion, int precio, boolean pagado, Coche coche) {
		this.idReparacion = idReparacion;
		this.idcoche = idcoche;
		this.fechareparacion = fechareparacion;
		this.descripcion = descripcion;
		this.precio = precio;
		this.pagado = pagado;
		this.coche = coche;
	}

	public int getIdReparacion() {
		return idReparacion;
	}

	public void setIdReparacion(int idReparacion) {
		this.idReparacion = idReparacion;
	}

	public int getIdcoche() {
		return idcoche;
	}

	public void setIdcoche(int idcoche) {
		this.idcoche = idcoche;
	}

	public LocalDate getFechareparacion() {
		return fechareparacion;
	}

	public void setFechareparacion(LocalDate fechareparacion) {
		this.fechareparacion = fechareparacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	public boolean isPagado() {
		return pagado;
	}

	public void setPagado(boolean pagado) {
		this.pagado = pagado;
	}

	public Coche getCoche() {
		return coche;
	}

	public void setCoche(Coche coche) {
		this.coche = coche;
	}

	@Override
	public String toString() {
		return "Reparacion{" +
				"coche=" + coche.getMatricula() +
				", pagado=" + pagado +
				", precio=" + precio +
				", descripcion='" + descripcion + '\'' +
				", fechareparacion=" + fechareparacion +
				", idcoche=" + idcoche +
				", idReparacion=" + idReparacion +
				'}';
	}
}
