class Link{

    url;
    static instancia;

    static instanciar(){
        if(!this.instancia)
            this.instancia = new Link();
        return this.instancia;
    }

    getUrl(){
        return this.url;
    }

    setUrl(url){
        this.url = url;
    }

}
export default Link;