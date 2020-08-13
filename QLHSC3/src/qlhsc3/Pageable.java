/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package qlhsc3;

import org.hibernate.engine.spi.ExecutableList.Sorter;



/**
 *
 * @author ThanhTung
 * @param <T>
 */
public interface Pageable<T> {
    public Integer getPage();
    public Integer getOffset();
    public Integer getLimit();
    public Sorter getSorter();
    public String getSearchKey();
    public T getFilterModel();
}
