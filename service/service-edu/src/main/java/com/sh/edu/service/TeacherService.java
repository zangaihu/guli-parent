package com.sh.edu.service;

import com.sh.edu.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author sunhu
 * @since 2021-04-20
 */
public interface TeacherService extends IService<Teacher> {
    /**
     * 根据id删除
     * @author sunhu
     * @date 2021/4/25 14:00
     * @param id
     * @return java.lang.Boolean
     */
    public Boolean removeById(String id);
}
