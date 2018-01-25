package com.capgemini.ntc.test.core.base.encryption.providers;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.jasypt.properties.PropertyValueEncryptionUtils;

import com.capgemini.ntc.test.core.base.encryption.IDataEncryptionService;
import com.capgemini.ntc.test.core.exceptions.BFSecureModuleException;
import com.google.common.base.Preconditions;

/**
 * Basic data encryption service to encapsulate and isolate the encryption/decryption logic.
 *
 * @author Lukasz Stefaniszyn, Capgemini
 * @author Marek Puchalski, Capgemini
 */
public class DataEncryptionService implements IDataEncryptionService {
	
	private static IDataEncryptionService	instance;
	private StandardPBEStringEncryptor		encryptor	= new StandardPBEStringEncryptor();
	
	private DataEncryptionService(File path) {
		Preconditions.checkNotNull(path);
		setSecret(readSecret(path));
	}
	
	/**
	 * Initializes the service based on the path to a file containing the secret.
	 * NOTE: This is the best way to instantiate the service, as you should be holding
	 * the secret in a file that is not checked into your repository, and is passed
	 * among your team members over a different channel.
	 *
	 * @param path
	 *          path to the file with the secret (only first line is relevant).
	 * @return Class instance.
	 */
	public static IDataEncryptionService init(File path) {
		if (instance == null) {
			synchronized (DataEncryptionService.class) {
				if (instance == null) {
					instance = new DataEncryptionService(path);
				}
			}
		}
		return instance;
	}
	
	public static IDataEncryptionService getInstance() {
		return DataEncryptionService.instance;
	}
	
	public static void delInstance() {
		DataEncryptionService.instance = null;
	}
	
	private String readSecret(File secretFile) {
		try (Scanner scanner = new Scanner(secretFile)) {
			return readLine(scanner, secretFile).trim();
		} catch (FileNotFoundException e) {
			throw new BFSecureModuleException("File does not exist: " + secretFile);
		}
	}
	
	private String readLine(Scanner scanner, File secretFile) {
		if (scanner.hasNext()) {
			return scanner.next();
		} else {
			throw new BFSecureModuleException("File is empty: " + secretFile);
		}
	}
	
	@Override
	public String encrypt(String text) {
		try {
			return PropertyValueEncryptionUtils.encrypt(text, encryptor);
		} catch (Throwable t) {
			throw new BFSecureModuleException(t.getMessage(), t);
		}
	}
	
	@Override
	public String decrypt(String text) {
		if (!isEncrypted(text)) {
			throw new BFSecureModuleException("Text is not encrypted: " + text);
		}
		try {
			return PropertyValueEncryptionUtils.decrypt(text, encryptor);
		} catch (Throwable t) {
			throw new BFSecureModuleException(t.getMessage(), t);
		}
	}
	
	@Override
	public void setSecret(String secret) {
		if (secret == null) {
			throw new BFSecureModuleException("Secret must not be null");
		}
		if (!secret.trim()
		        .equals(secret)) {
			throw new BFSecureModuleException("Secret contains whitespaces which are trimable. "
			        + "This can cause problems when using command line tools for encryption, due which is not allowed.");
		}
		if (secret.length() < 8) {
			throw new BFSecureModuleException("Secrets must not be shorter than 8 characters");
		}
		
		if (encryptor.isInitialized()) {
			encryptor = new StandardPBEStringEncryptor();
		}
		encryptor.setPassword(secret);
	}
	
	@Override
	public boolean isEncrypted(String text) {
		if (text == null) {
			return false;
		}
		return PropertyValueEncryptionUtils.isEncryptedValue(text);
	}
}
