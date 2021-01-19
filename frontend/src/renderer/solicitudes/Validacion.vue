<template>
    <div id="cuerpo" class="cuerpoValidacion">
        <Cabecera :teclado="false" :menu="true" :pageBack="pageBack"/>
        <div class="container mt-5 pt-5">
            <div class="row">
                <div class="col-12 text-center validacion">
                    <img v-if="imagen" src="~@/assets/ico/check.png" alt="check">
                    <div>
                        <h4>{{mensaje}}</h4>
                    </div>
                    <div class="row">
                        <div class="col-7 centrar centrado divBoton">
                            <input @click="aceptar()" type="button" value="Aceptar" class="botonValidar">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import Router from '../router/index';
    import Cabecera from '../components/include/Cabecera';
    export default {
        name: "Autentificacion",
        components: {
            Router,
            Cabecera
        },
        data(){
            return{
                mensaje: Router.currentRoute.params.mensaje,
                imagen: Router.currentRoute.params.imagen,
                page: Router.currentRoute.params.page,
                pageBack: {name:"listaresumen", params: { tipo: 'correo' }}
            }
        },
        created(){
            this.pageBack = page === 'registration' ? this.pageBack = "/principal" : {name:"listaresumen", params: { tipo: 'correo' }}
            console.log(this.page);
            console.log(this.pageBack);
        },
        methods:{
            aceptar(){
                Router.push(this.pageBack);
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

.success{
    padding: 10px;
    text-align: center;
    border-radius: 10px;
    color: rgb(117, 255, 170);
    font-size: 16px;
    font-family: 'Roboto', sans-serif;
    font-weight: 100;
}

.cuerpoValidacion{
    line-height: 100%;
}

.botonValidar{
    padding: 3%;
}

.validacion img{
    width: 12%;
}

.validacion > div{
    padding: 1.5%;
}

.validacion h4{
    margin-top: 1%;
    text-shadow: 1px 1px 20px rgba(0, 0, 0,0.85);
}

</style>