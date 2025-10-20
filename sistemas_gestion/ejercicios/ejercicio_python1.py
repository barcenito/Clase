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
        self.productos = []

    
    def conectar(self, mode):   
        if os.path.exists(self.path):
            self.file = open(self.path, mode)
    
    def cerrar_conexion(self):
        if self.file:
            self.file.close()

    def update_prods_list(self):
        self.conectar('r')
        for line in self.file:
            datos = line.strip().split('-')
            self.productos.append(Producto(datos[0], float(datos[1]), int(datos[2])))
        self.cerrar_conexion()

    def get_productos(self):
        self.update_prods_list()
        return self.productos
    
    def set_productos(self, productos):
        self.productos = productos

    def buscar_producto(self, nombre):
        self.update_prods_list()
        for producto in self.productos:
            if producto.get_nombre() == nombre:
                return producto
        return None

    def guardar_producto(self, producto):
        self.productos.append(producto)

    def guardar_producto_bd(self, producto):
        self.conectar('a+')
        if self.file:
            for line in self.file:
                datos = line.strip().split('-')
                if datos[0] == producto.get_nombre():
                    line = f"{producto.get_nombre()}-{producto.get_precio()}-{producto.get_cantidad()}\n"
                    print("Producto actualizado en la base de datos.\n")
                    return
                else:
                    self.file.seek(0, os.SEEK_END)
                    self.file.write(f"{producto.get_nombre()}-{producto.get_precio()}-{producto.get_cantidad()}\n")
                    self.file.flush()
        self.cerrar_conexion()

    def update_bd(self):
        for producto in self.productos:
            self.guardar_producto_bd(producto)

class ProductosController():
    def __init__(self):
        self.dao = ProductosDAO()
        self.dao.update_prods_list()
    def menu(self):
        while True:
            print("1. Agregar Producto")
            print("2. Buscar Producto")
            print("3. Listar Productos")
            print("4. Salir")
            opcion = input("Seleccione una opción: ")
            if opcion == '1':
                self.add_producto()
            elif opcion == '2':
                self.buscar_producto()
            elif opcion == '3':
                self.listar_productos()
            elif opcion == '4':
                print("Saliendo del programa.")
                self.dao.update_bd()
                break
            else:
                print("Opción no válida. Intente de nuevo.")

    def add_producto(self):
        nombre = input("Ingrese el nombre del producto: ")
        precio = float(input("Ingrese el precio del producto: "))
        cantidad = int(input("Ingrese la cantidad del producto: "))
        nuevo_producto = Producto(nombre, precio, cantidad)
        self.dao.guardar_producto(nuevo_producto)
        print("Producto agregado exitosamente.")
        self.dao.update_bd()

    def listar_productos(self):
        productos = self.dao.get_productos()
        for producto in productos:
            print(f"Nombre: {producto.get_nombre()}, Precio: {producto.get_precio()}, Cantidad: {producto.get_cantidad()}")

    def buscar_producto(self):
        nombre = input("Ingrese el nombre del producto a buscar: ")
        producto = self.dao.buscar_producto(nombre)
        if producto:
            print(f"Producto encontrado: {producto.get_nombre()}, Precio: {producto.get_precio()}, Cantidad: {producto.get_cantidad()}")
        else:
            print("Producto no encontrado.")

if __name__ == "__main__":
    controller = ProductosController()
    controller.menu()
