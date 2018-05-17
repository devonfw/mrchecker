package com.capgemini.mrchecker.test.core.base.encryption;

import com.capgemini.mrchecker.test.core.exceptions.BFSecureModuleException;

/**
 * Interface defining functionality to be provided by data encryption services
 * (services that need to handle encrypted, sensitive data that is needed for
 * testing purposes).
 * 
 * @author Lukasz Stefaniszyn, Capgemini
 * @author Marek Puchalski, Capgemini
 */
public interface IDataEncryptionService {
	
	/**
	 * Encrypts the given, not null text. The encrypted text needs to be decorated with e.g.
	 * ENC(<here the encrypted value>), so in the future, when we need to decode the text,
	 * we can distinguish between cipher text and plain text. Every data encryption instance can define
	 * its own decorators (prefix and/or suffix) to be added this way.
	 * 
	 * @param text
	 *          Text to be encrypted.
	 * @return encrypted and decorated text as string
	 * @throws BFSecureModuleException
	 *           in case of encryption problems.
	 */
	String encrypt(String text) throws BFSecureModuleException;
	
	/**
	 * Decrypts the given text. The cipher text must be decorated by the prefix and/or suffix
	 * defined by the data encryption service. If the text is not decorated, BFSecureModuleException
	 * will be thrown. Please call isEncrypted first, if you are unsure, if you have plaintext or
	 * cipher text.
	 * 
	 * @param text
	 *          decorated cipher text.
	 * @return decrypted text as string.
	 * @throws BFSecureModuleException
	 *           in case of decryption problems.
	 */
	String decrypt(String text) throws BFSecureModuleException;
	
	/**
	 * @param text
	 *          plain text or cipher text.
	 * @return true, if the text is encrypted (is cipher text). This means only, that the text
	 *         is decorated with a prefix and/or suffix the service defines. false otherwise.
	 */
	boolean isEncrypted(String text);
	
	/**
	 * Sets the secret for data encryption/decryption. Please note: this should not be a
	 * password. Passwords are usually meant to be remembered by humans and they do not contain
	 * to much entropy. A secret should have high entropy (as you will not need to remember it).
	 * Secrets can usually be stored in files, but they should never be checked into repository!
	 * Pass it over to your team members over a different channel. NOTE: The service providing
	 * an implementation can set additional rules to improve the quality of the secrets you
	 * pass over to this method like e.g. the minimal accepted length of the secret.
	 * 
	 * @param secret
	 *          secret to be used for data encryption/decryption. NOTE: "password1" is a bad
	 *          secret. Something like "IM%&@N8932m>|j9123nG&kyYWB" is.
	 * @throws BFSecureModuleException
	 *           error setting the secret value (usually quality
	 *           rules are not fulfilled.
	 */
	public void setSecret(String secret) throws BFSecureModuleException;
	
}
