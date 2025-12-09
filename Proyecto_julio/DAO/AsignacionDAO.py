import peewee
from models.Asignacion import Asignacion
class AsignacionDAO:
	def __init__(self):
		if not Asignacion.table_exists():
			Asignacion.create_table()
	def add_asignacion(self, descripcion):
		try:
			new_asignacion = Asignacion(descripcion)
		except peewee.PeeweeException as e:
			print(f"Error añadiendo trabajdor : {e}")
			return None
	def delete_asignacion(self, id_asignacion):
		try:
			q = Asignacion.delete().where(Asignacion.id == id_asignacion)
			deleted_rows = q.execute()
			return deleted_rows > 0
				
		except peewee.PeeweeException as e:
			print(f"Error borrando: {e}")
			return False
	def get_all_asignaciones(self):
		try:
			return list(Asignacion.select())
		except peewee.PeeweeException as e:
			print(f"Error borrando: {e}")
			return False