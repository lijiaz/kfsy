package com.baizhi.controller;
import com.baizhi.entity.DataGrid;
import com.baizhi.entity.Image;
import com.baizhi.entity.Medicine;
import com.baizhi.entity.Product;
import com.baizhi.service.PillService;
import com.github.pagehelper.Page;
import org.apache.commons.io.FilenameUtils;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.*;
/**
 * 药品Controller
 * Created by MaXn on 2017/6/14.
 */
@Controller
@RequestMapping("/pill")
public class PillController {

    @Resource
    private PillService pillService;

    //缩略图上传
    @RequestMapping("/uploadSmall")
    @ResponseBody
    public Product uploadSmall(MultipartFile file, HttpServletRequest request){
        String realPath = request.getSession().getServletContext().getRealPath("/");
        File f = new File(realPath, "upload");
        if(!f.exists()){
            f.mkdirs();
        }
        String newFileName = UUID.randomUUID().toString() +"."+ FilenameUtils.getExtension(file.getOriginalFilename());
        try {
            file.transferTo(new File(f,newFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Product product = new Product();
        product.setThumbnail("/upload/" + newFileName);
        return product;
    }

    //详情图上传
    @RequestMapping("/uploadDetail")
    @ResponseBody
    public Map<String,List<String>> uploadDetail(HttpServletRequest request){
        Map<String,List<String>> map = new HashMap<String, List<String>>();
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> fs= multipartRequest.getFiles("images");
        if(fs.size()>4){
            return map;
        }else{
            List<String> url = new ArrayList<String>();
            String realPath = request.getSession().getServletContext().getRealPath("/");
            File f = new File(realPath, "upload");
            if(!f.exists()){
                f.mkdirs();
            }
            for (MultipartFile file : fs) {
                String newFileName = UUID.randomUUID().toString() +"."+ FilenameUtils.getExtension(file.getOriginalFilename());
                try {
                    file.transferTo(new File(f,newFileName));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                url.add("/upload/" + newFileName);
            }
            map.put("urls",url);
            return map;
        }
    }

    @RequestMapping("/save")
    @ResponseBody
    public void save(String[] urls, @DateTimeFormat(pattern = "yyyy-MM-dd") Product product, Medicine medicine){

        List<Image> images = new ArrayList<Image>();
        for (String url : urls) {
            Image image = new Image();
            image.setUrl(url);
            images.add(image);
        }
        medicine.setImages(images);
        try{
            pillService.add(product,medicine);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    //分页查询所有
    @RequestMapping("/getAll")
    @ResponseBody
    public DataGrid<Product> getAll(Integer page,Integer rows){
        Page<Product> pages = pillService.queryAllProduct(page, rows);
        DataGrid<Product> dg = new DataGrid<Product>();
        dg.setTotal(Integer.valueOf(String.valueOf(pages.getTotal())));
        dg.setRows(pages.getResult());
        return dg;
    }

    //不分页查询所有
    @ResponseBody
    @RequestMapping("/getAllProduct")
    public List<Product> getAllProduct(){
        List<Product> products = pillService.queryAll();
        return products;
    }

    @RequestMapping("/del")
    @ResponseBody
    public void delete(String id){
        pillService.remove(id);
    }


    //获取一个药品的详情
    @RequestMapping("/getDetail")
    @ResponseBody
    public Medicine getDetail(String id){
        Medicine medicine = pillService.queryOneMedicine(id);
        return medicine;
    }


}
