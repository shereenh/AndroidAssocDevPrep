package com.shereen.paginglibrary.data.entity.ans;

import com.google.gson.annotations.SerializedName;
import com.shereen.paginglibrary.data.entity.ans.AnsItem;

import java.util.List;

/**
 * Created by shereen on 1/3/19
 */


public class AnsStackResponse {
    @SerializedName("items")
    public List<AnsItem> ansItems;
    public boolean has_more;
}