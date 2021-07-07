package vn.fpt.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fpt.model.Catagory;
import vn.fpt.repository.CatagoryRepository;
import vn.fpt.service.CatagoryService;

import java.util.List;
@Service
public class CatagoryServiceImpl implements CatagoryService {
    @Autowired
    CatagoryRepository catagoryRepository;
    @Override
    public List<Catagory> findAllCatagory() {
        return catagoryRepository.findAll();
    }

    @Override
    public Catagory findCatagoryById(int id) {
        return catagoryRepository.findById(id).orElse(null);
    }

    @Override
    public void createCatagory(Catagory catagory) {
        catagoryRepository.save(catagory);
    }

    @Override
    public void updateCatagory(Catagory catagory) {
        catagoryRepository.save(catagory);
    }

    @Override
    public void deleteCatagory(int  id) {
        catagoryRepository.deleteById(id);
    }
}
