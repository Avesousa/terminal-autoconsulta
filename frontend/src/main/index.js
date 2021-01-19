import { app, BrowserWindow, session} from 'electron';

import Properties from '../renderer/modelos/Properties';
import Code from '../renderer/modelos/Code';
import Log from '../renderer/Log/Log';
const version = require("../../package.json").version;
const properties = Properties.instanciar();
const log = Log.instance().log;
const code = Code.instanciar();

/**
 * Set `__static` path to static files in production
 * https://simulatedgreg.gitbooks.io/electron-vue/content/en/using-static-assets.html
 */
if (process.env.NODE_ENV !== 'development') {
  global.__static = require('path').join(__dirname, '/static').replace(/\\/g, '\\\\')
}

let mainWindow
const winURL = process.env.NODE_ENV === 'development'
  ? `http://localhost:9080`
  : `file://${__dirname}/index.html`

function createWindow () {
  /**
   * Initial window options
   */
  mainWindow = new BrowserWindow({
    webPreferences: {
      plugins: true
    },
    height: 768,
    width: 1366,
    frame: false
  });

  code.setCode(new Date());
  log.info("Versión de BSF-TERMINAL AUTOCONSULTA: " + version);
  log.info("Se ha realizado la apertura en fecha/hora/segundo: " + code.getCode() + " / id of main window: " + mainWindow.id);
  log.info("El valor del enable para proxy es: " + properties.getProxyEnable());
  if(properties.getIsEnable()){
    if(properties.getProxyEnable() == true){
      var my_proxy = "http://"+properties.getProxyIp()+":"+properties.getProxyPort();
      mainWindow.webContents.session.setProxy({proxyRules:my_proxy}, function(){
        log.info("Está habilitado el proxy: " + my_proxy);
      });
      session.fromPartition("proxy-bsf").setProxy({proxyBypassRules:my_proxy},function(){
        log.info("Está habilitado en proxy-bsf el proxy: " + my_proxy);
      });
    }
  }else{
    log.error(properties.getError());
  }
  
  mainWindow.setFullScreen(true)
  mainWindow.loadURL(winURL)
  
  mainWindow.on('closed', () => {
    mainWindow = null
  })
}


app.on('ready', () => {
  if(!mainWindow) 
    createWindow() 
})

app.on('window-all-closed', () => {
  if (process.platform !== 'darwin') {
    app.quit()
  }
})

app.on('activate', () => {
  if (mainWindow === null) {
    createWindow()
  }
})

app.commandLine.appendSwitch('disable-pinch');
