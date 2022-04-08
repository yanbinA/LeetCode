package rsa;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import com.sun.xml.internal.bind.api.impl.NameConverter;
import org.bouncycastle.jcajce.spec.SM2ParameterSpec;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import java.io.*;

import java.security.*;

import java.security.cert.Certificate;

import java.security.cert.CertificateException;

import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * 首先需要通过jdk的keytool生成秘钥库等，需确保机器中安装有jdk
 * 0.在本代码环境下,执行1命令时,秘钥库的密码和证书的密码须一致，请自行修改路径
 * 请确保路径下无名为mykeystore.keystore的文件，否则可能会报密码错等
 * 1.keytool -genkey -alias mykey -keyalg RSA -keystore D:\tools\mykeystore.keystore -keysize 1024 -validity 3650
 * 2.keytool -export -alias mykey -keystore  D:\tools\mykeystore.keystore -file  D:\tools\mykey.cer
 * 3.代码中的秘钥库密码为keystore,请自行修改
 *
 */

public class RSACertHelper {
    public static void main(String[] args) {
        String path = "D:\\tools\\";

        String keyStoreFile = "mykeystore.keystore";

        String passwd = "secret";

        String keyAlias = "mykey";

        String pfxFile = "mykey.pfx";

        String cerFile = "mykey.cer";

        System.out.println("请确保已执行完注释中的两条命令再执行本代码\n\n");

        System.out.println("1.开始生成PFX文件");

        coverToPfx(path + keyStoreFile, passwd, keyAlias, path + pfxFile);

        System.out.println("===============================================================");

        System.out.println("2.开始提取.cer中的公钥字符串");

        String cerStr = getPubStr(path + cerFile);

        System.out.println("从.cer文件中提取的公钥字符串如下:");

        System.out.println(cerStr);

        Map<String, Object> key = getKey(path + pfxFile, passwd);
        PrivateKey prikey = (PrivateKey) key.get("privateKey");
        PublicKey publicKey = (PublicKey) key.get("publicKey");
        byte[] bytes = signSha256withRSA("123".getBytes(), "userId".getBytes(), prikey);
        String sign = Hex.toHexString(bytes);
        System.out.println(sign);
        boolean b = verifySha256withRSA("123".getBytes(), "userId".getBytes(), Hex.decode(sign), publicKey);
        System.out.println("原始验签:"+b);

        byte[] sign1 = sign(prikey, "123");
        String hexString = Hex.toHexString(sign1);
        System.out.println(hexString);
        System.out.println("原始验签1:" + verifySign(publicKey, "123",  Hex.decode(hexString)));
        System.out.println("原始验签2:" + verifySign(publicKey, "123", sign1));

    }

