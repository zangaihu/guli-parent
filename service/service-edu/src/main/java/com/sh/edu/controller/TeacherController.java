package com.sh.edu.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sh.commonutils.R;
import com.sh.edu.entity.Teacher;
import com.sh.edu.service.TeacherService;
import com.sh.servicebase.exception.GuliException;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author sunhu
 * @since 2021-04-20
 */
@RestController
@RequestMapping("/edu/teacher")
public class TeacherController {


    @Autowired
    TeacherService teacherService;

    @ApiOperation(value = "分页讲师列表")
    @GetMapping("{page}/{limit}")
    public R pageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,@RequestBody Teacher teacher){
        Page<Teacher> pageParam = new Page<>(page, limit);
        teacherService.page(pageParam, null);
        List<Teacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();
        return  R.ok().data("total", total).data("rows", records);
    }


    @GetMapping("{id}")
    public R getTeacher(@PathVariable Integer id){
        Teacher teacher = teacherService.getById(id);
        return R.ok().data("teacher",teacher);

    }

    @PostMapping("{id}")
    public R update(@PathVariable Integer id,@RequestBody Teacher teacher){


        return R.ok();
    }

}

