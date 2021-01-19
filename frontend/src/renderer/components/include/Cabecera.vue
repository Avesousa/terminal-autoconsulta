<template>
  <div id="cabecera" class="container-fluid cabecera">
    <div v-if="menu" class="row justify-content-md-center">
      <div class="col-4">
        <p class="izq" @click="volver()">
          <img src="~@/assets/ico/back.png" alt="volver" />
        </p>
      </div>
      <div class="col-4">
        <img class="logo logoPeq" src="~@/assets/logo.png" alt="Logo de carrefour" />
      </div>
      <div class="col-4">
        <p class="der" @click="irPrincipal()">
          <img src="~@/assets/ico/home.png" alt="casa" />
        </p>
        <p
          class="teclado"
          v-if="teclado && (tecladoVisibleData || tecladoVisible)"
          @click="mostrarTeclado(false)"
        >
          <img src="~@/assets/ico/keyboard.png" alt="casa" />
        </p>
      </div>
    </div>
    <div v-else class="row justify-content-md-center">
      <div class="col-12">
        <img class="logo" src="~@/assets/logo.png" alt="Logo de carrefour" />
      </div>
    </div>
  </div>
</template>
<script>
import Router from "../../router/index";
import Properties from "../../modelos/Properties";
import Teclado from "./Teclado";
import Log from '../../Log/Log';
const properties = Properties.instanciar();
const teclado = Teclado.instanciar();

export default {
  name: "Cabecera",
  components: {
    Router,
    Teclado
  },
  props: {
    teclado: Boolean,
    menu: Boolean,
    tecladoVisible: Boolean,
    haveTime: {
      type: Boolean,
      default: true
    },
    isSummaryPdf: {
      type: Boolean,
      default: false
    },
    isConfirmMail: {
      type: Boolean,
      default: false
    },
    pageBack: {
      default: "/principal"
    }
  },
  data: function() {
    return {
      tecladoVisibleData: false,
      time: 0,
      banderaTime: false
    };
  },
  methods: {
    mostrarTeclado: function(opcion) {
      this.$emit("mostrarTeclado", opcion);
    },
    volver: function() {
      log.info("--> Cabecera/volver");
      teclado.cerrarTeclado();
      if (!this.isSummaryPdf && !this.isConfirmMail) {
        log.info("--> Entro a volver");
        Router.push(this.pageBack);
      } else if (this.isSummaryPdf) {
        log.info("--> Entro a volver siendo summaryPdf");
        Router.push({
          name: "listaresumen",
          params: { tipo: "resumen" }
        });
      } else if (this.isConfirmMail) {
        log.info("--> Entro a devolver siendo confirmMail");
        Router.push("iniciarResumen");
      }
    },
    irPrincipal: function() {
      teclado.cerrarTeclado();
      Router.push("/principal");
    },
    cerrarTeclado: function() {
      this.$emit("cerrarTeclado");
    },
    visibilidadDeTeclado: function(value) {
      this.tecladoVisibleData = value;
    },
    refresh: function() {
      let tiempoDeEspera = properties.getTimer();
      clearTimeout(this.time);
      if (properties.getIsEnable()) {
        if (this.haveTime) {
          this.time = setTimeout(() => {
            if (this.$route.path != "/inactividad" && this.$route.path != "/") {
              log.info("[Cabecera => Inactividad] - Ruta deshabilitada: " + this.$route.path);
              teclado.cerrarTeclado();
              Router.push("/principal").catch(error => {
                log.error("[Cabecera => Inactividad] - No puede volver a principal por: ");
                log.error(error);
              });
            }
          }, tiempoDeEspera);
        }
      } else {
        log.error(properties.getError());
      }
    }
  },
  created() {
    this.refresh();
  },
  mounted() {
    document.addEventListener("click", this.refresh);
  }
};
</script>
<style>
.cabecera {
  padding: 15px;
  color: white;
  font-weight: 900;
}

.cabecera p {
  cursor: pointer;
}

.cabecera p.der {
  text-align: center;
}

.cabecera p.der-izq {
  text-align: left;
}

.cabecera p.derFalse {
  text-align: right;
  padding-right: 10%;
  margin-top: 8%;
}

p.der,
p.izq,
p.teclado {
  position: absolute;
  display: inline;
}

p.teclado {
  margin-top: 3%;
}

p.der,
p.izq {
  margin-top: 5%;
}

p.der,
p.teclado {
  right: 0;
}

p.izq {
  left: 0;
  margin-left: 15%;
}

p.teclado {
  margin-right: calc((105px * 0.6) + 15%);
}

p.der {
  margin-right: 15%;
}

p.der img,
p.izq img {
  width: 50%;
}

p.teclado img {
  width: 75%;
}

.cabecera p.izq {
  text-align: center;
}

.logo {
  height: auto;
  width: 15%;
  display: flex;
  margin: 0 auto;
}

.logoPeq {
  width: 60%;
}
</style>