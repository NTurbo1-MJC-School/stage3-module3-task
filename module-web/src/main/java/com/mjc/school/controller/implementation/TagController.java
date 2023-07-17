package com.mjc.school.controller.implementation;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.annotation.CommandHandler;
import com.mjc.school.controller.interfaces.TagControllerInterface;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;
import com.mjc.school.service.interfaces.TagServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class TagController implements TagControllerInterface {

    private final TagServiceInterface tagService;

    @Autowired
    public TagController(@Qualifier("tagService") TagServiceInterface tagService) {
        this.tagService = tagService;
    }

    @CommandHandler("3")
    public List<TagDtoResponse> readAll() {
        List<TagDtoResponse> tags = tagService.readAll();
        if (tags.isEmpty()) {
            System.out.println("There is no tag data in the database yet.");
        }
        return tags;
    }

    @CommandHandler("6")
    public TagDtoResponse readById(Long tagId) {
        return tagService.readById(tagId);
    }

    @CommandHandler("9")
    public TagDtoResponse create(TagDtoRequest dtoRequest) {
        return tagService.create(dtoRequest);
    }

    @CommandHandler("12")
    public TagDtoResponse update(TagDtoRequest dtoRequest) {
        return tagService.update(dtoRequest);
    }

    @CommandHandler("15")
    public boolean deleteById(Long tagId) {
        return tagService.deleteById(tagId);
    }

    @CommandHandler("17")
    public List<TagDtoResponse> readByNewsId(Long newsId) {
        return tagService.readByNewsId(newsId);
    }
}
