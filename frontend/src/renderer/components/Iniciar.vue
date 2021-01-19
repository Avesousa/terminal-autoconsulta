<template>
  <div id="cuerpo">
    <Alerta v-if="alerta_error" @cerrarAviso="cerrarAviso" :text="textoAviso" />
    <Cabecera
      ref="cabecera"
      :tecladoVisible="true"
      :teclado="true"
      :menu="true"
      @mostrarTeclado="mostrarTeclado"
      :pageBack="pageBack"
    />
    <div class="container contenedor-principal mt-5">
      <!-- Contenedor de login -->
      <div id="login" class="row seccion text-center">
        <Login
          ref="login"
          :mostradorLogin="true"
          :adhesionCorreo="false"
          :registrador="true"
          @mostrarError="mostrarError"
          :traermeTokenLink="true"
        />
      </div>
    </div>
  </div>
</template>
<script>
import Alerta from "./include/Alerta";
import Cabecera from "./include/Cabecera";
import Login from "./complementos/Login";
import Router from "../router/index";
import Log from "../Log/Log";
import CargandoTeclado from "./include/CargandoTeclado";
const resumen = require("@/assets/btn_resumen.png");
const resumenOn = require("@/assets/btn_resumenOn.png");
const eresumen = require("@/assets/btn_eresumen.png");
const eresumenOn = require("@/assets/btn_eresumenOn.png");
const log = Log.instance().log;

export default {
  name: "resumen",
  components: {
    Alerta,
    Cabecera,
    Login,
    Log,
    CargandoTeclado
  },
  data: function() {
    return {
      loginBoolean: false,
      correoBoolean: false,
      alerta_error: false,
      cargandoTecladoBoolean: false,
      pageBack: "/principal",
      textoAviso: "Ha ocurrido un error",
      keyBoardNumber: false,
      btnLogin: {
        off: resumen,
        on: resumenOn,
        show: resumen
      },
      btnCorreo: {
        off: eresumen,
        on: eresumenOn,
        show: eresumen
      }
    };
  },
  methods: {
    mostrar: function(tipo) {
      if (tipo == "login") {
        this.loginBoolean = !this.loginBoolean;
        this.correoBoolean = false;
      } else {
        this.loginBoolean = false;
        this.correoBoolean = !this.correoBoolean;
      }
      this.cambiarImagenes();
    },

    cambiarImagenes: function() {
      this.btnLogin.show = this.loginBoolean
        ? this.btnLogin.on
        : this.btnLogin.off;
      this.btnCorreo.show = this.correoBoolean
        ? this.btnCorreo.on
        : this.btnCorreo.off;
    },

    mostrarTeclado: function(opcion) {
      this.$refs.login.mostrarTeclado(opcion);
    },
    mostrarError: function(error) {
      this.alerta_error = true;
      this.textoAviso = error;
    },
    cerrarAviso: function() {
      this.alerta_error = false;
    }
  },
  mounted: function() {
    document.addEventListener("keypress", e => {
      this.$refs.cabecera.refresh();
    });
  }
};
</script>
<style>
@import url("./css/principal.css");

p > img {
  width: 10%;
}

.seccion {
  margin-top: 2%;
}
</style>