/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package backend.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLNonTransientConnectionException;
import java.sql.SQLSyntaxErrorException;
import java.sql.Statement;

/**
 *
 * @author icecat
 */
public class ConexionDB {
        private final String HOST = "127.0.0.1";
        private final String PORT = "3306";
        private final String DATABASE = "Textiles";
        private final String USER = "root";
        private final String PASS = "";
        private String url = "jdbc:mariadb://"+HOST+":"+PORT+"/"+DATABASE;
        private Connection conn;
        
        public ConexionDB(){
            conectar();
        }
        
        public ConexionDB(String host, String port, String database, String user, String pass){
            
        }
        
        public Connection conectar() {
            try {
                conn = DriverManager.getConnection(url, USER, PASS);
                System.getLogger(ConexionDB.class.getName()).log(System.Logger.Level.INFO, (String) null, "Conexion exitosa");
            } catch (SQLException ex) {
                System.getLogger(ConexionDB.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
            return conn;
        }
        public void desconectar() {
            if(conn!=null){
                try {
                    conn.close();
                    System.out.println("Desconexion exitosa");
                } catch (SQLException ex) {
                    System.getLogger(ConexionDB.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
                }
            }
        }
}
