import com.mongodb.client.*; 
import com.mongodb.MongoClient; 
import com.mongodb.MongoCredential;

import org.bson.*;
import org.json.simple.JSONObject; 
import org.json.simple.JSONValue;   

public class ConnectToDB	 { 
   
   public static void main( String args[] ) {  
      
      // Creating a Mongo client 
      MongoClient mongo = new MongoClient( "10.10.15.202" , 27017 ); 
   
      // Creating Credentials 
      MongoCredential credential; 
      credential = MongoCredential.createCredential("te3421", "te3421db", 
         "password".toCharArray()); 
      System.out.println("Connected to the database successfully");  
      
      // Accessing the database 
      MongoDatabase database = mongo.getDatabase("te3421db"); 
      System.out.println("Credentials ::"+ credential);     
      
      //Creating Collection
      database.createCollection("sample1"); 
      System.out.println("\nCollection created successfully");
      MongoCollection<Document> col= database.getCollection("sample1");
      
      
      //Encoding JSON Object
      JSONObject obj=new JSONObject();    
	  obj.put("name","sharvil");    
	  obj.put("age",new Integer(19));    
	  obj.put("salary",new Double(600000));  
		
	  Document doc= Document.parse(obj.toString());
	  col.insertOne(doc);
	
	  
	//Decoding JSON Object
	  
	  Object obj1=JSONValue.parse(obj.toString());  
	  JSONObject jsonObject = (JSONObject) obj1;  
  
	  String name = (String) jsonObject.get("name");  
	  double salary = (Double) jsonObject.get("salary");  
	  long age = (Long) jsonObject.get("age");  
	  System.out.println("Name : "+name+"\nSalary :  "+salary+"\nAge :  "+age);  
	
	
	


   } 
}

