package net.java.sip.communicator.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.NoSuchElementException;

public class ForwardDB {
	private ParseXMLCredentials Creden;

	Connection conn = null;
	PreparedStatement stmt = null;
	String query_delete="delete from forwarding where forwardFrom=?";
	String query_insert="insert into forwarding set forwardFrom=?, forwardTo=?";
	String query_select="SELECT forwardTo FROM forwarding where forwardFrom = ?";
	public ForwardDB() {
		Creden = new ParseXMLCredentials();
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public String getForwardRequest(String username) {
		String forwardTo = "";
		try {
			if (conn == null)
				conn = DriverManager.getConnection(Creden.getUrl(),Creden.getUsername(), Creden.getPassword());
			stmt = conn.prepareStatement(query_select);

			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				forwardTo = rs.getString("forwardTo");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return forwardTo;
	}

	public void ForwardSet(String fromUser, String toUser)
			throws NoSuchElementException, RuntimeException {

		try {
			if (conn == null)
				conn = DriverManager.getConnection(Creden.getUrl(),Creden.getUsername(), Creden.getPassword());
		} catch (SQLException e) {
			
			e.printStackTrace();
		}

		// Check if the user is registered
		try {
			stmt = conn.prepareStatement("SELECT * FROM users where username = ?");
			stmt.setString(1, toUser);
			ResultSet rs = stmt.executeQuery();
			if ((rs == null) || (!rs.next()))
				throw new NoSuchElementException();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Check for circle
		HashSet<String> userSet = new HashSet<String>();
		userSet.add(fromUser);
		userSet.add(toUser);

		try {
			stmt = conn.prepareStatement(query_select);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String tempFrom = toUser;
		String forwardTo;
		
		while (true) {
			try {
				stmt.setString(1, tempFrom);
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					forwardTo = rs.getString("forwardTo");
					if (!userSet.add(forwardTo))
						throw new RuntimeException();
					tempFrom = forwardTo;
				} else
					break;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		/*
		 Update Forward from old one to new one
		 */

		try {
			stmt = conn
					.prepareStatement("delete from forwarding where forwardFrom=?");
			stmt.setString(1, fromUser);
			stmt.executeUpdate();
			stmt = conn
					.prepareStatement("insert into forwarding set forwardFrom=?, forwardTo=?");
			stmt.setString(1, fromUser);
			stmt.setString(2, toUser);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void ForwardReset(String user) {
		String query_delete="delete from forwarding where forwardFrom=?";
		try {
			if (conn == null)
				conn = DriverManager.getConnection(Creden.getUrl(),Creden.getUsername(), Creden.getPassword());
			stmt = conn.prepareStatement(query_delete);
			stmt.setString(1, user);
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
}
