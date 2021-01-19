class Resumen{

    nacimiento;
    documento;
    tipoDocumento;
	tarjeta;
    resumen;
    
    constructor(n,d,td,ta,r){
        this.nacimiento = n;
        this.documento = d;
        this.tipoDocumento = td;
        this.tarjeta = ta;
        this.resumen = r;
    }

    getFechaNacimiento(){
        return this.nacimiento;
    }

    getDocumento(){
        return this.documento;
    }

    getTipoDocumento(){
        return this.tipoDocumento;
    }

    getTarjeta(){
        return this.tarjeta;
    }

    getResumen(){
        return this.resumen;
    }
}
export default Resumen;