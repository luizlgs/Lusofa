import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    public static void main(String[] args) throws Exception {
        Connection conexao = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://dancingduck.clarete.dev", "root", "root");
        }
        catch (ClassNotFoundException ex){
            System.out.println("Driver do banco de dados não localizado");
        }
        catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao acessar o banco de dados: "+ex.getMessage());
        }
        finally {
            if (conexao != null) {
                conexao.close();
            }
        }
    }
}


