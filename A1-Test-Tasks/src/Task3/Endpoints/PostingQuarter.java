package Task3.Endpoints;

import Task3.DB.ConnectionToMySQL;
import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class PostingQuarter  extends BaseEndpoint implements HttpHandler {
    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String requestParamValue = null;
        Gson gson = new Gson();
        String requestURI = httpExchange.getRequestURI().toString();
        System.out.println(requestURI);

        System.out.println(httpExchange.getRequestMethod());
        if ("GET".equals(httpExchange.getRequestMethod())) {
            System.out.println("task3-6/year/quarter: GET handled");


            ConnectionToMySQL db = new ConnectionToMySQL();
            try {
//                System.out.println(requestURI.split("/")[3]);
//                String Response = gson.toJson(db.quarterSelect(requestURI.split("/")[3]));
//                System.out.println(Response);//тут можно убрать ограничение в кол-во записей
//                //добавил лишь только потому что при проверке в postman, когда отправляешь больше 4 элементов, выдает ошибку в postman.
//                handleResponse(httpExchange, Response);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("task3-6: Nothing handled");
        }

    }

    private void handleResponse(HttpExchange httpExchange, String requestParamValue)  throws  IOException {
        OutputStream outputStream = httpExchange.getResponseBody();
        String Response = requestParamValue;
        super.setHttpExchangeResponseHeaders(httpExchange);
        httpExchange.sendResponseHeaders(200, Response.length());
        outputStream.write(Response.getBytes("UTF-8"));
        outputStream.flush();
        outputStream.close();
    }
}
