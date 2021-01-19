class Cliente{

    static instancia;

	nombre;
    apellido;
    documento;
    user;
    tipoDocumento;
    nacimiento;
    correo;
    url;
    
    setCliente(cliente){
        this.nombre = cliente.nombre;
        this.apellido = cliente.apellido;
        this.documento = cliente.dni;
        this.tipoDocumento = cliente.tipo;
        this.nacimiento = cliente.nacimiento;
        this.correo = cliente.correo;
        this.url = cliente.url;

        // console.log("Cliente: [ \nnombre: " + this.nombre + "\napellido: " + this.apellido +
        // "\ndocumento: " + this.documento + "\ntipoDocumento: " + this.tipoDocumento +
        // "\nnacimiento: " + this.nacimiento + "\nnacimiento: " + this.nacimiento + "]");
    }


    static instanciar(){
        if(!this.instancia)
            this.instancia = new Cliente();
        return this.instancia;
    }

    getUrl(){
        return this.url;
    }

    getCorreo(){
        return this.correo;
    }

    getNombre(){
        return this.nombre;
    }

    getApellido(){
        return this.apellido;
    }

    getCliente(){
        return this.nombre + " " + this.apellido;
    }

    getFechaNacimiento(){
        return this.nacimiento;
    }

    getTipoDocumento(){
        return this.tipoDocumento;
    }

    getDocumento(){
        return this.documento;
    }
}

export default Cliente;