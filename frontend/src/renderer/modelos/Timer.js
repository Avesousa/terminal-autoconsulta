const powerMonitor = require('electron');
class Timer{
    
    static instancia;
    time = 180;

    static instanciar(){
        if(!this.instancia){
            this.instancia = new Timer();
        }
        return this.instancia;
    }

    setTime(){
        //powerMonitor.
    }



}
export default Timer;