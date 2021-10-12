package it.prova.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import it.prova.connection.MyConnection;
import it.prova.model.Film;
import it.prova.model.Regista;

public class FilmDao {


	// =============================================== LIST
	public List<Film> list() {

		Connection c = null;
		ResultSet rs = null;
		Statement s = null;
		List<Film> result = new ArrayList<Film>();

		try {

			c = MyConnection.getConnection();
			s = c.createStatement();

			rs = s.executeQuery("select * from film a inner join regista i on i.id=a.regista_id;");

			while (rs.next()) {
				Film abitanteTemp = new Film();
				abitanteTemp.setTitolo(rs.getString("titolo"));
				abitanteTemp.setGenere(rs.getString("genere"));
				abitanteTemp.setId(rs.getLong("id"));
				abitanteTemp.setDurata(rs.getInt("durata"));

				Regista indirizzoTemp = new Regista();
				indirizzoTemp.setId(rs.getLong("id"));
				indirizzoTemp.setNome(rs.getString("nome"));
				indirizzoTemp.setCognome(rs.getString("cognome"));
				indirizzoTemp.setNuomeroOscarVinti(rs.getInt("numerooscarvinti"));

				abitanteTemp.setRegista(indirizzoTemp);
				result.add(abitanteTemp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				s.close();
				c.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
		
		// =============================================== INSERT
			public int insert(Film input) {

				Connection c = null;
				PreparedStatement ps = null;
				int result = 0;

				try {

					c = MyConnection.getConnection();
					ps = c.prepareStatement("INSERT INTO film (titolo, genere, durata) VALUES (?, ?, ?);");
					ps.setString(1, input.getTitolo());
					ps.setString(2, input.getGenere());
					ps.setInt(3, input.getDurata());

					result = ps.executeUpdate();

				} catch (Exception e) {

					e.printStackTrace();

				} finally {
					try {
						ps.close();
						c.close();

					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				return result;
			}
			
			// =============================================== UPDATE
			public int update(Film input) {

				if (input == null || input.getId() < 1) {
					return 0;
				}

				Connection c = null;
				PreparedStatement ps = null;
				int result = 0;

				try {

					c = MyConnection.getConnection();
					ps = c.prepareStatement("UPDATE film SET titolo=?, genere=?, durata=? where id=?;");
					ps.setString(1, input.getTitolo());
					ps.setString(2, input.getGenere());
					ps.setInt(3, input.getDurata());
					ps.setLong(4, input.getId());

					result = ps.executeUpdate();

				} catch (Exception e) {

					e.printStackTrace();

				} finally {
					try {
						ps.close();
						c.close();

					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				return result;
			}

			// =============================================== DELETE
			public int delete(Film input) {

				if (input == null || input.getId() < 1) {
					return 0;
				}

				Connection c = null;
				PreparedStatement ps = null;
				int result = 0;

				try {

					c = MyConnection.getConnection();
					ps = c.prepareStatement("DELETE from film where id=?;");
					ps.setLong(1, input.getId());

					result = ps.executeUpdate();

				} catch (Exception e) {

					e.printStackTrace();

				} finally {
					try {
						ps.close();
						c.close();

					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				return result;
			}

			// =============================================== FINDBYID
			public Film findById(long input) {

				if (input < 1) {
					return null;
				}

				Connection c = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				Film result = null;

				try {

					c = MyConnection.getConnection();
					ps = c.prepareStatement("select * from film u where u.id=?;");
					ps.setLong(1, input);

					rs = ps.executeQuery();

					if (rs.next()) {
						result = new Film();
						result.setTitolo(rs.getString("titolo"));
						result.setGenere(rs.getString("genere"));
						result.setId(rs.getLong("id"));
						result.setDurata(rs.getInt("durata"));

					}

				} catch (Exception e) {

					e.printStackTrace();

				} finally {
					try {
						rs.close();
						ps.close();
						c.close();

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				return result;
			}
			
			
			public int insertCompleto(Film film) {

				Connection c = null;
				PreparedStatement ps = null;
				PreparedStatement ps2 = null;
				int result = 0;

				try {

					c = MyConnection.getConnection();

					// prima l'indirizzo
					ps = c.prepareStatement("INSERT INTO regista (nome, cognome,numerooscarvinti) VALUES (?, ?,?);",
							Statement.RETURN_GENERATED_KEYS);
					ps.setString(1, film.getRegista().getNome());
					ps.setString(2, film.getRegista().getCognome());
					ps.setInt(3, film.getRegista().getNuomeroOscarVinti());

					int registiInseriti = ps.executeUpdate();

					// mi metto da parte l'id inserito
					int last_inserted_id = -1;
					ResultSet rs = ps.getGeneratedKeys();
					if (rs.next()) {
						last_inserted_id = rs.getInt(1);
					}

					// poi l'abitante
					if (registiInseriti > 0) {
						ps2 = c.prepareStatement("INSERT INTO film (titolo, genere,durata,regista_id) VALUES (?, ?,?,?);");
						ps2.setString(1, film.getTitolo());
						ps2.setString(2, film.getGenere());
						ps2.setInt(3, film.getDurata());
						ps2.setInt(4, last_inserted_id);
						result = ps2.executeUpdate();
					}

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						ps.close();
						c.close();

					} catch (Exception e) {
						e.printStackTrace();
					}
				}

				return result;
			}
			
			public Film selectJoinById(Long idFilmInput) {

				Connection c = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				Film result = null;

				try {

					c = MyConnection.getConnection();
					ps = c.prepareStatement(
							"SELECT * FROM filmregista.film a left outer join filmregista.regista i on a.regista_id=i.id where a.id=?; ");
					ps.setLong(1, idFilmInput);

					rs = ps.executeQuery();

					if (rs.next()) {
						result = new Film();
						// leggo abitante dal resultset
						result.setId(rs.getLong("a.id"));
						result.setTitolo(rs.getString("a.titolo"));
						result.setGenere(rs.getString("a.genere"));
						result.setDurata(rs.getInt("a.durata"));
						// leggo indirizzo
						Regista registaTemp = new Regista();
						registaTemp.setId(rs.getLong("i.id"));
						registaTemp.setNome(rs.getString("i.nome"));
						registaTemp.setCognome(rs.getString("i.cognome"));
						registaTemp.setNuomeroOscarVinti(rs.getInt("i.numerooscarvinti"));
						// imposto l'indirizzo trovato dentro il mio abitante di
						// partenza
						result.setRegista(registaTemp);
					} else {
						result = null;
					}

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						rs.close();
						ps.close();
						c.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				return result;
			}
}