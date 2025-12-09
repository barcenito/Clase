import sys
import os
from PySide6.QtWidgets import QApplication
from peewee import SqliteDatabase
from controller.AppController import AppController # <-- El nuevo controlador
from database.database import db as db_proxy
from models.Trabajador import Trabajador
from models.Tarea import Tarea
from models.Asignacion import Asignacion

def inicializar_bd():
    db_real = SqliteDatabase('proyecto.db')
    db_proxy.initialize(db_real)
    db_proxy.connect()
    db_proxy.create_tables([Trabajador, Tarea, Asignacion], safe=True)

if __name__ == "__main__":
    app = QApplication(sys.argv)

    # Cargar hoja de estilos
    style_file_path = os.path.join(os.path.dirname(__file__), "view", "style.qss")
    if os.path.exists(style_file_path):
        with open(style_file_path, "r") as f:
            app.setStyleSheet(f.read())
    
    # Inicializar BD
    inicializar_bd()

    # Iniciar la aplicación a través del AppController
    app_controller = AppController()
    app_controller.show_main_window()
    
    sys.exit(app.exec())
