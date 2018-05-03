package logic;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.PathVariable;

import db.UUIDDAO;
import java.util.ArrayList;

@RestController
public class myUUIDController {

    // OK
    @RequestMapping(value = "/home")
    public ArrayList<String> create () {
        ArrayList<String> welcome = new ArrayList<String>();
        welcome.add("Welcome to UUIDMaster");
        welcome.add("USE: ");
        welcome.add("create new UUID example: http://10.3.50.48:9000/createUUID/Source_EntityId/EntityName/SourceName");
        welcome.add("Add existing UUID example: http://10.3.50.48:9000/insertUUID/UUID/Source_EntityId/EntityName/SourceName");
        welcome.add("Update existing UUID example: http://10.3.50.48:9000/updateUUID/UUID/SourceName");
        return welcome;
    }

    // OK
    @RequestMapping(value = "/createUUID/{seid}/{en}/{sn}", method=RequestMethod.GET)
    public myUUID test(@PathVariable String seid, @PathVariable String en, @PathVariable String sn){
        return myUUID.createNewUUID(seid, en, sn);
    }

    // OK
    @RequestMapping(value = "/insertUUID/{uuid}/{seid}/{en}/{sn}", method=RequestMethod.GET)
    public myUUID test(@PathVariable String uuid, @PathVariable String seid, @PathVariable String en, @PathVariable String sn){
        return myUUID.insertUUID(uuid, seid, en, sn);
    }

    // OK
    @RequestMapping(value = "/updateUUID/{uuid}/{sn}", method=RequestMethod.GET)
    public myUUID test(@PathVariable String uuid, @PathVariable String sn){
        return myUUID.updateVersion(uuid, sn);
    }
}