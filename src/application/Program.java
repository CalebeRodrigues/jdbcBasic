package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import db.DB;

public class Program {

	public static void main(String[] args) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Connection conn = null;
		PreparedStatement st = null;
		
		try {
			conn = DB.getConnection();
			
			st = conn.prepareStatement("Insert Into department (Name) values ('D1'), ('D2')",
															Statement.RETURN_GENERATED_KEYS);
			int rowsAffected = st.executeUpdate();


			if(rowsAffected > 0) {
				ResultSet res = st.getGeneratedKeys();
				while(res.next()) {
					int id = res.getInt(1);
					System.out.println("Done! Id = " + id );
				}	
			}
			else System.out.println("No rows Affected!");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
	}

}
