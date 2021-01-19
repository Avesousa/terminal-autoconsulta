class Token{

    static instancia;
    token;

    static instanciar(){
        if(!this.instancia)
            this.instancia = new Token();
        return this.instancia;
    }

    getToken(){
        return this.token;
    }

    setToken(token){
        this.token = token;
    }
}

export default Token;