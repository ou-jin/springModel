package sys.domain;

import com.mysql.fabric.xmlrpc.base.Data;

public class sysUserInfo {
	      private  long user_id;
	     
		
	      private  String username;
	     
		
	      private  String password;
	     
		
	      private  String manager_designation;
	     
		
	      private  long role_id;
	     
		
	      private  String role_name;
	     
		
	      private  Data power_start;
	     
		
	      private  Data power_end;
	     
		
	      private  long create_time;
	     
		
	      private  String del_flag;

	      private  String token;
	      
	      private  String expire_time;
	      
	      
	      
	      public String getExpire_time() {
			return expire_time;
		}
		public void setExpire_time(String expire_time) {
			this.expire_time = expire_time;
		}
		public String getToken() {
			return token;
		   }
		  public void setToken(String token) {
			this.token = token;
		   }
		  public  long  getUser_id(){
	      		return  this.user_id;
	      };
	      public  void  setUser_id(long user_id){
	      	this.user_id=user_id;
	      }  
	     
	      public  String  getUsername(){
	      		return  this.username;
	      };
	      public  void  setUsername(String username){
	      	this.username=username;
	      }  
	     
	      public  String  getPassword(){
	      		return  this.password;
	      };
	      public  void  setPassword(String password){
	      	this.password=password;
	      }  
	     
	      public  String  getManager_designation(){
	      		return  this.manager_designation;
	      };
	      public  void  setManager_designation(String manager_designation){
	      	this.manager_designation=manager_designation;
	      }  
	     
	      public  long  getRole_id(){
	      		return  this.role_id;
	      };
	      public  void  setRole_id(long role_id){
	      	this.role_id=role_id;
	      }  
	     
	      public  String  getRole_name(){
	      		return  this.role_name;
	      };
	      public  void  setRole_name(String role_name){
	      	this.role_name=role_name;
	      }  
	      	public Data getPower_start() {
			return power_start;
			}
			public void setPower_start(Data power_start) {
				this.power_start = power_start;
			}
			public Data getPower_end() {
				return power_end;
			}
			public void setPower_end(Data power_end) {
				this.power_end = power_end;
			}
			public  long  getCreate_time(){
	      		return  this.create_time;
	      };
	      public  void  setCreate_time(long create_time){
	      	this.create_time=create_time;
	      }  
	     
	      public  String  getDel_flag(){
	      		return  this.del_flag;
	      };
	      public  void  setDel_flag(String del_flag){
	      	this.del_flag=del_flag;
	      }  
	     
	    
	 
}
