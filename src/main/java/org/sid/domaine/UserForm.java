package org.sid.domaine;

public class UserForm {

    private String userName;
    private String passWord;
    private String passWordConfirmed;

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public String getPassWordConfirmed() {
        return passWordConfirmed;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setPassWordConfirmed(String passWordConfirmed) {
        this.passWordConfirmed = passWordConfirmed;
    }
}
