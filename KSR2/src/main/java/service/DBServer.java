package service;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import dao.RunDao;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import static com.mongodb.client.model.Filters.eq;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

public class DBServer {
    private static final DBServer instance = new DBServer();

    private static MongoClient client;
    private static MongoDatabase database;
    private static MongoCollection<RunDao> runs;

    private DBServer() {
        ConnectionString connectionString = new ConnectionString("mongodb+srv://admin:admin@cluster0-5cjvr.mongodb.net/test?retryWrites=true&w=majority");
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        MongoClientSettings clientSettings = MongoClientSettings.builder()
                .applyConnectionString(connectionString)
                .codecRegistry(pojoCodecRegistry)
                .build();

        this.client = MongoClients.create(clientSettings);
        this.database = client.getDatabase("horses");
        this.runs = database.getCollection("horses_runs", RunDao.class);
    }

    public MongoCollection getRuns() {return runs;}

    public static DBServer getInstance() {
        return instance;
    }

}
