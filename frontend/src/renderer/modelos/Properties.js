import Log from '../Log/Log';
const log = Log.instance().log;
class Properties{

    static instancia;
    isProperties = false;
    mensajeDeError = "La ubicación de las properties son incorrectas"
    
    constructor(){
        var propertiesReader = require('properties-reader');
        try {
            this.properties = propertiesReader('terminal-autoconsulta.properties');
            this.isProperties = true;
        } catch (error) {
            this.isProperties = false;
        }
    }
    static instanciar(){
        if(!this.instancia)
            this.instancia = new Properties();
        return this.instancia;
    }
    getTerminal(){
        return this.isProperties? this.properties.get('terminal.name') : "Nombre de la terminal";
    }
    getTimer(){
        return this.isProperties? this.properties.get('terminal.timer') : 18000;
    }

    getWebService(){
        return this.isProperties? this.properties.get('terminal.webservice.host') : "http://localhost:8080";
    }
    getMcoUrlPrincipal(){
        return this.isProperties? this.properties.get('terminal.mco.url.principal') : "http://localhost";
    }
    getPathTeclado(){
        return this.isProperties? this.properties.get('terminal.path.teclado') : "C:\\dev\\keyboard\\keyboardV2-2.jar";
    }
    getPathTecladoNumerico(){
        return this.isProperties? this.properties.get('terminal.path.teclado.numerico') : "C:\\dev\\keyboard\\keyboardV2-number.jar";
    }
    getProxyEnable(){
        return this.isProperties? this.properties.get('proxy.enable') : "false";
    }
    getProxyIp(){
        return this.isProperties? this.properties.get('proxy.ip') : "10.94.165.18";
    }
    getProxyPort(){
        return this.isProperties? this.properties.get('proxy.port') : "8085";
    }
    getIsEnable(){
        return this.isProperties;
    }
    getError(){
        return this.mensajeDeError;
    }
    getVideoPromocion(){
        let video = this.isProperties ? this.properties.get('marketing.video') : "C:\\dev\\marketing\\basico.mp4";
        log.info("Path video: " + video);
        return video;
    }
}
export default Properties;