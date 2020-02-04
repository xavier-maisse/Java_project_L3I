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
			e.printStackTrace();
		}	
	}
	
	public void updateInBDD(String joueur, int score) {
		try {
			st = cn.createStatement();
			String query = "UPDATE score SET score= '"+score+"' WHERE joueur='"+joueur+"'";
			
			st.executeUpdate(query);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
	}
	public boolean existInBdd(String joueur) {
		try {
			st = cn.createStatement();
			String query = "SELECT * FROM score WHERE joueur= '" + joueur +"'";
			ResultSet rs;
			rs = st.executeQuery(query);
			return rs.next();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void saveToBDD(String joueur, int score) {
		if(existInBdd(joueur)) {
			updateInBDD(joueur, score);
		}else {
			insertIntoBDD(joueur, score);
		}
	}

}
