package pwd.initializr.common.web.business.bo;

import java.util.List;

/**
 * pwd.initializr.common.web.business.bo@ms-web-initializr
 *
 * <h1>TODO what you want to do?</h1>
 *
 * date 2019-09-14 15:26
 *
 * @author DingPengwei[www.dingpengwei@foxmail.com]
 * @version 1.0.0
 * @since DistributionVersion
 */
public class ObjectList<T> {

    /**
     * 总数据量
     */
    private Long total;
    /**
     * 总页数
     */
    private Long pages;
    /**
     * 当前页码
     */
    private Long index;
    /**
     * 当前页面容量
     */
    private Long size;

    private List<T> elements;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Long getPages() {
        return pages;
    }

    public void setPages(Long pages) {
        this.pages = pages;
    }

    public Long getIndex() {
        return index;
    }

    public void setIndex(Long index) {
        this.index = index;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public List<T> getElements() {
        return elements;
    }

    public void setElements(List<T> elements) {
        this.elements = elements;
    }
}
