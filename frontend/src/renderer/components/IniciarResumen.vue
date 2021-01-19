<template>
  <div id="cuerpo">
    <Alerta v-if="alerta_error" @cerrarAviso="cerrarAviso" :text="textoAviso" />
    <Cabecera
      :teclado="true"
      :menu="true"
      ref="cabecera"
      @mostrarTeclado="mostrarTeclado"
      :pageBack="pageBack"
    />
    <div class="container contenedor-principal">
      <div class="row">
        <div class="col-12 text-center">
          <h3>¿Querés obtener tu resumen de cuenta?</h3>
          <h4>Elegí la opción deseada:</h4>
        </div>
      </div>
      <div class="row botones">
        <div class="col-6 btnVista">
          <button
            class="alt summary"
            @click="mostrar('login')"
            :class="{'buttonOn' : isButtonLoginOn}"
          >
            <div>
              <img src="@/assets/btn_resumen.png" alt="cuentaOnline" />
            </div>
          </button>
        </div>
        <div class="col-6 btnVista">
          <button
            class="alt summary"
            @click="mostrar('correo')"
            :class="{'buttonOn' : isButtonCorreoOn}"
          >
            <div>
              <img src="@/assets/btn_eresumen.png" alt="Resumen Electrónico" />
            </div>
          </button>
        </div>
      </div>

      <div>
        <!-- Contenedor de login -->
        <div id="login" v-if="loginBoolean" class="row seccion text-center">
          <Login
            ref="login"
            :mostradorLogin="true"
            :adhesionCorreo="false"
            :traermeCliente="true"
            @mostrarError="mostrarError"
          />
        </div>
        <!-- Contenedor de correo -->
        <div id="correo" v-if="correoBoolean" class="row seccion text-center">
          <Login
            ref="login"
            :adhesionCorreo="false"
            :traermeCorreo="true"
            @mostrarError="mostrarError"
          />
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import Alerta from "./include/Alerta";
import Cabecera from "./include/Cabecera";
import Login from "./complementos/Login";
import CargandoTeclado from "./include/CargandoTeclado";

export default {
  name: "resumen",
  components: {
    Cabecera,
    Login,
    Alerta,
    CargandoTeclado
  },
  data: function() {
    return {
      loginBoolean: false,
      correoBoolean: false,
      isButtonLoginOn: false,
      isButtonCorreoOn: false,
      pageBack: "/principal",
      valorBotonLogin: "Ingresar",
      valorBotonCorreo: "Enviar",
      alerta_error: false,
      textoAviso: "Ha ocurrido un error",
      keyBoardNumber: false
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
      this.cambiarEstado();
      this.visibilidad();
      try {
        this.$refs.login.enfocar(true, tipo == "login");
      } catch (error) {}
    },

    cambiarEstado: function() {
      this.isButtonLoginOn = this.loginBoolean;
      this.isButtonCorreoOn = this.correoBoolean;
    },

    mostrarTeclado: function(opcion) {
      this.$refs.login.mostrarTeclado(opcion);
    },
    visibilidad: function() {
      this.$refs.cabecera.visibilidadDeTeclado(
        this.loginBoolean || this.correoBoolean
      );
    },
    mostrarError: function(error) {
      this.alerta_error = true;
      this.textoAviso = error;
    },
    cerrarAviso: function() {
      this.alerta_error = false;
    }
  },
  mounted(){
    document.addEventListener("keypress", e => {
      this.$refs.cabecera.refresh();
    });
  }
};
</script>
<style>
@import url("./css/principal.css");

h3 {
  font-family: "Roboto", sans-serif;
  font-size: 7vh;
  font-weight: 700;
  color: white;
  letter-spacing: -3px;
}

h4 {
  font-family: "Roboto", sans-serif;
  font-weight: 300;
  font-size: 1.5em;
  color: white;
  letter-spacing: -1px;
  margin-top: -5px;
}

p > img {
  width: 10%;
}

.seccion {
  margin-top: 2%;
}
</style>