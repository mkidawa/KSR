package model;

import dao.RunDao;

import java.util.ArrayList;
import java.util.List;

public class RunsModel {
    public RunsModel() {};
    public List<RunDao> runs = new ArrayList<RunDao>();

    public void addRun(RunDao r) { runs.add(r); }


}
