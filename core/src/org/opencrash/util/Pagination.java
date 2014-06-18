package org.opencrash.util;

import java.awt.print.Pageable;

/**
 * Created by Fong on 18.06.14.
 */
public class Pagination {
    private Integer itemsCount;
    private Integer currentPage;
    private String url;
    private String nextPage;
    private String pages;

    public Pagination(Integer itemsCount, String url,Integer currentPage) {
        this.itemsCount = itemsCount;
        this.url = url;
        this.currentPage = currentPage;
    }
    public String paginate(){
        String disabled=null;
        if(this.currentPage.equals(1)){
            disabled = "class='disabled'";
        }else{

        }
        return "<ul class='pagination'><li"+disabled+"><a href='"+"'>&laquo;</a></li>"+pages+nextPage;
    }
}
