class Log{
    
    constructor(){
        this.log = require('electron-log');
        const fs = require('fs');
        this.log.transports.file.format = '{y}/{m}/{d} {h}:{i}:{s}:{ms} {text}';
        this.log.transports.file.level = 'info';
        this.log.transports.file.maxSize = 25 * 1024 * 1024;
        this.log.transports.file.file = './Log/bsf-log.txt';
        this.log.transports.file.streamConfig = { flags: 'w' };
        // set existed file stream
        this.log.transports.file.stream = fs.createWriteStream('log.txt');  
    }

    static _instance;

    static instance(){
        if(!this._instance)
            this._instance = new Log();
        return this._instance;
    }

    do(){
        return this.log;
    }

}

export default Log;