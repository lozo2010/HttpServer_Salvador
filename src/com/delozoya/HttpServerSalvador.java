package com.delozoya;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.org.apache.bcel.internal.generic.FLOAD;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HttpServerSalvador {

    static float t1;
    static float t2;
    static float t3;
    static float t4;
    static float t5;
    static float t6;
    static float t7;
    static float t8;
    static float t9;
    static float t10;
    static float t11;
    static float t12;
    static float t13;
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
                t.sendResponseHeaders(200,createWeb(t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13).length());
                OutputStream out = t.getResponseBody();
                out.write(createWeb(t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13).getBytes());
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
                //System.out.println(response.toString());
                String s =response.toString().substring(0,3);
                //System.out.println(s.toString());
                switch(s) {
                    case "t1 ":
                        System.out.println("t1 es "+response.toString().substring(4));
                        t1 = Float.parseFloat(response.toString().substring(4));
                        break;
                    case "t2 ":
                        //System.out.println("t2 es "+response.toString().substring(4));
                        t2 = Float.parseFloat(response.toString().substring(4));
                        break;
                    case "t3 ":
                        //System.out.println("t1 es "+response.toString().substring(4));
                        t3 = Float.parseFloat(response.toString().substring(4));
                        break;
                    case "t4 ":
                        //System.out.println("t2 es "+response.toString().substring(4));
                        t4 = Float.parseFloat(response.toString().substring(4));
                        break;
                    case "t5 ":
                        //System.out.println("t1 es "+response.toString().substring(4));
                        t5 = Float.parseFloat(response.toString().substring(4));
                        break;
                    case "t6 ":
                        //System.out.println("t2 es "+response.toString().substring(4));
                        t6 = Float.parseFloat(response.toString().substring(4));
                        break;
                    case "t7 ":
                        //System.out.println("t1 es "+response.toString().substring(4));
                        t7 = Float.parseFloat(response.toString().substring(4));
                        break;
                    case "t8 ":
                        //System.out.println("t2 es "+response.toString().substring(4));
                        t8 = Float.parseFloat(response.toString().substring(4));
                        break;
                    case "t9 ":
                        //System.out.println("t1 es "+response.toString().substring(4));
                        t9 = Float.parseFloat(response.toString().substring(4));
                        break;
                    case "t10":
                        //System.out.println("t2 es "+response.toString().substring(5));
                        t10 = Float.parseFloat(response.toString().substring(5));
                        break;
                    case "t11":
                        //System.out.println("t1 es "+response.toString().substring(5));
                        t11 = Float.parseFloat(response.toString().substring(5));
                        break;
                    case "t12":
                        //System.out.println("t2 es "+response.toString().substring(5));
                        t12 = Float.parseFloat(response.toString().substring(5));
                        break;
                    case "t13":
                        //System.out.println("t2 es "+response.toString().substring(5));
                        t13 = Float.parseFloat(response.toString().substring(5));
                        break;
                }
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

        String T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13 = null;

        if (t1>80){
            T1 = "<td>T1</td>\n<td bgcolor=\"#FF0000\">"+t1+" &deg;C</td>\t\t\n</tr>\n" ;
        }else{
            T1 = "<td>T1</td>\n    <td>"+t1+" &deg;C</td>\t\t\n</tr>\n" ;
        }
        if (t2>80){
            T2 = "<td>T2</td>\n<td bgcolor=\"#FF0000\">"+t2+" &deg;C</td>\t\t\n</tr>\n" ;
        }else{
            T2 = "<td>T2</td>\n    <td>"+t2+" &deg;C</td>\t\t\n</tr>\n" ;
        }

        if (t3>80){
            T3 = "<td>T3</td>\n<td bgcolor=\"#FF0000\">"+t3+" &deg;C</td>\t\t\n</tr>\n" ;
        }else{
            T3 = "<td>T3</td>\n    <td>"+t3+" &deg;C</td>\t\t\n</tr>\n" ;
        }

        if (t4>80){
            T4 = "<td>T4</td>\n<td bgcolor=\"#FF0000\">"+t4+" &deg;C</td>\t\t\n</tr>\n" ;
        }else{
            T4 = "<td>T4</td>\n    <td>"+t4+" &deg;C</td>\t\t\n</tr>\n" ;
        }
        if (t5>80){
            T5 = "<td>T5</td>\n<td bgcolor=\"#FF0000\">"+t5+" &deg;C</td>\t\t\n</tr>\n" ;
        }else{
            T5 = "<td>T5</td>\n    <td>"+t5+" &deg;C</td>\t\t\n</tr>\n" ;
        }

        if (t6>80){
            T6 = "<td>T6</td>\n<td bgcolor=\"#FF0000\">"+t6+" &deg;C</td>\t\t\n</tr>\n" ;
        }else{
            T6 = "<td>T6</td>\n    <td>"+t6+" &deg;C</td>\t\t\n</tr>\n" ;
        }

        if (t7>80){
            T7 = "<td>T7</td>\n<td bgcolor=\"#FF0000\">"+t7+" &deg;C</td>\t\t\n</tr>\n" ;
        }else{
            T7 = "<td>T7</td>\n    <td>"+t7+" &deg;C</td>\t\t\n</tr>\n" ;
        }
        if (t8>80){
            T8 = "<td>T8</td>\n<td bgcolor=\"#FF0000\">"+t8+" &deg;C</td>\t\t\n</tr>\n" ;
        }else{
            T8 = "<td>T8</td>\n    <td>"+t8+" &deg;C</td>\t\t\n</tr>\n" ;
        }

        if (t9>80){
            T9 = "<td>T9</td>\n<td bgcolor=\"#FF0000\">"+t9+" &deg;C</td>\t\t\n</tr>\n" ;
        }else{
            T9 = "<td>T9</td>\n    <td>"+t9+" &deg;C</td>\t\t\n</tr>\n" ;
        }

        if (t10>80){
            T10 = "<td>T10</td>\n<td bgcolor=\"#FF0000\">"+t10+" &deg;C</td>\t\t\n</tr>\n" ;
        }else{
            T10 = "<td>T10</td>\n    <td>"+t10+" &deg;C</td>\t\t\n</tr>\n" ;
        }
        if (t11>80){
            T11 = "<td>T11</td>\n<td bgcolor=\"#FF0000\">"+t11+" &deg;C</td>\t\t\n</tr>\n" ;
        }else{
            T11 = "<td>T11</td>\n    <td>"+t11+" &deg;C</td>\t\t\n</tr>\n" ;
        }

        if (t12>80){
            T12 = "<td>T12</td>\n<td bgcolor=\"#FF0000\">"+t12+" &deg;C</td>\t\t\n</tr>\n" ;
        }else{
            T12 = "<td>T12</td>\n    <td>"+t12+" &deg;C</td>\t\t\n</tr>\n" ;
        }

        if (t13>80){
            T13 = "<td>T13</td>\n<td bgcolor=\"#FF0000\">"+t13+" &deg;C</td>\t\t\n</tr>\n" ;
        }else{
            T13 = "<td>T13</td>\n    <td>"+t13+" &deg;C</td>\t\t\n</tr>\n" ;
        }

        return "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "<style>\n" +
                "table {\n" +
                "    width: 25%;\n" +
                "    margin-left: 130px;\n"+
                "}\n" +
                "table, th, td {\n" +
                "    border: 2px solid black;\n" +
                "    border-collapse: collapse;\n" +
                "}\n" +
                "th, td {\n" +
                "    padding: 5px;\n" +
                "    text-align: center;\n" +
                "}\n" +
                "table.names tr:nth-child(even) {\n" +
                "    background-color: #eee;\n" +
                "}\n" +
                "table.names tr:nth-child(odd) {\n" +
                "   background-color:#fff;\n" +
                "}\n" +
                "table.names th\t{\n" +
                "    background-color: grey;\n" +
                "    color: white\n" +
                "}\n" +
                "</style>\n" +
                "<title>Estado Tiempo Real</title>"+
                "</head>\n" +
                "<body>\n" +
                "<div style=\"padding: 0px; float: left; width: 45%; text-align: justify;\">"+
                "<H1>Temperaturas Tiempo Real </H1>"+
                "\n" +
                "<table class=\"names\">\n" +
                "  <tr>\n" +
                "    <th>Sensor</th>\n" +
                "    <th>Temperatura</th>\t\t\n" +
                "  </tr>\n" +
                "  <tr>\n" + T1 +
                "  <tr>\n" + T2 +
                "  <tr>\n" + T3 +
                "  <tr>\n" + T4 +
                "  <tr>\n" + T5 +
                "  <tr>\n" + T6 +
                "  <tr>\n" + T7 +
                "  <tr>\n" + T8 +
                "  <tr>\n" + T9 +
                "  <tr>\n" + T10 +
                "  <tr>\n" + T11 +
                "  <tr>\n" + T12 +
                "  <tr>\n" + T13 +
                "</table>\n" +
                "\n" +
                "</div>"+
                "<div style=\"padding: 0px; float: right; width: 45%; text-align: justify;\">"+
                "<H1>Estado bombas y valvulas</H1>"+
                "</div>"+
                "</body>\n" +
                " <META HTTP-EQUIV=\"REFRESH\" CONTENT=\"5 ;URL=index\">"+
                "</html>";
    }
}
