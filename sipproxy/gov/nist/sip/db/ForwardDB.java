package gov.nist.sip.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ForwardDB {

	private ParseXMLCredentials Creden;

	Connection conn = null;
	PreparedStatement stmt = null;

	public ForwardDB() {
		Creden = new ParseXMLCredentials();
	}

	public String getForward(String from) {
		String forwardTo = null;
		String query_select ="SELECT forwardTo FROM forwarding where forwardFrom = ?";
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(Creden.getUrl(),Creden.getUsername(), Creden.getPassword());
			stmt = conn.prepareStatement(query_select);
			
			String tempFrom = from;
			stmt.setString(1, tempFrom);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
					forwardTo = rs.getString("forwardTo");
					tempFrom = forwardTo;
					stmt.setString(1, tempFrom);
					rs = stmt.executeQuery();

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return forwardTo;
	}
	

}
