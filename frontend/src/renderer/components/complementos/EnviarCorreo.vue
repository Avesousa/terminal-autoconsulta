<template>
    <div id="cuerpo">
        <Cabecera :menu="isError" ref="cabecera" :pageBack="pageBack"/>
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

import Cabecera from '../include/Cabecera';
import Cliente from '../../modelos/Cliente';
import SolicitudServicios from '../../solicitudes/SolicitudServicios';
import Log from '../../Log/Log';
import Router from '../../router/index';
const log = Log.instance().log;

export default {
    name: "EnviarCorreo",
    components:{
        Cabecera,
        Cliente,
        Log,
        SolicitudServicios,
        Router
    },
    data(){
        return{
            service: SolicitudServicios.instance(),  
            pageBack: {name:"listaresumen", params: { tipo: 'correo' }},
            cliente: Cliente.instanciar(),
            isLoad: true,
            isError: false,
            card: Router.currentRoute.params.card,
            date: Router.currentRoute.params.date
        }
    },
    methods:{
        enviarCorreo: function(){
            log.info("--> EnviarCorreo/enviarCorreo");
            this.isLoad = true;
            this.service.conectar({
                    remitente:this.cliente.getCorreo(),
                    fecha:this.date,
                    tarjeta:this.card,
                    fechaNacimiento:this.cliente.getFechaNacimiento(),
                    doc: this.cliente.getDocumento(),
                    cliente: this.cliente.getCliente()
                },'GET','enviar').
                then(valor=>{
                    Router.push({
                        name:"validacion",
                        params: {
                            mensaje:valor.resultado,
                            imagen: false,
                            page: 'enviarCorreo'
                        }
                    });
                    log.info("--> EnviarCorreo/enviarCorreo/ Ha enviado el correo sin error captado");
                }).catch(valor=>{
                    log.info("--> EnviarCorreo/enviarCorreo/ No se ha podido enviar el correo");
                    if(valor.flag){
                        log.info("--> EnviarCorreo/enviarCorreo/ Vuelve a reintentar enviar el correo");
                        this.service.header();
                        this.enviarCorreo(fecha,tarjeta);
                    }else{
                        log.info("--> EnviarCorreo/enviarCorreo/ Ocurrió un error al intentar enviar correo");
                        this.isError = true;
                        this.isLoad = false;
                        this.validacionIngreso = false;
                        this.mensajeError = valor.mensaje;
                    }
                });
            setTimeout(function(){
                this.validacionIngreso = true;                    
            },1000);
        }
    },
    created: function(){
        this.enviarCorreo();
    },
    
    
}
</script>
<style>

</style>