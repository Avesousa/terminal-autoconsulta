class Usuario{

    tipoDocumento;
	documento;
	usuario;
    password;
    option;
    genero;
    correo;
    fechaNacimiento;
    eresumen;
    
    constructor(t,d,u,p,o,g,e){
        this.tipoDocumento = t;
        this.documento = d;
        this.usuario = u;
        this.password = p;
        this.option = o;
        this.genero = g;
        this.eresumen = e;
    }

    toString(){
        return "Documento: " + this.documento + " Usuario: " + this.usuario + " PassWord: " + this.password;
    }



}

export default Usuario;