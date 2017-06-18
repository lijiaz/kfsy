package com.baizhi.controller;
import com.baizhi.entity.DataGrid;
import com.baizhi.entity.Evaluate;
import com.baizhi.service.EvaluateService;
import com.github.pagehelper.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 *用户评价的Controller
 * Created by MaXn on 2017/6/13.
 */
@Controller
@RequestMapping("/evaluate")
public class EvaluateController {

    @Resource
    private EvaluateService evaluateService;

    @RequestMapping("/getAll")
    @ResponseBody
    public DataGrid<Evaluate> getAll(Integer page,Integer rows){
        Page<Evaluate> pages = evaluateService.queryAllEval(page, rows);
        DataGrid<Evaluate> dg = new DataGrid<Evaluate>();
        dg.setTotal(Integer.valueOf(String.valueOf(pages.getTotal())));
        dg.setRows(pages.getResult());
        return dg;
    }

    @RequestMapping("/getAllBy")
    @ResponseBody
    public DataGrid<Evaluate> getAllBy(Integer page,Integer rows,String name,String value){
        DataGrid<Evaluate> dg = new DataGrid<Evaluate>();
        Page<Evaluate> pages = null;
        if("content".equals(name)){
            pages = evaluateService.queryByContent(page, rows, value);
        }else{
            pages = evaluateService.queryByStar(page, rows, value);
        }
        dg.setTotal(Integer.valueOf(String.valueOf(pages.getTotal())));
        dg.setRows(pages.getResult());
        return dg;
    }

}
