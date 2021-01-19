class NuevoUsuario{
    idTipoDocumento;
	documento;
    correoElectronico;
    fechaNacimiento;

    constructor(t,d,c,f){
        this.idTipoDocumento = t;
        this.documento = d;
        this.correoElectronico = c;
        this.fechaNacimiento = f;
    }

    toString(){
        return "Documento: " + this.documento + " Correo: " + this.correoElectronico;
    }
    
}

export default NuevoUsuario;