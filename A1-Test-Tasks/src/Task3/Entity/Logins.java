package Task3.Entity;

public class Logins {

    private String Application;
    private String AppAccountName;
    private boolean IsActive;
    private String JobTitle;
    private String Department;

    public Logins(String application, String appAccountName, boolean isActive, String jobTitle, String department) {
        Application = application;
        AppAccountName = appAccountName;
        IsActive = isActive;
        JobTitle = jobTitle;
        Department = department;
    }

    public String getApplication() {
        return Application;
    }

    public void setApplication(String application) {
        Application = application;
    }

    public String getAppAccountName() {
        return AppAccountName;
    }

    public void setAppAccountName(String appAccountName) {
        AppAccountName = appAccountName;
    }

    public boolean isActive() {
        return IsActive;
    }

    public void setActive(boolean active) {
        IsActive = active;
    }

    public String getJobTitle() {
        return JobTitle;
    }

    public void setJobTitle(String jobTitle) {
        JobTitle = jobTitle;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    public String ToString(){
        return getApplication()+ ";" + getAppAccountName()+ ";" + isActive()+ ";" + getJobTitle()+ ";" + getDepartment();
    }
}
