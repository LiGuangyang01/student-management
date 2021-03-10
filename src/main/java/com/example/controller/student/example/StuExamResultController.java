package com.example.controller.student.example;

import com.example.entity.Questions;
import com.example.entity.StuInformation;
import com.example.entity.StuResult;
import com.example.service.ExamLessonService;
import com.example.service.ExamQuestionsService;
import com.example.service.StuExamResultService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.*;

@Controller
@RequestMapping("/stu")
public class StuExamResultController {
    //属性注入
    @Autowired
    private ExamQuestionsService exampleQuestionsService;
    //属性注入
    @Autowired
    private ExamLessonService examLessonService;

    //属性注入
    @Autowired
    private StuExamResultService stuExamResultService;

    /*  计算学生考试成绩并存储到数据库
    **  将表单传输的name和value以map形式接受然后遍历它
     */
    @RequestMapping("/postExam")
    public String postExam(@RequestParam Map<String,String> map, Model model, HttpSession session){
        String username= (String) session.getAttribute("username");
        Integer taotiId=null;
         //String abc=null;
        Questions questions=new Questions();
        Set<String> keySet=map.keySet();
        Iterator<String> it=keySet.iterator();
        Integer radioscores=0;     //单选分
        Integer checkscores=0;    //多选分
        Integer total=0;         //总得分
        while (it.hasNext()){
           String key=it.next();
            String value=map.get(key);
              questions = exampleQuestionsService.findExamAnswerById(key);
                taotiId=questions.getTaotiid();
                if(questions.getType().equals("单选")) {

                    if(questions.getAnswer().equals(value)) {
                        radioscores+=10;
                    }
                }
                  if(questions.getType().equals("多选")) {

                    if(questions.getAnswer().equals(value)) {
                        checkscores+=20;
                    }
                }
        }

        total=radioscores+checkscores;
        //存储数据到数据库
        StuResult stuResult=new StuResult();
        stuResult.setRadioscores(radioscores);
        stuResult.setCheckscores(checkscores);
        stuResult.setTotal(total);
        stuResult.setCreatetime(new Timestamp(new Date().getTime()));
        stuResult.setTaotiid(taotiId);
        stuResult.setUsername(username);
        boolean addStuExamResult= stuExamResultService.addStuExamResult(stuResult);

        //返回参数给前端显示
        StuInformation stuInformation=stuExamResultService.findExamStuByUsername(username);
        String stuid=stuInformation.getStuid();
        String stuname=stuInformation.getStuname();
        String lessonname=questions.getLessonname();
        Map<String, Integer> resultmap1 = new HashMap<String, Integer>();
        resultmap1.put("rdioscores",radioscores);
        resultmap1.put("checkscores", checkscores);
        resultmap1.put("total", total);
        model.addAttribute("resultmap1", resultmap1);

        Map<String, String> resultmap2 = new HashMap<String,String>();
        resultmap2.put("lessonname",lessonname);
        resultmap2.put("stuname", stuname);

        resultmap2.put("stuid", stuid);
        model.addAttribute("resultmap2", resultmap2);
        return "student/examResult";
    }
    //用户查询所有考试记录
    @RequestMapping("/queryResult")
    public String queryResult(Model model, HttpSession session){
        String username= (String) session.getAttribute("username");

        //返回用户查询所有考试记录
        List<Map<String,Object>> queryList=stuExamResultService.findExamByUsername(username);
        model.addAttribute("queryList", queryList);
        return "student/examResultAll";
    }



    //将考试信息保存到Excel
    @RequestMapping("/saveExcel")
    public void saveExcel(HttpSession session, HttpServletResponse response)throws Exception{
        String username= (String) session.getAttribute("username");
        //返回用户查询所有考试记录
        List<Map<String, Object>> listMap = stuExamResultService.findExamByUsername(username);
        Workbook workbook=new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("您的考试成绩单");
        Row row=sheet.createRow(0);
        Cell cell00=row.createCell(0);
        cell00.setCellValue("考试课程");
        Cell cell01=row.createCell(1);
        cell01.setCellValue("单选题成绩");
        Cell cell02=row.createCell(2);
        cell02.setCellValue("多选题成绩");
        Cell cell03=row.createCell(3);
        cell03.setCellValue("总成绩");
        Cell cell04=row.createCell(4);
        cell04.setCellValue("考试时间");
        for(int i = 0;i<listMap.size();i++){
            row=sheet.createRow(i+1);
            Cell cell0=row.createCell(0);
            cell0.setCellValue((String)listMap.get(i).get("lessonname"));
            Cell cell1=row.createCell(1);
            cell1.setCellValue((Integer)listMap.get(i).get("radioscores"));
            Cell cell2=row.createCell(2);
            cell2.setCellValue((Integer)listMap.get(i).get("checkscores"));
            Cell cell3=row.createCell(3);
            cell3.setCellValue((Integer)listMap.get(i).get("total"));
            Cell cell4=row.createCell(4);
            cell4.setCellValue((Timestamp)listMap.get(i).get("createtime"));
            //获取单元格样式
            CreationHelper creationHelper = workbook.getCreationHelper();
            CellStyle cellStyle = workbook.createCellStyle();
            //定义单元格日期样式
            cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-mm-dd hh:mm:ss"));
            //设置单元格样式
            cell4.setCellStyle(cellStyle);
        }
        stuExamResultService.export(response, workbook, "examResults.xls");
    }

}
