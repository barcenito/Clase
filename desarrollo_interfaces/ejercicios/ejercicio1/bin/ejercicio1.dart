
import 'dart:io';
void main(List<String> arguments) {
	while (true) {
		// BUCLE DE CALCULADORA
		print('Ingrese el primer número (o "salir" para terminar):');
		String? input1 = stdin.readLineSync();
		if (input1 == null) {
      		print('Error: No se pudo leer la entrada.');
      		continue;
    	}
		print("entrada : $input1");
		if (input1 == 'salir') break;
		double num1 = double.parse(input1!);
		print('Ingrese el segundo número:');
		String? input2 = stdin.readLineSync();
		double num2 = double.parse(input2!);
		print('Ingrese la operación (+, -, *, /):');
		String? operation = stdin.readLineSync();
		double result;
		switch (operation) {
			case '+':
				result = num1 + num2;
				break;
			case '-':
				result = num1 - num2;
				break;
			case '*':
				result = num1 * num2;
				break;
			case '/':
				if (num2 == 0) {
					print('Error: División por cero no permitida.');
					continue;
				}
				result = num1 / num2;
				break;
			default:
				print('Operación no válida.');
				continue;
		}
		print('El resultado es: $result');
	}
}
