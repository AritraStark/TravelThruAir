package service;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

import org.bson.Document;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoException;
import com.mongodb.ReadPreference;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.util.JSON;

import model.deals;

public class dealcurrService {
	private ConcurrentMap<Integer, deals> deals;
    private AtomicInteger key;
    private MongoCollection<Document> collections;
    private MongoClient mongoClient;
    private MongoDatabase database;

    public dealcurrService() {
        this.deals = new ConcurrentHashMap<Integer, deals>();
        key = new AtomicInteger();
        MongoClientURI uri = new MongoClientURI("mongodb+srv://servlet:servlet@cluster0.e0prs.mongodb.net/development?ssl=true&maxIdleTimeMS=5000&retryWrites=true&w=majority");
        this.mongoClient = new MongoClient(uri);
        this.database = mongoClient.getDatabase("curr");
        this.collections = database.getCollection("deals");
        this.addDeal(new deals("Aa", 1000, "Mumbai", "Delhi"));
        this.addDeal(new deals("Bb", 6000, "Amritsar", "Bangalore"));
        this.addDeal(new deals("Cc",4000, "Kolkata", "Chennai"));
    }

    public String findAllDeals() {
    	GsonBuilder x = new GsonBuilder();
        Gson gson = x.create();
        StringBuilder items = new StringBuilder();
        FindIterable<Document> find = this.collections.find();
        try( MongoCursor<Document> cursor = find.iterator() ) 
        {
        	items.append("[");
            while (cursor.hasNext()) {

                items.append(cursor.next().toJson());
                if(cursor.hasNext()) {
                    items.append(",");
                }
            }
            items.append("]");
        }
        catch(MongoException e)
        {
            System.out.println(e.getMessage());
        }
        finally {
        	//this.mongoClient.close();
        }

        
        //List<deals> list = new ArrayList<deals>(this.deals.values());
        // convert the list to json and return as string
        //return this.toJson(list);
        //return "";
        return items.toString();
    }

    public boolean createcurrDeal(String jsonPayload) {
        if (jsonPayload == null) return false;
        DB database = this.mongoClient.getDB("dev");
        DBCollection dbCollection = database.getCollection("curr");
        
        try{
        	DBObject dbObject = (DBObject)JSON.parse(jsonPayload);
            dbCollection.insert(dbObject);
            return true;
        }
        catch(Exception e) {
        	System.out.println(e);
        }
        
//        Gson gson = new Gson();
//        try {
//            deals deal = (deals) gson.fromJson(jsonPayload, deals.class);
//            if (deal != null) {
//                return this.addDeal(deal);
//            }
//        }
//        catch (Exception e) {}
        return false;
    }

    private String toJson(Object list) {
        if (list == null) return null;
        GsonBuilder x = new GsonBuilder();
        Gson gson = x.create();
        String json = null;
        try {
            json = gson.toJson(list);
        }
        catch (Exception e) {}
        return json;
    }

    private boolean addDeal(deals deal) {
        Integer id = key.incrementAndGet();
        deal.setId(id);
        this.deals.put(id, deal);
        return true;
    }
}
