from pewee import *
db = SqliteDatabase('gestion.db')
class Trabajador(Model):
	id = IntegerField()
	nombre = CharField()
	ap1 = CharField()
	ap2 = CharField()
	rol = IntegerField()
	superior = ForeignKeyField('self', null=True, backref='subordinados')
	def Meta():
		database = db