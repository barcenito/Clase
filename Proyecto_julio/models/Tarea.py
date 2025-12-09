from peewee import *
from database.database import BaseModel


class Tarea(BaseModel):
    titulo = CharField(unique=True)
    descripcion = TextField()

    def __str__(self):
        return self.titulo
