/**
 * 
 */
package carddealer;




import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeEditor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author vps
 *
 */
@RestController
public class endpointClass {
	
	UserList objUserList = new UserList();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	}

	
	@RequestMapping(path = "/getTeamName/{dealerName}")
	public String getTeamName(@PathVariable("dealerName") String dealerName) throws Exception {
		
		
		return "TEAM-VEF";
	}
	@RequestMapping(path = "/setTeamName/{dealerName}")
	public String setTeamName(@PathVariable("dealerName") String dealerName) throws Exception {
		
		
		return "TEAM-VEF";
	}
	

	
	@RequestMapping(path = "/getTeamName/{dealerName}")
	public String getGameName(@PathVariable("dealerName") String dealerName) throws Exception {
		
		
		return "TEAM-VEF";
	}
	@RequestMapping(path = "/setTeamName/{dealerName}")
	public String setGameName(@PathVariable("dealerName") String dealerName) throws Exception {
		
		
		return "TEAM-VEF";
	}
	
	
	@RequestMapping(path = "/getAllUsersCards/{dealerName}")
	public String returnAllUsersCard(@PathVariable("dealerName") String dealerName) throws Exception {
		
		DealCards objDC = new DealCards(dealerName);
		return objDC.getAllUsersCards();
		//return shfldArr;
	}
	
	
	@RequestMapping(path = "/regUserStr/{uname}/u/{unqStr}")
	public String registerUserUnqStr(@PathVariable("uname") String userName, @PathVariable("unqStr") String unqStr) throws Exception {
		
		return objUserList.registerUserCode(userName, unqStr);
		//return dealerName;
	}

	@GetMapping(
			  value = "/getCardsImages/{uname}/u/{unqStr}",
			  produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
			)
	public @ResponseBody byte[] returnAssignedCardImages(@PathVariable("uname") String userName, @PathVariable("unqStr") String unqStr) throws Exception {
		UserList oULunq = new UserList(userName, unqStr);
		String[] userCards = objUserList.getUserCards(userName).split(",");
		//String tmpStr = objPS.cardList.toString();
		manageCache objMC = new manageCache();
		return objMC.getUserCardImages(userCards[0],userCards[1]);
	}
	
	@RequestMapping(path = "/shuffleCards/{dealerName}")
	public String shuffleCardsRefUsers(@PathVariable("dealerName") String dealerName) throws Exception {
		
		DealCards objSC = new DealCards(dealerName);
		String shfldArr = objSC.shuffleCardsFuncCont();
		return shfldArr;
	}

	@RequestMapping(path = "/distributeCards/{dealerName}")
	public String distributeCardsCont(@PathVariable("dealerName") String dealerName) throws Exception {
		
		DealCards objSC = new DealCards(dealerName);
		String shfldArr = objSC.distributeCardsCont();
		return shfldArr;
	}

	
	@RequestMapping(path = "/setSingleBlindUser/{dealerName}/uname/{userName}")
	public String setSingleBlindUserFunc(@PathVariable("dealerName") String dealerName, @PathVariable("userName") String userName) throws Exception {
		DealCards objDC = new DealCards(dealerName);
		return objDC.setSingleBlindUser(userName);
		//return dealerName;
	}

	@GetMapping(
			  value = "/getFlopCardsImages/{dealerName}",
			  produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
			)
	public @ResponseBody byte[] getFlopCardsImages(@PathVariable("dealerName") String dealerName) throws Exception {
		
		DealCards objSC = new DealCards(dealerName);
		String [] flopCards = objSC.getFlopCards().split(",");
		//String tmpStr = objPS.cardList.toString();
		manageCache objMC = new manageCache();
		return objMC.getCardImages(flopCards);//getFlopCardImages(flopCards[0]+".png",flopCards[1]+".png",flopCards[2]+".png");
	}
	

	
	@GetMapping(
			  value = "/getTurnCardImages/{dealerName}",
			  produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
			)
	public @ResponseBody byte[] getTurnCardImages(@PathVariable("dealerName") String dealerName) throws Exception {
		DealCards objSC = new DealCards(dealerName);
		String [] flopCards = new String [4];
		flopCards =	(objSC.getFlopCards() + "," + objSC.getTurnCard()).split(",");
		//String tmpStr = objPS.cardList.toString();
		//String turnCard 
		//flopCards[3] = objSC.getTurnCard();
		//flopCards[3]=turnCard;
		manageCache objMC = new manageCache();
		return objMC.getCardImages(flopCards);
	}
	
	@GetMapping(
			  value = "/getRiverCardImages/{dealerName}",
			  produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
			)
	public @ResponseBody byte[] getRiverCardImages(@PathVariable("dealerName") String dealerName) throws Exception {
		DealCards objSC = new DealCards(dealerName);
		String [] flopCards = new String [5];
		//flopCards =	objSC.getFlopCards().split(",");
		//String tmpStr = objPS.cardList.toString();
		//flopCards[3] = objSC.getTurnCard();
		//flopCards[3]=turnCard;
		//flopCards[4] = objSC.getRiverCard();
		flopCards =	(objSC.getFlopCards() + "," + objSC.getTurnCard() + "," + objSC.getRiverCard()).split(",");
		manageCache objMC = new manageCache();
		return objMC.getCardImages(flopCards);
	}
	
	@RequestMapping(path = "/addUsers/{unames}")
	public String addUser(@PathVariable("unames") String userNames) {
		
		UserList objUserList = new UserList();
		objUserList.addUsers(userNames);
		return "success";
	}
	
	
	@RequestMapping(path = "/cleanUp/{dealerName}")
	public String cleanUp(@PathVariable("dealerName") String dealerName) {
		
		return "NO CLEAN IMPLEMENTED YET";
	}
	
	
	@RequestMapping(path = "/setDealer/{dealerName}/{newDealerName}")
	public String setDealer(@PathVariable("dealerName") String dealerName, @PathVariable("newDealerName") String newDealerName) throws Exception {
		DealCards objDC = new DealCards(dealerName);
		return objDC.setDealer(newDealerName);
		//return dealerName;
	}
	
	@RequestMapping(path = "/getDealer")
	public String getDealer() throws Exception {
		
		DealCards objDC = new DealCards();
		return objDC.getDealer();
	}
	
