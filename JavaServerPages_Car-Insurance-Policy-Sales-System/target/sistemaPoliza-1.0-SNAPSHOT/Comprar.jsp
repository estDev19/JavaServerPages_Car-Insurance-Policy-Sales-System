<%@ include file="commons/header.jspf"%>
<%@ include file="commons/navbar.jspf"%>
<div class="container">
    <form class="row g-4 m-5" action="Controlador?menu=Comprar" method="POST">
        <div class="col-4">
            <label class="form-label">Número de placa</label>
            <input type="text" name="txtNumeroPlaca" class="form-control">
        </div>
        <div class="col-4">
            <label class="form-label">Modelo</label>
            <div >
                <select name="selectMarcaModelo">
                    <option selected>Modelo</option>
                    <c:forEach var="marca" items="${marcas}">
                        <optgroup label="${marca.getNombre()}">
                            <c:forEach var="modelo" items="${marca.getListaModelos()}">
                                <option value="${modelo.getIdModelo()}" >${modelo.getFechaAnio()} - ${modelo.getNombre()}</option>
                            </c:forEach>
                        </optgroup>
                    </c:forEach>
                </select>
            </div>
        </div>
        <div class="col-4">
            <label class="form-label">Valor asegurado (sin espacios)</label>
            <input type="text" class="form-control" name="txtValorAsegurado">
        </div>
        <div class="col-4">
            <label class="form-label">Plazo</label><br>
            <div class="col-md-4">
                <select class="form-select" name="selectPlazo" id="selectPlazo">
                    <option selected>Plazo</option>
                    <option>Trimestral</option>
                    <option>Semestral</option>
                    <option>Anual</option>
                </select>
            </div>
        </div>
        <div class="col-4">
            <label class="form-label">Fecha de inicio de vigencia</label>
            <input type="text" class="form-control" placeholder="dd/mm/aaaa" name="txtFechaInicio">
        </div>
        <label>Categoría-coberturas</label>
        <div class="col-8">
            <div class="mb-3 col-4">
                <label for="idCategoria" class="form-label"></label>
                <select class="form-select" id="idCategoria" name="idCategoria" required>
                    <option value="">Selecciona una cobertura</option>
                    <c:forEach var="categoria" items="${categorias}">
                        <optgroup label="${categoria.getDescripcion()}">
                            <c:forEach var="cobertura" items="${categoria.getListaCoberturas()}">
                                <option value="${cobertura.getIdCobertura()}"> ${cobertura.getDescripcion()}</option>
                            </c:forEach>
                        </optgroup>
                    </c:forEach>
                </select>
            </div>           
            <div class="col-4">
          
                <button name="accion" value="ComprarPoliza" type="submit" class="btn btn-primary">Comprar Poliza</button>
            </div>
        </div>
    </form>
</div>
<%@ include file="commons/footer.jspf"%>