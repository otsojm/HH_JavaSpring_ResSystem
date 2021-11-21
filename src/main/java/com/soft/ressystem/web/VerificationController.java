package com.soft.ressystem.web;

import java.io.IOException;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.soft.ressystem.domain.VerDocumentRepo;
import com.soft.ressystem.InfoUser;
import com.soft.ressystem.domain.User;
import com.soft.ressystem.domain.UserRepo;
import com.soft.ressystem.domain.VerDocument;

@Controller
public class VerificationController {

	@Autowired
	public VerDocumentRepo dRepo;

	@Autowired
	public UserRepo uRepo;

	public InfoUser infouser = new InfoUser();

	@PostMapping("/upload")
	public String singleFileUpload(@RequestParam("file") MultipartFile multipart) throws IOException {

		String username = infouser.getUsername();

		User user = uRepo.findUserByUsername(username);

		System.out.println(user);

		user.setPricecategory(6.0);

		user.setCustomertype("Senior/student");
		
		uRepo.delete(user);
		
		uRepo.insert(user);

		try {

			VerDocument documents = new VerDocument();

			documents.setFile(new Binary(BsonBinarySubType.BINARY, multipart.getBytes()));

			dRepo.save(documents);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:gamelist";
	}
}
