<%-- 
    Document   : index
    Created on : 24/09/2019, 12:16:21 PM
    Author     : Antonio
--%>

<%@page import="javax.swing.table.DefaultTableModel"%>
<%@page import="java.lang.String"%>
<%@page import="java.util.List"%>
<%@page import="clases.Empleados"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        
        <title>FORMULARIO EMPLEADOS</title>
        <link href="bootstrap-4/css/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="css_proyecto/Styles.css" rel="stylesheet" type="text/css"/>
        <script src="bootstrap-4/js/jquery.js" type="text/javascript"></script>
        <script src="bootstrap-4/js/bootstrap.js" type="text/javascript"></script>
        
    </head>
    <body>
    <link href="https://fonts.googleapis.com/css?family=Turret+Road&display=swap" rel="stylesheet">
    <br>        
    <h1 class="titulo_pag"><strong>Formulario Empleados</strong></h1>                       
                
        <br>
        <center>
        <button type="button" class="btn btn-primary btn-lg btn-block" id="btn_nuevo" data-toggle="modal" data-target="#modal">Nuevo</button>
        </center>
        <div class="modal fade" id="modal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-title" id="modalLabel">
                        <font color="black" page="Arial">
                        <br>
                        <h5 class="modal-title" id="modalLabel">Datos Empleado</h5>
                        <br>
                        </font>                                             
                        
                    
            <center>
            <form action="sr_empleados_puestos" method="post" class="form-group" id="form_empleados">
                <input type="text" class="form-control" id="txt_id" name="txt_id" placeholder="0" readonly>
                <input type="text" class="form-control" id="txt_nombres" name="txt_nombres" placeholder="Nombre1 Nombre2" required>
                <input type="text" class="form-control" id="txt_apellidos" name="txt_apellidos" placeholder="Apellidos1 Apellidos2" required>
                <input type="text" class="form-control" id="txt_direccion" name="txt_direccion" placeholder="Direccion Completa"required>
                <input type="tel" class="form-control" id="txt_telefono" name="txt_telefono" placeholder="Telefono" pattern="[0-9]{8}" required>                
                <input type="dpi" class="form-control" id="txt_dpi" name="txt_dpi" placeholder="DPI" pattern="[0-9]{13}"required>                
                <input type="date" class="form-control" id="txt_fn" name="txt_fn" placeholder="Fecha de Nacimiento"required>                
                
                <select id="drop_puestos" name="drop_puestos" class="form-control" >
                    <% 
                        Empleados objeto_empleado = new Empleados();
                        
                        List<List<String>> lista = objeto_empleado.combo_puestos();
                        for(int i = 0; i<lista.get(0).size();i++){
                            out.println("<option value ='"+ lista.get(0).get(i)+"'>"+ lista.get(1).get(i)+"</option>");
                        }

                    %>                   
                </select>
                <br>
                <br>
                
                <center>
                <input type="submit" class="btn btn-info btn-lg" id="btn_agregar" name="btn_agregar" value="Agregar">
                <input type="submit" class="btn btn-warning btn-lg" id="btn_modificar" name="btn_modificar" value="Modificar">
                <input type="submit" class="btn btn-danger btn-lg" id="btn_eliminar" name="btn_eliminar" value="Eliminar" onclick="javascript:if(!confirm('¿Desea Eliminar?'))return false">
                <input type="submit" class="btn btn-primary btn-lg" id="btn_limpiar" name="btn_limpiar" value="Limpiar" <%--onClick="javascript:foco(txt_carne);"--%>>
                </center>
                
                
            </form>
            </center>
                    </div>
                </div>
            </div>
        </div>
                <div class="table-responsive">
                    <center>
                    <table class="table table-hover table-bordered table-striped">
                        <thead class="thead-dark">
                        <th>Nombres</th>
                        <th>Apellidos</th>
                        <th>Direccion</th>
                        <th>Telefono</th>                        
                        <th>Puesto</th>
                        <th>DPI</th>                       
                        <th>Fecha de Nacimiento</th>
                        <th>Fecha de Ingreso</th>
                        
                        
                        </thead>
                        <br>
                        <br>
                        
                        <tbody id="tbl_empleados">
                            <% 
                                Empleados llenarTabla = new Empleados();
                                DefaultTableModel tblModelo= new DefaultTableModel();
                                tblModelo = llenarTabla.tbl_datos();
                                for(int a=0;a< tblModelo.getRowCount();a++){
                                out.println("<tr data-idempleado="+ tblModelo.getValueAt(a,0).toString()  +" data-idtp="+ tblModelo.getValueAt(a,9).toString()  +" >");
                                out.println("<td>" + tblModelo.getValueAt(a,1).toString()  + "</td>");
                                out.println("<td>" + tblModelo.getValueAt(a,2).toString()  + "</td>");
                                out.println("<td>" + tblModelo.getValueAt(a,3).toString()  + "</td>");
                                out.println("<td>" + tblModelo.getValueAt(a,4).toString()  + "</td>");
                                out.println("<td>" + tblModelo.getValueAt(a,5).toString()  + "</td>");
                                out.println("<td>" + tblModelo.getValueAt(a,6).toString()  + "</td>");
                                out.println("<td>" + tblModelo.getValueAt(a,7).toString()  + "</td>");
                                out.println("<td>" + tblModelo.getValueAt(a,8).toString()  + "</td>");                                
                                
                                out.println("</tr>");
                                }
                            
                             %>
                        </tbody>
                        
                    </table>
                    </center>
                </div>
    <script type="text/javascript">
        $('#tbl_empleados').on('click','tr td',function(evt){
            
            var target,ide,idp,nombres,apellidos,direccion,telefono,dpi,fn,fi;
            target = $(event.target);
            
            $("#modal").modal("show");
            
            document.getElementById("btn_agregar").disabled = true;
            document.getElementById("btn_modificar").disabled = false;
            document.getElementById("btn_eliminar").disabled = false;            
            
            
            ide=target.parents().data('idempleado');
            idp=target.parents().data('idtp');
            
            
            nombres = target.parents("tr").find("td").eq(0).html();
            apellidos = target.parents("tr").find("td").eq(1).html();
            direccion = target.parents("tr").find("td").eq(2).html();
            telefono = target.parents("tr").find("td").eq(3).html();
            dpi = target.parents("tr").find("td").eq(5).html();            
            fn = target.parents("tr").find("td").eq(6).html();            
            
            $("#txt_id").val(ide);            
            $("#txt_nombres").val(nombres);
            $("#txt_apellidos").val(apellidos);
            $("#txt_direccion").val(direccion);
            $("#txt_telefono").val(telefono);
            $("#txt_dpi").val(dpi);                
            $("#txt_fn").val(fn);            
            $("#drop_puestos").val(idp);            
        
        
        });
    </script>
    
    <script type="text/javascript">
        
        $(document).ready(function() {
            $('#btn_limpiar').click(function() {
            document.getElementById("form_empleados").reset();            
            });
        });
          
        $(document).ready(function() {
            $('#btn_nuevo').click(function() {
            document.getElementById("form_empleados").reset();
            document.getElementById("btn_agregar").disabled = false;
            document.getElementById("btn_modificar").disabled = true;
            document.getElementById("btn_eliminar").disabled = true;            
            });
        });   
        
        
               
    </script>            

    </body>
    <div class="footer">
      <div class="container">
          <h1 id="footer_pag">Guatemala © 2019, Todos los Derechos Reservados <br> Diseño Web: Antonio Barrios Castillo</h1>
      </div>
    </div>
    </html>

