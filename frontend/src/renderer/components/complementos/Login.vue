<template>
  <div class="container-fluid">
    <div>
      <!-- SELECT DOCUMENTO - DOCUMENTO -->
      <div v-if="mostradorLogin" class="col-12">
        <div class="row centrado margin-top-0">
          <div class="col-2 izquierda">
            <div class="row">
              <div
                class="select-custom-div col-12"
                :class="{'select-custom-div-push':selectOn['selectDocumentoOn']}"
                v-on:click="runSelect('selectDocumentoOn')"
              >
                <i class="ico_left ico_dni"></i>
                <div ref="selectDocumento">DNI</div>
                <i class="ico_right ico_flecha"></i>
              </div>
            </div>
            <div
              v-if="selectOn['selectDocumentoOn']"
              ref="optionDocumento"
              class="row option-custom-div"
            >
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
      <!-- SELECT DOCUMENTO CORREO - DOCUMENTO CORREO -->
      <div v-if="!mostradorLogin" class="col-12">
        <div class="row">
          <div class="col-2 izquierda">
            <div class="row">
              <div
                class="select-custom-div col-12"
                :class="{'select-custom-div-push':selectOn['selectDocumentoCorreoOn']}"
                v-on:click="runSelect('selectDocumentoCorreoOn')"
              >
                <i class="ico_left ico_dni"></i>
                <div ref="selectDocumentoCorreo">DNI</div>
                <i class="ico_right ico_flecha"></i>
              </div>
            </div>
            <div
              v-if="selectOn['selectDocumentoCorreoOn']"
              ref="optionDocumentoCorreo"
              class="row option-custom-div"
            >
              <div class="col-12">
                <div id="selectDocumentoCorreo">
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
              name="documentoCorreo"
              id="documentoCorreo"
              ref="documentoCorreo"
              @click="mostrarTeclado(false)"
              v-model="documentoCorreo"
              @focus="foco"
            />
          </div>
        </div>
      </div>
      <!-- SELECT GENERO - USUARIO -->
      <div v-if="mostradorLogin" class="col-12">
        <div class="row centrado margin-top-0">
          <div class="col-3 izquierda">
            <div class="row">
              <div
                class="select-custom-div col-12"
                :class="{'select-custom-div-push':selectOn['selectGenderOn']}"
                v-on:click="runSelect('selectGenderOn')"
              >
                <i class="ico_left ico_gender"></i>
                <div ref="selectGender">Género</div>
                <i class="ico_right ico_flecha"></i>
              </div>
            </div>
            <div v-if="selectOn['selectGenderOn']" ref="optionGender" class="row option-custom-div">
              <div class="col-12">
                <div id="selectGender">
                  <ul id="genero">
                    <li>Femenino</li>
                    <li>Masculino</li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
          <div class="col-4 derecha">
            <i class="ico_left icoPadding ico_usuario"></i>
            <input
              type="text"
              name="user"
              id="user"
              v-model="user"
              ref="user"
              placeholder="Usuario"
              @focus="foco"
              @click="mostrarTeclado(true)"
            />
          </div>
        </div>
      </div>
      <!-- SELECT GENERO CORREO -->
      <div v-if="!mostradorLogin" class="col-12">
        <div class="row">
          <div class="col-7 centrar centrado margin-top-0">
            <div class="row">
              <div
                class="select-custom-div col-12"
                :class="{'select-custom-div-push':selectOn['selectGenderCorreoOn']}"
                v-on:click="runSelect('selectGenderCorreoOn')"
              >
                <i class="ico_left ico_gender"></i>
                <div ref="selectGenderCorreo">Género</div>
                <i class="ico_right ico_flecha"></i>
              </div>
            </div>
            <div
              v-if="selectOn['selectGenderCorreoOn']"
              ref="optionGenderCorreo"
              class="row option-custom-div"
            >
              <div class="col-12">
                <div id="selectGenderCorreo">
                  <ul id="genero">
                    <li>Femenino</li>
                    <li>Masculino</li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- PASSWORD -->
      <div v-if="mostradorLogin" class="col-12">
        <div class="row">
          <div class="col-7 centrar centrado margin-top-0">
            <i class="ico_left ico_pass"></i>
            <input
              :type="banderaInput ? 'text': 'password'"
              name="password"
              id="password"
              placeholder="Contraseña"
              v-model="password"
              @focus="foco"
              ref="password"
              @click="mostrarTeclado(true)"
            />
            <i class="ico_right ico_ojo" @click="cambiarAtext()"></i>
          </div>
        </div>
      </div>
      <!-- ADHESION DE CORREO -->
      <div v-if="adhesionCorreo" class="col-12">
        <div class="row">
          <div class="col-7 centrar centrado">
            <div class="adherirCorreo">
              <label class="check">
                <span class="adherirEResumen">¿Querés adherirte a Resumen Electrónico?</span>
                <input
                  type="checkbox"
                  name="adhererirCorreo"
                  id="adhererirCorreo"
                  :value="banderaAdherirCorreo"
                  checked
                />
                <i @click="adherirCorreo()"></i>
              </label>
            </div>
          </div>
        </div>
      </div>
      <!-- MENSAJE DE CARGA -->
      <div v-if="isLoad" class="col-12 load load-w">
        <div class="row">
          <img src="~@/assets/load.gif" alt="load carrefour" />
          <br />
          <span>Espere por favor...</span>
        </div>
      </div>
      <!-- BOTON DE CARGA -->
      <div v-else class="col-12">
        <div class="row">
          <div class="col-7 centrar centrado divBoton margin-top-0">
            <input
              v-if="traermeCliente"
              @click="getCliente('cliente')"
              type="button"
              value="Ingresar"
            />
            <input
              v-if="traermeTokenLink"
              @click="getCliente('token')"
              type="button"
              value="Ingresar"
            />
            <input v-if="traermeCorreo" @click="getCliente('correo')" type="button" value="Enviar" />
          </div>
        </div>
      </div>
      <!-- SECTOR DE PREGUNTA PARA REGISTRO -->
      <div v-if="registrador" class="col-12">
        <div class="row">
          <div class="col-12 centrar centrado registro">
            <p>
              ¿No tenés usuario?
              <router-link :to="{name:'registration', params:{ventanaLogin:true}}">
                <span class="registrador">Registrate</span>
              </router-link>
            </p>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import Teclado from "../include/Teclado";
