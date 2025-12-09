from peewee import CharField, ForeignKeyField
from database.database import BaseModel


class Trabajador(BaseModel):
    nombre = CharField()
    ap1 = CharField()
    ap2 = CharField(null=True)
    rol = CharField()
    cargo = CharField()
    # 'self' crea una relación donde un trabajador puede tener a otro como superior.
    # null=True permite que haya trabajadores sin un superior (ej. el director).
    superior = ForeignKeyField('self', backref='subordinados', null=True)


    def __str__(self):
        return f"{self.nombre} {self.ap1} ({self.cargo})"