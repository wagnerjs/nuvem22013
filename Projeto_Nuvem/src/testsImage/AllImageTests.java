package testsImage;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;

public class AllImageTests{
	public static AmazonEC2 ec2;
	public static String imgId;
	public static String instanceId;
	public List<String> instancesIds = new ArrayList<String>();
	public static String amiId;
	public static String imgLocation;
	
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

		fillInstanceInformations();
	}
	
	public void fillInstanceInformations(){
		DescribeInstancesResult describeInstancesRequest = ec2.describeInstances();
		List<Reservation> reservations = describeInstancesRequest.getReservations();
        Reservation reserv = describeInstancesRequest.getReservations().get(0);
        Instance instance = reserv.getInstances().get(0);
        
        for(Reservation reservation : reservations){
        	instancesIds.add(reservation.getInstances().get(0).getInstanceId());	
        }
        
        instanceId = instance.getInstanceId();
        imgId = instance.getImageId();
        
//        DescribeImagesResult descImg = ec2.describeImages();
//        List<Image> imgs = descImg.getImages();
//        
//        for(Image img : imgs){
//        	imgLocation = img.getImageLocation();
//        }
//        
//        System.out.println(imgLocation);
	}
	
	/**
	 * Ger�ncia de Imagens:
	 * CreateImage - OK
	 * DeregisterImage - OK
	 * ModifyImageAttribute - N�o implementado
	 * ResetImageAttribute - N�o implementado
	 * DescribeImageAttribute - OK
	 * DescribeImages - OK
	 */
//	@Test
//	public void testCreateImage(){
//		int statusCode = 200;
//		
//		try{
//			CreateImageRequest imgReq = new CreateImageRequest(instanceId,imgId);
//			amiId = ec2.createImage(imgReq).getImageId();
//		}catch(AmazonServiceException ase){
//			System.out.println("Caught Exception: " + ase.getMessage());
//            System.out.println("Reponse Status Code: " + ase.getStatusCode());
//            System.out.println("Error Code: " + ase.getErrorCode());
//            System.out.println("Request ID: " + ase.getRequestId());
//			statusCode = ase.getStatusCode();
//		}
//		
//		Assert.assertEquals("Compativel", true,
//				(statusCode == 200));
//	}
	
//	@Test
//	public void testDeregisterImage(){
//		int statusCode = 200;
//		
//		try{
//			DeregisterImageRequest derImgReq = new DeregisterImageRequest("ami-aa56cd9a");
////			DeregisterImageRequest derImgReq = new DeregisterImageRequest(aimId);
//			ec2.deregisterImage(derImgReq);
//			
//		}catch(AmazonServiceException ase){
//			System.out.println("Caught Exception: " + ase.getMessage());
//            System.out.println("Reponse Status Code: " + ase.getStatusCode());
//            System.out.println("Error Code: " + ase.getErrorCode());
//            System.out.println("Request ID: " + ase.getRequestId());
//			statusCode = ase.getStatusCode();
//		}
//		
//		Assert.assertEquals("Compativel", true,
//				(statusCode == 200));
//	}
	
//	@Test
//	public void testModifyImageAttribute(){
//		int statusCode = 200;
//		
//		try{
//			ModifyImageAttributeRequest modImgReq = new ModifyImageAttributeRequest(amiId,"launchPermission");
//			LaunchPermissionModifications launch = modImgReq.getLaunchPermission();
//			modImgReq.setLaunchPermission(launch);
//			ec2.modifyImageAttribute(modImgReq);
//		}catch(AmazonServiceException ase){
//			System.out.println("Caught Exception: " + ase.getMessage());
//            System.out.println("Reponse Status Code: " + ase.getStatusCode());
//            System.out.println("Error Code: " + ase.getErrorCode());
//            System.out.println("Request ID: " + ase.getRequestId());
//			statusCode = ase.getStatusCode();
//		}
//		
//		Assert.assertEquals("Compativel", true,
//				(statusCode == 200));
//	}
	
//	@Test
//	public void testResetImageAttribute(){
//		int statusCode = 200;
//		
//		try{
//			ResetImageAttributeRequest restImgAttrReq = new ResetImageAttributeRequest(amiId,"launchPermission");
//			ec2.resetImageAttribute(restImgAttrReq);
//		}catch(AmazonServiceException ase){
//			System.out.println("Caught Exception: " + ase.getMessage());
//            System.out.println("Reponse Status Code: " + ase.getStatusCode());
//            System.out.println("Error Code: " + ase.getErrorCode());
//            System.out.println("Request ID: " + ase.getRequestId());
//			statusCode = ase.getStatusCode();
//		}
//		
//		Assert.assertEquals("Compativel", true,
//				(statusCode == 200));
//	}
	
//	@Test
//	public void testDescribeImageAttribute() { 
//		
//		int statusCodeReturn = 200;
//		
//		try{
//			DescribeImageAttributeRequest descImgAttrReq = new DescribeImageAttributeRequest(amiId,"kernel");
//			System.out.println(ec2.describeImageAttribute(descImgAttrReq).getImageAttribute());
//		}catch(AmazonServiceException ase){
//			System.out.println("Caught Exception: " + ase.getMessage());
//            System.out.println("Reponse Status Code: " + ase.getStatusCode());
//            System.out.println("Error Code: " + ase.getErrorCode());
//            System.out.println("Request ID: " + ase.getRequestId());
//			statusCodeReturn = ase.getStatusCode();
//		}
//		
//		Assert.assertEquals("Compativel", true,
//				(statusCodeReturn == 200));
//	}

//	@Test
//	public void testDescribeImages() { 
//		
//		int statusCodeReturn = 200;
//		
//		try{
//			ec2.describeImages();
//		}catch(AmazonServiceException ase){
//			System.out.println("Caught Exception: " + ase.getMessage());
//            System.out.println("Reponse Status Code: " + ase.getStatusCode());
//            System.out.println("Error Code: " + ase.getErrorCode());
//            System.out.println("Request ID: " + ase.getRequestId());
//			statusCodeReturn = ase.getStatusCode();
//		}
//		
//		Assert.assertEquals("Compativel", true,
//				(statusCodeReturn == 200));
//	}
}