import SolicitudServicios from "../../solicitudes/SolicitudServicios";
import Usuario from "../../modelos/Usuario";
import Token from "../../modelos/Token";
import Cliente from "../../modelos/Cliente";
import Autentificacion from "../../solicitudes/Autentificacion";
import Log from "../../Log/Log";
import Link from "../../modelos/Link";
import { TheMask } from "vue-the-mask";
import Router from "../../router/index";

const log = Log.instance().log;
const teclado = Teclado.instanciar();

export default {
  name: "login",
  components: {
    SolicitudServicios,
    Autentificacion,
    Token,
    Cliente,
    Log,
    Link,
    Teclado,
    TheMask
  },
  props: {
    mostradorLogin: Boolean,
    adhesionCorreo: Boolean,
    traermeCliente: Boolean,
    traermeTokenLink: Boolean,
    traermeCorreo: Boolean,
    registrador: Boolean
  },
  data: function() {
    return {
      banderaInput: false,
      banderaAdherirCorreo: "si",
      nombreDeInput: "",
      mensaje: "",
      validacionIngreso: true,
      tecladoNumericoVisible: false,
      tecladoVisible: false,
      inputSeleccionado: false,
      isLoad: false,
      selectOn: {
        selectDocumentoOn: false,
        selectGenderOn: false,
        selectDocumentoCorreoOn: false,
        selectDocumentoRegistoOn: false,
        selectGenderCorreoOn: false
      },
      clickSelect: false,
      url: "",
      fecha: "",
      documento: "",
      documentoCorreo: "",
      password: "",
      user: "",
      documentoRegistro: "",
      correo: "",
      tipoDocumento: "2",
      gender: "G",
      service: SolicitudServicios.instance(),
      token: Token.instanciar(),
      link: Link.instanciar(),
      mensajeError: "Verificar los datos ingresados"
    };
  },
  created() {
    this.enfocar(false);
  },
  methods: {
    cambiarImagenes: function() {
      this.btnLogin.show = this.loginBoolean
        ? this.btnLogin.on
        : this.btnLogin.off;
      this.btnCorreo.show = this.correoBoolean
        ? this.btnCorreo.on
        : this.btnCorreo.off;
    },
    runSelect: function(select) {
      this.stopSelect();
      this.selectOn[select] = !this.selectOn[select];
      this.clickSelect = this.selectOn[select];
    },
    llenarSelect(tipo, val, ref) {
      if (tipo == "tipo" || tipo == "genero") {
        this.$refs[ref].innerHTML = val;
      }
      this.enfocar(false);
    },
    stopSelect: function() {
      this.selectOn["selectDocumentoOn"] = false;
      this.selectOn["selectGenderOn"] = false;
      this.selectOn["selectDocumentoCorreoOn"] = false;
      this.selectOn["selectDocumentoRegistoOn"] = false;
      this.selectOn["selectGenderCorreoOn"] = false;
    },
    mostrarTeclado: function(opcion) {
      log.info("--> Login/mostrarTeclado");
      if (this.tecladoVisible || this.tecladoNumericoVisible) {
        if (this.inputSeleccionado) {
          this.inputSeleccionado = false;
          if (
            (this.nombreDeInput == "documento" ||
              this.nombreDeInput == "documentoCorreo") &&
            !this.tecladoNumericoVisible
          ) {
            teclado.cerrarTeclado();
            teclado.mostrarTeclado(true);
            this.tecladoNumericoVisible = true;
            this.tecladoVisible = false;
          } else if (
            !(
              this.nombreDeInput == "documento" ||
              this.nombreDeInput == "documentoCorreo"
            ) &&
            this.tecladoNumericoVisible
          ) {
            teclado.cerrarTeclado();
            teclado.mostrarTeclado(false);
            this.tecladoNumericoVisible = false;
            this.tecladoVisible = true;
          }
          return;
        } else {
          if (!opcion) {
            this.cerrarTeclado();
          }
        }
      } else {
        teclado.mostrarTeclado(
          this.nombreDeInput == "documento" ||
            this.nombreDeInput == "documentoCorreo"
        );
        this.tecladoVisible = !(
          this.nombreDeInput == "documento" ||
          this.nombreDeInput == "documentoCorreo"
        );
        this.tecladoNumericoVisible =
          this.nombreDeInput == "documento" ||
          this.nombreDeInput == "documentoCorreo";
      }
      this.inputSeleccionado = false;
    },
    cerrarTeclado: function() {
      log.info("--> Login/cerrarTeclado");
      teclado.cerrarTeclado();
      this.tecladoVisible = false;
      this.tecladoNumericoVisible = false;
    },
    validar: function() {
      log.info("--> Login/validar");
      if (this.mostradorLogin) {
        log.info("--> Login/validar/ es mostrador");
        if (!(documento.value != "")) {
          this.mensajeError = "Debe ingresar un documento";
          return false;
        } else if (!(user.value != "")) {
          this.mensajeError = "Debe ingresar un usuario";
          return false;
        } else if (this.gender == "G") {
          this.mensajeError = "Seleccione el género";
          return false;
        } else if (!(password.value != "")) {
          this.mensajeError = "Debe ingresar una contraseña";
          return false;
        }
      } else {
        log.info("--> Login/validar/ no es mostrador");
        if (!(documentoCorreo.value != "")) {
          this.mensajeError = "Debe ingresar un documento válido";
          return false;
        } else if (this.gender == "G") {
          this.mensajeError = "Seleccione el género";
          return false;
        }
      }
      return true;
    },
    cambiarAtext: function() {
      this.banderaInput = !this.banderaInput;
      this.enfocar(false);
    },
    enfocar(flag, ventana) {
      try {
        if (
          this.nombreDeInput != "documentoCorreo" &&
          this.nombreDeInput != "" &&
          this.mostradorLogin
        ) {
          this.$refs[this.nombreDeInput].focus();
        } else {
          this.$refs[
            this.mostradorLogin ? "documento" : "documentoCorreo"
          ].focus();
          this.mostrarTeclado(false);
        }
      } catch (error) {}
    },
    adherirCorreo: function() {
      this.banderaAdherirCorreo =
        this.banderaAdherirCorreo == "si" ? "no" : "si";
    },
    foco: function(input) {
      this.nombreDeInput = input.target.id;
      this.inputSeleccionado = true;
    },
    getCliente: function(option) {
      this.validacionIngreso = true;
      log.info("--> Login/getCliente");
      if (this.validar()) {
        log.info(
          "--> Login/getCliente/ validado que los datos están en condiciones"
        );
        this.isLoad = true;
        let userNew = new Usuario(
          this.tipoDocumento,
          this.hacerDocumento(
            option == "correo" ? documentoCorreo.value : documento.value
          ),
          option == "correo" ? "" : user.value,
          option == "correo" ? "" : password.value,
          option,
          this.gender,
          this.adhesionCorreo ? this.banderaAdherirCorreo : false
        );
        this.service
          .conectar(userNew, "POST", "getClienteAll")
          .then(valor => {
            log.info(
              "[Login] - Termina correctamente la conexión, arrojando resultado"
            );
            var cliente = Cliente.instanciar();
            cliente.setCliente(valor.resultado);
            if (option == "correo") this.url = option;
            else this.url = cliente.getUrl();
            this.terminarLogin(option);
            log.info("[Login] - Termina de realizar la acción");
          })
          .catch(valor => {
            if (valor.flag) {
              log.error(
                "[Login] - Se ha intentado conectar al webservice con un token vencido."
              );
              this.service.header();
              log.info("[Login] - Volverá a intentar conectarse");
              this.getCliente(option);
            } else {
              log.error(
                "[Login] - Ocurrió un error al intentar validar los datos"
              );
              log.error(
                "[Login] - Error ocurrido (" +
                  valor.error +
                  "): " +
                  valor.mensaje
              );
              this.mostrarError(valor.mensaje);
              this.url = "";
            }
            this.isLoad = false;
          });
      } else {
        log.info(
          "--> [Login] Los datos que se ingresaron no están aptos para la consulta"
        );
        this.validacionIngreso = false;
        this.mostrarError(this.mensajeError);
      }
    },
    mostrarError(error) {
      this.$emit("mostrarError", error);
    },
    terminarLogin(option) {
      if (this.traermeTokenLink) {
        log.info(
          "[Login=>Terminar] Ha comenzado a realizar la conexión a la url de mco"
        );
        Router.push({
          name: "navegadorMco",
          params: { link: this.url }
        });
      } else if (option == "correo") {
        log.info("[Login=>Terminar] Confirmación del correo");
        Router.push({
          name: "confirmacion"
        });
      } else {
        log.info("[Login=>Terminar] Listas de resumenes");
        Router.push({
          name: "listaresumen",
          params: { tipo: "resumen" }
        });
      }
      this.cerrarTeclado();
    },
    hacerDocumento(doc) {
      let valor = doc.replace(/\./g, "");
      return valor;
    },
    formatear: function(num) {
      num += "";
      var splitStr = num.split(".");
      var splitLeft = splitStr[0];
      var splitRight = splitStr.length > 1 ? this.sepDecimal + splitStr[1] : "";
      var regx = /(\d+)(\d{3})/;
      while (regx.test(splitLeft)) {
        splitLeft = splitLeft.replace(regx, "$1" + "." + "$2");
      }
      return splitLeft + splitRight;
    },
    destroyed() {
      console.log("8 - destroyed");
    }
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
        } else if (e.path[1].id == "genero")
          this.gender = e.path[0].innerHTML.charAt(0);

        this.llenarSelect(e.path[1].id, e.path[0].innerHTML, e.path[2].id);
      }
      this.enfocar(false);
    });
  }
};
</script>
<style>
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

.adherirCorreo {
  background-color: rgba(255, 255, 255, 0.3);
  padding: 10px;
  border-radius: 10px;
}

.adherirEResumen {
  font-size: 18px;
}
</style>