package com.example.webstore.controller;

import com.example.webstore.argumentResolver.Login;
import com.example.webstore.controller.dto.ItemSearchCondition;
import com.example.webstore.controller.dto.PageDto;
import com.example.webstore.domain.*;
import com.example.webstore.dto.ItemFormDto;
import com.example.webstore.dto.ItemImgDto;
import com.example.webstore.dto.MainItemDto;
import com.example.webstore.file.FileService;
import com.example.webstore.service.CategoryService;
import com.example.webstore.service.ItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;
    private final CategoryService categoryService;
    private final FileService fileService;


    @ModelAttribute("itemTypes")
    public List<ItemType> itemTypeList() {
        List<ItemType> itemTypes = new ArrayList<>();
        itemTypes.add(new ItemType("HIGHEST", "최상"));
        itemTypes.add(new ItemType("BEST", "상"));
        itemTypes.add(new ItemType("LOWER", "중"));
        return itemTypes;
    }

    @ModelAttribute("itemCategories")
    public List<ItemCategory> itemCategoryList() {
        List<ItemCategory> itemCategories = new ArrayList<>();
        itemCategories.add(new ItemCategory("BOOK", "책"));
        itemCategories.add(new ItemCategory("MUSIC", "음반"));
        return itemCategories;
    }

    @ModelAttribute("sortParams")
    public List<SortParams> sortParamsList() {
        return ItemSortParamsCreate.getInstance();
    }

    @GetMapping("/item/new")
    public String itemForm(Model model) {

        model.addAttribute("itemFormDto", new ItemFormDto());
        return "item/addItemV2";
    }

    @PostMapping("/item/new")
    public String itemNew(@Login User loginMember, ItemFormDto itemFormDto, BindResult bindResult,
                          Model model, @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList, RedirectAttributes redirectAttributes) throws IOException {


        if (itemImgFileList.get(0).isEmpty() && itemFormDto.getId() == null) {
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값 입니다.");
            return "item/itemForm";
        }

        Long id = itemService.saveItem(loginMember, itemFormDto, itemImgFileList);
        log.info("itemInfo={}", itemService.findById(id).toString());

        redirectAttributes.addAttribute("itemId", id);
        return "redirect:/item/{itemId}";
    }

    @GetMapping("/item/{itemId}")
    public String itemDetail(@PathVariable Long itemId, Model model) {
        ItemFormDto formDto = itemService.getItemDetail(itemId);
        for (ItemImgDto itemImgDto : formDto.getItemImgDtoList()) {
            log.info("img imgName={} savePath={}", itemImgDto.getImgName(),itemImgDto.getSavePath());
        }
        model.addAttribute("item", formDto);
        return "item/itemDetailV2";
    }

    @ResponseBody
    @GetMapping("/images/{filename}")
    public Resource downloadImage(@PathVariable String filename) throws MalformedURLException {
        //file:/users/.../nameh8787bghh33.png 이 uuid 경로를 찾아가지고,
        //urlResource가 찾는다.
        return new UrlResource("file:" + fileService.getFullPath(filename));
    }

    @GetMapping("/home")
    public String homeV2(Model model, ItemSearchCondition condition, @PageableDefault(size = 4, sort = "createdDate",direction = Sort.Direction.DESC) Pageable pageable)  {

        List<ItemCategory> categoryList = categoryService.findAll();
        model.addAttribute("categoryList",categoryList);

        Page<MainItemDto> results = itemService.searchPageSort(condition, pageable);
        model.addAttribute("items", results.getContent());

        log.info(pageable.toString());
        log.info("pageable.getSort={}", pageable.getSort());
        PageDto pageDto = new PageDto(results.getTotalElements(), pageable);

        model.addAttribute("page", pageDto);
        log.info(pageDto.toString());

        log.info(condition.toString());
        model.addAttribute("condition", condition);
        return "shop/index";
    }

   // @GetMapping("/home")
    public String home(Model model, ItemSearchCondition condition, @PageableDefault(size = 16, sort = "createdDate",direction = Sort.Direction.DESC) Pageable pageable) {

        List<ItemCategory> categoryList = categoryService.findAll();
        model.addAttribute("categoryList",categoryList);

        Page<MainItemDto> results = itemService.searchPageSort(condition, pageable);
        model.addAttribute("items", results.getContent());

        log.info(pageable.toString());
        log.info("pageable.getSort={}", pageable.getSort());
        PageDto pageDto = new PageDto(results.getTotalElements(), pageable);

        model.addAttribute("page", pageDto);
        log.info(pageDto.toString());

        log.info(condition.toString());
        model.addAttribute("condition", condition);

        return "item/saved1index";

    }

}
