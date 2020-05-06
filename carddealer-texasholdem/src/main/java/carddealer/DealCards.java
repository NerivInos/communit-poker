/**
 * 
 */
package carddealer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author vps
 *
 */
public class DealCards  {

	String cardsStr = "";
	manageCache objMC = new manageCache();
	String mapUCStr = "";
	String flopCardsStr = "";
	String turnCardStr = "";
	String riverCardStr = "";
	/**
	 * @param args
	 */
	
	DealCards(){}
	DealCards(String dealerName) throws Exception {
		if (dealerName.equalsIgnoreCase(getDealer()) || dealerName == "AWESOME") 
			System.out.println("Dealer is : " + dealerName);
		else {
			System.out.println("Dealer is : " + dealerName);
			//throw new Exception("You are not a dealer. Please set dealer first.");
		}
	}
	
	protected String getDealer() throws Exception{
		return objMC.readDealerFromCache();
	}
	protected String setDealer(String dealerName) throws Exception {
		objMC.addDealerToCache(dealerName);
		return getDealer();
	}
	
	protected String resetUserList() {
		
		return "SUCCESS";
	}
	
	protected String getAllUsersCards() {
		UserList objUL = new UserList();
		return Arrays.toString(objUL.getUsersCardsList().toArray());
	}
	
	public void saveToCache(String cardsStr) throws Exception {
		manageCache objMC = new manageCache();
		objMC.addCardsToCache(cardsStr);
	}
	
	private String[] getCardsToShuffle(Boolean newDeck) {
		String[] cardNums = {"AS","2S","3S","4S","5S","6S","7S","8S","9S","10S","JS","QS","KS","AD","2D","3D","4D","5D","6D","7D","8D","9D","10D","JD","QD","KD","AH","2H","3H","4H","5H","6H","7H","8H","9H","10H","JH","QH","KH","AC","2C","3C","4C","5C","6C","7C","8C","9C","10C","JC","QC","KC"};
		
		if (!newDeck) {
			try {
			getCardList().toArray(cardNums);
			}catch(Exception e) {System.out.println("Get Card List from cache Function failed."); System.out.println(e.getMessage());}
		}
		return cardNums;
	}
	
	public String shuffleCardsFunc()  {
		return shuffleCards(true);
	}

	public String shuffleCardsFuncCont()  {
		System.out.println("Inside shuffleCardsFuncCont ");
		return shuffleCards(false);
	}
	
	public String shuffleCards(Boolean newDeck) {
		try {
		String[] cardNums = getCardsToShuffle(newDeck);

		List<String> cardList = Arrays.asList(cardNums);
		Collections.shuffle(cardList);
		Collections.shuffle(cardList);
		Collections.shuffle(cardList);
		
		System.out.println("Inside shuffleCardsFunc ");
		cardList.toArray(cardNums);
		String cardsStr = Arrays.toString(cardNums).replaceAll("[^a-zA-Z0-9,]+","");
		saveToCache(cardsStr);
		
		System.out.println(cardsStr);
		System.out.println("END shuffleCardsFunc");
		}
		catch(Exception e) {System.out.println(e.getMessage());}
		return "Cards Shuffled Successfully.";
	}
	
	
	public String distributeCards() {
		
		try {
		ArrayList<String> objUList = getUserList();
		ArrayList<String> objCList = getCardList(); 
		Integer noOfUsers = objUList.size();
		for(int x=0;x < noOfUsers;x++)
		{
			String tmpUN = objUList.get(x);
			String cardNos= objCList.get(x) + "," + objCList.get(x + noOfUsers);
			
			mapUCStr = mapUCStr + ";" + tmpUN + ":" + cardNos;
		}
		//manageCache objMC = new manageCache();
		objMC.addUCmapToCache(mapUCStr);
		System.out.println("END distributeCards - " + mapUCStr);
		
		}catch(Exception e) {}
		return "Cards distributed successfully.";
		
	}

