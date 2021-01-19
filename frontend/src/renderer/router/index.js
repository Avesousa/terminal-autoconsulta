import Vue from 'vue';
import Router from 'vue-router';
import 'bootstrap/dist/css/bootstrap.min.css';
import Principal from '../components/Principal';
import IniciarResumen from '../components/IniciarResumen';
import Iniciar from '../components/Iniciar';
import SeleccionResumen from '../components/SeleccionResumen';
import Navegador from '../components/Navegador';
import NavegadorMco from '../components/NavegadorMco';
import Resumen from '../components/Resumen';
import Registration from '../components/complementos/Registration';
import Confirmacion from '../components/complementos/Confirmacion';
import Validacion from '../solicitudes/Validacion';
import EnviarCorreo from '../components/complementos/EnviarCorreo';
import ModificarCorreo from '../components/ModificarCorreo';
import Inactividad from '../components/complementos/Inactividad';
//import CargandoTeclado from '../components/include/CargandoTeclado';


Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/',
      name: 'principal',
      component: Principal
    },
    {
      path: '/inactividad',
      name: 'inactividad',
      component: Inactividad
    },
    {
      path: '/iniciar',
      name: 'iniciar',
      component: Iniciar
    },
    {
      path: '*',
      redirect: '/'
    },
    {
      path: '/listaresumen',
      name: 'listaresumen',
      component: SeleccionResumen
    },
    {
      path: '/navegador',
      name: 'navegador',
      component: Navegador
    },
    {
      path: '/navegadorMco',
      name: 'navegadorMco',
      component: NavegadorMco
    },
    {
      path: '/resumen/:tarjeta/:fecha',
      name: 'resumen',
      component: Resumen
    },
    {
      path: '/iniciarresumen',
      name: 'iniciarResumen',
      component: IniciarResumen
    },
    {
      path: '/confirmacion',
      name: 'confirmacion',
      component: Confirmacion
    },
    {
      path: '/validacion',
      name: 'validacion',
      component: Validacion
    },
    {
      path: '/registration',
      name: 'registration',
      component: Registration
    },
    {
      path: '/enviar',
      name: 'enviar',
      component: EnviarCorreo
    },
    {
      path: '/modificar',
      name: 'modificar',
      component: ModificarCorreo
    }
  ]
})
