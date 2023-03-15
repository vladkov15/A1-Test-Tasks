package Task3.Entity;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Postings {

    private String Mat_Doc;
    private String Item;
    private Date Doc_Date;
    private  Date Pstng_Date;
    private String Material_Description;
    private String Quantity;
    private String BUn;
    private String Amount_LC;
    private String Crcy;
    private String User_Name;
    private String Authorized_Delivery;

    public Postings(String mat_Doc, String item, String doc_Date, String pstng_Date, String material_Description, String quantity, String BUn, String amount_LC, String crcy, String user_Name, String authorized_delivery) {
        Mat_Doc = mat_Doc;
        Item = item;
        DateTimeFormatter f = DateTimeFormatter.ofPattern( "dd.MM.uuuu" ) ;
        LocalDate localDate = LocalDate.parse( doc_Date , f ) ;
        Doc_Date = Date.valueOf(localDate);
        localDate = LocalDate.parse(pstng_Date , f);
        Pstng_Date = Date.valueOf(localDate);
        Material_Description = material_Description;
        Quantity = quantity;
        this.BUn = BUn;
        Amount_LC = amount_LC;
        Crcy = crcy;
        User_Name = user_Name;
        Authorized_Delivery = authorized_delivery;
    }

    public String getMat_Doc() {
        return Mat_Doc;
    }

    public void setMat_Doc(String mat_Doc) {
        Mat_Doc = mat_Doc;
    }

    public String getItem() {
        return Item;
    }

    public void setItem(String item) {
        Item = item;
    }

    public Date getDoc_Date() {
        return Doc_Date;
    }

    public void setDoc_Date(Date doc_Date) {
        Doc_Date = doc_Date;
    }

    public Date getPstng_Date() {
        return Pstng_Date;
    }

    public void setPstng_Date(Date pstng_Date) {
        Pstng_Date = pstng_Date;
    }

    public String getMaterial_Description() {
        return Material_Description;
    }

    public void setMaterial_Description(String material_Description) {
        Material_Description = material_Description;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getBUn() {
        return BUn;
    }

    public void setBUn(String BUn) {
        this.BUn = BUn;
    }

    public String getAmount_LC() {
        return Amount_LC;
    }

    public void setAmount_LC(String amount_LC) {
        Amount_LC = amount_LC;
    }

    public String getCrcy() {
        return Crcy;
    }

    public void setCrcy(String crcy) {
        Crcy = crcy;
    }

    public String getUser_Name() {
        return User_Name;
    }

    public void setUser_Name(String user_Name) {
        User_Name = user_Name;
    }

    public String getAuthorized_Delivery() {
        return Authorized_Delivery;
    }

    public void setAuthorized_Delivery(String authorized_Delivery) {
        Authorized_Delivery = authorized_Delivery;
    }

    @Override
    public String toString() {
        return Mat_Doc + ";" + Item + ";" + Doc_Date + ";"  + Pstng_Date + ";"
                + Material_Description + ";" + Quantity + ";" + BUn + ";" + Amount_LC + ";" + Crcy + ";"
                + User_Name + ";" + Authorized_Delivery+"\n";

    }


}

