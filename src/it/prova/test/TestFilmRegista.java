package it.prova.test;

import java.util.ArrayList;
import java.util.List;

import it.prova.dao.FilmDao;
import it.prova.dao.RegistaDao;
import it.prova.model.Film;
import it.prova.model.Regista;

public class TestFilmRegista {

	public static void main(String[] args) {
	
		// creo instance
				FilmDao filmDaoInstance = new FilmDao();
				RegistaDao registaDaoInstance = new RegistaDao();
				
				//creo regista
				List<Regista> listaRegisti = new ArrayList<Regista>();
				Regista regista1 = new Regista("Mario","rossi",4);
				Regista regista2 = new Regista("Giuseppe","verdi",1);
				listaRegisti.add(regista1);
				listaRegisti.add(regista2);
				
				//inserisco regista
				registaDaoInstance.insert(regista1);
				registaDaoInstance.insert(regista2);
				
				//controllo regista insert
				for (Regista registaItem : registaDaoInstance.list()) {
					System.out.println(registaItem);
				}
				
				
				//inserisco film
				Film film1 =new Film("Il gladiatore","storico",2);
				Film film2 =new Film("Una notte da leoni","comico",3);
				
				filmDaoInstance.insertCompleto(film1);
				filmDaoInstance.insertCompleto(film2);
				
				
			
				//
				for (Film filmItem : filmDaoInstance.list()) {
					System.out.println(filmItem);
				}

			//	System.out.println(registaDaoInstance.findByOscarVinti());
				System.out.println(registaDaoInstance.registaMaxOscar());
		
	}

}
