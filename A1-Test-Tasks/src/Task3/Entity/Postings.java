package Task3.Entity;

public class Postings {

    private String Mat_Doc;
    private String Item;
    private String Doc_Date;
    private String Pstng_Date;
    private String Material_Description;
    private String Quantity;
    private String BUn;
    private String Amount_LC;
    private String Crcy;
    private String User_Name;
    private String Authorized_Delivery;

    public Postings(String mat_Doc, String item, String doc_Date, String pstng_Date, String material_Description, String quantity, String BUn, String amount_LC, String crcy, String user_Name) {
        Mat_Doc = mat_Doc;
        Item = item;
        Doc_Date = doc_Date;
        Pstng_Date = pstng_Date;
        Material_Description = material_Description;
        Quantity = quantity;
        this.BUn = BUn;
        Amount_LC = amount_LC;
        Crcy = crcy;
        User_Name = user_Name;
        Authorized_Delivery = "false";
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

    public String getDoc_Date() {
        return Doc_Date;
    }

    public void setDoc_Date(String doc_Date) {
        Doc_Date = doc_Date;
    }

    public String getPstng_Date() {
        return Pstng_Date;
    }

    public void setPstng_Date(String pstng_Date) {
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
        return Mat_Doc + ";\t" + Item + ";\t" + Doc_Date + ";\t"  + Pstng_Date + ";\t"
                + Material_Description + ";\t" + Quantity + ";\t" + BUn + ";\t" + Amount_LC + ";\t" + Crcy + ";\t"
                + User_Name + ";\t" + Authorized_Delivery + ";\t\n";

    }
}

