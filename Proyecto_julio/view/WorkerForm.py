from PySide6.QtWidgets import QDialog
# Importamos la clase de la interfaz compilada desde el archivo ui_workerform.py
from .ui_workerform import Ui_WorkerForm

# La clase ahora hereda de QDialog y de la clase de diseño Ui_WorkerForm
class WorkerForm(QDialog, Ui_WorkerForm):
    def __init__(self, parent=None):
        super().__init__(parent)
        # El método setupUi, que viene de Ui_WorkerForm, construye toda la interfaz
        self.setupUi(self)

        self.combo_rol.addItems(["Trabajador", "Mando", "Submando"])
        
        # Conectamos explícitamente las señales del buttonBox a los slots del QDialog
        self.buttonBox.accepted.connect(self.accept)
        self.buttonBox.rejected.connect(self.reject)

    # --- El resto de la clase permanece exactamente igual ---

    def populate_superiors(self, superiors, current_superior=None):
        self.combo_superior.clear()
        self.combo_superior.addItem("Ninguno", None)
        for superior in superiors:
            self.combo_superior.addItem(str(superior), superior)
        
        if current_superior:
            index = self.combo_superior.findData(current_superior)
            if index != -1:
                self.combo_superior.setCurrentIndex(index)

    def set_data(self, worker):
        self.setWindowTitle("Editar Trabajador")
        self.le_nombre.setText(worker.nombre)
        self.le_apellido1.setText(worker.ap1)
        self.le_apellido2.setText(worker.ap2 or "")
        self.le_cargo.setText(worker.cargo)
        self.combo_rol.setCurrentText(worker.rol)

    def get_data(self):
        return {
            "nombre": self.le_nombre.text(),
            "apellido1": self.le_apellido1.text(),
            "apellido2": self.le_apellido2.text(),
            "rol": self.combo_rol.currentText(),
            "cargo": self.le_cargo.text(),
            "superior": self.combo_superior.currentData()
        }