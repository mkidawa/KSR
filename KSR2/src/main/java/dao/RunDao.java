package dao;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class RunDao {
    private ObjectId id;

    @BsonProperty(value = "horse_id")
    private String horseId;

    @BsonProperty(value = "horse_age")
    private String horseAge;

    @BsonProperty(value = "lengths_behind")
    private String lenghtsBehind;

    @BsonProperty(value = "horse_rating")
    private String horseRating;

    @BsonProperty(value = "declared_weight")
    private String declaredWeight;

    @BsonProperty(value = "actual_weight")
    private String actualWeight;

    public RunDao() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getHorseId() {
        return horseId;
    }

    public void setHorseId(String horseId) {
        this.horseId = horseId;
    }

    public String getHorseAge() {
        return horseAge;
    }

    public void setHorseAge(String horseAge) {
        this.horseAge = horseAge;
    }

    public String getLenghtsBehind() {
        return lenghtsBehind;
    }

    public void setLenghtsBehind(String lenghtsBehind) {
        this.lenghtsBehind = lenghtsBehind;
    }

    public String getHorseRating() {
        return horseRating;
    }

    public void setHorseRating(String horseRating) {
        this.horseRating = horseRating;
    }

    public String getDeclaredWeight() {
        return declaredWeight;
    }

    public void setDeclaredWeight(String declaredWeight) {
        this.declaredWeight = declaredWeight;
    }

    public String getActualWeight() {
        return actualWeight;
    }

    public void setActualWeight(String actualWeight) {
        this.actualWeight = actualWeight;
    }

    @Override
    public String toString() {
        return "RunDao{" +
                "id=" + id +
                ", horseId='" + horseId + '\'' +
                ", horseAge='" + horseAge + '\'' +
                ", lenghtsBehind='" + lenghtsBehind + '\'' +
                ", horseRating='" + horseRating + '\'' +
                ", declaredWeight='" + declaredWeight + '\'' +
                ", actualWeight='" + actualWeight + '\'' +
                '}';
    }
}
