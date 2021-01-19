<template>
    <div id="cuerpo">
        <video id="publicidad" autoplay loop :src="video"></video>
    </div>
</template>

<script>
    import Router from '../../router/index';
    import Properties from '../../modelos/Properties';
    import Log from '../../Log/Log';
    const log = Log.instance().log;
    const properties = Properties.instanciar();

    export default {
        name: 'inactividad',
        data(){
            return{
                video: properties.getVideoPromocion()
            }
        },
        methods:{
            pausarPromocion: function(){
                Router.push("principal");
            }
        },
        mounted(){
            document.querySelector("#publicidad").addEventListener("click",(e)=>{
                this.pausarPromocion();
                log.info("El path del vídeo => " + properties.getVideoPromocion());
            })
        }
    }
</script>

<style>

    #publicidad{
        width: 100%;
    }

</style>