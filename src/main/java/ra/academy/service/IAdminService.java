package ra.academy.service;

import ra.academy.model.User;

import java.util.List;

public interface IAdminService {
    void banUserOrUnban(long id);
     long getTotalPage(int size,String search);
    List<User> findAll(int page, int size);
    List<User> sort(String SBN,String SBCD,String SBS);
     List<User> findAllByName(int page, int size,String name);
}

