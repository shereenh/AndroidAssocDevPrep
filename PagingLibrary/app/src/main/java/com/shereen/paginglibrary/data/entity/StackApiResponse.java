package com.shereen.paginglibrary.data.entity;

import java.util.List;

/**
 * Created by shereen on 1/3/19
 */


public class StackApiResponse {
    public List<Item> items;
    public boolean has_more;
    public int backoff;
    public int quota_max;
    public int quota_remaining;
}