package it.prova.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.prova.connection.MyConnection;
import it.prova.model.Regista;

public class RegistaDao {
	
	// =============================================== LIST
		public List<Regista> list() {

			Connection c = null;
			ResultSet rs = null;
			Statement s = null;
			Regista temp = null;
			List<Regista> result = new ArrayList<Regista>();

			try {

				c = MyConnection.getConnection();
				s = c.createStatement();

				rs = s.executeQuery("select * from regista;");

				while (rs.next()) {
					temp = new Regista();
					temp.setNome(rs.getString("nome"));
					temp.setCognome(rs.getString("cognome"));
					temp.setId(rs.getLong("id"));
					temp.setNuomeroOscarVinti(rs.getInt("numerooscarvinti"));
					result.add(temp);

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
			public int insert(Regista input) {

				Connection c = null;
				PreparedStatement ps = null;
				int result = 0;

				try {

					c = MyConnection.getConnection();
					ps = c.prepareStatement("INSERT INTO regista (nome, cognome, numerooscarvinti) VALUES (?, ?, ?);");
					ps.setString(1, input.getNome());
					ps.setString(2, input.getCognome());
					ps.setInt(3, input.getNuomeroOscarVinti());

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
			public int update(Regista input) {

				if (input == null || input.getId() < 1) {
					return 0;
				}

				Connection c = null;
				PreparedStatement ps = null;
				int result = 0;

				try {

					c = MyConnection.getConnection();
					ps = c.prepareStatement("UPDATE regista SET nome=?, cognome=?, numerooscarvinti=? where id=?;");
					ps.setString(1, input.getNome());
					ps.setString(2, input.getCognome());
					ps.setInt(3, input.getNuomeroOscarVinti());
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
			public int delete(Regista input) {

				if (input == null || input.getId() < 1) {
					return 0;
				}

				Connection c = null;
				PreparedStatement ps = null;
				int result = 0;

				try {

					c = MyConnection.getConnection();
					ps = c.prepareStatement("DELETE from regista where id=?;");
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
			public Regista findById(long input) {

				if (input < 1) {
					return null;
				}

				Connection c = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				Regista result = null;

				try {

					c = MyConnection.getConnection();
					ps = c.prepareStatement("select * from regista u where u.id=?;");
					ps.setLong(1, input);

					rs = ps.executeQuery();

					if (rs.next()) {
						result = new Regista();
						result.setNome(rs.getString("nome"));
						result.setCognome(rs.getString("cognome"));
						result.setId(rs.getLong("id"));
						result.setNuomeroOscarVinti(rs.getInt("numerooscarvinti"));

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
			
			public Regista selectById(Long id) {

				Connection c = null;
				PreparedStatement ps = null;
				ResultSet rs = null;
				Regista result = null;

				try {

					c = MyConnection.getConnection();
					ps = c.prepareStatement("select * from regista i where i.id=?;");
					ps.setLong(1, id);

					rs = ps.executeQuery();

					if (rs.next()) {
						result = new Regista();
						result.setId(rs.getLong("id"));
						result.setNome(rs.getString("nome"));
						result.setCognome(rs.getString("cognome"));
						result.setNuomeroOscarVinti(rs.getInt("numerooscarvinti"));
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
			
		public Regista registaMaxOscar() {

				Connection c = null;
				ResultSet rs = null;
				Statement s = null;
				Regista temp = null;
				Regista result = new Regista();

				try {

					c = MyConnection.getConnection();
					s = c.createStatement();

					rs = s.executeQuery("select max(numerooscarvinti),r.* from regista r;");

					while (rs.next()) {
						temp = new Regista();
						temp.setNome(rs.getString("nome"));
						temp.setCognome(rs.getString("cognome"));
						temp.setId(rs.getLong("id"));
						temp.setNuomeroOscarVinti(rs.getInt("numerooscarvinti"));
						result= temp;

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
			
			public Regista findByOscarVinti() {

				Connection c = null;
				ResultSet rs = null; 
				Statement s = null;
				Regista temp = null;
				List<Regista> result = new ArrayList<Regista>();
				int oscarVinti = 0;
				Regista temp2 = null;

				try {
					c = MyConnection.getConnection();
					s = c.createStatement();

					rs = s.executeQuery("SELECT*FROM regista");

					while (rs.next()) {
						temp = new Regista();
						temp.setNome(rs.getString("NOME"));
						temp.setCognome(rs.getString("COGNOME"));
						temp.setId(rs.getLong("id"));
						temp.setNuomeroOscarVinti(rs.getInt("NUMEROOSCARVINTI"));
						result.add(temp);
					
						for (Regista registaitem : result) {
							if (registaitem.getNuomeroOscarVinti() > oscarVinti) {
							temp2 = temp;
						}
						}
						
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
				return temp2;

			}
			
}
