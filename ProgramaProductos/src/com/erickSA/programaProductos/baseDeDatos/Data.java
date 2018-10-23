package com.erickSA.programaProductos.baseDeDatos;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Data {
    private Conexion con;

    public Data() throws ClassNotFoundException, SQLException {
        con = new Conexion("bdProgramaProductos");
    }
    
    public List<Producto> getProductosOferta(int precioCorte) throws SQLException{
        List<Producto> lista = new ArrayList<>();

        String select = "SELECT nombre, precio FROM producto WHERE precio < "+precioCorte;
        
        ResultSet rs = con.ejecutar(select);

        while(rs.next()){
            Producto p = new Producto();
            
            p.setNombre(rs.getString(1));
            p.setPrecio(rs.getInt(2));
            
            lista.add(p);
        }
        
        con.close();

        return lista;
    }
    
    public float getPromedioPrecios() throws SQLException{
        float promedio = 0;
        
        String query = "SELECT AVG(precio) FROM producto;";
        
        ResultSet rs = con.ejecutar(query);
        
        if(rs.next()){
            promedio = rs.getFloat(1);
        }
        
        con.close();
        
        return promedio;
    }
    
    // iniciar Sesión
    public String getNombre(String run, String password) throws SQLException{
        String nombre = null; // null
        
        String select = "SELECT nombre FROM usuario WHERE run = '"+run+"' AND password = '"+password+"';";
        ResultSet rs = con.ejecutar(select);
        
        // while(rs.next){} --> 
        if(rs.next()){
            nombre = rs.getString(1);
        }
        con.close();
        
        return nombre;
    }
    public void validateRun(Usuario u) throws SQLException{
        
        String select;
        
        select="CALL existeuser('"+u.getRun()+"','"+u.getNombre()+"','"+u.getPassword()+"');";
        ResultSet rs=con.ejecutar(select);
    }
    
    public void crearProducto(Producto p) throws SQLException{
        String insert = "INSERT INTO producto VALUES(NULL, '"+p.getNombre()+"','"+p.getMarca()+"','"+p.getPrecio()+"','"+p.getStock()+"');";
        con.ejecutar(insert);
    }
    public void createUser(Usuario u) throws SQLException{
        String insert ="INSERT INTO usuario VALUES(NULL,'"+u.getRun()+"','"+u.getNombre()+"','"+u.getPassword()+"');";
        con.ejecutar(insert);
    }
    
    
    public List<Producto> getProductos() throws SQLException {
        // Estante de cajas de pasteles
        List<Producto> lista = new ArrayList<>();

        // Llego la jefa
        String select = "SELECT * FROM producto";
        
        // "Basti esque...."
        ResultSet rs = con.ejecutar(select);

        // Quedan pasteles?
        while(rs.next()){
            // Abre un caja
            Producto p = new Producto();
            
            // Coloca los pasteles en la caja
            p.setId(rs.getInt(1));
            p.setNombre(rs.getString(2));
            p.setMarca(rs.getString("marca"));
            p.setPrecio(rs.getInt(4));
            p.setStock(rs.getInt("stock"));
            // Coloca los pasteles en la caja
            
            // Coloca la caja en el estante
            lista.add(p);
        }
        
        // Bota la caja vacía de pasteles
        con.close();

        // Retorna El estante con las cajas con pasteles
        return lista;
    }
    
    public List<Producto> getProductos(String texto) throws SQLException{
        List<Producto> lista = new ArrayList<>();

        String select = "SELECT * FROM producto WHERE nombre LIKE '%"+texto+"%' OR marca LIKE '%"+texto+"%';";
        
        ResultSet rs = con.ejecutar(select);

        while(rs.next()){
            Producto p = new Producto();
            
            p.setId(rs.getInt(1));
            p.setNombre(rs.getString(2));
            p.setMarca(rs.getString(3));
            p.setPrecio(rs.getInt(4));
            p.setStock(rs.getInt(5));
            
            lista.add(p);
        }
        
        con.close();

        return lista;
    }
    
    public void eliminarProducto(int id) throws SQLException{
        String delete = "DELETE FROM producto WHERE id = '"+id+"'";
        
        con.ejecutar(delete);
    }
    
    public void actualizarProducto(Producto p) throws SQLException{
        String update = "UPDATE " +
                        "    producto " +
                        "SET" +
                        "    precio = '"+p.getPrecio()+"'," +
                        "    stock = '"+p.getStock()+"'" +
                        "WHERE " +
                        "    id = '"+p.getId()+"';";
        con.ejecutar(update);
    }
    
//    public static void main(String[] args) {
//        try {
//            Data d = new Data();
//            
//            for (Producto pro : d.getProductosOferta(15000)) {
//                System.out.println(pro.getNombre()+" - "+pro.getPrecio());
//            }
//            
//        } catch (ClassNotFoundException ex) {
//        } catch (SQLException ex) {
//        }
//        
//        
//    }
}
