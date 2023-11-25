<%@ include file="commons/header.jspf"%>
<%@ include file="commons/navbar.jspf"%>
<div class="container-fluid m-4">
    <div class="col-md-8 col-sm-12">
        <h2 class="my-4">Lista de Clientes y Pólizas</h2>
        <div class="accordion m-4" id="marcasAccordion">
            <c:set var="i" value="1" />
            <c:forEach var="usuario" items="${listaClientes}">
                <div class="card mb-3">
                    <div class="card-header">
                        <h2 class="mb-0">
                            <button class="btn btn-link" type="button" data-bs-toggle="collapse" data-bs-target="#collapse${i}" aria-expanded="true" aria-controls="collapse${i}">
                                ${usuario.getNombre()}
                            </button>
                        </h2>
                    </div>
                    <div id="collapse${i}" class="collapse" aria-labelledby="heading${i}" data-bs-parent="#marcasAccordion">
                        <div class="card-body">
                            <ul class="list-group">
                                <c:forEach var="poliza" items="${usuario.getListaPolizas()}">
                                    <li class="list-group-item">
                                        <div class="row">
                                            <div class="col-6">
                                                <strong>ID de la Póliza:</strong> ${poliza.getIdPoliza()} <br>
                                                <strong>Placa del carro:</strong> (${poliza.getNumeroPlaca()})
                                            </div>
                                            <div class="col-6 text-end">
                                                <form action="Controlador?menu=Clientes" method="POST">
                                                    <input type="hidden" name="idPoliza" value="${poliza.getIdPoliza()}">
                                                    <button name="accion" value="MostrarDetalle" type="submit" class="btn btn-primary">Ver detalle de la póliza</button>
                                                </form>
                                            </div>
                                        </div>
                                    </li>
                                </c:forEach>
                            </ul>
                        </div>
                    </div>
                </div>
                <c:set var="i" value="${i + 1}" />
            </c:forEach>
        </div>
    </div>
</div>
<%@ include file="commons/footer.jspf"%>