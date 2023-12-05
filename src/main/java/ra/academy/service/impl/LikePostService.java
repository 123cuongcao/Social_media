package ra.academy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.academy.dao.ILikePostDao;
import ra.academy.model.LikePost;
import ra.academy.service.ILikePostService;

import java.util.List;

@Service
public class LikePostService implements ILikePostService {

    @Autowired
    private ILikePostDao likePostDao;
    @Override
    public List<LikePost> findAll() {
        return likePostDao.findAll();
    }

    @Override
    public int deleteById(Long id) {
        return likePostDao.deleteById(id);
    }
}
