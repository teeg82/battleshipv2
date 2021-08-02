package services.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;

import model.grid.GridFacade;
import model.profiles.UserProfile;

public class ProfileService {

	private Map<UUID, UserProfile> profiles;
	private UserProfile currentProfile;
	private Boolean isLoggedIntoProfile;
	
	private static final ProfileService service = new ProfileService();
	
	public static ProfileService getInstance(){
		return service;
	}
	
	private ProfileService(){
		currentProfile = null;
		profiles = loadProfiles();
	}
	
	public Map<UUID, String> getProfileIDAndNameMap(){
		Map<UUID, String> profileMap = new HashMap<UUID, String>();
		Set<Entry<UUID,UserProfile>> entrySet = profiles.entrySet();
		for(Entry<UUID,UserProfile> entry : entrySet){
			profileMap.put(entry.getKey(), entry.getValue().getUserName());
		}
		return profileMap;
	}
	
	@SuppressWarnings("unchecked")
	private Map<UUID, UserProfile> loadProfiles(){
		Map<UUID, UserProfile> userProfiles;
		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("profiles.dat"));
			userProfiles = (Map<UUID, UserProfile>) objectInputStream.readObject();
		} catch (Exception e) {
			System.out.println("Unable to load profiles data file.");
			userProfiles = new HashMap<UUID, UserProfile>();
		}
		return userProfiles;
	}
	
	private void saveProfiles(){
		try {
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("profiles.dat"));
			objectOutputStream.writeObject(profiles);
		} catch (Exception e) {
			System.out.println("Unable to save profiles data file.");
		}
	}
	
	public void handleCloseOperation(){
		if(currentProfile!= null){
			profiles.put(currentProfile.getUserID(), currentProfile);
	    	saveProfiles();
	    	currentProfile.clear();
	    	currentProfile = null;
		}    	
    	profiles.clear();
    	profiles = null;
    	isLoggedIntoProfile = null;
	}
	
	public UserProfile loginToProfile(UUID userID){
		currentProfile = null;
		currentProfile = profiles.get(userID);
		if(currentProfile == null){
			isLoggedIntoProfile = false;
		}else{
			isLoggedIntoProfile = true;
		}
		return currentProfile;
	}
	
	public Boolean isLoggedIn(){
		return isLoggedIntoProfile;
	}
	
	public String getUserName() {
		return currentProfile.getUserName();
	}

	public void incrementGamesPlayed() {
		currentProfile.accumulateGamesPlayed();	
	}

	public void incrementWin() {
		currentProfile.accumulateWin();	
	}

	public Object[][] getTableProfileData() {
		if(profiles == null || profiles.isEmpty()){
			profiles = this.loadProfiles();
		}
		if(currentProfile != null){
			profiles.put(currentProfile.getUserID(), currentProfile);
		}
		
		Set<Entry<UUID, UserProfile>> entrySet = profiles.entrySet();
		Object[][] tableProfileData = new Object[entrySet.size()][5];
		int index = 0;
		for(Entry<UUID, UserProfile> entry : entrySet){
			UserProfile profile = entry.getValue();
			Object[] data = {profile.getUserID(), profile.getUserName(), profile.getWins(), profile.getGamesPlayed(), (profile.getGrid() != null ? "Yes" : "No")};
			tableProfileData[index] = data;
			index++;
		}
		return tableProfileData;
	}

	public String getUserProfileText() {
		return currentProfile.toString();
	}

	public Object[] createNewProfile(String name) {
		UserProfile profile = new UserProfile();
		profile.setUserID(UUID.randomUUID());
		profile.setUserName(name);
		profile.setWins(0);
		profile.setGamesPlayed(0);
		
		profiles.put(profile.getUserID(), profile);
		this.saveProfiles();
		
		return new Object[]{profile.getUserID(), name, 0, 0};
	}

	public void deleteProfile(UUID selectedID) {
		profiles.remove(selectedID);
		this.saveProfiles();
	}

	public void saveGame(GridFacade grid) {
		// TODO Auto-generated method stub
		
	}
}