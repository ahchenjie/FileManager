package com.springapp.controller;

import com.springapp.dao.FileDAO;
import com.springapp.model.File;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialBlob;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

@Controller
 public class FileController {

	private FileDAO fileDAO;
	@Autowired
	@Qualifier("fileDAO")
	public void setFileDAO(FileDAO fd){
		this.fileDAO=fd;
	}

	@RequestMapping(value = "/",method = RequestMethod.GET)
	public String index(Model model) {
			model.addAttribute("message","Add new file");
			model.addAttribute("file", new File());
			model.addAttribute("fileList", this.fileDAO.fileList());
		return "files";
	}
	@RequestMapping(value = "/save",method = RequestMethod.POST)
	public String save(@ModelAttribute("file")File file,@RequestParam("multipartFile")MultipartFile multipartFile){

 				Blob blob = null;
 				try {
					blob = new SerialBlob(IOUtils.toByteArray(multipartFile.getInputStream()));
				} catch (SQLException| IOException e) {
					e.printStackTrace();
				}

			file.setName(multipartFile.getOriginalFilename());
			file.setContent(blob);
			file.setContentType(multipartFile.getContentType());
			file.setCreated(Date.valueOf(LocalDate.now()));

		try {
			fileDAO.saveFile(file);
		}catch (Exception e){
			e.printStackTrace();
		}
		return "redirect:/";
	}

	@RequestMapping("/download/{fileId}")
	public String download(@PathVariable("fileId")
						   Integer fileId,HttpServletResponse response){
		File file =fileDAO.get(fileId);
		try{
			response.setHeader("Content-Disposition","inline;filename=\"" +file.getName()+"\"");
			OutputStream out =response.getOutputStream();
			IOUtils.copy(file.getContent().getBinaryStream(),out);
			out.flush();
			out.close();
		}catch (IOException | SQLException e){
			e.printStackTrace();
		}
		return null;
	}

	@RequestMapping("/remove/{fileId}")
	public String remove(@PathVariable("fileId")
						 Integer fileId){
		fileDAO.remove(fileId);
		return "redirect:/";
	}
}