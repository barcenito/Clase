from pewee import *
db = SqliteDatabase('gestion.db')
class Tarea(Model):
	descripcion = CharField()
	def Meta():
		database = db
