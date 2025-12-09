from peewee import *

# Instancia de la base de datos que se usará en todo el proyecto
db = DatabaseProxy()

# Un modelo base que especifica nuestra base de datos
class BaseModel(Model):
    class Meta:
        database = db