package com.dansan.database;

import com.dansan.bean.HistoricoBean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.DriverManager;


public class BDManagement {

    /*private String username = "root";
    private String password = "Br4y4nl0p32";
    private String db = "jdbc:mysql://localhost:3306/plaza_estacionamiento";
    private String driver = "com.mysql.jdbc.Driver";*/
    public Statement st;

    // Se crea la conexion a la base plaza_estacionamiento
    Connection conexion;
    
    public Connection conectar(){
        
        try {
            String url = "jdbc:mysql://localhost:3306/requerimientosempresa";
            String usuario = "root";
            String contraseña = "Br4y4nl0p32";
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            
            conexion = (Connection) DriverManager.getConnection(url, usuario, contraseña);
            
            if(conexion != null){
                System.out.println("conectado a " + url);    
            }

            
            
        } catch (Exception e) {
            System.out.println("Error " + e);
        }
        return conexion;
    }
    

    /**
     * Metodos de la tabla tarifa
     */
    public double obtenerTarifa(char c) {
        double tarifa = 0;
        String pedido = "SELECT*FROM tarifas WHERE tipo_auto='" + c + "'";
        try {
            ResultSet rs = st.executeQuery(pedido);
            if (rs.next()) {
                tarifa = (double) rs.getObject("tarifa");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BDManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tarifa;
    }

    public ResultSet listarTarifas() {
        ResultSet rs = null;
        String pedido = "SELECT*FROM tarifas";
        try {
            rs = st.executeQuery(pedido);
        } catch (SQLException ex) {
            Logger.getLogger(BDManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    /**
     * Metodos de la tabla cochera
     */
    public ResultSet listarCocheras() {
        ResultSet rs = null;
        String pedido = "SELECT*FROM cocheras";
        try {
            rs = st.executeQuery(pedido);
        } catch (SQLException ex) {
            Logger.getLogger(BDManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public int numCochesEnCocheras(int piso) {
        int total = 0;
        ResultSet rs=null;
        String pedido = "SELECT count(*) as total FROM cocheras WHERE piso="+piso;
        try {
            rs = st.executeQuery(pedido);
            while(rs.next()){
                total = rs.getInt("total");
            }
        } catch (SQLException ex) {
            Logger.getLogger(BDManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return total;
    }
    
    
    public HistoricoBean obtenerCoche(String patente) {
        HistoricoBean bean = new HistoricoBean();
        try {
            String query = "SELECT*FROM cocheras WHERE patente='" + patente + "'";
            ResultSet rs = st.executeQuery(query);
            if (rs.next()) {
                bean.setPlaca(rs.getString("patente"));
                bean.setHora_ocupacion(rs.getDouble("hora_ocupacion"));
                String cad = (String) rs.getObject("tipo_auto");
                char c = cad.charAt(0);
                bean.setTipo_auto(c);
                bean.setPiso(rs.getInt("piso"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(BDManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bean;
    }

    public ArrayList<HistoricoBean> obtenerCoche(int piso) {
        ArrayList<HistoricoBean> beanes = new ArrayList<HistoricoBean>();
        try {
            String query = "SELECT*FROM cocheras WHERE piso=" + piso;
            ResultSet rs = st.executeQuery(query);
            rs.beforeFirst();
            while (rs.next()) {
                HistoricoBean bean = new HistoricoBean();
                bean.setPlaca(rs.getString("patente"));
                bean.setHora_ocupacion(rs.getDouble("hora_ocupacion"));
                String cad = (String) rs.getObject("tipo_auto");
                char c = cad.charAt(0);
                bean.setTipo_auto(c);
                bean.setPiso(rs.getInt("piso"));
                beanes.add(bean);
            }

        } catch (SQLException ex) {
            Logger.getLogger(BDManagement.class.getName()).log(Level.SEVERE, null, ex);
        }

        return beanes;
    }

    public void agregarCocheras(String patente, int piso, char tipo) {
        Date date = new Date();
        double fecha = date.getTime();
        String query = "insert into cocheras (patente,hora_ocupacion,tipo_auto,piso) ";
        query += "values('" + patente + "','" + fecha + "','" + tipo + "','" + piso + "')";
        try {
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(BDManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void borrarCocheras(String patente) {
        String query = "delete from cocheras where patente = '" + patente + "'";
        try {
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(BDManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Metodos de la tabla historico
     * @return 
     */
    public ResultSet listarHistorico() {
        ResultSet rs = null;
        String query = "SELECT*FROM historico";
        try {
            rs = st.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(BDManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }

    public void grabarHistorico(String patente, double hora_ocupacion, char tipo, int piso, double importe) {
        Date date = new Date();
        double fecha = date.getTime();
        String query = "";
        query += "insert into historico (patente,hora_ocupacion,tipo_auto,piso,hora_salida,importe) ";
        query += "values('" + patente + "','" + hora_ocupacion + "','" + tipo + "','" + piso + "','" + fecha + "','" + importe + "')";
        try {
            st.executeUpdate(query);
        } catch (SQLException ex) {
            Logger.getLogger(BDManagement.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
