from models.Trabajador import Trabajador
from models.Tarea import Tarea
import peewee 
class TareaDAO:
	def __init__(self):
		# La forma correcta de verificar si la tabla existe
		if not Tarea.table_exists():
			Tarea.create_table()
			# Opcional: Insertar datos de ejemplo
			# Tarea.create(titulo='Diseñar UI', descripcion='Diseñar la interfaz principal de la aplicación.')
			# Tarea.create(titulo='Implementar Lógica', descripcion='Codificar la lógica de negocio.')

	def add_tarea(self, descripcion):
		try:
			new_tarea = Tarea(descripcion)
		except peewee.PeeweeException as e:
			print(f"Error añadiendo trabajdor : {e}")
			return None
	def delete_tarea(self, id_tarea):
		try:
			q = Tarea.delete().where(Tarea.id == id_tarea)
			deleted_rows = q.execute()
			return deleted_rows > 0
				
		except peewee.PeeweeException as e:
			print(f"Error borrando: {e}")
			return False
	def get_all_tareas(self):
		try:
			return list(Tarea.select())
		except peewee.PeeweeException as e:
			print(f"Error obteniendo todas las tareas: {e}")
			return []