//OLD Functions
	
	@RequestMapping(path = "/getCards/{uname}")
	public String returnAssignedCard(@PathVariable("uname") String userName) {
		
		//UserList objUserList = new UserList();
		String userCards = objUserList.getUserCards(userName);
		//String tmpStr = objPS.cardList.toString();
		return  userCards;
	}
	
	
	@RequestMapping(path = "/distributeCardsOld/{dealerName}")
	public String distributeCards(@PathVariable("dealerName") String dealerName) throws Exception {
		
		DealCards objSC = new DealCards(dealerName);
		String shfldArr = objSC.distributeCards();
		return shfldArr;
	}
	
	@RequestMapping(path = "/getFlopCards/{dealerName}")
	public String getFlopCards(@PathVariable("dealerName") String dealerName) throws Exception {
		
		DealCards objSC = new DealCards(dealerName);
		String flopCards = objSC.getFlopCards();
		return flopCards;
	}
	
	
	
	@RequestMapping(path = "/getRiverCard/{dealerName}")
	public String getRiverCard(@PathVariable("dealerName") String dealerName) throws Exception {
		DealCards objSC = new DealCards(dealerName);
		String riverCard = objSC.getRiverCard();
		return riverCard;
	}
	
	@RequestMapping(path = "/getTurnCard/{dealerName}")
	public String getTurnCard(@PathVariable("dealerName") String dealerName) throws Exception{
		DealCards objSC = new DealCards(dealerName);
		String turnCard = objSC.getTurnCard();
		return turnCard;
	}
	
	@GetMapping(
			  value = "/getCardsImagesOld/{uname}",
			  produces = MediaType.APPLICATION_OCTET_STREAM_VALUE
			)
	public @ResponseBody byte[] returnAssignedCardImages(@PathVariable("uname") String userName) throws Exception {
		//UserList objUserList = new UserList();
		String[] userCards = objUserList.getUserCards(userName).split(",");
		//String tmpStr = objPS.cardList.toString();
		if(userCards.length <= 1 )
			System.out.println("NO USER FOUND");
		else {
			manageCache objMC = new manageCache();
			return objMC.getUserCardImages(userCards[0],userCards[1]);
		}
		return "You are uNaughty".getBytes();
	}
	
	@RequestMapping(path = "/shuffleCardsOld/{dealerName}")
	public String shuffleCards(@PathVariable("dealerName") String dealerName) throws Exception {
		
		DealCards objSC = new DealCards(dealerName);
		String shfldArr = objSC.shuffleCardsFunc();
		return shfldArr;
	}
	
}
