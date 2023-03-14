package Task3.Readers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FilesRider {


    public void Start(String csvFile, String cvsSplitBy ) throws FileNotFoundException {
        BufferedReader br = null;
        String line = "";



        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {

                if(!line.isEmpty()){
                    String[] array = line.split(cvsSplitBy);
                    for (int i = 0; i <array.length ; i++) {
                        System.out.print(array[i]);
                    }
                    System.out.println();
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

    }


}

