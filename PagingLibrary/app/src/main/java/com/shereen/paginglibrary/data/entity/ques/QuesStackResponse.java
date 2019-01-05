package com.shereen.paginglibrary.data.entity.ques;

import com.google.gson.annotations.SerializedName;
import com.shereen.paginglibrary.data.entity.ans.AnsItem;

import java.util.List;

/**
 * Created by shereen on 1/4/19
 */

public class QuesStackResponse {
    @SerializedName("items")
    public List<QuesItem> quesItems;
    public boolean has_more;
}
