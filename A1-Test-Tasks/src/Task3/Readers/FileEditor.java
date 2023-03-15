package Task3.Readers;

import Task3.Entity.Logins;
import Task3.Entity.Postings;

import java.io.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileEditor {


    public List<Logins> readLogin(String csvFile, String cvsSplitBy ) throws FileNotFoundException {
        BufferedReader br = null;
        String line = "";
        List<Logins> listOfLogins = new ArrayList<>();



        try {

            br = new BufferedReader(new FileReader(csvFile));
            int index = 0;
            while ((line = br.readLine()) != null) {
                if(!line.isEmpty() && index > 0) {
                    String[] logins = line.split(cvsSplitBy);

                    listOfLogins.add(new Logins(logins[0], logins[1], Boolean.valueOf(logins[2].trim()), logins[3], logins[4]));
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

        return listOfLogins;
    }

    public void start() throws IOException {
        FileWriter file_writer = null;
        List<Logins> listOfLogins = new ArrayList<>(readLogin("data/logins.csv", ","));
        List<Postings> listOfPostings = new ArrayList<>(readPostings("data/postings.csv",";"));

        for (int i = 0; i < listOfPostings.size(); i++) {
            for (int j = 0; j <listOfLogins.size() ; j++) {
                if (Objects.equals(listOfPostings.get(i).getUser_Name(), listOfLogins.get(j).getAppAccountName()) && listOfLogins.get(j).isActive()){
                  listOfPostings.get(i).setAuthorized_Delivery("true");
                    System.out.println("yes");
                }
            }
        }

            file_writer = new FileWriter("data/postings.csv");
            file_writer.write("Mat. Doc.;\tItem;\tDoc. Date;\tPstng Date;\tMaterial Description;\tQuantity;\tBUn;\tAmount LC;\tCrcy;\tUser Name;\tAuthorized Delivery\n");
        file_writer.close();
        file_writer = new FileWriter("data/postings.csv", true);
        for (int i = 0; i < listOfPostings.size(); i++) {
            file_writer.write( listOfPostings.get(i).toString());
        }
        if(file_writer != null) file_writer.close();








    }

    public List<Postings> readPostings(String csvFile, String cvsSplitBy ) throws FileNotFoundException {
        BufferedReader br = null;
        String line = "";
        List<Postings> listOfPostings = new ArrayList<>();



        try {
            br = new BufferedReader(new FileReader(csvFile));

            int index = 0;
            while ((line = br.readLine()) != null) {
                if(!line.isEmpty() && index > 0){
                    String[] posts = line.split(cvsSplitBy);
                    listOfPostings.add(new Postings(posts[0].trim(),posts[1].trim(), posts[2].trim(),posts[3].trim(),
                            posts[4].trim(),posts[5].trim(),
                            posts[6].trim(),posts[7].trim(),posts[8].trim(),posts[9].trim(), "false"));

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
