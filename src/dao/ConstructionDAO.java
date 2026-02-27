package dao;

import java.util.List;

import model.Construction;
import util.JsonUtil;

public class ConstructionDAO {

    private static final String FILE = "constructions.json";

    public List<Construction> load() {
        return JsonUtil.readList(FILE, Construction.class);
    }

    public void save(List<Construction> constructions) {
        JsonUtil.writeList(FILE, constructions);
    }
}