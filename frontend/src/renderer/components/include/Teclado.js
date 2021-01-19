import Properties from '../../modelos/Properties';
import Log from '../../Log/Log';
const properties = Properties.instanciar();
const log = Log.instance().log;

class Teclado{

    static instancia;
    bandera = false;
    keyboardProcessId;
    procesoTeclado = true;


    static instanciar(){
        if(!this.instancia)
            this.instancia = new Teclado();
        return this.instancia;
    }

    mostrarTeclado(esNumerico){
        if(this.bandera){
            this.cerrarTeclado();
        }else{
            if(properties.getIsEnable()){
                try {
                    const {exec} = require("child_process");
                    var keyboardExecutable = esNumerico ? properties.getPathTecladoNumerico() : properties.getPathTeclado();
                    //var keyboardExecutable = "pidof BSF";
                    log.info("Path keyboard: " + keyboardExecutable);
                    var keyboardExecution = exec(keyboardExecutable,(err,stdout,stderr) => {
                        log.info("Abrió teclado " + (esNumerico? "numerico" : "alfanumerico"));
                    })
                    console.log(keyboardExecution);
                    this.keyboardProcessId = keyboardExecution.pid;
                    this.bandera = true;
                    this.procesoTeclado = true;
                } catch (error) {
                    this.procesoTeclado = false;
                    log.error(error);
                }   
            }else{
                this.procesoTeclado = false;
                log.error(properties.getError());
                alert(properties.getError());
            }
        }
        
    }

    verProceso(){
        return this.procesoTeclado;
    }

    cerrarTeclado(){
        if(this.keyboardProcessId == undefined || this.keyboardProcessId == null){
            return;
        }
        try {
            var child = require('child_process');
            this.bandera = false;
            child.exec('taskkill /PID ' + this.keyboardProcessId + ' /T /F',function(err,stdout,stderr){
                if(err !== null){
                    log.info('Proceso en error');
                }
                log.info('Proceso del teclado cerrado');

            })

        } catch (error) {
            log.error('proceso de cierre en error exception: ' + error);
        }
    }

}

export default Teclado;