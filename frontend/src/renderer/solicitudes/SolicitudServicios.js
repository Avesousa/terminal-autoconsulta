'use strict'
import Log from '../Log/Log';
import Terminal from '../modelos/Terminal';
import Token from '../modelos/Token';
import Properties from '../modelos/Properties';


const log = Log.instance().log;
const terminal = new Terminal();
const axios = require('axios');
const token = Token.instanciar();
const properties = Properties.instanciar();
const server = properties.getWebService();

class SolicitudServicios{
    log = Log.instance();
    static _instanciar;

    static instance(){
        if(!this._instanciar)
            this._instanciar = new SolicitudServicios();
        return this._instanciar;
    }

    consultar(link){
        return new Promise((resolve,reject) =>{
            axios({
                method: 'POST',
                url: server + '/terminal/api/rest/' + link,
                headers: {
                    'Authorization': token.getToken()
                }
            }).then(res => {
                log.info("Ha realizado una devolución del servidor");
                resolve(this.devolucion(res,true));
            }).catch(error => {
                //log.error(error.response.headers);
                this.hacerLogError(error);
                reject(this.devolucion(error,false));
            })
        })
    }

    conectar(request,metodo,link){
        if(metodo == 'POST'){
            return new Promise((resolve,reject) =>{
                axios({
                    method: metodo,
                    url: server + '/terminal/api/rest/' + link,
                    data: request,
                    headers: {
                        'Authorization': token.getToken()
                    }
                }).then(res => {
                    log.info("Ha realizado una devolución del servidor");
                    resolve(this.devolucion(res,true));
                }).catch(error => {
                    this.hacerLogError(error);
                    reject(this.devolucion(error,false));
                })
            })
        } else{
            return new Promise((resolve,reject) =>{
                axios({
                    method: metodo,
                    url: server + '/terminal/api/rest/' + link,
                    params: request,
                    headers: {
                        'Authorization': token.getToken()
                    }
                }).then(res => {
                    log.info("Ha realizado una devolución del servidor");
                    resolve(this.devolucion(res,true));
                }).catch(error => {
                    //log.error(error.response.headers);
                    this.hacerLogError(error);
                    reject(this.devolucion(error,false));
                })
            })
        }

    }

    registrar(request){
        return new Promise((resolve,reject) =>{
            axios({
                method: 'POST',
                url: server + '/terminal/api/rest/registrar',
                data: request,
                headers: {
                    'Authorization': token.getToken()
                }
            }).then(res => {
                log.info("Ha realizado sastifactoriamente conexión para preregistrar al usuario");
                resolve(this.devolucion(res,true));
            }).catch(error => {
                log.info("Ha ocurrido un error con el preregistrar del usuario");
                //log.error(error.response.headers);
                this.hacerLogError(error);
                reject(this.devolucion(error,false));
            })
        });
    }

    header(){
        axios({
            method: 'POST',
            url: server + '/terminal/api/auth/signin',
            data: terminal
        }).then(res => {
            log.info("Ha realizado conexión con la terminal con el token " + res.data.accessToken);
            token.setToken(res.data.tokenType + " " + res.data.accessToken);
        }).catch(error => {
            log.info("Ha ocurrido un error con la autenticación de " + terminal.getTerminal());
            console.log(error);
        });
    }

    hacerLogError(error){
        console.log(error);
        if(error.response)
            log.info("Ha generado un error " + error.response.data.error + " - " + error.response.data.mensaje)
        else
            log.info("Ha ocurrido un error con la conexión al servidor");
    }

    devolucion(valor,bool){
            if(valor){
                if(bool){
                    return {
                        "tipo": bool,
                        "resultado": valor.data,
                    };
                } else{
                    if(valor.response){
                        return {
                            "tipo": bool,
                            "error": valor.response.data.error,
                            "mensaje": valor.response.data.mensaje,
                            "flag": valor.response.data.error == "401"
                        }
                    }else{
                        return {
                            "tipo": bool,
                            "error": "Error en el servidor",
                            "mensaje": "Ocurrió un error al intentar conectarse al webservice",
                            "flag": false
                        }
                    }
                }
            }else{
                return{
                    "tipo": false,
                    "error": 512,
                    "mensaje": "No se pudo comunicar con el servidor"
                }
            }
    }
}

export default SolicitudServicios