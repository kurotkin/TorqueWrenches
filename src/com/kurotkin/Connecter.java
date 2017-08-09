package com.kurotkin;

import com.kurotkin.model.Fastener;

import java.util.ArrayList;

/**
 * Created by Vitaly Kurotkin on 27.06.2017.
 */
public class Connecter {
    public static ArrayList<Fastener> Stahlwille() {
        return SQLreq.req();
    }
    public static ArrayList<Fastener> TohnichiCEM() {
        return TohnichiCEM.req();
    }
    public static ArrayList<Fastener> TohnichiSTC2() {
        return TohnichiSTC2.req();
    }
}
