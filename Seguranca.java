/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;
import java.sql.*;  

/**
 *
 * @author Rafael Gontijo Ferreira
 */
public class Seguranca {
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public static String Criptografa(String originalInput, String originalPassword) throws NoSuchAlgorithmException{
        
        String encodedString = Base64.getEncoder().encodeToString(originalInput.getBytes()); // nome em b64
       
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash256 = digest.digest(
         originalPassword.getBytes(StandardCharsets.UTF_8));
   
        
        return encodedString +":"+Arrays.toString(hash256);
    }
    public static int Mysql(String x){  
        try{  
        
            Class.forName("com.mysql.jdbc.Driver");  
            try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sonoo","root","root")) {
                Statement stmt=con.createStatement();
                ResultSet rs=stmt.executeQuery(x);
                //while(rs.next())
                   //ystem.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));
            }  
        }catch(ClassNotFoundException | SQLException e){ return 1;}  
        return 0;
    } 
    
    public static void GravarBanco(String Final){
        String[] Conteudo = Final.split(":");
        
        // Conteudo[0] = nome em base 64
        // Conteudo[1] = hash
        byte[] decodedBytes;
        decodedBytes = Base64.getDecoder().decode(Conteudo[0]);
        
        String decodedString = new String(decodedBytes); // nome em plain
        String hash = Conteudo[1];
        
            if (Mysql("SELECT Usuarios" + decodedString + "FROM *") != 0){
                    //Mysql("Sql para inserir usuario e hash");
        
            } else {
                System.out.println("Error");
            }
  
        
        }
    public static int CompararBanco(String Final){
        String[] Conteudo = Final.split(":");
        
        // Conteudo[0] = nome em base 64
        // Conteudo[1] = hash
        byte[] decodedBytes;
        decodedBytes = Base64.getDecoder().decode(Conteudo[0]);
        
        String decodedString = new String(decodedBytes); // nome em plain
        String hash = Conteudo[1];
        
            if (Mysql("SELECT Usuarios" + decodedString + "FROM *") == 0){
                // Comparar hash com hash na sql;    
                int condicao=0;
                //Mysql("");
                return condicao;
        
            } else {
                System.out.println("Error");
                return 1;
            }
  
        
        }
      

    }  
