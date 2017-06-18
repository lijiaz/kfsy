package com.baizhi.service;
import com.baizhi.dao.ImageDao;
import com.baizhi.dao.MedicineDao;
import com.baizhi.dao.ProductDao;
import com.baizhi.entity.Image;
import com.baizhi.entity.Medicine;
import com.baizhi.entity.Product;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.UUID;

/**
 * Created by MaXn on 2017/6/14.
 */
@Service("pillService")
@Transactional
public class PillServiceImpl implements PillService {


    @Autowired
    private ProductDao productDao;
    @Autowired
    private MedicineDao medicineDao;
    @Autowired
    private ImageDao imageDao;

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Product queryProductByName(String name) {
        return productDao.selectByName(name);
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Product queryOneProduct(String id) {
        return productDao.selectOne(id);
    }

    //分页查询所有产品
    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Page<Product> queryAllProduct(Integer pageNum, Integer pageSize) {
        Page<Product> page = PageHelper.startPage(pageNum, pageSize);
        productDao.selectAll();
        return page;
    }

    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public List<Product> queryAll() {
        return productDao.selectAll();
    }


    @Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
    public Medicine queryOneMedicine(String id) {
        return medicineDao.selectOne(id);
    }

    public void add(Product product, Medicine medicine) {
        String id = UUID.randomUUID().toString();
        product.setId(id);
        medicine.setId(id);
        String productName = product.getName();
        List<Image> images = medicine.getImages();

        for (Image image : images) {
            Image i = new Image(UUID.randomUUID().toString(),productName,image.getUrl(),"1",medicine);
            imageDao.insert(i);
        }
        medicineDao.insert(medicine);
        productDao.insert(product);
    }


    public void remove(String id) {
        imageDao.deleteByMedicineId(id);
        productDao.delete(id);
        medicineDao.delete(id);
    }


}

