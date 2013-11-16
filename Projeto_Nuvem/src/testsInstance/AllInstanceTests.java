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
import com.amazonaws.services.ec2.model.DescribeInstanceAttributeRequest;
import com.amazonaws.services.ec2.model.DescribeInstancesResult;
import com.amazonaws.services.ec2.model.Instance;
import com.amazonaws.services.ec2.model.MonitorInstancesRequest;
import com.amazonaws.services.ec2.model.RebootInstancesRequest;
import com.amazonaws.services.ec2.model.Reservation;
import com.amazonaws.services.ec2.model.RunInstancesRequest;
import com.amazonaws.services.ec2.model.StartInstancesRequest;
import com.amazonaws.services.ec2.model.StopInstancesRequest;
import com.amazonaws.services.ec2.model.UnmonitorInstancesRequest;


public class AllInstanceTests{
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
        	instancesIds.add(reservation.getInstances().get(0).getInstanceId());
        }
        
        instanceId = instance.getInstanceId();
        imgId = instance.getImageId();
	}
	
	/**
	 * Gerência de Ciclo de Vida das Instâncias:
	 * MonitorInstances - OK
	 * RebootInstances - OK
	 * RequestSpotInstances - Não implementado, pois pode gerar problemas de cobrança
	 * RunInstances - OK
	 * StartInstances - OK
	 * StopInstances - OK
	 * TerminateInstances - Não Implementado, pois termina instâncias para sempre
	 * UnmonitorInstances - OK
	 * DescribeInstanceAttribute - OK
	 * DescribeInstances - OK
	 * DescribeInstanceStatus - OK
	 * DescribeReservedInstances - OK
	 * DescribeReservedInstancesOfferings - OK
	 * DescribeSpotInstanceRequests - OK
	 */
	@Test
	public void testMonitorInstances(){
		int statusCodeReturn = 200;
		
		try{
			MonitorInstancesRequest monitorInstReq = new MonitorInstancesRequest(instancesIds);
			ec2.monitorInstances(monitorInstReq);
		}catch(AmazonServiceException ase){
			System.out.println("Caught Exception: " + ase.getMessage());
			System.out.println("Reponse Status Code: " + ase.getStatusCode());
			System.out.println("Error Code: " + ase.getErrorCode());
			System.out.println("Request ID: " + ase.getRequestId());
			statusCodeReturn = ase.getStatusCode();
		}
		
		Assert.assertEquals("Compativel", true,(statusCodeReturn == 200));
	}
	
	@Test
	public void testRebootInstances(){
		int statusCodeReturn = 200;
		
		try{
			RebootInstancesRequest rebootInstReq = new RebootInstancesRequest(instancesIds);
			ec2.rebootInstances(rebootInstReq);		
		}catch(AmazonServiceException ase){
			System.out.println("Caught Exception: " + ase.getMessage());
			System.out.println("Reponse Status Code: " + ase.getStatusCode());
			System.out.println("Error Code: " + ase.getErrorCode());
			System.out.println("Request ID: " + ase.getRequestId());
			statusCodeReturn = ase.getStatusCode();
		}
		
		Assert.assertEquals("Compativel", true,(statusCodeReturn == 200));
	}
	
	@Test
	public void testRunInstances(){
		int statusCodeReturn = 200;
		
		try{
			RunInstancesRequest runInstReq = new RunInstancesRequest(imgId, 1, 1); 
			ec2.runInstances(runInstReq);
		}catch(AmazonServiceException ase){
			System.out.println("Caught Exception: " + ase.getMessage());
            System.out.println("Reponse Status Code: " + ase.getStatusCode());
            System.out.println("Error Code: " + ase.getErrorCode());
            System.out.println("Request ID: " + ase.getRequestId());
			statusCodeReturn = ase.getStatusCode();
		}
		
		Assert.assertEquals("Compativel", true,(statusCodeReturn == 200));	
	}
	
	@Test
	public void testStartInstances(){
		int statusCodeReturn = 200;
		
		try{
			StartInstancesRequest startInstReq = new StartInstancesRequest(instancesIds);
			ec2.startInstances(startInstReq);
		}catch(AmazonServiceException ase){
			System.out.println("Caught Exception: " + ase.getMessage());
            System.out.println("Reponse Status Code: " + ase.getStatusCode());
            System.out.println("Error Code: " + ase.getErrorCode());
            System.out.println("Request ID: " + ase.getRequestId());
			statusCodeReturn = ase.getStatusCode();
		}
		
		Assert.assertEquals("Compativel", true,(statusCodeReturn == 200));	
	}
	
	@Test
	public void testStopInstances(){
		int statusCodeReturn = 200;
		
		try{
			StopInstancesRequest stopInstReq = new StopInstancesRequest(instancesIds);
			ec2.stopInstances(stopInstReq);
		}catch(AmazonServiceException ase){
			System.out.println("Caught Exception: " + ase.getMessage());
            System.out.println("Reponse Status Code: " + ase.getStatusCode());
            System.out.println("Error Code: " + ase.getErrorCode());
            System.out.println("Request ID: " + ase.getRequestId());
			statusCodeReturn = ase.getStatusCode();
		}
		
		Assert.assertEquals("Compativel", true,(statusCodeReturn == 200));	
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
	
	@Test
	public void testDescribreInstanceAttribute(){
		int statusCodeReturn = 200;
		
		try{
			DescribeInstanceAttributeRequest descInstAttrReq = new DescribeInstanceAttributeRequest(instanceId, "kernel");
			ec2.describeInstanceAttribute(descInstAttrReq);
		}catch(AmazonServiceException ase){
			System.out.println("Caught Exception: " + ase.getMessage());
            System.out.println("Reponse Status Code: " + ase.getStatusCode());
            System.out.println("Error Code: " + ase.getErrorCode());
            System.out.println("Request ID: " + ase.getRequestId());
			statusCodeReturn = ase.getStatusCode();
		}
		
		Assert.assertEquals(true,(statusCodeReturn == 200));	
	}
	
	@Test
	public void testDescribeInstances(){
		int statusCodeReturn = 200;
		
		try{
			ec2.describeInstances();
		}catch(AmazonServiceException ase){
			System.out.println("Caught Exception: " + ase.getMessage());
            System.out.println("Reponse Status Code: " + ase.getStatusCode());
            System.out.println("Error Code: " + ase.getErrorCode());
            System.out.println("Request ID: " + ase.getRequestId());
			statusCodeReturn = ase.getStatusCode();
		}
		
		Assert.assertEquals(true,(statusCodeReturn == 200));	
	}
	
	@Test
	public void testDescribeInstanceStatus(){
		int statusCodeReturn = 200;
		
		try{
			ec2.describeInstanceStatus();
		}catch(AmazonServiceException ase){
			System.out.println("Caught Exception: " + ase.getMessage());
            System.out.println("Reponse Status Code: " + ase.getStatusCode());
            System.out.println("Error Code: " + ase.getErrorCode());
            System.out.println("Request ID: " + ase.getRequestId());
			statusCodeReturn = ase.getStatusCode();
		}
		
		Assert.assertEquals(true,(statusCodeReturn == 200));	
	}
	
	@Test
	public void testDescribeReservedInstances(){
		int statusCodeReturn = 200;
		
		try{
			ec2.describeReservedInstances();
		}catch(AmazonServiceException ase){
			System.out.println("Caught Exception: " + ase.getMessage());
            System.out.println("Reponse Status Code: " + ase.getStatusCode());
            System.out.println("Error Code: " + ase.getErrorCode());
            System.out.println("Request ID: " + ase.getRequestId());
			statusCodeReturn = ase.getStatusCode();
		}
		
		Assert.assertEquals(true,(statusCodeReturn == 200));	
	}
	
	@Test
	public void testDescribeReservedInstancesOfferings(){
		int statusCodeReturn = 200;
		
		try{
			ec2.describeReservedInstancesOfferings();
		}catch(AmazonServiceException ase){
			System.out.println("Caught Exception: " + ase.getMessage());
            System.out.println("Reponse Status Code: " + ase.getStatusCode());
            System.out.println("Error Code: " + ase.getErrorCode());
            System.out.println("Request ID: " + ase.getRequestId());
			statusCodeReturn = ase.getStatusCode();
		}
		
		Assert.assertEquals(true,(statusCodeReturn == 200));	
	}

	@Test
	public void testSpotInstanceRequests(){
		int statusCodeReturn = 200;
		
		try{
			ec2.describeSpotInstanceRequests();
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
