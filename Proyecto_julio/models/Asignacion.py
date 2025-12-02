from pewee import *
db = SqliteDatabase('gestion.db')
class Asignacion(Model):
	empleado = ForeignKeyField(Trabajador, backref='asignaciones')
	tarea = ForeignKeyField(Tarea,backref='trabajadores')
	def Meta():
		database = db
	