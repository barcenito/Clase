from peewee import *
from database.database import BaseModel
from models.Trabajador import Trabajador
from models.Tarea import Tarea


class Asignacion(BaseModel):
	empleado = ForeignKeyField(Trabajador, backref='asignaciones')
	tarea = ForeignKeyField(Tarea,backref='trabajadores')
	