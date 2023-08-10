package view;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
Informações pra acessar o banco de dados
endereço: dancingduck.clarete.dev
usuario: root
senha: root
 */

public class Conexao {
    static Connection conexaodb = null;
    static Statement statement = null;
    static ResultSet received = null;
    public static void main(String[] args) throws Exception {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexaodb = DriverManager.getConnection("jdbc:mysql://dancingduck.clarete.dev", "root", "root");
            String selectDatabaseQuery = "USE dados_lusofa";
            statement = conexaodb.createStatement();
            statement.execute(selectDatabaseQuery);
            
        }
        catch (ClassNotFoundException ex){
            System.out.println("Driver do banco de dados não localizado");
        }
        catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao acessar o banco de dados: "+ex.getMessage());
        }     
    }
    public int verifyNameAndPassword(String nome, String senha) { //retorna 1 se o nome de usuário e senha recebido como parâmetro existir no banco de dados
        try {
            received = conexaodb.createStatement().executeQuery("select * from funcionarios");
            while(received.next()) {
                if(received.getString("login").equals(nome) && received.getString("senha").equals(senha)) {
                    return 1;
                }
            }
        }
        catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao acessar o banco de dados: "+ex.getMessage());
        }
        return 0;
    }

    public int verifyProduct(String produto) { //retorna 1 se o produto recebido como parâmetro existir no banco de dados
        try {
            received = conexaodb.createStatement().executeQuery("select * from estoque");
            while(received.next()) {
                if(received.getString("produto").equals(produto)) {
                    return 1;
                }
            }
        }
        catch (SQLException ex) {
            System.out.println("Ocorreu um erro ao acessar o banco de dados: "+ex.getMessage());
        }
        return 0;
    }

}




