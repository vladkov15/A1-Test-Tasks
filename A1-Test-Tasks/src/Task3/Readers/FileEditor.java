package Task3.Readers;

import Task3.Entity.Logins;
import Task3.Entity.Postings;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileEditor {


    public List<Logins> ReadLogin(String csvFile, String cvsSplitBy ) throws FileNotFoundException {
        BufferedReader br = null;
        String line = "";
        List<Logins> listOfLogins = new ArrayList<>();



        try {

            br = new BufferedReader(new FileReader(csvFile));
            int index = 0;
            while ((line = br.readLine()) != null) {
                index += 1;
                if(!line.isEmpty() && index > 0){
                    String[] logins = line.split(cvsSplitBy);
                        listOfLogins.add(new Logins(logins[0],logins[1],Boolean.parseBoolean(logins[2]),logins[3],logins[4]));
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return listOfLogins;
    }

    public void Start() throws IOException {
        FileWriter file_writer = null;
        List<Logins> listOfLogins = new ArrayList<>(ReadLogin("data/logins.csv", ","));
        List<Postings> listOfPostings = new ArrayList<>(ReadPostings("data/postings.csv",";"));

        for (int i = 0; i < listOfPostings.size(); i++) {
            for (int j = 0; j <listOfLogins.size() ; j++) {
                if (listOfPostings.get(i).getUser_Name() == listOfLogins.get(j).getAppAccountName() && listOfLogins.get(j).isActive()){
                  listOfPostings.get(i).setAuthorized_Delivery("true");
                    System.out.println("yes");
                }
            }
        }

            file_writer = new FileWriter("data/postings.csv");
            file_writer.write("Mat. Doc.;\tItem;\tDoc. Date;\tPstng Date;\tMaterial Description;\tQuantity;\tBUn;\tAmount LC;\tCrcy;\tUser Name;\tAuthorized Delivery\n");
            if(file_writer != null) file_writer.close();
        file_writer = new FileWriter("data/postings.csv", true);
        for (int i = 0; i < listOfPostings.size(); i++) {
            file_writer.write( listOfPostings.get(i).toString());
        }
        if(file_writer != null) file_writer.close();








    }

    public List<Postings> ReadPostings(String csvFile, String cvsSplitBy ) throws FileNotFoundException {
        BufferedReader br = null;
        String line = "";
        List<Postings> listOfPostings = new ArrayList<>();



        try {

            br = new BufferedReader(new FileReader(csvFile));
            int index = 0;
            while ((line = br.readLine()) != null) {
                if(!line.isEmpty() && index > 0){
                    String[] posts = line.split(cvsSplitBy);
                    listOfPostings.add(new Postings(posts[0],posts[1],posts[2],posts[3],
                            posts[4],posts[5],
                            posts[6],posts[7],posts[8],posts[9]));
                }
                index += 1;
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return listOfPostings;
    }

}
