<%@ include file="commons/header.jspf"%>
<%@ include file="commons/navbar.jspf"%>
<c:if test="${usuario != null && usuario.rol eq 'Usuario'}"> 
    <div class="container-fluid m-4">
        <div class="col-md-8 col-sm-12">
            <h2 class="my-4">Busque su p�liza por Placa</h2>

            �

            ������� <form action="Controlador?menu=Principal" method="POST" class="mb-4">
                <div class="input-group">
                    <input type="text" class="form-control" name="buscarPlaca" placeholder="Buscar por placa" aria-label="Buscar por placa">
                    <button name="accion" value="Busqueda" class="btn btn-primary" type="submit" id="buscarPlacaBtn">Buscar</button>
                </div>
            </form>

            �

            ������� <h1 class="text-center m-4">Mis P�lizas</h1>
            <table class="table table-striped">
                <thead>
                    <tr>
                        <th>Id P�liza</th>
                        <th>N�mero de placa</th>
                        <th>Valor asegurado</th>
                        <th>Fecha de inicio</th>
                        <th>Fecha de vencimiento</th>
                        <th>Detalles</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${usuario.getListaPolizas()}" var="poliza">
                        <tr>
                            <td><c:out value="${poliza.getIdPoliza()}" /></td>
                            <td><c:out value="${poliza.getNumeroPlaca()}" /></td>
                            <td><c:out value="${poliza.getValorAsegurado()}" /></td>
                            <td><c:out value="${poliza.getFechaCreacion()}" /></td>
                            <td><c:out value="${poliza.getFechaVencimiento()}" /></td>
                            <td>
                                <form action="Controlador?menu=Detalles" method="POST">
                                    <input type="hidden" name="idPoliza" value="${poliza.getIdPoliza()}">
                                    <button name="accion" value="MostrarDetalle" type="submit" class="btn btn-primary">Ver detalles</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    <c:if test="${fn:length(usuario.getListaPolizas()) == 0}">
                        <tr>
                            <td colspan="6">No hay p�lizas para mostrar</td>
                        </tr>
                    </c:if>
                </tbody>
            </table>
        </div>
    </div>
</c:if>
<c:if test="${usuario != null && usuario.rol eq 'Administrador'}"> 
    <main>
        <div class="row mt-5">
            <div class="col-12">
                <h1 class="text-center">Panel de Administraci�n</h1>
            </div>
        </div>

        �

        ��� <div class="row mt-4">
            <div class="col-12">
                <p class="lead">Bienvenido al panel de administraci�n del Sistema de P�lizas CR. Aqu� podr�s gestionar los siguientes aspectos del sistema:</p>
            </div>
        </div>

        �

        ��� <div class="row mt-4">
            <div class="col-md-4 col-sm-12">
                <div class="card text-center">
                    <div class="card-body">
                        <h5 class="card-title">Gesti�n de usuarios</h5>
                        <p class="card-text">Puedes ver la lista de usuarios y sus polizas.</p>           
                    </div>
                </div>
            </div>
            <div class="col-md-4 col-sm-12">
                <div class="card text-center">
                    <div class="card-body">
                        <h5 class="card-title">Gesti�n de marcas</h5>
                        <p class="card-text">Se pueden agregar marcas y modelos a las marcas.</p>
                    </div>
                </div>
            </div>
            <div class="col-md-4 col-sm-12">
                <div class="card text-center">
                    <div class="card-body">
                        <h5 class="card-title">Gesti�n de categor�as</h5>
                        <p class="card-text">Se pueden agregar categorias y coberturas a las categorias.</p>
                    </div>
                </div>
            </div>
        </div>
    </main>
</c:if>
<%@ include file="commons/footer.jspf"%>