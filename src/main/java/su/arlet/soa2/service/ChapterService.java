package su.arlet.soa2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import su.arlet.soa2.core.exceptions.EntityNotFoundException;
import su.arlet.soa2.dto.chapter.ChapterPresenter;
import su.arlet.soa2.core.Chapter;
import su.arlet.soa2.repo.ChapterRepo;

import java.util.Optional;


@Service
public class ChapterService {
    @Autowired
    private ChapterRepo chapterRepo;

    public Chapter getChapter(Long id) {
        return chapterRepo.getByID(id);
    }

    public Long createChapter(@RequestBody ChapterPresenter chapter) {
        return chapterRepo.createChapter(new Chapter(null, chapter.getName(), chapter.getMarinesCount()));
    }

    public Chapter getChapterByName(String name) {
        return chapterRepo.findByName(name).orElseThrow(() -> new EntityNotFoundException("Chapter with name " + name + " not found"));
    }

    public Optional<Chapter> findByName(String name) {
        return chapterRepo.findByName(name);
    }
}
