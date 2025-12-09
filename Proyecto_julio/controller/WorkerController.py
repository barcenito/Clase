import os
# Los imports correctos para cargar la interfaz dinámicamente
from PySide6.QtUiTools import QUiLoader
from PySide6.QtCore import QFile
from DAO.TrabajadorDAO import TrabajadorDAO
from view.WorkerForm import WorkerForm
from PySide6.QtWidgets import QMessageBox, QDialog # <-- Añadir QDialog a los imports

class WorkerController:
    def __init__(self, main_view, on_update_callback):
        self.dao = TrabajadorDAO()
        self.main_view = main_view
        self.on_update = on_update_callback

    def open_worker_form(self, worker_to_edit=None):
        """
        Abre el formulario de trabajador. Si se pasa un objeto 'worker_to_edit',
        funciona en modo edición. De lo contrario, en modo creación.
        """
        # 1. Crear la instancia de la vista del formulario
        form = WorkerForm(parent=self.main_view)
        
        # 2. Obtener todos los trabajadores para poblar la lista de "superiores"
        all_workers = self.dao.get_all_trabajadores()

        if worker_to_edit:
            # --- MODO EDICIÓN ---
            # Un trabajador no puede ser su propio superior, lo excluimos de la lista.
            possible_superiors = [w for w in all_workers if w.id != worker_to_edit.id]
            form.populate_superiors(possible_superiors, worker_to_edit.superior)
            form.set_data(worker_to_edit) # Rellenar el formulario con datos existentes
        else:
            # --- MODO CREACIÓN ---
            form.setWindowTitle("Crear Nuevo Trabajador")
            form.populate_superiors(all_workers)

        # 3. Mostrar el formulario y esperar la interacción del usuario
        # --- LÍNEA MODIFICADA ---
        if form.exec() == QDialog.Accepted:
        # ------------------------
            # Si el usuario pulsa "OK", recogemos los datos
            data = form.get_data()

            # 4. Lógica de validación en el controlador
            if not data["nombre"] or not data["apellido1"] or not data["cargo"]:
                QMessageBox.warning(self.main_view, "Datos Incompletos", "Nombre, Primer Apellido y Cargo son campos obligatorios.")
                return # Detenemos la operación si la validación falla

            # 5. Llamar al DAO apropiado
            if worker_to_edit:
                # Actualizar el trabajador existente
                self.dao.update_trabajador(worker_to_edit.id, data)
            else:
                # Crear el nuevo trabajador
                self.dao.add_trabajador(
                    data["nombre"], data["apellido1"], data["apellido2"],
                    data["rol"], data["cargo"], data["superior"]
                )
            
            # 6. Usar el callback para notificar que los datos han cambiado
            self.on_update()