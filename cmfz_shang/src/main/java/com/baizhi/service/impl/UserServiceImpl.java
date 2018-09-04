package com.baizhi.service.impl;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 小尚 on 2018/9/4.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDao userDao;


    @Override
    public List<User> user() {
        List<User> user = userDao.export();
        return user;
    }

    @Override
    public void export(HttpServletResponse response) {
        //创建workbook对象
        Workbook workbook = new HSSFWorkbook();
        /*定义单元格设置日期类型调整*/
        DataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("yyyy-MM-dd");
        CellStyle cellStyle1 = workbook.createCellStyle();
        cellStyle1.setDataFormat(format);
        /*字体调整*/
        CellStyle cellStyle = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setColor(Font.COLOR_RED);
        font.setFontName("楷体");
        font.setBold(true);
        cellStyle.setFont(font);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        /*工作表   并设置名称*/
        Sheet sheet = workbook.createSheet("用户管理");
        sheet.setColumnWidth(8, 30 * 256);
        sheet.setColumnWidth(11, 21 * 256);
        sheet.setColumnWidth(12, 20 * 256);
        String[] titles = {"编号", "用户名", "法名", "密码", "性别", "省份", "城市", "描述","电话号码", "盐值","状态", "图片","注册时间"};
        /*创建行  行下标*/
        Row row = sheet.createRow(0);
        //为行的单元格赋值
        for (int i = 0; i < titles.length; i++) {
            String title = titles[i];
            //为单元格注入设置的样式
            Cell cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(title);
        }
        /*从数据库中循环出相应的数据  list<user>*/
        List<User> users = userDao.export();
        for (int i = 0; i < users.size(); i++) {
            Row row1 = sheet.createRow(i + 1);
            row1.createCell(0).setCellValue(users.get(i).getId());//为第0行单元格，第0个空，赋id的属性值
            row1.createCell(1).setCellValue(users.get(i).getName());//为第1行单元格，第1个空，赋name的属性值
            row1.createCell(2).setCellValue(users.get(i).getDharmaName());//为第2行单元格，第2个空，赋DharmaName的属性值
            row1.createCell(3).setCellValue(users.get(i).getPassword());
            row1.createCell(4).setCellValue(users.get(i).getSex());
            row1.createCell(5).setCellValue(users.get(i).getProvince());
            row1.createCell(6).setCellValue(users.get(i).getCity());
            row1.createCell(7).setCellValue(users.get(i).getSign());
            row1.createCell(8).setCellValue(users.get(i).getPhoneNum());
            row1.createCell(9).setCellValue(users.get(i).getSalt());
            row1.createCell(10).setCellValue(users.get(i).getStatus());
            row1.createCell(11).setCellValue(users.get(i).getPhotoImg());
            row1.createCell(12).setCellValue(users.get(i).getRegistDate());
            //注入日期样式
            Cell cell1 = row1.createCell(12);
            cell1.setCellStyle(cellStyle1);
            cell1.setCellValue(users.get(i).getRegistDate());
        }
        String newName = new Date().getTime() + "";
        newName = newName + "用户数据" + ".xls";
        String s = null;
        try {
            s = new String(newName.getBytes("UTF-8"), "ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("content-Disposition", "attachment;fileName=" + s);
        try {
            workbook.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //导入
    @Override
    public void userImport(MultipartFile file) {
        Workbook workbook=null;
        try {
            workbook=new HSSFWorkbook(file.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        Sheet sheet = workbook.getSheet("用户管理");
        List<User> list=new ArrayList<User>();
        for (int i=1;i<=sheet.getLastRowNum();i++){
            Row row = sheet.getRow(i);
            Cell cell=row.getCell(0);
            int id = (int) cell.getNumericCellValue();
            String name = row.getCell(1).getStringCellValue();
            String dharmaName = row.getCell(2).getStringCellValue();
            String password = row.getCell(3).getStringCellValue();
            int sex = (int) row.getCell(4).getNumericCellValue();
            String province = row.getCell(5).getStringCellValue();
            String city = row.getCell(6).getStringCellValue();
            String sign = row.getCell(7).getStringCellValue();
            String phoneNum = row.getCell(8).getStringCellValue();
            String salt = row.getCell(9).getStringCellValue();
            int status = (int) row.getCell(10).getNumericCellValue();
            String photoImg = row.getCell(11).getStringCellValue();
            Date registDate = row.getCell(12).getDateCellValue();
            User user=new User(id,name,dharmaName,password,sex,province,city,sign,phoneNum,salt,status,photoImg,registDate);
            list.add(user);
            userDao.insert(user);
        }
        for (User user:list){
            System.out.println("-----------------------"+user);
        }
    }

    //自定义导出
    @Override
    public void customerExport(String title,String fileds,HttpServletResponse response){
        //创建workbook对象
        Workbook workbook = new HSSFWorkbook();
        //创建单元格样式
        DataFormat dataFormat = workbook.createDataFormat();
        short format = dataFormat.getFormat("yyyy-MM-dd");
        CellStyle cellStyle1 = workbook.createCellStyle();
        cellStyle1.setDataFormat(format);
        //根据workbook对象创建表
        Sheet sheet = workbook.createSheet("user1");
        //创建第一行索引数据
        Row row = sheet.createRow(0);
        //将接受的表头字符串拆分,   给第一行每个单元格赋值
        String[] split = title.split(",");
        for (int i = 0; i < split.length; i++) {
            String s = split[i];
            row.createCell(i).setCellValue(s);
        }
        //得到需要从数据库获得的数据的属性名
        String[] filed = fileds.split(",");
        List<User> users=userDao.export();
        //遍历集合确定每一行的数据
        for (int i=0;i<users.size();i++){
            Row row1=sheet.createRow(i+1);
            //通过反射创建每个对象的类对象
            Class<? extends User> aClass=users.get(i).getClass();
            //遍历字符串数组得到属性名
            for (int j=0;j<filed.length;j++){
                //通过属性名获得相应的get方法
                String methodName="get"+filed[j].substring(0,1).toUpperCase()+filed[j].substring(1);
                Object o=null;
                try {
                    //通过方法名获得对应的Method对象,并用Method对象获取实体对象中的属性值,返回为Object类型
                    o=aClass.getDeclaredMethod(methodName,null).invoke(users.get(i),null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (o instanceof Date){
                    //如果为Date类型就为单元格添加样式
                    sheet.setColumnWidth(j,25*256);
                    Cell cell=row1.createCell(j);
                    cell.setCellStyle(cellStyle1);
                    cell.setCellValue((Date) o);
                }else{
                    //将属性值转化为字符串
                    row1.createCell(j).setCellValue(String.valueOf(o));
                }
            }
        }
        String newName=new Date().getTime()+"";
        newName=newName+"用户数据"+".xls";
        String s=null;
        try {
            s=new String(newName.getBytes("UTF-8"),"ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("content-Disposition", "attachment;fileName="+s);
        try {
            workbook.write(response.getOutputStream());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //更新
    @Override
    public void update(int status, int id) {
        userDao.updateUser(status,id);
    }
}
