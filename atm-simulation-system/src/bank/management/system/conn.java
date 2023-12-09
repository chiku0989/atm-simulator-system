package bank.management.system;

import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import com.mongodb.client.model.Updates;
import org.bson.Document;

import static com.mongodb.client.MongoClients.*;


public class conn {


    MongoDatabase database;
    MongoClient mongoClient;
    MongoCollection<Document> collection;
    conn(){

        try{
            //creating connection with mongodb localhost
            mongoClient = create("mongodb://localhost:27017");

            //opening database "atmSimulation"
            database = mongoClient.getDatabase("atmSimulation");
            System.out.println("connected");

        } catch(Exception e){
            System.out.println(e);
        }


    }

    void chooseCollection(String collectionName){
        try{
            collection = database.getCollection(collectionName);
            System.out.println("Collection found");

        } catch(Exception e ){
            System.out.println("Sorry Collection not found "+e);
        }
    }

    void insertData(Document document){

        try{
            collection.insertOne(document);
            System.out.println("Data inserted");

        }catch(Exception e){
            System.out.println("data not inserted "+e);
        }
    }

    Document findUser(Document document) {
        Document userdata = null; // Initialize to null

        try {
            // Assuming 'collection' is a valid MongoDB collection object
            userdata = collection.find(document).first();

            if (userdata == null) {
                System.out.println("Cannot find user");
            }
            else {
                System.out.println("User found");
            }
        } catch (Exception e) {
            System.out.println("Error in running query");
            e.printStackTrace(); // Print the exception details for debugging
        } finally {
            return userdata; // Returning from finally block is not necessary
        }
    }



    void updateBalance(Document document, int value){
        chooseCollection("balance");
        try{
            System.out.println(value);
            collection.updateOne(document,Updates.set("Balance",value));
            System.out.println("updated successfully");
        }
        catch (Exception e){
            System.out.println(e);
        }
    }

    void changePin(Document document, int pin){
        chooseCollection("accounts");
        collection.findOneAndUpdate(document,Updates.set("Pin",pin));

        chooseCollection("balance");
        collection.findOneAndUpdate(document,Updates.set("Pin",pin));
        System.out.println("success");
    }

    void updateTransaction(long cardNo, int value ,String type){


        chooseCollection("transactions");
        String formattedTime = null;
       try{
           LocalDateTime currentTime = LocalDateTime.now();
           DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
           formattedTime = currentTime.format(formatter);

       }
       catch(Exception e){
           System.out.println("unable to fetch time");
        }


        Document transactionDoc = new Document();
        transactionDoc.put("Card Number",cardNo);
        transactionDoc.put("Value", value);
        transactionDoc.put("Type",type);
        transactionDoc.put("Time Stamp",formattedTime);
        try{
            insertData(transactionDoc);
            System.out.println("Transaction registered");
        }catch(Exception e){
            System.out.println("cannot insert data");
        }

    }


    //method to close connection
    void close(){
        mongoClient.close();
        System.out.println("Connection Closed");
    }
}
