from PySide6.QtWidgets import QMainWindow, QTreeWidgetItem, QListWidgetItem
from PySide6.QtCore import Qt
# Importamos la clase de la interfaz compilada desde el archivo ui_main_window.py
from .ui_main_window import Ui_MainWindow

# La clase ahora hereda de QMainWindow y de la clase de diseño Ui_MainWindow
class MainWindow(QMainWindow, Ui_MainWindow):
    def __init__(self, parent=None):
        super().__init__(parent)
        # El método setupUi, que viene de Ui_MainWindow, construye toda la interfaz
        self.setupUi(self)

    # --- El resto de la clase permanece exactamente igual ---

    def populate_workers_tree(self, workers):
        """
        Limpia y rellena el árbol de trabajadores con una estructura jerárquica.
        """
        self.tree_workers.clear()
        
        items_by_id = {}
        root_items = []

        for worker in workers:
            item = QTreeWidgetItem([f"{worker.nombre} {worker.ap1}", worker.cargo])
            item.setData(0, Qt.UserRole, worker)
            items_by_id[worker.id] = item

        for worker in workers:
            if worker.superior is None:
                root_items.append(items_by_id[worker.id])
            elif worker.superior.id in items_by_id:
                parent_item = items_by_id[worker.superior.id]
                child_item = items_by_id[worker.id]
                parent_item.addChild(child_item)
        
        self.tree_workers.addTopLevelItems(root_items)
        self.tree_workers.expandAll()

    def set_tasks(self, tasks):
        """Limpia y rellena la lista de tareas."""
        self.list_tasks.clear()
        for task in tasks:
            self.list_tasks.addItem(QListWidgetItem(str(task)))
    
    def get_selected_worker(self):
        """Devuelve el objeto trabajador completo del item seleccionado en el árbol."""
        selected_items = self.tree_workers.selectedItems()
        if selected_items:
            return selected_items[0].data(0, Qt.UserRole)
        return None