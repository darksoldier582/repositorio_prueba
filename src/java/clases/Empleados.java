/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;
import javax.swing.table.DefaultTableModel; //tabla
import javax.swing.JOptionPane; //mensajes
import java.sql.ResultSet; //ejecutar consulta
import java.sql.PreparedStatement; //enviar parametros en consulta sql
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import java.util.Calendar;//fecha y hora actual
import java.util.List;

/**
 *
 * @author Antonio
 */
public class Empleados {

    public int id_empleado;
    String nombre, apellido, direccion, telefono;
    public int id_puesto;
    String dpi, fecha_nacimiento, fecha_ingreso;
    
    DefaultTableModel tblModelo;
    
    public Conexion cls_Conectar;
    
    PreparedStatement parametro;

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }            
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getId_puesto() {
        return id_puesto;
    }

    public void setId_puesto(int id_puesto) {
        this.id_puesto = id_puesto;
    }
    
    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getFecha_nacimiento() {
        return fecha_nacimiento;
    }

    public void setFecha_nacimiento(String fecha_nacimiento) {
        this.fecha_nacimiento = fecha_nacimiento;
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }
    
    
    //LLENADO DE TABLA CON DATOS DE SQL
    public DefaultTableModel tbl_datos()
    {
        try{
            cls_Conectar = new Conexion();
            cls_Conectar.abrirConexion();
            String query;
            query = "select e.idEmpleado as id, e.nombre, e.apellido, e.direccion, e.telefono, \n" +
                    "concat(pue.idPuestos,') ',pue.puesto) as puestos, e.idPuestos as id_Puestos, e.dpi, e.fecha_nacimiento, e.fecha_ingreso from empleados as e \n" +
                    "inner join puestos as pue \n" +
                    "on e.idPuestos = pue.idPuestos;";
            
            ResultSet consulta = cls_Conectar.conexionBD.createStatement().executeQuery(query);            
            
            String encabezado[] = {"ID","NOMBRES","APELLIDOS","DIRECCION","TELEFONO", "PUESTO", "DPI", "NACIMIENTO", "INGRESO", "id_puesto"};
             tblModelo = new DefaultTableModel();
             tblModelo.setColumnIdentifiers(encabezado);            

             
             String datos[] = new String [10];
             while (consulta.next()){
                 datos[0] = consulta.getString("id");                 
                 datos[1] = consulta.getString("nombre");
                 datos[2] = consulta.getString("apellido");
                 datos[3] = consulta.getString("direccion");
                 datos[4] = consulta.getString("telefono");
                 datos[5] = consulta.getString("puestos");
                 datos[6] = consulta.getString("dpi");
                 datos[7] = consulta.getString("fecha_nacimiento");
                 datos[8] = consulta.getString("fecha_ingreso");
                 datos[9] = consulta.getString("id_Puestos");
                 
                 tblModelo.addRow(datos);
                 
             }
             cls_Conectar.cerrarConexion();
             return tblModelo;
        }
        catch(Exception ex){
            cls_Conectar.cerrarConexion();
            JOptionPane.showMessageDialog(null, ex.getMessage(),"ERROR QUERY",JOptionPane.ERROR_MESSAGE);
            return tblModelo;
        }        
    }
    
    //LLENADO DE COMBOBOX CON LOS DATOS DE SQL
    public List<List<String>> combo_puestos(){
        
        List<List<String>> lista = new ArrayList<List<String>>();
        lista.add(new ArrayList<String>());
        lista.add(new ArrayList<String>());        
        
        try{
            
            cls_Conectar = new Conexion();
            cls_Conectar.abrirConexion();
                       
            String query = "select idPuestos, puesto from puestos;";
            
            ResultSet consulta = cls_Conectar.conexionBD.createStatement().executeQuery(query);
            
            lista.get(0).add("0");
            lista.get(1).add("<< Elija >>");
            
            while(consulta.next()){
                lista.get(0).add(consulta.getString("idPuestos")); 
                lista.get(1).add(consulta.getString("puesto"));
            }
            cls_Conectar.cerrarConexion();                        
            
            
        
        }catch(Exception ex){
            
            System.out.println("ERROR QUERY:" + ex.getMessage());
            lista.get(0).add("0");
            lista.get(1).add("<< Elija >>");            
            
        }
        return lista;
    }

    public int insertar(){
            
        try{
            cls_Conectar = new Conexion();
            cls_Conectar.abrirConexion();
                
            String query = "insert into empleados(nombre, apellido, direccion, telefono, idPuestos, dpi, fecha_nacimiento, fecha_ingreso ) \n" +
                            "values (?,?,?,?,?,?,?,?);";
                
            parametro = (PreparedStatement) cls_Conectar.conexionBD.prepareStatement(query);
                
            parametro.setString(1, getNombre());
            parametro.setString(2, getApellido());
            parametro.setString(3, getDireccion());
            parametro.setString(4, getTelefono());
            parametro.setInt(5, getId_puesto());
            parametro.setString(6, getDpi());
            parametro.setString(7, getFecha_nacimiento());
           
            parametro.setString(8, getFecha_ingreso());
                
            int ejecutar = parametro.executeUpdate();
                
            cls_Conectar.cerrarConexion();
            
            return ejecutar;
                                    
            }catch(Exception ex){
                
                return 0;
                
            }

        }    

    public int eliminar()
    {
        try{
            cls_Conectar = new Conexion();
            cls_Conectar.abrirConexion();
            String query ="DELETE FROM empleados WHERE idEmpleado=?;";
            parametro = (PreparedStatement) cls_Conectar.conexionBD.prepareStatement(query);
            
            parametro.setInt(1,getId_empleado());
            int ejecutar=parametro.executeUpdate();
            cls_Conectar.cerrarConexion();
            return ejecutar;
        }catch(Exception ex){
              System.out.println("ERROR ELIMINAR:" + ex.getMessage());
              return 0;          


        }                        
    }    
    
    public int modificar()
    {
        try{
        cls_Conectar = new Conexion();
        cls_Conectar.abrirConexion();
        
        String query ="update empleados set nombre = ?, apellido = ?, direccion = ?, "
                + "telefono = ?, idPuestos = ?, dpi = ?, fecha_nacimiento = ? where idEmpleado = ?;";
        
        parametro = (PreparedStatement) cls_Conectar.conexionBD.prepareStatement(query);
        //parametro.setInt(1,getId_empleado());
        
        parametro.setString(1,getNombre());
        parametro.setString(2,getApellido());
        parametro.setString(3,getDireccion());
        parametro.setString(4,getTelefono());
        parametro.setInt(5,getId_puesto());
        parametro.setString(6,getDpi());
        parametro.setString(7,getFecha_nacimiento());
        parametro.setInt(8,getId_empleado());
        
        int ejecutar=parametro.executeUpdate();
        cls_Conectar.cerrarConexion();
        
        return ejecutar;
        }catch(Exception ex){
            
            System.out.println("ERROR MODIFICAR:" + ex.getMessage());
            return 0;           
    
    
        }
            
    } 
    

    
    
    
    
    
}
