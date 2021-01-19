<template>
    <div id="cuerpo">
        <Cabecera :teclado="false" :menu="true" :isSummaryPdf="true" :pageBack="pageBack"/>
        <div v-if="isOk">
            <div v-if="isLoad" class="container">
                <div class="col-12 load load-xl load-w mt-5">
                    <div class="row">
                        <img src="~@/assets/load.gif" alt="load carrefour"><br>
                        <span>Cargando documento de pdf...</span>
                    </div>
                </div>
            </div>
            <div v-else id="pdf">
                <embed :src="source" width="100%" height="800" type="text/html" />
            </div>
        </div>
        <div v-else>
            <div class="container mt-5">
                <div class="row mt-5">
                    <div class="col-12 text-center aviso">
                        <h4>{{mensajeError}}</h4>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
    import Router from '../router/index';
    import Token from '../modelos/Token';
    import Cliente from '../modelos/Cliente';
    import SolicitudServicios from "../solicitudes/SolicitudServicios";
    import Cabecera from './include/Cabecera';
    import FileSaver from 'file-saver';
    import Log from '../Log/Log';
    const log = Log.instance().log;
    export default {
        name: "Resumen",
        components:{
            Router,
            Token,
            Cliente,
            SolicitudServicios,
            Cabecera,
            Log,
        },
        data: function(){
            return{
            fecha: Router.currentRoute.params.fecha,
            tarjeta: Router.currentRoute.params.tarjeta,
            cliente: Cliente.instanciar(),
            service: SolicitudServicios.instance(),
            pageBack: {name:"listaresumen", params: { tipo: 'resumen' }},
            token: Token.instanciar(),
            source: "",
            mensajeError: "",
            isLoad: true,
            isOk: true,
            }
        },
        created: function(){
            log.info("--> Resumen/created");
            this.service.conectar({
                nacimiento: this.cliente.getFechaNacimiento(),
                tarjeta: this.tarjeta,
                fecha: this.fecha},
                'GET',
                'getResumenTarjeta')
                .then(valor => {
                    log.info("--> Resumen/created/ Ha realizado la consulta correctamente");
                    this.source = 'data:application/pdf;base64,'+ valor.resultado;
                    this.isLoad = false;
                }).catch(valor => {
                    log.info("--> Resumen/created/ Ha ocurrido un error en la conexión/consulta");
                    this.isOk = false;
                    this.mensajeError = valor.mensaje;
                });
        },
        methods: {
            crear: function(data, fileName){
                log.info("--> Resumen/crear");
                var a = document.createElement("a");
                document.body.appendChild(a);
                a.style = "display: none";
                var blob = new Blob([data], {type: "octet/stream"});
                var url = window.URL.createObjectURL(blob);
                return url;
            },

            saveData: function() {
                log.info("--> Resumen/saveData");
                var a = document.createElement("a");
                document.body.appendChild(a);
                a.style = "display: none";
                return function (data, fileName) {
                    var json = JSON.stringify(data);
                    blob = new Blob([json], {type: "octet/stream"});
                    url = window.URL.createObjectURL(blob);
                    a.href = url;
                    a.download = fileName;
                    a.click();
                    window.URL.revokeObjectURL(url);
                }
            }
        }
    }
</script>

<style>

#pdf embed{
    margin-top: -45px;
}

</style>