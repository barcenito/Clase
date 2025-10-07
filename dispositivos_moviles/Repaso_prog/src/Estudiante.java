public class Estudiante extends Usuario {
	public String curso;

	public Estudiante(String curso) {
		this.curso = curso;
	}

	public Estudiante(int id, String nombre, String email, String curso) {
		super(id, nombre, email);
		this.curso = curso;
	}

	public String getCurso() {
		return curso;
	}

	public void setCurso(String curso) {
		this.curso = curso;
	}

	@Override
	public String toString() {
		return "Estudiante{" +
				"curso='" + curso + '\'' +
				'}';
	}
}
