package Task3.Readers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FilesRider {


    public void start(String csvFile, String cvsSplitBy, int column ) throws FileNotFoundException {
        BufferedReader br = null;
        String line = "";



        try {
            if(column == -1)
            {
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
            }else{
                br = new BufferedReader(new FileReader(csvFile));
                while ((line = br.readLine()) != null) {

                    if(!line.isEmpty()){
                        String[] array = line.split(cvsSplitBy);

                            System.out.print(array[column]);

                        System.out.println();
                    }
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

