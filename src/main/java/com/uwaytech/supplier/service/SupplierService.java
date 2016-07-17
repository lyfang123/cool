package com.uwaytech.supplier.service;

        import com.github.pagehelper.Page;
        import com.uwaytech.supplier.domain.Supplier;

        import java.util.List;

/**
 * Created by moyi on 2016-06-07.
 */
public interface SupplierService {
    int insertSupplier(Supplier supplier);

    int deleteSupplier(Long id);

    Supplier getSupplier(long id);

    Page<Supplier> querySupplier(String name, Integer pageNum, Integer pageSize);

    int updateSupplier(Supplier supplier);

    boolean checkValid(String account);


}
