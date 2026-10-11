package model;

import java.util.List;

public class User {

    private int id;
    private   String name;
    private  String email;
    private List<String> mobileNumbers;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }

    public List<String> getMobileNumbers() {
        return mobileNumbers;
    }

    public void setMobileNumbers(List<String> mobileNumbers) {
        this.mobileNumbers = mobileNumbers;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public User() {
    }


    public User ( int id, String name, String email, List<String> mobileNumbers){
        this.id = id ;
      this.name = name ;
      this.email = email;
      this.mobileNumbers= mobileNumbers;


}

   public  String getName (){
    return name;
   }
   public  void  setName ( String name){
         this.name = name;
   }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", mobileNumbers=" + mobileNumbers +
                '}';
    }
}
