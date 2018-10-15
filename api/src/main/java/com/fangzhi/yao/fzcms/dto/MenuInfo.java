package com.fangzhi.yao.fzcms.dto;

import java.util.List;

public class MenuInfo {

    private String title; //菜单标题

    private String href; //菜单链接

    private boolean spread = false; //是否展开

    private List<MenuInfo> children; //子菜单列表（暂不支持，后续更新）

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public boolean getSpread() {
        return spread;
    }

    public void setSpread(boolean spread) {
        this.spread = spread;
    }

    public boolean isSpread() {
        return spread;
    }

    public List<MenuInfo> getChildren() {
        return children;
    }

    public void setChildren(List<MenuInfo> children) {
        this.children = children;
    }
}