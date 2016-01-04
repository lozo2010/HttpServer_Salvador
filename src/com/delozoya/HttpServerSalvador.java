package com.delozoya;

import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import com.sun.org.apache.bcel.internal.generic.FLOAD;
import sun.util.calendar.BaseCalendar;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.*;
import java.security.MessageDigest;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.zip.Checksum;

public class HttpServerSalvador {

    static int t1;
    static int t2;
    static int t3;
    static int t4;
    static int t5;
    static int t6;
    static int t7;
    static int t8;
    static int t9;
    static int t10;
    static int t11;
    static int t12;
    static int t13;
    static int b1;
    static int b2;
    static int b3;
    static int b4;
    static int b5;

    static int v31;
    static int v32;
    static int v33;
    static int v34;
    static int v35;
    static int v36;
    static int v37;
    static int v38;
    static int v39;
    static int vma1;
    static int vmc1;
    static int vmc2;
    static int vmc3;
    static int vmc4;
    static int vmc5;
    static int vmzm1;
    static int q1;


    public static void main(String[] args) {
	// write your code here

        try {
            //Date fecha = new Date();
            HttpServer server = HttpServer.create(new InetSocketAddress(9005), 0);
            server.createContext("/index", new indexHandler());
            server.createContext("/data", new GetData());
            server.createContext("/test.html", new FileHtml());
            server.setExecutor(null);
            server.start();
            System.out.println("Server Start in port 9005 and ip: "+ InetAddress.getLocalHost().getHostAddress() +"\n"+ new Date());
            server();
            //Checksum_data();
        }catch (Exception e){
            System.out.println("Error: "+e);
        }
    }

    static void server() throws IOException {
        System.out.println("**********Server Program**************");
        int byteRead =0;


            int bytesRead;
            int current = 0;
            int read = 0;
            int totalRead = 0;

            ServerSocket serverSocket = null;
            serverSocket = new ServerSocket(4444);
            if(!serverSocket.isBound())
                System.out.println("Sever Socket not Bounded...");
            else
                System.out.println("Server Socket bounded to Port : "+serverSocket.getLocalPort());

            //Socket clientSocket = serverSocket.accept();


            while(true) {

                 Socket clientSocket = null;
                clientSocket = serverSocket.accept();
                if(!clientSocket.isConnected())
                    System.out.println("Client Socket not Connected...");
                else
                    System.out.println("Client Socket Connected : "+clientSocket.getInetAddress());
                InputStream in = clientSocket.getInputStream();

                DataInputStream clientData = new DataInputStream(in);

                String fileName = clientData.readUTF();
                OutputStream output = new FileOutputStream(fileName);
                long size = clientData.readLong();
                byte[] buffer = new byte[1024];
                while (size > 0 && (bytesRead = clientData.read(buffer, 0, (int) Math.min(buffer.length, size))) != -1) {
                    output.write(buffer, 0, bytesRead);
                    size -= bytesRead;

                    totalRead += bytesRead;
                    //remaining -= read;
                    // System.out.println("read " + totalRead + " bytes.");
                }

                // Sending the response back to the client.
                ObjectOutputStream oos = new ObjectOutputStream(clientSocket.getOutputStream());
                oos.flush();
                oos.writeObject("ok");
                Date fecha = new Date();
                String fecha1 = new SimpleDateFormat("dd-MM-yyyy'-'HH:mm:ss").format(fecha);
                System.out.println("Base de Datos recibida correctamente. "+ fecha1);

                // Closing the FileOutputStream handle
                output.close();


            }

    }



    /*public static void filetranfer()  {
        try {
            ServerSocket server_socket = new ServerSocket(2233);
            Socket socket = server_socket.accept();
            FileOutputStream fos = new FileOutputStream(
                    "/home/david/test51.db");
            BufferedOutputStream out = new BufferedOutputStream(fos);
            byte[] buffer = new byte[1024];
            int count;
            OutputStream os = socket.getOutputStream();
            InputStream in = socket.getInputStream();
            while((count=in.read(buffer)) >=0 ){
                fos.write(buffer, 0, count);
            }
            fos.close();
            System.out.println("test");
            Resultant res = new Resultat(14);
            ObjectOutputStream oos = new ObjectOutputStream(os);
            oos.writeObject(res);
            oos.close();
            socket.close();
        }catch (Exception e){
            System.out.println("Error: "+e);
        }
    }*/


