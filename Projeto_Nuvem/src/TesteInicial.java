import javax.xml.ws.http.HTTPException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;

public class TesteInicial{
	public static AmazonEC2 ec2;

	@Before
	public void init() {
		String endPoint, accessKey, secretKey;
		endPoint = "ec2.us-west-2.amazonaws.com";
		accessKey = "AKIAICYSPO5O5U24UGTQ";
		secretKey = "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";
		
		BasicAWSCredentials credentials = new BasicAWSCredentials(
				accessKey, secretKey);
		ec2 = new AmazonEC2Client(credentials);
		ec2.setEndpoint(endPoint);
		
	}
	
	@Test
	public void testDescribeImages() {
		int statusCodeReturn = 200;
		
		try{
			ec2.describeImages();
		}catch(HTTPException e){
			AmazonServiceException exp = new AmazonServiceException("msg",e);
			statusCodeReturn = exp.getStatusCode();
		}
		
		Assert.assertEquals("Compativel", true,
				(statusCodeReturn >= 200 && statusCodeReturn <= 206));
	}
}
