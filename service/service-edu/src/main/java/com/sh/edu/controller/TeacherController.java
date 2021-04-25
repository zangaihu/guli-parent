package com.sh.edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
@CrossOrigin
public class TeacherController {


    @Autowired
    TeacherService teacherService;

    @ApiOperation(value = "分页讲师列表")
    @PostMapping("{page}/{limit}")
    public R pageList(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,@RequestBody Teacher teacher){
        Page<Teacher> pageParam = new Page<>(page, limit);
        teacherService.page(pageParam, new QueryWrapper<>(teacher));
        List<Teacher> records = pageParam.getRecords();
        long total = pageParam.getTotal();
        return  R.ok().data("total", total).data("rows", records);
    }


    @GetMapping("{id}")
    public R getTeacher(@PathVariable String id){
        Teacher teacher = teacherService.getById(id);
        return R.ok().data("item",teacher);

    }


    @DeleteMapping("{id}")
    @ApiOperation(value = "删除讲师")
    public R deleteById(@PathVariable String id){
        boolean b = teacherService.removeById(id);
        if (b){
            return R.ok();
        }else {
            return R.error().message("删除失败");
        }

    }

    @PostMapping("/update")
    public R update(@PathVariable String id,@RequestBody Teacher teacher){
        teacherService.saveOrUpdate(teacher);
        return R.ok();
    }

    @PostMapping("save")
    public R saveTeacher(@RequestBody Teacher teacher){
        teacherService.save(teacher);
        return R.ok();
    }

    @GetMapping("login")
    public R login(){
        return R.ok().data("msg","success");
    }

    @RequestMapping("user/info")
    public R getUserInfo(){
        return R.ok().data("roles",new Integer[]{1,2});
    }

    @RequestMapping("user/logout")
    public R logout(){
        return R.ok().data("msg","success");
    }
}

