package dao;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import model.Construction;

public class ConstructionDAO {

    private static final String FILE_PATH = "database/constructions.json";

    private final ObjectMapper mapper;

    public ConstructionDAO() {
        this.mapper = new ObjectMapper();
        this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
        ensureFileExists();
    }

    private void ensureFileExists() {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                mapper.writeValue(file, new ArrayList<Construction>());
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to create data file", e);
        }
    }

    public List<Construction> findAll() {
        try {
            return mapper.readValue(
                    new File(FILE_PATH),
                    new TypeReference<List<Construction>>() {}
            );
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }

    public void save(Construction construction) {
        List<Construction> list = findAll();
        list.add(construction);
        write(list);
    }

    public void update(Construction updated) {
        List<Construction> list = findAll();

        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId().equals(updated.getId())) {
                list.set(i, updated);
                break;
            }
        }

        write(list);
    }

    private void write(List<Construction> list) {
        try {
            mapper.writeValue(new File(FILE_PATH), list);
        } catch (IOException e) {
            throw new RuntimeException("Error saving data", e);
        }
    }
}