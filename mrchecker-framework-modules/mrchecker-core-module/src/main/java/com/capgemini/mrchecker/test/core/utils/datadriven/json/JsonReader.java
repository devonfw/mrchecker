package com.capgemini.mrchecker.test.core.utils.datadriven.json;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.capgemini.mrchecker.test.core.exceptions.BFInputDataException;

public class JsonReader {
	
	private JsonReader() {
		// NOP
	}
	
	/**
	 * Reads JSON file and return it's
	 * 
	 * @param jsonFile
	 * @return
	 */
	public static JSONObject readJson(File jsonFile) {
		JSONObject jsonObject = null;
		try {
			InputStream jsonStream = new FileInputStream(jsonFile);
			String genreJson = IOUtils.toString(jsonStream);
			try {
				jsonObject = new JSONObject(genreJson);
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (IOException e) {
			throw new BFInputDataException("Unable to read JSON file: " + jsonFile.getName());
		}
		return jsonObject;
	}
	
}
