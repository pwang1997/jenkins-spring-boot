package com.erp.erpspringboot.core.vendors;

import com.erp.erpspringboot.core.vendors.dao.VendorDao;
import com.erp.erpspringboot.core.vendors.model.VendorBO;
import com.erp.erpspringboot.utils.BOUtils;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Puck Wang
 * @project erp-spring-boot
 * @created 5/4/2024
 */

@Service
public class VendorManager {

  private final VendorDao vendorDao;

  public VendorManager(VendorDao vendorDao) {
    this.vendorDao = vendorDao;
  }

  @Transactional
  public VendorBO create(VendorBO vendor) {
    BOUtils.setDirtyFields(vendor);
    return vendorDao.saveAndFlush(vendor);
  }
  @Transactional
  public VendorBO update(Long id, VendorBO vendor) {
    BOUtils.setDirtyFields(vendor);
    return vendorDao.saveAndFlush(vendor);
  }

  @Transactional
  public void batchDelete(List<Long> ids) {
    vendorDao.deleteAllByIdInBatch(ids);
  }

  public List<VendorBO> list(int pageNumber, int pageSize) {
    return vendorDao.findAll(
        PageRequest.of(pageNumber, pageSize)
    ).toList();
  }
}
