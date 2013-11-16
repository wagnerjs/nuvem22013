package testsInstance;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.ec2.AmazonEC2;
import com.amazonaws.services.ec2.AmazonEC2Client;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.UnmonitorInstancesRequest;

/**
 * Funciona de qualquer forma
 * @author itallorossi
 *
 */

public class UnmonitorInstances {
	public static AmazonEC2 ec2;
	public static String imgId;
	public static String instanceId;
	public List<String> instancesIds = new ArrayList<String>();
	
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
        	if(!reservation.getInstances().get(0).getState().getName().equals("terminated")){
        		instancesIds.add(reservation.getInstances().get(0).getInstanceId());
        	}
        }
        
        instanceId = instance.getInstanceId();
        imgId = instance.getImageId();
	}
	
	@Test
	public void testUnmonitorInstances(){
		int statusCodeReturn = 200;
		
		try{
			UnmonitorInstancesRequest unmonInstReq = new UnmonitorInstancesRequest(instancesIds);
			ec2.unmonitorInstances(unmonInstReq);
		}catch(AmazonServiceException ase){
			System.out.println("Caught Exception: " + ase.getMessage());
            System.out.println("Reponse Status Code: " + ase.getStatusCode());
            System.out.println("Error Code: " + ase.getErrorCode());
            System.out.println("Request ID: " + ase.getRequestId());
			statusCodeReturn = ase.getStatusCode();
		}
		
		Assert.assertEquals(true,(statusCodeReturn == 200));	
	}	
}
