<template>
    <div class="container-fluid">
        <div v-if="normal">
            <div class="error">
                <div class="col-12">
                    <h4>{{mensaje}}</h4>
                </div>
            </div>
        </div>
        <div v-else>
            <div v-if="positivo" class="container-fluid success">
                <div class="col-12 aviso aviso-true">
                    <h4>{{mensaje}}</h4>
                </div>
            </div>
            <div v-else class="container-fluid error">
                <div class="col-12 aviso">
                    <h4>{{mensaje}}</h4>
                </div>
            </div>
        </div>

    </div>
    
    
</template>

<script>
    export default {
        name: "Autentificacion",
        props: {
            positivo: Boolean,
            normal: {
                type: Boolean,
                default: false
            }
        },
        data:function(){
            return{
                mensaje: "",
            }
        },
        methods:{
            setMensaje:function(valor){
                console.log("--> Autentificacion/setMensaje");
                this.mensaje = valor;
            }
        }
    
    }
    
</script>

<style>

.error{
    text-align: center;
    border-radius: 10px;
    color: brown;
    font-size: 16px;
    font-family: 'Roboto', sans-serif;
    font-weight: 100;
}

.error h4{
    color: brown;
    font-size: 20px;
    margin-bottom: 0;
}

.error > div{
    padding-top: 18px;
}

.success{
    text-align: center;
    border-radius: 10px;
    color: rgb(117, 255, 170);
    font-size: 16px;
    font-family: 'Roboto', sans-serif;
    font-weight: 100;
}

</style>