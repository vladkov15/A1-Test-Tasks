package Task3;


import Task3.Readers.FileEditor;
import Task3.Readers.FilesRider;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Task3 {

    public static void main(String[] args) throws IOException {
        //1. Прочитать файл logins.csv с локальной файловой системы
//        FilesRider fr = new FilesRider();
//        fr.Start("data/logins.csv", ",");

//        System.out.println("---------------------------------------------------------------------------------------");
        //2. Прочитать файл postings.csv с локальной файловой системы (строки со значениями в поле Mat. Doc.)
//        fr.Start("data/postings.csv",";");


        //3. Добавить булевое поле "авторизованная поставка" в данные из postings.csv, которое будет указывать, что
        // User Name (postings.csv) находится в списке AppAccountName (logins.csv) и IsActive
        FileEditor fe = new FileEditor();
        fe.Start();

    }
}
