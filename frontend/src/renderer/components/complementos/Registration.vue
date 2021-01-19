<template>
  <div id="cuerpo">
    <Alerta v-if="alerta_error" @cerrarAviso="cerrarAviso" :text="textoAviso" />
    <Cabecera
      :teclado="true"
      :menu="true"
      :tecladoVisible="true"
      ref="cabecera"
      @mostrarTeclado="mostrarTeclado"
      :pageBack="pageBack"
    />
      <div class="container contenedor-principal mt-5">
        <div class="row seccion text-center">
          <!-- SELECT DOCUMENTO - DOCUMENTO-->
          <div class="col-12">
            <div class="row">
              <div class="col-2 izquierda">
                <div class="row">
                  <div
                    class="select-custom-div col-12"
                    :class="{'select-custom-div-push':selectOn}"
                    v-on:click="runSelect()"
                  >
                    <i class="ico_left ico_dni"></i>
                    <div ref="selectDocumento">DNI</div>
                    <i class="ico_right ico_flecha"></i>
                  </div>
                </div>
                <div v-if="selectOn" ref="optionDocumento" class="row option-custom-div">
                  <div class="col-12">
                    <div id="selectDocumento">
                      <ul id="tipo">
                        <li>L.C</li>
                        <li>DNI</li>
                        <li>L.E</li>
                      </ul>
                    </div>
                  </div>
                </div>
              </div>
              <div class="col-5 derecha">
                <input
                  placeholder="Número"
                  name="documento"
                  id="documento"
                  ref="documento"
                  @click="mostrarTeclado(false)"
                  v-model="documento"
                  @focus="foco"
                />
              </div>
            </div>
          </div>
          <!-- CORREO -->
          <div class="col-12">
            <div class="row">
              <div class="col-7 centrar centrado margin-top-0">
                <i class="ico_left ico_usuario"></i>
                <input
                  type="text"
                  name="correo"
                  id="correo"
                  ref="correo"
                  placeholder="E-mail"
                  v-model="correo"
                  @focus="foco"
                  @click="mostrarTeclado(false)"
                />
              </div>
            </div>
          </div>
          <div class="col-12">
            <div class="row">
              <div class="col-7 centrar centrado">
                <i class="ico_left ico_fecha"></i>
                <the-mask
                  :mask="['##/##/####']"
                  placeholder="Fecha de Nacimiento"
                  name="fechaNacimiento"
                  id="fechaNacimiento"
                  @click.native="mostrarTeclado(false)"
                  v-model="fechaNacimiento"
                  @focus.native="foco"
                />
              </div>
            </div>
          </div>
          <div v-if="isLoad" class="col-12 load load-w">
            <div class="row">
              <img src="~@/assets/load.gif" alt="load carrefour" />
              <br />
              <span>Espere por favor...</span>
            </div>
          </div>
          <div v-else class="col-12">
            <div class="row">
              <div class="col-7 centrar centrado divBoton">
                <input @click="registrar()" type="button" value="Registrate" />
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
</template>
<script>
import Alerta from "../include/Alerta";
import Cabecera from "../include/Cabecera";
import Teclado from "../include/Teclado";
import SolicitudServicios from "../../solicitudes/SolicitudServicios";
import Autentificacion from "../../solicitudes/Autentificacion";
import Router from "../../router/index";
import { TheMask } from "vue-the-mask";
import Log from "../../Log/Log";
import NuevoUsuario from "../../modelos/NuevoUsuario";
import CargandoTeclado from '../include/CargandoTeclado';

const log = Log.instance().log;
const teclado = Teclado.instanciar();

