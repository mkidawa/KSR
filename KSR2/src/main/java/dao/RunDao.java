package dao;

import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class RunDao {
    private ObjectId id;

    @BsonProperty(value = "horse_id")
    private int horseId;

    @BsonProperty(value = "horse_age")
    private int horseAge;

    @BsonProperty(value = "horse_type")
    private String horseType;

    @BsonProperty(value = "lengths_behind")
    private double lengthsBehind;

    @BsonProperty(value = "horse_rating")
    private int horseRating;

    @BsonProperty(value = "declared_weight")
    private double declaredWeight;

    @BsonProperty(value = "actual_weight")
    private double actualWeight;

    @BsonProperty(value = "position_sec1")
    private int positionSec1;

    @BsonProperty(value = "position_sec2")
    private int positionSec2;

    @BsonProperty(value = "position_sec3")
    private int positionSec3;

    @BsonProperty(value = "position_sec4")
    private int positionSec4;

    @BsonProperty(value = "position_sec5")
    private int positionSec5;

    @BsonProperty(value = "position_sec6")
    private int positionSec6;

    @BsonProperty(value = "behind_sec1")
    private double behindSec1;

    @BsonProperty(value = "behind_sec2")
    private double behindSec2;

    @BsonProperty(value = "behind_sec3")
    private double behindSec3;

    @BsonProperty(value = "behind_sec4")
    private double behindSec4;

    @BsonProperty(value = "behind_sec5")
    private double behindSec5;

    @BsonProperty(value = "behind_sec6")
    private double behindSec6;

    private double time1;
    private double time2;
    private double time3;
    private double time4;
    private double time5;
    private double time6;

    @BsonProperty(value = "finish_time")
    private double finishTime;

    @BsonProperty(value = "win_odds")
    private double winOdds;

    @BsonProperty(value = "place_odds")
    private double placeOdds;

    public RunDao() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public int getHorseId() {
        return horseId;
    }

    public void setHorseId(int horseId) {
        this.horseId = horseId;
    }

    public int getHorseAge() { return horseAge; }

    public void setHorseAge(int horseAge) { this.horseAge = horseAge; }

    public String getHorseType() { return horseType; }

    public void setHorseType(String horseType) { this.horseType = horseType; }

    public double getLengthsBehind() {
        return lengthsBehind;
    }

    public void setLengthsBehind(double lengthsBehind) {
        this.lengthsBehind = lengthsBehind;
    }

    public int getHorseRating() {
        return horseRating;
    }

    public void setHorseRating(int horseRating) {
        this.horseRating = horseRating;
    }

    public double getDeclaredWeight() {
        return declaredWeight;
    }

    public void setDeclaredWeight(double declaredWeight) {
        this.declaredWeight = declaredWeight;
    }

    public double getActualWeight() {
        return actualWeight;
    }

    public void setActualWeight(double actualWeight) {
        this.actualWeight = actualWeight;
    }

    public int getPositionSec1() {
        return positionSec1;
    }

    public void setPositionSec1(int positionSec1) {
        this.positionSec1 = positionSec1;
    }

    public int getPositionSec2() {
        return positionSec2;
    }

    public void setPositionSec2(int positionSec2) {
        this.positionSec2 = positionSec2;
    }

    public int getPositionSec3() {
        return positionSec3;
    }

    public void setPositionSec3(int positionSec3) {
        this.positionSec3 = positionSec3;
    }

    public int getPositionSec4() {
        return positionSec4;
    }

    public void setPositionSec4(int positionSec4) {
        this.positionSec4 = positionSec4;
    }

    public int getPositionSec5() {
        return positionSec5;
    }

    public void setPositionSec5(int positionSec5) {
        this.positionSec5 = positionSec5;
    }

    public int getPositionSec6() {
        return positionSec6;
    }

    public void setPositionSec6(int positionSec6) {
        this.positionSec6 = positionSec6;
    }

    public double getBehindSec1() {
        return behindSec1;
    }

    public void setBehindSec1(double behindSec1) {
        this.behindSec1 = behindSec1;
    }

    public double getBehindSec2() {
        return behindSec2;
    }

    public void setBehindSec2(double behindSec2) {
        this.behindSec2 = behindSec2;
    }

    public double getBehindSec3() {
        return behindSec3;
    }

    public void setBehindSec3(double behindSec3) {
        this.behindSec3 = behindSec3;
    }

    public double getBehindSec4() {
        return behindSec4;
    }

    public void setBehindSec4(double behindSec4) {
        this.behindSec4 = behindSec4;
    }

    public double getBehindSec5() {
        return behindSec5;
    }

    public void setBehindSec5(double behindSec5) {
        this.behindSec5 = behindSec5;
    }

    public double getBehindSec6() {
        return behindSec6;
    }

    public void setBehindSec6(double behindSec6) {
        this.behindSec6 = behindSec6;
    }

    public double getTime1() {
        return time1;
    }

    public void setTime1(double time1) {
        this.time1 = time1;
    }

    public double getTime2() {
        return time2;
    }

    public void setTime2(double time2) {
        this.time2 = time2;
    }

    public double getTime3() {
        return time3;
    }

    public void setTime3(double time3) {
        this.time3 = time3;
    }

    public double getTime4() {
        return time4;
    }

    public void setTime4(double time4) {
        this.time4 = time4;
    }

    public double getTime5() {
        return time5;
    }

    public void setTime5(double time5) {
        this.time5 = time5;
    }

    public double getTime6() {
        return time6;
    }

    public void setTime6(double time6) {
        this.time6 = time6;
    }

    public double getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(double finishTime) {
        this.finishTime = finishTime;
    }

    public double getWinOdds() {
        return winOdds;
    }

    public void setWinOdds(double winOdds) {
        this.winOdds = winOdds;
    }

    public double getPlaceOdds() {
        return placeOdds;
    }

    public void setPlaceOdds(double placeOdds) {
        this.placeOdds = placeOdds;
    }

    @Override
    public String toString() {
        return "RunDao{" +
                "id=" + id +
                ", horseId=" + horseId +
                ", horseAge=" + horseAge +
                ", lengthsBehind=" + lengthsBehind +
                ", horseRating=" + horseRating +
                ", declaredWeight=" + declaredWeight +
                ", actualWeight=" + actualWeight +
                ", positionSec1=" + positionSec1 +
                ", positionSec2=" + positionSec2 +
                ", positionSec3=" + positionSec3 +
                ", positionSec4=" + positionSec4 +
                ", positionSec5=" + positionSec5 +
                ", positionSec6=" + positionSec6 +
                ", behindSec1=" + behindSec1 +
                ", behindSec2=" + behindSec2 +
                ", behindSec3=" + behindSec3 +
                ", behindSec4=" + behindSec4 +
                ", behindSec5=" + behindSec5 +
                ", behindSec6=" + behindSec6 +
                ", time1=" + time1 +
                ", time2=" + time2 +
                ", time3=" + time3 +
                ", time4=" + time4 +
                ", time5=" + time5 +
                ", time6=" + time6 +
                ", finishTime=" + finishTime +
                ", winOdds=" + winOdds +
                ", placeOdds=" + placeOdds +
                '}';
    }
}
