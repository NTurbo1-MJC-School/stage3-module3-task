package com.mjc.school.controller.interfaces;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.dto.TagDtoRequest;
import com.mjc.school.service.dto.TagDtoResponse;

import java.util.List;

public interface TagControllerInterface extends BaseController<TagDtoRequest, TagDtoResponse, Long> {

    List<TagDtoResponse> readByNewsId(Long newsId);
}
