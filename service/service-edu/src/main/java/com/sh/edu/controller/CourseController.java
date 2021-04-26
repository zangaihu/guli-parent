package com.sh.edu.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sh.commonutils.R;
import com.sh.edu.entity.Course;
import com.sh.edu.entity.CourserDTO;
import com.sh.edu.service.CourseService;
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

}

