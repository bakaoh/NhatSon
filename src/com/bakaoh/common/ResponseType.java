/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bakaoh.common;

/**
 *
 * @author kienpc
 */
public enum ResponseType
{

    JSON("application/json; charset=utf-8"),
    PLAIN("text/plain; charset=utf-8"),
    HTML("text/html; charset=utf-8");

    private final String mime;

    ResponseType(String mime) {
        this.mime = mime;
    }

    public String getValue() {
        return mime;
    }

}
