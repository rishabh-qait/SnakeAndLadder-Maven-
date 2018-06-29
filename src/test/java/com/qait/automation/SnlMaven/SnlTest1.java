package com.qait.automation.SnlMaven;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.testng.Assert;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
 

public class SnlTest1 {
	
	
	Board obj;
	JSONObject data;
	BoardModel model;
	
	
	
	
	@Test(expectedExceptions={MaxPlayersReachedExeption.class})
	public void trying_to_add_more_than_four_players() throws FileNotFoundException, 
	UnsupportedEncodingException, PlayerExistsException, GameInProgressException, 
	MaxPlayersReachedExeption, IOException
	{
		obj.registerPlayer("Player1");
		obj.registerPlayer("Player2");
		obj.registerPlayer("Player3");
		obj.registerPlayer("Player4");
		obj.registerPlayer("Player5");	
		
	}
	
	@Test(expectedExceptions={PlayerExistsException.class})
	public void try_adding_same_name_user() throws FileNotFoundException, 
	UnsupportedEncodingException, PlayerExistsException, GameInProgressException, 
	MaxPlayersReachedExeption, IOException
	{
		obj.registerPlayer("Player1");
		obj.registerPlayer("Player1");
		
	}
	
	@Test(expectedExceptions={GameInProgressException.class})
	public void try_adding_user_when_gamestarted() throws FileNotFoundException,
	UnsupportedEncodingException, PlayerExistsException, GameInProgressException, 
	MaxPlayersReachedExeption, IOException, JSONException, InvalidTurnException
	{
		obj.registerPlayer("Player1");
		obj.registerPlayer("Player2");
		JSONArray array=new JSONArray();
		array=obj.registerPlayer("Player3");
		//array=data.getJSONArray("players");
		
		String sid;
		
		for(int i = 0; i < array.length(); i++){
            JSONObject player = array.getJSONObject(i);
            sid=(String) player.get("uuid");
            UUID id=UUID.fromString(sid);
            obj.rollDice(id);
		}
		
		obj.registerPlayer("Player4");
	}
	@Test
	public void try_deleting_user() throws FileNotFoundException,
	UnsupportedEncodingException, PlayerExistsException, GameInProgressException,
	MaxPlayersReachedExeption, IOException, NoUserWithSuchUUIDException 
	{
		
		JSONArray newarr= new JSONArray();
		newarr=obj.registerPlayer("BHOLI");
		newarr=obj.registerPlayer("BHOLA");
		newarr=obj.registerPlayer("IMLI");
		int arrlengthbeforedeleting=newarr.length();
		
			JSONObject objectinnew=newarr.getJSONObject(1);
		String uuidofplayer=(String) objectinnew.get("uuid");	
		UUID  uuidofplayer1=UUID.fromString(uuidofplayer);
			//UUID uuidofplayer1=(UUID)objectinnew.get("uuid");
			
		newarr=obj.deletePlayer(uuidofplayer1);
	
		int arrlengthafterdeleting=newarr.length();
		System.out.println(arrlengthafterdeleting);
		System.out.println(arrlengthbeforedeleting);
		Assert.assertEquals(arrlengthafterdeleting, arrlengthbeforedeleting-1);
		
	}
	
	@Test(expectedExceptions={NoUserWithSuchUUIDException.class})
	public void try_deleting_user_already_deleted() throws NoUserWithSuchUUIDException,
	PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, IOException
	{
		UUID uuidofplayers=null;
		JSONArray newplayerarr=obj.registerPlayer("RAM");
for(int i=0;i<newplayerarr.length();i++) {
	JSONObject objectinnew=newplayerarr.getJSONObject(0);
String uuidofplayer=(String) objectinnew.get("uuid");	
 uuidofplayers=UUID.fromString(uuidofplayer);
obj.deletePlayer(uuidofplayers);
}
		obj.deletePlayer(uuidofplayers);
		
		
		
        	
	}
	@Test(expectedExceptions= {NoUserWithSuchUUIDException.class})
	public void try_deleting_user_not_registered_on_board() throws NoUserWithSuchUUIDException,
	PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, IOException
	{

		UUID invalid=UUID.randomUUID();
	obj.deletePlayer(invalid);
	
	}
	@Test(expectedExceptions={InvalidTurnException.class})
	public void try_making_invalid_turn() throws FileNotFoundException, UnsupportedEncodingException,
	PlayerExistsException, GameInProgressException, MaxPlayersReachedExeption, IOException,
	JSONException, InvalidTurnException
	{
		
		String sid;
		obj.registerPlayer("Player1");
		obj.registerPlayer("Player2");
		JSONArray array=new JSONArray();
		array=obj.registerPlayer("Player3");
		//array=data.getJSONArray("players");
		
		
		for(int i =array.length()-1;i>=0; i--){
            JSONObject player = array.getJSONObject(i);
            sid=(String) player.get("uuid");
            UUID id=UUID.fromString(sid);
            obj.rollDice(id);
		}
	}
	
	
	
	
	
	
	
	
	@BeforeMethod
	public void launchwindow() throws FileNotFoundException, UnsupportedEncodingException,
	JSONException, IOException
	{
		
		obj=new Board();
		data=new JSONObject();
		model=new BoardModel();
	}}
