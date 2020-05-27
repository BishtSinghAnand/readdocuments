package io.bisht.readerservice.controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import io.bisht.readerservice.model.User;
import io.bisht.readerservice.patterns.ReaderCsv;
import io.bisht.readerservice.patterns.ReaderFactorMethod;
import io.bisht.readerservice.patterns.ReaderFactory;

@Controller
public class ReaderController {

	@Autowired
	ReaderFactorMethod<User> read;
	
	@Autowired
	KafkaTemplate<String, User> kafkaTemplate;
	@Autowired
	ReaderController(KafkaTemplate<String, User> kafkaTemplate){
		this.kafkaTemplate=kafkaTemplate;
	}
	
	 @GetMapping("/")
	    public String index() {
	        return "index";
	    }

	    @PostMapping("/upload-csv-file")
	    public String uploadCSVFile(@RequestParam("file") MultipartFile file, Model model) {

	        // validate file
	        if (file.isEmpty()) {
	            model.addAttribute("message", "Please select a CSV file to upload.");
	            model.addAttribute("status", false);
	        } else {

	            // parse CSV file to create a list of `User` objects
	            try (Reader reader = new BufferedReader(new InputStreamReader(file.getInputStream()))) {

	            	
	                // create csv bean reader
	            	ReaderFactory<User> red=new ReaderCsv<User>(User.class);
	            	List<User> users=red.reader(reader);
	                // TODO: save users in DB?
	            	User user=new User();
	            	user.setAge(10);
	            	kafkaTemplate.send("mytopic",user);
	                // save users list on model
	                model.addAttribute("users", users);
	                model.addAttribute("status", true);

	            } catch (Exception ex) {
	                model.addAttribute("message", "An error occurred while processing the CSV file.");
	                model.addAttribute("status", false);
	            }
	        }

	        return "file-upload-status";
	    }
	 }

