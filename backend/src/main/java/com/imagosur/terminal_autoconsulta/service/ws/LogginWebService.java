package com.imagosur.terminal_autoconsulta.service.ws;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
public class LogginWebService implements ClientHttpRequestInterceptor{

	final static Logger log = LoggerFactory.getLogger(LogginWebService.class);

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        traceRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        traceResponse(response);
        return response;
    }

    private void traceRequest(HttpRequest request, byte[] body) throws IOException {
    	log.info("============================	request comienzo	============================");
        log.info("URL DE CONEXIÃ“N	: {}", request.getURI());
        log.info("MÃ‰TODO			: {}", request.getMethod());
        log.info("CABECERA		    : {}", request.getHeaders() );
        log.info("CUERPO DE CONSULTA: {}", new String(body, "UTF-8"));
        log.info("============================	  request final		============================");
    }

    private void traceResponse(ClientHttpResponse response) throws IOException {
        StringBuilder inputStringBuilder = new StringBuilder();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(response.getBody(), "UTF-8"));
        String line = bufferedReader.readLine();
        while (line != null) {
            inputStringBuilder.append(line);
            inputStringBuilder.append('\n');
            line = bufferedReader.readLine();
        }
        log.info("============================	response comienzo	============================");
        log.info("ESTATUS CÃ“DIGO	: {}", response.getStatusCode());
        log.info("ESTATUS TEXTO		: {}", response.getStatusText());
        log.info("CABECERA			: {}", response.getHeaders());
        log.info("CUERPO RESPUESTA	: {}", inputStringBuilder.toString());
        log.info("============================	  response final	============================");
        
    }

}