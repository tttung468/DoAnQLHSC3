/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pagination;

import java.io.Serializable;
import qlhsc3.Generic;
import sort.Sorter;



/**
 *
 * @author ThanhTung
 * @param <T>
 */
public interface Pageable<T extends Generic> {
    public Integer getPage();
    public Integer getOffset();
    public Integer getLimit();
    public Sorter getSorter();
    public String getSearchKey();
    public T getFilterModel();
}
