package ra.academy.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ra.academy.dao.IReelDao;
import ra.academy.model.Reel;
import ra.academy.service.IReelService;

@Service
public class ReelService implements IReelService {
    @Autowired
    private IReelDao reelDao;

    @Autowired
    private UploadService uploadService;

    @Override
    public Reel getReelById(Long id) {
        return reelDao.getReel(id);
    }

    @Override
    public Long addStory(Long userId, String inputUrl) {
        return reelDao.addStory(userId,inputUrl);
    }
}

