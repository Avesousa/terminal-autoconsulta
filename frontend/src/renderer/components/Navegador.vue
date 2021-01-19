<template>
    <div id="cuerpo">
        <Cabecera :teclado="true" :menu="true" :tecladoVisible="true" :haveTime="false" :destruirTeclado="true" @mostrarTeclado="mostrarTeclado"/>
        <div v-if="isOk" class="navegation">
            <div v-if="isLoad" class="container">
                <div class="col-12 load load-xl load-b mt-5">
                    <div class="row">
                    <!---->
                        <img src="~@/assets/load.gif" alt="load carrefour"><br>
                        <span>Cargando...</span>
                    </div>
                </div>
            </div>
            <WebView class="visualizador" :src="url" partition="persist:proxy-bsf"/>
        </div>
        <div v-else>
            <div class="container mt-5">
                <div class="row mt-5">
                    <div class="col-12 text-center aviso">
                        <h4>{{mensajeDeError}}</h4>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
    import Cabecera from './include/Cabecera';
    import Teclado from './include/Teclado';
    import Router from '../router/index';
    import Log from '../Log/Log';
    import Properties from '../modelos/Properties';
    import Electron from 'electron';
    const properties = Properties.instanciar();
    const log = Log.instance().log;
    const teclado = Teclado.instanciar();

    export default {
        
        name: 'navegador',
        components:{
            Cabecera,
            Log,
            Electron,
            Teclado
        },
        data: function(){
            return{
                inputs:{},
                nombreDeInput: "",
                teclado: false, 
                pageBack: "/principal",
                url: Router.currentRoute.params.link,
                isLoad: true,
                isOk: true,
                isWebOk: true,
                mensajeDeError: "Ha ocurrido un error al intentar cargar..."
            }
        },
        methods:{
            mostrarTeclado:function(){
                teclado.mostrarTeclado(false);
            },
            
            foco: function(input){
                this.nombreDeInput = input.target.id;
            },
            
            onChange: function(input) {
                this.inputs[this.nombreDeInput] = input;
            },
            
            onInputChange: function(input) {
                this.inputs[input.target.id] = input.target.value;
            },

            changeView: function(){
                log.info("La url ha cambiado ");
            }


        },
        mounted(){
            log.info("--> Navegador/mounted");
            const body = document.getElementById("cuerpo");
            const webview = document.querySelector('webview');
            webview.addEventListener('dom-ready', () => {
                this.isLoad = false;
                log.info("La URL abierta es: " + this.url);
            });
            webview.addEventListener('did-fail-load', (e) => {
                log.error("Ha ocurrido un error a la hora de carga la web: " + e.validatedURL);
                log.error("Error ocurrido [" + e.errorCode + "]: " + e.errorDescription );
                this.isOk = false;
                setTimeout(() => {
                    Router.push("principal")},3000);
            });

            webview.addEventListener('did-navigate',async (e) => {
                if(properties.getIsEnable()){
                    if(e.url == properties.getMcoUrlPrincipal()){
                        Router.push("principal");
                    }
                }else{
                    this.isOk = false;
                    this.mensajeDeError = properties.getError();
                    log.error(properties.getError());
                }
                
            });

        }
    }
</script>
<style>
@import url('./css/principal.css');

.visualizador{
    width: 100%;
    height: 83vh;
    margin: 0 auto;
}

.navegation{
    background-color: white;
}


</style>