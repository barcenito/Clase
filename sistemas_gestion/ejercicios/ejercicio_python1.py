import os

class Producto():
    def __init__(self, nombre, precio, cantidad):
        self.nombre = nombre
        self.precio = precio
        self.cantidad = cantidad
    #getters
    def get_nombre(self):
        return self.nombre
    def get_precio(self):
        return self.precio
    def get_cantidad(self):
        return self.cantidad
    #setters
    def set_nombre(self, nombre):
        self.nombre = nombre
    def set_precio(self, precio):
        self.precio = precio
    def set_cantidad(self, cantidad):
        self.cantidad = cantidad
    

class ProductosDAO():
    def __init__(self):
        self.productos = []
        self.path = './base_datos.txt'

    def agregar_producto(self, producto):
        self.productos.append(producto)

    def cargar_productos(self):
        if os.path.exists(self.path):
            with open(self.path, 'r') as file:
                return 0

    def listar_productos(self):
        return self.productos


file_path = './base_datos.txt'
if os.path.exists(file_path):
    with open(file_path, 'r') as file:
        lines = file.readlines()
        for line in lines:
            print(line.strip())