package com.sh.edu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sh.edu.entity.Course;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sh.edu.entity.CourserDTO;
import com.sh.edu.entity.query.CourseQO;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author sunhu
 * @since 2021-04-26
 */
public interface CourseService extends IService<Course> {

    String saveCourseInfo(CourserDTO courserDTO);

    void pageQuery(Page<Course> page, CourseQO courseQO);

}
