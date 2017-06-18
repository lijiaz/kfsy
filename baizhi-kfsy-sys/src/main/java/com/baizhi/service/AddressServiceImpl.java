package com.baizhi.service;
import com.baizhi.dao.AddressDao;
import com.baizhi.entity.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Created by MaXn on 2017/6/13.
 */
@Service("addressService")
@Transactional
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressDao addressDao;

    public void add(Address address) {
        address.setId(UUID.randomUUID().toString());
        addressDao.insert(address);
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Address queryOne(String id) {
        return addressDao.selectOne(id);
    }

    public void remove(String id) {
        addressDao.delete(id);
    }

    public void update(Address address) {
        addressDao.update(address);
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Address> queryAll() {
        return addressDao.selectAll();
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Address> queryAllByUserId(String id) {
        return addressDao.selectAllByUserId(id);
    }
}
