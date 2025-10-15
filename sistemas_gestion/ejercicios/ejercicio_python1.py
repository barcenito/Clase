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
        self.path = './base_datos.txt'
        self.file = None

    def conectar(self):
        if os.path.exists(self.path):
            self.file = open(self.path, 'rw+')
    
    def cerrar_conexion(self):
        if self.file:
            self.file.close()

    def guardar_producto(self, producto):
        if self.file:
            self.file.write(f"{producto.get_nombre()}-{producto.get_precio()}-{producto.get_cantidad()}\n")
            self.file.flush()
    
    def buscar_producto(self, nombre):
        for line in self.file:
            datos = line.strip().split('-')
            if datos[0] == nombre:
                return Producto(datos[0], float(datos[1]), int(datos[2]))

    def listar_productos(self):
        return self.productos

    def add_productos(self, productos):
        self.conectar()
        for producto in productos:
            self.guardar_producto(producto)
            
file_path = './base_datos.txt'
if os.path.exists(file_path):
    with open(file_path, 'r') as file:
        lines = file.readlines()
        for line in lines:
            print(line.strip())