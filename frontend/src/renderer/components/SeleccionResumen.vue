<template>
    <div id="cuerpo">
        <Cabecera :teclado="false" :menu="true" :pageBack="pageBack"/>
        <div class="container" :class="{'apagado':ocultarTodo}">
            <div class="row">
                <div class="col-12 text-center">
                    <h3>¿Querés obtener tu resumen de cuenta?</h3>
                    <h4>Seleccioná cuál querés ver: </h4>
                </div>
            </div> 
            <Autentificacion v-show="!validacionIngreso" ref="validador"/>
            <div v-if="hayResumen" class="row botones div-botones">
                <div v-show="muestra" v-for="resumen in resumenes" class="col-12 div-resumenes">
                    <div class="div-resumen">
                        <router-link :to="{name:'resumen', params:{tarjeta:resumen.tarjeta, fecha:resumen.fecha}}">
                            <img src="~@/assets/btn_cierreresumen.png" alt="Resumen">
                            <div class="texto-resumen">Cierre {{resumen.fecha}}</div>
                        </router-link>
                    </div>
                </div>
                <div v-show="!muestra" v-for="resumen in resumenes" class="col-12 div-resumenes">
                    <div class="div-resumen">
                        <div class="div-resumen" v-on:click="enviarCorreo(resumen.fecha,resumen.tarjeta)">
                            <img src="~@/assets/btn_cierreresumen.png" alt="Resumen">
                            <div class="texto-resumen">Cierre {{resumen.fecha}}
                        </div>
                    </div>
                    </div>
                </div>
            </div>
            <div v-else class="row botones div-botones">
                <div class="div-resumen">
                    <h1>{{mensaje}}</h1>
                </div>
            </div>
        </div>
        <div v-if="isLoad" class="container">
            <div class="col-12 load load-xl load-w">
                <div class="row">
                    <img src="~@/assets/load.gif" alt="load carrefour"><br>
                    <span>Enviando... Aguardá unos instantes</span>
                </div>
            </div>
        </div>
        <div v-if="isError">
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
    import { library } from '@fortawesome/fontawesome-svg-core';
    import { faUserSecret } from '@fortawesome/free-solid-svg-icons';
    import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome';
    import Cabecera from './include/Cabecera';
    import Cliente from '../modelos/Cliente';
    import SolicitudServicios from '../solicitudes/SolicitudServicios';
    import Resumen from '../modelos/Resumen';
    import Router from '../router/index';
    import Log from '../Log/Log';
    import Autentificacion from '../solicitudes/Autentificacion';
    import router from '../router/index';
    const log = Log.instance().log;

    library.add(faUserSecret)

    export default {
        name: 'listaresumen',
        components: { 
            Autentificacion,
            FontAwesomeIcon, 
            SolicitudServicios,
            Cabecera,
            Cliente,
            Router,
            Resumen,
            Log,
        },
        data(){ 
            return{
                service: SolicitudServicios.instance(), 
                pageBack: "/principal", 
                tipo: Router.currentRoute.params.tipo,
                cliente: Cliente.instanciar(),
                resumenes: null,
                hayResumen: true,
                muestra: Boolean,
                mensaje: "No tiene disponible ningún resumen",
                validacionIngreso: true,
                ocultarTodo: false,
                isLoad: false,
                isError: false,
                mensajeError: "Ha ocurrido un error"
            }
        },
        created: function(){
            this.getListaResumen();
        },
        methods: {
            getListaResumen: function(){
                log.info("--> SeleccionResumen/getListaResumen");
                this.muestra = (this.tipo == 'resumen');
                this.service.conectar(new Resumen(
                    this.cliente.getFechaNacimiento(),
                    this.cliente.getDocumento(),
                    this.cliente.getTipoDocumento()),'POST','getResumen')
                    .then(valor => {
                        log.info("[SeleccionResumen] - Termina correctamente la conexión, arrojando resultado");
                        this.resumenes = valor.resultado;
                        this.hayResumen = this.resumenes.length > 0;
                    }).catch(valor =>{
                        log.info("--> SeleccionResumen/getListaResumen/ Arrojo error en la conexión");
                        if(valor.flag){
                            log.info("--> SeleccionResumen/getListaResumen/ Reitenta la conexión");
                            this.service.header();
                            this.getListaResumen();
                        }else{
                            log.info("[SeleccionResumen] - Ocurrió un error al intentar validar los datos CODE: " + valor.error);
                            this.hayResumen = false;
                            this.validacionIngreso = false;
                            this.$refs.validador.setMensaje(valor.mensaje);
                        }
                    });
            },
            enviarCorreo: function(fecha,tarjeta){
                log.info("--> SeleccionResumen/enviarCorreo");
                Router.push({
                    name: 'enviar',
                    params: {date:fecha,card:tarjeta}
                })
            }
        }
    }
</script>
<style>
    @import url('./css/principal.css');
    @import url('https://fonts.googleapis.com/css?family=Roboto&display=swap');
    
    a{
        text-align: center;
        color: white;
        font-family: 'Roboto', sans-serif;
        font-size: 1.5em;
        cursor: pointer;
    }

    .div-resumen{
        position: relative;
        text-align: center;
        color: white;
        font-family: 'Roboto', sans-serif;
        font-size: 1.5em;
        cursor: pointer;
    }

    .div-resumen img{
        width: 100%;
    }

    .texto-resumen{
        position: absolute;
        top: 50%;
        left: 50%;
        transform: translate(-50%, -50%);
        cursor: pointer;
    }

    h3{
        font-family: 'Roboto', sans-serif;
        font-size: 7.5vh;
        font-weight: 700;
        color: white;
        letter-spacing: -3px;
    }

    h4{
        font-family: 'Roboto', sans-serif;
        font-weight: 300;
        font-size: 2em;
        color: white;
        letter-spacing: -1px;
        margin-top: -5px;
    }

    p > img{
        width: 10%;
    }
    #logo{
        width: 50%;
    }
    .div-botones button {
    width: 80%;
    display: flex;
    margin: 0 auto;
    text-align: center;
    outline:none;
    cursor: pointer;
    border: none;
    transition: all 0.15s ease;
    box-sizing: border-box;
  }

  .div-botones{
      margin: 0 auto;
      overflow:auto;
      overflow-x: hidden;
      width: 60%;
      height: 44vh;
    }

    .div-botones::-webkit-scrollbar{
        background-color: rgba(255, 255, 255, 0.2);
        border-radius: 12px;
        width: 10px;
    }

    .div-botones::-webkit-scrollbar-thumb {
        background: rgba(255, 255, 255, 0.2);
        border-radius: 12px;
    }

    .div-botones::-webkit-scrollbar-thumb:hover{
        background: rgba(255, 255, 255, 0.6);
        border-radius: 12px;
    }

    .apagado{
        display: none;
    }

</style>