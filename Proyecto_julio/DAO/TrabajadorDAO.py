from models.Trabajador import Trabajador
import peewee

class TrabajadorDAO:
    def __init__(self):
        # Crea la tabla si no existe (requiere que Trabajador tenga configurada la Meta database)
        if not Trabajador.table_exists():
            Trabajador.create_table()

    def add_trabajador(self, nombre, apellido1, apellido2, rol, cargo, superior):
        try:
            # .create() instancia el modelo y lo guarda en la BD automáticamente
            nuevo_trabajador = Trabajador.create(
                nombre=nombre,
                ap1=apellido1,  # <-- CORRECCIÓN: el campo del modelo es 'ap1'
                ap2=apellido2,   # <-- CORRECCIÓN: el campo del modelo es 'ap2'
                rol=rol,
                superior=superior,
                cargo=cargo
            )
            return nuevo_trabajador
        except peewee.PeeweeException as e:
            print(f"Error al añadir trabajador: {e}")
            return None

    def get_all_trabajadores(self):
        # .select() devuelve una consulta iterable
        return list(Trabajador.select())

    def get_trabajador_by_id(self, id_trabajador):
        try:
            return Trabajador.get_by_id(id_trabajador)
        except Trabajador.DoesNotExist:
            return None

    def update_trabajador(self, id_trabajador, data):
        try:
            trabajador = Trabajador.get_by_id(id_trabajador)
            
            trabajador.nombre = data["nombre"]
            trabajador.ap1 = data["apellido1"]
            trabajador.ap2 = data["apellido2"]
            trabajador.rol = data["rol"]
            trabajador.cargo = data["cargo"]
            trabajador.superior = data["superior"]
            
            trabajador.save()
            return trabajador
            
        except Trabajador.DoesNotExist:
            return None
        except peewee.PeeweeException as e:
            print(f"Error al actualizar: {e}")
            return None

    def delete_trabajador(self, id_trabajador):
        try:
            # Esto ejecuta: DELETE FROM trabajador WHERE id = id_trabajador
            # Es más rápido porque no necesita cargar el objeto primero si no quieres.
            q = Trabajador.delete().where(Trabajador.id == id_trabajador)
            filas_borradas = q.execute()
            return filas_borradas > 0 # Devuelve True si borró algo
        except peewee.PeeweeException as e:
            print(f"Error borrando: {e}")
            return False


