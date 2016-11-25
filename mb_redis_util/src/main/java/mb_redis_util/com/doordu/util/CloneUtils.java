package mb_redis_util.com.doordu.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * clone工具类
 * deepClone
 */
public class CloneUtils {
	
	/**
	 * fastjson clone
	 * @param t
	 * @return
	 */
	public static <T> T jsonClone(T t) {
		T result = null;
		if (t == null) {
			return result;
		}
		byte[] bytes = FastJsonSerializerUtils.serialize(t);
		result = FastJsonSerializerUtils.deserialize(bytes);
		return result;
	}
	
	/**
	 * byte clone
	 * @param t
	 * @return
	 * @throws Exception 
	 */
	@SuppressWarnings("unchecked")
	public static <T> T byteClone(T t) throws Exception {
		ByteArrayOutputStream bos = null;
		ObjectOutputStream oos = null;
		
		ByteArrayInputStream bis = null;
		ObjectInputStream ois = null;
		
		T o = null;
		try {
			if (t == null) {
				return o;
			}
			bos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(bos);
			oos.writeObject(t);
			
			bis = new ByteArrayInputStream(bos.toByteArray());
			ois = new ObjectInputStream(bis);
			
			o = (T) ois.readObject();
		} finally {
			if (bos != null) {
				try {
					bos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (oos != null) {
				try {
					oos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (bis != null) {
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return o;
	}
}
