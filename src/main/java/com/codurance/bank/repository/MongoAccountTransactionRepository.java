package com.codurance.bank.repository;

import com.codurance.bank.domain.AccountTransaction;
import com.fasterxml.jackson.core.JsonGenerator;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import de.undercouch.bson4jackson.BsonFactory;
import org.bson.Document;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.stream.Stream;

public class MongoAccountTransactionRepository implements AccountTransactionRepository {
    @Override
    public void store(AccountTransaction transaction) {
        try{
        MongoClient mongoClient = new MongoClient("local");
        MongoDatabase database = mongoClient.getDatabase("bank");
        MongoCollection<Document> collection = database.getCollection("transactions");

        BsonFactory factory = new BsonFactory();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

            JsonGenerator gen = factory.createJsonGenerator(baos);
            gen.writeStartObject();
            gen.writeFieldName("dateTime");
            gen.writeString(String.valueOf(transaction.dateTime));
            gen.writeFieldName("amount");
            gen.writeString(String.valueOf(transaction.amount));

            gen.close();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public Stream<AccountTransaction> fetch() {
        return null;
    }
}
