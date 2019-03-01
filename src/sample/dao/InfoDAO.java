package sample.dao;

import sample.entity.Info;

import java.util.List;

public interface InfoDAO {

    void insert(Info info);

    void update(Info info);

    void delete(int id);

    Info getById(int id);

    List<Info> getAll();
}
