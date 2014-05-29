package theworldnews.utilities.hashing;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

import theworldnews.database.news.queries.DisplayQueries;

public class Sha256 {

	public static String hashSha256(String input) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(input.getBytes("UTF-8"));
			StringBuffer asString = new StringBuffer();
			for (byte i : hash) {
				String hex = Integer.toHexString(0xff & i);
				if (hex.length() == 1)
					asString.append('0');
				asString.append(hex);
			}

			return asString.toString();
			// CRAP
		} catch (UnsupportedEncodingException e) {
			Logger lgr = Logger.getLogger(DisplayQueries.class.getName());
			lgr.log(Level.SEVERE, e.getMessage(), e);
		} catch (NoSuchAlgorithmException e) {
			Logger lgr = Logger.getLogger(DisplayQueries.class.getName());
			lgr.log(Level.SEVERE, e.getMessage(), e);
		}

		return "";
	}
}
