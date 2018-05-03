package logic;

import java.util.ArrayList;
import java.util.UUID;

import db.UUIDDAO;

public class myUUID {
	private String UUID_ID;
	private String source_EntityID;
	private int entityTypeID;
	private int entityVersion;
	private int source_ID;
	
	//Contructor
	public myUUID(String uUID_ID, String source_EntityID, int entityTypeID, int entityVersion, int source_ID) {
		this.UUID_ID = uUID_ID;
		this.source_EntityID = source_EntityID;
		this.entityTypeID = entityTypeID;
		this.entityVersion = entityVersion;
		this.source_ID = source_ID;
	}
	
	
	//Getters and Setters
	public String getUUID_ID() {
		return UUID_ID;
	}
	public void setUUID_ID(String uUID_ID) {
		this.UUID_ID = uUID_ID;
	}

	public String getSource_EntityID() {
		return source_EntityID;
	}
	public void setSource_EntityID(String source_EntityID) {
		this.source_EntityID = source_EntityID;
	}

	public int getEntityTypeID() {
		return entityTypeID;
	}
	public void setEntityTypeID(int entityTypeID) {
		this.entityTypeID = entityTypeID;
	}

	public int getEntityVersion() {
		return entityVersion;
	}
	public void setEntityVersion(int entityVersion) {
		this.entityVersion = entityVersion;
	}

	public int getSource_ID() {
		return source_ID;
	}
	public void setSource_ID(int source_ID) {
		this.source_ID = source_ID;
	}
	
    
	// Function that generates a UUID
	public static String generateUUID() {
		UUIDDAO dao = new UUIDDAO();
		String newUUID = UUID.randomUUID().toString();
		return newUUID;
	}
    
	// THREE FUNCTIONS THAT WILL BE USED BY OTHER SYSTEMS
	
	// Function that creates new UUID row into the database
	// Parameters of this function came from message
	public static myUUID createNewUUID(String sourceEntityId, String entityName, String sourceName) {
		try {
			UUIDDAO dao = new UUIDDAO();
			String newUUID = generateUUID();
			
			ArrayList<Entity> entity = dao.getEntityByName(entityName);
			ArrayList<Source> source = dao.getSourceByName(sourceName);
			
			if(entity.isEmpty()) {
				System.out.println("Entity not known");
				return null;
			}
			if(source.isEmpty()) {
				System.out.println("Source not known");
				return null;
			}
			else {
				myUUID UUID = new myUUID(newUUID, sourceEntityId, entity.get(0).getId(), 1, source.get(0).getId());
				dao.insert(UUID);
				System.out.println("Folowing row has been created into the database: ");
				System.out.println("UUID: " + newUUID);
				System.out.println("Source_EntityID: " + sourceEntityId);
				System.out.println("Entity: " + entity.get(0).getName());
				System.out.println("Entity_Version: 1");
				System.out.println("Source: " + source.get(0).getName());
				return UUID;
			}
		}
		catch (Exception e) {
			System.out.println("Something went wrong");
			return null;
		}
	}
	
	// Function that inserts a known UUID when an other system recieves an entity
	// with a UUID Id that is not known for the system
	public static myUUID insertUUID(String UUIDToinsert, String sourceEntityId, String entityName, String sourceName) {
		try {
			UUIDDAO dao = new UUIDDAO();
			
			ArrayList<Entity> entity = dao.getEntityByName(entityName);
			ArrayList<Source> source = dao.getSourceByName(sourceName);
			
			if(dao.getByUUID(UUIDToinsert).isEmpty()) {
				System.out.println("UUID not known");
				return null;
			}
			if(entity.isEmpty()) {
				System.out.println("Entity not known");
				return null;
			}
			if(source.isEmpty()) {
				System.out.println("Source not known");
				return null;
			}
			else {
				myUUID UUID = new myUUID(UUIDToinsert, sourceEntityId, entity.get(0).getId(), 1, source.get(0).getId());
				dao.insert(UUID);
				System.out.println("Folowing row has been inserted to the database: ");
				System.out.println("UUID: " + UUIDToinsert);
				System.out.println("Source_EntityID: " + sourceEntityId);
				System.out.println("Entity: " + entityName);
				System.out.println("Entity_Version: 1");
				System.out.println("Source: " + sourceName);
				return UUID;
			}
		}
		catch(Exception e) {
			System.out.println("Something went wrong");
			return null;
		}
	}
	
	// Function that updates the EntityVersion in the database
	// the parameter is form the message
	public static myUUID updateVersion(String findUUID, String sourceName) {
		UUIDDAO dao = new UUIDDAO();
		
		ArrayList<myUUID> result = dao.getByUUIDandSourceName(findUUID, sourceName);
		
		if(result.isEmpty()) {
			System.out.println("Row not found");
			return null;
		}
		else {
			myUUID UUID = result.get(0);
			dao.updateVersion(UUID);
			System.out.println("Folowing row has been updated in the database: ");
			System.out.println("UUID: " + findUUID);
			System.out.println("SourceName: " + sourceName);
			
			result = dao.getByUUIDandSourceName(findUUID, sourceName);
			UUID = result.get(0);
			return UUID;
		}
	}
}