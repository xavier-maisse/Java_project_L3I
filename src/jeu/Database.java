package jeu;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;





public class Database {

	private String url = "jdbc:mysql://mysql-ivax.alwaysdata.net/ivax_ptut";
	private String login = "ivax_miage";
	private String mdp = "MIAGE13";
	private Connection cn;
	private Statement st;
	
	
	public Database() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(url,login,mdp);
		} catch (SQLException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	public void insertIntoBDD(String joueur, int score) {
		try {
			st = cn.createStatement();
			String query = "INSERT INTO score VALUES ('"+joueur+"','"+score+"')";
			
			st.executeUpdate(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void readDataFromBdd(String joueur, int scrore) {
		try {
			st = cn.createStatement();
			String query = "SELECT * FROM score";
			ResultSet rs;
			rs = st.executeQuery(query);
			
			while(rs.next()) {
				System.out.println(rs.getString("score"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
