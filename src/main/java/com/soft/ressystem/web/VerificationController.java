package com.soft.ressystem.web;

import java.io.IOException;

import org.bson.BsonBinarySubType;
import org.bson.types.Binary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.soft.ressystem.domain.VerificationDocumentRepository;
import com.soft.ressystem.UserInfo;
import com.soft.ressystem.domain.User;
import com.soft.ressystem.domain.UserRepository;
import com.soft.ressystem.domain.VerificationDocument;

@Controller
public class VerificationController {

	@Autowired
	public VerificationDocumentRepository vRepo;

	@Autowired
	public UserRepository uRepo;

	public UserInfo infouser = new UserInfo();

	public VerificationDocument document = null;

	// Upload verification document for discount

	@PostMapping("/upload")
	public String singleFileUpload(@RequestParam("file") MultipartFile multipart) throws IOException {

		String username = infouser.getUsername();

		User user = uRepo.findUserByUsername(username);

		user.setPricecategory(6.0);

		user.setCustomertype("Senior/student");

		uRepo.delete(user);

		uRepo.insert(user);

		try {

			document = new VerificationDocument();

			document.setFile(new Binary(BsonBinarySubType.BINARY, multipart.getBytes()));

			vRepo.save(document);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "redirect:gamelist";
	}
}
