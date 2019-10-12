import com.mongodb.*;

import org.bson.Document;
import org.json.simple.*;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

public class B6 {
    
    public static void main(String[] args)  {
        
        JSONObject obj=new JSONObject();
        obj.put("name","ABC1");
        obj.put("age",new Integer(19));
        obj.put("salary",new Double(60000));
        System.out.println(obj);
        
        JSONObject obj2=new JSONObject();
        
        obj2.put("name","ABC2");
        obj2.put("age",new Integer(191));
        obj2.put("salary",new Double(600001));
        System.out.println(obj2);
        
        JSONObject obj3=new JSONObject();
        
        obj3.put("name","ABC32");
        obj3.put("age",new Integer(1913));
        obj3.put("salary",new Double(6000013));
        System.out.println(obj3);
        
        
        JSONArray arr = new JSONArray();
        arr.add(obj);
        arr.add(obj2);
        arr.add(obj3);
        
        
        try
        {
            MongoClient mongo = new MongoClient("localhost",27017);
            MongoCredential credential;
            credential = MongoCredential.createCredential("dbms","pratik","pratik123".toCharArray()); //dbname, username, ps
            System.out.println("Connected to the database successfully");
            
            MongoDatabase database = mongo.getDatabase("dbms");
            System.out.println("Credentials ::"+ credential);
            
            MongoCollection collection = database.getCollection("sampleCollection");
            
            for(int i=0;i<3;i++)
            {
                Document doc = Document.parse(arr.get(i).toString());
                collection.insertOne(doc);
            }
            
            FindIterable<Document> docs = collection.find();
            if (docs == null) {
                System.out.println("Not found");
            }
            
            for(Document doc1 : docs) {
                System.out.println(doc1);
            }
            
            System.out.println("Done");
            mongo.close();
        }
        catch(MongoException e){
            e.printStackTrace();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
