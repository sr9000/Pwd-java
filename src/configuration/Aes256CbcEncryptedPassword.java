package configuration;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Aes256CbcEncryptedPassword {
  public String keyDerivationFunction;
  public String messageDigestFunction;
  public String initializationVector;
  public String saltForKeyDerivationFunction;
  public String encryptedData;
}
