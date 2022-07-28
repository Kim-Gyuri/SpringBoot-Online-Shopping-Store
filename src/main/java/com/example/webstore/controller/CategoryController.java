package com.example.webstore.controller;

import com.example.webstore.controller.dto.ItemSearchCondition;
import com.example.webstore.controller.dto.PageDto;
import com.example.webstore.dto.MainItemDto;
import com.example.webstore.service.CategoryService;
import com.example.webstore.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;
    private final ItemService itemService;

    @GetMapping("/category/BOOK")
    public String showBook(Model model, ItemSearchCondition condition, @PageableDefault(size = 5, sort = "createdDate",direction = Sort.Direction.DESC) Pageable pageable) {


            Page<MainItemDto> results = itemService.categoryPageSort("BOOK", condition,pageable);
            model.addAttribute("items", results.getContent());

            log.info(pageable.toString());
            log.info("pageable.getSort={}", pageable.getSort());
            PageDto pageDto = new PageDto(results.getTotalElements(), pageable);

            model.addAttribute("page", pageDto);
            log.info(pageDto.toString());

            log.info(condition.toString());
            model.addAttribute("condition", condition);

        return "category/bookCategoryPage";
    }

    @GetMapping("/category/MUSIC")
    public String showMUSIC(Model model, ItemSearchCondition condition, @PageableDefault(size = 5, sort = "createdDate",direction = Sort.Direction.DESC) Pageable pageable) {


            Page<MainItemDto> results = itemService.categoryPageSort("MUSIC", condition, pageable);
            log.info("category paging={}", results.getTotalPages());
            model.addAttribute("items", results.getContent());

            log.info(pageable.toString());
            log.info("pageable.getSort={}", pageable.getSort());
            PageDto pageDto = new PageDto(results.getTotalElements(), pageable);

            model.addAttribute("page", pageDto);
            log.info(pageDto.toString());

            log.info(condition.toString());
            model.addAttribute("condition", condition);

        return "category/musicCategoryPage";
    }



}
