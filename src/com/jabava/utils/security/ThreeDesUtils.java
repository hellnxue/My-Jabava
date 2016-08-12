package com.jabava.utils.security;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/*字符串 DESede(3DES) 加密 
 * ECB模式/使用PKCS7方式填充不足位,目前给的密钥是192位 
 * 3DES（即Triple DES）是DES向AES过渡的加密算法（1999年，NIST将3-DES指定为过渡的 
 * 加密标准），是DES的一个更安全的变形。它以DES为基本模块，通过组合分组方法设计出分组加 
 * 密算法，其具体实现如下：设Ek()和Dk()代表DES算法的加密和解密过程，K代表DES算法使用的 
 * 密钥，P代表明文，C代表密表，这样， 
 * 3DES加密过程为：C=Ek3(Dk2(Ek1(P))) 
 * 3DES解密过程为：P=Dk1((EK2(Dk3(C))) 
 * */  
public class ThreeDesUtils {
	private static final String Algorithm = "DESede"; // 定义 加密算法,可用
	 
	
    // DES,DESede,Blowfish
 
    // keybyte为加密密钥，长度为24字节
    // src为被加密的数据缓冲区（源）
    public static String encryptMode( byte[] src)
    {
        try
        {
            // 生成密钥
            SecretKey deskey = new SecretKeySpec(new byte[24], Algorithm);
            // 加密
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.ENCRYPT_MODE, deskey);
            // 开始加密运算
            byte[] encryptedByteArray = c1.doFinal(src);
            // 加密运算之后 将byte[]转化为base64的String
            BASE64Encoder enc = new BASE64Encoder();
            return enc.encode(encryptedByteArray);
        }
        catch (java.security.NoSuchAlgorithmException e1)
        {
            e1.printStackTrace();
        }
        catch (javax.crypto.NoSuchPaddingException e2)
        {
            e2.printStackTrace();
        }
        catch (java.lang.Exception e3)
        {
            e3.printStackTrace();
        }
        return null;
    }
     
    // keybyte为加密密钥，长度为24字节
    // src为加密后的缓冲区
    public static String decryptMode(String src)
    {
        try {
            // 生成密钥
            SecretKey deskey = new SecretKeySpec(new byte[24], Algorithm);
            // 解密
            Cipher c1 = Cipher.getInstance(Algorithm);
            c1.init(Cipher.DECRYPT_MODE, deskey);
            // 解密运算之前
            BASE64Decoder dec = new BASE64Decoder();
            byte[] encryptedByteArray = dec.decodeBuffer(src);
            // 解密运算 将base64的String转化为byte[]
            byte[] result=c1.doFinal(encryptedByteArray);
            return new String(result,"UTF-8");
 
        } catch (java.security.NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (javax.crypto.NoSuchPaddingException e2) {
            e2.printStackTrace();
        } catch (java.lang.Exception e3) {
            e3.printStackTrace();
        }
        return null;
    }

}
