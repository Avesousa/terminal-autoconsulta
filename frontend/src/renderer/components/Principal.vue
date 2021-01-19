<template>
  <div id="cuerpo" class="page-principal">
    <Alerta v-if="alerta_error" @cerrarAviso="cerrarAviso" :text="textoAviso"/>
    <Cabecera :teclado="false" :menu="false"/>
    <main class="container ">
      <div class="row botones">
        <div class="col-12 btnVista">
          <button class="alt">
            <router-link to="/iniciar">
              <img src="~@/assets/btn_cuentaOnline.png" alt="cuentaOnline">
            </router-link>
          </button>
        </div>
        <div class="col-12 btnVista">
          <button class="alt"><router-link to="/iniciarresumen"><img src="~@/assets/btn_resumenelectronico.png" alt="Resumen Electrónico"></router-link></button>
        </div>
        <div class="col-12 btnVista last">
          <button class="alt">
            <router-link :to="{name:'navegador', params:{link:this.urlContacto}}">
              <img src="~@/assets/btn/contact.png" alt="Resumen Electrónico">
            </router-link></button>
        </div>
        <div class="col-12 btnVista">
          <p>Versión: {{version}}</p>
        </div>
      </div>
    </main>
    <div class="container-fluid footer">
      <div class="col-12 publicidad">
        <img src="~@/assets/publicidad.png" alt="Publicidad de aplicación">
      </div>
    </div>
  </div>

</template>

<script>
  import Cabecera from './include/Cabecera';
  import Router from '../router/index';
  import Link from '../modelos/Link';
  import Alerta from './include/Alerta';
  import SolicitudServicios from "../solicitudes/SolicitudServicios";
  import Log from '../Log/Log';
  import Properties from '../modelos/Properties';
  const link = Link.instanciar();
  const log = Log.instance().log;
  const properties = Properties.instanciar();
  const configuracion = require("../../../package.json");

  export default {
    name: 'principal',
    components: {
      Cabecera,
      Router,
      Alerta,
      SolicitudServicios
    },
    data: function(){
      return {
        urlContacto: "",
        alerta_error: false,
        service: SolicitudServicios.instance(),
        version: configuracion.version,
        time: 0,
        flagTime: false,
      }
    },
    methods: {

      cerrarAviso: function(){
        this.alerta_error = false;
      },
      mostrarError: function(error){
        this.alerta_error = true;
        this.textoAviso = error;
      },
      getUrlContacto: function(){
        log.info("--> Principal/getUrlContacto");
        this.service.consultar('getContacto')
          .then(valor => {
              log.info("[Consultando URL de contacto] - Termina correctamente la conexión, arrojando resultado");
              this.urlContacto = valor.resultado;
          }).catch(valor =>{
              log.info("--> Principal/getUrlContacto/ Ha arrojado un error en la conexión");
              if(valor.flag){
                  log.info("--> Principal/getUrlContacto/ Reintenta la conexión");
                  this.service.header();
                  this.getUrlContacto();
              }else{
                  log.info("[Consultando URL de contacto] - Ocurrió un error al intentar validar los datos CODE: " + valor.error);
                  this.mostrarError(valor.mensaje);
              }
          });
      },
      refrescar: function(){
        console.log("--> Cabecera/refresh");
        clearTimeout(this.time);
        if(properties.getIsEnable()){
          this.contar();
        }else{
            console.log(properties.getError());
        }
      },
      comenzarContador(){
        if(!this.flagTime){
          this.contar();
          this.flagTime = true;
        }
      },
      contar(){
        this.time = setTimeout(() => {
            this.$route.path == "/" ? 
            Router.push("inactividad").catch(() => {}) :
            null;
          },properties.getTimer());
      }
    },
    mounted(){
      this.getUrlContacto();
      document.querySelector("#cuerpo.page-principal").addEventListener("click",this.refrescar);
      this.comenzarContador();
    }
  }
</script>

<style>
  @import url('https://fonts.googleapis.com/css?family=Source+Sans+Pro');
  @import url('./css/principal.css');

  .footer{
    padding: 0;
    position: absolute;
    bottom: 0;
  }
  .publicidad img{
    width: 100%;
  }

  .publicidad{
    padding: 0;
    width: 100%;
    display: inline-flexbox;
    bottom: 0px;
  }

  main {
    display: flex;
    justify-content: space-between;
  }


  .left-side {
    display: flex;
    flex-direction: column;
  }


  
</style>
