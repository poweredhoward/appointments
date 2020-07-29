package Model;

public class Consultant {
//    id, name, password, username
    private int id;
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id = id;
    }

    public Consultant(int id, String username, String password){
        this.id = id;
        this.username = username;
        this.password = password;
    }
}
