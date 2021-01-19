<template>
  <div id="cuerpo">
    <Cabecera :teclado="false" :menu="true" :pageBack="pageBack"/>
    <main class="container ">
      <div class="row botones mco_actualizar">
        <div class="col-12 btnVista">
          <button class="alt">
            <router-link to="/iniciar">
              <img src="~@/assets/btn/mco_actualizar.png" alt="cuentaOnline">
            </router-link>
          </button>
        </div>
      </div>
    </main>
    <div class="container-fluid footer">
      <div class="col-12 publicidad">
        <img src="~@/assets/publicidad.png" alt="Publicidad de aplicación">
      </div>
    </div>
  </div>

</template>

<script>
  import Cabecera from './include/Cabecera';
  import Router from '../router/index';
  import Link from '../modelos/Link';
  const link = Link.instanciar();

  export default {
    name: 'modificarCorreo',
    components: {
      Cabecera,
      Router,
    },
    data: function(){
      return {
        pageBack: ""
      }
    },
  }
</script>

<style>
  @import url('https://fonts.googleapis.com/css?family=Source+Sans+Pro');
  @import url('./css/principal.css');

  .footer{
    padding: 0;
    position: absolute;
    bottom: 0;
  }
  .publicidad img{
    width: 100%;
  }

  .publicidad{
    padding: 0;
    width: 100%;
    display: inline-flexbox;
    bottom: 0px;
  }

  main {
    display: flex;
    justify-content: space-between;
  }


  .left-side {
    display: flex;
    flex-direction: column;
  }

  .mco_actualizar{
    margin: 0 auto;
  }

  .mco_actualizar button{
    margin-top: 12%;
  }
  
</style>
