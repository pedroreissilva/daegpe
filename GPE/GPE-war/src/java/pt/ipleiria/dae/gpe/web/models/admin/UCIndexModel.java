/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pt.ipleiria.dae.gpe.web.models.admin;

import java.util.List;
import pt.ipleiria.dae.gpe.lib.beans.UCBean;
import pt.ipleiria.dae.gpe.lib.dtos.UCDTO;
import pt.ipleiria.dae.gpe.lib.beans.query.options.AdminUCFindOptions;
import pt.ipleiria.dae.gpe.lib.beans.query.order.UCOrderBy;

/**
 *
 * @author LuoSKraD
 */
public class UCIndexModel {

    private final UCBean ucBean;

    public int pageId;
    public final int pageSize = 20;
    public UCOrderBy orderBy;
    public String search;

    public long count;
    public int pagesCount;

    public UCIndexModel(UCBean ucBean) {
        this.ucBean = ucBean;
        this.pageId = 1;
        this.orderBy = UCOrderBy.InternalIdAsc;
        this.search = "";
        this.count = 0;
    }

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    public int getOrderBy() {
        switch (orderBy) {
            case InternalIdAsc:
                return 1;
            case InternalIdDesc:
                return 2;
            case NameAsc:
                return 3;
            case NameDesc:
                return 4;
        }
        return 0;
    }

    public void setOrderBy(int val) {
        switch (val) {
            case 1:
                orderBy = UCOrderBy.InternalIdAsc;
                break;
            case 2:
                orderBy = UCOrderBy.InternalIdDesc;
                break;
            case 3:
                orderBy = UCOrderBy.NameAsc;
                break;
            case 4:
                orderBy = UCOrderBy.NameDesc;
                break;
        }
    }

    public int getPageSize() {
        return pageSize;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.pageId = 1;
        this.orderBy = UCOrderBy.InternalIdAsc;
        this.search = search;
    }

    public long getCount() {
        return count;
    }

    public int getPagesCount() {
        return pagesCount;
    }

    public List<UCDTO> getUcs() {
        AdminUCFindOptions options = new AdminUCFindOptions(pageId, pageSize, orderBy, search);
        List<UCDTO> list = ucBean.find(options);
        this.count = options.count;
        this.pagesCount = (int) Math.ceil((double) count / (double) pageSize);
        return list;
    }
}
