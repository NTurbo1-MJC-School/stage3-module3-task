package com.mjc.school.controller.interfaces;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.AuthorDtoResponse;

public interface AuthorControllerInterface extends BaseController<AuthorDtoRequest, AuthorDtoResponse, Long> {

    AuthorDtoResponse readByNewsId(Long newsId);
}
