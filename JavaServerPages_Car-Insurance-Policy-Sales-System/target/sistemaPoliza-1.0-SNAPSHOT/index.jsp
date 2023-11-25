<!doctype html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Sistema Polizas</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    </head>
    <body style="background-color: graytext">
        <div class="container mtg-4 col-lg-4 mt-4">
            <div class="card col-sm-10">
                <div class="card-body">
                    <form class="form-sign" action="ControladorLogin" method="POST">
                        <div class="form-group text-center">
                            <h3>Inicio</h3>
                            <label>Bienvenido al Sistema de Polizas</label><br>
                            <img src="img/inicio.png" alt="150" width="250"/>
                        </div>
                        <div class="form-group text-center">
                            <label>Usuario</label>
                            <input type="text" name="txtuser" class="form-control" placeholder="Ingrese su identificacion"/> 
                        </div>
                        <div class="form-group text-center">
                            <label>Contraseņa</label>
                            <input type="password" name="txtpass" class="form-control" placeholder="Ingrese su contraseņa"/> 
                            <input type="submit" name="accion" value="Ingresar" class="btn btn-primary btn btn-block"/>
                            <input type="submit" name="accion" value="Registrar" class="btn btn-primary btn btn-success"/>
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