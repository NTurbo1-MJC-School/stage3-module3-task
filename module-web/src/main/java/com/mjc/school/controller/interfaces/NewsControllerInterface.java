package com.mjc.school.controller.interfaces;

import com.mjc.school.controller.BaseController;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;

public interface NewsControllerInterface extends BaseController<NewsDtoRequest, NewsDtoResponse, Long> {
}
