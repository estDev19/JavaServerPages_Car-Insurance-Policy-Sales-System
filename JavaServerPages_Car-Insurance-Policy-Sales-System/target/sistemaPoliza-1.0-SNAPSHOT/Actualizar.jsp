<%@ include file="commons/header.jspf"%>
<%@ include file="commons/navbar.jspf"%>
<div class="mx-auto" style="width: 75%;">
    <div class="container mtg-5 col-lg-5">
        <div class="card col-sm-10">
            <div class="card-body">
                <form action="Controlador?menu=Actualizar" method="POST">
                    <div class="form-group text-center">
                        <label>Identificacion</label>
                        <input type="text" name="txtidentificacion"  value="${usuario.getIdentificacion()}"  class="form-control" disabled>
                    </div>
                    <div class="form-group text-center">
                        <label>Nombre</label>
                        <input type="text" name="txtnombre" value="${usuario.getNombre()}" class="form-control" required>
                    </div>
                    <div class="form-group text-center">
                        <label>Telefono</label>
                        <input type="text" name="txttelefono"  value="${usuario.getTelefono()}"  class="form-control" required>
                    </div>
                    <div class="form-group text-center">
                        <label>Correo</label>
                        <input type="text" name="txtcorreo"  value="${usuario.getCorreo()}"  class="form-control" required>
                    </div>
                    <div class="form-group text-center align-middle">
                        <input type="submit" name="accion" value="Actualizar" class="btn btn-success">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<%@ include file="commons/footer.jspf"%>