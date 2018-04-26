package hello;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import db.UUIDDAO;

@RestController
public class myUUIDController {

    @RequestMapping(value = "/createUUID")
    public myUUID create (@RequestParam(value="seid", defaultValue="none") String seid, @RequestParam(value="en", defaultValue="none") String en, @RequestParam(value="sn", defaultValue="none") String sn) {
        return myUUID.createNewUUID(seid, en, sn);
    }

    @RequestMapping(value = "/insertUUID")
    public String create (@RequestParam(value="uuid", defaultValue="none") String uuid, @RequestParam(value="en", defaultValue="none") String en) {
        return "test";
        //return myUUID.insertUUID(uuid, seid, en, sn);
    }
}