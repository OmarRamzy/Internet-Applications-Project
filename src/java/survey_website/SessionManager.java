package survey_website;


import java.util.HashMap;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ahmed
 */
public class SessionManager {

    private static Map instance = new HashMap();

    private SessionManager() {
    }

    public static Map get() {
        return instance;
    }
}
