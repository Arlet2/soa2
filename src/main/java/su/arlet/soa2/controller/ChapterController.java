package su.arlet.soa2.controller;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import su.arlet.soa2.dto.IdWrapper;
import su.arlet.soa2.dto.LongToIdSerializer;
import su.arlet.soa2.dto.chapter.ChapterPresenter;
import su.arlet.soa2.service.ChapterService;

@AllArgsConstructor
@RestController
@RequestMapping("/chapters")
public class ChapterController {

    private ChapterService chapterService;


    @PostMapping
    public IdWrapper createChapter(@RequestBody @Validated ChapterPresenter chapter) {
        return new IdWrapper(chapterService.createChapter(chapter));
    }




}
