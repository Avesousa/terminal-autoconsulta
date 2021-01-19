class Mensaje{

    static instancia;

    static comenzar(){
        if(!this.instancia)
            this.instancia = new Mensaje();
        return this.instancia;
    }

    loginError = "Las credenciales ingresadas son inválidas";
    vacio = "Ha ocurrido un error en el servidor o la información suministrada pudo estar vacia"
    servidor = "Ha ocurrido un error en el servidor";
    servicioVPN = "No hay comunicación con el servidor";
    error = "Ha ocurrido un error";
    

    status(error,type){

        switch(error){
            //Caso de error de credenciales
            case "409":
                switch(type){
                    case "login": return this.loginError;
                    default: return this.default();
                }

            //Error con el servidor o envío de información errónea.
            case "400": 
                switch(type){
                    case "login": return this.vacio;
                    default: return this.error400();
                }

            //Error con la conexión del web service.
            case "511": return this.error511();
            default: return this.default();
        }

    }

    error511(){
        alert(this.servicioVPN);
        return this.servicioVPN;
    }

    error400(){
        alert(this.servidor);
        return this.servidor;
    }

    default(){
        alert(this.error);
        return this.error;
    }
}

export default Mensaje;