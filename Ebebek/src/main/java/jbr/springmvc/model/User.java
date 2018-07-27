package jbr.springmvc.model;

public class User {

  private String username;
  private String password;
  private String email;
  private String conf_pass;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    System.out.println("username: " + username);
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getConf_pass() {
    return conf_pass;
  }

  public void setConf_pass(String conf_pass) {
    this.conf_pass = conf_pass;
  }
}
