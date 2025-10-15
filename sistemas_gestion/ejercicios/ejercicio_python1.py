import os

class Producto():
    def __init__(self, _id, nombre, precio):
        self.id = _id
        self.nombre = nombre
        self.precio = precio
    def get_nombre(self):
        return self.nombre
    def get_precio(self):
        return self.precio

class ProductosDAO():
    def __init__(self):
        self.productos = []
        self.path = './base_datos.txt'
        self.cargar_productos()

    def agregar_producto(self, producto):
        self.productos.append(producto)

    def cargar_productos(self):
        if os.path.exists(self.path):
            with open(self.path, 'r') as file:
                return

    def listar_productos(self):
        return self.productos


file_path = './base_datos.txt'
if os.path.exists(file_path):
    with open(file_path, 'r') as file:
        lines = file.readlines()
        for line in lines:
            print(line.strip())