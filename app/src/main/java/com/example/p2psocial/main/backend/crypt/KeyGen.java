package backend.crypt;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Key;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import android.content.Context;

public class KeyGen implements Serializable {

    private static final long serialVersionUID = 123456789L;
    private static final String RSA = "RSA";

    private KeyPair kp;
    private Context context;

    public KeyGen(Context context) {
        this.context = context;
    }

    public void generateRSAKkeyPair() throws Exception {
        SecureRandom secureRandom = new SecureRandom();
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA);
        keyPairGenerator.initialize(4096, secureRandom);
        kp = keyPairGenerator.generateKeyPair();

        storeKey(kp.getPrivate(), "id_rsa");
        storeKey(kp.getPublic(), "id_rsa.pub");
    }

    private void storeKey(Key key, String fileName) throws Exception {
        byte[] keyBytes = key.getEncoded();
        byte[] encodedKey = Base64.getEncoder().encode(keyBytes);

        try (FileOutputStream fos = context.openFileOutput(fileName, Context.MODE_PRIVATE)) {
            fos.write(encodedKey);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean checkKeysExist() {
        return context.getFileStreamPath("id_rsa").exists() && context.getFileStreamPath("id_rsa.pub").exists();
    }

    //##########################
    // Public key functions
    //##########################

    // connverts encoded public key to publicKey object
    public PublicKey convertPublicKey(String pubKeyStr) {

        // Decode the Base64-encoded public key
        byte[] decodedKey = Base64.getDecoder().decode(pubKeyStr);

        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedKey);

        KeyFactory keyFactory;

        try {

            keyFactory = KeyFactory.getInstance("RSA");
            PublicKey pubKey = keyFactory.generatePublic(keySpec);
            return pubKey;

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {

            e.printStackTrace();

        }

        return null;

    }

    public PublicKey getPublicKey() {

        // read public key from file
        Path filePath = Paths.get("./data/id_rsa.pub").toAbsolutePath();

        String key;

        try {

            key = new String(Files.readAllBytes(filePath), StandardCharsets.UTF_8);
            PublicKey pubkey = convertPublicKey(key);
            return pubkey;

        } catch (IOException e) {

            e.printStackTrace();

        }

        return null;
    }

    public String getPublicKeyStr() {

        // read public key from file
        Path filePath = Paths.get("./data/id_rsa.pub").toAbsolutePath();

        String pubKeyStr;

        try {

            pubKeyStr = new String(Files.readAllBytes(filePath), StandardCharsets.UTF_8);
            return pubKeyStr;

        } catch (IOException e) {

            e.printStackTrace();

        }

        return null;
    }



    //##########################
    // Private key functions
    //##########################


    public String getPrivateKeyString() {

        // read public key from file
        Path filePath = Paths.get("./data/id_rsa").toAbsolutePath();

        String privKeyString;

        try {

            privKeyString = new String(Files.readAllBytes(filePath), StandardCharsets.UTF_8);
            return privKeyString;

        } catch (IOException e) {

            e.printStackTrace();

        }

        return null;
    }


    // connverts encoded public key to publicKey object
    public PrivateKey convertPrivateKey(String privKeyString) {

        // Decode the Base64-encoded public key
        byte[] decodedKey = Base64.getDecoder().decode(privKeyString);

        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodedKey);

        KeyFactory keyFactory;

        try {

            keyFactory = KeyFactory.getInstance("RSA");
            PrivateKey privKey = keyFactory.generatePrivate(keySpec);
            return privKey;

        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {

            e.printStackTrace();

        }

        return null;

    }

    public PrivateKey getPrivatKey() {

        // read public key from file
        Path filePath = Paths.get("./data/id_rsa").toAbsolutePath();

        String key;

        try {

            key = new String(Files.readAllBytes(filePath), StandardCharsets.UTF_8);
            PrivateKey privKey = convertPrivateKey(key);
            return privKey;

        } catch (IOException e) {

            e.printStackTrace();

        }

        return null;
    }

}