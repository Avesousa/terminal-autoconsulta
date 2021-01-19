class Code{
    
    static instancia;
    code;

    static instanciar(){
        if(!this.instancia){
            this.instancia = new Code();
        }
        return this.instancia;
    }

    setCode(code){
        this.code = code;
    }

    getCode(){
        return this.code;
    }

}
export default Code;