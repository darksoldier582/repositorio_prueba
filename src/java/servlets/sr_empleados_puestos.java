/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import clases.Empleados;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Antonio
 */
public class sr_empleados_puestos extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            Empleados objeto_empleado = new Empleados();
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet sr_empleados_puestos</title>");    
            out.println("<meta http-equiv='refresh' content='0;URL=http://localhost:8080/web_empleados_puestos/ '/>");
            out.println("</head>");
            out.println("<body>");
            
            if(request.getParameter("btn_agregar")!=null){                            

            objeto_empleado.setNombre(request.getParameter("txt_nombres"));
            objeto_empleado.setApellido(request.getParameter("txt_apellidos"));       
            objeto_empleado.setDireccion(request.getParameter("txt_direccion"));
            objeto_empleado.setTelefono(request.getParameter("txt_telefono"));                  
            
            objeto_empleado.setDpi(request.getParameter("txt_dpi"));                  
            objeto_empleado.setFecha_nacimiento(request.getParameter("txt_fn"));                  
            
            java.util.Date date = new java.util.Date();
            java.text.SimpleDateFormat sdf=new java.text.SimpleDateFormat("yyyy-MM-dd");
            String fecha = sdf.format(date); 
            
            objeto_empleado.setFecha_ingreso(fecha);
            
            objeto_empleado.setId_puesto(Integer.parseInt(request.getParameter("drop_puestos")));
             
            //objeto_estudiante.insertar();                        
                if (objeto_empleado.insertar()>0)
                {                    
                            
                    //out.println("<h1>EL REGISTRO DE DATOS FUE EXITOSO</h1>");

                    //out.println("<a href='index.jsp'>REGRESAR...</a>");
                    
                    
                    
                    out.println("<dialog> <script> alert('REGISTRO AGREGADO CON EXITO'); </script></dialog>");
                   


                }
                else
                {
                    out.println("<dialog> <script> alert('ERROR, REGISTRO NO AGREGADO'); </script></dialog>");


                }                                                                                   

            }
            
            if(request.getParameter("btn_eliminar")!=null){
            
                objeto_empleado.setId_empleado(Integer.parseInt(request.getParameter("txt_id")));
                                   
                if (objeto_empleado.eliminar()>0)
                {
                    //out.println("<h1>LA ELIMINACION DE DATOS FUE EXITOSA</h1>");

                    //out.println("<a href='index.jsp'>REGRESAR...</a>");
                    
                    
                    out.println("<dialog> <script> alert('REGISTRO ELIMNADO CON EXITO'); </script></dialog>");
                    

                }
                else
                {
                    out.println("<dialog> <script> alert('ERROR, REGISTRO NO ELIMNADO'); </script></dialog>");


                }                                                                                   

            }   
            
            if(request.getParameter("btn_modificar")!=null){
                            
                objeto_empleado.setNombre(request.getParameter("txt_nombres"));
                objeto_empleado.setApellido(request.getParameter("txt_apellidos"));
                objeto_empleado.setDireccion(request.getParameter("txt_direccion"));
                objeto_empleado.setTelefono(request.getParameter("txt_telefono"));
                objeto_empleado.setDpi(request.getParameter("txt_dpi"));
                objeto_empleado.setFecha_nacimiento(request.getParameter("txt_fn"));                
                objeto_empleado.setId_puesto(Integer.parseInt(request.getParameter("drop_puestos")));
                objeto_empleado.setId_empleado(Integer.parseInt(request.getParameter("txt_id")));
                                   
                if (objeto_empleado.modificar()>0)
                {
                    //out.println("<h1>LA MODIFICACION DE DATOS FUE EXITOSA</h1>");

                    //out.println("<a href='index.jsp'>REGRESAR...</a>");
                    
                    
                    out.println("<dialog> <script> alert('REGISTRO MODIFICADO CON EXITO'); </script></dialog>");  
                    
                }
                else
                {
                    out.println("<dialog> <script> alert('ERROR, REGISTRO NO MODIFICADO'); </script></dialog>");



                }                                                                                   

            }     
            
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}


