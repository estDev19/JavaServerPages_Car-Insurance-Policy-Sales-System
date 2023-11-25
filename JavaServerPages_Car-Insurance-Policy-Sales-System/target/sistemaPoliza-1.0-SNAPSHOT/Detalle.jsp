<%@ include file="commons/header.jspf"%>
<%@ include file="commons/navbar.jspf"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Detalle de la poliza</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    </head>
    <body>

        <div class="container mt-3">
            <h1 class="mb-4">Detalles de la póliza</h1>
            <div class="row">
                <div class="col-md-6">
                    <div class="card mb-3">
                        <div class="card-header">Información general</div>
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item"><strong>ID de la Póliza:</strong> ${detallePoliza.getIdPoliza()}</li>
                            <li class="list-group-item"><strong>Placa del carro:</strong> ${detallePoliza.getNumeroPlaca()}</li>
                            <li class="list-group-item"><strong>Valor Asegurado:</strong> ${detallePoliza.getValorAsegurado()}</li>
                            <li class="list-group-item"><strong>Plazo:</strong> ${detallePoliza.getPlazo()}</li>
                            <li class="list-group-item"><strong>Fecha de Creación:</strong> ${detallePoliza.getFechaCreacion()}</li>
                            <li class="list-group-item"><strong>Fecha de Vencimiento:</strong> ${detallePoliza.getFechaVencimiento()}</li>
                        </ul>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="card">
                        <div class="card-header">Información adicional</div>
                        <ul class="list-group list-group-flush">
                            <li class="list-group-item"><strong>ID de Usuario:</strong> ${detallePoliza.getIdUsuario()}</li>
                            <li class="list-group-item"><strong>Nombre de Modelo:</strong> ${detalleModelo.getNombre()}</li>
                            <li class="list-group-item"><strong>Nombre de Marca:</strong> ${detalleMarca.getNombre()}</li>
                            <li class="list-group-item"><strong>Descripción de Cobertura:</strong> ${detalleCobertura.getDescripcion()}</li>
                            <li class="list-group-item"><strong>Descripción de Categoría:</strong> ${detalleCategoria.getDescripcion()}</li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js" integrity="sha384-zYPOMqeu1DAVkHiLqWBUTcbYfZ8osu1Nd6Z89ify25QV9guujx43ITvfi12/QExE" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js" integrity="sha384-Y4oOpwW3duJdCWv5ly8SCFYWqFDsfob/3GkgExXKV4idmbt98QcxXYs9UoXAB7BZ" crossorigin="anonymous"></script>

    </body>
</html>
<%@ include file="commons/footer.jspf"%>