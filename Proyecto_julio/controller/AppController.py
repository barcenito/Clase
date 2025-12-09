from view.MainWindow import MainWindow # <-- CORRECCIÓN: Importamos desde MainWindow.py
from DAO.TrabajadorDAO import TrabajadorDAO
from DAO.TareaDAO import TareaDAO
from .WorkerController import WorkerController
from PySide6.QtWidgets import QMessageBox

class AppController:
    def __init__(self):
        # 1. Crear la Vista Principal
        self.view = MainWindow()

        # 2. Crear los DAOs
        self.trabajador_dao = TrabajadorDAO()
        self.tarea_dao = TareaDAO()

        # 3. Crear los Controladores de Entidad
        # Le pasamos la vista y la función para refrescar como callback
        self.worker_controller = WorkerController(self.view, self.refresh_data)
        # self.task_controller = TaskController(...) # <-- Futura implementación

        # 4. Conectar las señales de la vista a los nuevos manejadores
        self.view.btn_create_worker.clicked.connect(self.handle_create_worker)
        self.view.btn_edit_worker.clicked.connect(self.handle_edit_worker)
        
        # 5. Cargar los datos iniciales
        self.refresh_data()

    def show_main_window(self):
        """Muestra la ventana principal de la aplicación."""
        self.view.show()

    def refresh_data(self):
        """Obtiene los datos más recientes de los DAOs y actualiza la vista."""
        # Actualizar árbol de trabajadores
        workers = self.trabajador_dao.get_all_trabajadores()
        self.view.populate_workers_tree(workers)

        # Actualizar lista de tareas
        tasks = self.tarea_dao.get_all_tareas()
        self.view.set_tasks(tasks)

    def handle_create_worker(self):
        """Manejador para el botón 'Crear'. Llama al controlador sin pasarle un objeto."""
        self.worker_controller.open_worker_form()

    def handle_edit_worker(self):
        """Manejador para el botón 'Editar'. Obtiene el trabajador seleccionado y se lo pasa al controlador."""
        selected_worker = self.view.get_selected_worker()
        if selected_worker:
            self.worker_controller.open_worker_form(selected_worker)
        else:
            QMessageBox.information(self.view, "Selección Requerida", "Por favor, selecciona un trabajador del árbol para poder editarlo.")
