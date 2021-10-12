package it.prova.test;

import it.prova.dao.FilmDao;
import it.prova.dao.RegistaDao;
import it.prova.model.Film;
import it.prova.model.Regista;

public class TestFilm2 {
public static void main(String[] args) {
	
	FilmDao filmDAOInstance = new FilmDao();
	RegistaDao registaDaoInstance = new RegistaDao();
	
	
	Film film = filmDAOInstance.selectJoinById(0L);
	Regista regista = registaDaoInstance.selectById(0L);
	System.out.println(film);
	
	
	registaDaoInstance.insert(new Regista("Mario","rossi",4));
	
	regista = new Regista("Anna","grey",3);
	film = new Film("La vita è bella","drama",7);
	film.setRegista(regista);
	
	//registaDaoInstance.insert(regista);
	filmDAOInstance.insertCompleto(film);
	
	//film.setRegista(registaDaoInstance.selectById(0L));
	//filmDAOInstance.insertCompleto(film);
	
	System.out.println("Situazione sul db....");
	for (Film filmItem : filmDAOInstance.list()) {
		System.out.println(filmItem);
	}
	
	/*for (Regista registaItem : registaDaoInstance.list()) {
		System.out.println(registaItem);
	}*/
	
	System.out.println(registaDaoInstance.registaMaxOscar());
	
	
	
}
}