export default {
  name: "Registration",
  components: {
    Cabecera,
    TheMask,
    Autentificacion,
    SolicitudServicios,
    NuevoUsuario,
    Alerta,
    Teclado,
    CargandoTeclado
  },
  params:{
    ventanaLogin: false,
  },
  data: function() {
    return {
      tecladoNumericoVisible: false,
      pageBack: "/iniciar",
      tecladoVisible: false,
      inputSeleccionado: false,
      isLoad: false,
      clickSelect: false,
      selectOn: false,
      validacionIngreso: true,
      tipoDocumento: "2",
      service: SolicitudServicios.instance(),
      fechaNacimientoMask: "",
      alerta_error: false,
      textoAviso: "Ha ocurrido un error",
      documento: "",
      correo: "",
      date: "",
      fechaNacimiento: "",
      nombreDeInput: "",
      cargandoTecladoBoolean: false,
      keyBoardNumber: false
    };
  },
  methods: {
    runSelect: function() {
      this.selectOn = !this.selectOn;
      this.clickSelect = this.selectOn;
    },
    llenarSelect(tipo, val, ref) {
      if (tipo == "tipo") {
        this.$refs[ref].innerHTML = val;
      }
      this.enfocar();
    },
    stopSelect: function() {
      this.selectOn = false;
    },
    enfocar(){
      console.log("Registro ===> " + this.nombreDeInput);
      if(this.nombreDeInput != ""){
          console.log("Nombre de input ===> " + this.nombreDeInput);
          this.$refs[this.nombreDeInput].focus();
      }else{
          this.$refs['documento'].focus();
          console.log("LA VENTANA VIENE DE LOGIN ===> " + this.ventanaLogin);
          ventanaLogin ? null : this.mostrarTeclado(false);
      }
    },
    cambiarImagenes: function() {
      this.btnLogin.show = this.loginBoolean
        ? this.btnLogin.on
        : this.btnLogin.off;
      this.btnCorreo.show = this.correoBoolean
        ? this.btnCorreo.on
        : this.btnCorreo.off;
    },
    cerrarTeclado: function(){
        log.info("--> Registration/cerrarTeclado");
        teclado.cerrarTeclado();
        this.tecladoVisible = false;
        this.tecladoNumericoVisible = false;
    },
    mostrarTeclado: function(opcion) {
      log.info("--> Registration/mostrarTeclado");
      if (this.tecladoVisible || this.tecladoNumericoVisible) {
        if (this.inputSeleccionado) {
          if (
            (this.nombreDeInput == "documento" ||
              this.nombreDeInput == "fechaNacimiento") &&
            !this.tecladoNumericoVisible
          ) {
            teclado.cerrarTeclado();
            teclado.mostrarTeclado(true);
            this.tecladoNumericoVisible = true;
            this.tecladoVisible = false;
          } else if (
            !(
              this.nombreDeInput == "documento" ||
              this.nombreDeInput == "fechaNacimiento"
            ) &&
            this.tecladoNumericoVisible
          ) {
            teclado.cerrarTeclado();
            teclado.mostrarTeclado(false);
            this.tecladoNumericoVisible = false;
            this.tecladoVisible = true;
          }
          this.inputSeleccionado = false;
          return;
        } else {
          teclado.cerrarTeclado();
          this.tecladoVisible = false;
          this.tecladoNumericoVisible = false;
        }
      } else {
        teclado.mostrarTeclado(
          this.nombreDeInput == "documento" ||
            this.nombreDeInput == "fechaNacimiento"
        );
        this.tecladoVisible = !(
          this.nombreDeInput == "documento" ||
          this.nombreDeInput == "fechaNacimiento"
        );
        this.tecladoNumericoVisible =
          this.nombreDeInput == "documento" ||
          this.nombreDeInput == "fechaNacimiento";
      }
      this.inputSeleccionado = false;
    },
    foco: function(input) {
      this.nombreDeInput = input.target.id;
      this.inputSeleccionado = true;
    },
    onChange: function(input) {
      this.inputs[this.nombreDeInput] = input;
    },
    onInputChange: function(input) {
      this.inputs[input.target.id] = input.target.value;
    },
    validarCorreo: function() {
      log.info("--> Registration/validarCorreo");
      if (this.correo.length > 4) {
        let plan = correo.value.split("@");
        let arroba = correo.value.indexOf("@");
        if (plan.length > 1) {
          let punto = plan[1].indexOf(".");
          return arroba > 0 && punto > 0;
        }
      }
      return false;
    },
    validar: function() {
      log.info("--> Registration/validar");
      let fecha = fechaNacimiento.value;
      let banderaFecha = fecha.length == 10;
      let bandera = this.validarCorreo() && documento.value.length > 4;
      return banderaFecha && bandera;
    },
    registrar: function(option) {
      log.info("--> Registration/registrar");
      if (this.validar()) {
        log.info("--> Registration/registrar/ Se valido todos los datos en correcto");
        this.isLoad = true;
        this.cerrarTeclado();
        let user = new NuevoUsuario(
          this.tipoDocumento,
          documento.value,
          correo.value,
          fechaNacimiento.value
        );
        this.service
          .conectar(user, "POST", "registrar")
          .then(valor => {
            log.info("[Registro] - Realizo correctamente el registro");
            Router.push({
              name: "validacion",
              params: {
                mensaje: valor.resultado.message,
                imagen: true,
                page: "registration"
              }
            });
          })
          .catch(valor => {
            if (valor.flag) {
              this.service.header();
              this.registrar(option);
            } else {
              log.info(
                "[Registro] - Ocurrió un error al intentar registrar el usuario"
              );
              console.log(valor);
              this.validacionIngreso = false;
              this.mostrarError(valor.mensaje);
              this.url = "";
              this.isLoad = false;
            }
          });
        setTimeout(function() {
          log.info("--> Registration/registrar/setTimeOut");
          this.validacionIngreso = true;
        }, 2000);
      } else {
        log.info("--> Registration/registrar/ No se validaron los datos del registro");
        this.isLoad = false;
        this.mostrarError("Verificar los datos ingresados");
        setTimeout(function() {
          this.validacionIngreso = true;
        }, 2000);
      }
    },
    mostrarError: function(error) {
      this.alerta_error = true;
      this.textoAviso = error;
    },
    cerrarAviso: function() {
      this.alerta_error = false;
    },
    hacerDocumento(doc){
      log.info("--> Registration/hacerDocumento");
      let valor = doc;
      return valor;
    }
  },
  created(){
    this.enfocar();
  },
  mounted() {
    document.addEventListener("click", e => {
      if (!this.clickSelect) {
        this.stopSelect();
      } else {
        this.clickSelect = false;
      }

      if (e.path[0].tagName == "LI") {
        if (e.path[1].id == "tipo") {
          switch (e.path[0].innerHTML) {
            case "L.C":
              this.tipoDocumento = 1;
              break;
            case "DNI":
              this.tipoDocumento = 2;
              break;
            case "L.E":
              this.tipoDocumento = 3;
              break;
          }
        }

        this.llenarSelect(e.path[1].id, e.path[0].innerHTML, e.path[2].id);
      }
      this.enfocar();
    });

    document.addEventListener("keypress", e => {
      this.$refs.cabecera.refresh();
    });
  }
};
</script>
<style>
@import url("../css/principal.css");
@import url("../css/principal.css");
.registro p {
  font-size: 18px;
  color: white;
  font-family: "Roboto", sans-serif;
}

input[type="date"]::before {
  content: attr(placeholder);
  color: white;
  text-align: center;
}

input[type="date"]::-webkit-inner-spin-button,
input[type="date"]::-webkit-outer-spin-button {
  -webkit-appearance: none;
  margin: 0;
}

input[type="number"] {
  -moz-appearance: textfield;
}

.registrador {
  font-weight: 900;
  cursor: pointer;
}
</style>
