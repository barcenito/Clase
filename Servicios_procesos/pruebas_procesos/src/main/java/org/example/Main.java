package org.example;

import org.example.models.Raton;

import java.util.ArrayList;

public class Main {
	static void main() {
		int N_THREADS = 2;
		int num_migas = 1000;
		ArrayList<Raton> ratones = new ArrayList<>();
		ArrayList<Thread> threads = new ArrayList<>();
		ArrayList<Thread.State> estados = new ArrayList<>();

		for (int i = 0; i < N_THREADS; i++) {
			Raton r = new Raton("ratoncito"+ i);
			ratones.add(r);
			Thread t =  new Thread(r);
			threads.add(t);
			estados.add(t.getState());
			t.start();
		}
		ratones.forEach(raton -> {raton.run();});
	}
}
