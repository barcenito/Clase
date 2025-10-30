import 'dart:convert';

import 'package:flutter/material.dart';
import 'package:http/http.dart' as http;

void main() {
    runApp(MyApp());
}

class MyApp extends StatelessWidget {
    MyApp({super.key});

    // This widget is the root of your application.
    List<String> images = ["https://placehold.co/600x900.png",
                                                    "https://placehold.co/600x900.png",
                                                    "https://placehold.co/600x900.png",
                                                    "https://placehold.co/600x900.png",
                                                    "https://placehold.co/600x900.png",
                                                    "https://placehold.co/600x900.png"];

    @override
    Widget build(BuildContext context) {
        // Llamamos a get_json dentro de un FutureBuilder para poder usar la función async
        return MaterialApp(
            title: 'main',
            home: Scaffold(
                appBar: AppBar(title: const Text('Claves del mapa')),
                body: FutureBuilder<Map<String, dynamic>>(
                    future: get_json(1),
                    builder: (context, snapshot) {
                        if (snapshot.connectionState == ConnectionState.waiting) {
                            return const Center(child: CircularProgressIndicator());
                        }
                        if (snapshot.hasError) {
                            return Center(child: Text('Error: ${snapshot.error}'));
                        }
                        final Map<String, dynamic> mapa = snapshot.data ?? {};
                        final keys = mapa.keys.toList();
                        // Mostrar solo los nombres de las claves en una GridView
                        return GridView.builder(
                            gridDelegate: const SliverGridDelegateWithFixedCrossAxisCount(crossAxisCount: 2),
                            itemCount: keys.length,
                            itemBuilder: (context, index) {
                                final key = keys[index];
                                return Card(
                                    margin: const EdgeInsets.all(8),
                                    child: Center(child: Text(key, textAlign: TextAlign.center)),
                                );
                            },
                        );
                    },
                ),
            ),
        );
    }

    // Función async que devuelve un Future<Map<String, dynamic>>
    Future<Map<String, dynamic>> get_json(int id) async {
        // Usar Uri.https y GET para jsonplaceholder
        final uri = Uri.https('jsonplaceholder.typicode.com', '/posts', {'id': id.toString()});
        final response = await http.get(uri);

        if (response.statusCode == 200) {
            final Map<String, dynamic> mapa = jsonDecode(response.body) as Map<String, dynamic>;
            return mapa;
        } else {
            throw Exception('Request failed: ${response.statusCode}');
        }
    }
}
