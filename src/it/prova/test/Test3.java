package it.prova.test;

import it.prova.dao.FilmDao;
import it.prova.dao.RegistaDao;
import it.prova.model.Film;
import it.prova.model.Regista;

public class Test3 {

	public static void main(String[] args) {
		
		System.out.println("Inizio....");
		//questi servono solo per invocare
		FilmDao filmDaoInstance = new FilmDao();
		RegistaDao registaDaoIstance = new RegistaDao();
		
		
		Film film = filmDaoInstance.selectJoinById(1L);
		
		
		film = new Film("la grande bellezza","drammatico",150);
		film.setRegista(new Regista("Paolo","Sorrentino", 10));
		filmDaoInstance.insertCompleto(film);
		
		film = new Film("la grande bellezza","drammatico",150);
		film.setRegista(new Regista("Paolo","Sorrentino", 10));
		filmDaoInstance.insertCompleto(film);
		
		
		System.out.println("Situazione sul db....");
		for (Film filmItem : filmDaoInstance.list()) {
			System.out.println(filmItem);
		}
		
		
	}


	}
