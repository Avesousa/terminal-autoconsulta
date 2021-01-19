<template>
    <div id="cuerpo">
        <Cabecera :teclado="false" :menu="true" :pageBack="pageBack" :isConfirmMail="true"/>
        <div class="container mt-5">
            <div class="row">
                <div class="col-12">
                    <h3 class="correo">Se enviará el resumen de cuenta a la siguiente dirección de correo electrónico</h3><br>
                </div>
                <div class="col-12 text-center aviso aviso-true bg-aviso">
                    <div class="col-12 correo">
                        <h4>{{correo}}</h4><br>
                    </div>
                    <div class="row justify-content-md-center">
                        <div class="col-5 documento">
                            <div>
                                <i class="ico_left ico_dni"></i>
                                <h4>D.N.I ingresado {{formatear(dni)}}</h4>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row mt-5">
                <div class="col-12">
                    <div class="row">
                        <div class="col-6 btn-confirm btn-confirm-izq">
                            <input @click="enviarCorreo()" type="button" value="Enviar correo">
                        </div>
                        <div class="col-6 btn-confirm btn-confirm-der">
                            <input @click="modificar()" type="button" value="Modificar Mail">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>
<script>
    import Router from '../../router/index';
    import Token from '../../modelos/Token';
    import Cliente from '../../modelos/Cliente';
    import Cabecera from '../include/Cabecera';
    import FileSaver from 'file-saver';
    import SolicitudServicios from '../../solicitudes/SolicitudServicios'; 

    export default {
        name: "Confirmacion",
        components:{
            Router,
            Cliente,
            SolicitudServicios,
            Cabecera
        },
        data: function(){
            return{
            cliente: Cliente.instanciar(),
            correo: String,
            dni: String,
            pageBack: "/principal"
            }
        },
        created: function(){
            this.agregar();
        },
        methods: {
            agregar: function(){
                console.log("--> Confirmación/agregar");
                this.correo = this.cliente.correo;
                this.dni = this.cliente.documento;
                this.limpiarCorreo();
            },
            limpiarCorreo:function(){
                console.log("--> Confirmación/limpiarCorreo");
                let bandera = this.correo.split("@");
                let user = bandera[0];
                let host = bandera[1];
                let banderaUser = "";
                let banderaHost = "***";
                let indiceUser = user.length > 4 ? (parseInt(user.length/2))-1 : 0;
                let indiceHost = host.length > 4 ? (parseInt((host.length-3)/2))-1 : 0;
                for(var i = 0; i <= indiceUser; i++){
                    banderaUser += user.charAt(i);
                }
                for(var i = indiceHost; i < host.length; i++){
                    banderaHost += host.charAt(i);
                }
                this.correo = banderaUser + "***@" + banderaHost;               
            },
            enviarCorreo: function(){
                console.log("--> Confirmación/enviarCorreo");
                Router.push({
                        name:"listaresumen",
                        params: { tipo: 'correo'}});
            },
            modificar: function(){
                Router.push('/modificar');
            },
            formatear:function (num){
                console.log("--> Confirmación/formatear");
                num +='';
                var splitStr = num.split('.');
                var splitLeft = splitStr[0];
                var splitRight = splitStr.length > 1 ? this.sepDecimal + splitStr[1] : '';
                var regx = /(\d+)(\d{3})/;
                while (regx.test(splitLeft)) {
                    splitLeft = splitLeft.replace(regx, '$1' + '.' + '$2');
                }
                return splitLeft +splitRight;
            }
        }
    }
</script>

<style>

h3.correo{
    font-size: 45px;
    text-align: center;
}

div.correo h4{
    font-size: 40px;
}

div.documento{
    background-color: rgba(255, 255, 255, 0.3);
    border-radius: 20px;
}
div.documento h4{
    padding: 2%;
    text-shadow: 1px 1px 12px rgba(0,0,0,0.75);
    margin-bottom: 0;
    text-align: start;
    margin-left: 45px;
}

div.documento .ico_dni{
    box-shadow: 1px 1px 12px rgba(0,0,0,0.65);
    width: 30px;
    height: 26px;
    top: calc(50% - 15px);
    left: 28px;
}

div.aviso h4{
    font-weight: lighter;
}

.bg-aviso{
    background-color: rgba(255, 255, 255, 0.3);
}

.btn-confirm input[type="button"]{
    width: 50%;
}

.btn-confirm-izq input[type="button"]{
    float: right;
}

</style>