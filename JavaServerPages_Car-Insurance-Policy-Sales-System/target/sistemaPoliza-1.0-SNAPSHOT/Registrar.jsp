<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Registrarse</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    </head>
    <body style="background-color: graytext">
        <div class="container mtg-4 col-lg-5 mt-4">
            <div class="card col-sm-10">
                <div class="card-body">
                    <form class="form-sign  needs-validation" action="ControladorLogin" method="POST">
                        <div class="form-group text-center m-b-1">
                            <label>Bienvenido al Sistema de Polizas</label><br>
                            <h3>Registrarse</h3>
                            <img src="img/inicio.png" alt="100" width="200"/>
                            <div>                        
                                <label>Ingrese sus datos y una vez registrado sera redirigido a la pagina de ingreso</label><br>
                            </div>
                        </div>
                        <div class="form-group">
                            <label>Identificacion</label>
                            <input type="text" name="txtidentificacion" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>Nombre Completo</label>
                            <input type="text" name="txtnombre" class="form-control" required>
                        </div>

                        <div class="row">
                            <div class="col">
                                <label>Telefono</label>
                                <input type="text" name="txttelefono" class="form-control" required>
                            </div>
                            <div class="col">
                                <label>Correo</label>
                                <input type="email" name="txtcorreo" class="form-control" placeholder="Ingrese un correo valido" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label>Contraseña</label>
                            <input type="password" name="txtpass" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>Nombre Completo Tarjeta</label>
                            <input type="text" name="txtnombretarjeta" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>Numero Tarjeta</label>
                            <input type="text" name="txttarjetanum" class="form-control" pattern="[0-9]{16}" title="Por favor, ingrese un número de tarjeta válido de 16 dígitos" placeholder="Ingrese los 16 digitos de su tarjeta"required>
                        </div>

                        <div class="row">
                            <div class="col">
                                <label>Año vencimiento</label>
                                <input type="number" id="txtanio" name="txtanio" class="form-control" min="23" required>
                            </div>
                            <div class="col">
                                <label>Mes vencimiento</label>
                                <input type="number" id="txtmes" name="txtmes" class="form-control" min="1" max="12" required>
                            </div>
                            <div class="col">
                                <label>CCV</label>
                                <input type="password" inputmode="numeric" pattern="[0-9]{3}" name="txtccv" class="form-control" required>
                            </div>
                        </div>
                        <div class="form-group text-center align-middle">
                            <input type="submit" name="accion" value="Registrar" class="btn btn-primary btn btn-success"/>
                            <button type="button" onclick="location.href = 'index.jsp'" class="btn btn-primary btn btn-block">Regresar</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>


        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.7/dist/umd/popper.min.js" integrity="sha384-zYPOMqeu1DAVkHiLqWBUTcbYfZ8osu1Nd6Z89ify25QV9guujx43ITvfi12/QExE" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.min.js" integrity="sha384-Y4oOpwW3duJdCWv5ly8SCFYWqFDsfob/3GkgExXKV4idmbt98QcxXYs9UoXAB7BZ" crossorigin="anonymous"></script>

    </body>
</html>