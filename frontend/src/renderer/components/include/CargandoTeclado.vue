<template>
    <div>
        <div v-if="!keyBoardNumber" class="bg_espera">
            <div class="cuerpo_espera izq let">
                <img src="~@/assets/keyboard/izq.png" alt="">
            </div>
            <div class="cuerpo_espera der let">
                <img src="~@/assets/keyboard/der.png" alt="">
            </div>
        </div>
        <div v-else class="bg_espera">
            <div class="cuerpo_espera der num">
                <img src="~@/assets/keyboard/num.png" alt="">
            </div>
        </div>
    </div>
</template>

<script>
export default {

    name: 'cargandoTeclado',
    props:{
        keyBoardNumber: Boolean
    },
    methods:{
    }
    
}
</script>

<style>

    .cuerpo_espera{
        position: fixed;
        width: 15%;
        height: 38%;
        bottom: 8%;
        padding: .5%;
        padding-top: .6%;
        filter: blur(1px);
    }
    .der{
        right: 2%;
    }
    .izq{
        left: 2%;
    }
    .num{
        /*width: 10%;*/
        height: 60%;
        right: 7%;
    }
    .let{
        width: 25%;
    }
    .cuerpo_espera img{
        position: absolute;
        bottom: 0;
        left: 0;
        width: 100%;
    }

    

</style>