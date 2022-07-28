package com.example.webstore.domain;

import java.util.Arrays;
import java.util.List;

public class ItemSortParamsCreate {

    private static final List<SortParams> sortParams = Arrays.asList(
            new SortParams("createDate,DESC", "최신순"),
            new SortParams("itemName.ASC", "이름순")
                                                                    );

    public static List<SortParams> getInstance() {
        return sortParams;
    }

    private ItemSortParamsCreate() {}

}
