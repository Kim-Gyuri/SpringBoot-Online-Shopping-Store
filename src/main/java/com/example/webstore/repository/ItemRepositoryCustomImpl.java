package com.example.webstore.repository;

import com.example.webstore.controller.dto.ItemSearchCondition;
import com.example.webstore.dto.MainItemDto;
import com.example.webstore.dto.QMainItemDto;
import com.example.webstore.dto.QUserMainItemDto;
import com.example.webstore.dto.UserMainItemDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.EntityManager;
import java.util.List;

import static com.example.webstore.domain.QItem.item;
import static com.example.webstore.domain.QItemImg.itemImg;

public class ItemRepositoryCustomImpl implements ItemRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public ItemRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<MainItemDto> searchPageSort(ItemSearchCondition condition, Pageable pageable) {
        List<MainItemDto> content = queryFactory
                .select(new QMainItemDto(
                        item.id.as("itemId"),
                        item.itemName.as("itemName"),
                        item.price,
                        item.stockQuantity,
                        itemImg.imgName,
                        item.itemType,
                        item.categoryType)
                       )
                .from(itemImg)
                .join(itemImg.item, item)
                .where(itemImg.reImgYn.eq("Y"))
                .where(itemNameContains(condition.getItemName()))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<MainItemDto> query = queryFactory
                .select(new QMainItemDto(
                        item.id.as("itemId"),
                        item.itemName.as("itemName"),
                        item.price,
                        item.stockQuantity,
                        itemImg.imgName,
                        item.itemType,
                        item.categoryType)
                       )
                .from(itemImg)
                .join(itemImg.item, item);

        return PageableExecutionUtils.getPage(content, pageable, ()->query.fetch().size());

    }

    @Override
    public Page<MainItemDto> categoryPageSort(String code, ItemSearchCondition condition, Pageable pageable) {
        List<MainItemDto> content = queryFactory
                .select(new QMainItemDto(
                        item.id.as("itemId"),
                        item.itemName.as("itemName"),
                        item.price,
                        item.stockQuantity,
                        itemImg.imgName,
                        item.itemType,
                        item.categoryType)
                       )
                .from(itemImg)
                .join(itemImg.item, item)
                .where(itemImg.reImgYn.eq("Y"))
                .where(itemNameContains(condition.getItemName()))
                .where(categoryTypeContains(code))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<MainItemDto> query = queryFactory
                .select(new QMainItemDto(
                        item.id.as("itemId"),
                        item.itemName.as("itemName"),
                        item.price,
                        item.stockQuantity,
                        itemImg.imgName,
                        item.itemType,
                        item.categoryType)
                       )
                .from(itemImg)
                .join(itemImg.item, item)
                .where(categoryTypeContains(code));

        return PageableExecutionUtils.getPage(content, pageable, ()->query.fetch().size());

    }

    @Override
    public List<UserMainItemDto> sortByUser() {
        List<UserMainItemDto> content = queryFactory
                .select(new QUserMainItemDto(
                        item.id.as("itemId"),
                        item.itemName.as("itemName"),
                        item.price,
                        item.stockQuantity,
                        itemImg.imgName,
                        item.itemType,
                        item.categoryType)
                       )
                .from(itemImg)
                .join(itemImg.item, item)
                .where(itemImg.reImgYn.eq("Y"))
                .fetch();

        return content;
    }

    private BooleanExpression itemNameContains(String itemName) {
        return StringUtils.hasText(itemName) ? item.itemName.contains(itemName) : null;
    }

    private BooleanExpression categoryTypeContains(String categoryType) {
        return StringUtils.hasText(categoryType) ? item.categoryType.contains(categoryType) : null;
    }
}
