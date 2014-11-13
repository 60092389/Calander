package DTO;

import java.io.Serializable;

public class User implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String n_id;
	private String u_id;
	private String name;
	private String password;
	
	public String getN_id() {return n_id;}
	public void setN_id(String n_id) {this.n_id = n_id;}
	public String getU_id() {return u_id;}
	public void setU_id(String u_id) {this.u_id = u_id;}
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	public String getPassword() {return password;}
	public void setPassword(String password) {this.password = password;}	
}
