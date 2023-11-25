<%@ include file="commons/header.jspf"%>
<%@ include file="commons/navbar.jspf"%>
<div class="container-fluid m-4">
    <div class="row">
        <div class="col-md-4 col-sm-12 mb-3">
            <div class="card">
                <div class="card-body">
                    <h2 class="card-title">Agregar Categoria</h2>
                    <form action="Controlador?menu=Categorias" method="POST">
                        <div class="mb-3">
                            <label for="descripcionCategoria" class="form-label">Descripcion</label>
                            <input type="text" class="form-control" id="descripcionCategoria" name="descripcionCategoria" required>
                        </div>
                        <button name="accion" value="AgregarCategoria" type="submit" class="btn btn-primary">Agregar Categoria</button>
                    </form>
                </div>
            </div>
            <div class="card mt-3">
                <div class="card-body">
                    <h2 class="card-title">Agregar Cobertura</h2>
                    <form action="Controlador?menu=Categorias" method="POST" enctype="multipart/form-data">
                        <div class="mb-3">
                            <label for="descripcionCobertura" class="form-label">Descripcion</label>
                            <input type="text" class="form-control" id="descripcionCobertura" name="descripcionCobertura" required>
                        </div>
                        <div class="mb-3">
                            <label for="idCategoria" class="form-label"></label>
                            <select class="form-select" id="idCategoria" name="idCategoria" required>
                                <option value="">Selecciona una categoría</option>
                                <c:forEach var="categoria" items="${listaCategorias}">
                                    <option value="${categoria.getIdCategoria()}">${categoria.getDescripcion()}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="costoCobertura" class="form-label">Costo (sin espacios)</label>
                            <input type="text" class="form-control" id="costoCobertura" name="costoCobertura" required>
                        </div>
                        <button name="accion" value="AgregarCobertura" type="submit" class="btn btn-primary">Agregar Cobertura</button>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-md-8 col-sm-12">
            <h2 class="my-4">Lista de Categorias y Coberturas</h2>
            <div class="accordion m-4" id="categoriasAccordion">
                <c:set var="i" value="1" />
                <c:forEach var="categoria" items="${listaCategorias}">
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="heading${i}">
                            <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapse${i}" aria-expanded="true" aria-controls="collapse${i}">
                                ${categoria.getDescripcion()}
                            </button>
                        </h2>
                        <div id="collapse${i}" class="accordion-collapse collapse" aria-labelledby="heading${i}" data-bs-parent="#categoriasAccordion">
                            <div class="accordion-body">
                                <ul class="list-group">
                                    <c:forEach var="cobertura" items="${categoria.getListaCoberturas()}">
                                        <li class="list-group-item">
                                            ${cobertura.getDescripcion()}. Costo en colones: ${cobertura.getCosto()}                              
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
</div>
<%@ include file="commons/footer.jspf"%>