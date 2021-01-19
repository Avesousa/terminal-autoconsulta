<template>
    <div>
        <div class="bg_alert justify-content-center">
            <div class="titulo_alerta">
                <div class="row">
                    <div class="col-10">
                        <h3 class="izq">Aviso</h3>
                    </div>
                    <div class="col-2">
                        <h3 class="der"><a @click="cerrarAviso()">x</a></h3>
                    </div>
                </div>
            </div>
            <div class="cuerpo_alerta">
                <img src="~@/assets/ico/exclamation.png" alt="">
                <p>{{text}}</p>
                <div class="row">
                    <div class="btn_alerta centrar divBoton centrado margin-top-0">
                        <input type="button" @click="cerrarAviso()" value="Aceptar">
                    </div>
                </div>
            </div>
        </div>
    </div>

</template>

<script>
export default {

    name: 'alerta',
    props:{
         text: {
             type: String,
             default:"Ha ocurrido un error"
        }
    },
    methods:{
         cerrarAviso: function(){
                this.$emit("cerrarAviso");
            }
    }
    
}
</script>

<style>

    .bg_alert{
        background-color: rgba(0,0,0,0.8);
        height: 100vh;
        width: 100%;
        z-index: 200;
        position: fixed;
        text-align: center;
    }

    .cuerpo_alerta, .titulo_alerta{
        width: 25%;
    }

    .titulo_alerta{
        position: relative;
        background-color: #00519A;
        margin-top: 30vh;
        padding-top: .8%;
        padding-bottom: .4%;
        border-radius: 15px 15px 0px 0px;
        padding-left: .4%;
    }

    .titulo_alerta h3, .titulo_alerta h3 a{
        font-size: 18px;
        font-family: 'Roboto','sans-serif';
        font-weight: lighter;
        display: flex;
        letter-spacing: 0;
    }

    .titulo_alerta h3 a{
        font-size: 20px;
    }

    .titulo_alerta h3 a:hover{
        text-shadow: 1px 1px 50px rgba(0,0,0,0.8);
    }

    .titulo_alerta div h3.der{
        text-align: left;
    }

    .titulo_alerta h3.izq{
        text-align: right;
        padding-left: 5%;
    }

    .titulo_alerta, .cuerpo_alerta{
        margin-left: 38%;
    }

    .cuerpo_alerta{
        background-color: rgb(255,255,255);
        padding: .5%;
        padding-top: .6%;
        border-radius: 0px 0px 15px 15px;
    }

    .cuerpo_alerta img{
        width: 18%;
        margin-bottom: .6%;
    }

    .cuerpo_alerta p{
        font-size: 16px;
        font-weight: 700;
        color: #00519A;
        margin-bottom: .3%;
    }

    .btn_alerta{
        width: 100%;
    }

    .cuerpo_alerta .btn_alerta input[type="button"]{
        width: 70%;
        font-size: 16px;
        border-radius: 10px;
        background:#00519A;
    }

    .cuerpo_alerta .btn_alerta input[type="button"]:hover{
        background: rgb(50, 120, 183);
    }

</style>