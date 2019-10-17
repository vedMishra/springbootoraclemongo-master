package com.springdata.oracle.mongo;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.springframework.batch.item.ItemProcessor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springdata.models.UsersCategory;

public class UsersItemProcessor implements ItemProcessor<UsersCategory, UsersCategory> {
	@Override
	public UsersCategory process(UsersCategory usersCategory) throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		String jsonString = mapper.writeValueAsString(usersCategory);
		FileWriter fw = null;
		BufferedWriter bw = null;
		//Gson gson = new Gson();

		try {
			File file = new File("g:\\usersCategoryJson.json");
			fw = new FileWriter(file.getAbsoluteFile(), true);
			bw = new BufferedWriter(fw);

			bw.write(jsonString);
			bw.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {

				if (bw != null)
					bw.close();

				if (fw != null)
					fw.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}
		}

		return usersCategory;
	}
}
