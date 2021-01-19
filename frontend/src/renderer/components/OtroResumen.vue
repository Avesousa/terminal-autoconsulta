<template>
    <div id="cuerpo">
        <Cabecera :teclado="true" :menu="true" @mostrarTeclado="mostrarTeclado" :pageBack="pageBack"/>
        <div class="container">
            <div class="row">
                <div class="col-12 text-center">
                    <h3>¿Querés obtener tu resumen de cuenta?</h3>
                </div>
            </div>

            <div class="container mt-5">
                <!-- Contenedor de login -->
                <div id="login" class="row seccion text-center">
                    <Login ref="login" :mostradorLogin="true" :adhesionCorreo="true"/>
                </div>

            </div>
        </div>
  </div>
    
</template>
<script>
    import Cabecera from './include/Cabecera';
    import Login from './complementos/Login';
    const resumen = require('@/assets/btn_resumen.png');
    const resumenOn = require('@/assets/btn_resumenOn.png');
    const eresumen = require('@/assets/btn_eresumen.png');
    const eresumenOn = require('@/assets/btn_eresumenOn.png');

    export default {
        name: 'resumen',
        components: {
            Cabecera,
            Login
        },
        data: function(){
            return{
                loginBoolean: false,
                correoBoolean: false,
                pageBack: "/principal",
                btnLogin:{
                    off: resumen,
                    on: resumenOn,
                    show: resumen
                },
                btnCorreo:{
                    off: eresumen,
                    on: eresumenOn,
                    show: eresumen
                }
            }
        },
        methods: {
            mostrar: function(tipo){
                if(tipo == "login"){
                    this.loginBoolean = !this.loginBoolean
                    this.correoBoolean = false
                } else {
                    this.loginBoolean = false
                    this.correoBoolean = !this.correoBoolean
                }
                this.cambiarImagenes();
                //vaciarInput();
            },

            cambiarImagenes: function(){
                this.btnLogin.show = this.loginBoolean ? this.btnLogin.on : this.btnLogin.off;
                this.btnCorreo.show = this.correoBoolean ? this.btnCorreo.on : this.btnCorreo.off;
            },

            mostrarTeclado: function(opcion){
                this.$refs.login.mostrarTeclado(opcion);
            },
        }
    }
</script>
<style>
    @import url('./css/principal.css');

    h3{
        font-family: 'Roboto', sans-serif;
        font-size: 6vh;
        font-weight: 700;
        color: white;
        letter-spacing: -3px;

    }

    p > img{
        width: 10%;
    }

    .seccion{
        margin-top: 2%;
    }

   
</style>