package com.baizhi.controller;


import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3AudioHeader;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.TagException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/**
 * Created by 小尚 on 2018/8/31.
 */
@RestController
@RequestMapping("/chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    @RequestMapping("/addChapter")
    public void addChapter (MultipartFile audio, int albumId, String title, HttpSession session){
        /*拿到文件后保存到哪里*/
        String projectPath=session.getServletContext().getRealPath("/");
        /*新建一个上传文件需要的文件夹*/
        String audioDir=projectPath+"audio";
        File file=new File(audioDir);
        if(!file.exists()){
            file.mkdir();
        }
        String originalFilename=audio.getOriginalFilename();
        String extension= FilenameUtils.getExtension(originalFilename);
        String uuid= UUID.randomUUID().toString();
        String newName=uuid+"."+extension;
        File file1=null;
        try {
            file1=new File(audioDir,newName);
            audio.transferTo(new File(audioDir,newName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //  Chapter chapter=new Chapter(0,title,"/audio/"+newName,);
        //文件大小
        long a = audio.getSize();
        long b =1024*1024;
        double size = new BigDecimal((float)a/b).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

        //文件时长
        MP3File mp3 = null;
        try {
            mp3 = (MP3File) AudioFileIO.read(file1);
        } catch (CannotReadException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TagException e) {
            e.printStackTrace();
        } catch (ReadOnlyFileException e) {
            e.printStackTrace();
        } catch (InvalidAudioFrameException e) {
            e.printStackTrace();
        }
        MP3AudioHeader audioHeader = (MP3AudioHeader)mp3.getAudioHeader();
        int c = audioHeader.getTrackLength();
        double length = new BigDecimal((float)c/60).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();

        Chapter chapter = new Chapter(0,title,""+length+"分",""+size+"MB",new Date(),"/audio/"+newName,albumId);

        chapterService.addChapter(chapter);
    }
    /*下载*/
    @RequestMapping("/down")
    public void down(String url, String title, HttpSession session, HttpServletResponse response){
        /*找到下载的文件
        * response   设置header    content-type*/
        String realPath=session.getServletContext().getRealPath("/");
        String uploadDir=realPath+"audio";
        String filePath=uploadDir+"/"+url;
        File file=new File(filePath);
        String extension=FilenameUtils.getExtension(url);
        title=title+"."+extension;
        String newName=null;
        try {
            //将文件名转换成http协议中规定的格式
            newName=new String(title.getBytes("utf-8"),"ISO-8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //tomacat中音频输出固定格式
        response.setContentType("audio/mpeg");
        response.setHeader("content-Disposition","attachment;fileName="+newName);
        try {
            //创建响应字节输出流
            ServletOutputStream outputStream=response.getOutputStream();
            //将文件转换成字节数组后通过字节输出流传输出去
            outputStream.write(FileUtils.readFileToByteArray(file));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
