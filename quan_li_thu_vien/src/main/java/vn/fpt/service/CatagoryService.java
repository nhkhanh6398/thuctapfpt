package vn.fpt.service;

import vn.fpt.model.Author;
import vn.fpt.model.Catagory;

import java.util.List;

public interface CatagoryService {
    public List<Catagory> findAllCatagory();

    public Catagory findCatagoryById(int id);

    public void createCatagory(Catagory catagory);

    public void updateCatagory(Catagory catagory);

    public void deleteCatagory(int id);
}
