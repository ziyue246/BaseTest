package common.utils;



import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.List;

/**
 * 字符编码类
 *
 * @author grs
 * @since 2011年7月
 */
public class EncoderUtil {



	/**
	 * 特殊字符集
	 */
	private static final List<Character> SPECIAL_CHAR = Arrays.asList('!', '?',
			//	'<', '>', '【', '】', '“', '”', '°', '{', '|', '}', '~', '?', '?',
			'?', '¤', '?', '?', '§', '¨', '?', '?', '?', '?', '?', '±', '?',
			'?', '?', '?', '?', '·', '?', '?', '?', '?', '?', '?', '?', '?',
			'?', '?', '?', '?', '?', '?', '?', '?', '?', '?', '?', '?', '?',
			'?', '?', '?', '?', '?', '?', '?', '?', '?', '?', '×', '?', '?',
			'?', '?', '?', '?', '?', '?', 'à', 'á', '?', '?', '?', '?', '?',
			'?', 'è', 'é', 'ê', '?', 'ì', 'í', '?', '?', '?', '?', 'ò', 'ó',
			'?', '?', '?', '÷', '?', 'ù', 'ú', '?', 'ü', '?', '?', '?', '[',
			']', ' ');
	private static final String CODE = "%";
	private static final String CODE_25 = "%25";

	// 对中英文和特殊字符的编码
	private static String encode2Hex(String text, String encode, boolean add) {
		if (encode.contains("iso")) {
			encode = "utf-8";
		}
		StringBuffer result = new StringBuffer();
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			if (c >= 0 && c <= 255) {
				if (SPECIAL_CHAR.contains(c))
					result.append(add ? CODE_25 : CODE).append(
							Integer.toHexString(c).toUpperCase());
				else
					result.append(c);
			} else {
				byte[] b = new byte[0];
				try {
					b = Character.toString(c).getBytes(encode);
				} catch (Exception e) {
					e.printStackTrace();
				}
				for (int j = 0; j < b.length; j++) {
					int k = b[j];
					if (k < 0)
						k += 256;
					result.append(add ? CODE_25 : CODE).append(
							Integer.toHexString(k).toUpperCase());
				}
				b = null;
			}
		}
		return result.toString();
	}
	/**
	 *
	 * @param content
	 * @param encode
	 * @return
	 */
	public static String urlEncoder(String content,String encode){
		try {
			return  URLEncoder.encode(content, encode);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String urlDecoder(String content,String encode){
		try {
			return URLDecoder.decode(content, encode);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * 将16进制编码转换为中文
	 *
	 * @param text
	 * @return
	 */
	public static String hex2String(String text) {
		byte[] baKeyword = new byte[text.length() / 2];
		try {
			for (int i = 0; i < baKeyword.length; i++) {
				baKeyword[i] = (byte) (0xff & Integer.parseInt(
						text.substring(i * 2, i * 2 + 2), 16));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			text = new String(baKeyword, "utf-8");
		} catch (Exception e1) {
			e1.printStackTrace();
		} finally {
			baKeyword = null;
		}
		return text;
	}

	/**
	 * 中英文编码处理
	 *
	 * @param words
	 * @param encode
	 * @return
	 */
	public static String encodeKeyWords(String words, String encode, boolean add) {
		StringBuffer codes = new StringBuffer();
//		StringBuffer word = new StringBuffer();
		int i = 0;
//		words += ' ';
		while (i < words.length()) {
			char c = words.charAt(i);
			codes.append(encode2Hex(String.valueOf(c), encode, add));
//			if (words.charAt(i) != ' ') {
//				word.append(words.charAt(i));
//			} else {
//				codes.append(encode2Hex(word+" ", encode, add));
//				word.append("");
//			}
			i++;
		}
		return codes.toString();
	}

	public static String encodeKeyWords(String words, String encoding) {
		return encodeKeyWords(words, encoding, false);
	}

	// base64
	static private char[] alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/="
			.toCharArray();
	public static char[] getBase64Encode(byte[] data) {
		char[] out = new char[((data.length + 2) / 3) * 4];
		for (int i = 0, index = 0; i < data.length; i += 3, index += 4) {
			boolean quad = false;
			boolean trip = false;
			int val = (0xFF & (int) data[i]);
			val <<= 8;
			if ((i + 1) < data.length) {
				val |= (0xFF & (int) data[i + 1]);
				trip = true;
			}
			val <<= 8;
			if ((i + 2) < data.length) {
				val |= (0xFF & (int) data[i + 2]);
				quad = true;
			}
			out[index + 3] = alphabet[(quad ? (val & 0x3F) : 64)];
			val >>= 6;
			out[index + 2] = alphabet[(trip ? (val & 0x3F) : 64)];
			val >>= 6;
			out[index + 1] = alphabet[val & 0x3F];
			val >>= 6;
			out[index + 0] = alphabet[val & 0x3F];
		}
		return out;

	}

}