    public static Map<String, Object> getKey(String cerUrl, String cerPassword) {
        Map<String, Object> map = new HashMap<>();
        try {
            Security.addProvider(new BouncyCastleProvider());
            KeyStore ks = KeyStore.getInstance("PKCS12", "BC");
            FileInputStream fis = new FileInputStream(cerUrl);
            getKey(cerPassword, map, ks, fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    public static Map<String, Object> getKey(byte[] certBytes, String cerPassword) {
        Map<String, Object> map = new HashMap<>();
        try {
            Security.addProvider(new BouncyCastleProvider());
            KeyStore ks = KeyStore.getInstance("PKCS12", "BC");
            ByteArrayInputStream inputStream = new ByteArrayInputStream(certBytes);
            getKey(cerPassword, map, ks, inputStream);
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return map;
    }

    private static void getKey(String cerPassword, Map<String, Object> map, KeyStore ks, InputStream inputStream) throws IOException, NoSuchAlgorithmException, CertificateException, KeyStoreException, UnrecoverableKeyException {
        char[] nPassword = null;
        if ((cerPassword == null) || cerPassword.trim().equals("")) {
            nPassword = null;
        } else {
            nPassword = cerPassword.toCharArray();
        }
        ks.load(inputStream, nPassword);
        inputStream.close();
        Enumeration<String> enumas = ks.aliases();
        String keyAlias = null;
        if (enumas.hasMoreElements()) {
            keyAlias = enumas.nextElement();
        }
        PrivateKey prikey = (PrivateKey) ks.getKey(keyAlias, nPassword);
        Certificate cert = ks.getCertificate(keyAlias);
        PublicKey pubkey = cert.getPublicKey();
        map.put("publicKey", pubkey);
        map.put("privateKey", prikey);
        map.put("certificate", cert);
        System.out.println(getCertId((X509Certificate) cert));
    }

    public static String getCertId(X509Certificate x509) {
        return x509.getSerialNumber().toString(16);
    }

//1生成pfx文件

    public static void coverToPfx(String keyStoreFile, String passwd, String keyAlias, String pfxFile) {
        try {
            KeyStore inputKeyStore = KeyStore.getInstance("JKS");

            FileInputStream fis = new FileInputStream(keyStoreFile);

            char[] nPassword = null;

            if ((passwd == null)

                    || passwd.trim().equals("")) {
                nPassword = null;

            } else {
                nPassword = passwd.toCharArray();

            }

            inputKeyStore.load(fis, nPassword);

            fis.close();

            KeyStore outputKeyStore = KeyStore.getInstance("PKCS12");

            outputKeyStore.load(null, passwd.toCharArray());

// System.out.println("alias=[" + keyAlias + "]");

            if (inputKeyStore.isKeyEntry(keyAlias)) {
                Key key = inputKeyStore.getKey(keyAlias, passwd.toCharArray());

                Certificate[] certChain = inputKeyStore

                        .getCertificateChain(keyAlias);

                outputKeyStore.setKeyEntry(keyAlias, key, passwd

                        .toCharArray(), certChain);

            }

            FileOutputStream out = new FileOutputStream(pfxFile);

            outputKeyStore.store(out, nPassword);

            out.close();

            System.out.println("已生成PFX文件" + pfxFile);

        } catch (Exception e) {
            e.printStackTrace();

        }

    }

//2获取公钥字符串

    public static String getPubStr(String cerFile) {
        String key = "";

// 读取证书文件

        try {
            CertificateFactory cf = CertificateFactory.getInstance("X.509");

            FileInputStream in = new FileInputStream(cerFile);

//生成一个证书对象并使用从输入流 inStream 中读取的数据对它进行初始化。

            Certificate c = cf.generateCertificate(in);

            PublicKey publicKey = c.getPublicKey();

            key = Base64.encode(publicKey.getEncoded());

        } catch (CertificateException e) {
            e.printStackTrace();

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        }

        return key;

    }

    static {
        if (Security.getProvider("BC") == null) {
            Security.addProvider(new BouncyCastleProvider());
        }
    }

    /**
     *
     * @param msg
     * @param userId
     * @param privateKey
     * @return r||s，直接拼接byte数组的rs
     */
    public static byte[] signSha256withRSA(byte[] msg, byte[] userId, PrivateKey privateKey) {
//		return rsAsn1ToPlainByteArray(signSha256withRSAAsn1Rs(msg, userId, privateKey));
        return signSha256withRSAAsn1Rs(msg, userId, privateKey);
    }

    /**
     *
     * @param msg
     * @param userId
     * @param privateKey
     * @return rs in <b>asn1 format</b>
     */
    public static byte[] signSha256withRSAAsn1Rs(byte[] msg, byte[] userId, PrivateKey privateKey) {
        try {
            SM2ParameterSpec parameterSpec = new SM2ParameterSpec(userId);
            Signature signer = Signature.getInstance("Sha256withRSA", "BC");
            //signer.setParameter(parameterSpec);
            signer.initSign(privateKey, new SecureRandom());
            signer.update(msg, 0, msg.length);
            byte[] sig = signer.sign();
            return sig;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param msg
     * @param userId
     * @param rs
     *            r||s，直接拼接byte数组的rs
     * @param publicKey
     * @return
     */
    public static boolean verifySha256withRSA(byte[] msg, byte[] userId, byte[] rs, PublicKey publicKey) {
//		return verifySha256withRSAAsn1Rs(msg, userId, rsPlainByteArrayToAsn1(rs), publicKey);
        return verifySha256withRSAAsn1Rs(msg, userId, rs, publicKey);
    }

    /**
     *
     * @param msg
     * @param userId
     * @param rs
     *            in <b>asn1 format</b>
     * @param publicKey
     * @return
     */
    public static boolean verifySha256withRSAAsn1Rs(byte[] msg, byte[] userId, byte[] rs, PublicKey publicKey) {
        try {
            SM2ParameterSpec parameterSpec = new SM2ParameterSpec(userId);
            Signature verifier = Signature.getInstance("Sha256withRSA", "BC");
            //verifier.setParameter(parameterSpec);
            verifier.initVerify(publicKey);
            verifier.update(msg, 0, msg.length);
            return verifier.verify(rs);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static final String SIGNATURE_ALGORITHM = "SHA256withRSA";
    public static final String ENCODE_ALGORITHM = "SHA-256";
    public static final String PLAIN_TEXT = "test string";

    /**
     * 签名
     * @param privateKey  私钥
     * @param plainText 明文
     * @return
     */
    public static byte[] sign(PrivateKey privateKey, String plainText) {
        MessageDigest messageDigest;
        byte[] signed = null;
        try {
            messageDigest = MessageDigest.getInstance(ENCODE_ALGORITHM);
            messageDigest.update(plainText.getBytes());
            byte[] outputDigest_sign = messageDigest.digest();
            System.out.println("SHA-256编码后-----》"
                    + Base64.encode(outputDigest_sign));
            Signature Sign = Signature.getInstance(SIGNATURE_ALGORITHM);
            Sign.initSign(privateKey);
            Sign.update(outputDigest_sign);
            signed = Sign.sign();
            System.out.println("SHA256withRSA签名后-----》"
                    + Base64.encode(signed));
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            e.printStackTrace();
        }
        return signed;
    }

    /**
     * 验签
     * @param publicKey 公钥
     * @param plain_text 明文
     * @param signed 签名
     */
    public static boolean verifySign(PublicKey publicKey, String plain_text, byte[] signed) {
        MessageDigest messageDigest;
        boolean SignedSuccess = false;
        try {
            messageDigest = MessageDigest.getInstance(ENCODE_ALGORITHM);
            messageDigest.update(plain_text.getBytes());
            byte[] outputDigest_verify = messageDigest.digest();
            //System.out.println("SHA-256加密后-----》" +bytesToHexString(outputDigest_verify));
            Signature verifySign = Signature.getInstance(SIGNATURE_ALGORITHM);
            verifySign.initVerify(publicKey);
            verifySign.update(outputDigest_verify);
            SignedSuccess = verifySign.verify(signed);
            System.out.println("验证成功？---" + SignedSuccess);

        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException e) {
            e.printStackTrace();
        }
        return SignedSuccess;
    }

}