	public String distributeCardsCont() {
		
		try {
		ArrayList<String> objUList = getUserList();
		ArrayList<String> objCList = getCardList(); 
		
		String singleBlindUser = "";
		try {
		singleBlindUser = getSingleBlindUser();
		}catch(Exception e) {System.out.println("GET Single Blind User Function failed."); System.out.println(e.getMessage());}
		
		int indexSBU = objUList.indexOf(singleBlindUser);
		int nextSBU = indexSBU+1;
		Integer noOfUsers = objUList.size();
		int counter = 0;
		indexSBU++;
		while (counter < noOfUsers) {
			
			String tmpUN = "";
			String cardNos= objCList.get(counter) + "," + objCList.get(counter + noOfUsers);			
			
			int uInd = indexSBU;
			if(indexSBU >= noOfUsers) {
				uInd = indexSBU - noOfUsers;
			}
			tmpUN = objUList.get(uInd);
			mapUCStr = mapUCStr + ";" + tmpUN + ":" + cardNos;
			indexSBU++;
			counter++;
		}
		//manageCache objMC = new manageCache();
		objMC.addUCmapToCache(mapUCStr);
		System.out.println("END distributeCards - " + mapUCStr);
		
		try {
			if(nextSBU >= noOfUsers) {
				nextSBU = nextSBU - noOfUsers;
			}
			System.out.println(setSingleBlindUser(objUList.get(nextSBU)));
		}catch(Exception e) {System.out.println("ADD Single Blind User Function failed."); System.out.println(e.getMessage());}
		
		}catch(Exception e) {}
		return "Cards distributed successfully.";
		
	}
	
	protected String setSingleBlindUser(String userName) throws Exception {
		
		objMC.addSingleBlindUserToCache(userName);
		return "Successfully set: " + userName + " as Single Blind user.";
	}
	
	protected String getSingleBlindUser() throws Exception {
		return objMC.readSingleBlindUserFromCache();
	}
	
	private ArrayList<String> getUserList() {
		ArrayList<String> objUList = null;
		try {
			
			objUList = new ArrayList<String>(Arrays.asList(objMC.readUsersFromCache().split(",")));
	}catch(Exception e) {}
		return objUList;
	}
	
	private ArrayList<String> getCardList() {
		ArrayList<String> objCList = null;
		try {
			objCList = new ArrayList<String>(Arrays.asList(objMC.readCardsFromCache().split(",")));
		}catch(Exception e) {}
		return objCList;
	}
	
	
	public String getFlopCards() {
		try {
			ArrayList<String> objUList = getUserList();
			ArrayList<String> objCList = getCardList();
			Integer noOfUsers = objUList.size();
			Integer flopCardIndex = noOfUsers*2+1;
			flopCardsStr = objCList.get(flopCardIndex) + "," + objCList.get(flopCardIndex + 1) + "," + objCList.get(flopCardIndex + 2);
			System.out.println("END Flop Cards: " + flopCardsStr);
		}catch(Exception e) {}
		return flopCardsStr;
	}
	
	public String getTurnCard() {
		try {
			ArrayList<String> objUList = getUserList();
			ArrayList<String> objCList = getCardList();
			Integer noOfUsers = objUList.size();
			Integer flopCardIndex = noOfUsers*2+1;
			
			turnCardStr = objCList.get(flopCardIndex + 4);
			System.out.println("END Turn Card: " + turnCardStr);
		}catch(Exception e) {}
		return turnCardStr;
	}
	
	public String getRiverCard() {
		try {
			ArrayList<String> objUList = getUserList();
			ArrayList<String> objCList = getCardList();
			Integer noOfUsers = objUList.size();
			Integer flopCardIndex = noOfUsers*2+1;
			
			riverCardStr = objCList.get(flopCardIndex + 6);
			System.out.println("END River Card: " + riverCardStr);
		}catch(Exception e) {}
		return riverCardStr;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=1;i<=52;i++) {
			if (i <= 13)
				System.out.print( "\"" + i +  "S\"," );
			else if (i <= 26) {
			
				System.out.print( "\"" + (i-13) +  "D\"," );
			}
			else if (i <= 39)
				System.out.print( "\"" + (i-26) +  "H\"," );
			else
				System.out.print( "\"" + (i-39) +  "C\"," );
			
		}
	} 
	
}
