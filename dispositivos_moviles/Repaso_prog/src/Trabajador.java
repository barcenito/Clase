import java.time.*;

import static java.lang.Math.ceil;

public class Trabajador extends Usuario{
	public enum cargo_enum {Director, Secretario, Profesor};
	public String especialidad;
	public LocalDate fecha_comienzo;
	public int IRPF;
	public cargo_enum cargo;
	public double salario;

	public Trabajador(String especialidad, LocalDate fecha_comienzo, int IRPF, cargo_enum cargo) {
		this.especialidad = especialidad;
		this.fecha_comienzo = fecha_comienzo;
		this.IRPF = IRPF;
		this.cargo = cargo;
	}

	public Trabajador(int id, String nombre, String email, String especialidad, LocalDate fecha_comienzo, int IRPF, cargo_enum cargo) {
		super(id, nombre, email);
		this.especialidad = especialidad;
		this.fecha_comienzo = fecha_comienzo;
		this.IRPF = IRPF;
		this.cargo = cargo;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public LocalDate getFecha_comienzo() {
		return fecha_comienzo;
	}

	public void setFecha_comienzo(LocalDate fecha_comienzo) {
		this.fecha_comienzo = fecha_comienzo;
	}

	public int getIRPF() {
		return IRPF;
	}

	public void setIRPF(int IRPF) {
		this.IRPF = IRPF;
	}

	public cargo_enum getCargo() {
		return cargo;
	}

	public void setCargo(cargo_enum cargo) {
		this.cargo = cargo;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public double calcular_salario(LocalDate fecha){
		double sb = 1500;
		if(getCargo()==cargo.Director){
			sb += 500;
		} else if (getCargo()==cargo.Secretario) {
			sb += 300;
 		}
		double n_years = LocalDate.now().getYear() - getFecha_comienzo().getYear();
		sb += (ceil(n_years/3) * 90);
		sb = sb - ((sb*IRPF)/100);
		return sb;
	}
}
