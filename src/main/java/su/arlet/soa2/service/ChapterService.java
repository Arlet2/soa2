package su.arlet.soa2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import su.arlet.soa2.core.Chapter;
import su.arlet.soa2.repo.ChapterRepo;

@Service
public class ChapterService {
    @Autowired
    private ChapterRepo chapterRepo;

    public Chapter getChapter(Long id) {
        return chapterRepo.getByID(id);
    }
}
