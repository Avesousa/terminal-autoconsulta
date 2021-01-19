import Properties from '../modelos/Properties';
const properties = Properties.instanciar();

class Terminal{

    terminal = properties.getTerminal();
    password = "Q1w2e3r4";
    
    getTerminal(){
        return this.terminal;
    }

}

export default Terminal;