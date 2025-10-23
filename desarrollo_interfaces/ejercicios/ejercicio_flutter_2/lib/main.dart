import 'package:flutter/material.dart';

void main() {
	runApp(MainApp());
}
class MainApp extends StatefulWidget{
	@override
	_MainApp createState() => _MainApp();}

class _MainApp extends State <MainApp>{
	
	int _counter = 0;
	void _incrementCounter(){
		// 
		setState(() {
			_counter++;
			print("polla que se come el lilipollah $_counter\n");
		});
	}

	@override
	Widget build(BuildContext context){
		return MaterialApp(
			home:Scaffold(
				body:Center(
					child:	
						Column(
							mainAxisAlignment: MainAxisAlignment.center,
							children:[

								Text("Otro texto"),
								Text('Presiona el boton $_counter veces'),
								ElevatedButton(onPressed: _incrementCounter, child: Text("Elboton"))
							],
							)
						)
				),
		);
	}



	
}



