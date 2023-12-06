package ra.academy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.academy.dao.IAdminDao;
import ra.academy.dto.UserEditInfor;
import ra.academy.model.User;
import ra.academy.service.IAdminService;
import ra.academy.service.IUserService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService implements IAdminService {
    @Autowired
    private IUserService userService;
    @Autowired
    private IAdminDao adminDao;

    @Override
    public long getTotalPage(int size,String search) {
        long count = adminDao.findAll().stream().filter(u->u.getFullName().contains(search)&& !u.isRole()).count();
        if (count % size == 0) {
            return count / size;
        }
        return count / size + 1;
    }


    @Override
    public List<User> findAll(int page, int size) {
        return adminDao.findAllP(size,page*size);
    }


    @Override
    public List<User> sort(String SBN, String SBCD, String SBS) {
        return userService.findAllUser();
    }

    @Override
    public List<User> findAllByName(int page, int size, String name) {
        return adminDao.findAllByName(size,page*size,name);
    }

    @Override
    public void banUserOrUnban(long id) {
        adminDao.banOrUnbanUser(id);

    }
}


