package ru.sibsutis.project;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sibsutis.project.databases.User;

@Controller
public class BarterController {

    @RequestMapping(value = "/authorization", method = RequestMethod.GET)
    private String authorization() {
        return "authorization";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    private String mainPage() {
        return null;//todo
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    private String profile() {
        return null;//todo
    }





}
