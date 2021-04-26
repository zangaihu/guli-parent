package com.sh.edu.service;

import com.sh.edu.entity.Subject;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sh.edu.entity.SubjectVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author sunhu
 * @since 2021-04-26
 */
public interface SubjectService extends IService<Subject> {

    void importSubjectData(MultipartFile file);

    List<SubjectVO> nestedList();
}
