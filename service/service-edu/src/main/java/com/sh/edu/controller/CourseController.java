package com.sh.edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sh.commonutils.R;
import com.sh.edu.entity.Course;
import com.sh.edu.entity.CourserDTO;
import com.sh.edu.entity.query.CourseQO;
import com.sh.edu.service.CourseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author sunhu
 * @since 2021-04-26
 */
@RestController
@CrossOrigin
@RequestMapping("/edu/course")
public class CourseController {

    @Autowired
    CourseService courseService;

    @PostMapping("save-course-info")
    public R saveCourseInfo(@RequestBody CourserDTO courserDTO){
        String courseId=courseService.saveCourseInfo(courserDTO);
        if (StringUtils.isBlank(courseId)){
            return R.error();
        }
        return R.ok().data("courseId",courseId);
    }


    @GetMapping("list")
    public R courseList(){
        List<Course> list = courseService.list(new QueryWrapper<>());
        return R.ok().data("item",list);
    }

    @ApiOperation(value = "分页课程列表")
    @GetMapping("{page}/{limit}")
    public R pageQuery(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,

            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,

            @ApiParam(name = "courseQO", value = "查询对象", required = false)
                    CourseQO courseQO){

        Page<Course> pageParam = new Page<>(page, limit);

        courseService.pageQuery(pageParam, courseQO);
        List<Course> records = pageParam.getRecords();
        long total = pageParam.getTotal();
        return  R.ok().data("total", total).data("rows", records);
    }



}

