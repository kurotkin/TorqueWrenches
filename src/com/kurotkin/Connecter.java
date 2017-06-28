package com.kurotkin;

import java.util.ArrayList;

/**
 * Created by Vitaly Kurotkin on 27.06.2017.
 */
public class Connecter {
    public static ArrayList<Fastener> Stahlwille() {
        return SQLreq.req();
    }
}
