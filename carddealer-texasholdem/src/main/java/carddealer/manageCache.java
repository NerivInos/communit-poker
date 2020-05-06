/**
 * 
 */
package carddealer;


import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import javax.imageio.ImageIO;



/**
 * @author vps
 *
 */


public class manageCache {

	String uFilePath = "/opt/pokercache/pokerUsers.csv";
	//File objUserFile = new File(uFilePath);
	String cFilePath = "/opt/pokercache/shuffledCards.csv";
	//File objCardsFile = new File(cFilePath);
	String mapUCFilePath = "/opt/pokercache/mapUserCards.csv";
	//File objMapUCFile = new File(mapUCFilePath);
	String flopCardsFilePath = "/opt/pokercache/flopCards.csv";
	//File objFlopCardsFile = new File(flopCardsFilePath);
	
	String turnCardFilePath = "/opt/pokercache/turnCard.csv";
	//File objTurnCardFile = new File(turnCardFilePath);
	
	String riverCardFilePath = "/opt/pokercache/riverCard.csv";
	//File objRiverCardFile = new File(riverCardFilePath);	

	String dealerFilePath = "/opt/pokercache/dealerName.csv";
	//File objDealerFile = new File(dealerFilePath);	
	
	String imageFileDir = "/opt/pokercache/images";
	String userBaseDir = "/opt/pokercache";
	String teamDir = "";
	String gameDir = "";
	String singleBlindUserFileName = "singleBlindUserName.csv";
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
	private String generateFullFilePath(String fileName) {
		return userBaseDir + teamDir + gameDir + "/" + fileName;
	}
	
	protected void addUserCodeToCache(String userName, String unqStr) throws Exception{
		System.out.println("addUserCodeToCache");
		
		addToFile(generateFullFilePath(userName),unqStr);
	}
	
	protected String getUserCodeFromCache(String userName) throws Exception{
	System.out.println("getUserCodeFromCache");
	return readFile(generateFullFilePath(userName));	
	} 
	
	protected byte[] getFlopCardImages(String imgFN1, String imgFN2,String imgFN3) throws Exception{
		BufferedImage bImage = joinBufferedImage(joinBufferedImage(getBfrdImg(imgFN1), getBfrdImg(imgFN2)),getBfrdImg(imgFN3));
		return getByteArray(bImage);
	   }
	
	protected BufferedImage getBfrdImg(String imageFileName) throws Exception{
		String imageFilePath = imageFileDir + "/" + imageFileName + ".png";
		return ImageIO.read(new File(imageFilePath));      	
	}
	
	protected byte[] getByteArray(BufferedImage bImage) throws Exception{
		  ByteArrayOutputStream bos = new ByteArrayOutputStream();
	      ImageIO.write(bImage, "png", bos );
	      byte [] data = bos.toByteArray();
	      return data;     	
	}
	
	protected byte[] getUserCardImages(String imgFN1, String imgFN2) throws Exception{
		BufferedImage bImage = joinBufferedImage(getBfrdImg(imgFN1), getBfrdImg(imgFN2));
		return getByteArray(bImage);
	}
	
	protected byte [] getCardImages(String [] cards) throws Exception {
		BufferedImage bImage = null;
		bImage = getBfrdImg(cards[0]);
		for (int x=1;x<cards.length;x++) {
			bImage = joinBufferedImage(bImage,getBfrdImg(cards[x]));
		}		
		return getByteArray(bImage);
	}
	
	protected static BufferedImage joinBufferedImage(BufferedImage img1, BufferedImage img2) {
		    //int offset = 2;
		    int width = img1.getWidth() + img2.getWidth();
		    int height = img1.getHeight();// + img2.getHeight();
		    BufferedImage newImage = new BufferedImage(width, height,
		        BufferedImage.TYPE_INT_ARGB);
		    Graphics2D g2 = newImage.createGraphics();
		    Color oldColor = g2.getColor();
		    g2.setPaint(Color.WHITE);
		    g2.fillRect(0, 0, width, height);
		    g2.setColor(oldColor);
		    g2.drawImage(img1, null, 0, 0);
		    g2.drawImage(img2, null, img1.getWidth(),0);
		    g2.scale(-20.00, -20.00);
		    g2.dispose();
		    return newImage;
		  }
	
	protected static BufferedImage joinBufferedImageVERT(BufferedImage img1, BufferedImage img2) {
	    //int offset = 2;
	    int width = img1.getWidth();
	    int height = img1.getHeight() + img2.getHeight();
	    BufferedImage newImage = new BufferedImage(width, height,
	        BufferedImage.TYPE_INT_ARGB);
	    Graphics2D g2 = newImage.createGraphics();
	    Color oldColor = g2.getColor();
	    g2.setPaint(Color.WHITE);
	    g2.fillRect(0, 0, width, height);
	    g2.setColor(oldColor);
	    g2.drawImage(img1, null, 0, 0);
	    g2.drawImage(img2, null, 0, img1.getHeight());
	    g2.dispose();
	    return newImage;
	  }
	
	protected void addUsersToCache(String userNames) throws Exception {
			System.out.println("Inside addUsersToCache");
			addToFile(uFilePath,userNames);
	}
	
	protected String readUsersFromCache() throws Exception{
		System.out.println("readUsersFromCache");
		return readFile(uFilePath);
	}
	
	protected void addCardsToCache(String cardsSeq) throws Exception{
			System.out.println("addCardsToCache");
			addToFile(cFilePath,cardsSeq);
	}
	
protected String readCardsFromCache() throws Exception{
		System.out.println("readCardsFromCache");
		return readFile(cFilePath);	
	}
	
	protected void addUCmapToCache(String uCMaps) throws Exception{
		System.out.println("addUCmapToCache");
		addToFile(mapUCFilePath,uCMaps);
	}
	
	protected String readUCmapFromCache() throws Exception{
		System.out.println("readUCmapFromCache");
		return readFile(mapUCFilePath);	
	}
	
	protected String addDealerToCache(String dealerName) throws Exception {
			System.out.println("addDealerToCache");
			return addToFile(dealerFilePath,dealerName);
	}
	
	protected String readDealerFromCache() throws Exception{
			System.out.println("readDealerFromCache");
			return readFile(dealerFilePath);
	}
	
	
	protected String addSingleBlindUserToCache(String userName) throws Exception {
			System.out.println("addSingleBlindUserToCache");
			return addToFile(generateFullFilePath(singleBlindUserFileName),userName);
	}
	
	protected String readSingleBlindUserFromCache() throws Exception{
			System.out.println("readSingleBlindUserFromCache");
			return readFile(generateFullFilePath(singleBlindUserFileName));
	}
	
	private String readFile(String filePath ) throws Exception{
		String fileData  = "";
		BufferedReader reader = null;
		try {	
			reader = new BufferedReader(new FileReader(filePath));
			fileData  = reader.readLine();
			reader.close();
		}catch(Exception e){ System.out.println(e.getMessage()); throw e;}
		finally {reader.close();}
		return fileData;
	}
	
	private String addToFile(String filePath, String fileData ) throws Exception{
		FileWriter objFW = null;
		try {	
			objFW = new FileWriter(filePath, false);
			objFW.write(fileData);
			objFW.flush();
			objFW.close();
		}catch(Exception e){ System.out.println(e.getMessage()); throw e;}
		finally {objFW.close();}
		return "SUCCESS";
	}

	
}