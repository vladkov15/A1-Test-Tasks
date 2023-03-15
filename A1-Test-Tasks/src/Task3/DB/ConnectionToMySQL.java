package Task3.DB;


import Task3.Entity.Logins;
import Task3.Entity.Postings;
import Task3.Readers.FileEditor;

import java.io.FileNotFoundException;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ConnectionToMySQL {
    private Connection connect = null;
    private Statement statement = null;
    private PreparedStatement preparedStatement = null;
    private ResultSet resultSet = null;

    List<Logins> listOfLogins = new ArrayList<>(new FileEditor().readLogin("data/logins.csv", ","));
    List<Postings> listOfPostings = new ArrayList<>(new FileEditor().readPostings("data/postings.csv",";"));

    public ConnectionToMySQL() throws FileNotFoundException {

    }


    public Connection connectDataBase() throws Exception {
        try {

            String url = "jdbc:mysql://localhost:3306/a1";
            String userName = "root";
            String password = "14082001vlad";

            Class.forName("com.mysql.cj.jdbc.Driver");

            connect = DriverManager
                    .getConnection(url, userName, password);
            statement = connect.createStatement();


        } catch (Exception e) {
            throw e;
        }
        return connect;
    }


    public void insertLogins(Connection connect) throws SQLException {
        for (int i = 0; i < listOfLogins.size() ; i++) {
            preparedStatement = connect
                    .prepareStatement("insert into logins values ( ?,?,?,?,?)");
            preparedStatement.setString(1, listOfLogins.get(i).getApplication());
            preparedStatement.setString(2, listOfLogins.get(i).getAppAccountName());
            preparedStatement.setBoolean(3, listOfLogins.get(i).isActive());
            preparedStatement.setString(4, listOfLogins.get(i).getJobTitle());
            preparedStatement.setString(5, listOfLogins.get(i).getDepartment());
            preparedStatement.executeUpdate();
        }

        close();

    }

    public void insertPostings(Connection connect) throws SQLException {

        for (int i = 0; i < listOfPostings.size() ; i++) {

            preparedStatement = connect
                    .prepareStatement("insert into postings values ( ?,?,?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, listOfPostings.get(i).getMat_Doc());
            preparedStatement.setString(2,  listOfPostings.get(i).getItem());
            DateTimeFormatter form = DateTimeFormatter.ofPattern( "dd.MM.uuuu" );
            LocalDate localDateDoc = LocalDate.parse( listOfPostings.get(i).getDoc_Date(), form);
            preparedStatement.setDate(3,  Date.valueOf(localDateDoc));
            LocalDate localDatePstng = LocalDate.parse( listOfPostings.get(i).getPstng_Date(), form);
            preparedStatement.setDate(4,  Date.valueOf(localDatePstng));
            preparedStatement.setString(5, listOfPostings.get(i).getMaterial_Description());
            preparedStatement.setString(6, listOfPostings.get(i).getQuantity());
            preparedStatement.setString(7, listOfPostings.get(i).getBUn());
            preparedStatement.setString(8, listOfPostings.get(i).getAmount_LC());
            preparedStatement.setString(9, listOfPostings.get(i).getCrcy());
            preparedStatement.setString(10, listOfPostings.get(i).getUser_Name());
            preparedStatement.setBoolean(11, Boolean.parseBoolean(listOfPostings.get(i).getAuthorized_Delivery()));
            preparedStatement.executeUpdate();
        }

        close();
    }

    public void writeLogin(Connection connect) throws SQLException {
        statement = connect.createStatement();
        resultSet = statement
                .executeQuery("select * from logins");

        while (resultSet.next()) {

            String app = resultSet.getString("Application");
            String name = resultSet.getString("AppAccountName");
            boolean active = resultSet.getBoolean("IsActive");
            String job = resultSet.getString("JobTitle");
            String department = resultSet.getString("Department");
            System.out.println(new Logins(app, name, active, job, department).ToString());
        }
    }

    public List<Postings> writePostings(Connection connect) throws SQLException {
        List<Postings> postingsList = new ArrayList<>();
        statement = connect.createStatement();
        resultSet = statement
                .executeQuery("select * from postings");

        while (resultSet.next()) {

            String mat_doc = resultSet.getString("Mat. Doc.");
            String item = resultSet.getString("Item");
            String doc_date = resultSet.getString("Doc. Date");
            String pstng_date = resultSet.getString("Pstng Date");
            String material_description = resultSet.getString("Material Description");
            String quantity = resultSet.getString("Quantity");
            String BUn = resultSet.getString("BUn");
            String amount_lc = resultSet.getString("Amount LC");
            String crcy = resultSet.getString("Crcy");
            String user_name = resultSet.getString("User Name");
            String authorized_delivery = String.valueOf(resultSet.getBoolean("Authorized Delivery"));

            postingsList.add(new Postings(mat_doc,item,doc_date,pstng_date,material_description,quantity,BUn,amount_lc,crcy,user_name,authorized_delivery));
        }

        return postingsList;
    }


    public List<Postings> yearSelect(Connection connect, String year) throws Exception {
        List<Postings> resultList = new ArrayList<>();

        statement = connect.createStatement();

        resultSet = statement
                .executeQuery("select * from a1.postings where year(DocDate) in ('"+year+"')");


        while (resultSet.next()) {

            String mat_doc = resultSet.getString("MatDoc");
            String item = resultSet.getString("Item");
            String doc_date = resultSet.getString("DocDate");
            String pstng_date = resultSet.getString("PstngDate");
            String material_description = resultSet.getString("MaterialDescription");
            String quantity = resultSet.getString("Quantity");
            String BUn = resultSet.getString("BUn");
            String amount_lc = resultSet.getString("AmountLC");
            String crcy = resultSet.getString("Crcy");
            String user_name = resultSet.getString("UserName");
            String authorized_delivery = String.valueOf(resultSet.getBoolean("AuthorizedDelivery"));
            System.out.println(mat_doc+item+doc_date+pstng_date+material_description+quantity+BUn+amount_lc+crcy+user_name+authorized_delivery);
            resultList.add(new Postings(mat_doc,item,doc_date,pstng_date,material_description,quantity,BUn,amount_lc,crcy,user_name,authorized_delivery));

        }

        close();

        return resultList;
    }

    public List<Postings> quarterSelect(Connection connect, String quarter) throws Exception {
        String monthEnd = String.valueOf(Integer.parseInt(quarter)*3);
        String monthStart = String.valueOf(Integer.parseInt(quarter)*3-2);

        System.out.println("select * from a1.postings where DocDate between '2020-0"+monthStart+"-01' and '2020-0"+monthEnd+"-30' ");

        List<Postings> resultList = new ArrayList<>();

        statement = connect.createStatement();

        resultSet = statement
                .executeQuery("select * from a1.postings where DocDate between '2020-0"+monthStart+"-01' and '2020-0"+monthEnd+"-30' ");


        while (resultSet.next()) {

            String mat_doc = resultSet.getString("MatDoc");
            String item = resultSet.getString("Item");
            String doc_date = resultSet.getString("DocDate");
            String pstng_date = resultSet.getString("PstngDate");
            String material_description = resultSet.getString("MaterialDescription");
            String quantity = resultSet.getString("Quantity");
            String BUn = resultSet.getString("BUn");
            String amount_lc = resultSet.getString("AmountLC");
            String crcy = resultSet.getString("Crcy");
            String user_name = resultSet.getString("UserName");
            String authorized_delivery = String.valueOf(resultSet.getBoolean("AuthorizedDelivery"));
            System.out.println(mat_doc+item+doc_date+pstng_date+material_description+quantity+BUn+amount_lc+crcy+user_name+authorized_delivery);
            resultList.add(new Postings(mat_doc,item,doc_date,pstng_date,material_description,quantity,BUn,amount_lc,crcy,user_name,authorized_delivery));

        }
        close();

        return resultList;
    }

    public List<Postings> monthSelect(Connection connect, String month) throws Exception {

        List<Postings> resultList = new ArrayList<>();
        statement = connect.createStatement();
        String sql = null;
        if (Integer.parseInt(month) <= 9){
            sql = "select * from a1.postings where month(DocDate) in ('0"+month+"')";
        }else {
            sql = "select * from a1.postings where month(DocDate) in ('"+month+"')";
        }
        resultSet = statement
                .executeQuery(sql);


        while (resultSet.next()) {

            String mat_doc = resultSet.getString("MatDoc");
            String item = resultSet.getString("Item");
            String doc_date = resultSet.getString("DocDate");
            String pstng_date = resultSet.getString("PstngDate");
            String material_description = resultSet.getString("MaterialDescription");
            String quantity = resultSet.getString("Quantity");
            String BUn = resultSet.getString("BUn");
            String amount_lc = resultSet.getString("AmountLC");
            String crcy = resultSet.getString("Crcy");
            String user_name = resultSet.getString("UserName");
            String authorized_delivery = String.valueOf(resultSet.getBoolean("AuthorizedDelivery"));
            System.out.println(mat_doc+item+doc_date+pstng_date+material_description+quantity+BUn+amount_lc+crcy+user_name+authorized_delivery);
            resultList.add(new Postings(mat_doc,item,doc_date,pstng_date,material_description,quantity,BUn,amount_lc,crcy,user_name,authorized_delivery));

        }
        close();

        return resultList;
    }

    public List<Postings> daySelect(Connection connect, String day) throws Exception {

        List<Postings> resultList = new ArrayList<>();
        statement = connect.createStatement();
        String sql = null;
        if (Integer.parseInt(day) <= 9){
            sql = "select * from a1.postings where day(DocDate) in ('0"+day+"')";
        }else {
            sql = "select * from a1.postings where day(DocDate) in ('"+day+"')";
        }
        resultSet = statement
                .executeQuery(sql);


        while (resultSet.next()) {

            String mat_doc = resultSet.getString("MatDoc");
            String item = resultSet.getString("Item");
            String doc_date = resultSet.getString("DocDate");
            String pstng_date = resultSet.getString("PstngDate");
            String material_description = resultSet.getString("MaterialDescription");
            String quantity = resultSet.getString("Quantity");
            String BUn = resultSet.getString("BUn");
            String amount_lc = resultSet.getString("AmountLC");
            String crcy = resultSet.getString("Crcy");
            String user_name = resultSet.getString("UserName");
            String authorized_delivery = String.valueOf(resultSet.getBoolean("AuthorizedDelivery"));
            System.out.println(mat_doc+item+doc_date+pstng_date+material_description+quantity+BUn+amount_lc+crcy+user_name+authorized_delivery);
            resultList.add(new Postings(mat_doc,item,doc_date,pstng_date,material_description,quantity,BUn,amount_lc,crcy,user_name,authorized_delivery));

        }
        close();

        return resultList;
    }




    private void close() {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

            if (statement != null) {
                statement.close();
            }

            if (connect != null) {
                connect.close();
            }
        } catch (Exception e) {

        }
    }
}
