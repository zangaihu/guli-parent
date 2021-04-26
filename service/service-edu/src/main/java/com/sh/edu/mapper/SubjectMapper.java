package com.sh.edu.mapper;

import com.sh.edu.entity.Subject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sh.edu.entity.SubjectVO;

import java.util.List;

/**
 * <p>
 * 课程科目 Mapper 接口
 * </p>
 *
 * @author sunhu
 * @since 2021-04-26
 */
public interface SubjectMapper extends BaseMapper<Subject> {


    public List<SubjectVO> getAll();
}
