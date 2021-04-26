package com.sh.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sh.edu.entity.Course;
import com.sh.edu.entity.CourseDescription;
import com.sh.edu.entity.CourserDTO;
import com.sh.edu.entity.query.CourseQO;
import com.sh.edu.mapper.CourseMapper;
import com.sh.edu.service.CourseDescriptionService;
import com.sh.edu.service.CourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sh.servicebase.exception.GuliException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author sunhu
 * @since 2021-04-26
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {


    @Autowired
    private CourseDescriptionService courseDescriptionService;

    /**
     * 保存课程信息
     * @author sunhu
     * @date 2021/4/26 14:27
     * @param courserDTO
     * @return java.lang.String
     */
    @Override
    public String saveCourseInfo(CourserDTO courserDTO) {
        Course course=new Course();
        course.setStatus(Course.COURSE_DRAFT);
        BeanUtils.copyProperties(courserDTO,course);

        boolean result1 = this.save(course);
        if (!result1){
            throw new GuliException();
        }

        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setDescription(courserDTO.getDescription());
        courseDescription.setId(course.getId());

        boolean resultDescription = courseDescriptionService.save(courseDescription);
        if (!resultDescription){
            throw new GuliException();
        }

        return course.getId();
    }

    /**
     * 分页查询课程列表
     *
     * @author sunhu
     * @date 2021/4/26 15:13
     * @param page
     * @param courseQO:
     * @return void
     */
    @Override
    public void pageQuery(Page<Course> page, CourseQO courseQO) {

        QueryWrapper<Course> wrapper = new QueryWrapper<>();

        if (Objects.isNull(courseQO)){
            baseMapper.selectPage(page,wrapper);
            return;
        }

        if (StringUtils.isNotBlank(courseQO.getTitle())){
            wrapper.like("title",courseQO.getTitle());
        }
        if (StringUtils.isNotBlank(courseQO.getTeacherId()) ) {
            wrapper.eq("teacher_id", courseQO.getTeacherId());
        }
        if (StringUtils.isNotBlank(courseQO.getSubjectParentId())) {
            wrapper.ge("subject_parent_id", courseQO.getSubjectParentId());
        }
        if (StringUtils.isNotBlank(courseQO.getSubjectId())) {
            wrapper.ge("subject_id", courseQO.getSubjectId());
        }

        baseMapper.selectPage(page,wrapper);

    }


}
