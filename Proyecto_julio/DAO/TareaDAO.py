from models.Trabajador import Trabajador
from models.Tarea import Tarea
import peewee
class TareaDAO():
	def __init__(self):
		if not Tarea.table.exists():
			Tarea.create_table()
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