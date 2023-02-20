package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseController;
import com.mjc.school.controller.annotation.CommandHandler;
import com.mjc.school.service.BaseService;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.dto.NewsDtoResponse;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class NewsController implements BaseController<NewsDtoRequest, NewsDtoResponse, Long> {

    private final BaseService<NewsDtoRequest, NewsDtoResponse, Long> service;

    public NewsController(BaseService<NewsDtoRequest, NewsDtoResponse, Long> service) {
        this.service = service;
    }


    @Override
    @CommandHandler(operation = "1")
    public List<NewsDtoResponse> readAll() {
        return service.readAll();
    }

    @Override
    @CommandHandler(operation = "2")
    public NewsDtoResponse readById(Long id) {
        return service.readById(id);
    }


    @CommandHandler(operation = "16")
    public NewsDtoResponse readTagsByNewsId(Long id) {
        return service.readTagsByNewsId(id);
    }

    @Override
    @CommandHandler(operation = "3")
    public NewsDtoResponse create(NewsDtoRequest createRequest) {
        return service.create(createRequest);
    }

    @Override
    @CommandHandler(operation = "4")
    public NewsDtoResponse update(NewsDtoRequest updateRequest) {
        return service.update(updateRequest);
    }

    @Override
    @CommandHandler(operation = "5")
    public boolean deleteById(Long id) {
        return service.deleteById(id);
    }


}
