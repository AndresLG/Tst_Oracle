package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.VentaM;

public class VentaImpl extends conexion {

    public void agregar(VentaM venta) throws Exception {
        try {
            this.conexion();
            String sql = "INSERT INTO VENTA (AÑOVEN,TOTVEN) VALUES(?,?)";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            st.setString(1, venta.getAÑOVEN());
            st.setString(2, venta.getTOTVEN());
            st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            this.cerrar();
        }
    }
    
    public List<VentaM> listar() throws Exception {
        List<VentaM> lista;
        ResultSet rs;
        try {
            this.conexion();
            String sql = "SELECT CODVEN,AÑOVEN,TOTVEN FROM VENTAS ORDER BY CODVEN";
            PreparedStatement st = this.getCn().prepareStatement(sql);
            rs = st.executeQuery();
            lista = new ArrayList();
            while(rs.next()){
                VentaM venta = new VentaM();
                venta.setCODVEN(rs.getString("CODVEN"));
                venta.setAÑOVEN(rs.getString("AÑOVEN"));
                venta.setTOTVEN(rs.getString("TOTVEN"));
                lista.add(venta);
            }
            return lista;
        } catch (SQLException e) {
            throw e;
        }finally{
            this.cerrar();
        }
    }

}
