package su.arlet.soa2.controller;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import su.arlet.soa2.core.Chapter;
import su.arlet.soa2.service.ChapterService;

@AllArgsConstructor
@RestController
@RequestMapping("/chapters")
public class ChapterController {

    private ChapterService chapterService;


    @PostMapping
    public Long createChapter(@RequestBody ChapterPresenter chapter) {
        return chapterService.createChapter(chapter);
    }


}
