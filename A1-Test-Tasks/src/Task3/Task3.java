package Task3;


import Task3.DB.ConnectionToMySQL;
import Task3.Endpoints.PostingMonth;
import Task3.Endpoints.PostingQuarter;
import Task3.Endpoints.PostingYear;
import Task3.Endpoints.PostingDay;
import Task3.Readers.FileEditor;
import Task3.Readers.FilesRider;
import com.sun.net.httpserver.HttpContext;
import com.sun.net.httpserver.HttpServer;


import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class Task3 {

    public static void main(String[] args) throws Exception {
        //1. Прочитать файл logins.csv с локальной файловой системы
        FilesRider fr = new FilesRider();
        fr.start("data/logins.csv", ",", -1);

        System.out.println("-------------------------------------------------------------------------------");

        //2. Прочитать файл postings.csv с локальной файловой системы (строки со значениями в поле Mat. Doc.)
        fr.start("data/postings.csv",";",0);


        //3. Добавить булевое поле "авторизованная поставка" в данные из postings.csv, которое будет указывать, что
        // User Name (postings.csv) находится в списке AppAccountName (logins.csv) и IsActive
        FileEditor fe = new FileEditor();
        fe.start();


        //4. Cохранить в SQL СУБД данные файла logins.csv
        ConnectionToMySQL db = new ConnectionToMySQL();
        db.insertLogins(db.connectDataBase());

        //5. 5. Сохранить в SQL СУБД данные файла postings.csv (с дополнительным полем)
        db.insertPostings(db.connectDataBase());

//        6. Отдавать по GET (REST API) за период (день, месяц, квартал, год) данные из базы, загруженные из postings.csv \
//        (с возможностью запроса с фильтром по полю "авторизованная поставка")
        HttpServer server = HttpServer.create();
        server.bind(new InetSocketAddress(38889), 0);
        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
        HttpContext context = server.createContext("/api/task3-6/year/", new PostingYear());
        context = server.createContext("/api/task3-6/quarter/", new PostingQuarter());
        context = server.createContext("/api/task3-6/month/", new PostingMonth());
        context = server.createContext("/api/task3-6/day/", new PostingDay());
        //for example: http://localhost:38889/api/task3-6/day/25 without filter
        //or
        //http://localhost:38889/api/task3-6/day/25/authorized-delivery/(true | false)
        server.setExecutor(threadPoolExecutor);
        server.start();
        System.out.println("Server started");

    }
}
