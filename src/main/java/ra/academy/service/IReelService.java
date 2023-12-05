package ra.academy.service;

import ra.academy.model.Reel;

public interface IReelService {
    Long addStory(Long userId, String inputUrl);
    Reel getReelById(Long id);
}
