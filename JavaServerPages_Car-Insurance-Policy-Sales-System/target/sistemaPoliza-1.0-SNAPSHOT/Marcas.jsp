<%@ include file="commons/header.jspf"%>
<%@ include file="commons/navbar.jspf"%>
<div class="container-fluid m-4">
    <div class="row">
        <div class="col-md-4 col-sm-12 mb-3">
            <div class="card">
                <div class="card-body">
                    <h2 class="card-title">Agregar Marca</h2>
                    <form action="Controlador?menu=Marcas" method="POST">
                        <div class="mb-3">
                            <label for="nombreMarca" class="form-label">Nombre de la marca</label>
                            <input type="text" class="form-control" id="nombreMarca" name="nombreMarca" required>
                        </div>
                        <button name="accion" value="AgregarMarca" type="submit" class="btn btn-primary">Agregar Marca</button>
                    </form>
                </div>
            </div>
            <div class="card mt-3">
                <div class="card-body">
                    <h2 class="card-title">Agregar Modelo</h2>
                    <form action="Controlador?menu=Marcas" method="POST" enctype="multipart/form-data">
                        <div class="mb-3">
                            <label for="nombreModelo" class="form-label">Nombre del modelo</label>
                            <input type="text" class="form-control" id="nombreModelo" name="nombreModelo" required>
                        </div>
                        <div class="mb-3">
                            <label for="idMarca" class="form-label">Marca</label>
                            <select class="form-select" id="idMarca" name="idMarca" required>
                                <option value="">Selecciona una marca</option>
                                <c:forEach var="marca" items="${listaMarcas}">
                                    <option value="${marca.getIdMarca()}">${marca.getNombre()}</option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="mb-3">
                            <label for="fechaAnio" class="form-label">Año</label>
                            <input type="number" class="form-control" id="fechaAnio" name="fechaAnio" pattern="[0-9]{4}" min="2000" required>
                        </div>
                        <div class="mb-3">
                            <label for="imagen" class="form-label">Imagen del modelo (Valido unicamente JPG)</label>
                            <input type="file" class="form-control" id="imagen" name="imagen" required accept="image/jpg">

                        </div>
                        <button name="accion" value="AgregarModelo" type="submit" class="btn btn-primary">Agregar Modelo</button>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-md-8 col-sm-12" ">
            <h2 class="my-4">Lista de Marcas y Modelos</h2>
            <div class="accordion m-4" id="marcasAccordion">
                <c:set var="i" value="1" />
                <c:forEach var="marca" items="${listaMarcas}">
                    <div class="accordion-item" >
                        <h2 class="accordion-header" id="heading${i}">
                            <button  class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#collapse${i}" aria-expanded="true" aria-controls="collapse${i}">
                                ${marca.getNombre()}
                            </button>
                        </h2>
                        <div id="collapse${i}" class="accordion-collapse collapse" aria-labelledby="heading${i}" data-bs-parent="#marcasAccordion">
                            <div class="accordion-body">
                                <ul class="list-group">
                                    <c:forEach var="modelo" items="${marca.getListaModelos()}">
                                        <li class="list-group-item">
                                            ${modelo.getNombre()} (${modelo.getFechaAnio()})
                                            <img src="data:image/jpg;base64,${modelo.getBase64Image()}" alt="${modelo.getNombre()}" class="img-thumbnail" width="100">
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