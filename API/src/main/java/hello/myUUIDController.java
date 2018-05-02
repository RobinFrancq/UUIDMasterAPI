package hello;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import db.UUIDDAO;
import java.util.ArrayList;

@RestController
public class myUUIDController {

    // OK
    @RequestMapping(value = "/home")
    public ArrayList<myUUID> create () {
        
        UUIDDAO dao = new UUIDDAO();
        ArrayList<myUUID> database = dao.getAll();

    /*
        for(int i=0; i<database.size(); i++){
        	String uuid = database.get(i).getUUID_ID();
        	String sourceEntityId = database.get(i).getSource_EntityID();
        	int entityTypeId = database.get(i).getEntityTypeID();
        	int entityVersion = database.get(i).getEntityVersion();
        	int sourceId = database.get(i).getSource_ID();
        	
        	result = result + uuid + "\t\t";
        	result = result + sourceEntityId + "\t\t";
        	result = result + entityTypeId + "\t\t";
        	result = result + entityVersion + "\t\t";
        	result = result + sourceId + "\n";
        }
        */
        
        return database;
    }

    // OK
    @RequestMapping(value = "/createUUID")
    public myUUID create (@RequestParam(value="seid", defaultValue="none") String seid, @RequestParam(value="en", defaultValue="none") String en, @RequestParam(value="sn", defaultValue="none") String sn) {
        return myUUID.createNewUUID(seid, en, sn);
    }

    // OK
    @RequestMapping(value = "/insertUUID")
    public myUUID create (@RequestParam(value="uuid", defaultValue="none") String uuid, @RequestParam(value="seid", defaultValue="none") String seid, @RequestParam(value="en", defaultValue="none") String en, @RequestParam(value="sn", defaultValue="none") String sn) {
        return myUUID.insertUUID(uuid, seid, en, sn);
    }

    // OK
    @RequestMapping(value = "/updateUUID")
    public myUUID create (@RequestParam(value="uuid", defaultValue="none") String uuid, @RequestParam(value="sn", defaultValue="none") String sn) {
        return myUUID.updateVersion(uuid, sn);
    }
    
}