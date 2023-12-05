package ra.academy.dao;

import ra.academy.model.Reel;
import ra.academy.model.User;

import javax.servlet.http.HttpSession;

public interface IReelDao extends IGenericDao<User, Long> {
    Reel findByUserEmail(String userName);

    Long addStory(Long userId, String upload_url);
    Reel getReel(long idReel);
}
