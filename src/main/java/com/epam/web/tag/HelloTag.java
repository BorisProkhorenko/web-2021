package com.epam.web.tag;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;


public class HelloTag extends TagSupport {

    private Integer items;
    private Integer itemsOnPage;
    private String name;

    @Override
    public int doStartTag() throws JspException {
        return super.doStartTag();
    }

    private int countPages(int items, int itemsOnPage) {
        if (items % itemsOnPage == 0) {
            if (items / itemsOnPage == 0) {
                return 1;
            } else {
                return items / itemsOnPage;
            }

        } else {
            return items / itemsOnPage + 1;
        }
    }
    public void setItems(Integer items) {
        this.items = items;
    }

    public Integer getItems() {
        return items;
    }

    public Integer getItemsOnPage() {
        return itemsOnPage;
    }

    public void setItemsOnPage(Integer itemsOnPage) {
        this.itemsOnPage = itemsOnPage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
