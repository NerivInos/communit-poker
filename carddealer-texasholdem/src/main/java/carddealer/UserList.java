/**
 * 
 */
package carddealer;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author vps
 *
 */
public class UserList {

	manageCache objMC = new manageCache();
	String mapUCStr = "";
	String flopCardsStr = "";
	String turnCardStr = "";
	String riverCardStr = "";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int i=0;
	}

	UserList(){}
	UserList(String userName, String unqStr) throws Exception {
		if (unqStr.equalsIgnoreCase(getUserCode(userName)) || unqStr  == "AWESOME") {
				System.out.println("UserName is : " + userName + " - Unique Str:" + unqStr);
		}
		else {
			System.out.println("UserName is : " + userName + " - Unique Str:" + unqStr);
			throw new Exception("Incorrect User String Supplied.");
		}
	}
	
	protected String registerUserCode(String userName, String unqStr) {
		
		try {
		objMC.addUserCodeToCache(userName, unqStr );
		}
		catch(Exception e) {System.out.println(e.getMessage());}
		return "Success";
	}
	
	protected String getUserCode(String userName) {
		String userCode = "";
		try {
		userCode = objMC.getUserCodeFromCache(userName);
		}
		catch(Exception e) {System.out.println(e.getMessage());}
		return userCode;
	}
	
	
	public String addUsers(String userNames) {
		
		try {
		objMC.addUsersToCache(userNames);
		}
		catch(Exception e) {System.out.println(e.getMessage());}
		return "Success";
	}
	
	public String getUserCards(String userName) {
		
		ArrayList<String> objUCList = getUsersCardsList();
		Integer ucCount = objUCList.size();
		String userCards = "";
		for(int x=0; x < ucCount; x++) {
			if (objUCList.get(x).split(":")[0].equalsIgnoreCase(userName)) {
				userCards = objUCList.get(x).split(":")[1];
				break;
			}
		}
		
		return userCards;
	}
	
	protected ArrayList<String> getUsersCardsList() {
		ArrayList<String> objUCList = null;
		try {
			objUCList = new ArrayList<String>(Arrays.asList(objMC.readUCmapFromCache().split(";")));
		}catch(Exception e){System.out.println(e.getMessage());}
		return objUCList;
	}
}