    static class indexHandler implements HttpHandler{

        public void handle(HttpExchange t){
            try{
                InputStream in = t.getRequestBody();
                //String response = ;
                t.sendResponseHeaders(200,createWeb(t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,b1,b2,b3,b4,b5,v31,v32,v33,v34,v35,
                        v36,v37,v38,v39,vma1,vmc1,vmc2,vmc3,vmc4,vmc5,vmzm1,q1).length());
                OutputStream out = t.getResponseBody();
                out.write(createWeb(t1,t2,t3,t4,t5,t6,t7,t8,t9,t10,t11,t12,t13,b1,b2,b3,b4,b5,v31,v32,v33,v34,v35,
                        v36,v37,v38,v39,vma1,vmc1,vmc2,vmc3,vmc4,vmc5,vmzm1,q1).getBytes());
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
                os.write(response.getBytes());
                //System.out.println(response);
                String s =response.substring(0,3);
                //System.out.println(s);
                switch(s) {
                    case "t1 ":
                        //System.out.println("t1 es "+response.substring(4));
                        t1 = Integer.parseInt(response.substring(5).trim());
                        break;
                    case "t2 ":
                        //System.out.println("t2 es "+response.substring(4));
                        t2 = Integer.parseInt(response.substring(5).trim());
                        break;
                    case "t3 ":
                        //System.out.println("t1 es "+response.substring(4));
                        t3 = Integer.parseInt(response.substring(5).trim());
                        break;
                    case "t4 ":
                        //System.out.println("t2 es "+response.substring(4));
                        t4 = Integer.parseInt(response.substring(5).trim());
                        break;
                    case "t5 ":
                        //System.out.println("t1 es "+response.substring(4));
                        t5 = Integer.parseInt(response.substring(5).trim());
                        break;
                    case "t6 ":
                        //System.out.println("t2 es "+response.substring(4));
                        t6 = Integer.parseInt(response.substring(5).trim());
                        break;
                    case "t7 ":
                        //System.out.println("t1 es "+response.substring(4));
                        t7 = Integer.parseInt(response.substring(5).trim());
                        break;
                    case "t8 ":
                        //System.out.println("t2 es "+response.substring(4));
                        t8 = Integer.parseInt(response.substring(5).trim());
                        break;
                    case "t9 ":
                        //System.out.println("t1 es "+response.substring(4));
                        t9 = Integer.parseInt(response.substring(5).trim());
                        break;
                    case "t10":
                        //System.out.println("t2 es "+response.substring(5));
                        t10 = Integer.parseInt(response.substring(6).trim());
                        break;
                    case "t11":
                        //System.out.println("t1 es "+response.substring(5));
                        t11 = Integer.parseInt(response.substring(6).trim());
                        break;
                    case "t12":
                        //System.out.println("t2 es "+response.substring(5));
                        t12 = Integer.parseInt(response.substring(6).trim());
                        break;
                    case "t13":
                        //System.out.println("t2 es "+response.substring(5));
                        t13 = Integer.parseInt(response.substring(6).trim());
                        break;


                    case "b1 ":
                        //System.out.println("b1 es "+response.substring(4));
                        b1 = Integer.parseInt(response.substring(5,6).trim());
                        break;
                    case "b2 ":
                        //System.out.println("b2 es "+response.substring(4));
                        b2 = Integer.parseInt(response.substring(5).trim());
                        break;
                    case "b3 ":
                        //System.out.println("b3 es "+response.substring(4));
                        b3 = Integer.parseInt(response.substring(5).trim());
                        break;
                    case "b4 ":
                        //System.out.println("b4 es "+response.substring(4));
                        b4 = Integer.parseInt(response.substring(5).trim());
                        break;
                    case "b5 ":
                        //System.out.println("b5 es "+response.substring(4));
                        b5 = Integer.parseInt(response.substring(5).trim());
                        break;

                    case "v1 ":
                        //System.out.println("t1 es "+response.substring(4));
                        v31 = Integer.parseInt(response.substring(5).trim());
                        break;
                    case "v2 ":
                        //System.out.println("t2 es "+response.substring(4));
                        v32 = Integer.parseInt(response.substring(5).trim());
                        break;
                    case "v3 ":
                        //System.out.println("t1 es "+response.substring(4));
                        v33 = Integer.parseInt(response.substring(5).trim());
                        break;
                    case "v4 ":
                        //System.out.println("t2 es "+response.substring(4));
                        v34 = Integer.parseInt(response.substring(5).trim());
                        break;
                    case "v5 ":
                        //System.out.println("t1 es "+response.substring(4));
                        v35 = Integer.parseInt(response.substring(5).trim());
                        break;
                    case "v6 ":
                        //System.out.println("t2 es "+response.substring(4));
                        v36 = Integer.parseInt(response.substring(5).trim());
                        break;
                    case "v7 ":
                        //System.out.println("t1 es "+response.substring(4));
                        v37 = Integer.parseInt(response.substring(5).trim());
                        break;
                    case "v8 ":
                        //System.out.println("t2 es "+response.substring(4));
                        v38 = Integer.parseInt(response.substring(5).trim());
                        break;
                    case "v9 ":
                        //System.out.println("t1 es "+response.substring(4));
                        v39 = Integer.parseInt(response.substring(5).trim());
                        break;
                    case "v10":
                        //System.out.println("t2 es "+response.substring(5));
                        vma1 = Integer.parseInt(response.substring(6).trim());
                        break;
                    case "v11":
                        //System.out.println("t1 es "+response.substring(5));
                        vmc1 = Integer.parseInt(response.substring(6).trim());
                        break;
                    case "v12":
                        //System.out.println("t2 es "+response.substring(5));
                        vmc2 = Integer.parseInt(response.substring(6).trim());
                        break;
                    case "v13":
                        //System.out.println("t2 es "+response.substring(5));
                        vmc3 = Integer.parseInt(response.substring(6).trim());
                        break;
                    case "v14":
                        //System.out.println("t2 es "+response.substring(5));
                        vmc4 = Integer.parseInt(response.substring(6).trim());
                        break;
                    case "v15":
                        //System.out.println("t2 es "+response.substring(5));
                        vmc5 = Integer.parseInt(response.substring(6).trim());
                        break;
                    case "v16":
                        //System.out.println("t2 es "+response.substring(5));
                        vmzm1 = Integer.parseInt(response.substring(6).trim());
                        break;
                    case "v17":
                        //System.out.println("t2 es "+response.substring(5));
                        q1 = Integer.parseInt(response.substring(6).trim());
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
                    int count;
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

    public static String createWeb(int t1, int t2, int t3, int t4, int t5, int t6, int t7, int t8,
                                   int t9, int t10, int t11, int t12, int t13, int b1, int b2, int b3,
                                   int b4, int b5, int v31, int v32, int v33, int v34, int v35, int v36, int v37,
                                   int v38, int v39, int vma1, int vmc1, int vmc2, int vmc3, int vmc4, int vmc5,
                                   int vmzm1, int q1){

        String T1, T2, T3, T4, T5, T6, T7, T8, T9, T10, T11, T12, T13, B1, B2, B3, B4, B5, V31, V32, V33, V34, V35, V36,
        V37, V38, V39, VMA1, VMC1, VMC2, VMC3, VMC4, VMC5, VMZM1, Q1;

        if (t1>80){
            T1 = "<td bgcolor=\"#FF0000\">"+t1+"&deg;C</td>" ;
        }else{
            T1 = "<td>"+t1+"&deg;C</td>" ;
        }
        if (t2>80){
            T2 = "<td bgcolor=\"#FF0000\">"+t2+"&deg;C</td>" ;
        }else{
            T2 = "<td>"+t2+"&deg;C</td>" ;
        }

        if (t3>80){
            T3 = "<td bgcolor=\"#FF0000\">"+t3+"&deg;C</td>" ;
        }else{
            T3 = "<td>"+t3+"&deg;C</td>" ;
        }

        if (t4>80){
            T4 = "<td bgcolor=\"#FF0000\">"+t4+"&deg;C</td>" ;
        }else{
            T4 = "<td>"+t4+"&deg;C</td>" ;
        }
        if (t5>80){
            T5 = "<td bgcolor=\"#FF0000\">"+t5+"&deg;C</td>" ;
        }else{
            T5 = "<td>"+t5+"&deg;C</td>" ;
        }

        if (t6>80){
            T6 = "<td bgcolor=\"#FF0000\">"+t6+"&deg;C</td>" ;
        }else{
            T6 = "<td>"+t6+"&deg;C</td>" ;
        }

        if (t7>80){
            T7 = "<td bgcolor=\"#FF0000\">"+t7+"&deg;C</td>" ;
        }else{
            T7 = "<td>"+t7+"&deg;C</td>" ;
        }
        if (t8>80){
            T8 = "<td bgcolor=\"#FF0000\">"+t8+"&deg;C</td>" ;
        }else{
            T8 = "<td>"+t8+"&deg;C</td>" ;
        }

        if (t9>80){
            T9 = "<td bgcolor=\"#FF0000\">"+t9+"&deg;C</td>" ;
        }else{
            T9 = "<td>"+t9+"&deg;C</td>" ;
        }

        if (t10>80){
            T10 = "<td bgcolor=\"#FF0000\">"+t10+"&deg;C</td>" ;
        }else{
            T10 = "<td>"+t10+"&deg;C</td>" ;
        }
        if (t11>80){
            T11 = "<td bgcolor=\"#FF0000\">"+t11+"&deg;C</td>" ;
        }else{
            T11 = "<td>"+t11+"&deg;C</td>" ;
        }

        if (t12>80){
            T12 = "<td bgcolor=\"#FF0000\">"+t12+"&deg;C</td>" ;
        }else{
            T12 = "<td>"+t12+"&deg;C</td>" ;
        }

        if (t13>80){
            T13 = "<td bgcolor=\"#FF0000\">"+t13+"&deg;C</td>" ;
        }else{
            T13 = "<td>"+t13+"&deg;C</td>" ;
        }


        if (b1 == 0){
            B1 = "<td>B1</td><td bgcolor=\"#FF0000\">OFF</td>" ;
        }else{
            B1 = "<td>B1</td>    <td bgcolor=\"#00FF00\">ON</td>" ;
        }
        if (b2 == 0){
            B2 = "<td>B2</td><td bgcolor=\"#FF0000\">OFF</td>" ;
        }else{
            B2 = "<td>B2</td>    <td bgcolor=\"#00FF00\">ON</td>" ;
        }
        if (b3 == 0){
            B3 = "<td>B3</td><td bgcolor=\"#FF0000\">OFF</td>" ;
        }else{
            B3 = "<td>B3</td>    <td bgcolor=\"#00FF00\">ON</td>" ;
        }

        if (b4 == 0){
            B4 = "<td>B4</td><td bgcolor=\"#FF0000\">OFF</td>" ;
        }else{
            B4 = "<td>B4</td>    <td bgcolor=\"#00FF00\">ON</td>" ;
        }

        if (b5 == 0){
            B5 = "<td>B5</td><td bgcolor=\"#FF0000\">OFF</td>" ;
        }else{
            B5 = "<td>B5</td>    <td bgcolor=\"#00FF00\">ON</td>" ;
        }


        if (v31==0){
            V31 = "<td>V31</td><td bgcolor=\"#FF0000\">OFF</td>" ;
        }else{
            V31 = "<td>V31</td>    <td bgcolor=\"#00FF00\">ON</td>";
        }

        if (v32==0){
            V32 = "<td>V32</td><td bgcolor=\"#FF0000\">OFF</td>" ;
        }else{
            V32 = "<td>V32</td>    <td bgcolor=\"#00FF00\">ON</td>";
        }

        if (v33==0){
            V33 = "<td>V33</td><td bgcolor=\"#FF0000\">OFF</td>" ;
        }else{
            V33 = "<td>V33</td>    <td bgcolor=\"#00FF00\">ON</td>";
        }

        if (v34==0){
            V34 = "<td>V34</td><td bgcolor=\"#FF0000\">OFF</td>" ;
        }else{
            V34 = "<td>V34</td>    <td bgcolor=\"#00FF00\">ON</td>";
        }

        if (v35==0){
            V35 = "<td>V35</td><td bgcolor=\"#FF0000\">OFF</td>" ;
        }else{
            V35 = "<td>V35</td>    <td bgcolor=\"#00FF00\">ON</td>";
        }

        if (v36==0){
            V36 = "<td>V36</td><td bgcolor=\"#FF0000\">OFF</td>" ;
        }else{
            V36 = "<td>V36</td>    <td bgcolor=\"#00FF00\">ON</td>";
        }

        if (v37==0){
            V37 = "<td>V37</td><td bgcolor=\"#FF0000\">OFF</td>" ;
        }else{
            V37 = "<td>V37</td>    <td bgcolor=\"#00FF00\">ON</td>";
        }

        if (v38==0){
            V38 = "<td>V38</td><td bgcolor=\"#FF0000\">OFF</td>" ;
        }else{
            V38 = "<td>V38</td>    <td bgcolor=\"#00FF00\">ON</td>";
        }

        if (v39==0){
            V39 = "<td>V39</td><td bgcolor=\"#FF0000\">OFF</td>" ;
        }else{
            V39 = "<td>V39</td>    <td bgcolor=\"#00FF00\">ON</td>";
        }

        if (vma1==0){
            VMA1 = "<td>VMA1</td><td bgcolor=\"#FF0000\">OFF</td>" ;
        }else{
            VMA1 = "<td>VMA1</td>    <td bgcolor=\"#00FF00\">ON</td>";
        }

        if (vmc1==0){
            VMC1 = "<td>VMC1</td><td bgcolor=\"#FF0000\">OFF</td>" ;
        }else{
            VMC1 = "<td>VMC1</td>    <td bgcolor=\"#00FF00\">ON</td>";
        }

        if (vmc2==0){
            VMC2 = "<td>VMC2</td><td bgcolor=\"#FF0000\">OFF</td>" ;
        }else{
            VMC2 = "<td>VMC2</td>    <td bgcolor=\"#00FF00\">ON</td>";
        }

        if (vmc3==0){
            VMC3 = "<td>VMC3</td><td bgcolor=\"#FF0000\">OFF</td>" ;
        }else{
            VMC3 = "<td>VMC3</td>    <td bgcolor=\"#00FF00\">ON</td>";
        }

        if (vmc4==0){
            VMC4 = "<td>VMC4</td><td bgcolor=\"#FF0000\">OFF</td>" ;
        }else{
            VMC4 = "<td>VMC4</td>    <td bgcolor=\"#00FF00\">ON</td>";
        }

        if (vmc5==0){
            VMC5 = "<td>VMC5</td><td bgcolor=\"#FF0000\">OFF</td>" ;
        }else{
            VMC5 = "<td>VMC5</td>    <td bgcolor=\"#00FF00\">ON</td>";
        }

        if (vmzm1==0){
            VMZM1 = "<td>VMZM1</td><td bgcolor=\"#FF0000\">OFF</td>" ;
        }else{
            VMZM1 = "<td>VMZM1</td>    <td bgcolor=\"#00FF00\">ON</td>";
        }

        if (q1==0){
            Q1 = "<td>Q1</td><td bgcolor=\"#FF0000\">OFF</td>" ;
        }else{
            Q1 = "<td>Q1</td>    <td bgcolor=\"#00FF00\">ON</td>";
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
               // "<div style=\"padding: 0px; float: left; width: 40%; text-align: justify;\">"+
                "<H1 align=\"center\"> Temperaturas Tiempo Real </H1>"+
                "\n" +
                "<table class=\"names\">\n" +
                /*"  <tr>\n" +
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
                "  <tr>\n" + T13 +*/
                "<TR><TH>Sensor</TH>"+
                "<TD>T1</TD> <TD>T2</TD> <TD>T3</TD><TD>T4</TD> <TD>T5</TD> <TD>T6</TD><TD>T7</TD> <TD>T8</TD> <TD>T9</TD><TD>T10</TD> <TD>T11</TD> <TD>T12</TD><TD>T13</TD></TR>"+
                "<TR><TH>Temperatura</TH>"+
                T1+T2+T3+T4+T5+T6+T7+T8+T9+T10+T11+T12+T13+"</TR>"+
                "</table>\n" +
                "\n" +
                //"</div>"+
                //"<div style=\"padding: 0px; float: right; width: 60%; text-align: justify;\">"+
                "<H1 align=\"center\" >Estado bombas y valvulas</H1>"+
                "\n"+
                //"<H3 float: center;>Energia Solar</H3>"+
                "<table style=\"float: left; \"class=\"names\">" +
                "<table style=\"float: left; \"class=\"names\">" +
                "<TR>"+
                "<TH COLSPAN=2>Energia solar</TH>"+
                "<TH COLSPAN=4>Calefaccion</TH>"+
                "<TH COLSPAN=2>Agua Caliente</TH>"+
                "</TR>"+
                "<TR>"+
                "<TH>Bombas/Valvulas</TH> <TH>Estado</TH>"+
                "<TH>Bombas/Valvulas</TH> <TH>Estado</TH>"+
                "<TH>Bombas/Valvulas</TH> <TH>Estado</TH>"+
                "<TH>Bombas/Valvulas</TH> <TH>Estado</TH>"+
                "</TR>"+
                "<TR>"+
                B1+
                VMZM1+
                VMC2+
                B4+
                "</TR>"+
                "<TR>"+
                V31+
                V32+
                VMC4+
                VMC5+
                "</TR>"+
                "<tr>"+
                V38+
                V36+
                B2+
                VMA1+
                "</tr>"+
                "<tr>"+
                V37+
                B5+
                V33+
                "<TD></TD><TD></TD>"+
                "</tr>"+
                "<tr>"+
                V35+
                B3+
                VMC1+
                "<TD></TD><TD></TD>"+
                "</tr>"+
                "<tr>"+
                V39+
                V34+
                VMC3+
                "<TD></TD><TD></TD>"+
                "</tr>"+
                "<TR><tD></td><tD></td>"+Q1+"<tD></td><TD></TD><tD></td></tr>"+

                "</TABLE>"+
                //"</div>"+
                "</body>\n" +
                " <META HTTP-EQUIV=\"REFRESH\" CONTENT=\"5 ;URL=index\">"+
                "</html>";
    }

    public static void Checksum_data(String file){
        String route = "/home/david/";

        try {
            MessageDigest md = MessageDigest.getInstance("SHA1");
            FileInputStream fis = new FileInputStream(route+file);
            byte[] dataBytes = new byte[1024];

            int nread = 0;

            while ((nread = fis.read(dataBytes)) != -1) {
                md.update(dataBytes, 0, nread);
            }
            ;

            byte[] mdbytes = md.digest();

            //convert the byte to hex format
            StringBuffer sb = new StringBuffer("");
            for (int i = 0; i < mdbytes.length; i++) {
                sb.append(Integer.toString((mdbytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            System.out.println("Digest(in hex format):: " + sb.toString());

        }catch(Exception e){
            System.out.println(e);
        }
    }
}
