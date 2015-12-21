package com.delozoya;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpServerSalvador {

    public static void main(String[] args) {
	// write your code here
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(9005), 0);
            server.createContext("/index", new indexHandler());
            server.createContext("/data", new GetData());
            server.createContext("/test.html", new FileHtml());
            server.setExecutor(null);
            server.start();
        }catch (Exception e){
            System.out.println("Error: "+e);
        }
    }

    static class indexHandler implements HttpHandler{

        public void handle(HttpExchange t){
            try{
                InputStream in = t.getRequestBody();
                //String response = ;
                t.sendResponseHeaders(200,createWeb(1,1,1,1,1,1,1,1,1,1,1,1,1).length());
                OutputStream out = t.getResponseBody();
                out.write(createWeb(1,1,1,1,1,1,1,1,1,1,1,1,1).getBytes());
                out.close();
            } catch (Exception e){
                System.out.println("Error: "+e);
            }
        }
    }

    static class GetData implements HttpHandler {

        @Override

        public void handle(HttpExchange he) {
            try {
                // parse request
                Map<String, Object> parameters = new HashMap<String, Object>();
                URI requestedUri = he.getRequestURI();
                String query = requestedUri.getRawQuery();
                parseQuery(query, parameters);

                // send response
                String response = "";
                for (String key : parameters.keySet())
                    response += key + " = " + parameters.get(key) + "\n";
                he.sendResponseHeaders(200, response.length());
                OutputStream os = he.getResponseBody();
                os.write(response.toString().getBytes());
                System.out.println(response.toString());
                os.close();
            } catch (Exception e) {
                System.out.println("Error: " + e);
            }
        }
    }

    static class FileHtml implements HttpHandler{

        @Override
        public void handle(HttpExchange t) {
            try{
                String root = "/var/www/";
                URI uri = t.getRequestURI();
                File file = new File(root + uri.getPath()).getCanonicalFile();
                if (!file.getPath().startsWith(root)) {
                    // Suspected path traversal attack: reject with 403 error.
                    String response = "403 (Forbidden)\n";
                    t.sendResponseHeaders(403, response.length());
                    OutputStream os = t.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                } else if (!file.isFile()) {
                    // Object does not exist or is not a file: reject with 404 error.
                    String response = "404 (Not Found)\n";
                    t.sendResponseHeaders(404, response.length());
                    OutputStream os = t.getResponseBody();
                    os.write(response.getBytes());
                    os.close();
                } else {
                    // Object exists and is a file: accept with response code 200.
                    t.sendResponseHeaders(200, 0);
                    OutputStream os = t.getResponseBody();
                    FileInputStream fs = new FileInputStream(file);
                    final byte[] buffer = new byte[0x10000];
                    int count = 0;
                    while ((count = fs.read(buffer)) >= 0) {
                        os.write(buffer, 0, count);
                    }
                    fs.close();
                    os.close();

                }
            } catch (Exception e){
                System.out.println("Error: "+e);
            }
        }
    }

    public static void parseQuery(String query, Map<String,
            Object> parameters) throws UnsupportedEncodingException {

        if (query != null) {
            String pairs[] = query.split("[&]");
            for (String pair : pairs) {
                String param[] = pair.split("[=]");
                String key = null;
                String value = null;
                if (param.length > 0) {
                    key = URLDecoder.decode(param[0],
                            System.getProperty("file.encoding"));
                }

                if (param.length > 1) {
                    value = URLDecoder.decode(param[1],
                            System.getProperty("file.encoding"));
                }

                if (parameters.containsKey(key)) {
                    Object obj = parameters.get(key);
                    if (obj instanceof List<?>) {
                        List<String> values = (List<String>) obj;
                        values.add(value);

                    } else if (obj instanceof String) {
                        List<String> values = new ArrayList<String>();
                        values.add((String) obj);
                        values.add(value);
                        parameters.put(key, values);
                    }
                } else {
                    parameters.put(key, value);
                }
            }
        }
    }

    public static String createWeb(float t1, float t2, float t3, float t4, float t5, float t6, float t7, float t8,
                                    float t9, float t10, float t11, float t12, float t13){
        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<style>\n" +
                "table {\n" +
                "    width:15%;\n" +
                "}\n" +
                "table, th, td {\n" +
                "    border: 1px solid black;\n" +
                "    border-collapse: collapse;\n" +
                "}\n" +
                "th, td {\n" +
                "    padding: 5px;\n" +
                "    text-align: left;\n" +
                "}\n" +
                "table.names tr:nth-child(even) {\n" +
                "    background-color: #eee;\n" +
                "}\n" +
                "table.names tr:nth-child(odd) {\n" +
                "   background-color:#fff;\n" +
                "}\n" +
                "table.names th\t{\n" +
                "    background-color: black;\n" +
                "    color: white\n" +
                "}\n" +
                "</style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<table class=\"names\">\n" +
                "  <tr>\n" +
                "    <th>Sensor</th>\n" +
                "    <th>Temperatura</th>\t\t\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>T1</td>\n" +
                "    <td>"+t1+"</td>\t\t\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>T2</td>\n" +
                "    <td>"+t2+"</td>\t\t\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>T3</td>\n" +
                "    <td>"+t3+"</td>\t\t\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>T4</td>\n" +
                "    <td>"+t4+"</td>\t\t\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>T5</td>\n" +
                "    <td>"+t5+"</td>\t\t\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>T6</td>\n" +
                "    <td>"+t6+"</td>\t\t\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>T7</td>\n" +
                "    <td>"+t7+"</td>\t\t\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>T8</td>\n" +
                "    <td>"+t8+"</td>\t\t\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>T9</td>\n" +
                "    <td>"+t9+"</td>\t\t\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>T10</td>\n" +
                "    <td>"+t10+"</td>\t\t\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>T11</td>\n" +
                "    <td>"+t11+"</td>\t\t\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>T12</td>\n" +
                "    <td>"+t12+"</td>\t\t\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td>T13</td>\n" +
                "    <td>"+t13+"</td>\t\t\n" +
                "  </tr>\n" +
                "</table>\n" +
                "\n" +
                "</body>\n" +
                "</html>";
    }
}
