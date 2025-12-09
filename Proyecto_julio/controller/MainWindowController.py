import os
from PySide6.QtWidgets import QMainWindow, QTreeWidget, QTreeWidgetItem, QListWidgetItem
from PySide6.QtCore import Qt
from PySide6 import uic

class MainWindow(QMainWindow):
    def __init__(self):
        super().__init__()
        ui_file_path = os.path.join(os.path.dirname(__file__), "main_window.ui")
        uic.loadUi(ui_file_path, self)

    def populate_workers_tree(self, workers):
        self.tree_workers.clear()
        
        workers_by_id = {worker.id: worker for worker in workers}
        items_by_id = {}
        root_items = []

        # Recorremos todos los trabajadores para crear su item en el arbol
        for worker in workers:
            # Creamos el item con las columnas Nombre y Cargo
            item = QTreeWidgetItem([f"{worker.nombre} {worker.ap1}", worker.cargo])
            # Guardamos el objeto 'worker' completo en el item para usarlo después (ej. al editar)
            item.setData(0, Qt.UserRole, worker)
            items_by_id[worker.id] = item

        # Recorremos de nuevo para construir la jerarquía
        for worker in workers:
            # Si no tiene superior, es un nodo raíz
            if worker.superior is None:
                root_items.append(items_by_id[worker.id])
            # Si tiene superior, lo añadimos como hijo de su superior
            elif worker.superior.id in items_by_id:
                parent_item = items_by_id[worker.superior.id]
                child_item = items_by_id[worker.id]
                parent_item.addChild(child_item)
        
        self.tree_workers.addTopLevelItems(root_items)
        self.tree_workers.expandAll()

    def set_tasks(self, tasks):
        self.list_tasks.clear()
        for task in tasks:
            self.list_tasks.addItem(QListWidgetItem(str(task)))
    
    def get_selected_worker(self):
        selected_items = self.tree_workers.selectedItems()
        if selected_items:
            # Devolvemos el objeto 'worker' que guardamos antes
            return selected_items[0].data(0, Qt.UserRole)
        return None
