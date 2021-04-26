package com.sh.edu.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sh.edu.entity.ExcelSubjectData;
import com.sh.edu.entity.Subject;
import com.sh.edu.service.SubjectService;
import com.sh.servicebase.exception.GuliException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.Objects;

/**
 * @author sunhu
 * @date 2021/4/26 10:24
 */
public class SubjectExcelListener extends AnalysisEventListener<ExcelSubjectData> {

    @Autowired
    public SubjectService subjectService;


    public SubjectExcelListener(){}


    @Override
    public void invoke(ExcelSubjectData excelSubjectData, AnalysisContext analysisContext) {

        if (Objects.isNull(excelSubjectData)){
            throw new GuliException(20001,"添加失败");
        }

        // 添加一级分类
        Subject oneSubject = this.existOneSubject(excelSubjectData.getOneSubjectName());
        if (Objects.isNull(oneSubject)){
            oneSubject=new Subject();
            oneSubject.setTitle(excelSubjectData.getOneSubjectName());
            oneSubject.setParentId("0");
            subjectService.save(oneSubject);
        }

        String oneSubjectId = oneSubject.getId();
        Subject twoSubject = this.existTwoSubject(excelSubjectData.getTwoSubjectName(), oneSubjectId);
        if (Objects.isNull(twoSubject)){
            twoSubject=new Subject();
            twoSubject.setTitle(excelSubjectData.getTwoSubjectName());
            twoSubject.setParentId(oneSubjectId);
            subjectService.save(twoSubject);
        }
    }


    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息："+headMap);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }


    /**
     * 判断
     * @author sunhu
     * @date 2021/4/26 10:34
     * @param name:
     * @return com.sh.edu.entity.Subject
     */
    public Subject existOneSubject(String name){
        QueryWrapper<Subject> wrapper=new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id","0");
        Subject one = subjectService.getOne(wrapper);
        return one;
    }


    public Subject existTwoSubject(String name,String pid){

        QueryWrapper<Subject> wrapper=new QueryWrapper<>();
        wrapper.eq("title",name);
        wrapper.eq("parent_id",pid);

        Subject subject = subjectService.getOne(wrapper);
        return subject;
    }
}
